<template>
	<view class="box">
		<image :src="shop.logo" mode='aspectFit' class="nx-logo"></image>
		<view class="nx-form">
			<input maxlength="11" class="nx-input" placeholder="请输入手机号" v-model="recover.phone" />
			<input class="nx-input" password placeholder="请输入新密码" v-model="recover.password" />
			<input class="nx-input" placeholder="选择店铺" @focus="inputBtn" v-model="text" />
			<view class="nx-input" style="display: inline-flex;justify-content: center;align-items: center;">
				<input placeholder="请输入验证码" maxlength="6" v-model="recover.code" />
				<!-- 验证码组件 -->
				<u-code ref="uCode" @change="codeChange"></u-code>
				<u-button color="#9A3B86" size="mini" shape="circle" :ripple="true" @tap="getCode">{{tips}}
				</u-button>
			</view>
			<button class="nx-btn" @click="recoverBtn">找回密码</button>
			<navigator url="/pages/user/login" open-type="redirect" hover-class="none" class="nx-label">已有账号，点此去登录
			</navigator>
			<u-picker @cancel="show=false" @confirm="confirm" :show="show" closeOnClickOverlay :columns="columns"
				keyName="name" @close="show=false"></u-picker>
		</view>

	</view>
</template>

<script>
	import {
		shopList
	} from '@/api/shop.js'
	import {
		changePwd,
		send
	} from '@/api/user.js'
	export default {
		data() {
			return {
				//店铺信息
				shop: {},
				recover: {
					phone: '', //手机号
					password: '', //密码
					code: "" //验证码
				},
				tips: '',
				show: false,
				columns: [],
				text: '',
			};
		},
		onLoad() {
			//如果登录过的直接显示从缓存中取出店铺信息的logo
			this.shop = uni.getStorageSync("shop");
		},
		methods: {
			confirm(data) {
				//点击确定进行赋值操作
				this.text = data.value[0].name
				this.show = false;
				this.shop=data.value[0]
				//设置店铺信息到缓存中
				uni.setStorageSync("shop",this.shop)
			},
			inputBtn() {
				this.columns = []
				if (this.recover.phone != "") {
					shopList(this.recover).then(res => {
						this.columns.push(res.voList)
					})
					this.show = true;
				}else{
					uni.showToast({
						title: '请先输入手机号',
						icon: 'none'
					});
					this.show = false;
				}
				//禁止键盘弹出
				uni.hideKeyboard()
			},
			codeChange(text) {
				this.tips = text;
			},
			async getCode() {
				if (!this.recover.phone) {
					uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					});
					return;
				}
				if (this.$refs.uCode.canGetCode) {
					// 模拟向后端请求验证码
					uni.showLoading({
						title: '正在获取验证码',
						icon: 'none'
					})
					//请求后端发送验证码
					send({
						phone: this.recover.phone
					}).then(res => {
						if (res.success) {
							uni.hideLoading();
							uni.showToast({
								title: "验证码已发送",
								icon: 'none'
							})
							// 通知验证码组件内部开始倒计时
							this.$refs.uCode.start();
						}
					})
				} else {
					uni.showToast({
						title: "一分钟后可重新发送",
						icon: 'none'
					})
				}
			},
			async recoverBtn() {
				if (!this.recover.phone) {
					uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					});
					return;
				}
				if (!this.recover.password) {
					uni.showToast({
						title: '请输入新密码',
						icon: 'none'
					});
					return;
				}
				if (!this.text) {
					uni.showToast({
						title: '请选择店铺',
						icon: 'none'
					});
					return;
				}
				if (!this.recover.code) {
					uni.showToast({
						title: '请输入验证码',
						icon: 'none'
					});
					return;
				}
				changePwd(this.recover).then(res => {
					if (res.success) {
						uni.showToast({
							title: '密码修改成功！',
							icon: 'none'
						});
						setTimeout(() => {
							uni.navigateTo({
								url: "./login",
							});
						}, 1000)
					}
				})
			}
		}
	}
</script>

<style scoped>
	.box {
		padding: 0 100upx;
		position: relative;
		background: #fff;
		height: 100%;
	}

	.nx-logo {
		width: 100%;
		width: 100%;
		height: 500upx;
	}


	.nx-input {
		background: #e2f5fc;
		margin-top: 30upx;
		border-radius: 100upx;
		padding: 20upx 40upx;
		font-size: 32upx;
		box-sizing: content-box;
	}

	.input-placeholder,
	.nx-input {
		color: #94afce;
	}

	.nx-label {
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}

	.nx-btn {
		background: #9A3B86;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 32upx;
		margin-top: 60upx;
	}

	.u-button {
		height: 30px !important;
		width: 180rpx !important;
	}

	.nx-btn:after {
		border: 0;
	}

	/*按钮点击效果*/
	.nx-btn.button-hover {
		transform: translate(1upx, 1upx);
	}
</style>
