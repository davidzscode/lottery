package com.qihang.config;//package com.qihang.config;
//
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//
///**  由于可以加盟不同的代理，所以支付的方式不能写死，所以这里全部注释
// * @author qihang
// */
//@Component
//@Data
//@ConfigurationProperties(prefix = "alipay")
//public class AliPayConfig {
//
//    private String appId;
//    private String alipayPrivateKey;
//    private String alipayPublicKey;
//    private String apiGateway;
//    private String charset;
//    private String format;
//    private String signType;
//
//    @Bean
//    public AlipayClient AlipayClient() {
//        AlipayClient alipayClient = new DefaultAlipayClient(apiGateway, appId, alipayPrivateKey, format, charset, alipayPublicKey, signType);
//        return alipayClient;
//    }
//}
