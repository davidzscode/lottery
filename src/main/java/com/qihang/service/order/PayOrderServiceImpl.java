package com.qihang.service.order;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.log.LogUtil;
import com.qihang.common.util.order.OrderNumberGenerationUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.constant.Constant;
import com.qihang.controller.order.admin.pay.dto.PayOrderQueryDTO;
import com.qihang.controller.order.admin.pay.vo.PayOrderQueryVO;
import com.qihang.controller.order.app.pay.dto.PayCreateOrderDTO;
import com.qihang.controller.order.app.pay.dto.PayOrderDTO;
import com.qihang.controller.order.app.pay.dto.PayOrderListDTO;
import com.qihang.controller.order.app.pay.vo.GetPayOrderListVO;
import com.qihang.controller.order.app.pay.vo.PayOrderListVO;
import com.qihang.controller.order.app.pay.vo.PayOrderVO;
import com.qihang.controller.order.app.pay.vo.PayUrlVO;
import com.qihang.domain.dict.SysDictDO;
import com.qihang.domain.order.PayOrderDO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.order.pay.PayOrderStateEnum;
import com.qihang.enumeration.order.pay.PayOrderTypeEnum;
import com.qihang.enumeration.user.IsAgentEnum;
import com.qihang.mapper.dict.SysDictMapper;
import com.qihang.mapper.order.PayOrderMapper;
import com.qihang.mapper.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qihang
 * @since 2022-10-13
 */
