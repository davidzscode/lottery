<template>
	<view class="box">
		
			<!-- <nav-bar :title="'充值'" :back="true"></nav-bar> -->
			<hx-navbar :config="{title: '充值', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
			
			<!-- <nav-bottom :current="3"></nav-bottom> -->
			<view class="block">
				<view class="title">
					充值金币
				</view>
				<view class="content">
					<view class="amount">
						<view class="list">
							<u-row gutter="16">
								<u-col text-align="center" class="col-sty" v-for="(amount,index) in amountList"
									:key="index" @click="select(amount.price)"
									:class="{'on':amount.price == payParam.price}">
									<view>{{amount.text}}</view>
								</u-col>
							</u-row>
						</view>
					</view>
				</view>
				<u-input border="bottom" prefixIcon="rmb-circle" placeholder="请输入充值金额" type="number" v-model="price" 	clearable></u-input>
			</view>
			<view class="block">
				<view class="title">
					选择支付方式
				</view>
				<view class="content">
					<view class="pay-list">
						<view class="row" @click="payParam.payType=0">
							<view class="left">
								<image src="@/static/images/user/alipay.png"></image>
							</view>
							<view class="center">
								支付宝支付
							</view>
							<view class="right">
								<radio :checked="payParam.payType==0" color="#9A3B86" />
							</view>
						</view>
						<view class="row" @click="payParam.payType=1">
							<view class="left">
								<image src="@/static/images/user/wxpay.png"></image>
							</view>
							<view class="center">
								微信支付
							</view>
							<view class="right">
								<radio :checked="payParam.payType==1" color="#9A3B86" />
							</view>
						</view>
						<view class="row" @click="payParam.payType=2">
							<view class="left">
								<image src="@/static/images/user/bank.png"></image>
							</view>
							<view class="center">
								银联支付
							</view>
							<view class="right">
								<radio :checked="payParam.payType==2" color="#9A3B86" />
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="pay">
				<view class="btn" :disabled="disabled" @click="doDeposit">立即充值</view>
				<view class="tis">
					点击立即充值，即代表您同意<view class="terms">
						《条款协议》
					</view>
				</view>
			</view>
		
	</view>
</template>

<script>
	import {
		createOrder
	} from '@/api/payOrder.js'
	export default {
		data() {
			return {
				disabled: false,
				price: "",
				amountList: [{
					text: "68￥",
					price: 68
				}, {
					text: "126￥",
					price: 126
				}, {
					text: "518￥",
					price: 518
				}, {
					text: "1050￥",
					price: 1050
				}],
				payParam: {
					price: "",
					type: "0",
					payType: "0",
					channelId: '0'
				},
				userInfo: {},
			};
		},
		methods: {
			select(amount) {
				this.payParam.price = amount;
			},
			doDeposit() {
				if (this.payParam.payType == 1) {
					uni.showToast({
						title: '微信支付暂未开放',
						icon: 'none'
					});
					return;
				}
				if (this.payParam.payType == 2) {
					uni.showToast({
						title: '银联支付暂未开放',
						icon: 'none'
					});
					return;
				}
				if (this.payParam.price == "") {
					if (this.price == "") {
						uni.showToast({
							title: '金额不能为空',
							icon: 'none'
						});
						return;
					}
				}
				if (this.price != "") {
					this.payParam.price = this.price
				}
				if (this.payParam.price <= 0) {
					uni.showToast({
						title: '充值的金额不能低于0元',
						icon: 'none'
					});
					return;
				}
				uni.showLoading({
					title: '支付中......'
				})
				createOrder(this.payParam).then(res => {
					if (res.success) {
						location.href = res.url
						uni.hideLoading();
					}
				})
			},
		},
		onLoad() {
			let info = uni.getStorageSync("info")
			this.userInfo = info;
		},
	}
</script>

<style lang="scss" scoped>
	.box {
		background: #fff;

		.u-row {
			display: block;
			margin-left: 0px !important;
		}

		.block {
			padding: 20upx 3%;

			.title {
				width: 100%;
				font-size: 34upx;
			}

			.content {
				.my {
					width: 100%;
					height: 100upx;
					display: flex;
					align-items: center;
					font-size: 30upx;
					border-bottom: solid 1upx #eee;
				}

				.amount {
					width: 100%;

					.list {
						justify-content: space-between;
						padding: 20upx 0;

						.col-sty {
							width: 45%;
							height: 80upx;
							display: inline-flex;
							justify-content: center !important;
							align-items: center !important;
							border-radius: 10upx;
							box-shadow: 0upx 5upx 20upx rgba(0, 0, 0, 0.05);
							font-size: 32upx;
							background-color: #f1f1f1;
							margin: 0px 30rpx 30rpx 0px;
							color: 333;

							&.on {
								width: 45%;
								height: 80upx;
								border-radius: 10upx;
								box-shadow: 0upx 5upx 20upx rgba(0, 0, 0, 0.05);
								font-size: 32upx;
								background-color: #f1f1f1;
								color: 333;
								background: #9A3B86;
								color: #fff;
							}
						}
					}
				}

				.pay-list {
					width: 100%;
					border-bottom: solid 1upx #eee;

					.row {
						width: 100%;
						height: 120upx;
						display: flex;
						align-items: center;

						.left {
							width: 100upx;
							flex-shrink: 0;
							display: flex;
							align-items: center;

							image {
								width: 80upx;
								height: 80upx;
							}
						}

						.center {
							width: 100%;
							font-size: 30upx;
						}

						.right {
							width: 100upx;
							flex-shrink: 0;
							display: flex;
							justify-content: flex-end;
						}
					}
				}
			}
		}

		.pay {
			margin: 20upx 0rpx 80rpx 0rpx;
			width: 100%;
			display: flex;
			justify-content: center;
			flex-wrap: wrap;

			.btn {
				width: 70%;
				height: 80upx;
				border-radius: 80upx;
				display: flex;
				justify-content: center;
				align-items: center;
				color: #fff;
				background: #9A3B86;
				box-shadow: 0upx 5upx 10upx rgba(0, 0, 0, 0.2);
			}

			.tis {
				margin-top: 10upx;
				width: 100%;
				font-size: 24upx;
				display: flex;
				justify-content: center;
				align-items: baseline;
				color: #999;

				.terms {
					color: #5a9ef7;
				}
			}
		}
	}
</style>
