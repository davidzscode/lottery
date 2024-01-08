<template>
	<div class="football-confirm padding-bottom-90 box">
		<!-- <nav-bar :title="'投注单'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '投注单', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		<div>
			<div class="padding" style="display: flex;justify-content: space-around;align-items: center;">
				<u-button @click="continues" style="width: 40%;" color="#9A3B86" text="添加赛事"></u-button>
				<u-button @click="documentary" style="width: 40%;" color="#9A3B86" text="发起跟单"></u-button>
			</div>
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
									<view class="demo-layout bg-purple">{{item.number}}</view>
								</u-col>
								<u-col span="4" textAlign="center" justify="center" align="center">
									<view class="demo-layout bg-purple">{{item.visitingTeam}}</view>
								</u-col>
								<u-col span="0" textAlign="center" justify="center" align="center">
									<view class="demo-layout bg-purple-light">VS</view>
								</u-col>
								<u-col span="4" textAlign="center" justify="center" align="center">
									<view class="demo-layout bg-purple-dark">{{item.homeTeam}}</view>
								</u-col>
								<u-col span="1" textAlign="center" justify="center" align="flex-end">
									<u-icon name="close-circle" @tap="close(index)"></u-icon>
								</u-col>
							</u-row>
							<u-row class="body" customStyle="margin-bottom: 10px" justify="center" align="center"
								gutter="10">
								<u-col span="12" textAlign="center" justify="center" align="center">
									<u-button style="border-radius: 5px;" color='#F2F2F2' class="font">
										<span style="color: #9A3B86;"
											v-for="(winNegative,index) in item.winNegativeOddsList"
											v-if="winNegative.active">
											{{winNegative.describe}}({{winNegative.odds}}),
										</span>

										<span style="color: #9A3B86;"
											v-for="(cedePoints,index) in item.cedePointsOddsList"
											v-if="cedePoints.active">
											让{{cedePoints.describe}}({{cedePoints.odds}}),
										</span>

										<span style="color: #9A3B86;" v-for="(size,index) in item.sizeOddsList"
											v-if="size.active">
											{{size.describe}}[{{size.score}}]({{size.odds}}),
										</span>

										<span style="color: #9A3B86;"
											v-for="(difference,index) in item.differenceOddsList"
											v-if="difference.active">
											{{difference.describe}}({{difference.odds}}),
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
						<u-button @click="change" icon="arrow-down" :text="text"></u-button>
						<p>{{notes}}注{{times}}倍 <span class="text-primary text-md margin-left-5">共{{price}}元</span></p>
						<p style="color: #9A3B86;">预计奖金：
							<span>
								{{minPrice}}
							</span>
							<span v-if="minPrice!=maxPrice">
								~{{maxPrice}}
							</span>
							元
						</p>
					</div>
				</div>
				<!--确定-->
				<div class="buyFooter_count">
					<view
						style="display: flex; justify-content: center; align-items: center; padding-bottom: 30px;padding-top: 15px;">
						投<u-number-box :value="times" iconStyle="color: #9A3B86" integer inputWidth="70"
							@change="numberChange">
						</u-number-box>倍
					</view>
					<button size="mini" type="button" @tap="optimization">奖金优化</button>
					<button class="buyFooter_buyBtn" type="button" @tap="confirmIsShow=true">提交</button>
				</div>
			</div>
		</div>
		<u-popup :show="show" @close="show=false" style="z-index: 0;" mode="bottom" closeable>
			<span style="padding: 10px 20px;">自由过关</span>
			<view style="margin-bottom: 140px;" class="tag">
				<uni-row>
					<uni-col :span="5" v-for="(item, index) in strandArr" :key="index">
						<u-tag :text="item.name" :plain="!item.checked" type="warning" :name="index"
							@click="checkboxClick">
						</u-tag>
					</uni-col>
				</uni-row>
			</view>
		</u-popup>
		<u-modal title="支付给店主" :show="confirmIsShow" :zoom="false" confirmText="立即支付" showCancelButton
			confirmColor="#9A3B86" @confirm="betting" @cancel="() => confirmIsShow = false">
			<view class="tip">
				<p style="text-align: center;">竞彩篮球</p>
				<p style="margin-top: 15px;">共{{notes}}注{{times}}倍，您需要支付{{price}}元</p>
			</view>
		</u-modal>
	</div>
</template>

