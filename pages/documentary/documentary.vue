<template>
	<view class="box">
		<!-- <view class="status_bar"></view> -->
		<!-- <cmd-nav-bar @iconOne="iconOneBtn" iconOne="search" title="跟单大厅" font-color="#fff" background-color="#9A3B86"> -->
		<!-- </cmd-nav-bar> -->
		<!-- <nav-bottom :current="2"></nav-bottom> -->
<!-- 		<hx-navbar
			:config="{
				title: '跟单大厅', 
				backgroundColor: [1,'#8D3D87'], 
				color: '#ffffff',
			}"
		/> -->
		<hx-navbar 
			:config="{
				title: '跟单大厅', 
				backgroundColor: [1,'#8D3D87'], 
				color: '#ffffff',
				rightButton:[{
					txt: '规则',
					key: 'help',
					icon: '&#xe64c;',
					position: 'left'
				}]
			}"
			@clickBtn="onClickBtn"
		/>
		
		<view class="content">
			<scroll-view>
				<view style="white-space: nowrap;">
<!-- 					<uni-card is-shadow class="desc">
						<view class="topic">
							<image class="img" src="@/static/images/documentary/tuijian.png"></image>
							<span class="title">推荐专家</span>
						</view>
						<view class="topic" v-for="item in rankingData.expertUserList" :key="item.id"
							@tap="queryInfo(item.id)">
							<view style="display: flex; flex-direction: column; align-items: center;">
								<view>
									<u-avatar size="31" :src="item.avatar"></u-avatar>
								</view>
								<span style="font-size: 13px; white-space: nowrap;">
									{{item.nickname}}</span>
							</view>
						</view>
					</uni-card> -->
					<uni-card is-shadow class="desc">
						<view class="topic">
							<image class="img" src="@/static/images/documentary/lianhong.png"></image>
							<span class="title">连红榜</span>
						</view>
						<view class="topic" v-for="item in rankingData.lianHongUserList" :key="item.id"
							@tap="queryInfo(item.id)">
							<view style="display: flex; flex-direction: column; align-items: center;">
								<view>
									<u-avatar size="31" :src="item.avatar"></u-avatar>
								</view>
								<span style="font-size: 13px; white-space: nowrap;">{{item.nickname}}</span>
							</view>
						</view>
					</uni-card>
					<uni-card is-shadow class="desc">
						<view class="topic">
							<image class="img" src="@/static/images/documentary/shenglv.png"></image>
							<span class="title">胜率榜</span>
						</view>
						<view class="topic" v-for="item in rankingData.winUserList" :key="item.id"
							@tap="queryInfo(item.id)">
							<view style="display: flex; flex-direction: column; align-items: center;">
								<view>
									<u-avatar size="31" :src="item.avatar"></u-avatar>
								</view>
								<span style="font-size: 13px; white-space: nowrap;">{{item.nickname}}</span>
							</view>
						</view>
					</uni-card>
					<uni-card is-shadow class="desc">
						<view class="topic">
							<image class="img" src="@/static/images/documentary/yingli.png"></image>
							<span class="title">盈利榜</span>
						</view>
						<view class="topic" v-for="item in rankingData.profitUserList" :key="item.id"
							@tap="queryInfo(item.id)">
							<view style="display: flex; flex-direction: column; align-items: center;">
								<view>
									<u-avatar size="31" :src="item.avatar"></u-avatar>
								</view>
								<span style="font-size: 13px; white-space: nowrap;">{{item.nickname}}</span>
							</view>
						</view>
					</uni-card>
				</view>
				<view style="background: #fff;height: 50px;">
					<u-tabs :list="tabsList" lineColor="#9A3B86" :scrollable="false" lineWidth="50"
						:activeStyle="{color: '#9A3B86',fontWeight: 'bold',transform: 'scale(1.05)'}" @click="tabsBtn">
					</u-tabs>
				</view>
				<scroll-view>
					<u-empty :show="documentaryData.length<=0" icon="http://cdn.uviewui.com/uview/empty/data.png"
						mode="data" text="没有跟单数据"></u-empty>
					<view class="item-title" v-for="(item,index) in documentaryData" :key="index">
						<view style="padding: 0 10px;">
							<view style="display: flex; justify-content: space-between; align-items: center;">
								<view style="display: flex; align-items: center;">
									<view @tap="queryInfo(item.id)">
										<image class="img" :src="item.avatar"></image>
									</view>
									<view style="display: flex; flex-direction: column; margin-left: 10px;"
										@tap="documentaryInfoBtn(item.documentaryId,item.id)">
										<view class="userInfo">
											{{item.nickname}}
											<!-- <view class="statusOne">
												10连红
											</view>
											<view class="statusTwo">
												近10中10
											</view> -->
											<!-- <view style="display: flex; align-items: center; font-size: 11px;"> -->
												<view
													style="height: 22px; line-height: 20px; padding: 0px 5px; background-color: #9A3B86; text-align: center; border-radius: 7px 0px 0px 7px; color: white;margin-left: 10px;">
													<!-- {{item.lianHongCount}}连红 -->
													{{item.lianhong}}连红
													</view>
												<view
													style="color: #9A3B86; border: 1px solid red; border-radius: 0px 7px 7px 0px; height: 20px; line-height: 20px; text-align: center; padding: 0px 5px; ">
													{{item.count}}中{{item.winCount}}
												</view>
												
											<!-- </view> -->
										</view>
										<view style="color: grey;">
											预计回报{{item.remuneration}}倍</view>
									</view>
								</view>
								<view style="display: flex; flex-direction: column; align-items: center;"
									@tap="documentaryInfoBtn(item.documentaryId,item.id)">
									<view style="color: grey;">
										截止时间</view>
									<u-count-down :time="item.endTime" format="HH:mm:ss"></u-count-down>
								</view>
							</view>
							<view style="margin: 10px 0px;" @tap="documentaryInfoBtn(item.documentaryId,item.id)">
								{{item.describe}}
							</view>
							<view style="display: flex; align-items: center;"
								@tap="documentaryInfoBtn(item.documentaryId,item.id)">
								<view
									style="display: flex; align-items: center; background-color: rgb(249, 249, 249); padding: 0 10px; border-radius: 10px; width: 80%; justify-content: space-around;">
									<view
										style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
										<!-- <view>
											<image style="width: 15px;height: 15px;" :src="item.url">
											</image>
										</view> -->
										<view>{{item.name}}</view>
										<view>{{item.scheme}}</view>
									</view>
									<view style="border-right: 1px solid rgb(214, 214, 214); height: 26px; width: 1px;">
									</view>
									<view style="display: flex; flex-direction: column; align-items: center;">
										<view>
											自购</view>
										<view style="color: #9A3B86;">{{item.price}}</view>
									</view>
									<view style="border-right: 1px solid rgb(214, 214, 214); height: 26px; width: 1px;">
									</view>
									<view style="display: flex; flex-direction: column; align-items: center;">
										<view>
											人气</view>
										<view style="color: #9A3B86;">{{item.quantity}}</view>
									</view>
								</view>
								<view
									style="width: 20%; text-align: center; background-image: linear-gradient(to top, #8D3D87 0%, #8D3D87 100%); padding: 8px 10px; margin: 0px 10px; color: white; border-radius: 10px;">
									<text>
										<span>跟单</span>
									</text>
								</view>
							</view>
						</view>
					</view>
				</scroll-view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	import {
		ranking,
		queryDocumentaryByType,
	} from '@/api/documentary.js'
	export default {
		data() {
			return {
				tabsList: [{
					name: '人气跟单',
				}, {
					name: '跟单总额',
				}, {
					name: '我的关注',
				}],
				rankingData: {},
				documentaryData: [],
				type: 0
			}
		},
		methods: {
			documentaryInfoBtn(documentaryId, userId) {
				uni.navigateTo({
					url: "/pages/documentary/documentaryInfo?id=" + documentaryId + "&uid=" + userId
				})
			},
			iconOneBtn() {
				uni.navigateTo({
					url: "/pages/documentary/search"
				})
			},
			onClickBtn(data){
				uni.navigateTo({
					url: "/pages/documentary/documentaryhelp"
				})
			},
			init() {
				uni.showLoading();
				ranking().then(res => {
					this.rankingData = res;
				})
				queryDocumentaryByType(this.type).then(res => {
					this.documentaryData = res.voList;
					uni.hideLoading();
				})
			},
			tabsBtn(item) {
				this.type = item.index;
				this.init();
			},
			queryInfo(id) {
				uni.navigateTo({
					url: "/pages/documentary/homePage?uid=" + id
				})
			}
		},
		onLoad() {
			this.init();
		},
		onPullDownRefresh() {
			this.init();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
		},
		onTabItemTap(e) {
		    // tab 点击时执行，此处直接接收单击事件
		    this.init();
		},
	}
