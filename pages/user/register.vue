<template>
	<view class="box" style="position: fixed;width: 100%;">
		<view class="page" style="width: 100%; height: 100%;">
			<view class="" style="position: relative; top: 210px; left: 20px;">
				<div class="login-box">
							<div class="input-box">
								<uni-icons class="nx-icon" type="person" size="23" color="#9A3B86"></uni-icons>
								<input maxlength="11" class="nx-input" placeholder="请输入手机号" v-model="register.phone" />
							</div>
							<div class="input-box">
								<uni-icons class="nx-icon" type="locked" size="24" color="#9A3B86"></uni-icons>
								<input class="nx-input" password placeholder="请输入密码" v-model="register.password" />
							</div>
							<div class="input-box">
								<uni-icons class="nx-icon" type="locked" size="24" color="#9A3B86"></uni-icons>
								<input class="nx-input" password placeholder="请输入密码" v-model="register.repeat_password" />
							</div>
							<div class="input-box">
								<uni-icons class="nx-icon" type="shop" size="24" color="#9A3B86"></uni-icons>
								<input class="nx-input" :disabled="shopDisabled" placeholder="选择店铺" @focus="inputBtn" v-model="text" />
							</div>
							<button :disabled="btnDisabled" class="nx-btn" @click="registerBtn">立即注册</button>
						</div>
				<!-- 			<view class="nx-input" style="display: inline-flex;justify-content: center;align-items: center;">
								<input maxlength="6" placeholder="请输入验证码" v-model="register.code" /> -->
								<!-- 验证码组件 -->
				<!-- 				<u-code ref="uCode" @change="codeChange"></u-code>
								<u-button color="#9A3B86" size="mini" shape="circle" :ripple="true" @click="getCode">{{tips}}
								</u-button>
							</view> -->
							<navigator v-if="register.uid==''" url="/pages/user/login" open-type="redirect" hover-class="none"
								class="nx-label">已有账号，点此去登录
							</navigator>
							<u-picker @cancel="show=false" @confirm="confirm" :show="show" closeOnClickOverlay :columns="columns"
								keyName="name" @close="show=false"></u-picker>
			</view>
			
		</view>
		
		

	</view>
</template>

<script>
	import {
		register,
		send,
		getTenantId,
		checkPhoneIsExist
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
		data() {
			return {
				//店铺信息
				shop: {},
				register: {
					phone: '', //手机号
					password: '', //密码
					repeat_password: '', //确认密码
					code: "", //验证码
					uid: "",
					whether: ""
				},
				tips: '',
				btnDisabled: false,
				shopDisabled: false,
				show: false,
				columns: [],
				text: '',
			};
		},
		onLoad(option) {
			console.log(option)
			//如果登录过的直接显示从缓存中取出店铺信息的logo
			this.shop = uni.getStorageSync("shop");
			this.register.whether = option.whether
			//初始化
			shopList(this.register).then(res => {
				this.columns.push(res.voList)
				//邀请进来的需要在这里赋值pid数据
				if (option.uid != undefined && option.uid != null) {
					this.register.uid = option.uid
					this.shopDisabled = true;
					//如果是邀请注册的查询邀请他们的租户id
					getTenantId(option.uid).then(res => {
						//设置店铺名字
						this.columns[0].map(item => {
							if (item.id == res.tenantId) {
								this.text = item.name;
								//设置店铺信息到缓存中
								uni.setStorageSync("shop", item)
								this.shop = item
							}
						})
					})
				}
			})
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
			codeChange(text) {
				this.tips = text;
			},
			confirm(data) {
				//点击确定进行赋值操作
				this.text = data.value[0].name
				this.show = false;
			},
			inputBtn() {
				this.show = true;
				//禁止键盘弹出
				uni.hideKeyboard()
			},
			async getCode() {
				if (!this.register.phone) {
					uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					});
					return;
				}
				//校验手机号是否存在
				checkPhoneIsExist(this.register).then(res => {
					if (res.success) {
						if (this.$refs.uCode.canGetCode) {
							// 模拟向后端请求验证码
							uni.showLoading({
								title: '正在获取验证码'
							})
							//请求后端发送验证码
							send({
								phone: this.register.phone
							}).then(res => {
								if (res.success) {
									uni.hideLoading();
									uni.showToast({
										title: "验证码已发送"
									})
									// 通知验证码组件内部开始倒计时
									this.$refs.uCode.start();
								}
							})
						} else {
							uni.showToast({
								title: "一分钟后可重新发送"
							})
						}
					}
				}).catch(err => {
					if (this.register.uid != null && this.register.uid != undefined) {
						if (err == 'E0755') {
							setTimeout(() => {
								uni.redirectTo({
									url: "/pages/personal/download"
								});
							}, 2000)
						}
					}
				})
			},
			async registerBtn() {
				if (!this.register.phone) {
					uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					});
					return;
				}
				if (!this.register.password) {
					uni.showToast({
						title: '请输入密码',
						icon: 'none'
					});
					return;
				}
				if(this.register.password != this.register.repeat_password){
					uni.showToast({
						title: '两次输入密码不一致',
						icon: 'none'
					});
					return;
				}
				// if (!this.register.code) {
				// 	uni.showToast({
				// 		title: '请输入验证码',
				// 		icon: 'none'
				// 	});
				// 	return;
				// }
				if (!this.text) {
					uni.showToast({
						title: '请选择店铺',
						icon: 'none'
					});
					return;
				}
				// this.btnDisabled = true;
				uni.showLoading({
					title: '注册中......'
				})
				register(this.register).then(res => {
					if (res.success) {
						setAuthtoken(res.token)
						setExternalLogintoken(res.refreshToken)
						setUserInfo(res.user)
						if (this.register.uid != null && this.register.uid != undefined) {
							//邀请进来的注册完后跳转到下载页面
							setTimeout(() => {
								uni.redirectTo({
									url: "/pages/personal/download"
								});
								uni.hideLoading();
								this.btnDisabled = false;
							}, 200)
						} else {
							setTimeout(() => {
								uni.redirectTo({
									url: "../home/home"
								});
								uni.hideLoading();
								this.btnDisabled = false;
							}, 200)
						}
					} else {
						uni.hideLoading();
						this.btnDisabled = false;
					}
				})
			}
		}
	}
</script>

<style scoped>
	.box {
		/* padding: 15px 10upx; */
		position: relative;
		background: #55ffff;
		height: 100vh;
		display: flex;
		flex-direction: column;
		align-items: center;
		background: linear-gradient(to bottom, #9A3B86, #ffffff);
	}

	.nx-logo {
		width: 100%;
		width: 100%;
		height: 500upx;
	}

	.u-button {
		height: 30px !important;
		width: 180rpx !important;
	}
	
	.nx-icon {
		padding: 20upx 15upx;
		display: inline-block;
	}

	.input-placeholder,
	
/* 	.nx-input {
		color: #94afce;
	} */
	
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

	.nx-label {
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		/* color: #818da0; */
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
		/* padding: 40px 0 0 0; */
		padding: 40px 0px;
		/* background-color: #fff; */
		background-color: rgba(38, 38, 38, 0.4);
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
	
	.page {
	  background-image: url('@/static/images/login/loginBackground.jpg'); //这里将图片路径替换成你的图片路径
	  background-size: cover; // 背景图片等比例缩放并铺满整个页面
	  background-repeat: no-repeat; // 不重复平铺背景图片
	  background-position: center center; // 背景图片居中显示
	}
	
</style>