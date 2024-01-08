<template>
	<view class="box">
		<!-- <nav-bar :title="'跟单详情'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '跟单详情', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}" />

		<view class="container">
			<view class="userBox">
				<view class="userBox-left">
					<u-avatar size="45" :src="documentaryData.avatar" @click="queryInfo(documentaryData.id)"></u-avatar>
					<view class="info">
						<view class="info-top">{{documentaryData.nickname}}</view>
						<view class="info-bottom">
							<view class="hit">
								{{documentaryData.hit}}
							</view>
							<view class="profit">
								盈利率{{documentaryData.profit}}%</view>
						</view>
					</view>
				</view>
				<view v-if="!documentaryData.isFollow" @tap="followBtn('0',documentaryData.id)"
					style="border: 1px solid #9A3B86; border-radius: 15px; height: 25px; line-height: 25px; padding: 0px 15px; text-align: center; color: #9A3B86;margin-right: 15px;">
					关注</view>
				<view @tap="followBtn('1',documentaryData.id)" v-else
					style="border: 1px solid grey; border-radius: 15px; height: 25px; line-height: 25px; padding: 0px 15px; text-align: center; color: grey;margin-right: 15px;">
					取消关注</view>
			</view>
			<view class="content">
				<view class="titleInfo">
					<view class="myFlex">
						<view>
							<image style="width: 25px; height: 25px;"
								src="https://zzbhddoc.oss-cn-hangzhou.aliyuncs.com/template/aae0c7b4-08bd-41b7-8434-6fff4792fdc4.png">
							</image>
						</view>
						<view style="margin-left: 5px;">{{documentaryData.name}}</view>
					</view>
					<view>
						<text style="color: grey;"><span>佣金</span></text>
						<text style="color: red;"><span>{{documentaryData.commission}}%</span></text>
					</view>
				</view>
				<view style="margin: 10px 0px;">{{documentaryData.describe}}</view>

				<view class="orderInfo">
					<view class="total">
						<view class="myFlex">
							<view v-if="documentaryData.endTime>0" class="stopTime">
								<view>截止时间</view>
								<u-count-down :time="documentaryData.endTime" format="HH:mm:ss"></u-count-down>
							</view>
							<view v-else style="font-size: 15px;margin-right: 10px;">
								{{documentaryData.orderState=="1"?'待开奖':documentaryData.orderState=="2"?'未中奖':documentaryData.orderState=="3"||documentaryData.orderState=="4"?'已中奖':''}}
							</view>
							<view class="awardInfo" style="">
								<view class="subStyle">
									<view>自购金额</view>
									<view style="color: red;">{{documentaryData.price}}</view>
								</view>
								<view class="line"></view>
								<view class="subStyle">
									<view>跟单金额</view>
									<view style="color: red;">{{documentaryData.totalDocumentaryPrice}}</view>
								</view>
								<view class="line"></view>
								<view class="subStyle">
									<view>起投金额</view>
									<view style="color: red;">{{documentaryData.riseThrowPrice}}</view>
								</view>
							</view>
						</view>
					</view>
					<view class="detail">
						<!-- 选项卡切换 -->
						<u-tabs lineHeight="2" :list="tabsList" lineColor="#9A3B86" :scrollable="false" lineWidth="60"
							:activeStyle="{color: '#9A3B86',transform: 'scale(1.05)'}" @click="tabsBtn">
						</u-tabs>
						<!-- 内容 -->
						<scroll-view>
							<view v-if="programmeFlag">
								<view v-if="documentaryData.state=='1'&&documentaryData.endTime>0"
									style="display: flex;flex-direction: column;justify-content: center;align-items: center;color: grey;font-size: 20px;">
									<u-icon name="lock" size="50" color="grey"></u-icon>
									<text>开赛后可见</text>
									<text>截止时间 {{documentaryData.deadline|formatDate(that)}}</text>
								</view>
								<view v-else>
									<!-- 下注内容 -->
									<uni-card is-shadow v-if="documentaryData.name=='竞彩足球'">
										<view class="cardTitle">
											<span class="title">{{documentaryData.name}}</span>
											<text class="awType">
												<uni-row>
													<uni-col :span="documentaryData.pssTypeList.length<=2?12:24">
														<u-tag :text="documentaryData.notes+'注'" type="error"></u-tag>
														<u-tag :text="documentaryData.times +'倍'"
															style="margin-left: 10px;">
														</u-tag>
													</uni-col>
													<uni-col :span="6" :key="index"
														v-for="(item,index) in documentaryData.pssTypeList">
														<u-tag v-if="item==1" :borderColor="index|mapTagColor"
															:bgColor="index|mapTagColor" text="单关"
															:style="documentaryData.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
														</u-tag>
														<u-tag v-else :borderColor="index|mapTagColor"
															:bgColor="index|mapTagColor" :text="item+'串一'"
															:style="documentaryData.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
														</u-tag>
													</uni-col>
												</uni-row>

											</text>
										</view>
										<uni-table stripe emptyText="暂无更多数据">
											<!-- 表头行 -->
											<uni-tr>
												<uni-th width="10" align="center">场次</uni-th>
												<uni-th width="100" align="center">主队/客队</uni-th>
												<uni-th width="65" align="center">投注内容</uni-th>
												<uni-th width="50" align="center">赛果</uni-th>
											</uni-tr>
											<!-- 表格数据行 -->
											<uni-tr v-for="(record,index) in documentaryData.ballInfoList" :key="index">
												<uni-td align="center">{{record.number}}</uni-td>
												<uni-td align="center">{{record.homeTeam}}
													<span class="rangqiu"
														:class="{rangqiuBlue:record.letBall < 0}">({{record.letBall}})</span>
													<br>{{record.visitingTeam}}
												</uni-td>
												
												<!-- <uni-td align="center"> -->
													<!-- notLet.describe==record.award[0] 如果选择的和中奖的结果一样标记为红色 -->
													<!-- <span :style="notLet.describe==record.award[0]?'color:#9A3B86':''"
														v-for="(notLet,index) in record.content.notLetOddsList"
														v-if="notLet.active">
														{{notLet.describe}}({{notLet.odds}})<span
															v-if="record.content.notLetOddsList.length-index>1">,</span>
													</span>
													<span v-if="record.content.notLetOddsList.length>0">|</span>
													<br>

													<span :style="record.halfFullCourt!=undefined&&lets.describe==(Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)>Number(record.halfFullCourt.split(',')[1].split(':')[1])?'胜'
														:Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)<Number(record.halfFullCourt.split(',')[1].split(':')[1])?'负'
														:'平')?'color:#9A3B86':''" v-for="(lets,index) in record.content.letOddsList" v-if="lets.active">
														让{{lets.describe}}({{lets.odds}})<span
															v-if="record.content.letOddsList.length-index>1">,</span>
													</span>
													
													<span v-if="record.content.letOddsList.length>0">|</span>
													<br>
													<span :style="goal.describe==record.award[2]?'color:#9A3B86':''"
														v-for="(goal,index) in record.content.goalOddsList"
														v-if="goal.active">
														{{goal.describe}}({{goal.odds}})<span
															v-if="record.content.goalOddsList.length-index>1">,</span>
													</span>
													
													<span v-if="record.content.goalOddsList.length>0">|</span>
													<br>
													<span :style="half.describe==record.award[3]?'color:#9A3B86':''"
														v-for="(half,index) in record.content.halfWholeOddsList"
														v-if="half.active">
														{{half.describe}}({{half.odds}})<span
															v-if="record.content.halfWholeOddsList.length-index>1">,</span>
													</span>
													
													<span v-if="record.content.halfWholeOddsList.length>0">|</span>
													<br>
													<span :style="score.describe==record.award[4]?'color:#9A3B86':''"
														v-for="(score,index) in record.content.scoreOddsList"
														v-if="score.active">
														{{score.describe}}({{score.odds}})<span
															v-if="record.content.scoreOddsList.length-index>1">,</span>
														<br>
													</span>
													<span v-if="record.content.scoreOddsList.length>0">|</span>
													<br> -->
												<!-- </uni-td> -->
												<uni-td align="center">
													<!-- notLet.describe==record.award[0] 如果选择的和中奖的结果一样标记为红色 -->
													<span :style="notLet.describe==record.award[0]?'color:red':''"
														v-for="(notLet,index) in record.content.notLetOddsList" v-if="notLet.active">
														{{notLet.describe}}({{notLet.odds}})<br>
													</span>
												
													<span :style="record.halfFullCourt!=undefined&&lets.describe==(Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)>Number(record.halfFullCourt.split(',')[1].split(':')[1])?'胜'
													:Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)<Number(record.halfFullCourt.split(',')[1].split(':')[1])?'负'
													:'平')?'color:red':''" v-for="(lets,index) in record.content.letOddsList" v-if="lets.active">
														让{{lets.describe}}({{lets.odds}})<br>
													</span>
													<span :style="goal.describe==record.award[2]?'color:red':''"
														v-for="(goal,index) in record.content.goalOddsList" v-if="goal.active">
														{{goal.describe}}({{goal.odds}})<br>
													</span>
													<span :style="half.describe==record.award[3]?'color: red':''"
														v-for="(half,index) in record.content.halfWholeOddsList" v-if="half.active">
														{{half.describe}}({{half.odds}})<br>
													</span>
													<span :style="score.describe==record.award[4]?'color:#ff0000':''"
														v-for="(score,index) in record.content.scoreOddsList" v-if="score.active">
														{{score.describe}}({{score.odds}})<br>
													</span>
												</uni-td>
												
												<uni-td align="center">
													<span v-if="record.halfFullCourt!=undefined">
														{{record.halfFullCourt.split(',')[1]}}<br>半{{record.halfFullCourt.split(',')[0]}}
													</span>
												</uni-td>
											</uni-tr>
										</uni-table>
									</uni-card>

									<uni-card is-shadow v-if="documentaryData.name=='竞彩篮球'">
										<view>
											<span class="title">{{documentaryData.name}}</span>
											<p style="display: flex;justify-content: flex-end;align-items: center;">
												<uni-row>
													<uni-col :span="documentaryData.pssTypeList.length<=2?12:24">
														<u-tag :text="documentaryData.notes+'注'" type="error"></u-tag>
														<u-tag :text="documentaryData.times +'倍'"
															style="margin-left: 10px;"></u-tag>
													</uni-col>
													<uni-col :span="6" :key="index"
														v-for="(item,index) in documentaryData.pssTypeList">
														<u-tag v-if="item==1" :borderColor="index|mapTagColor"
															:bgColor="index|mapTagColor" text="单关"
															:style="documentaryData.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
														</u-tag>
														<u-tag v-else :borderColor="index|mapTagColor"
															:bgColor="index|mapTagColor" :text="item+'串一'"
															:style="documentaryData.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
														</u-tag>
													</uni-col>
												</uni-row>
											</p>
										</view>
										<uni-table stripe emptyText="暂无更多数据">
											<!-- 表头行 -->
											<uni-tr>
												<uni-th width="50" align="center">场次</uni-th>
												<uni-th width="70" align="center">客队/主队</uni-th>
												<uni-th width="50" align="center">投注内容</uni-th>
												<uni-th width="60" align="center">赛果</uni-th>
											</uni-tr>
											<!-- 表格数据行 -->
											<uni-tr v-for="(record,index) in documentaryData.ballInfoList" :key="index">
												<uni-td width="50" align="center">{{record.number}}</uni-td>
												<uni-td width="70" align="center">{{record.visitingTeam}}
													<span class="rangqiu"
														:class="{rangqiuBlue:record.letBall < 0}">({{record.letBall}})</span>
													<br> VS <br>{{record.homeTeam}}
												</uni-td>
												<uni-td width="50" align="center">
													<span
														:style="winNegative.describe==record.award[0]?'color:#9A3B86':''"
														v-for="(winNegative,index) in record.content.winNegativeOddsList"
														v-if="winNegative.active">
														{{winNegative.describe}}({{winNegative.odds}})<span
															v-if="record.content.winNegativeOddsList.length-index>1">,</span>
													</span>
													<span v-if="record.content.winNegativeOddsList.length>0">|</span>
													<br>
													<span :style="record.halfFullCourt!=undefined&&cedePoints.describe==(Number(record.halfFullCourt.split(':')[1])+Number(record.letBall)>Number(record.halfFullCourt.split(':')[0])?'主胜'
														:'主负')?'color:#9A3B86':''" v-for="(cedePoints,index) in record.content.cedePointsOddsList"
														v-if="cedePoints.active">
														让{{cedePoints.describe}}({{cedePoints.odds}})<span
															v-if="record.content.cedePointsOddsList.length-index>1">,</span>
													</span>
													<span v-if="record.content.cedePointsOddsList.length>0">|</span>
													<br>
													<span :style="record.halfFullCourt!=undefined&&size.describe==(Number(record.halfFullCourt.split(':')[1])+Number(record.halfFullCourt.split(':')[0])>Number(size.score)?'大'
														:'小')?'color:#9A3B86':''" v-for="(size,index) in record.content.sizeOddsList" v-if="size.active">
														{{size.describe}}<span
															style="color: #1afa29;">[{{size.score}}]</span>({{size.odds}})<span
															v-if="record.content.sizeOddsList.length-index>1">,</span>
													</span>
													<span v-if="record.content.sizeOddsList.length>0">|</span>
													<br>
													<span
														:style="difference.describe==record.award[2]?'color:#9A3B86':''"
														v-for="(difference,index) in record.content.differenceOddsList"
														v-if="difference.active">
														{{difference.describe}}({{difference.odds}})<span
															v-if="record.content.differenceOddsList.length-index>1">,</span>
													</span>
													<span v-if="record.content.differenceOddsList.length>0">|</span>
													<br>
												</uni-td>
												<uni-td width="50" align="center">
													<span v-if="record.halfFullCourt!=undefined">
														{{record.halfFullCourt}}
													</span>
												</uni-td>
											</uni-tr>
										</uni-table>
									</uni-card>

								</view>
							</view>
							<view v-if="frequencyFlag">
								<view
									style="background-color: rgb(255, 151, 106); text-align: center; color: white; margin: 10px 0px; height: 25px; line-height: 25px; border-radius: 5px;">
									默认展示前10位跟单用户</view>
								<u-empty icon="http://cdn.uviewui.com/uview/empty/data.png"
									v-show="documentaryData.documentaryUserList.length<=0" mode="data" text="沒有跟单用户~">
								</u-empty>
								<view v-if="documentaryData.documentaryUserList.length>0"
									v-for="(item,index) in documentaryData.documentaryUserList" :key="index"
									style="display: flex;justify-content: space-between; align-items: center; width: 92%;margin: auto;margin-bottom: 15px;">
									<view style="display: flex;align-items: center;">
										<u-avatar size="40" :src="item.avatar"></u-avatar>
										<text style="padding-left: 10px;">{{item.nickname}}</text>
									</view>
									<view>
										<text style="color: #9A3B86;font-weight: bold;">￥{{item.price}}</text>
									</view>
								</view>
							</view>
						</scroll-view>


					</view>
				</view>

			</view>
		</view>
		<view v-if="documentaryData.endTime>0"
			style="position: fixed; left: 0px; right: 0px; bottom: 0px; background-color: white; padding: 10px;">
			<view style="display: flex; align-items: center;justify-content: flex-end;margin-right: 10px;">
				投<u-number-box @change="valChange"></u-number-box>倍
			</view>
			<view style="display: flex; align-items: center; justify-content: space-between;">
				<view style="display: flex; align-items: center; margin-left: 10px;">
					<text>{{multiple}}倍</text>
					<view style="margin-left: 10px;">合计<text style="color: #9A3B86;"><span>{{price}}</span></text>元
					</view>
				</view>
				<view style="margin-right: 10px;">
					<u-button @tap="confirmIsShow=true"
						style="background-color: #9A3B86; color: #ffffff;border-radius: 10px;padding: 0px 45px;margin-top: 10px;"
						text="立即跟单"></u-button>
				</view>
			</view>
		</view>
		<u-modal title="支付给店主" :show="confirmIsShow" :zoom="false" confirmText="立即支付" showCancelButton
			confirmColor="#9A3B86" @confirm="betting" @cancel="() => confirmIsShow = false">
			<view class="tip">
				<p style="text-align: center;">{{documentaryData.name}}</p>
				<p style="margin-top: 15px;">共{{this.documentaryData.notes}}注{{multiple}}倍，您需要支付{{price}}元</p>
			</view>
		</u-modal>


		<!-- 		<u-mask>
			<view class="warp">
				<view class="rect">
					<text class="tx1">由用户分享的店内，全网发起推荐中提供的信息内容仅做参考，并不作为您的选单依据，亦不为真实性，准确性负责，请您知悉！！！</text>
				</view>				
			</view>					
		</u-mask> -->




	</view>
