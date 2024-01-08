<template>
	<view class="box">
		<!-- 		<cmd-nav-bar title="竞彩篮球" :back="true" font-color="#fff" background-color="#9A3B86" @rightText="rightText"
			right-text="篮球开奖">
		</cmd-nav-bar> -->
		<hx-navbar :config="{title: '竞彩篮球', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}" />

		<div class="basketball_wrap">
			<!--主要内容-->
			<div class="basketball_main" v-show="isMainShow">
				<u-sticky offset-top="44" bgColor="#fff"
					style="display: flex;justify-content: center;align-items: center;top: 0;">
					<u-tabs :list="lassificationcList" lineColor="#9A3B86"
						:activeStyle="{color: '#9A3B86',fontWeight: 'bold',transform: 'scale(1.05)'}"
						@click="changeSelectBall"></u-tabs>
				</u-sticky>
				<!--1混合过关-->
				<u-empty icon="http://cdn.uviewui.com/uview/empty/data.png" :show="basketballList.length<=0" mode="data"
					text="暂无比赛"></u-empty>
				<div class="basketball_hunheguoguan" v-if="changeBall == 0">
					<!--每周比赛-->
					<div class="basketball_weekGame" v-for="(wk,weekIdx) in basketballList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span>共有{{wk.count}}场比赛可投</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.basketballMatchList" :key="index">
								<div class="games_left">
									<span><b> {{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color" :text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.visitingTeam}}<b>(客)</b><strong>vs</strong><b>(主)</b>{{game.homeTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<!--胜负-->
										<tr>
											<td class="table_left">胜负</td>
											<td class="table_center">
												<p v-if="game.winNegativeOddsList.length<=0">
													<span>未开售</span>
												</p>
												<p v-else>
													<span :class="{active:item.active}" style="position: relative;"
														@tap="selectBtn(game,item,weekIdx,index,1)"
														v-for="(item,winNegativeIndex) in game.winNegativeOddsList"><b>{{item.describe}}</b><b>{{item.odds}}</b>
														<view
															v-if="game.winNegativeOddsList.length>0&&winNegativeIndex==0&&game.isSingle=='1'"
															style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
															<view
																style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																单</view>
														</view>
													</span>
												</p>
											</td>
											<!--点击更多玩法时 传递：接口相关编号、队伍名-->
											<td class="morePlay" rowspan="3" @tap="morePlay(game,weekIdx,index)">
												<span v-if="game.choiceCount==0">更多<br>玩法</span>
												<span v-else>已选<span
														style="color: #9A3B86;display: block">{{game.choiceCount}}</span>项</span>
											</td>
										</tr>
										<!--让分-->
										<tr>
											<td class="table_left table_left_two">让分</td>
											<td class="table_center">
												<p v-if="game.cedePointsOddsList.length<=0">
													<span>未开售</span>
												</p>
												<p v-else>
													<span :class="{active:game.cedePointsOddsList[0].active}"
														style="position: relative;"
														@tap="selectBtn(game,game.cedePointsOddsList[0],weekIdx,index,2)"><b>{{game.cedePointsOddsList[0].describe}}</b><b>{{game.cedePointsOddsList[0].odds}}</b>
														<view
															v-if="game.winNegativeOddsList.length<=0&&game.isSingle=='1'"
															style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
															<view
																style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																单</view>
														</view>
													</span>
													<span :class="{active:game.cedePointsOddsList[1].active}"
														@tap="selectBtn(game,game.cedePointsOddsList[1],weekIdx,index,2)"><b>{{game.cedePointsOddsList[1].describe}}
															<strong
																:class="{fenRed: game.cedePoints > 0,active:game.cedePointsOddsList[1].active}">({{game.cedePoints}})</strong></b><b>{{game.cedePointsOddsList[1].odds}}</b></span>
												</p>
											</td>
										</tr>
										<!--大小分-->
										<tr>
											<td class="table_left table_left_three">总分</td>
											<td class="table_center">
												<p v-if="game.sizeOddsList.length<=0">
													<span>未开售</span>
												</p>
												<p>
													<span style="position: relative;"
														@tap="selectBtn(game,game.sizeOddsList[0],weekIdx,index,3)"
														:class="{active:game.sizeOddsList[0].active}"><b>大于{{game.sizeOddsList[1].odds}}分</b><b>{{game.sizeOddsList[0].odds}}</b>
														<view v-if="game.isSingle=='1'"
															style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
															<view
																style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																单</view>
														</view>
													</span>
													<span @tap="selectBtn(game,game.sizeOddsList[2],weekIdx,index,3)"
														:class="{active:game.sizeOddsList[2].active}"><b>小于{{game.sizeOddsList[1].odds}}分</b><b>{{game.sizeOddsList[2].odds}}</b></span>
												</p>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--2胜负-->
				<div class="basketball_shengpingfu" v-if="changeBall == 1">
					<div class="basketball_weekGame" v-for="(wk,weekIdx) in basketballList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span>共有{{wk.count}}场比赛可投</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.basketballMatchList">
								<div class="games_left">
									<span><b> {{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color" :text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.visitingTeam}}<b>(客)</b><strong>vs</strong><b>(主)</b>{{game.homeTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<!--胜负-->
										<tr>
											<td class="table_center">
												<p v-if="game.winNegativeOddsList.length<=0">
													<span>未开售</span>
												</p>
												<p v-else>
													<span :class="{active:item.active}" style="position: relative;"
														@tap="selectBtn(game,item,weekIdx,index,1)"
														v-for="(item,winNegativeIndex) in game.winNegativeOddsList"><b>{{item.describe}}</b><b
															:class="{active:item.active}">{{item.odds}}</b>
														<view
															v-if="game.winNegativeOddsList.length>0&&winNegativeIndex==0&&game.isSingle=='1'"
															style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
															<view
																style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																单</view>
														</view>
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
				<!--3让分胜负-->
				<div class="basketball_shengpingfu" v-if="changeBall == 2">
					<div class="basketball_weekGame" v-for="(wk,weekIdx) in basketballList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span>共有{{wk.count}}场比赛可投</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.basketballMatchList">
								<div class="games_left">
									<span><b>{{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color" :text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.visitingTeam}}<b>(客)</b><strong>vs</strong><b>(主)</b>{{game.homeTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<!--胜负-->
										<tr>
											<td class="table_center">
												<p v-if="game.cedePointsOddsList.length<=0">
													<span>未开售</span>
												</p>
												<p v-else>
													<span :class="{active:game.cedePointsOddsList[0].active}"
														style="position: relative;"
														@tap="selectBtn(game,game.cedePointsOddsList[0],weekIdx,index,2)"><b>客胜</b><b
															:class="{active:game.cedePointsOddsList[0].active}">{{game.cedePointsOddsList[0].odds}}</b>
														<view
															v-if="game.winNegativeOddsList.length<=0&&game.isSingle=='1'"
															style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
															<view
																style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																单</view>
														</view>
													</span>
													<span :class="{active:game.cedePointsOddsList[1].active}"
														@tap="selectBtn(game,game.cedePointsOddsList[1],weekIdx,index,2)"><b>主胜
															<strong
																:class="{title_Num_red: game.cedePoints > 0}">({{game.cedePoints}})</strong></b><b
															:class="{active:game.cedePointsOddsList[1].active}">{{game.cedePointsOddsList[1].odds}}</b></span>
												</p>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--4胜分差-->
				<div class="basketball_fencha" v-if="changeBall == 3">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in basketballList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span>共有{{wk.count}}场比赛可投</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.basketballMatchList">
								<img src="@/static/images/football/dan.png" />
								<div class="games_left">
									<span><b> {{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color" :text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.visitingTeam}}<strong>vs</strong>{{game.homeTeam}}</span>
									</span>
									<!--投注区-->
									<div @click="morePlay(game,weekIdx,index)">
										<span v-for="s in game.differenceOddsList" v-if="s.active"
											style="color: #9A3B86;font-weight: bold">
											{{s.describe}} |
										</span>
										<span v-if="game.differenceFlag!='false'">
											点击展开胜分差投注区
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--5大小分-->
				<div class="basketball_shengpingfu" v-if="changeBall == 4">
					<div class="basketball_weekGame" v-for="(wk,weekIdx) in basketballList" :key="weekIdx">
						<!--比赛折叠条-->
						<div class="weekGame_banner" @click="clickBanner(weekIdx)">
							<div class="banner_left">
								<span>{{wk.startTime}}</span>
								<span>{{wk.Data}}</span>
								<span>共有{{wk.count}}场比赛可投</span>
							</div>
							<u-icon :name="isChangeIcon === weekIdx?'arrow-up':'arrow-down'"></u-icon>
						</div>
						<!--比赛-->
						<div class="weekGame_games" v-show="isShowGames === weekIdx">
							<!--每个比赛-->
							<div class="games" v-for="(game,index) in wk.basketballMatchList">
								<div class="games_left">
									<span><b> {{game.number}}</b></span>
									<u-tag :borderColor="game.color" :bgColor="game.color" :text="game.match">
									</u-tag>
									<span>{{game.deadline|formatDate(that)}} 截止</span>
									<span style="color: rgb(41, 121, 255);" @click="analysis(game.analysis)">分析</span>
								</div>
								<div class="games_right">
									<!--比赛队伍-->
									<span class="right_title">
										<span>{{game.visitingTeam}}<b>(客)</b><strong>vs</strong><b>(主)</b>{{game.homeTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<!--胜负-->
										<tr>
											<td class="table_center">
												<p v-if="game.sizeOddsList.length<=0">
													<span>未开售</span>
													<span>未开售</span>
												</p>
												<p v-else>
													<span @tap="selectBtn(game,game.sizeOddsList[0],weekIdx,index,3)"
														:class="{active:game.sizeOddsList[0].active}"><b>大于{{parseInt(game.sizeOddsList[1].odds)}}分</b><b
															:class="{active:game.sizeOddsList[0].active}">{{game.sizeOddsList[0].odds}}</b></span>
													<span @tap="selectBtn(game,game.sizeOddsList[2],weekIdx,index,3)"
														:class="{active:game.sizeOddsList[2].active}"><b>小于{{parseInt(game.sizeOddsList[1].odds)}}分</b><b
															:class="{active:game.sizeOddsList[2].active}">{{game.sizeOddsList[2].odds}}</b></span>
												</p>
											</td>
										</tr>
									</table>
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
						{{basketball.visitingTeam}}(客)
						<span>VS</span>
						{{basketball.homeTeam}}(主)
					</h5>
					<!--玩法:根据头部index切换更多玩法显示内容-->
					<div class="morePlay_select">
						<!--1胜负-->
						<div v-show="changeBall === 0" class="morePlay_shengfu">
							<!--左-->
							<div class="shengfu_left">
								<span class="shengfu_left_top">胜负</span>
							</div>
							<!--右-->
							<div class="shengfu_right"
								v-if="basketball.winNegativeOddsList!=undefined&&basketball.winNegativeOddsList.length<=0">
								<span>未开售</span>
								<span>未开售</span>
							</div>
							<div class="shengfu_right"
								v-else-if="basketball.winNegativeOddsList!=undefined&&basketball.winNegativeOddsList.length>0">
								<span :class="{active:item.active}" @tap="selectBtn(basketball,item,listIdx,childIdx,1)"
									style="position: relative;"
									v-for="(item,winNegativeIndex) in basketball.winNegativeOddsList"><b>{{item.describe}}</b><b
										:class="{active:item.active}">{{item.odds}}</b>
									<view
										v-if="basketball.winNegativeOddsList.length>0&&winNegativeIndex==0&&basketball.isSingle=='1'"
										style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
										<view
											style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
											单</view>
									</view>
								</span>
							</div>
						</div>
						<!--2让分胜负-->
						<div v-show="changeBall === 0" class="morePlay_rangfen">
							<!--左-->
							<div class="rangfen_left">
								<span class="rangfen_left_top">让分胜负</span>
							</div>
							<!--右-->
							<div class="rangfen_right"
								v-if="basketball.cedePointsOddsList!=undefined&&basketball.cedePointsOddsList.length<=0">
								<span>未开售</span>
								<span>未开售</span>
							</div>
							<div class="rangfen_right"
								v-else-if="basketball.cedePointsOddsList!=undefined&&basketball.cedePointsOddsList.length>0">
								<div>
									<span :class="{active:basketball.cedePointsOddsList[0].active}"
										style="position: relative;"
										@tap="selectBtn(basketball,basketball.cedePointsOddsList[0],listIdx,childIdx,2)"><b>客胜</b><b
											:class="{active:basketball.cedePointsOddsList[0].active}">{{basketball.cedePointsOddsList[0].odds}}</b>
										<view v-if="basketball.winNegativeOddsList.length<=0&&basketball.isSingle=='1'"
											style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
											<view
												style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
												单</view>
										</view>
									</span>
									<span :class="{active:basketball.cedePointsOddsList[1].active}"
										@tap="selectBtn(basketball,basketball.cedePointsOddsList[1],listIdx,childIdx,2)"><b>主胜
											<strong
												:class="{fenRed: basketball.cedePoints > 0,active:basketball.cedePointsOddsList[1].active}">({{basketball.cedePoints}})</strong></b><b
											:class="{active:basketball.cedePointsOddsList[1].active}">{{basketball.cedePointsOddsList[1].odds}}</b></span>
								</div>
								<p class="right_bottom">让分胜负(主队<b>{{basketball.cedePoints}}</b>)</p>
							</div>
						</div>
						<!--3大小分-->
						<div v-show="changeBall === 0" class="morePlay_yushezongfen">
							<!--左-->
							<div class="yushezongfen_left">
								<span class="yushezongfen_left_top">总分</span>
							</div>
							<!--右-->
							<div class="yushezongfen_right"
								v-if="basketball.sizeOddsList!=undefined&&basketball.sizeOddsList.length<=0">
								<span>未开售</span>
								<span>未开售</span>
							</div>
							<div class="yushezongfen_right"
								v-else-if="basketball.sizeOddsList!=undefined&&basketball.sizeOddsList.length>0">
								<div>
									<span @tap="selectBtn(basketball,basketball.sizeOddsList[0],listIdx,childIdx,3)"
										:class="{active:basketball.sizeOddsList[0].active}"><b>大于{{basketball.sizeOddsList[1].odds}}分</b><b
											:class="{active:basketball.sizeOddsList[0].active}">{{basketball.sizeOddsList[0].odds}}</b></span>
									<span @tap="selectBtn(basketball,basketball.sizeOddsList[2],listIdx,childIdx,3)"
										:class="{active:basketball.sizeOddsList[2].active}"><b>小于{{basketball.sizeOddsList[1].odds}}分</b><b
											:class="{active:basketball.sizeOddsList[2].active}">{{basketball.sizeOddsList[2].odds}}</b></span>
								</div>
								<p class="right_bottom">预设总分(<b>{{parseFloat(basketball.sizeOddsList[1].odds)}}</b>)
								</p>
							</div>
						</div>
						<!--4胜分差 客-->
						<div v-show="changeBall === 0 || changeBall === 3" class="morePlay_shengfencha">
							<!--左-->
							<div class="shengfencha_left">
								<span class="shengfencha_left_top">胜分差</span>
							</div>
							<!--右-->
							<div class="shengfencha_right"
								style="height: 31.2vmin;width: 100%;justify-content: center;background: #fff;"
								v-if="basketball.differenceOddsList!=undefined&&basketball.differenceOddsList.length<=0">
								<span>未开售</span>
							</div>
							<div class="shengfencha_right"
								v-else-if="basketball.differenceOddsList!=undefined&&basketball.differenceOddsList.length>0">
								<div>
									<span @tap="selectBtn(basketball,a,listIdx,childIdx,4)" :class="{active:a.active}"
										v-if="index <= 2 " v-for="(a,index) in basketball.differenceOddsList"
										:key="index"><b>{{shengfenchaArr[index]}}</b><b
											:class="{active:a.active}">{{a.odds}}</b></span>
								</div>
								<div>
									<span @tap="selectBtn(basketball,a,listIdx,childIdx,4)" :class="{active:a.active}"
										v-if="index <= 5 && index >2 "
										v-for="(a,index) in basketball.differenceOddsList"
										:key="index"><b>{{shengfenchaArr[index]}}</b><b
											:class="{active:a.active}">{{a.odds}}</b></span>
								</div>
								<p class="right_bottom">{{basketball.visitingTeam}}(客胜)</p>
							</div>
						</div>
						<!--5胜分差 主-->
						<div v-show="changeBall === 0 || changeBall === 3" class="morePlay_shengfencha">
							<!--左-->
							<div class="shengfencha_left fenchaBlue">
								<span class="shengfencha_left_top ">胜分差</span>
							</div>

							<div class="shengfencha_right"
								style="height: 31.2vmin;width: 100%;justify-content: center;background: #fff;"
								v-if="basketball.differenceOddsList!=undefined&&basketball.differenceOddsList.length<=0">
								<span>未开售</span>
							</div>
							<!--右-->
							<div class="shengfencha_right"
								v-else-if="basketball.differenceOddsList!=undefined&&basketball.differenceOddsList.length>0">
								<div>
									<span :class="{active:a.active}" v-if="index >= 6 && index < 9"
										@tap="selectBtn(basketball,a,listIdx,childIdx,4)"
										v-for="(a,index) in basketball.differenceOddsList"
										:key="index"><b>{{shengfenchaArr[index]}}</b><b
											:class="{active:a.active}">{{a.odds}}</b></span>
								</div>
								<div>
									<span v-if="index >= 9 " :class="{active:a.active}"
										v-for="(a,index) in basketball.differenceOddsList"
										@tap="selectBtn(basketball,a,listIdx,childIdx,4)"
										:key="index"><b>{{shengfenchaArr[index]}}</b><b
											:class="{active:a.active}">{{a.odds}}</b></span>
								</div>
								<p class="right_bottom">{{basketball.homeTeam}}(主胜)</p>
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
		querybBasketballList
	} from '@/api/basketball.js'
	import BuyFooterSport from '../common/buy-footer-sport.vue'
	export default {
		name: 'Basketball',
		components: {
			BuyFooterSport
		},
		data() {
			return {
				lassificationcList: [{
					name: '混合过关'
				}, {
					name: '胜负'
				}, {
					name: '让分胜负'
				}, {
					name: '胜分差'
				}, {
					name: '大小分'
				}],
				basketballList: [],
				that: this,
				basketball: {},
				// 混合玩法-更多玩法
				isShowMorePlay: false,
				isChangeIcon: 0,
				isShowGames: 0,
				isMainShow: true,
				isShowCount: false,
				changeBall: 0,
				MethodsArr: ['混合过关', '胜负', '让分胜负', '胜分差', '大小分'],
				shengfenchaArr: ['1-5', '6-10', '11-15', '16-20', '21-25', '26分+', '1-5', '6-10', '11-15', '16-20',
					'21-25', '26分+'
				],
				listIdx: "",
				childIdx: "",
				count: "",
				total: "",
				selectItem: []
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
		onLoad() {
			this.fecthBasketballData()
		},
		onPullDownRefresh() {
			this.fecthBasketballData();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
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
					//胜负赔率
					this.basketballList[weekIdx].basketballMatchList[index].winNegativeOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 2) {
					//让分胜负赔率
					this.basketballList[weekIdx].basketballMatchList[index].cedePointsOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 3) {
					//大小分赔率
					this.basketballList[weekIdx].basketballMatchList[index].sizeOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 4) {
					//胜分差赔率
					this.basketballList[weekIdx].basketballMatchList[index].differenceOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
					//标识是否选择了胜分差
					this.$set(wk, 'differenceFlag', "false")
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
			//清空事件
			clean() {
				this.count = "";
				this.fecthBasketballData()
				this.selectItem = []
			},
			rightText() {
				uni.navigateTo({
					url: "pages/basketball/basketballResult"
				});
			},
			confirm() {
				var count;
				var flag = true;
				this.selectItem.map((item) => {
					//循环遍历是否是单关
					if (item.isSingle == '1') {
						if (item.winNegativeOddsList.length > 0) {
							item.cedePointsOddsList.map(cedePoints => {
								if (cedePoints.active) {
									count = this.selectItem.length;
									//用来标记是否是单关，传到下单也有用来判断是否有那个单关的下注选项
									flag = false;
									return;
								}
							})
						}
					} else {
						//不是单关判断让球，非让球,大小分是否有选择数据
						item.winNegativeOddsList.map(winNegative => {
							if (winNegative.active) {
								count = this.selectItem.length;
								//用来标记是否是单关，传到下单也有用来判断是否有那个单关的下注选项
								flag = false;
								return;
							}
						})
						item.cedePointsOddsList.map(cedePoints => {
							if (cedePoints.active) {
								count = this.selectItem.length;
								//用来标记是否是单关，传到下单也有用来判断是否有那个单关的下注选项
								flag = false;
								return;
							}
						})
						item.sizeOddsList.map(size => {
							if (size.active) {
								count = this.selectItem.length;
								//用来标记是否是单关，传到下单也有用来判断是否有那个单关的下注选项
								flag = false;
								return;
							}
						})
					}
				})
				if (count < 2 || this.selectItem.length <= 0) {
					uni.showToast({
						title: '至少选择二场比赛',
						icon: 'none'
					});
					return;
				}
				if (this.selectItem.length > 8) {
					uni.showToast({
						title: '最多选择8场比赛',
						icon: 'none'
					});
					return;
				}
				//总投注选项数不能超过20个
				let selectCount = 0;
				this.selectItem.map((map, idx) => {
					selectCount += map.choiceCount
				})
				if (selectCount > 20) {
					uni.showToast({
						title: '总投注选项数不能超过20个',
						icon: 'none'
					});
					return;
				}
				uni.navigateTo({
					url: "pages/basketball/basketballConfirm?obj=" + encodeURIComponent(JSON.stringify(this
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
			// 获取竞彩蓝球数据
			fecthBasketballData() {
				uni.showLoading();
				querybBasketballList().then(res => {
					this.basketballList = res.voList
					setTimeout(function() {
						uni.hideLoading();
					}, 50);
				})
			},
			// 不同玩法对应投注
			changeSelectBall(item) {
				this.fecthBasketballData()
				this.changeBall = item.index
				this.selectItem = [];
				this.count = "";
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
				this.basketball = item;
				this.listIdx = listIdx
				this.childIdx = childIdx
			}
		},
	}
</script>

<style scoped lang="scss">
	/deep/.u-tag {
		width: 45px;
		height: 20px;
		justify-content: center;
		align-items: center;
	}

	/deep/.u-tag__text--medium {
		font-size: 11px;
		line-height: 11px;
	}

	.box {
		padding-bottom: 0px;
	}

	/deep/.u-empty {
		margin-top: 50% !important;
	}

	/deep/.cmd-nav-bar-right-text {
		font-size: 16px !important;
	}


	page {
		background-color: #f7f9fa;
	}

	.title_Num_red {
		color: #FF5562 !important;
	}

	.active {
		background-color: #9A3B86 !important;
		color: #fff !important;
	}

	.basketball_wrap {

		/*主内容*/
		.basketball_main {
			background-color: #f7f9fa;
			width: 100%;
			padding-bottom: 10px;

			/*混合*/
			.basketball_hunheguoguan {
				width: 100%;
				padding-bottom: 10px;

				.basketball_weekGame {
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
						}

						.icon-jiantou2-change {
							transform: rotate(180deg);
						}
					}

					/*比赛*/
					.weekGame_games {
						width: 100%;
						border-bottom: 1px solid #e6e6e6;

						.games {
							box-sizing: border-box;
							display: flex;
							align-items: center;
							justify-content: flex-start;
							height: 48.66667vmin;
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
								width: 100%;
								box-sizing: border-box;

								.right_title {
									/*box-sizing: border-box;*/
									display: flex;
									align-items: center;
									justify-content: center;

									span {
										height: 27px;
										font-size: 3.73333vmin;
										display: inline-block;
										font-weight: 700;
										color: #5d5d5d;
										white-space: nowrap;

										strong {
											margin: 0 4vmin;
											font-weight: 400;
										}

										b {
											font-size: 3.2vmin;
											color: #999999;
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
											// font-size: 3.46667vmin;
											font-size: 13px;
											// font-weight: 500 !important;
											background-color: white;
										}

										.table_left {
											border: none;
											height: 11.73333vmin;
											width: 3.73333vmin;
											font-size: 2.93333vmin;
											text-align: center;
											background: #34ccc3;
											vertical-align: middle;
											color: white;
										}

										.table_left_two {
											background-color: #fbb52f;
										}

										.table_left_three {
											background-color: #36a8f8;
										}

										.table_center {
											height: 11.73333vmin;
											box-sizing: border-box;
											border: 1px solid #e6e6e6;
											display: flex;
											justify-content: center;
											align-items: center;

											p {
												display: flex;
												align-items: center;
												height: 100%;
												box-sizing: border-box;
												justify-content: center;

												/*text-align: center;*/
												span {
													width: 31.5vmin;
													display: flex;
													align-items: center;
													justify-content: center;
													flex-direction: column;
													height: 100%;
													border-right: 1px solid #e6e6e6;

													strong {
														color: #5791F9;
													}

													.fenRed {
														color: #9A3B86;
													}

													b:first-child {
														// margin-bottom: 1.5vmin;
														margin-bottom: -0.4vmin;
														font-weight: normal;
													}

													b:last-child {
														font-weight: 500;
														color: #999;
													}
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

				.basketball_weekGame:last-child {
					margin-bottom: 13.33333vmin;
				}
			}

			/*胜负\让分胜负\大小分*/
			.basketball_shengpingfu {
				background-color: #f7f9fa;
				width: 100%;

				.basketball_weekGame {
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
								width: 100%;
								box-sizing: border-box;

								.right_title {
									/*box-sizing: border-box;*/
									display: flex;
									align-items: center;
									justify-content: center;
									position: relative;

									span {
										margin-bottom: 2.66667vmin;
										font-size: 3.73333vmin;
										display: inline-block;
										font-weight: 700;
										color: #5d5d5d;
										white-space: nowrap;

										strong {
											margin: 0 4vmin;
											font-weight: 400;
										}

										b {
											font-size: 3.2vmin;
											color: #999;
										}
									}

									.title_Num {
										font-size: 3.73333vmin;
										position: absolute;
										top: 0;
										right: 0;
										margin-right: 1.33333vmin;
										color: #5791F9;
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

										.table_center {
											height: 11.73333vmin;
											box-sizing: border-box;
											border: 1px solid #e6e6e6;
											display: flex;
											justify-content: center;
											align-items: center;

											p {
												display: flex;
												align-items: center;
												height: 100%;
												box-sizing: border-box;

												/*text-align: center;*/
												span {
													width: 38vmin;
													display: flex;
													align-items: center;
													justify-content: center;
													flex-direction: column;
													height: 100%;
													border-right: 1px solid #e6e6e6;

													strong {
														color: #5791F9;
													}

													b:first-child {
														// font-size: 3.73333vmin;
														// margin-bottom: 1.1vmin;
														font-size: 13px;
														margin-bottom: -0.4vmin;
														font-weight: normal;
													}

													b:last-child {
														// color: #666
														color: #999;
														font-weight: 500;
													}
												}

												span:last-child {
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

				.basketball_weekGame:last-child {
					margin-bottom: 13.33333vmin;
				}
			}

			/*胜分差*/
			.basketball_fencha {
				background-color: #f7f9fa;
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
							height: 28.26667vmin;
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
								margin-top: 15px;

								span {
									text-overflow: ellipsis;
									display: block;
									margin-bottom: 1.8vmin;
									white-space: nowrap;
								}
							}

							.games_right {
								width: 100%;
								box-sizing: border-box;
								overflow: hidden;
								/*超出隐藏*/
								text-overflow: ellipsis;
								/*隐藏后添加省略号*/
								white-space: nowrap;
								/*强制不换行*/
								text-overflow: ellipsis;
								-o-text-overflow: ellipsis;

								.right_title {
									display: flex;
									align-items: center;
									justify-content: center;
									position: relative;

									span {
										margin-bottom: 3.66667vmin;
										font-size: 3.73333vmin;
										display: inline-block;
										font-weight: 700;
										color: #5d5d5d;
										white-space: nowrap;

										strong {
											margin: 0 4vmin;
											font-weight: 400;
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
		}

		/*更多玩法*/
		.morePlays {
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

					/*胜负*/
					.morePlay_shengfu {
						width: 100%;
						// font-size: 3.46667vmin;
						// color: #333333;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;
						margin-bottom: 2.3vmin;


						/*左*/
						.shengfu_left {
							background-color: #36a8f8;
							width: 6.9vmin;
							height: 11.73333vmin;
							display: flex;
							justify-content: center;
							align-items: center;
							text-align: center;
							font-size: 3.16667vmin;

							span {
								writing-mode: vertical-rl;
								color: white;
							}
						}

						/*右*/
						.shengfu_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							span {
								background-color: white;
								height: 11.73333vmin;
								width: 43.33333vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								align-items: center;
								justify-content: center;
								flex-direction: column;

								b:last-child {
									color: #999;
									font-weight: 500;
								}

								b:first-child {
									// margin-bottom: 1.2vmin;
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}
							}
						}
					}

					/*让分胜负*/
					.morePlay_rangfen {
						width: 100%;
						// font-size: 3.46667vmin;
						// color: #333333;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;
						margin-bottom: 2.3vmin;
						box-sizing: border-box;

						/*左*/
						.rangfen_left {
							background-color: #34ccc3;
							width: 6.9vmin;
							height: 19.6vmin;
							display: flex;
							justify-content: center;
							text-align: center;
							font-size: 3.16667vmin;
							align-items: center;

							span {
								writing-mode: vertical-rl;
								color: white;
							}
						}

						/*右*/
						.rangfen_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-direction: column;
							/*justify-content: center;*/
							flex-wrap: wrap;

							div {
								display: flex;
								align-items: center;

								span {
									background-color: white;
									height: 11.73333vmin;
									width: 43.33333vmin;
									border-right: 1px solid #e6e6e6;
									border-bottom: 1px solid #e6e6e6;
									display: flex;
									align-items: center;
									justify-content: center;
									flex-direction: column;

									b:last-child {
										color: #999;
										font-weight: 500;
									}

									b:first-child {
										margin-bottom: -0.4vmin;
										font-weight: normal;
									}
								}
							}

							p {
								width: 43.33333vmin*2;
								display: flex;
								align-items: center;
								justify-content: center;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								background-color: #f7f9fa;
								color: #666;
								height: 7.73333vmin;

								b {
									color: #FF5562;
								}
							}
						}
					}

					/*预设总分*/
					.morePlay_yushezongfen {
						width: 100%;
						font-size: 3.46667vmin;
						color: #333333;
						display: flex;
						align-items: center;
						margin-bottom: 2.3vmin;
						box-sizing: border-box;

						/*左*/
						.yushezongfen_left {
							background-color: #6b8dff;
							width: 6.9vmin;
							height: 19.6vmin;
							display: flex;
							justify-content: center;
							text-align: center;
							align-items: center;
							font-size: 3.16667vmin;

							span {
								writing-mode: vertical-rl;
								color: white;
							}
						}

						/*右*/
						.yushezongfen_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-direction: column;
							flex-wrap: wrap;
							font-size: 13px;
							color: #303133;

							div {
								display: flex;
								align-items: center;

								span {
									background-color: white;
									height: 11.73333vmin;
									width: 43.33333vmin;
									border-right: 1px solid #e6e6e6;
									border-bottom: 1px solid #e6e6e6;
									display: flex;
									align-items: center;
									justify-content: center;
									flex-direction: column;

									b:last-child {
										// color: #666;
										color: #999;
										font-weight: 500;
									}

									b:first-child {
										// margin-bottom: 1.2vmin;
										margin-bottom: -0.4vmin;
										font-weight: normal;
									}
								}
							}

							p {
								width: 43.33333vmin*2;
								display: flex;
								align-items: center;
								justify-content: center;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								background-color: #f7f9fa;
								color: #666;
								height: 7.73333vmin;

								b {
									color: #FF5562;
								}
							}
						}
					}

					/*胜分差1*/
					.morePlay_shengfencha {
						width: 100%;
						// font-size: 3.46667vmin;
						// color: #333333;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;
						margin-bottom: 2.3vmin;
						box-sizing: border-box;

						/*左*/
						.shengfencha_left {
							background-color: #fbb52f;
							width: 6.9vmin;
							height: 31.2vmin;
							display: flex;
							justify-content: center;
							text-align: center;
							font-size: 3.16667vmin;
							align-items: center;

							span {
								writing-mode: vertical-rl;
								color: white;
							}
						}

						.fenchaBlue {
							background-color: #36a8f8;
						}

						/*右*/
						.shengfencha_right {
							border-top: 1px solid #e6e6e6;
							display: flex;
							align-items: center;
							flex-direction: column;
							flex-wrap: wrap;

							div {
								display: flex;
								align-items: center;
								flex-wrap: wrap;

								span {
									background-color: white;
									height: 11.73333vmin;
									width: 28.8vmin;
									border-right: 1px solid #e6e6e6;
									border-bottom: 1px solid #e6e6e6;
									display: flex;
									align-items: center;
									justify-content: center;
									flex-direction: column;

									b:last-child {
										// color: #666;
										color: #999;
										font-weight: 500;
									}

									b:first-child {
										// margin-bottom: 1.2vmin;
										margin-bottom: -0.4vmin;
										font-weight: normal;
									}
								}
							}

							p {
								width: 43.33333vmin*2;
								display: flex;
								align-items: center;
								justify-content: center;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								background-color: #f7f9fa;
								color: #666;
								height: 7.73333vmin;
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
						background-color: #FF5562;
						color: white;
					}
				}
			}
		}
	}
</style>