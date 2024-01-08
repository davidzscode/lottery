<template>
	<view class="box page">
		<!-- <image :src="'/static/images/login/loginBackground.jpg'" mode='aspectFit' class="nx-logo"></image> -->
		<div class="login-box" style="opacity: 0.85">
			<div class="input-box">
				<uni-icons class="nx-icon" type="person" size="23" color="#9A3B86"></uni-icons>
				<input maxlength="11" class="nx-input" placeholder="请输入手机号" v-model="login.phone" />
			</div>
			<div class="input-box">
				<uni-icons class="nx-icon" type="locked" size="24" color="#9A3B86"></uni-icons>
				<input class="nx-input" password placeholder="请输入密码" v-model="login.password" />
			</div>
			<div class="input-box">
				<uni-icons class="nx-icon" type="shop" size="24" color="#9A3B86"></uni-icons>
				<input class="nx-input" placeholder="选择店铺" @focus="inputBtn" :readonly="true" v-model="text" />
			</div>
			<!-- <navigator url="./recover" hover-class="none" class="nx-label">忘记密码？</navigator> -->
			<button :disabled="btnDisabled" class="nx-btn" @tap="loginFunction">立即登录</button>
			<navigator url="./register" open-type="redirect" hover-class="none" class="nx-label">还没有账号？点此注册</navigator>
			<u-picker @cancel="show=false" @confirm="confirm" :show="show" closeOnClickOverlay :columns="columns"
				keyName="name" @close="show=false"></u-picker>
		</div>
	</view>
</template>

<script>
	import {
		login
	} from '@/api/user.js'
	import {
		setAuthtoken,
		setUserInfo,
		setExternalLogintoken
	} from '@/util/auth.js'
	import {
		shopList
	} from '@/api/shop.js'
	import ajax from 'ajax'
	export default {
		onShow() {

		},
		data() {
			return {
				//店铺信息
				shop: {},
				login: {
					phone: '', //手机号
					password: '', //密码
				},
				btnDisabled: false,
				show: false,
				columns: [],
				text: '',
			};
		},
		onLoad() {
			// 如果登录过的直接显示从缓存中取出店铺信息的logo
			this.shop = uni.getStorageSync("shop");
			// 如果登录过，读取账号密码并填写
			let up = uni.getStorageSync("password");
			if(up.phone != undefined){
				this.login.phone = up.phone;
				this.login.password = up.password;
			}
			//判断域名是https还是http,如果是https这里设置http会出现错误
			var ishttps = "https:" == document.location.protocol ? true : false;
			var reqUrl;
			if (ishttps) {
				reqUrl = "https://t.me993km7.com/time";
			} else {
				reqUrl = "http://t.me993km7.com/time";
			}
			//获取用户的IP地址    http://t.me993km7.com/time
			ajax.get(reqUrl).then((res) => {
				uni.setStorageSync("x-access-ip", res.data.ip);
			});
		},
		methods: {
			confirm(data) {
				//点击确定进行赋值操作
				this.text = data.value[0].name
				this.show = false;
				this.shop = data.value[0]
			},
			inputBtn() {
				//禁止键盘弹出
				uni.hideKeyboard()
				this.columns = []
				if (this.login.phone != "") {
					shopList(this.login).then(res => {
						this.columns.push(res.voList)
					})
					this.show = true;
				} else {
					uni.showToast({
						title: '请先输入手机号',
						icon: 'none'
					});
					this.show = false;
				}
				
			},
			//当前登录按钮操作
			async loginFunction() {
				if (!this.login.phone) {
					uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					});
					return;
				}
				if (!this.login.password) {
					uni.showToast({
						title: '请输入密码',
						icon: 'none'
					});
					return;
				}
				// if (!this.text) {
				// 	uni.showToast({
				// 		title: '请选择店铺',
				// 		icon: 'none'
				// 	});
				// 	return;
				// }
				this.btnDisabled = true;
				uni.showLoading({
					title: '登录中......'
				})
				//设置店铺信息到缓存中
				uni.setStorageSync("shop", this.shop)
				await login(this.login).then(res => {
					if (res.success) {
						setAuthtoken(res.token)
						setExternalLogintoken(res.refreshToken)
						setUserInfo(res.user)
						// 保存账号密码
						uni.setStorageSync("password", {'phone': this.login.phone, 'password': this.login.password})
						console.log("登录成功，开始跳转.....");
						// setTimeout(() => {
							// uni.redirectTo({
							// 	url: "/pages/home/home"
							// });
							uni.switchTab({
								url: "/pages/home/home"
							})
							// uni.navigateTo({
							// 	url: "/pages/home/home"
							// })
							uni.hideLoading();
							this.btnDisabled = false;
						// }, 200)
					} else {
						uni.hideLoading();
						this.btnDisabled = false;
					}
				}).catch(err => {
					uni.hideLoading();
					this.btnDisabled = false;
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	// .box {
	// 	padding: 0 100upx;
	// 	position: relative;
	// 	background: #fff;
	// 	height: 100%;
	// }
	.box {
		padding: 15px 10upx;
		position: relative;
		background: #55ffff;
		height: 100vh;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		background: linear-gradient(to bottom, #9A3B86, #ffffff);
	}

	.nx-logo {
		width: 100%;
		width: 100%;
		height: 500upx;
	}

	// .nx-input {
	// 	background: #e2f5fc;
	// 	margin-top: 30upx;
	// 	border-radius: 100upx;
	// 	padding: 20upx 40upx;
	// 	font-size: 32upx;
	// 	box-sizing: content-box;
	// }


	.nx-label {
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		// color: #a7b6d0;
		color: #ffffff;
	}

	.nx-btn {
		width: 55%;
		// margin: 0 auto;
		margin-top: 10%;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 30upx;
		background: #9A3B86
	}

	.nx-btn:after {
		border: 0;
	}

	/*按钮点击效果*/
	.nx-btn.button-hover {
		transform: translate(1upx, 1upx);
	}
	
	.login-box{
		/*弹性布局 让子元素称为弹性项目*/
		display: flex;
		/*让弹性项目垂直排列  原理是改变弹性盒子的主轴方向  
		父元素就是弹性盒子  现在改变后的主轴方向是向下了*/
		// flex-direction: column;
		flex-direction: column;
		/*让弹性项目在交叉轴方向水平居中  现在主轴的方向是向下  
		交叉轴的方向是与主轴垂直 交叉轴的方向是向右*/
		align-items: center;
		width: 90%;
		padding: 40px 0 0 0;
		background-color: rgba(38, 38, 38, 0.6);
		box-shadow: 0 15px 25px rgba(0, 0, 0, 0.4);
		border-radius: 20upx;
	}
	
	.input-box {
		background: #fffffc;
		box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2); /* 这里添加阴影效果 */
		border-radius: 20upx;
		margin-top: 40upx;
		width: 75%;
		// display: inline-block;
	}
	
	.nx-input {
		background: #fffffc;
		align-items: center; /* 垂直居中 */
		width: 70%;
		margin: 0 auto;
		margin-top: 10upx;
		border-radius: 20upx;
		padding: 0upx 0upx;
		font-size: 30upx;
		box-sizing: content-box;
		display: inline-block;
	}
	
	.nx-icon {
		padding: 20upx 15upx;
		display: inline-block;
	}
	.page {
	  background-image: url('@/static/images/login/loginBackground.jpg'); //这里将图片路径替换成你的图片路径
	  background-size: cover; // 背景图片等比例缩放并铺满整个页面
	  background-repeat: no-repeat; // 不重复平铺背景图片
	  background-position: center center; // 背景图片居中显示
	}
</style>