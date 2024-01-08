<template>
	<div class="football-confirm padding-bottom-90 box">

		<!-- <nav-bar :title="'投注单'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '投注单', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		<div>
			<div class="padding">
				<div class="scheme-box">
					<div class="box-top">
						<image src="@/static/images/football/scheme_box_top.png" style="width: 100%; height: 30px;">
						</image>
					</div>
					<uni-card is-shadow>
						<div class="box-content" v-for="(item,index) in selectItem">
							<u-row customStyle="margin-bottom: 10px" justify="center" align="center" gutter="10">
								<u-col span="3" textAlign="center" justify="center" align="flex-start">
									<div class="paiban">
										<div>
											<view class="demo-layout bg-purple">{{item.number}}</view>
										</div>
										<div class="content" v-if="item.isGallbladder">
											<p>胆</p>
										</div>
									</div>
								</u-col>
								<u-col span="4" textAlign="center" justify="center" align="center">
									<view class="demo-layout bg-purple">{{item.homeTeam}}</view>
								</u-col>
								<u-col span="0" textAlign="center" justify="center" align="center">
									<view class="demo-layout bg-purple-light">VS</view>
								</u-col>
								<u-col span="4" textAlign="center" justify="center" align="center">
									<view class="demo-layout bg-purple-dark">{{item.visitingTeam}}</view>
								</u-col>
								<u-col span="1" textAlign="center" justify="center" align="flex-end">
									<u-icon name="close-circle" @tap="close(index)"></u-icon>
								</u-col>
							</u-row>
							<u-row class="body" customStyle="margin-bottom: 10px" justify="center" align="center"
								gutter="10">
								<u-col span="12" textAlign="center" justify="center" align="center">
									<u-button style="border-radius: 5px;" color='#F2F2F2' class="font">
										<span style="color: #9A3B86;" v-for="(notLet,index) in item.notLetOddsList"
											v-if="notLet.active">
											{{notLet.describe}},
										</span>
									</u-button>
								</u-col>
							</u-row>
						</div>
						<div class="box-bottom">
							<image src="@/static/images/football/scheme_box_bottom.png"
								style="width: 100%; height: 5px;"></image>
						</div>
					</uni-card>
				</div>
			</div>
			<div class="buyFooter_wrap">
				<!--垃圾箱-->
				<div class="buyFooter_count">
					<div>
						<p>{{notes}}注{{times}}倍 <span class="text-primary text-md margin-left-5">共{{price}}元</span>
						</p>
					</div>
				</div>
				<!--确定-->
				<div class="buyFooter_count">
					<view
						style="display: flex; justify-content: center; align-items: center; padding-bottom: 60px;padding-top: 15px;">
						投<u-number-box :value="times" iconStyle="color: #9A3B86" integer inputWidth="70"
							@change="numberChange">
						</u-number-box>倍
					</view>
					<button class="buyFooter_buyBtn" type="button" @tap="confirmIsShow=true">提交</button>
				</div>
			</div>
		</div>
		<u-modal title="支付给店主" :show="confirmIsShow" :zoom="false" confirmText="立即支付" showCancelButton
			confirmColor="#9A3B86" @confirm="betting" @cancel="() => confirmIsShow = false">
			<view class="tip">
				<p style="text-align: center;">竞彩足球</p>
				<p style="margin-top: 15px;">共{{notes}}注{{times}}倍，您需要支付{{price}}元</p>
			</view>
		</u-modal>

	</div>
</template>

