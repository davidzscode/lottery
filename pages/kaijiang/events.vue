<template>
	<view>
		
		<view class="status_bar"></view>
		<!-- 开奖按钮的关联选项卡 -->
		<!-- <nav-bottom :current="1"></nav-bottom> -->
		
		<view>
			<u-subsection style="height: 33px;" inactiveColor="#FFF" activeColor="#8d3d87" bgColor="#8d3d87" fontSize="14px" :list="list" :current="curNow" @change="sectionBall"></u-subsection>
		</view>
		
		<u-tabs v-if="isFootball" style="background-color: #8d3d87; border-top: 1px solid #FFFFFF80;" inactiveStyle="color:#FFFFFF80;"  activeStyle="color:#FFF;" :scrollable="false" lineWidth="45" lineColor="#FFF" :list="tabList"
			@click="selectBtn">
		</u-tabs>
		<view v-if="football_curNow!==2" class="week" style="background-color: aquamarine;">
			<uni-datetime-picker  :value="todayDate"  type="date" @change="changeDate" :clear-icon="false"/>
		</view>
		<!--  -->
		
		<!-- 足球 -->
		<view  v-if="isFootball" >
			<view class="mach-date" >
				<uni-data-select v-if="football_curNow==2"
					  v-model="value"
				      :localdata="range"
				      @change="changeIssue"
					  placeholder="选择期号"
				></uni-data-select>
				
				<!-- 胜负彩显示期号 -->
				<view class="issue">	
				</view>
				
			</view>
			
			<!-- 竞彩数据 -->
				<view v-if="football_curNow==0" class="content">
					<view class="item"  v-for="(game,id) in footballList" :key="id" @click="details(id)">
						<view class="contenttop">
							<span class="title">{{game.number}}.{{game.match}}</span>
							<span v-if="game.matchStatus=='未开赛'" class="title">{{game.matchStatus}}</span>
							<span v-if="game.matchStatus=='完场'" class="title red">{{game.matchStatus}}</span>
							
							<span v-if="game.matchStatus=='未开赛'" class="time">{{game.openTime}}</span>
							<span v-if="game.matchStatus!='未开赛' && game.matchStatus!='完场'" class="title green" style="margin-left: 0.15rem;">{{game.matchStatus}}</span>
							<div v-if="game.liveUrl!=null && game.recordUrl==null &&  game.matchStatus!='未开赛'" class="matchPlaying">
								<span>正在直播</span>
								<i class="liveIcon"></i>
							</div>
							<div v-if="game.recordUrl!=null" class="matchPlaying">
								<span>回放</span>
								<i class="liveIcon"></i>
							</div>
						</view>
						<view class="contentbottom">
							<div class="box1">
								<p class="fl">
									<image class="imgLogo" src="@/static/images/football/zhu.png"></image>
									<span class="name">{{game.homeTeam}}</span>
								</p>
							</div>
							<div class="box2">
								<span v-if="game.matchStatus=='未开赛'"  class="score">VS</span>
								<span v-if="game.matchStatus=='完场'" class="score red">{{game.realScore}}</span>
								<span v-if="game.matchStatus!='未开赛' &&game.matchStatus!='完场'" class="score green">{{game.realScore}}</span>
							</div>
							<div class="box3">
								<p class="fr">
									<image class="imgLogo" src="@/static/images/football/ke.png"></image>
									<span class="name">{{game.visitingTeam}}</span>
								</p>
							</div>
						</view>
						
						<view v-if="game.matchStatus!='未开赛' && game.halfScore!='-' && game.halfScore!=''" class="red" style="text-align: center;">
							<span>上半场</span>{{game.halfScore}}
						</view>
						
						<div style="color: rgb(126, 126, 126);">
							竞彩sp
							<span style="display: inline-block; margin-left: 5px;">{{game.notLetOdds}}</span>
							<span style="float: right;">
								<span style="margin-right: 5px;">主({{game.letBall}})</span>
								{{game.letOdds}}
							</span>
						</div>
					</view>
				</view>
				
			<!-- 胜负彩数据 -->
				<view v-if="football_curNow==2" class="content">
					<view class="item"  v-for="(game,id) in footballList" :key="id" @click="details(id)">
						<view class="contenttop">
							<span class="title">{{game.number}}.{{game.match}}</span>
							<span v-if="game.matchStatus=='未开赛'" class="title">{{game.matchStatus}}</span>
							<span v-if="game.matchStatus=='完场'" class="title red">{{game.matchStatus}}</span>
							
							<span v-if="game.matchStatus=='未开赛'" class="time">{{game.openTime}}</span>
							<span v-if="game.matchStatus!='未开赛' && game.matchStatus!='完场'" class="title green" style="margin-left: 0.15rem;">{{game.matchStatus}}</span>
							<div v-if="game.liveUrl!=null && game.recordUrl==null &&  game.matchStatus!='未开赛'" class="matchPlaying">
								<span>正在直播</span>
								<i class="liveIcon"></i>
							</div>
							<div v-if="game.recordUrl!=null" class="matchPlaying">
								<span>回放</span>
								<i class="liveIcon"></i>
							</div>
						</view>
						<view class="contentbottom">
							<div class="box1">
								<p class="fl">
									<image class="imgLogo" src="@/static/images/football/zhu.png"></image>
									<span class="name">{{game.homeTeam}}</span>
								</p>
							</div>
							<div class="box2">
								<span v-if="game.matchStatus=='未开赛'"  class="score">VS</span>
								<span v-if="game.matchStatus=='完场'" class="score red">{{game.realScore}}</span>
								<span v-if="game.matchStatus!='未开赛' &&game.matchStatus!='完场'" class="score green">{{game.realScore}}</span>
							</div>
							<div class="box3">
								<p class="fr">
									<image class="imgLogo" src="@/static/images/football/ke.png"></image>
									<span class="name">{{game.visitingTeam}}</span>
								</p>
							</div>
						</view>
						
						<view v-if="game.matchStatus!='未开赛' && game.halfScore!='-' && game.halfScore!=''" class="red" style="text-align: center;">
							<span>上半场</span>{{game.halfScore}}
						</view>
						
					</view>
				</view>
				
			<!-- 北单数据 -->
				<view v-if="football_curNow==1" class="content">
					<view class="item"  v-for="(game,id) in footballList" :key="id" @click="details(id)">
						<view class="contenttop">
							<span class="title">{{game.number}}.{{game.match}}</span>
							<span v-if="game.matchStatus=='未开赛'" class="title">{{game.matchStatus}}</span>
							<span v-if="game.matchStatus=='完场'" class="title red">{{game.matchStatus}}</span>
							
							<span v-if="game.matchStatus=='未开赛'" class="time">{{game.openTime}}</span>
							<span v-if="game.matchStatus!='未开赛' && game.matchStatus!='完场'" class="title green" style="margin-left: 0.15rem;">{{game.matchStatus}}</span>
							<div v-if="game.liveUrl!=null && game.recordUrl==null &&  game.matchStatus!='未开赛'" class="matchPlaying">
								<span>正在直播</span>
								<i class="liveIcon"></i>
							</div>
							<div v-if="game.recordUrl!=null" class="matchPlaying">
								<span>回放</span>
								<i class="liveIcon"></i>
							</div>
						</view>
						<view class="contentbottom">
							<div class="box1">
								<p class="fl">
									<image class="imgLogo" src="@/static/images/football/zhu.png"></image>
									<span class="name">{{game.homeTeam}}</span>
								</p>
							</div>
							<div class="box2">
								<span v-if="game.matchStatus=='未开赛'"  class="score">VS</span>
								<span v-if="game.matchStatus=='完场'" class="score red">{{game.realScore}}</span>
								<span v-if="game.matchStatus!='未开赛' &&game.matchStatus!='完场'" class="score green">{{game.realScore}}</span>
							</div>
							<div class="box3">
								<p class="fr">
									<image class="imgLogo" src="@/static/images/football/ke.png"></image>
									<span class="name">{{game.visitingTeam}}</span>
								</p>
							</div>
						</view>
						
						<view v-if="game.matchStatus!='未开赛' && game.halfScore!='-' && game.halfScore!=''" class="red" style="text-align: center;">
							<span>上半场</span>{{game.halfScore}}
						</view>
						
					</view>
				</view>
		</view>
		
		<!-- 篮球 -->
		<view v-if="!isFootball" class="">
			<view class="content">
				<view class="item"  v-for="(game,id) in basketballList" :key="id" @click="detailsBasketball(id)">
					<view class="contenttop">
						<span class="title">{{game.number}}.{{game.match}}</span>
						<span v-if="game.matchStatus=='未开赛'" class="title">{{game.matchStatus}}</span>
						<span v-if="game.matchStatus=='完场'" class="title red">{{game.matchStatus}}</span>
						
						<span v-if="game.matchStatus=='未开赛'" class="time">{{game.openTime}}</span>
						<span v-if="game.matchStatus!='未开赛' && game.matchStatus!='完场'" class="title green" style="margin-left: 0.15rem;">{{game.matchStatus}}</span>
						<div v-if="game.liveUrl!=null && game.recordUrl==null &&  game.matchStatus!='未开赛'" class="matchPlaying">
							<span>正在直播</span>
							<i class="liveIcon"></i>
						</div>
						<div v-if="game.recordUrl!=null" class="matchPlaying">
							<span>回放</span>
							<i class="liveIcon"></i>
						</div>
					</view>
					<view class="contentbottom">
						<div class="box1">
							<p class="fl">
								<image class="imgLogo" src="@/static/images/football/zhu.png"></image>
								<span class="name">{{game.homeTeam}}</span>
							</p>
						</div>
						<div class="box2">
							<span v-if="game.matchStatus=='未开赛'"  class="score">VS</span>
							<span v-if="game.matchStatus=='完场'" class="score red">{{game.realScore}}</span>
							<span v-if="game.matchStatus!='未开赛' &&game.matchStatus!='完场'" class="score green">{{game.realScore}}</span>
						</div>
						<div class="box3">
							<p class="fr">
								<image class="imgLogo" src="@/static/images/football/ke.png"></image>
								<span class="name">{{game.visitingTeam}}</span>
							</p>
						</div>
					</view>
					
					<view v-if="game.matchStatus!='未开赛' && game.halfScore!='-' && game.halfScore!=''" class="red" style="text-align: center;">
						<span>上半场</span>{{game.halfScore}}
					</view>
					
					<div style="color: rgb(126, 126, 126);">
						竞彩sp
						<span style="display: inline-block; margin-left: 5px;">{{game.notLetOdds}}</span>
						<span style="float: right;">
							<span style="margin-right: 5px;">主({{game.letBall}})</span>
							{{game.letOdds}}
						</span>
					</div>
				</view>
			</view>
			
		</view>

			<view v-if="footballList.length==0" class="nodata">
				<!-- <image  src="@/static/images/football/nodata.png" draggable="false"></image> -->
				<span>暂无比赛信息</span>
			</view>

	</view>
	
	
