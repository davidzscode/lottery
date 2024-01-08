<template>
	<view class="content-wrap box">

		<!-- <nav-bar :title="'钱包'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '钱包', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		<u-sticky>
			<view class="card-wrap">
				<view class="card">
					<view class="amount-wrap">
						<view style="display: flex;justify-content: center;align-items: center;">
							<u-icon name="rmb-circle" color="#FFFFFF" size="31"></u-icon>
							<view class="amount">
								彩金：{{user.gold}}
							</view>
						</view>
						<view style="display: flex;justify-content: center;align-items: center;padding-top: 25px;">
							<u-icon name="rmb-circle" color="#FFFFFF" size="31"></u-icon>
							<view class="amount">
								奖金：{{user.price}}
							</view>
						</view>
					</view>
				</view>
			</view>
<!-- 			<view class="nav-wrap">
				<view class="item" @click="handleToPage('/pages/pay/pay')">
					<view>
						<image src="@/static/images/wallet/recharge.png" />
					</view>
					<text>充值</text>
				</view>
				
				<view class="item">
					<view>
						<image src="@/static/images/wallet/transfer.png" />
					</view>
					<text>转账</text>
				</view>
				
				<view class="item" @click="handleToPage('/pages/pay/earnings')">
					<view>
						<image src="@/static/images/wallet/withdrawal.png" />
					</view>
					<text>提现</text>
				</view>
			</view> -->
		</u-sticky>
		<view class="list-wrap" style="padding-top: 25px;">
			<view class="card">
				<view class="title">交易明细</view>
				<u-empty icon="http://cdn.uviewui.com/uview/empty/data.png" :show="payOrderList.length<=0" mode="data"
					text="没有交易记录"></u-empty>
				<view class="item" v-for="(item,index) in payOrderList" :key="index"
					:style="[{animation: 'show ' + ((index+1)*0.2+1) + 's 1'}]">
					<view class="icon-wrap">
						<view class="uicon blue">
							<u-icon name="arrow-upward" color="#ffffff"></u-icon>
						</view>
						<u-line class="u-line" length="50rpx" border-style="dotted" color="#6665f6" direction="col">
						</u-line>
					</view>

					<view class="left">
						<view class="top">{{item.type|formatType}}</view>
						<view class="time">{{item.createTime|formatDate(that)}}</view>
					</view>
					<view class="right">￥{{item.price}}</view>
				</view>
			</view>
		</view>

	</view>
</template>
<script>
	import {
		getUser
	} from "@/api/user.js"
	import {
		getPayOrderPage
	} from '@/api/payOrder.js'
	import {
		setUserInfo
	} from "@/util/auth.js"
	export default {
		data() {
			return {
				user: {},
				that: this,
				payOrderList: [],
				total: 0,
				//查询条件
				queryParam: {
					type: "",
					pageNo: 1,
					pageSize: 10
				},
			};
		},
		filters: {
			//格式化类型
			formatType(type) {
				if (type == "0") {
					return "账户充值"
				} else if (type == "1") {
					return "下单竞彩足球"
				} else if (type == "2") {
					return "下单竞彩篮球"
				} else if (type == "3") {
					return "下单北京单场"
				} else if (type == "4") {
					return "下单排列3"
				} else if (type == "5") {
					return "提现打款"
				} else if (type == "6") {
					return "竞彩足球派奖"
				} else if (type == "7") {
					return "竞彩篮球派奖"
				} else if (type == "8") {
					return "北京单场派奖"
				} else if (type == "9") {
					return "排列3派奖"
				} else if (type == "10") {
					return "竞彩足球退票"
				} else if (type == "11") {
					return "竞彩篮球退票"
				} else if (type == "12") {
					return "北京单场退票"
				} else if (type == "13") {
					return "排列3退票"
				} else if (type == "14") {
					return "发单返佣金"
				} else if (type == "15") {
					return "排列五下单"
				} else if (type == "16") {
					return "排列五派奖"
				} else if (type == "17") {
					return "排列五退票"
				} else if (type == "18") {
					return "七星彩下单"
				} else if (type == "19") {
					return "七星彩派奖"
				} else if (type == "20") {
					return "七星彩退票"
				} else if (type == "21") {
					return "下单足球14场"
				} else if (type == "22") {
					return "足球14场派奖"
				} else if (type == "23") {
					return "足球14场退票"
				}else if (type == "24") {
					return "下单任选九"
				}else if (type == "25") {
					return "任选九派奖"
				}else if (type == "26") {
					return "任选九退票"
				}else if (type == "27") {
					return "下单大乐透"
				}else if (type == "28") {
					return "大乐透派奖"
				}else if (type == "29") {
					return "大乐透退票"
				}
			},
			//格式化日期
			formatDate(data, that) {
				return that.globalUtil.dateTimeFormat(data)
			},
		},
		onLoad() {
			this.init();
		},
		onPullDownRefresh() {
			this.payOrderList = []
			this.init();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
		},
		//滚动到底部进行分页事件
		onReachBottom() {
			if (this.payOrderList.length < this.queryParam.pageNo * 10) {
				return;
			}
			this.queryParam.pageNo++;
			this.init();
		},
		methods: {
			init() {
				uni.showLoading();
				getUser().then(res => {
					this.user = res
					setUserInfo(res);
				})
				//获取支付订单列表
				getPayOrderPage(this.queryParam).then(res => {
					if (res.success) {
						this.total = res.total;
						this.payOrderList = [...this.payOrderList, ...res.voList]
						setTimeout(function() {
							uni.hideLoading();
						}, 500);
					}
				})
			},
			async handleToPage(page) {
				uni.navigateTo({
					url: page
				});
			}
		}
	};