<script>
	import {
		calculation
	} from '@/api/winburden.js'
	import {
		createOrder
	} from '@/api/racingball.js'
	export default {
		data() {
			return {
				minPrice: "",
				maxPrice: "",
				calculationFootballData: {},
				confirmIsShow: false,
				selectItem: [],
				//注
				notes: 0,
				//总金额
				price: 0,
				//默认50倍
				times: 50,
				calculationParam: {
					winBurdenMatchList: [],
					multiple: 0,
					pssTypeList: [9],
					notes: "",
					type: 7,
					forecast: "",
					schemeDetails: ""
				},
				optimizationDate: {}
			}
		},
		onLoad(option) {
			//获取传过滤的数据
			let obj = JSON.parse(decodeURIComponent(option.obj));
			//数组通过number进行排序
			this.selectItem = obj.sort(function(a, b) {
				return a.number - b.number
			})
			//初始化数据
			this.init(this.selectItem);
		},
		methods: {
			//删除比赛
			close(idx) {
				//必须14场比赛
				if (this.selectItem.length <= 9) {
					uni.showToast({
						title: '至少选择9场比赛',
						icon: 'none'
					});
					return;
				}
				//刪除数据后需要重新计算金额
				this.notes = 0;
				this.price = 0;
				this.selectItem.splice(idx, 1);
				this.init(this.selectItem)
			},
			//初始化事件
			init(itemList, flag) {
				var arr = []
				//计算多少注多少钱
				if (itemList.length > 0) {
					//移除未选中的数据,方便好做下步的逻辑处理
					this.selectItem.map((map, idx) => {
						let notLetOddsList = map.notLetOddsList.filter((notLet, idx) => {
							return notLet.active == true
						})
						//将只有选中的数据放到原数组中
						this.$set(this.selectItem[idx], "notLetOddsList", notLetOddsList)
					})
					this.calculationParam.winBurdenMatchList = this.selectItem;
					this.calculationParam.multiple = this.times;
					this.calculationWinBurden()
				}
			},
			//计算注，预计金额
			calculationWinBurden() {
				calculation(this.calculationParam).then(res => {
					//多少注
					this.notes = res.notes;
					//计算多少钱
					this.price = this.times * res.notes * 2
					//预计奖金最小最大值
					this.minPrice = res.minPrice
					this.maxPrice = res.maxPrice
					this.optimizationDate = res
				})
			},
			//立即支付事件
			betting() {
				if (this.price == 0 || this.notes == 0) {
					uni.showToast({
						title: '预计奖未计算完',
						icon: 'none'
					});
					return;
				}
				if (this.price < 10) {
					uni.showToast({
						title: '下单金额最低10元起投',
						icon: 'none'
					});
					return;
				}
				uni.showLoading();
				this.confirmIsShow = false;
				this.calculationParam.notes = this.notes
				this.calculationParam.forecast = this.maxPrice
				this.calculationParam.schemeDetails = JSON.stringify(this.optimizationDate.averageOptimizationList)
				createOrder(this.calculationParam).then(res => {
					if (res.success) {
						//标识为已经下单了
						uni.setStorageSync('isPay', true);
						setTimeout(function() {
							uni.hideLoading();
						}, 500);
						uni.navigateTo({
							url: "/pages/order/lotteryOrderDetails?id=" + res.id,
						})
					}
				})
			},
			continues() {
				uni.navigateBack();
			},
			//标签选择事件
			numberChange(item) {
				//点击倍数按钮时重新计算金额
				this.price = this.notes * item.value * 2
				this.times = item.value
				this.calculationParam.multiple = item.value
				this.calculationFootball();
			},
		}
	}
</script>

<style scoped lang="scss">
	.font {
		overflow: hidden;
		/*超出隐藏*/
		text-overflow: ellipsis;
		/*隐藏后添加省略号*/
		white-space: nowrap;
		/*强制不换行*/
		text-overflow: ellipsis;
		-o-text-overflow: ellipsis;
	}

	.paiban {
		display: flex;
		align-items: center;
		justify-content: space-between;
		flex-direction: row;
		width: 100%;
	}

	.content {
		width: 25px;
		height: 25px;
		background-color: #9A3B86;
		border-radius: 50%;

		p {
			width: 25px;
			height: 25px;
			color: #fff;
			text-align: center;
			line-height: 25px;
			font-size: 14px;
		}
	}

	.box {
		padding-bottom: 100px;
	}

	page {
		background-color: #f8f8f8;
	}

	.buyFooter_wrap {
		width: 100%;
		position: fixed;
		bottom: 0;
		left: 0;
		display: flex;
		align-items: center;
		border-top: 1px solid #e6e6e6;
		background-color: #f7f7f7;
		color: #8f9090;
		z-index: 30;

		/*确定*/
		.buyFooter_buyBtn {
			border: none;
			text-align: center;
			background: #9A3B86;
			color: #FFFFFF;
			font-size: 4.8vmin;
			width: 24.46667vmin;
			border-radius: 4px;
			position: fixed;
			right: 0;
			bottom: 0px;
		}

		.buyFooter_count {
			width: 46.46667vmin;
			padding-left: 30rpx;

			p {
				font-size: 14px;

				.text-primary {
					font-size: 15px;
					margin-left: 5px;
					color: #9A3B86;
				}
			}
		}
	}

	.bg-purple-light {
		font-size: 16px;
	}

	.demo-layout {
		font-size: 14px;
	}

	.padding {
		width: 98%;
		margin: auto;
		margin-top: 50rpx;
	}

	.scheme-box .box-top img,
	.scheme-box .box-bottom img {
		width: 100%;
	}

	.scheme-box .box-bottom,
	.scheme-box .box-top {
		line-height: 0;
		font-size: 0;
	}

	.scheme-box .box-content,
	.scheme-box .box-bottom {
		padding: 10rpx 20rpx;
	}
</style>