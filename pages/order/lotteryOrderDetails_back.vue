<template>
	<!-- 购彩订单详情列表 -->
	<view class="box">
		<!-- 		<cmd-nav-bar @iconOne="backClick" iconOne="chevron-left" title="订单详情" font-color="#fff"
			background-color="#9A3B86">
		</cmd-nav-bar> -->
		<hx-navbar :config="{title: '订单详情', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}" />

		<view class="info">
			<view style="display: flex;justify-content: space-around;align-items: center;padding-top: 40px">
				<view>
					<p clsaa="data">{{lotteryOrder.winPrice|formatWinPrice}}</p>
					<p class="font">中奖金额</p>
				</view>
				<view>
					<p clsaa="data">{{lotteryOrder.state|formatState}}</p>
					<p class="font">订单状态</p>
				</view>
				<view>
					<p clsaa="data">{{lotteryOrder.price}}元</p>
					<p class="font">投注金额</p>
				</view>
			</view>
			<view style="padding-top: 15px;"
				v-if="lotteryOrder.type=='0'||lotteryOrder.type=='1'||lotteryOrder.type=='2'">
				<u-button size="normal" shape="circle" customStyle="color:#000;width:92%;height:35px" color="#fff">
					预测奖金：<span style="color:#9A3B86">￥{{lotteryOrder.forecast}}</span>
				</u-button>
			</view>
		</view>
		<view class="body">
			<uni-card is-shadow v-if="lotteryOrder.type=='3'">
				<view>
					<span class="title">{{lotteryOrder.ballName}}</span>
					<p style="display: flex;justify-content: flex-end;align-items: center;">
						<u-tag :text="lotteryOrder.notes+'注'" type="error"></u-tag>
						<u-tag :text="lotteryOrder.times +'倍'" style="margin-left: 10px;"></u-tag>
					</p>
				</view>
				<uni-table stripe emptyText="暂无更多数据">
					<!-- 表头行 -->
					<uni-tr>
						<uni-th width="50" align="center">期号</uni-th>
						<uni-th width="50" align="center">投注内容</uni-th>
						<uni-th width="50" align="center">投注方式</uni-th>
						<uni-th width="50" align="center">赛果</uni-th>
					</uni-tr>
					<!-- 表格数据行 -->
					<uni-tr v-for="(record,index) in lotteryOrder.recordList" :key="index">
						<uni-td width="50" align="center">{{record.stageNumber}}</uni-td>
						<uni-td width="50" align="center">
							{{record.hundred}} <span v-if="record.hundred!=null">|</span> {{record.ten}}
							<span v-if="record.ten!=null">|</span>
							<span v-if="record.mode=='1'||record.mode=='2'" v-for="(c,index) in record.individual">
								{{c.num}}
								<span style="color: #9A3B86;" v-if="c.isGallbladder">[胆]</span>
								<span v-if="index<record.individual.length-1">,</span>
							</span>
							<span v-if="record.mode!='1'&&record.mode!='2'">
								{{record.individual}}
							</span>
						</uni-td>
						<uni-td width="50" align="center">{{record.mode|formatMode}}</uni-td>
						<uni-td width="50" align="center">{{record.reward}}</uni-td>
					</uni-tr>
				</uni-table>

			</uni-card>

			<uni-card is-shadow v-if="lotteryOrder.type=='4'">
				<view>
					<span class="title">{{lotteryOrder.ballName}}</span>
					<p style="display: flex;justify-content: flex-end;align-items: center;">
						<u-tag :text="lotteryOrder.notes+'注'" type="error"></u-tag>
						<u-tag :text="lotteryOrder.times +'倍'" style="margin-left: 10px;"></u-tag>
					</p>
				</view>
				<uni-table stripe emptyText="暂无更多数据">
					<!-- 表头行 -->
					<uni-tr>
						<uni-th width="15" align="center">期号</uni-th>
						<uni-th width="65" align="center">投注内容</uni-th>
						<uni-th width="65" align="center">赛果</uni-th>
					</uni-tr>
					<!-- 表格数据行 -->
					<uni-tr v-for="(record,index) in lotteryOrder.recordList" :key="index">
						<uni-td align="center">{{record.stageNumber}}</uni-td>
						<uni-td align="center">{{record.myriad}} | {{record.kilo}} | {{record.hundred}} |
							{{record.ten}} |
							{{record.individual}}
						</uni-td>
						<uni-td align="center">{{record.reward}}</uni-td>
					</uni-tr>
				</uni-table>
			</uni-card>

			<uni-card is-shadow v-if="lotteryOrder.type=='5'">
				<view>
					<span class="title">{{lotteryOrder.ballName}}</span>
					<p style="display: flex;justify-content: flex-end;align-items: center;">
						<u-tag :text="lotteryOrder.notes+'注'" type="error"></u-tag>
						<u-tag :text="lotteryOrder.times +'倍'" style="margin-left: 10px;"></u-tag>
					</p>
				</view>
				<uni-table stripe emptyText="暂无更多数据">
					<!-- 表头行 -->
					<uni-tr>
						<uni-th width="15" align="center">期号</uni-th>
						<uni-th width="65" align="center">投注内容</uni-th>
						<uni-th width="65" align="center">赛果</uni-th>
					</uni-tr>
					<!-- 表格数据行 -->
					<uni-tr v-for="(record,index) in lotteryOrder.recordList" :key="index">
						<uni-td align="center">{{record.stageNumber}}</uni-td>
						<uni-td align="center">{{record.hundredMyriad}} |{{record.tenMyriad}} |{{record.myriad}} |
							{{record.kilo}} | {{record.hundred}} |
							{{record.ten}} |
							{{record.individual}}
						</uni-td>
						<uni-td align="center">{{record.reward}}</uni-td>
					</uni-tr>
				</uni-table>
			</uni-card>

			<uni-card is-shadow v-if="lotteryOrder.type=='8'">
				<view>
					<span class="title">{{lotteryOrder.ballName}}</span>
					<p style="display: flex;justify-content: flex-end;align-items: center;">
						<u-tag :text="lotteryOrder.notes+'注'" type="error"></u-tag>
						<u-tag :text="lotteryOrder.times +'倍'" style="margin-left: 10px;"></u-tag>
					</p>
				</view>
				<uni-table stripe emptyText="暂无更多数据">
					<!-- 表头行 -->
					<uni-tr>
						<uni-th width="15" align="center">期号</uni-th>
						<uni-th width="65" align="center">投注内容</uni-th>
						<uni-th width="65" align="center">赛果</uni-th>
					</uni-tr>
					<!-- 表格数据行 -->
					<uni-tr v-for="(record,index) in lotteryOrder.recordList" :key="index">
						<uni-td align="center">{{record.stageNumber}}</uni-td>
						<uni-td align="center">
							<span v-for="(c,index) in record.ten">
								{{c.num}}
								<span style="color: #9A3B86;" v-if="c.isGallbladder">[胆]</span>
								<span v-if="index<record.ten.length-1">,</span>
							</span>
							<span style="padding: 0px 5px">|</span>
							<span v-for="(c,index) in record.individual">
								{{c.num}}
								<span style="color: #9A3B86;" v-if="c.isGallbladder">[胆]</span>
								<span v-if="index<record.individual.length-1">,</span>
							</span>
						</uni-td>
						<uni-td align="center">{{record.reward}}</uni-td>
					</uni-tr>
				</uni-table>
			</uni-card>

			<uni-card is-shadow v-if="lotteryOrder.type=='0'">

				<view class="nav-cont">
					<view class="fa">
						<view class="des-title">
							<view class="des-title-item">
								{{lotteryOrder.ballName}}
								<text>(2串1,3串1,4串1,5串1,6串1,7串1,8串1,247注,2倍)</text>
							</view>

						</view>
					</view>

					<view class="mytable">
						<view class="zj-list">
							<view class="zj-list-cont jc">
								<!-- 头部 -->
								<view class="zj-list-header list-item">
									<view class="left">
										场次
									</view>
									<view class="right">
										主队VS客队
									</view>
									<view class="sg">
										赛果
									</view>
								</view>
								<!-- 每一项 list 开始 -->	
								<view class="list-item" v-for="(record,index) in lotteryOrder.ballInfoList" :key="index">
									<view class="left">
										{{record.number}}
									</view>
									<view class="right">
										<view class="right-cont">
											<view class="right-top">
												<view class="f1">
													{{record.homeTeam}}
													<span class="rangqiu"
														:class="{rangqiuBlue:record.letBall < 0}">({{record.letBall}})</span>
													<!-- <text class="text-t">(-1)</text> -->
													VS {{record.visitingTeam}}
												</view>
												<view class="sg"></view>
											</view>
												
											<view class="right-bottom">
												
												<!-- <view class="f1" style="padding: 0px; display: flex; flex-direction: column;">
													<view class="f1" style="padding: 0px;">
														<view class="f1" style="border-bottom: 0.5px solid rgb(220, 225, 229); display: flex; justify-content: space-around;">
															<view class="code-item">
																<view class="labels">
																	让胜
																</view>
																<view class="code-v">
																	1.88
																</view>
															</view>
														</view>
														<view class="sg">
															<view class="code-item">
																<view class="code-v">
																	1.88
																</view>
															</view>
														</view>
													</view>
													
												</view> -->
												
												
												<uni-td align="center">
													<span :style="notLet.describe==record.award[0]?'color:#9A3B86':''"
														v-for="(notLet,index) in record.content.notLetOddsList" v-if="notLet.active">
														{{notLet.describe}}({{notLet.odds}})<br>
													</span>
													<span :style="record.halfFullCourt!=undefined&&lets.describe==(Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)>Number(record.halfFullCourt.split(',')[1].split(':')[1])?'胜'
													:Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)<Number(record.halfFullCourt.split(',')[1].split(':')[1])?'负'
													:'平')?'color:#9A3B86':''" v-for="(lets,index) in record.content.letOddsList" v-if="lets.active">
														让{{lets.describe}}({{lets.odds}})<br>
													</span>
													<span :style="goal.describe==record.award[2]?'color:#9A3B86':''"
														v-for="(goal,index) in record.content.goalOddsList" v-if="goal.active">
														{{goal.describe}}({{goal.odds}})<br>
													</span>
													<span :style="half.describe==record.award[3]?'color:#9A3B86':''"
														v-for="(half,index) in record.content.halfWholeOddsList" v-if="half.active">
														{{half.describe}}({{half.odds}})<br>
													</span>
													<span :style="score.describe==record.award[4]?'color:#9A3B86':''"
														v-for="(score,index) in record.content.scoreOddsList" v-if="score.active">
														{{score.describe}}({{score.odds}})<br>
													</span>
												</uni-td>
												<uni-td align="center">
													<span v-if="record.halfFullCourt!=undefined">
														全({{record.halfFullCourt.split(',')[1]}})半{{record.halfFullCourt.split(',')[0]}}
													</span>
												</uni-td>
												
											</view>
										</view>
									</view>
								</view>
								<!-- 每一项 list 结束 -->
							
							
							</view>

						</view>
					</view>


				</view>
			</uni-card>

			<uni-card is-shadow v-if="lotteryOrder.type=='1'">
				<view v-if="lotteryOrder.documentaryFlag&&!lotteryOrder.openFlag&&!lotteryOrder.isEnd"
					style="display: flex;flex-direction: column;justify-content: center;align-items: center;color: grey;font-size: 20px;">
					<u-icon name="lock" size="50" color="grey"></u-icon>
					<text style="margin-top: 10px;">开赛后可见</text>
					<text style="margin: 20px 0px;">截止时间 {{lotteryOrder.deadline|formatDate(that)}}</text>
				</view>
				<view v-else>
					<view>
						<span class="title">{{lotteryOrder.ballName}}</span>
						<p style="display: flex;justify-content: flex-end;align-items: center;">
							<uni-row>
								<uni-col :span="lotteryOrder.pssTypeList.length<=2?12:24">
									<u-tag :text="lotteryOrder.notes+'注'" type="error"></u-tag>
									<u-tag :text="lotteryOrder.times +'倍'" style="margin-left: 10px;"></u-tag>
								</uni-col>
								<uni-col :span="6" :key="index" v-for="(item,index) in lotteryOrder.pssTypeList">
									<u-tag v-if="item==1" :borderColor="index|mapTagColor" :bgColor="index|mapTagColor"
										text="单关"
										:style="lotteryOrder.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
									</u-tag>
									<u-tag v-else :borderColor="index|mapTagColor" :bgColor="index|mapTagColor"
										:text="item+'串一'"
										:style="lotteryOrder.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
									</u-tag>
								</uni-col>
							</uni-row>
						</p>
					</view>
					<uni-table stripe emptyText="暂无更多数据">
						<!-- 表头行 -->
						<uni-tr>
							<uni-th width="10" align="center">场次</uni-th>
							<uni-th width="100" align="center">客队/主队</uni-th>
							<uni-th width="75" align="center">投注内容</uni-th>
							<uni-th width="40" align="center">赛果</uni-th>
						</uni-tr>
						<!-- 表格数据行 -->
						<uni-tr v-for="(record,index) in lotteryOrder.ballInfoList" :key="index">
							<uni-td align="center">{{record.number}}</uni-td>
							<uni-td align="center">{{record.visitingTeam}}
								<span class="rangqiu"
									:class="{rangqiuBlue:record.letBall < 0}">({{record.letBall}})</span>
								<br> VS <br>{{record.homeTeam}}
							</uni-td>
							<uni-td align="center">
								<span :style="winNegative.describe==record.award[0]?'color:#9A3B86':''"
									v-for="(winNegative,index) in record.content.winNegativeOddsList"
									v-if="winNegative.active">
									{{winNegative.describe}}({{winNegative.odds}})<br>
								</span>
								<span :style="record.halfFullCourt!=undefined&&cedePoints.describe==(Number(record.halfFullCourt.split(':')[1])+Number(record.letBall)>Number(record.halfFullCourt.split(':')[0])?'主胜'
								:'主负')?'color:#9A3B86':''" v-for="(cedePoints,index) in record.content.cedePointsOddsList"
									v-if="cedePoints.active">
									让{{cedePoints.describe}}({{cedePoints.odds}})<br>
								</span>
								<span :style="record.halfFullCourt!=undefined&&size.describe==(Number(record.halfFullCourt.split(':')[1])+Number(record.halfFullCourt.split(':')[0])>Number(size.score)?'大'
								:'小')?'color:#9A3B86':''" v-for="(size,index) in record.content.sizeOddsList" v-if="size.active">
									{{size.describe}}<span
										style="color: #1afa29;">[{{size.score}}]</span>({{size.odds}})<br>
								</span>
								<span :style="difference.describe==record.award[2]?'color:#9A3B86':''"
									v-for="(difference,index) in record.content.differenceOddsList"
									v-if="difference.active">
									{{difference.describe}}({{difference.odds}})<br>
								</span>
							</uni-td>
							<uni-td align="center">
								<span v-if="record.halfFullCourt!=undefined">
									{{record.halfFullCourt}}
								</span>
							</uni-td>
						</uni-tr>
					</uni-table>
				</view>
			</uni-card>

			<uni-card is-shadow v-if="lotteryOrder.type=='2'">
				<view>
					<span class="title">{{lotteryOrder.ballName}}</span>
					<p style="display: flex;justify-content: flex-end;align-items: center;">
						<uni-row>
							<uni-col :span="lotteryOrder.pssTypeList.length<=2?12:24">
								<u-tag :text="lotteryOrder.notes+'注'" type="error"></u-tag>
								<u-tag :text="lotteryOrder.times +'倍'" style="margin-left: 10px;"></u-tag>
							</uni-col>
							<uni-col :span="6" :key="index" v-for="(item,index) in lotteryOrder.pssTypeList">
								<u-tag v-if="item==1" :borderColor="index|mapTagColor" :bgColor="index|mapTagColor"
									text="单关"
									:style="lotteryOrder.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
								</u-tag>
								<u-tag v-else :borderColor="index|mapTagColor" :bgColor="index|mapTagColor"
									:text="item+'串一'"
									:style="lotteryOrder.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
								</u-tag>
							</uni-col>
						</uni-row>
					</p>
				</view>
				<uni-table stripe emptyText="暂无更多数据">
					<!-- 表头行 -->
					<uni-tr>
						<uni-th width="10" align="center">场次</uni-th>
						<uni-th width="100" align="center">主队/客队</uni-th>
						<uni-th width="65" align="center">投注内容</uni-th>
						<uni-th width="50" align="center">赛果<br>(全/半)</uni-th>
					</uni-tr>
					<!-- 表格数据行 -->
					<uni-tr v-for="(record,index) in lotteryOrder.ballInfoList" :key="index">
						<uni-td align="center">{{record.number}}</uni-td>
						<uni-td align="center">{{record.homeTeam}}
							<span class="rangqiu" :class="{rangqiuBlue:record.letBall < 0}">({{record.letBall}})</span>
							<br> VS <br>{{record.visitingTeam}}
						</uni-td>
						<uni-td align="center">
							<span :style="record.halfFullCourt!=undefined&&lets.describe==(Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)>Number(record.halfFullCourt.split(',')[1].split(':')[1])?'胜'
								:Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)<Number(record.halfFullCourt.split(',')[1].split(':')[1])?'负'
								:'平')?'color:#9A3B86':''" v-for="(lets,index) in record.content.letOddsList" v-if="lets.active">
								<!-- 判断如果中了就显示开奖后的赔率，没中的就显示开始下注的赔率 -->
								<span v-if="record.halfFullCourt!=undefined&&lets.describe==(Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)>Number(record.halfFullCourt.split(',')[1].split(':')[1])?'胜'
								:Number(record.halfFullCourt.split(',')[1].split(':')[0])+Number(record.letBall)<Number(record.halfFullCourt.split(',')[1].split(':')[1])?'负'
								:'平')">让{{lets.describe}}({{record.bonusOdds[0]}})</span>
								<span v-else>让{{lets.describe}}({{lets.odds}})</span><br>
							</span>
							<span :style="goal.describe==record.award[1]?'color:#9A3B86':''"
								v-for="(goal,index) in record.content.goalOddsList" v-if="goal.active">
								<!-- 判断如果中了就显示开奖后的赔率，没中的就显示开始下注的赔率 -->
								<span v-if="goal.describe==record.award[1]">
									{{goal.describe}}({{record.bonusOdds[1]}})
								</span>
								<span v-else>
									{{goal.describe}}({{goal.odds}})
								</span>
								<br>
							</span>
							<span :style="score.describe==record.award[2]?'color:#9A3B86':''"
								v-for="(score,index) in record.content.scoreOddsList" v-if="score.active">
								<!-- 判断如果中了就显示开奖后的赔率，没中的就显示开始下注的赔率 -->
								<span v-if="score.describe==record.award[2]">
									{{score.describe}}({{record.bonusOdds[2]}})
								</span>
								<span v-else>
									{{score.describe}}({{score.odds}})
								</span>
								<br>
							</span>
							<span :style="oddEven.describe==record.award[3]?'color:#9A3B86':''"
								v-for="(oddEven,index) in record.content.oddEvenOdds" v-if="oddEven.active">
								<!-- 判断如果中了就显示开奖后的赔率，没中的就显示开始下注的赔率 -->
								<span v-if="oddEven.describe==record.award[3]">
									{{oddEven.describe}}({{record.bonusOdds[3]}})
								</span>
								<span v-else>
									{{oddEven.describe}}({{oddEven.odds}})
								</span>
								<br>
							</span>
							<span :style="half.describe==record.award[4]?'color:#9A3B86':''"
								v-for="(half,index) in record.content.halfWholeOddsList" v-if="half.active">
								<!-- 判断如果中了就显示开奖后的赔率，没中的就显示开始下注的赔率 -->
								<span v-if="half.describe==record.award[4]">
									{{half.describe}}({{record.bonusOdds[4]}})
								</span>
								<span v-else>
									{{half.describe}}({{half.odds}})
								</span>
								<br>
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

			<uni-card is-shadow v-if="lotteryOrder.type=='6'||lotteryOrder.type=='7'">
				<view>
					<span class="title">{{lotteryOrder.ballName}}</span>
					<p style="display: flex;justify-content: flex-end;align-items: center;">
						<uni-row>
							<uni-col :span="lotteryOrder.pssTypeList.length<=2?12:24">
								<u-tag :text="lotteryOrder.notes+'注'" type="error"></u-tag>
								<u-tag :text="lotteryOrder.times +'倍'" style="margin-left: 10px;"></u-tag>
							</uni-col>
							<uni-col :span="6" :key="index" v-for="(item,index) in lotteryOrder.pssTypeList">
								<u-tag v-if="item==1" :borderColor="index|mapTagColor" :bgColor="index|mapTagColor"
									text="单关"
									:style="lotteryOrder.pssTypeList.length==1?'margin-left: 35px;':'margin-left: 10px;'">
								</u-tag>
							</uni-col>
						</uni-row>
					</p>
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
					<uni-tr v-for="(record,index) in lotteryOrder.ballInfoList" :key="index">
						<uni-td align="center">
							<div class="paiban">
								<div>
									<view class="demo-layout bg-purple">{{record.number}}</view>
								</div>
								<div class="content" v-if="record.content.isGallbladder">
									<p>胆</p>
								</div>
							</div>
						</uni-td>
						<uni-td align="center">{{record.homeTeam}}
							<br> VS <br>{{record.visitingTeam}}
						</uni-td>
						<uni-td align="center">
							<!-- notLet.describe==record.award[0] 如果选择的和中奖的结果一样标记为红色 -->
							<span :style="notLet.describe==record.award[0]?'color:#9A3B86':''"
								v-for="(notLet,index) in record.content.notLetOddsList" v-if="notLet.active">
								{{notLet.describe}}({{notLet.odds}})<br>
							</span>
						</uni-td>
						<uni-td align="center">
							<span v-if="record.award!=undefined">
								{{record.award[0]}}
							</span>
						</uni-td>
					</uni-tr>
				</uni-table>
			</uni-card>

			<uni-card class="phone"
				v-if="lotteryOrder.schemeDetails!=null&&lotteryOrder.schemeDetails!=undefined&&lotteryOrder.schemeDetails!=''&&lotteryOrder.type!=3&&lotteryOrder.type!=4&&lotteryOrder.type!=5&&lotteryOrder.type!=8">
				<p>
					<view class="title">方案详情</view>
					<uni-table border stripe emptyText="暂无更多数据" class="make">
						<!-- 表头行 -->
						<uni-tr>
							<uni-th width="40px" align="center">过关</uni-th>
							<uni-th align="center" width="100px">单注组合</uni-th>
							<uni-th align="center" width="30px">倍数</uni-th>
							<uni-th align="center" width="80px">预测奖金</uni-th>
						</uni-tr>
						<!-- 表格数据行 -->
						<tbody v-for="(item,index) in lotteryOrder.schemeDetails" :key="index">
							<uni-tr>
								<uni-td align="center">{{item.type}}</uni-td>
								<uni-td align="center">
									<view @click="open(index,item)"
										style="display: flex;justify-content: center;align-items: center;flex-direction: column;font-size: 12px;">
										<span v-for="(ball,idx) in item.ballCombinationList" :key="idx">
											{{ball.homeTeam}}|{{ball.content}}<br>
										</span>
										<u-icon name="arrow-down"></u-icon>
									</view>
								</uni-td>
								<uni-td align="center">
									{{item.notes}}
								</uni-td>
								<uni-td align="center"
									v-if="lotteryOrder.type!=6 && lotteryOrder.type!=7">{{item.forecastBonus}}</uni-td>
								<uni-td align="center" v-else>
									浮动奖金<br>
									以官网<br>
									出奖为准
								</uni-td>
							</uni-tr>

							<uni-tr v-if="item.isShow" style="background: #FAF9DE">
								<uni-th width="10px" align="center">场次</uni-th>
								<uni-th align="center" width="85px">主队</uni-th>
								<uni-th align="center" width="85px">客队</uni-th>
								<uni-th align="center" width="80px">投注内容</uni-th>
							</uni-tr>
							<uni-tr v-for="(data,idx) in item.ballCombinationList" :key="idx" v-if="item.isShow"
								style="background: #FAF9DE">
								<uni-td align="center">{{data.number}}</uni-td>
								<uni-td align="center">{{data.homeTeam}}</uni-td>
								<uni-td align="center">{{data.visitingTeam}}</uni-td>
								<uni-td align="center">{{data.content}}</uni-td>
							</uni-tr>
						</tbody>
					</uni-table>
				</p>
			</uni-card>

			<uni-card class="phone"
				v-if="lotteryOrder.schemeDetails!=null&&lotteryOrder.schemeDetails!=undefined&&lotteryOrder.schemeDetails!=''&&lotteryOrder.type==3||lotteryOrder.type==4||lotteryOrder.type==5||lotteryOrder.type==8">
				<p>
					<view class="title">方案详情</view>
					<uni-table border stripe emptyText="暂无更多数据" class="make">
						<!-- 表头行 -->
						<uni-tr>
							<uni-th width="20px" align="center">期号</uni-th>
							<uni-th align="center" width="30px" v-if="lotteryOrder.type==3">过关</uni-th>
							<uni-th align="center" width="80px">中奖组合</uni-th>
							<uni-th align="center" width="30px">倍数</uni-th>
							<uni-th align="center" width="120px">预测奖金</uni-th>
						</uni-tr>
						<!-- 表格数据行 -->
						<tbody v-for="(item,index) in lotteryOrder.schemeDetails" :key="index">
							<uni-tr>
								<uni-td align="center">{{item.stageNumber}}</uni-td>
								<uni-td align="center" v-if="lotteryOrder.type==3">
									{{item.mode|formatMode}}
								</uni-td>
								<uni-td align="center">
									{{item.content}}
								</uni-td>
								<uni-td align="center">
									{{lotteryOrder.times}}
								</uni-td>
								<uni-td align="center"
									v-if="lotteryOrder.type==3||lotteryOrder.type==4">{{item.forecastBonus}}</uni-td>
								<uni-td align="center" v-else>
									浮动奖金以官网出奖为准
								</uni-td>
							</uni-tr>
						</tbody>
					</uni-table>
				</p>
			</uni-card>

			<uni-card class="phone" v-if="lotteryOrder.bill!=undefined">
				<p>
					<view class="title">彩票照片</view>
					<u-image v-if="lotteryOrder.bill!=undefined" @click="imgListPreview(lotteryOrder.bill)"
						:src="lotteryOrder.bill"
						style="display: flex;justify-content: center;align-items: center;margin-top: 20px;"></u-image>
				</p>
			</uni-card>

			<uni-card is-shadow class="tip phone">
				<view class="uni-body">订单编号：{{lotteryOrder.orderId}}</view>
				<view class="uni-body">创建时间：{{lotteryOrder.createTime|formatDate(that)}}</view>
				<view class="uni-body">出票时间：{{lotteryOrder.ticketingTime|formatDate(that)}}</view>
				<view class="uni-body" v-if="lotteryOrder.type=='2'">提示：赔率以最后出奖的赔率为准</view>
				<view class="uni-body" v-else>提示：赔率以实际出票的赔率为准</view>
				<!-- <view class="uni-body">提示：中奖金额以实际出奖金额的为准</view> -->
			</uni-card>


		</view>

	</view>
