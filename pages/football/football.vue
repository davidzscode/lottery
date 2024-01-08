<template>
	<view>
		<hx-navbar :config="{title: '竞彩足球', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}" />
		<!-- <cmd-nav-bar title="竞彩足球" :back="true" font-color="#fff" background-color="#9A3B86" @rightText="rightText" -->
		<!-- right-text="足球开奖"> -->
		<!-- </cmd-nav-bar> -->
		<u-sticky offset-top="44" bgColor="#fff"
			style="margin-bottom: 0px;display: flex;justify-content: center;align-items: center;top: 0;">
			<u-tabs :list="lassificationcList" lineColor="#9A3B86"
				:activeStyle="{color: '#9A3B86',fontWeight: 'bold',transform: 'scale(1.05)'}"
				@click="changeSelectBall"></u-tabs>
		</u-sticky>
		<div class="soccer_wrap">
			<!--main-->
			<div class="soccer_main" v-show="isMainShow">

				<!--混合过关-->
				<u-empty icon="http://cdn.uviewui.com/uview/empty/data.png" :show="beidanList.length<=0" mode="data"
					text="暂无比赛"></u-empty>
				<div class="soccer_hunheguoguan" v-if="changeBall == 0">
					<!--每周比赛-->
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
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
							<div class="games" v-for="(game,index) in wk.footballMatchList" :key="index">
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
										<span
											class="title_team">{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<tr>
											<td class="burangqiu">0</td>
											<td class="sheng_top">
												<p>
													<span style="position: relative;"
														v-for="(notLet,notLetIndex) in game.notLetOddsList"
														:class="{active:notLet.active}"
														@tap="selectBtn(game,notLet,weekIdx,index,1)">{{notLet.describe}}
														{{notLet.odds}}
														<view
															v-if="game.notLetOddsList.length>0&&notLetIndex==0&&game.isSingle=='1'"
															style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
															<view
																style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																单</view>
														</view>
													</span>
													<span v-if="game.notLetOddsList.length<=0">未开售</span>
												</p>
											</td>
											<!--点击更多玩法时 传递：接口相关编号、队伍名-->
											<td class="morePlay" rowspan="2" @tap="morePlay(game,weekIdx,index)">
												<span v-if="game.choiceCount==0">更多<br>玩法</span>
												<span v-else>已选<span
														style="color: #9A3B86;display: block">{{game.choiceCount}}</span>项</span>
											</td>
										</tr>
										<tr>
											<td class="rangqiu" :class="{rangqiuBlue:game.letBall < 0}">
												{{game.letBall}}
											</td>
											<td class="sheng_top">
												<p>
													<span v-if="game.letOddsList.length<=0">未开售</span>
													<span v-else v-for="(lets,letsIndex) in game.letOddsList"
														style="position: relative;" :class="{active:lets.active}"
														@tap="selectBtn(game,lets,weekIdx,index,2)">{{lets.describe}}
														{{lets.odds}}
														<view
															v-if="game.notLetOddsList.length<=0&&letsIndex==0&&game.isSingle=='1'"
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
				<!--胜平负-->
				<div class="soccer_shengpingfu" v-if="changeBall == 1">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
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
							<div class="games" v-for="(game,index) in wk.footballMatchList">
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
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</em></span>
									</span>
									<!--表格-->
									<table>
										<tr v-if="game.notLetOddsList.length<=0">
											<td class="sheng_top">
												<span>未开售</span>
											</td>
										</tr>
										<tr v-else>
											<td class="sheng_top">
												<p>
													<span @tap="selectBtn(game,notLet,weekIdx,index,1)"
														style="position: relative;" :class="{active:notLet.active}"
														class="sheng_top_block"
														v-for="(notLet,notLetIndex) in game.notLetOddsList">
														<span>{{notLet.describe}}</span>
														<span :class="{active:notLet.active}">{{notLet.odds}}
															<view
																v-if="game.notLetOddsList.length>0&&notLetIndex==0&&game.isSingle=='1'"
																style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
																<view
																	style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																	单</view>
															</view>
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
				<!--让球平负-->
				<div class="soccer_shengpingfu" v-if="changeBall == 2">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
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
							<div class="games" v-for="(game,index) in wk.footballMatchList">
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
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--表格-->
									<table>
										<tr>
											<td class="sheng_top">
												<p>
													<span v-if="game.letOddsList.length<=0">未开售</span>
													<span v-else @tap="selectBtn(game,lets,weekIdx,index,2)"
														style="position: relative;" :class="{active:lets.active}"
														class="sheng_top_block"
														v-for="(lets,letsIndex) in game.letOddsList">
														<span>{{lets.describe}}</span>
														<span :class="{active:lets.active}">{{lets.odds}}
															<view
																v-if="game.notLetOddsList.length<=0&&letsIndex==0&&game.isSingle=='1'"
																style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
																<view
																	style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
																	单</view>
															</view>
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
				<div class="soccer_zongjinqiu" v-if="changeBall == 3">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
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
							<div class="games" v-for="(game,index) in wk.footballMatchList">
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
				<div class="soccer_bifen" v-if="changeBall == 4">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
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
							<div class="games" v-for="(game,idx) in wk.footballMatchList">
								<img src="@/static/images/football/dan.png" />
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
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--投注区-->
									<div @click="morePlay(game,weekIdx,idx)">
										<span v-for="s in game.scoreOddsList" v-if="s.active"
											style="color: #9A3B86;font-weight: bold">
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
				<div class="soccer_bifen" v-if="changeBall == 5">
					<div class="soccer_weekGame" v-for="(wk,weekIdx) in beidanList" :key="weekIdx">
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
							<div class="games" v-for="(game,idx) in wk.footballMatchList">
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
										<span>{{game.homeTeam}}<strong>vs</strong>{{game.visitingTeam}}</span>
									</span>
									<!--投注区-->
									<div @click="morePlay(game,weekIdx,idx)">
										<span v-for="s in game.halfWholeOddsList" v-if="s.active"
											style="color: #9A3B86;font-weight: bold">
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
						{{football.homeTeam}}
						<span>VS</span>
						{{football.visitingTeam}}
					</h5>
					<!--玩法:根据头部index切换更多玩法显示内容-->
					<div class="morePlay_select">
						<!--1混合-->
						<div v-show="changeBall === 0" class="morePlay_hunhe">
							<!--左-->
							<div class="hunhe_left">
								<span class="hunhe_left_top">0</span>
								<span class="hunhe_left_bottom">{{football.letBall}}</span>
							</div>
							<!--右-->
							<div class="hunhe_right">
								<span v-if="football.notLetOddsList!=undefined&&football.notLetOddsList.length<=0"
									style="width: 100%;"><b>未开售</b></span>
								<span style="position: relative;"
									v-else-if="football.notLetOddsList!=undefined&&football.notLetOddsList.length>0"
									v-for="(notLet,notLetIndex) in football.notLetOddsList"
									:class="{active:notLet.active}"
									@tap="selectBtn(football,notLet,listIdx,childIdx,1)">{{notLet.describe}}
									<b :class="{active:notLet.active}">{{notLet.odds}}
										<view
											v-if="football.notLetOddsList.length>0&&notLetIndex==0&&football.isSingle=='1'"
											style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
											<view
												style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
												单</view>
										</view>
									</b></span>

								<span v-if="football.letOddsList!=undefined&&football.letOddsList.length<=0"
									style="width: 100%;"><b>未开售</b></span>
								<span style="position: relative;"
									v-else-if="football.letOddsList!=undefined&&football.letOddsList.length>0"
									v-for="(lets,letsIndex) in football.letOddsList" :class="{active:lets.active}"
									@tap="selectBtn(football,lets,listIdx,childIdx,2)">{{lets.describe}}
									<b :class="{active:lets.active}">{{lets.odds}}</b>
									<view v-if="football.notLetOddsList.length<=0&&letsIndex==0&&football.isSingle=='1'"
										style="position: absolute; left: 0px; top: 0px; width: 0px; height: 0px; border-top: 25px solid #9A3B86; border-right: 25px solid transparent;">
										<view
											style="position: absolute; left: 1px; top: -25px; color: white; font-size: 11px;">
											单</view>
									</view>
								</span>
							</div>
						</div>
						<!--2总进球-->
						<div v-show="changeBall === 0" class="morePlay_zongjinqiu">
							<!--左-->
							<div class="zongjinqiu_left">
								<span>总进球</span>
							</div>
							<!--右-->
							<div class="zongjinqiu_right" v-if="football.goalOddsList!=undefined">
								<span v-if="football.goalOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-else @tap="selectBtn(football,a,listIdx,childIdx,3)" :class="{active:a.active}"
									v-for="(a,index) in football.goalOddsList" :key="index"><b>{{index}}<em
											v-if="index === 7">+</em></b> <b
										:class="{active:a.active}">{{a.odds}}</b></span>
							</div>
						</div>
						<!--3半全场-->
						<div v-show="changeBall === 0 || changeBall === 5" class="morePlay_banquanchang">
							<!--左-->
							<div class="banquanchang_left">
								<span>半全场</span>
							</div>
							<!--右-->
							<div class="banquanchang_right" v-if="football.halfWholeOddsList!=undefined">
								<span v-if="football.halfWholeOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-else :class="{active:a.active}" @tap="selectBtn(football,a,listIdx,childIdx,5)"
									v-for="(a,index) in football.halfWholeOddsList"><b>{{bqcItems[index]}}</b>
									<b :class="{active:a.active}">{{a.odds}}</b></span>
							</div>
						</div>
						<!--4比分-->
						<div v-show="changeBall === 0" class="morePlay_bifen">
							<!--左-->
							<div class="bifen_left">
								<span>比分胜</span>
								<span>比分平</span>
								<span>比分负</span>
							</div>
							<!--右-->
							<div class="bifen_right" v-if="football.scoreOddsList!=undefined">
								<span v-if="football.scoreOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-else @tap="selectBtn(football,b,listIdx,childIdx,4)"
									v-for="(b,index) in football.scoreOddsList" :key="index"
									:class="{doubleW:index === 12 || index === 30, treW:index === 17,active:b.active}">
									<b>{{bifenItems[index]}}</b>
									<b :class="{active:b.active}">{{b.odds}}</b>
								</span>
							</div>
						</div>
						<!--5比分拆分版-->
						<div v-show="changeBall === 4" class="morePlay_bifen_zhusheng">
							<!--左-->
							<div class="bifen_left">
								<span>主胜</span>
							</div>
							<!--右-->
							<div class="bifen_right" v-if="football.scoreOddsList!=undefined">
								<span v-if="football.scoreOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-if="index <= 12" v-for="(b,index) in football.scoreOddsList" :key="index"
									@tap="selectBtn(football,b,listIdx,childIdx,4)"
									:class="{doubleW:index === 12,active:b.active}">
									<b>{{bifenItems[index]}}</b>
									<b :class="{active:b.active}">{{b.odds}}</b>
								</span>
							</div>
						</div>
						<div v-show="changeBall === 4" class="morePlay_bifen_ping">
							<!--左-->
							<div class="bifen_left">
								<span>平</span>
							</div>
							<!--右-->
							<div class="bifen_right" v-if="football.scoreOddsList!=undefined">
								<span v-if="football.scoreOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-if="index > 12 && index <= 17" @tap="selectBtn(football,b,listIdx,childIdx,4)"
									v-for="(b,index) in football.scoreOddsList" :key="index"
									:class="{treW:index === 17,active:b.active}">
									<b>{{bifenItems[index]}}</b>
									<b :class="{active:b.active}">{{b.odds}}</b>
								</span>
							</div>
						</div>
						<div v-show="changeBall === 4" class="morePlay_bifen_kesheng">
							<!--左-->
							<div class="bifen_left">
								<span>客胜</span>
							</div>
							<!--右-->
							<div class="bifen_right" v-if="football.scoreOddsList!=undefined">
								<span v-if="football.scoreOddsList.length<=0" style="width: 100%;"><b>未开售</b></span>
								<span v-if="index > 17" v-for="(b,index) in football.scoreOddsList" :key="index"
									@tap="selectBtn(football,b,listIdx,childIdx,4)"
									:class="{doubleW:index === 30,active:b.active}">
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
		footballWinEvenLoseList
	} from '@/api/football.js'
	import BuyFooterSport from '../common/buy-footer-sport.vue'
	export default {
		name: 'Soccer',
		components: {
			BuyFooterSport
		},
		data() {
			return {
				lassificationcList: [{
					name: '混合过关'
				}, {
					name: '胜平负'
				}, {
					name: '让球胜平负'
				}, {
					name: '总进球'
				}, {
					name: '比分'
				}, {
					name: '半全场'
				}],
				bifenItems: ['1:0', '2:0', '2:1', '3:0', '3:1', '3:2', '4:0', '4:1', '4:2', '5:0', '5:1', '5:2', '胜其他',
					'0:0', '1:1', '2:2', '3:3', '平其他', '0:1', '0:2', '1:2', '0:3', '1:3', '2:3', '0:4', '1:4', '2:4',
					'0:5', '1:5', '2:5', '负其他'
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
				football: {},
				that: this,
				listIdx: "",
				childIdx: "",
				count: "",
				total: "",
				selectItem: [],
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
		onPullDownRefresh() {
			this.fecthSoccerData();
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
					//不让球
					this.beidanList[weekIdx].footballMatchList[index].notLetOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 2) {
					//让球
					this.beidanList[weekIdx].footballMatchList[index].letOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 3) {
					//总进球
					this.beidanList[weekIdx].footballMatchList[index].goalOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
				} else if (type == 4) {
					//比分
					this.beidanList[weekIdx].footballMatchList[index].scoreOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
					//标识是否选择了比分
					this.$set(wk, 'scoreFlag', "false")
				} else if (type == 5) {
					//半全场
					this.beidanList[weekIdx].footballMatchList[index].halfWholeOddsList.map(data => {
						if (item.id == data.id) {
							this.$set(data, "active", !item.active)
						}
					})
					this.$set(wk, 'halfWholeFlag', "false")
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
				// this.isShowCount=true;
				this.count = this.selectItem.length;
				// this.total=2;
			},
			rightText() {
				uni.navigateTo({
					url: "pages/football/footballResult"
				});
			},
			clean() {
				this.count = "";
				this.fecthSoccerData();
				this.selectItem = []
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
			confirm() {
				var count;
				var flag = true;
				this.selectItem.map((item) => {
					//循环遍历是否是单关
					if (item.isSingle == '1') {
						//如果是单关并且让球不为空的清空下，看不让球有没有被选择，选择了就不是单关
						if (item.notLetOddsList.length > 0) {
							item.letOddsList.map(lets => {
								if (lets.active) {
									count = this.selectItem.length;
									//用来标记是否是单关，传到下单也有用来判断是否有那个单关的下注选项
									flag = false;
									return;
								}
							})
						}
					} else {
						//不是单关判断让球，非让球是否有选择数据
						item.notLetOddsList.map(notLet => {
							if (notLet.active) {
								count = this.selectItem.length;
								//用来标记是否是单关，传到下单也有用来判断是否有那个单关的下注选项
								flag = false
								return;
							}
						})
						item.letOddsList.map(lets => {
							if (lets.active) {
								count = this.selectItem.length;
								//用来标记是否是单关，传到下单也有用来判断是否有那个单关的下注选项
								flag = false
								return;
							}
						})
					}
				})
				//如果不是单关,选中了让球或者非让球就需要校验场数，比赛是最低二场
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
				//每场相乘不能大于16
				let rideCount = 1;
				this.selectItem.some((map, idx) => {
					selectCount += map.choiceCount
					let a = 0;
					map.letOddsList.some(b => {
						if (b.active) {
							a++;
							return true;
						}
					})
					map.notLetOddsList.some(b => {
						if (b.active) {
							a++;
							return true;
						}
					})
					map.goalOddsList.some(b => {
						if (b.active) {
							a++;
							return true;
						}
					})
					map.halfWholeOddsList.some(b => {
						if (b.active) {
							a++;
							return true;
						}
					})
					map.scoreOddsList.some(b => {
						if (b.active) {
							a++;
							return true;
						}
					})
					rideCount *= a;
				})
				if (selectCount > 20) {
					uni.showToast({
						title: '总投注选项数不能超过20个',
						icon: 'none'
					});
					return;
				}
				if (rideCount > 16) {
					uni.showToast({
						title: '每场选择玩法数连乘不能超过16个',
						icon: 'none'
					});
					return;
				}
				uni.navigateTo({
					url: "pages/football/footballConfirm?obj=" + encodeURIComponent(JSON.stringify(this
						.selectItem)) + "&flag=" + encodeURIComponent(flag)
				});
			},
			// 获取竞彩足球数据
			fecthSoccerData() {
				uni.showLoading();
				footballWinEvenLoseList().then(res => {
					this.beidanList = res.voList
					setTimeout(function() {
						uni.hideLoading();
					}, 50);
				})
			},
			// 不同玩法对应投注
			changeSelectBall(item) {
				this.fecthSoccerData()
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
				this.football = item;
				this.listIdx = listIdx
				this.childIdx = childIdx
			},
		},
		onLoad() {
			this.fecthSoccerData()
		}
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

			/*混合过关*/
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
							height: 36.66667vmin;
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
									// width: 15vmin;
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
											color: #303133;
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
														margin-bottom: .66667vmin;
													}

													span:last-child {
														color: #666;
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
							height: 27.33333vmin;
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
								margin-top: 20px;

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
									color: #333333;
									border-bottom: none;

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
							height: 27.33333vmin;
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
								margin-top: 20px;

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
						// font-size: 3.46667vmin;
						font-size: 13px;
						color: #303133;
						display: flex;
						align-items: center;
						margin-bottom: 2.3vmin;

						/*左*/
						.hunhe_left {
							span {
								width: 9.9vmin;
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
								width: 27.8vmin;
								border-right: 1px solid #e6e6e6;
								border-bottom: 1px solid #e6e6e6;
								display: flex;
								align-items: center;
								justify-content: center;

								b:last-child {
									color: #999;
								}

								b:first-child {
									margin-right: 7vmin;
									font-weight: 500;
									margin-left: 5px;
								}
							}
						}
					}

					/*总进球*/
					.morePlay_zongjinqiu {
						margin-bottom: 2.3vmin;
						/*height: 18.13333vmin;*/
						width: 100%;
						// font-size: 3.46667vmin;
						font-size: 13px;
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
									font-weight: normal;

									em {
										font-style: normal;
									}
								}

								b:last-child {
									color: #999;
									font-weight: 500;
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
						font-size: 13px;
						color: #303133;
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
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}

								b:last-child {
									color: #999;
									font-weight: 500;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}

							.treW {
								width: 37vmin;
							}
						}
					}

					/*比分-主胜*/
					.morePlay_bifen_zhusheng {
						margin-bottom: 2.13333vmin;
						/*height: 18.13333vmin;*/
						width: 100%;
						// font-size: 3.46667vmin;
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
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}

								b:last-child {
									color: #999;
									font-weight: 500;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}

							.treW {
								width: 37vmin;
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
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}

								b:last-child {
									color: #999;
									font-weight: 500;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}

							.treW {
								width: 37vmin;
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
									margin-bottom: -0.4vmin;
									font-weight: normal;
								}

								b:last-child {
									color: #999;
									font-weight: 500;
								}
							}

							.doubleW {
								width: 12.3vmin*2;
							}

							.treW {
								width: 37vmin;
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