</template>

<script>
	import {
		queryDocumentaryById,
		createDocumentaryUser
	} from '@/api/documentary.js'
	import {
		addRelationship
	} from '@/api/follow.js'
	export default {
		data() {
			return {
				documentaryId: "",
				confirmIsShow: false,
				that: this,
				obj: {},
				multiple: 1,
				price: 0,
				programmeFlag: true,
				frequencyFlag: false,
				documentaryData: {},
				tabsList: [{
					name: '方案详情',
				}, {
					name: '跟单次数',
					badge: {
						value: 0,
					}
				}],
			}
		},
		onLoad(option) {
			this.obj = option;
			this.init(option.id, option.uid)
			// this.$refs.popup.open('center')
		},
		filters: {
			mapTagColor(index) {
				switch (index % 20) {
					case 0:
						return '#D52BB3'
					case 1:
						return '#3CC48D'
					case 2:
						return '#d81e06'
					case 3:
						return '#1296db'
					case 4:
						return '#13227a'
					case 5:
						return '#3cc2a8'
					case 6:
						return '#1cedf2'
					case 7:
						return '#6b1cf2'
					case 8:
						return '#e71cf2'
					case 9:
						return '#f28a1c'
					case 10:
						return '#f4ea2a'
					default:
						return '#19be6b'
				}
			},
			formatDate(data, that) {
				if (null == data) {
					return null;
				}
				return that.globalUtil.dateTimeFormat(data)
			},
		},
		methods: {
			queryInfo(id) {
				uni.navigateTo({
					url: "/pages/documentary/homePage?uid=" + id
				})
			},
			betting() {
				createDocumentaryUser({
					'multiple': this.multiple,
					'orderId': this.documentaryData.orderId,
					'documentaryId': this.documentaryId
				}).then(res => {
					if (res.success) {
						//标识为已经下单了
						uni.setStorageSync('isPay', true);
						uni.navigateTo({
							url: "/pages/order/lotteryOrderDetails?id=" + res.id,
						})
					}
				})
			},
			valChange(item) {
				//点击倍数按钮时重新计算金额
				this.price = this.documentaryData.notes * item.value * 2
				this.multiple = item.value
			},
			tabsBtn(item) {
				if (item.index == 0) {
					this.programmeFlag = true;
					this.frequencyFlag = false;
				} else if (item.index == 1) {
					this.programmeFlag = false;
					this.frequencyFlag = true;
				}
			},
			followBtn(type, id) {
				addRelationship(type, id).then(res => {
					if (res.success) {
						this.init(this.obj.id, this.obj.uid);
					}
				})
			},
			init(id, uid) {
				uni.showLoading();
				this.documentaryId = id;
				queryDocumentaryById(id, uid).then(res => {
					this.documentaryData = res;
					this.price = this.documentaryData.riseThrowPrice
					if (this.documentaryData.documentaryUserList.length <= 0) {
						this.tabsList[1].name = "跟单次数（0）"
					}
					//设置跟单人数
					this.tabsList[1].badge.value = this.documentaryData.documentaryUserList.length
					//将字符串转对象
					this.documentaryData.ballInfoList.map((item, idx) => {
						this.$set(this.documentaryData.ballInfoList[idx], "content", JSON.parse(item
							.content))
						//將比賽结果转换成数组，并返回
						if (item.award != null) {
							this.$set(this.documentaryData.ballInfoList[idx], "award", item.award.split(
								','))
						} else {
							//考虑比赛结果还没有出的话设置一个默认值，防止报错
							var moren = [0, 0, 0, 0, 0]
							this.$set(this.documentaryData.ballInfoList[idx], "award", moren)
						}
						if (item.bonusOdds != null) {
							this.$set(this.documentaryData.ballInfoList[idx], "bonusOdds", item.bonusOdds
								.split(','))
						} else {
							//考虑比赛结果还没有出的话设置一个默认值，防止报错
							var moren = ["", "", "", "", ""]
							this.$set(this.documentaryData.ballInfoList[idx], "bonusOdds", moren)
						}
					})
					setTimeout(function() {
						uni.hideLoading();
					}, 200);
				})
			},
		}
	}
