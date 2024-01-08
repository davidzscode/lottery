<template>
	<view class="content box">
		
			<!-- <nav-bar :title="'修改密码'" :back="true"></nav-bar> -->
			<hx-navbar :config="{title: '修改密码', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
			<view class="list">
				<view class="list-call">
					<image class="img" src="/static/images/personal/phone.png"></image>
					<input class="sl-input" type="number" v-model="recover.phone" maxlength="11" placeholder="请输入手机号" />
				</view>
				<view class="list-call">
					<image class="img" src="/static/images/personal/pwd.png"></image>
					<input class="sl-input" type="text" v-model="recover.password" maxlength="32" placeholder="请输入新密码"
						:password="!showPassword" />
					<image class="img"
						:src="showPassword?'/static/images/personal/op.png':'/static/images/personal/cl.png'"
						@tap="display"></image>
				</view>
				<view class="list-call">
					<image class="img" src="/static/images/personal/code.png"></image>
					<input class="sl-input" type="number" v-model="recover.code" maxlength="6" placeholder="验证码" />
					<view class="yzm" :class="{ yzms: second>0 }" @tap="getcode">{{computedCode}}</view>
				</view>
			</view>
			<view class="button-login" @tap="change()">
				<text>修改密码</text>
			</view>
		
	</view>
</template>

<script>
	import {
		removeAuthtoken,
		removeUserInfo,
		removeExternalLogintoken
	} from "@/util/auth.js"
	import {
		changePwd,
		send,
		logout,
	} from '@/api/user.js'
	var _this, js;
	export default {
		data() {
			return {
				recover: {
					phone: '', //手机号
					password: '', //密码
					code: "" //验证码
				},
				second: 0,
				showPassword: false,
			}
		},
		onLoad() {
			_this = this;
		},
		computed: {
			computedCode() {
				if (this.second == 0) {
					return '获取验证码';
				} else {
					if (this.second < 10) {
						return '重新获取0' + this.second;
					} else {
						return '重新获取' + this.second;
					}
				}
			}
		},
		onUnload() {
			this.clear()
		},
		methods: {
			//显示或者隐藏密码
			display() {
				this.showPassword = !this.showPassword
			},
			//清除清理器
			clear() {
				clearInterval(js)
				js = null
				this.second = 0
			},
			getcode() {
				if (!this.recover.phone) {
					uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					});
					return;
				}
				if (this.second > 0) {
					uni.showToast({
						title: "一分钟后可重新发送",
						icon: 'none'
					})
					return;
				}
				_this.second = 60;
				js = setInterval(function() {
					_this.second--;
					if (_this.second == 0) {
						_this.clear()
					}
				}, 1000)
				//请求后端发送验证码
				send({
					phone: this.recover.phone
				}).then(res => {
					if (res.success) {
						uni.hideLoading();
					}
				})
			},
			change() {
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
				if (!this.recover.code) {
					uni.showToast({
						title: '请输入验证码',
						icon: 'none'
					});
					return;
				}
				uni.showLoading();
				changePwd(this.recover).then(res => {
					if (res.success) {
						setTimeout(function() {
							uni.showToast({
								title: '密码修改成功！',
								icon: 'none'
							});
							logout().then(res => {
								if (res.success) {
									removeAuthtoken();
									removeExternalLogintoken();
									removeUserInfo();
									uni.redirectTo({
										url: "/pages/user/login"
									});
									uni.hideLoading();
								}
							})
						}, 500);
					}
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.content {
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	.list {
		display: flex;
		flex-direction: column;
		padding-top: 50rpx;
		padding-left: 70rpx;
		padding-right: 70rpx;
	}

	.list-call {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		height: 100rpx;
		color: #333333;
		border-bottom: 0.5px solid #e2e2e2;
	}

	.list-call .img {
		width: 40rpx;
		height: 40rpx;
	}

	.list-call .sl-input {
		flex: 1;
		text-align: left;
		font-size: 32rpx;
		margin-left: 16rpx;
	}

	.button-login {
		color: #FFFFFF;
		font-size: 16px;
		width: 90%;
		background: #9A3B86 !important;
		line-height: 85rpx;
		border-radius: 6px;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		margin-top: 100rpx;
	}

	.yzm {
		color: #FF7D13;
		font-size: 24rpx;
		line-height: 64rpx;
		padding-left: 10rpx;
		padding-right: 10rpx;
		width: auto;
		height: 64rpx;
		border: 1rpx solid rgba(255, 131, 30, 1);
		border-radius: 50rpx;
	}

	.yzms {
		color: #999999 !important;
		border: 1rpx solid #999999;
	}
</style>