</template>

<script>
	import {
		getLotteryOrderPage,
		getLotteryOrderById
	} from '@/api/lotteryOrder.js'
	export default {
		data() {
			return {
				lotteryOrder: {},
				that: this,
			}
		},
		onLoad(option) {
			this.init(option.id);
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
			formatMode(mode) {
				if (mode == "0") {
					return "直选";
				} else if (mode == "1") {
					return "组三";
				} else if (mode == "2") {
					return "组六";
				} else if (mode == "3") {
					return "直选和值";
				} else if (mode == "4") {
					return "组选和值";
				} else if (mode == "5") {
					return "组三复式";
				}
			},
			//格式化日期
			formatDate(data, that) {
				if (data != null && data != undefined) {
					return that.globalUtil.dateTimeFormat(data)
				}
			},
			formatWinPrice(data) {
				if (data == null || data == undefined || data == 0) {
					return "暂无";
				}
				return data;
			},
			//格式化状态
			formatState(data) {
				if (data == -1) {
					return "待接单";
				} else if (data == 0) {
					return "待出票";
				} else if (data == 1) {
					return "待开奖";
				} else if (data == 2) {
					return "未中奖";
				} else if (data == 3) {
					return "待派奖";
				} else if (data == 4) {
					return "已派奖";
				} else if (data == 5) {
					return "拒绝";
				} else if (data == 6) {
					return "已退票";
				}
			}
		},
		methods: {
			open(index, item) {
				this.$set(this.lotteryOrder.schemeDetails[index], "isShow", !item.isShow)
			},
			imgListPreview(url) {
				var urlList = []
				urlList.push(url) //push中的参数为 :src="item.img_url" 中的图片地址
				uni.previewImage({
					indicator: "number",
					loop: true,
					urls: urlList
				})
			},
			//返回事件处理
			backClick() {
				//如果是从下单页面过来的直接跳转首页。
				let isPay = uni.getStorageSync('isPay');
				if (isPay) {
					uni.reLaunch({
						url: "/pages/home/home"
					})
				} else {
					uni.navigateBack();
				}
			},
			//初始化事件
			init(id) {
				uni.showLoading();
				getLotteryOrderById(id).then(res => {
					this.lotteryOrder = res;
					if (res.schemeDetails != null && res.schemeDetails != undefined && res.schemeDetails != "") {
						this.lotteryOrder.schemeDetails = JSON.parse(this.lotteryOrder.schemeDetails)
						//过滤掉数字彩
						if (this.lotteryOrder.type != 3 && this.lotteryOrder.type != 4 && this.lotteryOrder.type !=
							5 && this.lotteryOrder.type != 8) {
							this.lotteryOrder.schemeDetails.map((item, index) => {
								item.forecastBonus = parseFloat((item.forecastBonus / item.notes) * this
									.lotteryOrder.times).toFixed(2)
								item.notes = this.lotteryOrder.times
							})
						}
					}
					//将字符串转对象
					if (this.lotteryOrder.ballInfoList != null) {
						this.lotteryOrder.ballInfoList.map((item, idx) => {
							this.$set(this.lotteryOrder.ballInfoList[idx], "content", JSON.parse(item
								.content))
							//將比賽结果转换成数组，并返回
							if (item.award != null) {
								this.$set(this.lotteryOrder.ballInfoList[idx], "award", item.award.split(
									','))
							} else {
								//考虑比赛结果还没有出的话设置一个默认值，防止报错
								var moren = ["", "", "", "", ""]
								this.$set(this.lotteryOrder.ballInfoList[idx], "award", moren)
							}
							if (item.bonusOdds != null) {
								this.$set(this.lotteryOrder.ballInfoList[idx], "bonusOdds", item.bonusOdds
									.split(','))
							} else {
								//考虑比赛结果还没有出的话设置一个默认值，防止报错
								var moren = ["", "", "", "", ""]
								this.$set(this.lotteryOrder.ballInfoList[idx], "bonusOdds", moren)
							}
						})
					}
					//处理超过一定高度采用滚动条
					this.$nextTick(() => {
						if (document.querySelector(".make").clientHeight > 500) {
							document.querySelector(".make").setAttribute("class", "scheme")
						}
					})
					//处理排列3的组三和组六的字符串转数组
					if (this.lotteryOrder.type == "3") {
						this.lotteryOrder.recordList.map(item => {
							if (item.mode == "1" || item.mode == "2") {
								item.individual = JSON.parse(item.individual)
							}
						})
					}
					//处理大乐透
					if (this.lotteryOrder.type == "8") {
						this.lotteryOrder.recordList.map(item => {
							item.ten = JSON.parse(item.ten)
							item.individual = JSON.parse(item.individual)
						})
					}
				})
				setTimeout(function() {
					uni.hideLoading();
				}, 500);
			}
		}
	}