</script>

<style lang="less" scoped>
	/deep/.uni-col {
		padding: 0px 0px 5px 0px !important;
		display: flex !important;
		justify-content: center !important;
		align-items: center !important;
	}

	/deep/.u-tag--medium {
		height: 19px;
	}

	.uni-card--border {
		border: 1px solid #fff;
	}

	.title {
		font-size: 28rpx;
	}

	.rangqiu {
		color: #9A3B86;
	}

	.rangqiuBlue {
		color: #2d8cf0 !important;
	}

	.uni-card {
		margin: 0px !important;
		padding: 0px !important;
	}

	.warp {
		user-select: none;
		color: #303133;
		font-size: 14px;
		margin: 0;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		justify-content: center;
		height: 100%;

		.rect {
			user-select: none;
			color: #303133;
			font-size: 14px;
			margin: 0;
			display: block;
			box-sizing: border-box;
			width: 314px;
			height: 289px;
			border-radius: 12px;
			// background: url(./static/img/shownoti.9c10ffd0.png) no-repeat 50%;
			background-size: 100% 100%;

			.tx1 {
				user-select: none;
				box-sizing: border-box;
				font-size: 14px;
				font-weight: 400;
				color: #666;
				padding-top: 96px;
				margin: 26px;
				display: flex;
			}

			.tx2 {
				user-select: none;
				margin: 0;
				box-sizing: border-box;
				width: 80%;
				height: 41px;
				background: #8d3d87;
				border-radius: 20px;
				display: flex;
				margin-left: 10%;
				align-items: center;
				justify-content: center;
				font-size: 14px;
				font-weight: 400;
				color: #fff;
			}
		}
	}

	.container {
		padding: 20px 5px;

		.myFlex {
			display: flex;
			align-items: center;
		}

		.userBox {
			display: flex;
			justify-content: space-between;
			align-items: center;

			.userBox-left {
				display: flex;
				align-items: center;

				.info {
					display: flex;
					flex-direction: column;
					margin-left: 10px;

					.info-top {
						margin-bottom: 5px;
					}

					.info-bottom {
						display: flex;
						align-items: center;
						font-size: 11px;

						.hit {
							color: #9A3B86;
							border: 1px solid red;
							border-radius: 7px 0px 0px 7px;
							height: 20px;
							line-height: 20px;
							text-align: center;
							padding: 0px 10px;
						}

						.profit {
							height: 22px;
							line-height: 20px;
							padding: 0px 10px;
							background-color: #9A3B86;
							text-align: center;
							border-radius: 0px 7px 7px 0px;
							color: white;
						}
					}
				}
			}
		}

		.content {
			background-color: white;
			padding: 10px;
			border-radius: 10px;
			margin-top: 30px;

			.titleInfo {
				display: flex;
				align-items: center;
				justify-content: space-between;
				border-bottom: 1px solid rgb(238, 238, 238);
				padding-bottom: 5px;
			}

			.orderInfo {
				.total {
					.stopTime {
						display: flex;
						flex-direction: column;
						margin-right: 10px;
						width: 70px;
					}

					.awardInfo {
						display: flex;
						align-items: center;
						background-color: rgb(249, 249, 249);
						padding: 10px;
						border-radius: 10px;
						width: 80%;
						justify-content: space-around;

						.subStyle {
							display: flex;
							flex-direction: column;
							align-items: center;
						}

						.line {
							border-right: 1px solid rgb(214, 214, 214);
							height: 25px;
							width: 1px;
						}
					}
				}

				.detail {
					.cardTitle {
						display: flex;
						justify-content: flex-start;

						.title {
							font-size: 28rpx;
							padding-right: 10px;
							font-weight: 700;
						}

						.awType {
							display: flex;
							justify-content: flex-end;

						}

					}
				}
			}
		}

	}


	page {
		background: #fff;
		font-size: 15px;
		color: #333;
		font-family: Helvetica Neue, Helvetica, sans-serif;
	}
</style>