<template>
	<view class="box">
<!-- 		<cmd-nav-bar title="北京单场" :back="true" font-color="#fff" background-color="#9A3B86" @rightText="rightText"
			right-text="北单开奖">
		</cmd-nav-bar> -->
		<hx-navbar :config="{title: '北京单场', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		<div class="soccer_wrap">
			<!--main-->
			<div class="soccer_main" v-show="isMainShow">
				<u-sticky  offset-top="44"  bgColor="#fff"
					style="margin-bottom: 30px;display: flex;justify-content: center;align-items: center;">
					<u-tabs :list="lassificationcList" lineColor="#9A3B86"
						:activeStyle="{color: '#9A3B86',fontWeight: 'bold',transform: 'scale(1.05)'}"
						@click="changeSelectBall"></u-tabs>
				</u-sticky>
				<!--胜平负-->
				<u-empty icon="http://cdn.uviewui.com/uview/empty/data.png" :show="beidanList.length<=0" mode="data"
					text="暂无比赛"></u-empty>
				<div class="soccer_hunheguoguan" v-if="changeBall == 0">
					<!--每周比赛-->
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span style="display: block;color: #d4237a;">共有{{wk.count}}场比赛</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.beiDanMatchList" :key="index">
								<div class="games_left">
									<span><b>{{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color"
										:text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span
											class="title_team">{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<tr>
											<td class="rangqiu"
												:class="game.letBall < 0?'rangqiuBlue':game.letBall == 0?'rangqiuGreen':''">
												{{game.letBall}}
											</td>
											<td class="sheng_top">
												<p>
													<span v-if="game.letOddsList.length<=0">未开售</span>
													<span v-else v-for="(lets,letsIndex) in game.letOddsList"
														style="position: relative;" :class="{active:lets.active}"
														@tap="selectBtn(game,lets,weekIdx,index,1)">{{lets.describe}}
														{{lets.odds}}
													</span>
												</p>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--上线单双-->
				<div class="soccer_shengpingfu" v-if="changeBall == 1">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span style="display: block;color: #d4237a;">共有{{wk.count}}场比赛</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.beiDanMatchList">
								<div class="games_left">
									<span><b> {{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color"
										:text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<tr>
											<td class="sheng_top">
												<p>
													<span v-if="game.oddEvenOdds.length<=0">未开售</span>
													<span v-else @tap="selectBtn(game,oddEven,weekIdx,index,2)"
														:class="{active:oddEven.active}" class="sheng_top_block"
														style="width:25%"
														v-for="(oddEven,oddEvenIndex) in game.oddEvenOdds">
														<span>{{oddEven.describe}}</span>
														<span :class="{active:oddEven.active}">{{oddEven.odds}}
														</span>
													</span>
												</p>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--总进球-->
				<div class="soccer_zongjinqiu" v-if="changeBall == 2">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span style="display: block;color: #d4237a;">共有{{wk.count}}场比赛</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.beiDanMatchList">
								<div class="games_left">
									<span><b> {{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color"
										:text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<tr v-if="game.goalOddsList.length>0">
											<td class="gameInfoSpan" :class="{active:goal.active}" v-if="idx < 4"
												@tap="selectBtn(game,goal,weekIdx,index,3)"
												v-for="(goal,idx) in game.goalOddsList" :key="idx">
												<span><b>{{idx}}</b>{{goal.odds}}</span>
											</td>
										</tr>
										<tr v-if="game.goalOddsList.length>0">
											<td class="gameInfoSpan" v-if="idx >= 4" :class="{active:goal.active}"
												@tap="selectBtn(game,goal,weekIdx,index,3)"
												v-for="(goal,idx) in game.goalOddsList" :key="idx">
												<span><b>{{idx}}<span v-if="idx === 7">+</span></b>{{goal.odds}}</span>
											</td>
										</tr>
										<tr v-if="game.goalOddsList.length<=0">
											未开售
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--比分-->
				<div class="soccer_bifen" v-if="changeBall == 3">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span style="display: block;color: #d4237a;">共有{{wk.count}}场比赛</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,idx) in wk.beiDanMatchList">
								<div class="games_left">
									<span>{{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color"
										:text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--投注区-->
									<div @click="morePlay(game,weekIdx,idx)">
										<span v-for="s in game.scoreOddsList" v-if="s.active" style="color: #9A3B86;font-weight: bold">
											{{s.describe}} |
										</span>
										<span v-if="game.scoreFlag!='false'">
											点击展开比分投注区
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--半全场-->
				<div class="soccer_bifen" v-if="changeBall == 4">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span style="display: block;color: #d4237a;">共有{{wk.count}}场比赛</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,idx) in wk.beiDanMatchList">
								<div class="games_left">
									<span>{{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color"
										:text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--投注区-->
									<div @click="morePlay(game,weekIdx,idx)">
										<span v-for="s in game.halfWholeOddsList" v-if="s.active" style="color: #9A3B86;font-weight: bold">
											{{s.describe}} |
										</span>
										<span v-if="game.halfWholeFlag!='false'">
											点击展开半全场
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<BuyFooterSport :count='count' :total='total' :isShowCount="isShowCount" @clean="clean"
					@confirm="confirm"></BuyFooterSport>
			</div>
			<!--隐藏：更多玩法 根据index更改数据 根据isShowMorePlay控制显示-->
			<u-popup class="morePlays" :show="isShowMorePlay" mode="bottom" @close="isShowMorePlay = false">
				<div class="morePlay">
					<!--队伍-->
					<h5 class="morePlay_title">
						{{beidan.homeTeam}}
						<span>VS</span>
						{{beidan.visitingTeam}}
					</h5>
					<!--玩法:根据头部index切换更多玩法显示内容-->
					<div class="morePlay_select">
						<!--1半全场-->
						<div v-show="changeBall === 4" class="morePlay_banquanchang">
							<!--左-->
							<div class="banquanchang_left">
								<span>半全场</span>
							</div>
							<!--右-->
							<div class="banquanchang_right" v-if="beidan.halfWholeOddsList!=undefined">
								<span v-if="beidan.halfWholeOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-else :class="{active:a.active}" @tap="selectBtn(beidan,a,listIdx,childIdx,5)"
									v-for="(a,index) in beidan.halfWholeOddsList"><b>{{bqcItems[index]}}</b>
									<b :class="{active:a.active}">{{a.odds}}</b></span>
							</div>
						</div>
						<!--2比分拆分版-->
						<div v-show="changeBall === 3" class="morePlay_bifen_zhusheng">
							<!--左-->
							<div class="bifen_left">
								<span>主胜</span>
							</div>
							<!--右-->
							<div class="bifen_right" v-if="beidan.scoreOddsList!=undefined">
								<span v-if="beidan.scoreOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-if="index <= 9" v-for="(b,index) in beidan.scoreOddsList" :key="index"
									@tap="selectBtn(beidan,b,listIdx,childIdx,4)"
									:class="{doubleW:index === 0,active:b.active}">
									<b>{{bifenItems[index]}}</b>
									<b :class="{active:b.active}">{{b.odds}}</b>
								</span>
							</div>
						</div>
						<div v-show="changeBall === 3" class="morePlay_bifen_ping">
							<!--左-->
							<div class="bifen_left">
								<span>平</span>
							</div>
							<!--右-->
							<div class="bifen_right" v-if="beidan.scoreOddsList!=undefined">
								<span v-if="beidan.scoreOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-if="index > 9 && index <= 14" @tap="selectBtn(beidan,b,listIdx,childIdx,4)"
									v-for="(b,index) in beidan.scoreOddsList" :key="index"
									:class="{doubleW:index === 10,active:b.active}">
									<b>{{bifenItems[index]}}</b>
									<b :class="{active:b.active}">{{b.odds}}</b>
								</span>
							</div>
						</div>
						<div v-show="changeBall === 3" class="morePlay_bifen_kesheng">
							<!--左-->
							<div class="bifen_left">
								<span>客胜</span>
							</div>
							<!--右-->
							<div class="bifen_right" v-if="beidan.scoreOddsList!=undefined">
								<span v-if="beidan.scoreOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-if="index > 14" v-for="(b,index) in beidan.scoreOddsList" :key="index"
									@tap="selectBtn(beidan,b,listIdx,childIdx,4)"
									:class="{doubleW:index === 15,active:b.active}">
									<b>{{bifenItems[index]}}</b>
									<b :class="{active:b.active}">{{b.odds}}</b>
								</span>
							</div>
						</div>
					</div>
					<!--取消/确定-->
					<div class="morePlay_action">
						<div @click="isShowMorePlay = false">取消</div>
						<div @click="isShowMorePlay = false">确定</div>
					</div>
				</div>
			</u-popup>
		</div>

	</view>
</template>

<script>
	import {
		beidanList
	} from '@/api/beidan.js'
	import BuyFooterSport from '../common/buy-footer-sport.vue'
	export default {
		name: 'Soccer',
		components: {
			BuyFooterSport
		},
		data() {
			return {
				lassificationcList: [{
						name: '让球胜平负'
					}, {
						name: '上下单双',
					},
					{
						name: '总进球'
					}, {
						name: '比分'
					}, {
						name: '半全场'
					}
				],
				bifenItems: ['胜其他', '1:0', '2:0', '2:1', '3:0', '3:1', '3:2', '4:0', '4:1', '4:2', '平其他',
					'0:0', '1:1', '2:2', '3:3', '负其他', '0:1', '0:2', '1:2', '0:3', '1:3', '2:3', '0:4', '1:4', '2:4'
				],
				bqcItems: ['胜-胜', '胜-平', '胜-负', '平-胜', '平-平', '平-负', '负-胜', '负-平', '负-负'],
				// 混合玩法-更多玩法
				isShowMorePlay: false,
				isShowCount: false,
				changeBall: 0,
				isChangeIcon: 0,
				isShowGames: 0,
				beidanList: [],
				isMainShow: true,
				beidan: {},
				that: this,
				listIdx: "",
				childIdx: "",
				count: "",
				total: "",
				selectItem: []
			}
		},
		//下完单后返回到该页面数据进行重置
		onShow() {
			let isPay = uni.getStorageSync('isPay');
			if (isPay) {
				//数据刷新重置
				this.fecthSoccerData();
				//删除标识
				uni.removeStorageSync('isPay');
				this.selectItem = []
			}
		},
		filters: {
			formatDate(data, that) {
				if (null == data) {
					return null;
				}
				return that.globalUtil.timeFormat(data)
			},
		},
		methods: {
			/** 选中时改变当前单元格的背景颜色
			 * @param {Object} wk 当前比赛数据
			 * @param {Object} item 当前点击的数据
			 * @param {Object} weekIdx 上上级父级list的index
			 * @param {Object} index 上级父级list的index
			 * @param {Object} ,type 类型
			 */
			selectBtn(wk, item, weekIdx, index, type) {
				if (type == 1) {
					//让球
					this.beidanList[weekIdx].beiDanMatchList[index].letOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 2) {
					//单双
					this.beidanList[weekIdx].beiDanMatchList[index].oddEvenOdds.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 3) {
					//总进球
					this.beidanList[weekIdx].beiDanMatchList[index].goalOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 4) {
					//比分
					this.beidanList[weekIdx].beiDanMatchList[index].scoreOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
					//标识是否选择了比分
					this.$set(wk,'scoreFlag',"false")
				} else if (type == 5) {
					//半全场
					this.beidanList[weekIdx].beiDanMatchList[index].halfWholeOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
					this.$set(wk,'halfWholeFlag',"false")
				}
				//计算选中了几项
				if (item.active) {
					wk.choiceCount++;
				} else {
					wk.choiceCount--;
				}

				//获取选中的数据放入新的数组中
				if (this.selectItem.length > 0) {
					//标识是否在数组中是否存在
					let flag = true
					this.selectItem.map((d, idx) => {
						//判断id 如果相同进行重新赋值 在将flag置为true
						if (d.id == wk.id) {
							flag = false
							//如果都取消了判断选中项是否为零，为零就删除元素
							if (d.choiceCount == 0) {
								//index 当前元素索引；1：需要删除的元素个数
								this.selectItem.splice(idx, 1);
							}
							//重新赋值数据
							d = item;
						}
					})
					//如果都不存在进行添加到数组中
					if (flag) {
						this.selectItem.push(wk)
					}
				} else {
					//第一次数组为空的时候添加到数组中
					this.selectItem.push(wk)
				}
				this.count = this.selectItem.length;
			},
			clean() {
				this.count="";
				this.fecthSoccerData();
				this.selectItem = []
			},
			confirm() {
				var count;
				var flag = true;
				if (this.selectItem.length <= 0) {
					uni.showToast({
						title: '至少选择一场比赛',
						icon: 'none'
					});
					return;
				}
				if (this.selectItem.length > 15) {
					uni.showToast({
						title: '最多选择15场比赛',
						icon: 'none'
					});
					return;
				}
				//总投注选项数不能超过20个
				 let selectCount=1;
				this.selectItem.map((map, idx) => {
					selectCount*=map.choiceCount
				})
				if(selectCount*2>100000){
					uni.showToast({
						title: '单倍方案金额不能超过10万元',
						icon: 'none'
					});
					return;
				}
				uni.navigateTo({
					url: "pages/beidan/beidanConfirm?obj=" + encodeURIComponent(JSON.stringify(this
						.selectItem)) + "&flag=" + encodeURIComponent(flag)
				});
			},
			analysis(url) {
				if (url == null || url == "" || url == undefined) {
					uni.showToast({
						title: '暂无分析数据',
						icon: 'none'
					});
					return;
				}
				uni.navigateTo({
					url: "/pages/common/analysis?url=" + encodeURIComponent(url)
				});
			},
			// 获取竞彩北单数据
			fecthSoccerData() {
				uni.showLoading();
				beidanList().then(res => {
					this.beidanList = res.voList
					setTimeout(function() {
						uni.hideLoading();
					}, 0);
				})
			},
			// 不同玩法对应投注
			changeSelectBall(item) {
				this.fecthSoccerData();
				this.changeBall = item.index
				this.selectItem = [];
				this.count="";
			},
			rightText() {
				uni.navigateTo({
					url: "pages/beidan/beidanResult"
				});
			},
			// 点击折叠条
			clickBanner(index) {
				if (this.isShowGames === index) {
					this.isShowGames = index + 'a'
					this.isChangeIcon = index + 'a'
				} else {
					this.isShowGames = index
					this.isChangeIcon = index
				}
			},
			// 点击打开更多玩法
			morePlay(item, listIdx, childIdx) {
				// 控制更多玩法显示
				this.isShowMorePlay = true
				this.beidan = item;
				this.listIdx = listIdx
				this.childIdx = childIdx
			},
		},
		onLoad() {
			this.fecthSoccerData()
		},
		onPullDownRefresh() {
			this.fecthSoccerData();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
		},
	}
</script>

<style scoped lang="scss">
	/deep/.u-tag{
		width: 45px;
		height: 20px;
		justify-content: center;
		align-items: center;
	}
	/deep/.u-tag__text--medium {
	    font-size: 11px;
	    line-height: 11px;
	}
	page {
		background-color: #f7f9fa;
	}

	/deep/.cmd-nav-bar-right-text {
		font-size: 16px !important;
	}

	/deep/.u-empty {
		margin-top: 50% !important;
	}

	.active {
		background-color: #9A3B86 !important;
		color: #fff !important;
	}

	.box {
		padding-bottom: 0px;
	}

	.soccer_wrap {
		.soccer_main {
			background-color: #f7f9fa;
			width: 100%;
			padding-bottom: 10px;

			.soccer_hunheguoguan {
				width: 100%;
				padding-bottom: 10px;

				.soccer_weekGame {
					width: 100%;

					/*比赛折叠条*/
					.weekGame_banner {
						width: 100%;
						background-color: white;
						display: flex;
						align-items: center;
						justify-content: space-between;
						padding: 0 4vmin;
						height: 12.8vmin;
						box-sizing: border-box;
						border-bottom: 1px solid #eee;

						.banner_left {
							span {
								margin-left: 3.46667vmin;
								font-size: 3.46667vmin;
							}
						}

						i {
							font-size: 5.26667vmin;
							color: #c7c7c7;
							/*transform: rotate(180deg);*/
						}

						.icon-jiantou2-change {
							transform: rotate(180deg);
						}
					}

					/*比赛*/
					.weekGame_games {
						width: 100%;

						.games {
							box-sizing: border-box;
							display: flex;
							align-items: center;
							justify-content: flex-start;
							height: 30.66667vmin;
							margin: 0 2.66667vmin;
							border-bottom: 1px solid #e6e6e6;

							/*box-sizing: border-box;*/
							.games_left {
								width: 15vmin;
								height: 100%;
								font-size: 2.93333vmin;
								font-weight: 400;
								margin-right: 2.4vmin;
								color: #333333;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								span {
									text-overflow: ellipsis;
									display: block;
									margin-bottom: 1.8vmin;
									white-space: nowrap;
								}
							}

							.games_right {
								/*height: 100%;*/
								box-sizing: border-box;

								.right_title {
									/*box-sizing: border-box;*/
									display: flex;
									align-items: center;
									justify-content: flex-start;
									position: relative;

									b {
										position: absolute;
										left: 0;
										top: -2vmin;
										width: 3.73333vmin;
										display: inline-block;
										font-weight: 400;
										font-size: 5.33333vmin;
										-webkit-transform: scale(.5);
										transform: scale(.5);
										line-height: 1.2;
									}

									.title_team {
										/*margin:0 auto;*/
										font-size: 3.2vmin;
										height: 35px;
										font-weight: 700;
										color: #5d5d5d;
										width: 100%;
										display: flex;
										justify-content: center;
										align-items: center;

										strong {
											margin: 0 .66667vmin;
											font-weight: 400;
										}

										em {
											font-style: normal;
											font-size: 2.4vmin;
											font-weight: 400;
										}
									}
								}

								table {
									width: 100%;
									border: 1px solid #e6e6e6;
									color: #333333;

									tr {
										td {
											/*vertical-align: middle;*/
											font-size: 3.46667vmin;
											background-color: white;
										}

										.burangqiu {
											border: none;
											width: 3.73333vmin;
											font-size: 2.93333vmin;
											text-align: center;
											background: #e6e6e6;
											height: 9.6vmin;
											vertical-align: middle
										}

										.rangqiu {
											color: white;
											border: none;
											width: 3.73333vmin;
											font-size: 2.93333vmin;
											text-align: center;
											background: #9A3B86;
											height: 9.6vmin;
											vertical-align: middle
										}

										.rangqiuBlue {
											background: #2d8cf0;
										}

										.rangqiuGreen {
											background: #1afa29;
										}

										.sheng_top {
											height: 9.6vmin;
											box-sizing: border-box;
											border: 1px solid #e6e6e6;
											font-size: 3.46667vmin;
											text-align: center;
											justify-content: center;

											p {
												display: flex;
												align-items: center;
												height: 100%;
												box-sizing: border-box;
												justify-content: center;

												span {

													width: 20.5vmin;
													display: flex;
													align-items: center;
													justify-content: center;
													height: 100%;
													border-right: 1px solid #e6e6e6;
												}

												span:last-child {
													border: none;
												}
											}
										}

										.morePlay {
											width: 10.66667vmin;
											font-size: 3.46667vmin;
											text-align: center;
											border: 1px solid #e6e6e6;
											color: #999999;
											vertical-align: middle;
											line-height: 1.5;
										}
									}
								}
							}
						}

						.games:last-child {
							border: none;
						}
					}
				}

				.soccer_weekGame:last-child {
					margin-bottom: 13.33333vmin;
				}
			}

			/*胜平负\让球胜平负*/
			.soccer_shengpingfu {
				background-color: white;
				width: 100%;

				.soccer_weekGame {
					width: 100%;

					/*比赛折叠条*/
					.weekGame_banner {
						width: 100%;
						background-color: white;
						display: flex;
						align-items: center;
						justify-content: space-between;
						padding: 0 4vmin;
						height: 12.8vmin;
						box-sizing: border-box;
						border-bottom: 1px solid #eee;

						.banner_left {
							span {
								margin-left: 3.46667vmin;
								font-size: 3.46667vmin;
							}
						}

						i {
							font-size: 5.26667vmin;
							color: #c7c7c7;
							/*transform: rotate(180deg);*/
						}

						.icon-jiantou2-change {
							transform: rotate(180deg);
						}
					}

					/*比赛*/
					.weekGame_games {
						width: 100%;

						.games {
							padding: 3.2vmin 0;
							box-sizing: border-box;
							display: flex;
							align-items: center;
							justify-content: flex-start;
							height: 25.33333vmin;
							margin: 0 2.66667vmin;
							border-bottom: 1px solid #e6e6e6;

							.games_left {
								width: 15vmin;
								height: 100%;
								font-size: 2.93333vmin;
								font-weight: 400;
								margin-right: 2.4vmin;
								color: #333333;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								span {
									text-overflow: ellipsis;
									display: block;
									margin-bottom: 1.8vmin;
									white-space: nowrap;
								}
							}

							.games_right {
								/*height: 100%;*/
								box-sizing: border-box;
								width: 100%;

								.right_title {
									display: flex;
									align-items: center;
									justify-content: center;
									position: relative;

									span {
										margin-bottom: 2.66667vmin;
										font-size: 3.2vmin;
										display: inline-block;
										font-weight: 700;
										color: #5d5d5d;
										white-space: nowrap;

										strong {
											margin: 0 .66667vmin;
											font-weight: 400;
										}

										em {
											color: #999;
											font-style: normal;
											font-size: 2.4vmin;
											font-weight: 400;
										}
									}
								}

								table {
									width: 100%;
									border: 1px solid #e6e6e6;
									color: #333333;

									tr {
										td {
											/*vertical-align: middle;*/
											font-size: 3.46667vmin;
											background-color: white;
										}

										.sheng_top {
											height: 11.73333vmin;
											box-sizing: border-box;
											border: 1px solid #e6e6e6;
											// font-size: 3.73333vmin;
											font-size: 13px;
											text-align: center;

											p {
												display: flex;
												align-items: center;
												height: 100%;
												box-sizing: border-box;
												justify-content: center;

												/*text-align: center;*/
												.sheng_top_block {
													width: 25.3vmin;
													display: flex;
													align-items: center;
													justify-content: center;
													height: 100%;
													border-right: 1px solid #e6e6e6;
													flex-direction: column;

													span {
														// margin-bottom: .66667vmin;
														margin-bottom: -0.4vmin;
													}

													span:last-child {
														// color: #666;
														color: #999;
														font-weight: 500;
													}
												}

												.sheng_top_block:last-child {
													border: none;
												}
											}
										}
									}
								}
							}
						}

						.games:last-child {
							border: none;
						}
					}
				}

				.soccer_weekGame:last-child {
					margin-bottom: 13.33333vmin;
				}
			}

			/*总进球*/
			.soccer_zongjinqiu {
				background-color: white;
				width: 100%;

				.soccer_weekGame {
					width: 100%;

					/*比赛折叠条*/
					.weekGame_banner {
						width: 100%;
						background-color: white;
						display: flex;
						align-items: center;
						justify-content: space-between;
						padding: 0 4vmin;
						height: 12.8vmin;
						box-sizing: border-box;
						border-bottom: 1px solid #eee;

						.banner_left {
							span {
								margin-left: 3.46667vmin;
								font-size: 3.46667vmin;
							}
						}

						i {
							font-size: 5.26667vmin;
							color: #c7c7c7;
							/*transform: rotate(180deg);*/
						}

						.icon-jiantou2-change {
							transform: rotate(180deg);
						}
					}

					/*比赛*/
					.weekGame_games {
						width: 100%;

						.games {
							padding: 1.2vmin 0;
							position: relative;
							/*box-sizing: border-box;*/
							display: flex;
							align-items: center;
							justify-content: flex-start;
							height: 25.33333vmin;
							margin: 0 2.66667vmin;
							border-bottom: 1px solid #e6e6e6;

							img {
								position: absolute;
								top: 0;
								left: -11px;
								width: 5.06667vmin;
								height: 3.2vmin;
							}

							.games_left {
								width: 15vmin;
								height: 100%;
								font-size: 2.93333vmin;
								font-weight: 400;
								margin-right: 2.4vmin;
								color: #333333;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								span {
									text-overflow: ellipsis;
									display: block;
									margin-bottom: 1.8vmin;
									white-space: nowrap;
								}
							}

							.games_right {
								box-sizing: border-box;

								.right_title {
									display: flex;
									align-items: center;
									justify-content: center;
									position: relative;

									span {
										margin-bottom: 2.66667vmin;
										font-size: 3.2vmin;
										display: inline-block;
										font-weight: 700;
										color: #5d5d5d;
										white-space: nowrap;

										strong {
											margin: 0 .66667vmin;
											font-weight: 400;
										}

										em {
											color: #999;
											font-style: normal;
											font-size: 2.4vmin;
											font-weight: 400;
										}
									}
								}

								table {
									width: 100%;
									border: 1px solid #e6e6e6;
									border-bottom: none;
									color: #333333;

									tr {
										border: 1px solid #e6e6e6;
										background-color: white;

										td {
											border-bottom: 1px solid #e6e6e6;
											color: #666;
											width: 20.73333vmin;
											height: 7.73333vmin;
											box-sizing: border-box;
											font-size: 3.2vmin;
											text-align: left;
											border-right: 1px solid #e6e6e6;
											vertical-align: middle;

											span {
												font-weight: 400;

												b {
													margin-right: 2vmin;
												}
											}
										}
									}
								}
							}
						}

						.games:last-child {
							border: none;
						}
					}
				}

				.soccer_weekGame:last-child {
					margin-bottom: 13.33333vmin;
				}
			}

			/*比分*/
			.soccer_bifen {
				background-color: white;
				width: 100%;

				.soccer_weekGame {
					width: 100%;

					/*比赛折叠条*/
					.weekGame_banner {
						width: 100%;
						background-color: white;
						display: flex;
						align-items: center;
						justify-content: space-between;
						padding: 0 4vmin;
						height: 12.8vmin;
						box-sizing: border-box;
						border-bottom: 1px solid #eee;

						.banner_left {
							span {
								margin-left: 3.46667vmin;
								font-size: 3.46667vmin;
							}
						}

						i {
							font-size: 5.26667vmin;
							color: #c7c7c7;
							/*transform: rotate(180deg);*/
						}

						.icon-jiantou2-change {
							transform: rotate(180deg);
						}
					}

					/*比赛*/
					.weekGame_games {
						width: 100%;

						.games {
							height: 24.26667vmin;
							position: relative;
							box-sizing: border-box;
							display: flex;
							align-items: center;
							justify-content: flex-start;
							margin: 0 2.66667vmin;
							border-bottom: 1px solid #e6e6e6;

							img {
								position: absolute;
								top: 0;
								left: -11px;
								width: 5.06667vmin;
								height: 3.2vmin;
							}

							.games_left {
								width: 15vmin;
								height: 100%;
								font-size: 2.93333vmin;
								font-weight: 400;
								margin-right: 2.4vmin;
								color: #333333;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								span {
									text-overflow: ellipsis;
									display: block;
									margin-bottom: 1.8vmin;
									white-space: nowrap;
								}
							}

							.games_right {
								width: 100vmin;
								box-sizing: border-box;
								overflow: hidden; /*超出隐藏*/
								text-overflow: ellipsis;/*隐藏后添加省略号*/
								white-space: nowrap;/*强制不换行*/
								 text-overflow: ellipsis;
								  -o-text-overflow:ellipsis;	
								.right_title {
									display: flex;
									align-items: center;
									justify-content: center;
									position: relative;

									span {
										margin-bottom: 4.66667vmin;
										display: inline-block;
										font-weight: 700;
										color: #5d5d5d;
										white-space: nowrap;
										font-size: 3.2vmin;

										strong {
											margin: 0 .66667vmin;
											font-weight: 400;
										}

										em {
											font-style: normal;
											font-size: 2.4vmin;
											font-weight: 400;
											color: #999;
										}
									}
								}

								div {
									box-sizing: border-box;
									padding: 3.2vmin 0;
									height: 9.6vmin;
									width: 100%;
									border: .26667vmin solid #e6e6e6;
									background: #fff;
									color: #666;
									font-size: 3.46667vmin;
									border-radius: 4px;
									display: flex;
									align-items: center;
									justify-content: center;
								}
							}
						}

						.games:last-child {
							border: none;
						}
					}
				}

				.soccer_weekGame:last-child {
					margin-bottom: 13.33333vmin;
				}
			}

			.weekGame_games {
				border-bottom: 1px solid #e6e6e6;
			}
		}

		/*更多玩法*/
		.morePlays {
			/*height: 500px;*/
			width: 100%;

			.morePlay {
				background-color: #F2F2F2;
				width: 100%;

				/*头部*/
				h5 {
					/*font-weight: bold;*/
					font-size: 2.93333vmin;
					width: 100%;
					height: 9.33333vmin;
					background: #fff;
					margin-bottom: 2.13333vmin;
					display: flex;
					align-items: center;
					justify-content: center;

					span {
						margin: 0 14.66667vmin;
						font-weight: bold;
					}
				}

				/*选择*/
				.morePlay_select {
					margin: 0 2.93333vmin;
					font-size: 1.86667vmin;

					/*混合*/
					.morePlay_hunhe {
						/*height: 18.13333vmin;*/
						width: 100%;
						font-size: 3.46667vmin;
						color: #333333;
						display: flex;
						align-items: center;
						margin-bottom: 2.3vmin;

						/*左*/
						.hunhe_left {
							span {
								width: 6.9vmin;
								height: 70rpx;
								display: flex;
								align-items: center;
								justify-content: center;
							}

							.hunhe_left_top {
								background-color: rgb(224, 224, 224);
							}

							.hunhe_left_bottom {
								background-color: #19BE6B;
								color: white;
							}
						}

						/*右*/
						.hunhe_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							span {
								background-color: white;
								height: 9.1vmin;
								width: 28.8vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								align-items: center;
								justify-content: center;

								b:last-child {
									color: #666;
								}

								b:first-child {
									margin-right: 7vmin;
								}
							}
						}
					}

					/*总进球*/
					.morePlay_zongjinqiu {
						margin-bottom: 2.3vmin;
						/*height: 18.13333vmin;*/
						width: 100%;
						font-size: 3.46667vmin;
						color: #333333;
						display: flex;
						align-items: center;

						/*左*/
						.zongjinqiu_left {
							height: 21.13333vmin;
							background-color: #34ccc3;

							span {
								display: flex;
								align-items: center;
								justify-content: center;
								height: 100%;
								writing-mode: vertical-rl;
								letter-spacing: .53333vmin;
								font-size: 3.2vmin;
								width: 6.9vmin;
								color: white;
							}
						}

						.zongjinqiu_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;
							height: 18.13333vmin;

							span {
								background-color: white;
								height: 18.13333vmin/2;
								width: 21.5vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								align-items: center;
								justify-content: center;
								flex-direction: column;

								b {
									width: 21.5vmin/2;
									display: flex;
									justify-content: center;

									em {
										font-style: normal;
									}
								}

								b:last-child {
									color: #666;
								}
							}
						}
					}

					/*半全场*/
					.morePlay_banquanchang {
						margin-bottom: 2.13333vmin;
						/*height: 18.13333vmin;*/
						width: 100%;
						// font-size: 3.46667vmin;
						// color: #333333;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;
						

						/*左*/
						.banquanchang_left {
							height: 27.06667vmin;
							background-color: #36a8f8;

							span {
								display: flex;
								align-items: center;
								justify-content: center;
								height: 100%;
								writing-mode: vertical-rl;
								letter-spacing: .53333vmin;
								font-size: 3.2vmin;
								width: 6.9vmin;
								color: white;
							}
						}

						.banquanchang_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							span {
								background-color: white;
								height: 8.8vmin;
								width: 28.8vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								align-items: center;
								justify-content: center;

								b {
									width: 28.82vmin/2;
									display: flex;
									justify-content: center;
									margin-left: 10rpx;
									font-weight: normal;
								}

								b:last-child {
									// color: #666;
									color: #999;
									font-weight: 500;
								}
							}
						}
					}

					/*比分*/
					.morePlay_bifen {
						margin-bottom: 2.13333vmin;
						/*height: 18.13333vmin;*/
						width: 100%;
						font-size: 3.46667vmin;
						color: #333333;
						display: flex;
						align-items: center;

						/*左*/
						.bifen_left {
							height: 54.13333vmin;

							span {
								background-color: #fbb52f;
								display: flex;
								align-items: center;
								justify-content: center;
								writing-mode: vertical-rl;
								letter-spacing: .53333vmin;
								font-size: 3.2vmin;
								width: 6.9vmin;
								color: white;
								border-bottom: 2px solid #fff;
							}

							span:nth-child(1) {
								background-color: #fbb52f;
								height: 38%;
							}

							span:nth-child(2) {
								background-color: #D52BB3;
								height: 22%;
							}

							span:nth-child(3) {
								background-color: #1cedf2;
								height: 38%;
							}
						}

						/*右*/
						.bifen_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							span {
								background-color: white;
								height: 10.5vmin;
								width: 12.19vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								b:first-child {
									margin-bottom: 1.6vmin;
								}

								b:last-child {
									color: #666;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}
						}
					}

					/*比分-主胜*/
					.morePlay_bifen_zhusheng {
						margin-bottom: 2.13333vmin;
						/*height: 18.13333vmin;*/
						width: 100%;
						// font-size: 3.46667vmin;
						// color: #333333;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;

						/*左*/
						.bifen_left {
							height: 54.13333vmin/5*2;
							background-color: #fbb52f;

							span {
								display: flex;
								align-items: center;
								justify-content: center;
								height: 100%;
								writing-mode: vertical-rl;
								letter-spacing: .53333vmin;
								font-size: 3.2vmin;
								width: 6.9vmin;
								color: white;
							}
						}

						/*右*/
						.bifen_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							span {
								background-color: white;
								height: 10.5vmin;
								width: 12.19vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								b:first-child {
									// margin-bottom: 1.6vmin;
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}

								b:last-child {
									// color: #666;
									color: #999;
									font-weight: 500;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}

						}
					}

					/*比分-平*/
					.morePlay_bifen_ping {
						margin-bottom: 2.13333vmin;
						/*height: 18.13333vmin;*/
						width: 100%;
						// font-size: 3.46667vmin;
						// color: #333333;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;

						/*左*/
						.bifen_left {
							height: 160rpx;
							background-color: #5791F9;

							span {
								display: flex;
								align-items: center;
								justify-content: center;
								height: 100%;
								writing-mode: vertical-rl;
								letter-spacing: .53333vmin;
								font-size: 3.2vmin;
								width: 6.9vmin;
								color: white;
							}
						}

						/*右*/
						.bifen_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							span {
								background-color: white;
								height: 10.5vmin;
								width: 12.2vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								b:first-child {
									// margin-bottom: 1.6vmin;
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}

								b:last-child {
									// color: #666;
									color: #999;
									font-weight: 500;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}
						}
					}

					/*比分-客胜*/
					.morePlay_bifen_kesheng {
						margin-bottom: 2.13333vmin;
						width: 100%;
						// font-size: 3.46667vmin;
						// color: #333333;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;

						/*左*/
						.bifen_left {
							height: 54.13333vmin/5*2;
							background-color: #34ccc3;

							span {
								display: flex;
								align-items: center;
								justify-content: center;
								height: 100%;
								writing-mode: vertical-rl;
								letter-spacing: .53333vmin;
								font-size: 3.2vmin;
								width: 6.9vmin;
								color: white;
							}
						}

						/*右*/
						.bifen_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							span {
								background-color: white;
								height: 10.5vmin;
								width: 12.19vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								flex-direction: column;
								align-items: center;
								justify-content: center;

								b:first-child {
									// margin-bottom: 1.6vmin;
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}

								b:last-child {
									// color: #666;
									color: #999;
									font-weight: 500;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}
						}
					}
				}

				/*取消/确定*/
				.morePlay_action {
					width: 100%;
					font-size: 4.26667vmin;
					display: flex;

					div {
						width: 50%;
						height: 11.73333vmin;
						border-radius: 1.1vmin;
						display: flex;
						align-items: center;
						justify-content: center;
					}

					div:first-child {
						background: #ccc;
						color: #333333;
					}

					div:last-child {
						background-color: #9A3B86;
						color: white;
					}
				}
			}
		}
	}
</style>