</template>

<script>
	import {
		footballWinEvenLoseList,
		eventList
	} from '@/api/football.js'
	import {
		getIssues,
		winBurdenEventList
	} from '@/api/winburden.js'
	import{
		beidanEventList
	} from '@/api/beidan.js'
	import{
		basketballEvents
	} from '@/api/basketball.js'
	

	export default {
		
		data() {
			return {
				todayDate: new Date().toISOString().slice(0, 10),
				selectDate: new Date().toISOString().slice(0, 10),
				issue: '',
				kaijiangUrl: '',
				list: ['足球', '篮球'],
				curNow: 0,//list的下标
				tabList: [{
					name: '竞彩',
				}, {
					name: '北单',
				}, {
					name: '足彩'
				}],
				football_curNow: 0,//tablist的下标
				footballList: [],
				basketballList: [],
				value: 0,
				range: [],
				isFootball: true,
			}
		},
		onLoad(options) {
			this.init()
	    },
		onReady() {
		},
		onShow() {
		},
		onTabItemTap(e) {
		    // tab 点击时执行，此处直接接收单击事件
		    this.init();
		},
		onPullDownRefresh() {
			this.init()
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 1000);
		},
		methods: {
			sectionBall(curr) {
				if(curr==0){
					this.isFootball=true;
					this.getFootballEvents({'selectDate': this.selectDate})
				}else if(curr==1){
					this.isFootball=false;
					// basketballEvents({'selectDate': this.selectDate})
					this.getBasketballEvents({'selectDate': this.selectDate})
				}
				this.curNow = curr;
				// this.todayDate = new Date().toISOString().slice(0, 10);
				console.log(curr, this.todayDate);
				if(this.curNow == 0){	// 足球数据
					uni.showLoading();
					getIssues().then(res => {
						console.log(res.voList);
						this.range = res.voList;
						if(this.range.length>0){
							this.issue = this.range[0]['text'];
						}
					})
					this.getFootballEvents({'selectDate': this.todayDate})
				}else{	// 篮球数据
					
				}
			},
			getBasketballEvents(data){
				basketballEvents(data).then(res => {
					if(res.voList.length>0){
						this.basketballList = res.voList[0].basketballMatchList;
					}else{
						this.basketballList = [];
					}
					setTimeout(function() {
						uni.hideLoading();
					}, 50);
				})
			},
			init(){
				uni.showLoading();
				this.todayDate = new Date().toISOString().slice(0, 10);
				getIssues().then(res => {
					console.log(res.voList);
					this.range = res.voList;
					if(this.range.length>0){
						this.issue = this.range[0]['text'];
					}
				})
				this.getFootballEvents({'selectDate': this.todayDate})
			},
			selectBtn(curr){
				this.football_curNow=curr.index
				switch(curr.index){
					case 0:{
						console.log('竞彩');
						this.getFootballEvents({'selectDate': this.todayDate})
						break
					}
					case 1:{
						console.log('北单');
						this.getBeidanEvents({'selectDate': this.todayDate})
						break
					}
					case 2:{
						console.log('胜负彩');
						this.getWinBurdenEvents();
						break
					}
				}
			},
			changeIssue(e) {
				console.log('=================================');
				this.issue = this.range[e]['text']
				console.log(this.issue);
				console.log('=================================');
			    this.getWinBurdenEvents();
			},
			changeDate(e) {
				this.selectDate = e;
				if(this.isFootball){
					this.getFootballEvents({'selectDate': this.selectDate})
				}else{
					// basketballEvents({'selectDate': this.selectDate})
					this.getBasketballEvents({'selectDate': this.selectDate})
				}
			},
			// 获取竞彩，北单，胜负彩
			getFootballEvents(data){
				eventList(data).then(res => {
					console.log(res.voList[0]);
					if(res.voList.length>0){
						this.footballList = res.voList[0].footballMatchList;
					}else{
						this.footballList = [];
					}
					setTimeout(function() {
						uni.hideLoading();
					}, 50);
				})
			},
			getWinBurdenEvents(){
				let data = {'issue': this.issue}
				winBurdenEventList(data).then(res =>{
					if(res.voList.length>0){
						this.footballList = res.voList[0].winBurdenMatchList;
					}else{
						this.footballList = [];
					}
					console.log(this.footballList);
					setTimeout(function() {
						uni.hideLoading();
					}, 50);
				})
			},
			getBeidanEvents(data){
				beidanEventList(data).then(res =>{
					if(res.voList.length>0){
						this.footballList = res.voList[0].beiDanMatchList;
					}else{
						this.footballList = [];
					}
					console.log(this.footballList);
					setTimeout(function() {
						uni.hideLoading();
					}, 50);
				})
			},
			details(id) {
				const detailData = {
					number: this.footballList[id].number, 
					matchStatus: this.footballList[id].matchStatus, 
					match: this.footballList[id].match, 
					openTime: this.footballList[id].openTime,
					homeTeam: this.footballList[id].homeTeam,
					visitingTeam: this.footballList[id].visitingTeam,
					detailUrl: this.footballList[id].detailUrl.analysisUrl,
					realScore: this.footballList[id].realScore,
					liveUrl: this.footballList[id].liveUrl,
					recordUrl: this.footballList[id].recordUrl,
					cartoonUrl: this.footballList[id].cartoonUrl,
				};
				// console.log(JSON.stringify(detailData));
				// console.log(encodeURIComponent(JSON.stringify(detailData)));
				uni.navigateTo({
					url: "/pages/kaijiang/eventDetail?id=" + id + '&data=' + encodeURIComponent(JSON.stringify(detailData)),
				})
			},
			detailsBasketball(id) {
				const detailData = {
					number: this.basketballList[id].number, 
					matchStatus: this.basketballList[id].matchStatus, 
					match: this.basketballList[id].match, 
					openTime: this.basketballList[id].openTime,
					homeTeam: this.basketballList[id].homeTeam,
					visitingTeam: this.basketballList[id].visitingTeam,
					detailUrl: this.basketballList[id].detailUrl.analysisUrl,
					realScore: this.basketballList[id].realScore,
					liveUrl: this.basketballList[id].liveUrl,
					recordUrl: this.basketballList[id].recordUrl,
					cartoonUrl: this.basketballList[id].cartoonUrl,
				};
				// console.log(JSON.stringify(detailData));
				// console.log(encodeURIComponent(JSON.stringify(detailData)));
				uni.navigateTo({
					url: "/pages/kaijiang/eventDetailBasketball?id=" + id + '&data=' + encodeURIComponent(JSON.stringify(detailData)),
				})
			},
		},
	}