</script>

<style scoped lang="less">
	/deep/.u-count-down__text {
		color: #9A3B86;
	}

	.img {
		width: 35px;
		height: 35px;
		border-radius: 50%;
		
	}

	page {
		background: #FAFAFA;
		font-size: 15px;
		color: #333;
		font-family: Helvetica Neue, Helvetica, sans-serif;
	}

	.title {
		// color: rgb(255, 83, 89);
		color:#b480be;
		font-size: 11px;
	}

	/deep/.desc .uni-card__content {
		display: flex;
		flex-direction: row;
		align-items: center;
		padding: 10px 0px !important;
	}

	.topic {
		display: flex;
		align-items: center;
		flex-direction: column;
		justify-content: space-around;
		width: 20%;
	}

	/deep/.desc {
		border-radius: 10px;
		justify-content: space-around;
		margin: 5px 5px !important;
		padding: 0px !important;
	}

	.item-title {
		padding: 10px;
		background-color: white;
		margin-bottom: 10px;
	}
	
	.userInfo{
		display: flex;
		.statusOne{
			background-color: #f00702;
			color: #fff;
			padding: 4px;
			border-radius: 5px;
			font-size: 11px;
			margin-left: 5px;
		}
		.statusTwo{
			background-color: #fef0ee;
			color: #fb5b43;
			padding: 4px;
			border-radius: 5px;
			font-size: 11px;
			margin-left: 5px;
		}	
	}
</style>