</script>
<style lang="scss" scoped>
	.card-wrap {
		display: flex;
		justify-content: center;

		.card {
			display: flex;
			justify-content: flex-start;
			align-items: center;
			width: 100%;
			height: 200rpx;
			background: #9A3B86;
			// background-color: $uni-bg-assist;
			// border-radius: 18rpx;
			// box-shadow: 0 2px 12px 0 rgba(56, 130, 255, 0.6);
			color: #ffffff;
			padding: 40rpx;

			.amount-wrap {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: flex-start;

				.amount {
					margin-left: 25rpx;
					font-size: 40rpx;
					display: flex;

					.unit {
						font-size: 28rpx;
						margin-top: 10rpx;
						margin-left: 20rpx;
					}
				}

				.fewAmount {
					margin-top: 20rpx;
					font-size: 32rpx;
				}
			}

			.buttom {
				margin-top: 40rpx;
				font-size: 24rpx;
				color: #D9D9D9;
			}
		}
	}

	.nav-wrap {
		z-index: 1000;
		display: flex;
		background: #ffffff;
		height: 220rpx;
		margin-bottom: 5px;
		padding: 30rpx 50rpx;
		color: $uni-text-color;
		// margin: 10rpx 0;
		justify-content: space-between;
		font-weight: bold;

		.item {
			width: 150rpx;
			height: 150rpx;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;

			image {
				width: 88rpx;
				height: 88rpx;
				padding: 10rpx;
			}
		}
	}

	.list-wrap {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 100rpx;

		.card {
			width: 90%;
			height: 100%;
			background-color: #ffffff;
			border-radius: 18rpx;
			box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

			.title {
				padding: 20rpx;
				font-weight: 600;
			}

			.item {
				display: flex;
				font-size: 28rpx;
				justify-content: space-between;
				margin: 20rpx 20px 10px 20rpx;
				color: $uni-text-color;

				.icon-wrap {
					display: flex;
					flex-direction: column;
					align-items: center;

					.uicon {
						width: 40rpx;
						height: 40rpx;
						border-radius: 50%;
						display: flex;
						justify-content: center;
						color: #ffffff;

						&.blue {
							background: #9A3B86;
						}

						&.orange {
							background-color: #FF8E4A;
						}
					}

					.u-line {
						color: #9A3B86;
					}
				}

				.left {
					flex: 1;
					margin-left: 20rpx;

					.top {
						// font-weight: 600;
					}

					.time {
						font-size: 24rpx;
						color: #999999;
					}
				}

				.right {
					font-weight: 600;
					color: #9A3B86;
				}
			}
		}
	}
</style>
