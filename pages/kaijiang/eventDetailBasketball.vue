<template>
	<view>
		<!-- <cmd-nav-bar title="赛事详情" font-color="#fff" background-color="#8D3D87" back> -->
		<!-- </cmd-nav-bar> -->
		<!-- <view class="status_bar"></view> -->
		<hx-navbar :config="{title: '赛事详情', backgroundColor: [1,'#8D3D87'], color: '#ffffff', backPage: '/pages/kaijiang/events'}"/>
		
		<!-- <view v-if="!showVideo" class="myHeader"> -->
		<!-- 图片提醒 -->
		<view v-if="football.cartoonUrl == null && football.liveUrl == null" class="myHeader">
			<view class="header_con">
				<text style="color: white; font-size: 24px; white-space: nowrap;">
					<span>{{football.number}} {{football.match}}</span>
				</text>
			</view>
			<p class="timeTitle" style="padding-top: 10px;">{{football.openTime}}</p>
			
			<view class="ftMatchNewDetailBox">
				<view class="gameInformationDetail">
					<view class="gameBox">
						<view class="flex-1">
							<view>
								<image class="imgLogo"  src="@/static/images/football/zhu.png"></image>
							</view>
							<view>
								<text class="name">
									<span>{{football.homeTeam}}</span>
								</text>
							</view>
						</view>
						<view class="flex-1">
							<view style="margin: 12px;">
								<text class="status">{{football.matchStatus}}</text>
								<view class="playing"></view>	
							</view>
							<view>
								<text v-if="football.matchStatus=='未开赛'" class="score">VS</text>
								<text v-if="football.matchStatus!='未开赛' && football.matchStatus!='完场'" class="score">{{football.realScore}}</text>
								<text v-if="football.matchStatus=='完场'" class="score red">{{football.realScore}}</text>
								<!-- <text class="score">VS</text> -->
							</view>
						</view>
						<view class="flex-1">
							<view>
								<image class="imgLogo"  src="@/static/images/football/ke.png"></image>
							</view>
							<view>
								<text class="name">
									<span>{{football.visitingTeam}}</span>
								</text>
							</view>
						</view>
					</view>
				</view>
			</view>	
			<view v-if="false" class="playVideo">
				<view @tap="video" v-if="this.football.liveUrl!=null && this.football.recordUrl==null" class="d">
					<image style="width: 20px; height: 22px; margin: 10px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEoAAABKCAMAAAArDjJDAAAAP1BMVEUAAAD///////////////////////////////////////////////////////////////////////////////9Du/pqAAAAFHRSTlMA5tIKobtU82yEFUUcTHaxOWAqjd4Jh3UAAAGSSURBVFjD7ZbZjoQgEAC5BEEOj/7/b10bjRsd15HQO4nJ1FO/WJFYpGXU9FZVMXWrSCaopvHZJIAA7mZVAhIEYz0QwdgERGzn4xmzTe8BALNOq6pZTF4iwzw5eQ8BENdpr2KZsE3vmQXDOu1VkiEap/uq9kGq9TKWqfSZygxBax2mMpUKGuGbakeJauPfVGahTGUWDrWPznvv2jKVdR45/YIdqr61f7B2jZTWrh9WOz+rXV7Uzs9rn5HDi2psuJjGP1RROuRe7d4Aovr62i2sKFmbqPr9HKFetWEraj+oIN2qnYcOsZcqEPJQe5e5V7uCHU1F7Qr2WDoVSDqVI1MpsgNy+V5lbBtjbNW1iju2U6X8UOTliQp/nmj5xUmS6g7Gyus8bZ2PhcsrHFUjZIQmWKlagElDyUoFvnCyJry7WBMZeMAeNLFFVJkqtRlO8fvxoP8rclWgUDEyFYgML1NxkTFPqH2ySCpTNTbzrZ0gUVb9VgSqdKgdxE3MS+0KiMAzEZFPSgLHBWyAgp6hS0A1omM/jD6tW2UIFc0AAAAASUVORK5CYII="></image>
					<text style="line-height: 22px;">观看直播</text>
				</view>
				<view @tap="video" v-if="this.football.recordUrl!=null" class="d">
					<image style="width: 20px; height: 22px; margin: 10px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEoAAABKCAMAAAArDjJDAAAAP1BMVEUAAAD///////////////////////////////////////////////////////////////////////////////9Du/pqAAAAFHRSTlMA5tIKobtU82yEFUUcTHaxOWAqjd4Jh3UAAAGSSURBVFjD7ZbZjoQgEAC5BEEOj/7/b10bjRsd15HQO4nJ1FO/WJFYpGXU9FZVMXWrSCaopvHZJIAA7mZVAhIEYz0QwdgERGzn4xmzTe8BALNOq6pZTF4iwzw5eQ8BENdpr2KZsE3vmQXDOu1VkiEap/uq9kGq9TKWqfSZygxBax2mMpUKGuGbakeJauPfVGahTGUWDrWPznvv2jKVdR45/YIdqr61f7B2jZTWrh9WOz+rXV7Uzs9rn5HDi2psuJjGP1RROuRe7d4Aovr62i2sKFmbqPr9HKFetWEraj+oIN2qnYcOsZcqEPJQe5e5V7uCHU1F7Qr2WDoVSDqVI1MpsgNy+V5lbBtjbNW1iju2U6X8UOTliQp/nmj5xUmS6g7Gyus8bZ2PhcsrHFUjZIQmWKlagElDyUoFvnCyJry7WBMZeMAeNLFFVJkqtRlO8fvxoP8rclWgUDEyFYgML1NxkTFPqH2ySCpTNTbzrZ0gUVb9VgSqdKgdxE3MS+0KiMAzEZFPSgLHBWyAgp6hS0A1omM/jD6tW2UIFc0AAAAASUVORK5CYII="></image>
					<text style="line-height: 22px;">观看回放</text>
				</view>
			</view>
		</view>
		
		<view v-if="football.liveUrl != null" class="videoContent">
			<!-- <view class="matchPlayer"> -->
				<!-- <video class="matchPlayer" :src="this.football.liveUrl" controls></video> -->
				<video class="matchPlayer" :src="this.football.liveUrl"
					:autoplay="true"
					:header="{'Referer':referer}" controls>
				</video>
			<!-- </view> -->
			<view class="videoTips">
				<span style="color: rgb(153, 153, 153); margin-left: 15%;">如遇播放不佳，请及时联系管理员处理</span>
			</view>
		</view>
		
		<view v-if="football.cartoonUrl != null && football.liveUrl == null" class="videoContent">
			<iframe  style="width: 100%; height: 396rpx;" :src="football.cartoonUrl" frameborder="0"></iframe>
