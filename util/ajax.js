// 引入 ajax 模块
import ajax from 'ajax'
import cfg from '@/util/environment.js';
import {
	setAuthtoken,
	setExternalLogintoken,
	removeAuthtoken,
	removeExternalLogintoken,
	removeUserInfo
} from '@/util/auth.js'
// 创建请求实例
const instance = ajax.create({
	// 初始配置
	baseURL: cfg.BASE_API
})
// 添加请求拦截器
instance.interceptors.request.use(
	config => {
		// 在发送请求前做些什么
		config.header = Object.assign({
			'x-access-token': uni.getStorageSync("vue_authtoken"),
			// 取出缓存中的租户id，店铺id就是租户id
			'x-tenant-id': uni.getStorageSync("shop").id,
			//客户端ip
			'x-access-ip': uni.getStorageSync("x-access-ip"),
			'Referer': 'https://www.yingyukeji.cn/'
		}, config.headers);
		return config
	},
	error => {
		// 对请求错误做些什么
		return Promise.reject(error)
	}
)


// 添加响应拦截器
instance.interceptors.response.use(
	response => {
		// console.log('添加响应拦截器');
		// 对响应数据做些什么
		if (!response.data.success && response.data.errorCode != 'E0752') {
			uni.showToast({
				title: response.data.errorMsg,
				icon: "none",
				duration: 2500
			})
			return Promise.reject(response.data.errorCode)
		}
		
		//令牌过期处理
		if (response.data.errorCode == 'E0752' || response.data.errorCode == 'E0758') {
			/* //这是失效后直接退出*/
			uni.showModal({
				title: '提示',
				content: '登录已过期，请重新登录',
				showCancel:false,
				success: function (res) {
					uni.showLoading();
					removeAuthtoken();
					removeExternalLogintoken();
					removeUserInfo();
					setTimeout(() => {
						uni.redirectTo({
							url: "/pages/user/login"
						});
						uni.hideLoading();
					}, 500)
				}
			});
			
			
			/* //实现无感刷新token操作
			const config = response.config
			//刷新token接口
			ajax.post({
				url: cfg.BASE_API + "/user/refresh",
				data: {
					'token': uni.getStorageSync("vue_externalLogintoken")
				},
				success: (res) => {
					// 重置token
					config.header['x-access-token'] = res.data.token
					//重新设置本地缓存token
					setAuthtoken(res.data.token)
					setExternalLogintoken(res.data.refreshToken)
					//再次重新请求
					return instance(config)
				}
			}) */
		}
		return response.data
	},
	error => {
		console.log("请求错误");
		//令牌过期处理
		if (error.data.errorCode == 'E0752' || error.data.errorCode == 'E0758') {
			/* //这是失效后直接退出*/
			uni.showModal({
				title: '提示',
				content: '登录已过期，请重新登录',
				showCancel:false,
				success: function (res) {
					uni.showLoading();
					removeAuthtoken();
					removeExternalLogintoken();
					removeUserInfo();
					setTimeout(() => {
						uni.redirectTo({
							url: "/pages/user/login"
						});
						uni.hideLoading();
					}, 500)
				}
			});
			
			
			/* //实现无感刷新token操作
			const config = response.config
			//刷新token接口
			ajax.post({
				url: cfg.BASE_API + "/user/refresh",
				data: {
					'token': uni.getStorageSync("vue_externalLogintoken")
				},
				success: (res) => {
					// 重置token
					config.header['x-access-token'] = res.data.token
					//重新设置本地缓存token
					setAuthtoken(res.data.token)
					setExternalLogintoken(res.data.refreshToken)
					//再次重新请求
					return instance(config)
				}
			}) */
		}else{
			uni.showToast({
				title: "接口错误"
			})			
		}

		// 对响应错误做些什么
		return Promise.reject(error)
	}
)

// 导出 create 创建后的实例
export default instance
/* 后端源码请联系;QQ419367301
飞机Telegram账号:qihang9981 */