</script>从 

<style lang="scss">
	
		.content {
			user-select: none;
			color: #303133;
			font-size: 14px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			display: block;
			box-sizing: border-box;
			padding-bottom: 100px;
		}
		
		.item {
			user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    display: block;
			    box-sizing: border-box;
			    overflow: hidden;
			    background-color: #fff;
			    border-bottom: 10px solid #fafafa;
			    padding: 15px;
			    position: relative;
		}
		
		.contenttop {
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    display: block;
			    box-sizing: border-box;
		}
		
		.title {
			    user-select: none;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    background-color: #f8f8f8;
			    color: #7e7e7e;
			    border-radius: 8px;
			    padding: 5px;
			    display: inline-block;
		}
		
		.status {
			    user-select: none;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    margin-left: 10px;
			    margin-right: 10px;
			    color: #7e7e7e;
		}
		
		.time {
			    user-select: none;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    color: #7e7e7e;
		}
		
		.contentbottom {
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    -webkit-tap-highlight-color: transparent;
			    box-sizing: border-box;
			    display: flex;
			    margin: 5px;
			    justify-content: space-between;
			    align-items: center;
		}
		
		.box1 {
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    width: 40%;
		}
		
		.box2 {
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    width: 20%;
			    text-align: center;
		}
		
		.box3 {
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    width: 40%;
		}
		
		.fl {
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    float: left;
			    display: flex;
			    align-items: center;
		}
		
		.imgLogo{
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    overflow: hidden;
			    position: relative;
			    display: inline-block;
			    width: 28px;
			    height: 28px;
			    margin-right: 5px;
		}
		
		.name{
			    user-select: none;
			    color: #303133;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    font-size: 16px;
			    height: 20px;
		}
		
		.score{
			    user-select: none;
			    text-align: center;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    font-weight: 700;
			    font-size: 20px;
			    color: #7e7e7e;
		}
		
		.fr{
			    user-select: none;
			    color: #303133;
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    float: right;
			    display: flex;
			    align-items: center;
		}
		
		.liveIcon{
			    font-size: 14px;
			    color: #2eb270;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAPFBMVEUAAAAtsnAsr3Avs3Ets3Aus3Aus3Autm8ts3Ausm8us3Atsm8us3AutnAusnAus3EusnAusnAusnAusnBNYyypAAAAE3RSTlMAvz+pZbPSF3JYKZJ8C8dL54Px3WQFRAAAApZJREFUeNrt3IuOmzAQheGzGJuAw3Xe/127aZuGEhQFVu7OTM/3AKBfAmNsySAiIiIiIiIiIiKir2g6rFi7/B/jILLEDoXUSWTKKC/IT1OLm2KXR2kXuStSEuS3jMIGKVkS1lcvqhEpeK8oDxlFdVKupI+yElHWsikp1SFXlBXlL3WpDhlRViWbkkIdM0q7bkuKdAQ8s1DyXof+knc7tJc073YoL2k+vqsDuGxLjHbslBjt2Ckx2rFTYrTjueRypmP4/g6g3pYY7dgpMdqxU2K0Y6fEaMdOidGO8yWdso7nkut7HUlbB9BuS4x27JQY7dgpMdqxU6Kwo++qNzyVVC9sO6p3dD2+oLkmUWOoG5x0nUSV6YpToqiTcMIiCk047ENUCjjoIkpdcEiv7D1/mHocUYlalY8n6+izlUWtbP0bchc9DL43HwxRhiHaMEQbhmjDEG0Yog1DtGGINgzRhiHa/OOQFHKUG9shocKnMYmI6ZCAX5ogYjkkNbibTYdkPNSWQ1qsVMluSIW1PjoJAbKXENReQtAlJyFA9BKC7CUEo5cQNMlJCBC9hODiJQSVlxAgeQlB9hKC1ksIWi8hiF5Cai8h8BLSeQkZvYR4edlHJ8Nv7+SDODqZomQnk8bkYxrfLT7+R2onv7rBx+JDn3wsB42Lj3Wt2cmSafCxiN0lH9sK9eJjf2Q2u/U2Yi3Y3QzNeKgGw9vTU4+7drG8zy716vUwHSIzbrogIrZDJOWxnhf5ZDzkgSEMYchrDGEIQ15jCEMY8hpDGMKQ1xjCEJ8hQdQK/+fZQbWoVeOITtSqcMggSg04phWlWgAexq3ZyfmGESfMok7AKaOyA9ziiLOqnJQc2DiluQIREREREREREREREX2nHwgu5g6F/4nAAAAAAElFTkSuQmCC);
			    background-size: 100% 100%;
			    width: 20px;
			    height: 20px;
			    display: inline-block;
			    position: absolute;
		}
		
		.matchPlaying{
			    font-size: 14px;
			    margin: 0;
			    -webkit-tap-highlight-color: transparent;
			    position: absolute;
			    right: 30px;
			    top: 10px;
			    color: #2eb270;
		}
		
		.green{
			        text-align: center;
			        margin: 0;
			        -webkit-tap-highlight-color: transparent;
			        color: #2eb270!important;
		}
		
		.red{
			    user-select: none;
			    margin: 0;
			    color: #f80202!important;
		}
		
		uni-web-view.uni-webview--fullscreen {
			margin-top: 3rpx;
			margin-bottom: 146rpx;
		}
		.status_bar { 
		    height: var(--status-bar-height); width: 100%; 
			background-color: #9A3B86;
		}
		.date-listitem{
			color: #303133;
			white-space: nowrap;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			box-sizing: border-box;
			display: inline-block;
			width: 25%;
			text-align: center;
			font-size: 14px;
		}
		.date-item{
			white-space: nowrap;
			text-align: center;
			font-size: 14px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			box-sizing: border-box;
			height: 66px;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			color: #7e7e7e;
		}
		
	.nodata{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    text-align: center;
	    position: absolute;
	    top: 45%;
	    width: 100%;
	    height: 143px;
	}
</style>