<!-- 		<view class="videoTips">
			<span style="color: rgb(153, 153, 153); margin-left: 15%;">如遇播放不佳，请及时联系管理员处理</span>
		</view> -->
		</view>
			
		<view class="jzsj-top" style="background: white;">
			<view class="data-type">
				<view class="li" style="font-size: 16px;">
					<view :class="[(currentId==0)?'current':'']" @click="changeCurrentId(0)">分析</view>
					<view :class="[(currentId==1)?'current':'']" @click="changeCurrentId(1)">指数</view>
					<!-- <view :class="[(currentId==2)?'current':'']" @click="changeCurrentId(2)">指数</view> -->
					<!-- <view :class="[(currentId==3)?'current':'']" @click="changeCurrentId(3)">概率</view> -->
					<!-- <view :class="[(currentId==4)?'current':'']" @click="changeCurrentId(4)">积分</view> -->
				</view>
			</view>
		</view>
		
		<view id="pagecontent" style="background-color: rgb(255, 255, 255); margin-top: 10px;">
			<iframe v-if="iframes[iframeId]!=''" class="contentiframe" :src="iframes[iframeId]" frameborder="0"></iframe>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: "巴空军 VS 吉达联合",
				tabList: [{
					name: '实况',
					
				}, {
					name: '分析',
				}, {
					name: '指数',
				},{
					name: '概率',
				},{
					name: '积分'
				}],
				currentId: 1,
				iframeId: 1,
				iframes: [
					// 'http://client2.310win.com/aspx/data/Analysis/Event/2399169.html',  //实况
					// 'http://client2.310win.com/aspx/data/Analysis/2404422.html', //分析
					// 'http://client2.310win.com/Trade/info/EventIndex.aspx?id=2404422', //指数
					// 'http://client2.310win.com/Trade/info/MatchProbability.aspx?sportType=1&schID=2404422', //概率
					// 'http://client2.310win.com/aspx/Standings.aspx?ID=2404422'  //积分
				],
				showVideo: false, 
				referer: 'https://www.yingyukeji.cn/',
			}
		},
		onLoad(option) {
			this.init(option.id, option.data);
		},
		
		computed:{
			classObject: function () {
			  return {
			    active: this.isActive,
			  }
			}
		},
		methods: {
			test(t){
				console.log(t);
			},
			init(id, data){
				data = decodeURIComponent(data);
				console.log(data);
				this.football = JSON.parse(data);
				// console.log(this.football.detailUrl);
				this.iframes = this.football.detailUrl;
				// console.log(this.football.detailUrl);
			},
			changeCurrentId(id){
				this.currentId = id;
				this.iframeId = id;
			},
			video(){
				this.showVideo=true;
			}
		}
	}
