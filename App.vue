<script>
	export default {
		globalData: {
			updateTime: 1693859225
		},
		created() {
			// 在created生命周期钩子中创建全局定时任务
			// this.startGlobalTimer();
			this.taskFunction();
			// 每隔5秒执行一次任务
			this.taskTimer = setInterval(() => {
				this.taskFunction();
			}, 5000); // 5秒间隔
		},
		methods: {
			taskFunction() {
				// 在这里执行你的任务逻辑
				let updateTime = uni.getStorageSync('updateTime');
				console.log(updateTime);
				if (updateTime == undefined) {
					updateTime = Vue.prototype.$updateTime;
					uni.setStorageSync('updateTime', updateTime);
				} else {
					const timestamp = Math.floor(new Date().getTime() / 1000);
					// uni.setGlobalData('updateTime', updateTime);
					uni.setStorageSync('updateTime', updateTime);
					console.log('任务正在执行...');
					console.log(updateTime);
					console.log('任务正在执行...');
				}
			},
			init() {
				uni.showLoading({
					title: "正在初始化网络"
				})
			}
		},
		onLaunch: function(optios) {
			//token判断存在跳转至首页 不存在跳转登录页
			// #ifdef APP-PLUS
			// plus.navigator.setFullscreen(true);//隐藏状态栏
			let token = uni.getStorageSync("vue_authtoken");
			if (token) {
				//存在则关闭启动页进入首页
				plus.navigator.closeSplashscreen();
			} else {
				//不存在则跳转至登录页
				uni.reLaunch({
					url: "/pages/user/login",
					success: () => {
						plus.navigator.closeSplashscreen();
					}
				})
			}
			// #endif

			// #ifdef H5
			let token = uni.getStorageSync("vue_authtoken");
			if (token) {
				if (optios.path === 'pages/user/login') {
					//存在则关闭启动页进入首页
					uni.redirectTo({
						url: "/pages/home/home"
					})
				}
			} else {
				if (optios.path != 'pages/user/register') {
					//不存在则跳转至登录页
					//存在则关闭启动页进入首页
					uni.redirectTo({
						url: "/pages/user/login"
					})
				}
			}
			// #endif

		},
		onShow: function() {},
		onHide: function() {}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	/* 注意要写在第一行，同时给style标签加入lang="scss"属性 */
	@import "uview-ui/index.scss";
	@import "cl-uni/index.scss";
	// @import "uni_modules/uni-upgrade-center-app/utils/check-update";

	// .box {
	// 	//font-family: PingFangSC-Semibold;	
	// 	font-family: PingFangSC-Regular;
	// 	/*  #ifndef   H5 */
	// 	padding-bottom: 140upx;
	// 	padding-top: 170upx;
	// 	top: var(--status-bar-height);
	// 	/*  #endif */
	// }

	.status_bar {
		height: var(--status-bar-height);
		width: 100%;
		background-color: #f2d7ff;
		// background-color: #8D3D87;
	}

	.box {
		//font-family: PingFangSC-Semibold;	
		font-family: PingFangSC-Regular;
		/*  #ifndef   APP-PLUS */
		padding-bottom: 140upx;
		// padding-top: 88upx;
		top: var(--status-bar-height);
		/*  #endif */
	}

	//全局动画
	@keyframes show {
		0% {
			transform: translateY(-50px);
		}

		60% {
			transform: translateY(40upx);
		}

		100% {
			transform: translateY(0px);
		}
	}

	@-webkit-keyframes show {
		0% {
			transform: translateY(-50px);
		}

		60% {
			transform: translateY(40upx);
		}

		100% {
			transform: translateY(0px);
		}
	}
</style>