<template>
	<view class="box">
		
			<!-- <nav-bar :title="'提现记录'" :back="true"></nav-bar> -->
			<hx-navbar :config="{title: '提现记录', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
			
			<!-- <nav-bottom :current="3"></nav-bottom> -->
			<scroll-view class="u-page">
				<u-list>
					<u-empty icon="http://cdn.uviewui.com/uview/empty/data.png" :show="withdrawalRecord.length<=0" mode="data" text="没有提现记录"></u-empty>
					<u-list-item v-for="(item, index) in withdrawalRecord" :key="index">
						<div class="item">
							<div class="item1">
								<div class="item1l">[提现申请金额]</div>
								<div class="item1r">{{item.amount}}</div>
							</div>
							<div class="item1 item2">
								<div class="item2l">审核状态</div>
								<div class="item2r">{{item.state|formatState}}</div>
							</div>
							<div class="item1 item4">
								<div class="item4l">{{item.createTime|formatDate(that)}}</div>
							</div>
						</div>
						<u-line></u-line>
					</u-list-item>
				</u-list>
			</scroll-view>
		
	</view>
</template>

<script>
	import {
		withdrawalRecord
	} from "@/api/withdrawal.js"
	export default {
		data() {
			return {
				withdrawalRecord: [],
				that: this,
			}
		},
		onLoad() {
			this.loadmore()
		},
		onPullDownRefresh() {
			this.loadmore();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
		},
		filters: {
			formatDate(data, that) {
				if (null == data) {
					return null;
				}
				return that.globalUtil.dateTimeFormat(data)
			},
			formatState(data) {
				if (data == "0") {
					return "待审核";
				} else if (data == "1") {
					return "已打款";
				} else if (data == "2") {
					return "已拒绝";
				}
			},
		},
		methods: {
			loadmore() {
				uni.showLoading();
				withdrawalRecord().then(res => {
					this.withdrawalRecord = res.voList
				})
				setTimeout(function() {
					uni.hideLoading();
				}, 500);
			}
		},
	}
</script>

<style scoped lang="scss">
	/deep/.u-empty {
		margin-top: 50% !important;
	}
	.item {
		background: #fff;
		padding-top: 20rpx;
		padding-bottom: 20rpx;
		border-bottom: 1rpx solid #F8F8F9;

		.item1 {
			width: 690rpx;
			margin: 0 auto;
			height: 60rpx;
			display: flex;
			justify-content: space-between;
			align-items: center;
			flex-wrap: nowrap;
			flex-direction: row;

			.item1l {
				font-size: 28rpx;
				color: #666;
			}

			.item1r {
				font-size: 28rpx;
				color: #666;
			}
		}

		.item2 {

			.item2l {
				font-size: 28rpx;
				color: #666;
			}

			.item2r {
				font-size: 28rpx;
				color: #E4A273;
			}
		}

		.item4 {
			.item4l {
				font-size: 24rpx;
				color: #666;
			}
		}
	}
</style>
