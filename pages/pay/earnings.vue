<template>
	<view class="wp box">

<!-- 		<cmd-nav-bar back title="提现" font-color="#fff" background-color="#9A3B86" right-text="提现记录"
			@rightText="rightBtn">
		</cmd-nav-bar> -->
		<hx-navbar :config="{title: '提现', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		<!-- <nav-bottom :current="3"></nav-bottom> -->
		<view class="header">
			<view class="txt-xh">
				<view class="h3">
					¥{{user.price}}
				</view>
				<text>可提现金额(元)</text>
				<text class="s2">?</text>
			</view>
		</view>
		<view class="boe">
			<view class="m-about">
				<view class="h3">
					提现金额
				</view>
				<view class="num">
					<input class="inp" v-model="withdrawalParam.amount" placeholder="请输入金额" type="number">
				</view>
				<view class="h3" style="display: flex;justify-content: space-between;align-items: center;">
					<view>
						有{{user.gold}}不可提现
					</view>
					<view>
						<span style="color: rgb(41, 121, 255);" @click="see">查看原因</span>
					</view>
				</view>
				<view>
					<radio-group class="payType">
						<span @click="payTypeBtn('zfb')">
							<image src="/static/images/user/alipay.png" mode="scaleToFill" border="0"
								class="application_for_withdrawal_85"></image>
							<text decode="true" class="application_for_withdrawal_86">支付宝</text>
							<radio checked value="zfb" class="application_for_withdrawal_87">
							</radio>
						</span>
						<span @click="payTypeBtn('yl')">
							<image src="/static/images/user/bank.png" mode="scaleToFill" border="0"
								class="application_for_withdrawal_88"></image>
							<text decode="true" class="application_for_withdrawal_89">银行卡</text>
							<radio value="yl" class="application_for_withdrawal_90">
							</radio>
						</span>
					</radio-group>
				</view>
				<view style="padding-bottom: 30px;" v-show="zfbShow">
					<u-input placeholder="请输入支付宝账号" v-model="user.zfbNumber" @blur="inputBlur">
						<u-text text="支付宝账号" slot="prefix" margin="0 3px 0 0" type="tips"></u-text>
					</u-input>
				</view>
				<view style="padding-bottom: 20px;" v-show="ylShow">
					<u-form>
						<u-form-item>
							<u-input readonly placeholder="请输入姓名" v-model="user.name">
								<u-text text="姓名" slot="prefix" margin="0 3px 0 0" type="tips"></u-text>
							</u-input>
						</u-form-item>
						<u-form-item>
							<u-input placeholder="请输入银行名称" v-model="user.bankName" @blur="inputBlur">
								<u-text text="银行名称" slot="prefix" margin="0 3px 0 0" type="tips"></u-text>
							</u-input>
						</u-form-item>
						<u-form-item>
							<u-input placeholder="请输入银行卡号" v-model="user.bankNumber" @blur="inputBlur">
								<u-text text="银行卡号" slot="prefix" margin="0 3px 0 0" type="tips"></u-text>
							</u-input>
						</u-form-item>
					</u-form>
				</view>
				<view class="g-queding-kx">
					<view class="txt">
						<view class="s1" @tap="drawing">提现</view>
					</view>
				</view>
			</view>
		</view>
		<view class="main">
			<view class="txt-xh1">
				<view class="tit">
					<view class="h3">
						友情提示：
					</view>
					<view class="info">
						<text>发起提现前与店员沟通可大大加快提现审核的速度。</text>
					</view>
					<!-- <view class="info">
						2.<text>已完成的认养本金</text>（点击下方完成其“使用或提现”）
					</view>
					<view class="desc">
						<text>委托出让</text>
						<text>继续认养猪崽</text>
						<text style="margin-right: 0;">联系客服提现</text>
					</view>
					<view class="info">
						3.<text>每日受益</text>：每日累计可提现受益200元，提现的受益
						到微信零钱
					</view> -->
				</view>
			</view>
		</view>
		<u-popup :show="show" @close="show=false" mode="center">
			<uni-card>
				<p>
					<text>1. 为了防止套现和洗钱，充值，赠送的金额不能直接提现，您可用于投注。仅中奖奖金可提现。（如充值金额需要提现，可与店主协商处理）</text>
				</p>
				<p>
					<text>2. 本平台为店主的数字化管理工具，提现需要店主主动打款，用户资金是由店主托管和监管，如出现资金问题请及时联系店主处理与平台无关</text>
				</p>
				<p>
					<text>3. 如有联系不上店主需要帮助的，请在首页右上角点投诉并描述具体情况和诉求，平台客服会协助您处理</text>
				</p>
			</uni-card>
		</u-popup>

	</view>