</script>

<style scoped lang="scss">
	.paiban {
		display: flex;
		align-items: center;
		justify-content: space-between;
		flex-direction: row;
		width: 100%;
	}

	.content {
		width: 20px;
		height: 20px;
		background-color: #9A3B86;
		border-radius: 50%;

		p {
			width: 20px;
			height: 20px;
			color: #fff;
			text-align: center;
			line-height: 20px;
			font-size: 12px;
		}
	}

	.rangqiuBlue {
		color: #2d8cf0 !important;
	}

	.scheme {
		margin-top: 30px;
		overflow: auto;
		height: 1000rpx;
		overflow-x: hidden;
	}

	/deep/.uni-col {
		padding: 0px 0px 5px 0px !important;
		display: flex !important;
		justify-content: center !important;
		align-items: center !important;
	}

	.rangqiu {
		color: #9A3B86;
	}

	.box {
		padding-bottom: 0px;
		background: #f7f9fa;

		.uni-body {
			color: #909399;
		}

		.info {
			height: 320rpx;
			margin-bottom: 11px;
			background: radial-gradient(circle, rgba(63, 94, 251, 1) 0%, rgba(255, 63, 67, 1) 100%);
			color: #ffffff;
			display: flex;
			flex-direction: column;

			view {
				text-align: center;

				.font {
					font-size: 28rpx;
				}

				p {
					padding-bottom: 14rpx;
					font-size: 38rpx;
				}
			}
		}

		.body {
			.uni-card {
				margin: 0px !important;
				padding: 0px !important;
			}

			.title {
				font-size: 28rpx;
			}

			.tip {
				margin-top: 20rpx;

				view {
					margin-top: 20rpx;
				}
			}

			.phone {
				margin-top: 20rpx !important;
				height: 100%;
			}
		}
	}

	.nav-cont {
		background-color: #fff;
	}

	.fa {
		border-bottom: 0.5px solid #f1f1f1;
		// padding: 16px 16px 0 0px;
	}

	.des-title {
		font-size: 14px;
		display: flex;
		justify-content: space-between;
		color: #a6a6a6;
	}

	.zj-list {
		padding-top: 16px;
		box-sizing: border-box;
	}

	.nav-cont .zj-list .zj-list-cont {
		border: 1px solid #dce1e5;
	}
	
	
	.nav-cont .zj-list .zj-list-cont .list-item{
		display: flex;
		font-weight: 700;
		padding: 0;
		vertical-align: center;
		text-align: center;
		color: #4a4a4a;
		border-bottom: 0.5px solid #dce1e5;
		min-height: 38px;

	}
	
	.nav-cont .zj-list .jc .zj-list-header{
		background-color: #ebf0f5;
		height: 38px;
		line-height: 38px;
	}
	
	.nav-cont .zj-list .jc .list-item .left{
		display: flex;
		align-items: center;
		justify-content: center;
		border-right: 0.5px solid #dce1e5;
		width: 88px;
	}
	
	.nav-cont .zj-list .zj-list-cont .list-item .right{
		flex: 1;
		display: flex;
		align-items: center;
		justify-content: center;
		word-break: break-all;
	}
	
	.nav-cont .zj-list .zj-list-cont .list-item .sg{
		width: 88px;
		text-align: center;
		border-left: 0.5px solid #dce1e5;
	}
	
	.nav-cont .zj-list .jc .list-item .right .right-cont{
		width: 100%;
	}
	.nav-cont .zj-list .jc .list-item .right .right-cont .right-top{
		display: flex;
		border-bottom: 0.5px solid #dce1e5;
		height: 38px;
		flex: 1;
		width: 100%;
		align-items: center;
	}
	
	.nav-cont .zj-list .jc .list-item .right .right-cont .right-bottom .f1{
		flex: 1;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
		padding-top: 5px;
	}
	
	.text-t{
		color: green;
	}
	
	.code-item{
		display: flex;
		border: 0.5px solid #a7a7a7;
		font-size: 13px;
		font-weight: 700;
		height: 22px;
		min-width: 88px;
		align-items: center;
		border-radius: 4px;
		overflow: hidden;
		margin-bottom: 5px;
	}
	
	.code-item .labels{
		background-color: #3a3a3a;
		color: #fff;
		padding: 5px 11px;
	}
	.code-item .code-v{
		text-align: center;
		flex: 1;
		padding: 0 2px;
	}
	
	.nav-cont .zj-list .jc .list-item .right .right-cont .right-bottom{
		display: flex;
	}
	
	.nav-cont .zj-list .jc .list-item .right .right-cont .right-bottom .sg{
		width: 88px;
		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;
		border-left: 0.5px solid #dce1e5;
	}
	
	.nav-cont .zj-list .jc .list-item .right .right-cont .right-top .f1{
		flex: 1;
	}
</style>