<script>
	import {
		createOrder,
	} from '@/api/racingball.js'
	import {
		calculation,
	} from '@/api/basketball.js'
	export default {
		data() {
			return {
				minPrice: "",
				maxPrice: "",
				confirmIsShow: false,
				show: false,
				selectItem: [],
				//注
				notes: 0,
				//总金额
				price: 0,
				//默认50倍
				times: 50,
				strandArr: [],
				//标记是否单关
				flag: true,
				text: "",
				calculationParam: {
					basketballMatchList: [],
					multiple: 0,
					pssTypeList: [],
					notes: "",
					type: 1,
					forecast: "",
					schemeDetails: ""
				},
				optimizationDate: {}
			}

		},
		onLoad(option) {
			//获取传过滤的数据
			let obj = JSON.parse(decodeURIComponent(option.obj));
			this.flag = eval(option.flag);
			//数组通过id进行排序
			this.selectItem = obj.sort(function(a, b) {
				return a.id - b.id
			})
			//初始化数据
			this.init(this.selectItem, this.flag);
		},
		methods: {
			//删除比赛
			close(idx) {
				//判断是否是单关
				if (!this.flag) {
					if (this.selectItem.length <= 2) {
						uni.showToast({
							title: '至少选择二场比赛',
							icon: 'none'
						});
						return;
					}
				}
				//刪除数据后需要重新计算金额
				this.notes = 0;
				this.price = 0;
				this.strandArr = []
				this.text = "";
				this.calculationParam.pssTypeList = []
				this.selectItem.splice(idx, 1);
				this.init(this.selectItem, this.flag)
			},
			//初始化事件
			init(itemList, flag) {
				var arr = []
				if (itemList.length > 0) {
					//动态创建自由过关数组
					if (itemList.length == 1) {
						for (var i = 0; i < this.selectItem.length; i++) {
							let data = {};
							data.name = "单关"
							data.index = i + 1
							data.checked = true;
							this.text = data.name
							arr.push(data);
						}
					} else {
						if (flag) {
							let data = {};
							data.name = "单关"
							data.index = 1
							data.checked = false;
							arr.push(data);
						}
						for (var i = 1; i < this.selectItem.length; i++) {
							let data = {};
							data.name = i + 1 + "串一"
							data.index = i + 1
							data.checked = false;
							arr.push(data);

						}
					}
					//移除未选中的数据,方便好做下步的逻辑处理
					this.selectItem.map((map, idx) => {
						let winNegativeOddsList = map.winNegativeOddsList.filter((notLet, idx) => {
							return notLet.active == true
						})
						//将只有选中的数据放到原数组中
						this.$set(this.selectItem[idx], "winNegativeOddsList", winNegativeOddsList)

						let cedePointsOddsList = map.cedePointsOddsList.filter((notLet, idx) => {
							return notLet.active == true
						})
						this.$set(this.selectItem[idx], "cedePointsOddsList", cedePointsOddsList)

						let sizeOddsList = map.sizeOddsList.filter((notLet, idx) => {
							return notLet.active == true
						})
						this.$set(this.selectItem[idx], "sizeOddsList", sizeOddsList)

						let differenceOddsList = map.differenceOddsList.filter((notLet, idx) => {
							return notLet.active == true
						})
						this.$set(this.selectItem[idx], "differenceOddsList", differenceOddsList)
					})
					//循环便利看是否有分差
					var s1 = false;
					for (var i = 0; i < this.selectItem.length; i++) {
						if (this.selectItem[i].differenceOddsList.length != 0) {
							s1 = true;
						} 
					}

					if (s1) {
						let count = 2;
						if (flag) {
							count = 3;
						}
						arr.map((item, index) => {
							if (index > count) {
								arr.splice(index, 10)
							}
						})
					}

					this.strandArr = arr;
					//选中玩法
					this.text = this.strandArr[this.strandArr.length - 1].name;
					this.strandArr[this.strandArr.length - 1].checked = true;

					this.calculationParam.basketballMatchList = this.selectItem;
					this.calculationParam.multiple = this.times;
					this.calculationParam.pssTypeList.push(this.strandArr[this.strandArr.length - 1].index);
					this.calculationBasketball()
				}
			},
			//计算注，预计金额
			calculationBasketball() {
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
			change() {
				this.show = !this.show;
			},
			continues() {
				uni.navigateBack();
			},
			documentary() {
				this.calculationParam.notes = this.notes
				this.calculationParam.forecast = this.maxPrice
				this.calculationParam.schemeDetails = JSON.stringify(this.optimizationDate.averageOptimizationList)
				uni.navigateTo({
					url: "pages/documentary/place?obj=" + encodeURIComponent(JSON.stringify(this.calculationParam))
				});
			},
			//标签选择事件
			checkboxClick(name) {
				this.strandArr[name].checked = !this.strandArr[name].checked
				//将选中的值赋值给下拉框
				this.text = ""
				this.calculationParam.pssTypeList = []
				this.strandArr.map(map => {
					if (map.checked) {
						this.text += map.name
						this.calculationParam.pssTypeList.push(map.index)
					}
				})
				if (this.calculationParam.pssTypeList.length > 0) {
					this.calculationBasketball();
				} else {
					this.price = 0;
					this.notes = 0;
				}
			},
			numberChange(item) {
				//点击倍数按钮时重新计算金额
				this.price = this.notes * item.value * 2
				this.times = item.value
				this.calculationParam.multiple = item.value
				this.calculationBasketball();
			},
			//优化按钮事件
			optimization() {
				this.calculationParam.notes = this.notes
				this.calculationParam.forecast = this.maxPrice
				uni.navigateTo({
					url: "pages/common/optimization?obj=" + encodeURIComponent(JSON.stringify(this
						.optimizationDate)) + "&calculationParam=" + encodeURIComponent(JSON.stringify(this
						.calculationParam))
				});
			},
		}
	}
</script>

<style scoped lang="scss">
	page {
		background-color: #f8f8f8;
	}

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

	/deep/.u-popup .u-transition {
		z-index: 0 !important;
	}

	
	.tag {
		padding-top: 20px;
	
		/deep/.uni-col {
			padding: 10px !important;
			display: flex !important;
			justify-content: center !important;
			align-items: center !important;
		}
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

	.text-muted {
		padding-left: 40rpx;
	}

	.padding {
		width: 98%;
		margin: auto;
		margin-top: 50rpx;
	}

	.football-confirm .item .btn {
		height: 30px;
		line-height: 30px;
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

	.football-confirm .money-confirm {
		position: fixed;
		width: 100%;
		bottom: 0;
		left: 0;
	}

	.football-confirm .money-confirm .left {
		padding: 5px 10px;
	}

	.football-confirm .money-confirm .right {
		color: white;
	}
</style>