</template>

<script>
	import {
		getUser,
		binding
	} from "@/api/user"
	import {
		setUserInfo
	} from "@/util/auth"
	import {
		add
	} from "@/api/withdrawal"
	export default {
		data() {
			return {
				show: false,
				zfbShow: true,
				ylShow: false,
				user: {},
				withdrawalParam: {
					amount: "",
					type: "0",
				},
			}
		},
		onLoad() {
			this.init();
		},
		methods: {
			inputBlur() {
				binding(this.user).then(res => {
					if (res.success) {
						this.init();
					}
				})
			},
			see() {
				this.show = true;
			},
			rightBtn() {
				uni.navigateTo({
					url: "/pages/pay/withdrawalRecord"
				});
			},
			init() {
				getUser().then(res => {
					this.user = res
					setUserInfo(res);
				})
			},
			drawing() {
				if (this.user.isReal == "0") {
					uni.showToast({
						title: '请先进行实名认证',
						icon: 'none'
					});
					return;
				}
				if (this.withdrawalParam.amount == "") {
					uni.showToast({
						title: '提现金额不能为空',
						icon: 'none'
					});
					return;
				}
				if (this.withdrawalParam.type == "1") {
					if (this.user.name == "" || this.user.name == null) {
						uni.showToast({
							title: '姓名不能为空',
							icon: 'none'
						});
						return;
					}
					if (this.user.bankName == "" || this.user.bankName == null) {
						uni.showToast({
							title: '银行名称不能为空',
							icon: 'none'
						});
						return;
					}
					if (this.user.bankNumber == "" || this.user.bankNumber == null) {
						uni.showToast({
							title: '银行卡账号不能为空',
							icon: 'none'
						});
						return;
					}
				} else {
					if (this.user.zfbNumber == "" || this.user.zfbNumber == null) {
						uni.showToast({
							title: '支付宝账号不能为空',
							icon: 'none'
						});
						return;
					}
				}
				uni.showLoading({
					title: '提现申请已提交',
				});
				add(this.withdrawalParam).then(res => {
					if (res.success) {
						setTimeout(function() {
							uni.navigateTo({
								url: "/pages/pay/withdrawalRecord"
							});
							uni.hideLoading();
						}, 1000);
					}
				})
			},
			payTypeBtn(item) {
				if (item == 'zfb') {
					this.ylShow = false
					this.zfbShow = true;
					this.withdrawalParam.type = "0";
				} else if (item == 'yl') {
					this.ylShow = true
					this.zfbShow = false;
					this.withdrawalParam.type = "1";
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	/deep/.cmd-nav-bar-right-text {
		font-size: 16px !important;
	}

	.payType {
		height: 100upx;
		padding-top: 30rpx;
	}

	.payType .application_for_withdrawal_85 {
		white-space: normal;
		width: 50rpx;
		height: 50rpx;
		padding: 0upx;
		margin-top: 3upx;
		margin-left: 2upx;
		float: left;
		border-radius: 0upx;
		font-size: 48upx;
		line-height: 50rpx;
	}

	.payType .application_for_withdrawal_86 {
		white-space: normal;
		width: 81upx;
		height: 28upx;
		padding: 0upx;
		margin-top: 11upx;
		margin-left: 23upx;
		float: left;
		border-radius: 0upx;
		color: #646464;
		font-size: 27upx;
		line-height: 28upx;
	}

	.payType .application_for_withdrawal_87 {
		white-space: normal;
		width: 43upx;
		height: 44upx;
		padding: 0upx;
		margin-top: 2upx;
		margin-left: 4upx;
		float: left;
		border-radius: 0upx;
		font-size: 43upx;
		line-height: 44upx;
	}

	.payType .application_for_withdrawal_88 {
		white-space: normal;
		width: 50rpx;
		height: 50rpx;
		padding: 0upx;
		margin-top: 3upx;
		margin-left: 105upx;
		float: left;
		border-radius: 0upx;
		font-size: 49upx;
		line-height: 50rpx;
	}

	.payType .application_for_withdrawal_89 {
		white-space: normal;
		width: 81upx;
		height: 29upx;
		padding: 0upx;
		margin-top: 11upx;
		margin-left: 22upx;
		float: left;
		border-radius: 0upx;
		color: #646464;
		font-size: 27upx;
		line-height: 29upx;
	}

	.payType .application_for_withdrawal_90 {
		white-space: normal;
		width: 43upx;
		height: 43upx;
		padding: 0upx;
		margin-top: 3upx;
		margin-left: 11upx;
		float: left;
		border-radius: 0upx;
		font-size: 42upx;
		line-height: 43upx;
	}

	/deep/.uni-radio-wrapper .uni-radio-input-checked {
		background: #9A3B86 !important;
		border-color: #9A3B86 !important;
		width: 36rpx !important;
		height: 36rpx !important;
	}

	/deep/.uni-radio-wrapper .uni-radio-input {
		width: 36rpx !important;
		height: 36rpx !important;
	}

	.header {
		background: #9A3B86;
		position: relative;
		height: 400rpx;
	}

	.txt-xh {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translateY(-50%) translateX(-50%);
	}

	.txt-xh .h3 {
		font-weight: bold;
		color: #fff;
		margin-bottom: 5rpx;
		font-size: 52rpx;
	}

	.txt-xh text {
		color: #fff;
		font-size: 24rpx;
	}

	.txt-xh .s2 {
		border: 1px solid #fff;
		border-radius: 50%;
		display: inline-block;
		margin-left: 15rpx;
		font-size: 14rpx;
		width: 30rpx;
		height: 30rpx;
		text-align: center;
		line-height: 30rpx;
	}

	.m-about {
		margin: 0 40rpx;
		padding: 20rpx 40rpx;
		margin-top: -80rpx;
		background-color: #fff;
		border-radius: 10rpx;
		position: relative;
		z-index: 99;
		box-shadow: 0 0 20rpx rgba(0, 0, 0, 0.1);
		margin-bottom: 140rpx;
	}

	.m-about .h3 {
		font-size: 28rpx;
		margin-bottom: 25rpx;
	}

	.m-about .num {
		margin-bottom: 35rpx;
		border-bottom: 1px solid #f2f2f2;
	}

	.m-about .num .desc {
		float: left;
		font-size: 24rpx;
		margin-bottom: 15rpx;
	}

	uni-input {
		height: 1.4em;
		font-size: 24rpx;
		line-height: 1.4em;
		margin-bottom: 15rpx;
	}

	.g-queding-kx {
		text-align: center;
		background: #9A3B86;
		height: 80rpx;
		line-height: 80rpx;
		font-size: 28rpx;
		border-radius: 10rpx;
		color: #fff;
		width: 100%;
	}

	.txt-xh1 {
		padding: 0 60rpx;
	}

	.txt-xh1 .h3 {
		display: block;
		font-size: 24rpx;
		margin-bottom: 20rpx;
	}

	.txt-xh1 .info {
		display: inline-block;
		font-size: 24rpx;
		letter-spacing: 1rpx;
		margin-bottom: 15rpx;
	}

	.txt-xh1 .info text {
		color: #000;
	}

	.txt-xh1 .info {
		color: #666666;

	}

	.txt-xh1 .desc {
		font-size: 28rpx;
		color: #9A3B86;
		margin-bottom: 20rpx;
		margin-top: 5rpx;

	}

	.txt-xh1 .desc text {
		margin-right: 85rpx;

	}
</style>