</script>

<style>
	.videoTips{
	    user-select: none;
	    color: #303133;
	    font-size: 16px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	}
	.matchPlayer{
	    user-select: none;
	    color: #303133;
	    font-size: 16px;
	    margin: 0;
	    width: 100%;
	    height: 233px;
	    display: inline-block;
	    line-height: 0;
	    overflow: hidden;
	    position: relative;
	}
	.videoContent{
	    user-select: none;
	    color: #303133;
	    font-size: 16px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    padding: 0 0 1px;
	}
	.d{
	    user-select: none;
	    color: #fff;
	    text-align: center;
	    font-size: 19px;
	    margin: 0;
	    box-sizing: border-box;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    border-radius: 4px;
	}
	.playVideo{
	    user-select: none;
	    color: #fff;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    text-align: center;
	    font-size: 19px;
	    padding-bottom: 11px;
	    z-index: 33;
	}
	.myHeader{
		display: block;
		box-sizing: border-box;
		padding: 10px;
		height: 213px;
		background: url(@/static/images/football/liveBg.png) no-repeat;
		background-size: 100% 100%;
		color: #fff;
	}
	
	.header_con{
		user-select: none;
		font-size: 14px;
		color: #fff;
		margin: 0;
		-webkit-tap-highlight-color: transparent;
		display: block;
		box-sizing: border-box;
		text-align: center;
		margin-top: 6px;
	}
	
	.timeTitle{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    text-align: center;
	    margin: 0;
	    -webkit-tap-highlight-color: transparent;
	    padding-top: 10px;
	}
	
	.ftMatchNewDetailBox{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    margin-top: 10px;
	}
	
	.gameInformationDetail{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	}
	
	.gameBox{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    margin: 0;
	    box-sizing: border-box;
	    display: flex;
	    justify-content: space-between;
	    align-items: center;
	    text-align: center;
	}
	
	.imgLogo{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    text-align: center;
	    margin: 0;
	    overflow: hidden;
	    position: relative;
	    display: inline-block;
	    width: 52px;
	    height: 52px;
	}
	
	.flex-1{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    text-align: center;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    width: 30%;
	}
	
	.status{
		user-select: none;
		font-size: 14px;
		text-align: center;
		margin: 0;
		box-sizing: border-box;
		background-color: #8362a8;
		color: #fff;
		border-radius: 17px;
		padding: 2px 10px;
	}
	
	.playing{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    text-align: center;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	}
	
	.score{
		user-select: none;
		color: #fff;
		text-align: center;
		margin: 0;
		box-sizing: border-box;
		font-size: 31px;
		font-weight: 700;
	}
	
	.name{
	    user-select: none;
	    font-size: 14px;
	    color: #fff;
	    text-align: center;
	    margin: 0;
	    box-sizing: border-box;
	}
	
	.jzsj-top{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    background: white;
	}
	
	.data-type{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	}
	
	.current{
	    user-select: none;
	    line-height: 41px;
	    font-size: 16px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    color: #8d3d87;
	    border-bottom: 2px solid #8d3d87;
	}
	
	.li{
	    user-select: none;
	    color: #303133;
	    margin: 0;
	    box-sizing: border-box;
	    background-color: #fff;
	    height: 41px;
	    line-height: 41px;
	    display: flex;
	    justify-content: space-around;
	    font-size: 16px;
	}
	
	.pagecontent{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    /* height: 677px; */
		height: 100%;
	    background-color: rgb(255, 255, 255);
	    margin-top: 10px;
	}
	
	.contentiframe{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    border: none;
	    width: 100%;
	    height: 100%;
	}
	
	#pagecontent{
		user-select: none;
		color: #303133;
		font-size: 14px;
		margin: 0;
		-webkit-tap-highlight-color: transparent;
		display: block;
		box-sizing: border-box;
		height: 688px;
		background-color: rgb(255, 255, 255);
		margin-top: 10px;
	}
	
	.no_data{
		color: #333;
		font-weight: normal;
		font-size: 14px;
		position: absolute;
		top: 222px;
		left: 155px;
	}

	.red{
		    user-select: none;
		    margin: 0;
		    color: #f80202!important;
	}
</style>