@Service
@Slf4j
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrderDO> implements IPayOrderService {
    
    @Resource
    private SysDictMapper sysDictMapper;
    
    @Resource
    private LogUtil logUtil;
    
    @Resource
    private PayOrderMapper payOrderMapper;
    
    @Value("${alipay.notify_url}")
    private String notifyUrl;
    
    @Value("${alipay.return_url}")
    private String returnUrl;
    
    @Value("${alipay.api_gateway}")
    private String apiGateway;
    
    @Value("${alipay.charset}")
    private String charset;
    
    @Value("${alipay.format}")
    private String format;
    
    @Value("${alipay.sign_type}")
    private String signType;
    
    @Resource
    private UserMapper userMapper;
    
    /**
     * 查询自己的订单
     * @param payOrderDTO
     * @param userId
     */
    @Override
    public PayOrderListVO<PayOrderVO> list(PayOrderDTO payOrderDTO, Integer userId) {
        PayOrderListVO<PayOrderVO> commonList = new PayOrderListVO<>();
        if (payOrderDTO.getUserId() != null) {
            userId = payOrderDTO.getUserId();
        }
        List<String> typeList = new ArrayList<>();
        if (StrUtil.isNotBlank(payOrderDTO.getType())) {
            switch (payOrderDTO.getType()) {
                case "1":
                    typeList.addAll(List.of(new String[] {"1", "2", "3", "4"}));
                    break;
                case "2":
                    typeList.add("99");
                    break;
                case "3":
                    typeList.addAll(List.of(new String[] {"0", "5"}));
                    break;
                case "4":
                    typeList.addAll(List.of(new String[] {"6", "7", "8", "9"}));
                    break;
                case "5":
                    typeList.addAll(List.of(new String[] {"14", "30"}));
                    break;
                case "6":
                    typeList.addAll(List.of(new String[] {"98"}));
                    break;
                default:
                    break;
            }
        }
        //分页
        Page<PayOrderDO> page = new Page<>(payOrderDTO.getPageNo(), payOrderDTO.getPageSize());
        LambdaQueryWrapper<PayOrderDO> qw = new QueryWrapper<PayOrderDO>().lambda();
        //动态拼接查询条件
        qw.in(!typeList.isEmpty(), PayOrderDO::getType, typeList);
        qw.eq(PayOrderDO::getUserId, userId);
        qw.ge(payOrderDTO.getStartTime() != null, PayOrderDO::getCreateTime, payOrderDTO.getStartTime());
        qw.le(payOrderDTO.getEndTime() != null, PayOrderDO::getCreateTime, payOrderDTO.getEndTime());
        qw.and(item -> item.eq(PayOrderDO::getState, PayOrderStateEnum.PAID.getKey())
                .or()
                .eq(PayOrderDO::getState, PayOrderStateEnum.PAYMENT.getKey()));
        qw.orderByDesc(PayOrderDO::getCreateTime);
        Page<PayOrderDO> payOrderPage = payOrderMapper.selectPage(page, qw);
        commonList.setTotal(payOrderPage.getTotal());
        commonList.setVoList(BeanUtil.copyToList(payOrderPage.getRecords(), PayOrderVO.class));
        BigDecimal sumPrice = new BigDecimal(0);
        for (PayOrderVO payOrderVO : commonList.getVoList()) {
            if (payOrderVO.getPrice() != null) {
                sumPrice = sumPrice.add(payOrderVO.getPrice());
            }
        }
        commonList.setSumPrice(sumPrice);
        return commonList;
    }
    
    
    /**
     * 分类明细列表接口
     * @param payOrderDTO
     * @param shopkeeperId
     */
    @Override
    public CommonListVO<GetPayOrderListVO> getPayOrderList(PayOrderListDTO payOrderDTO, Integer shopkeeperId) {
        Integer tenantId = payOrderDTO.getTenantId();
        CommonListVO<GetPayOrderListVO> commonList = new CommonListVO<>();
        if (shopkeeperId != null) {
            UserDO shopkeeper = userMapper.selectById(shopkeeperId);
            if (!shopkeeper.getIsAgent().equals(IsAgentEnum.shopkeeper.getKey()) || shopkeeper.getTenantId() == null) {
                commonList.setSuccess(false);
                commonList.setErrorMsg("只有店主能查询自己店的用户");
                return commonList;
            }
        }
        
        Date startTime;
        if (payOrderDTO.getStartTime() == null) {
            startTime = DateUtil.parse(DateUtil.today() + " 00:00:00");
        } else {
            startTime = payOrderDTO.getStartTime();
        }
        Date endTime;
        if (payOrderDTO.getEndTime() == null) {
            endTime = DateUtil.parse(DateUtil.today() + " 23:59:59");
        } else {
            endTime = payOrderDTO.getEndTime();
        }
        //分页
        Page<PayOrderDO> page = new Page<>(payOrderDTO.getPageNo(), payOrderDTO.getPageSize());
        LambdaQueryWrapper<PayOrderDO> qw = new QueryWrapper<PayOrderDO>().lambda();
        //动态拼接查询条件
        qw.eq(PayOrderDO::getType, payOrderDTO.getType());
        qw.eq(PayOrderDO::getState, payOrderDTO.getState());
        qw.eq(PayOrderDO::getPayType, payOrderDTO.getPayType());
        qw.ge(PayOrderDO::getCreateTime, startTime);
        qw.le(PayOrderDO::getCreateTime, endTime);
        qw.eq(tenantId != null, PayOrderDO::getTenantId, tenantId);
        qw.orderByDesc(PayOrderDO::getCreateTime);
        Page<PayOrderDO> payOrderPage = payOrderMapper.selectPage(page, qw);
        Map<Integer, UserDO> userMap = userMapper.selectList(new QueryWrapper<UserDO>().lambda()
                        .eq(tenantId != null, UserDO::getTenantId, tenantId))
                .stream().collect(Collectors.toMap(UserDO::getId, UserDO -> UserDO));
        List<GetPayOrderListVO> getPayOrderListVOS = BeanUtil.copyToList(payOrderPage.getRecords(), GetPayOrderListVO.class);
        for (GetPayOrderListVO getPayOrderListVO : getPayOrderListVOS) {
            if (getPayOrderListVO != null && getPayOrderListVO.getUserId() != null && userMap.get(getPayOrderListVO.getUserId()) != null) {
                getPayOrderListVO.setNickname(userMap.get(getPayOrderListVO.getUserId()).getNickname());
                getPayOrderListVO.setPhone(userMap.get(getPayOrderListVO.getUserId()).getPhone());
            }
            
        }
        commonList.setTotal(payOrderPage.getTotal());
        commonList.setVoList(getPayOrderListVOS);
        return commonList;
    }
    
    @Override
    public BaseVO create(PayCreateOrderDTO payCreateOrder, Integer userId) throws AlipayApiException {
        //动态根据不同的key+租户id查询对应的的支付宝配置
        SysDictDO sysDictDO = sysDictMapper.selectOne(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getCode, Constant.ALIPAY_APPID_KAY)
                .eq(payCreateOrder.getChannelId() != null, SysDictDO::getChannelId, payCreateOrder.getChannelId()));
        String appId = sysDictDO.getValue();
        sysDictDO = sysDictMapper.selectOne(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getCode, Constant.ALIPAY_PUBLIC_KAY)
                .eq(payCreateOrder.getChannelId() != null, SysDictDO::getChannelId, payCreateOrder.getChannelId()));
        String alipayPublicKey = sysDictDO.getValue();
        sysDictDO = sysDictMapper.selectOne(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getCode, Constant.ALIPAY_PRIVATE_KAY)
                .eq(payCreateOrder.getChannelId() != null, SysDictDO::getChannelId, payCreateOrder.getChannelId()));
        String alipayPrivateKey = sysDictDO.getValue();
        //初始化支付配置
        AlipayClient alipayClient = new DefaultAlipayClient(apiGateway, appId, alipayPrivateKey, format, charset, alipayPublicKey, signType);
        
        UserDO userDO = userMapper.selectById(userId);
        
        PayUrlVO payUrlVO = new PayUrlVO();
        //创建订单
        PayOrderDO payOrder = new PayOrderDO();
        String orderId = OrderNumberGenerationUtil.getOrderId();
        payOrder.setOrderId(orderId);
        payOrder.setCreateTime(new Date());
        payOrder.setPrice(payCreateOrder.getPrice());
        payOrder.setState(PayOrderStateEnum.UNPAID.getKey());
        payOrder.setPayType(payCreateOrder.getPayType());
        payOrder.setUserId(userId);
        payOrder.setType(PayOrderTypeEnum.AUTO_RECHARGE.getKey());
        payOrder.setChannelId(payCreateOrder.getChannelId());
        //订单入库
        payOrderMapper.insert(payOrder);
        
        //请求支付宝获取当面付的二维码地址
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        JSONObject jo = new JSONObject();
        //设置你的订单号
        jo.putOpt("out_trade_no", orderId);
        //设置金额
        jo.putOpt("total_amount", payCreateOrder.getPrice());
        //设置标题名称
        jo.putOpt("subject", "【" + userDO.getNickname() + "-充值" + payCreateOrder.getPrice() + "元】");
        //当面付二维码
        jo.putOpt("scene", "bar_code");
        request.setBizContent(jo.toString());
        //发起请求获取响应信息
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        //获取支付链接response = {AlipayTradePrecreateResponse@14835}
        //app 打开支付宝扫码付款功能（当面付） saId=10000007 打开支付宝扫一扫功能 qrcode 请求网址（二维码网址）
        payUrlVO.setUrl("alipays://platformapi/startapp?saId=10000007&qrcode=" + response.getQrCode());
        return payUrlVO;
    }
    
    @Override
    @TenantIgnore
    public String callback(HttpServletRequest request) throws AlipayApiException {
        
        log.info("callback【{}】", request.getParameterMap());
        if (ObjectUtil.equal(request.getParameter("trade_status"), "TRADE_SUCCESS")) {
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            String orderId = params.get("out_trade_no");
            BigDecimal price = Convert.toBigDecimal(params.get("total_amount"));
            DateTime dateTime = DateUtil.parse(params.get("gmt_payment"));
            String sign = params.get("sign");
            PayOrderDO payOrder = payOrderMapper.selectOne(new QueryWrapper<PayOrderDO>().lambda().eq(PayOrderDO::getOrderId, orderId));
            //防止重复回调
            if (ObjectUtil.equal(payOrder.getState(), PayOrderStateEnum.PAID.getKey())) {
                return "success";
            }
            String content = AlipaySignature.getSignCheckContentV1(params);
            //根据key+租户id查询对应的公钥
            SysDictDO sysDictDO = sysDictMapper.selectOne(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getCode, Constant.ALIPAY_PUBLIC_KAY).eq(SysDictDO::getTenantId, payOrder.getTenantId()).eq(SysDictDO::getChannelId, payOrder.getChannelId()));
            String alipayPublicKey = sysDictDO.getValue();
            // 验证签名
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, alipayPublicKey, "UTF-8");
            // 支付宝验签
            if (checkSignature) {
                // 验签通过 修改订单
                payOrder.setState(PayOrderStateEnum.PAID.getKey());
                //支付时间
                payOrder.setUpdateTime(dateTime);
                payOrderMapper.updateById(payOrder);
                //给用户充值金
                UserDO user = userMapper.selectById(payOrder.getUserId());
                user.setGold(user.getGold().add(price));
                userMapper.updateById(user);
                logUtil.record("充值金额【" + price + "】,成功", user.getPhone(), user.getTenantId());
            }
        }
        return "success";
    }
    
    @Override
    public CommonListVO<PayOrderQueryVO> getAdminPayOrderPage(PayOrderQueryDTO payOrderQuery) {
        CommonListVO<PayOrderQueryVO> commonList = new CommonListVO<>();
        Integer userId = null;
        UserDO user = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getPhone, payOrderQuery.getPhone()));
        if (ObjectUtil.isNotNull(user)) {
            userId = user.getId();
        } else {
            if (StrUtil.isNotBlank(payOrderQuery.getPhone())) {
                return commonList;
            }
        }
        //分页
        Page<PayOrderDO> page = new Page<>(payOrderQuery.getPageNo(), payOrderQuery.getPageSize());
        LambdaQueryWrapper<PayOrderDO> qw = new QueryWrapper<PayOrderDO>().lambda();
        //动态拼接查询条件
        qw.eq(ObjectUtil.isNotNull(userId), PayOrderDO::getUserId, userId);
        qw.eq(StrUtil.isNotBlank(payOrderQuery.getOrderId()), PayOrderDO::getOrderId, payOrderQuery.getOrderId());
        qw.eq(StrUtil.isNotBlank(payOrderQuery.getState()), PayOrderDO::getState, payOrderQuery.getState());
        qw.eq(StrUtil.isNotBlank(payOrderQuery.getType()), PayOrderDO::getType, payOrderQuery.getType());
        qw.orderByDesc(PayOrderDO::getCreateTime);
        Page<PayOrderDO> payOrderPage = payOrderMapper.selectPage(page, qw);
        commonList.setTotal(payOrderPage.getTotal());
        List<PayOrderQueryVO> payOrderQueryVOList = new ArrayList<>();
        for (PayOrderDO payOrder : payOrderPage.getRecords()) {
            PayOrderQueryVO payOrderQueryVO = new PayOrderQueryVO();
            BeanUtils.copyProperties(payOrder, payOrderQueryVO);
            String nickname = userMapper.selectById(payOrder.getUserId()).getNickname();
            payOrderQueryVO.setNickname(nickname);
            payOrderQueryVOList.add(payOrderQueryVO);
        }
        commonList.setVoList(payOrderQueryVOList);
        return commonList;
    }
    
}
