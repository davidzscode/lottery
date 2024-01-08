<template>
	<view style="background-color: #f5f5f5; height: 50rem;background-image: linear-gradient(#f2d7f2 0,#f5f5f5 110px);">
		<!-- <nav-bar :title="'首页'"></nav-bar> -->
		<view class="status_bar"></view>
		<!-- <nav-bottom :current="0"></nav-bottom> -->

		<!-- <view style="margin-top:0 auto; margin-left: 3%; padding-bottom: 10px; padding-top: 6%; text-align: center;"> -->
			<!-- <text style="font-size:18px; color: black;font-style: oblique;">
				欢迎加入
					<span style="color:#9370db">{{shop.name}}</span>
			</text> -->
		<!-- </view> -->
		
		<view class="title">
			<view class="title-top">
				<view class="shopName">
					欢迎加入<span style="color:#8D3D87">{{shop.name}}</span>
				</view>
				<view class="relation">联系我们</view>
			</view>
		</view>


		
		<!--  background-image: linear-gradient(#f2d7f2,#f5f5f5); -->
		
		<view class="main-bg">
			<!-- <view -->
				<!-- style="border: 2px solid rgb(199, 202, 204); background-color: #FF6266; text-align: center; width: 40%; margin: 1px auto; height: 30px; display: flex; flex-direction: column; align-items: center; justify-content: center;"> -->
				<!-- <text style="color: rgb(247, 210, 127); font-weight: bold;"> -->
					<!-- <span>线下店铺 实体出票</span> -->
				<!-- </text> -->
			<!-- </view> -->
			
			<!-- <view style="display: flex; align-items: flex-end; "> -->
			<!-- 	<view class="dian-name">
					<img :src="shop.logo" alt=""
						style="width: 50px; height: 50px; border: 1px solid white; border-radius: 7px;">
				</view> -->
					<!-- <view style="margin-left: 155px; margin-top: 0%;"> -->
						<!-- <view style="display: flex; margin-top: 20%; margin-left: 10%;"> -->
						
						<!-- 三个图标 -->
							<view style="margin-top: 31%; margin-left: 5%; display: inline;">
								<img :src="'./static/images/home/tel.png'" alt=""
									style="width: 30px; height: 30px;">
							</view>
							<view style="margin-top: 31%; margin-left: 2%; display: inline;">
								<img :src="'./static/images/home/wx.png'" alt=""
									style="width: 30px; height: 30px;">
							</view>
							<view class="share">
								<button>分享好友</button>
							</view>
							<!-- <view @tap="invitation" style="margin-top: 15%; margin-left: 2%; display: inline;">
								<img  :src="'./static/images/home/share.png'" alt=""
									style="width: 30px; height: 30px;">
							</view> -->
						
						
					<!-- 	<view style="margin-top: 20%; display: inline;">
							<text>
								<span>{{shop.name}}</span>
							</text>
						</view> -->
						<!-- </view> -->
						<!-- <view style="display: flex; margin-top: 45%;"> -->
							<!-- <view
								style="background-color: #FF777A; display: flex; height: 22px; line-height: 22px; border-radius: 45px; align-items: center; padding: 0px 7px;">
								<text style="color: white; font-size: 11px; margin-right: 2px;">
									<span>评分</span>
								</text>
								<u-rate active-color="rgb(246, 203, 86)" :minCount="5" value="5"></u-rate>
								<u-icon name="arrow-right" color="rgb(212, 233, 253)" size="14"></u-icon>
								<span></span>
								</text>
							</view> -->
							<!-- <view @click="complaint"
								style="background-color: #FF777A; margin-left: 10px; height: 22px; line-height: 22px; border-radius: 45px; padding: 0px 10px;">
								<text style="color: white;">
									<span>投诉</span>
								</text>
							</view> -->
						<!-- </view> -->
					<!-- </view> -->
			<!-- </view> -->
			<!-- <view
				<!-- style="position: absolute; left: 0px; right: 0px; bottom: 0px; background-color: #FF777A; height: 65px; margin: 0px 7px; border-radius: 10px 10px 0px 0px; display: flex; justify-content: space-around; align-items: center;">
<!-- 				<view style="display: flex; flex-direction: column; align-items: center;" @tap="callPhone(0)">
					<view
						style="background-color: white; width: 30px; height: 30px; border-radius: 30px; display: flex; justify-content: center; align-items: center;">
						<img src="@/static/images/home/phone.png" alt="" style="width: 20px; height: 20px;">
					</view>
					<view style="color: white; padding-top: 2px;">联系店主</view>
				</view> -->
			<!-- 	<view style="display: flex; flex-direction: column; align-items: center;" @click="show=true">
					<view
						style="background-color: white; width: 30px; height: 30px; border-radius: 30px; display: flex; justify-content: center; align-items: center;">
						<img src="@/static/images/home/wx.png" alt="" style="width: 20px; height: 20px;">
					</view>
					<view style="color: white; padding-top: 2px;">添加微信</view>
				</view>
				<view style="display: flex; flex-direction: column; align-items: center;" @tap="invitation">
					<view
						style="background-color: white; width: 30px; height: 30px; border-radius: 30px; display: flex; justify-content: center; align-items: center;">
						<img src="@/static/images/home/invite.png" alt="" style="width: 15px; height: 15px;">
					</view>
					<view style="color: white; padding-top: 2px;">邀请好友</view>
				</view> -->
			<!-- </view> -->
		</view>
		

		
		
		
<!-- 		<uni-notice-bar :speed="30" color="#fff" showIcon background-color="#9A3B86" scrollable single
			:text="noticeMsg">
		</uni-notice-bar> -->
		<!-- 轮播图 -->
<!-- 		<view>
			<u-swiper :list="list1" indicator indicatorMode="dot" height="200" duration="800" radius="0"></u-swiper>
		</view> -->
		<uni-card is-shadow class="card">
			<view style="display: flex;justify-content:space-between;align-items: center;">
				<view style="display: flex;align-items: center;justify-content: center;margin: 5px 10px;">
					<image src="@/static/images/home/ticai.png" style="width: 25px;height: 25px;"></image>
					<view style="color: rgb(51, 51, 51);">体育彩票</view>
				</view>
				<!-- 显示近期中奖人 -->
				<view class="noticeBar">  
					<swiper class="list" circular="true" vertical="true" autoplay="true" interval="3000"
						duration="1000">
						<swiper-item v-for="item in noticeList" :key="item.id" @click="handleInfo(item.id)">
							{{ item.msg }}
						</swiper-item>
					</swiper>
				</view>
					    <view class="custom-modal" :class="{ 'show-modal': isModalVisible }">
					      <view class="modal-content">
							  <!-- <image style="width: 100%; height: auto; display: block;" src="@/static/images/home/bobao.png"></image> -->
					           <!-- 图片容器 -->
					           <div class="image-container">
					             <!-- 使用 CSS 控制图片大小 -->
					             <img
					               src="@/static/images/home/bobao.png"
					               class="responsive-image"
					             />
					           </div>
							<!-- 显示的内容 -->
					        <p style="margin-top: 5%; font-size: 18px; ">{{notice.msg}}</p>
					        <!-- 关闭按钮 -->
					        <button style="font-weight: bold; margin-top: 10%; color: #FF9A0D; background-color: #fff; border: 1px solid #FF9A0D;" @click="closeCustomModal">我知道了</button>
					      </view>
					    </view>
					  </view>
			<u-grid :border="false" col="4">
				<u-grid-item v-for="(item,index)  in ballList" customStyle="padding-top: 5px; padding-bottom: 5px"
					:key="index" @click="ballBtn(item)">
					<image style="width: 50px; height: 50px;" :src="item.url"></image>
					<text style="color: rgb(51, 51, 51);" class="grid-text">{{item.name}}</text>
				</u-grid-item>
			</u-grid>
		</uni-card>
		<uni-card is-shadow class="card">
			<view>
				<span>赛事情报</span>
			</view>
			<view class="sai">
				<view class="title" @tap="navClick('/pages/score/score')">
					<view class="body">
						<span class="font">足球赛事</span>
						<span style="color: rgb(51, 51, 51);">实时赛事</span>
					</view>
					<view class="img">
						<image style="width: 45px; height: 45px;"
							src="https://img1.baidu.com/it/u=1654964033,3385170305&fm=253&fmt=auto&app=138&f=JPEG?w=551&h=500">
						</image>
					</view>
				</view>
				<view class="title" @tap="navClick('/pages/score/score')">
					<view class="body">
						<span class="font">篮球赛事</span>
						<span style="color: rgb(51, 51, 51);">实时赛事</span>
					</view>
					<view class="img">
						<image style="width: 80px; height: 60px;"
							src="https://img0.baidu.com/it/u=280934613,2242225982&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=424">
						</image>
					</view>
				</view>
			</view>
		</uni-card>
		<!-- <image style="width: 100%; height: 90px;" src="../../static/images/home/QQ图片20230329193628.png"></image> -->
		<!-- <u-popup :show="show" round bgColor="transparent" @close="show=false" mode="center">
			<image src="@/static/images/home/lianxi.png"></image>
		</u-popup> -->
		<view class="page-browse-collar">
		<!-- 悬浮块 -->
		<view class="flot" v-if="isShowHongbao">
			<view class="wrap">
				<!-- 红包 -->
				<view class="envelope" @tap="receive">
					<image src="@/static/images/redenvelope/envelope.png" mode="aspectFill" class="cover" />
					<image src="@/static/images/redenvelope/btn.png" mode="aspectFill" class="btn" />
				</view>
	
				<!-- 底部块 -->
				<view class="bottom">
					<text>打开红包</text>
				</view>
			</view>
		</view>
	
		<!-- 奖品弹窗 -->
		<cl-popup
			:visible.sync="prizeVisible"
			direction="center"
			:padding="90"
			background-color="transparent"
			display:flex
		>
			<view class="main">
				<!-- 上半部分背景图 -->
				<image src="@/static/images/redenvelope/popup-top.png" mode="aspectFill" class="top" />
				
				<!-- 钱币icon -->
				<image src="@/static/images/redenvelope/popup-icon.png" mode="aspectFill" class="icon" />
				
				<!-- 下半部分背景图 -->
				<image src="@/static/images/redenvelope/popup-bottom.png" mode="aspectFill" class="bottom" />
				
				<view class="content">
					<!-- 奖金 -->
					<view class="price">
						<text class="num">{{hongbao.amount}}</text>
						<text class="unit">元</text>
					</view>
				
					<!-- 标题 -->
					<view class="title"> {{hongbao.hbName}} </view>
				
					<!-- 领取按钮 -->
					<view class="btn">
						<cl-button round @tap="closePopup">立即领取</cl-button>
					</view>
				</view>
			</view>
		</cl-popup>
			
		</view>
	</view>
	
</template>

<script>
	import {
		ballList
	} from '@/api/ballgame.js'
	import {
		noticeList
	} from '@/api/notice.js'
	import {
		centre
	} from '@/api/lotteryOrder.js'
import { getHongbao, recvHongbao } from '../../api/user'
	export default {
		data() {
			return {
				shop: {},
				show: false,
				noticeList: [],
				isModalVisible: false,
				noticeContent: '',
				prizeVisible: false, // 奖品弹窗
				list1: [
					require('@/static/images/home/banner8.png'),
					require('@/static/images/home/banner1.png'),
					'https://img0.baidu.com/it/u=956770624,438600488&fm=253&fmt=auto&app=138&f=JPG?w=558&h=369',
					require('@/static/images/home/banner2.png'),
					require('@/static/images/home/banner3.png'),
					require('@/static/images/home/banner4.png'),
					require('@/static/images/home/banner6.png'),
				],
				ballList: [],
				notice: {},
				queryHongbao:{
					'pageNo':1,
					'pageSize':20,
					'status':1
				},
				isShowHongbao:true,
				hongbao:{
				}
			}
		},
		//下完单后返回到该页面数据进行重置
		onShow() {
			let isPay = uni.getStorageSync('isPay');
			if (isPay) {
				//删除标识
				uni.removeStorageSync('isPay');
			}
		},
		methods: {
    showCustomModal() {
      this.isModalVisible = true;
    },
    closeCustomModal() {
      this.isModalVisible = false;
    },
			callPhone(phone) {
				window.location.href = 'tel://' + phone
			},
			complaint() {
				uni.showToast({
					title: '请联系店主投诉',
					content: "sads",
					icon: 'none'
				});
			},
			invitation() {
				uni.navigateTo({
					url: '/pages/personal/share',
				})
			},
			navClick(url) {
				uni.navigateTo({
					url: url,
				})

			},
			init() {
				uni.showLoading();
				ballList().then(res => {
					this.ballList = res.voList
				})
				noticeList().then(res => {
					this.notice = res.voList[0]
					if(this.notice != {}){
						// 未开启直接不处理
						if(this.notice.status!=1){
							this.isModalVisible=false;
						}
						// 开启后根据更新时间判断有没有显示过
						var lastUdpateTime = uni.getStorageSync("noticeUpdateTime")
						if(lastUdpateTime != this.notice.updateTime){
							uni.setStorageSync("noticeUpdateTime", this.notice.updateTime);
							this.isModalVisible=true;
						}
					}
				})
				centre().then(res => {
					this.noticeList = res.voList;
				})
				getHongbao(this.queryHongbao).then(res=>{
					if(res.total>0){
						this.isShowHongbao=true;
						this.hongbao=res.voList[0];
					}else{
						this.isShowHongbao=false;
						this.hongbao={};
					}
					console.log(res);
				}),
				
				setTimeout(function() {
					uni.hideLoading();
				}, 500);
				
			},
			ballBtn(item) {
				if (item.name == "竞彩足球") {
					uni.navigateTo({
						url: "/pages/football/football"
					});
				} else if (item.name == "竞彩篮球") {
					uni.navigateTo({
						url: "/pages/basketball/basketball"
					});
				} else if (item.name == "北京单场") {
					uni.navigateTo({
						url: "/pages/beidan/beidan"
					});
				} else if (item.name == "排列3") {
					uni.navigateTo({
						url: "/pages/pailie/pailie3"
					});

				} else if (item.name == "排列5") {
					uni.navigateTo({
						url: "/pages/pailie5/pailie5"
					});
				} else if (item.name == "七星彩") {
					uni.navigateTo({
						url: "/pages/qxc/qxc"
					});
				} else if (item.name == "足球14场") {
					uni.navigateTo({
						url: "/pages/winburden/winburden"
					});
				} else if (item.name == "任选九") {
					uni.navigateTo({
						url: "/pages/victorydefeat/victorydefeat"
					});
				} else if (item.name == "大乐透") {
					uni.navigateTo({
						url: "/pages/dlt/dlt"
					});
				} else {
					uni.showToast({
						title: '敬请期待',
						icon: 'none'
					});
				}
			},
			// 领取红包
			receive() {
				this.prizeVisible = true;
				console.log("领红包调用");
				// setTimeout(() => {
				// 	this.num = 0;
				// }, 300);
			},
			
			// 关闭奖品弹窗
			closePopup() {
				this.prizeVisible = false;
				recvHongbao({'id':this.hongbao.id}).then(res=>{
					if(res.success==true){
						uni.showToast({
							title:'领取成功',
							icon:'none'
						})
						getHongbao(this.queryHongbao).then(res=>{
							if(res.total>0){
								this.isShowHongbao=true;
								this.hongbao=res.voList[0];
							}else{
								this.isShowHongbao=false;
								this.hongbao={};
							}
							console.log(res);
						});
					}else{
						uni.showToast({
							title:'领取失败',
							icon:'none'
						})
					}
				})
			},
		},
		onLoad() {
			this.init();
			//如果登录过的直接显示从缓存中取出店铺信息的logo
			this.shop = uni.getStorageSync("shop");
		}
	}
</script>

<style scoped lang="scss">

	/deep/.uni-card .uni-card__content {
		padding: 0px;
		background-color: white;
		// background-color: rgba(28, 141, 108, 0.2);
	}

	/deep/.uni-swiper-slide-frame {
		text-align: right;
	}
	// /deep/.uni-page-wrapper{
	// 	// background-color: #f5f5f5 !important;
	// 	// font-size: 100px;
	// 	width: 400px;
	// }
	.relation{
	    user-select: none;
	    margin: 0;
	    font-size: 15px;
	    font-weight: 400;
	    color: #000;
	}
	.title{
	    user-select: none;
	    color: #303133;
	    font-size: 15px;
	    position: relative;
	    z-index: 99999999999999;
	    margin: 0 17px;
	    padding-top: 22px;
	    display: flex;
	    justify-content: space-between;
	    align-items: flex-end;
	    flex-direction: row;
	    flex-wrap: nowrap;
	}
	.title-top{
	    user-select: none;
	    color: #303133;
	    font-size: 15px;
	    margin: 0;
	    display: flex;
	    flex-direction: row;
	    flex-wrap: nowrap;
	    justify-content: space-between;
	    align-items: center;
	    width: 100%;
	    position: relative;
	}
	
	.shopName{
	    user-select: none;
	    margin: 0;
	    font-style: italic;
	    height: 29px;
	    font-size: 22px;
	    font-weight: 600;
	    color: #000;
	}

	.noticeBar {
		width: 55%;
		height: 50rpx;
		line-height: 50rpx;
		color: #999;
		font-size: 24rpx;

		.list {
			width: 100%;
			height: 100%;
		}
	}

	/deep/.uni-card {
		padding: 0px !important;
	}
	
	// .home-control{
	// 	background-color: #f5f5f5 !important;
	// }
	.share{
		margin-top: 31%;
		margin-left: 3%;
	}
	.share button{
		// border-radius: 10%;
		// width: 55%;
		// margin: 0 auto;
		// margin-top: 10%;
		color: #9370db;
		border: 0;
		border-radius: 100upx;
		font-size: 20upx;
		background: #ffff83
	}

	.main-bg {
		display: flex;
		height: 155px;
		// width: 100%;
		width: 94%;
		// background-color: #9A3B86;
		// background: linear-gradient(to bottom, #9A3B86, #ffa255);
		background-image: url('/static/images/home/homeBannerBg.png');
		background-size: cover;
		position: relative;
		// margin-top: 0.4em;
		margin: 0 auto;
	}

	.uni-noticebar {
		margin-bottom: 0px;
	}

	/deep/image>img {
		// 提高图片清晰度
		image-rendering: -moz-crisp-edges;
		/* Firefox */
		image-rendering: -o-crisp-edges;
		/* Opera */
		image-rendering: -webkit-optimize-contrast;
		/* Webkit (non-standard naming) */
		image-rendering: crisp-edges;
		-ms-interpolation-mode: nearest-neighbor;
		/* IE (non-standard property) */
	}
	
	
	/* 自定义弹窗样式 */
	.custom-modal {
	  display: none;
	  position: fixed;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background-color: rgba(0, 0, 0, 0.5); /* 半透明背景 */
	  justify-content: center;
	  align-items: center;
	  z-index: 9999;
	}
	
	.show-modal {
	  display: flex; /* 显示弹窗内容 */
	}
	
	.modal-content {
		border-radius: 10%;
		width: 65%;
		background-color: white;
		padding: 20px;
		box-shadow: 0px 0px 10px #FF9A0D;
		display: flex;
		justify-content: center; /* 水平居中对齐 */
		align-items: center; /* 垂直居中对齐 */
		flex-direction: column; /* 垂直排列子元素 */
		text-align: center; /* 水平居中对齐文本 */
	}

// 体育彩票盒子
	.card {
		margin: 0px auto !important;
		background-color: #fffcfc;
		// background-color: #f5f5f5;
		box-shadow: 0 15px 25px rgba(0, 0, 0, 0.4);
		border-radius: 20upx;
		width: 94%;
		
	}

	.noticebar {
		margin-bottom: 0px !important;
	}

	.sai {
		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.title {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.body {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		
	}

	.card {
		margin-top: 30rpx !important;
	}

	.img {
		margin-left: 20rpx;
	}
	
	/* 图片容器样式 */
	.image-container {
	  max-width: 35%; /* 最大宽度为容器的宽度 */
	  overflow: hidden; /* 隐藏溢出部分 */
	}
	
	/* 响应式图片样式 */
	.responsive-image {
	  width: 100%; /* 图片宽度为容器的宽度 */
	  height: auto; /* 高度自动根据宽度调整，保持宽高比例 */
	  display: block; /* 去除图片下方空白 */
	}

	/deep/.font {
		color: #9A3B86 !important;
	}
	
	.page-browse-collar {
		// .list {
		// 	.item {
		// 		display: flex;
		// 		width: 100%;
		// 		padding: 40rpx 20rpx;
		// 		box-sizing: border-box;
		// 		.avatar {
		// 			flex-shrink: 0;
		// 			width: 102rpx;
		// 			height: 102rpx;
		// 			margin-right: 20rpx;
		// 			border-radius: 50%;
		// 		}
		// 		.main {
		// 			display: flex;
		// 			flex-direction: column;
		// 			text {
		// 				display: block;
		// 			}
		// 			.cover {
		// 				width: 336rpx;
		// 				height: 336rpx;
		// 				border-radius: 18rpx;
		// 			}
		// 			.name,
		// 			.content {
		// 				margin-bottom: 20rpx;
		// 			}
		// 		}
		// 	}
		// }
		.flot {
			position: fixed;
			right: 40rpx;
			bottom: 156rpx;
			width: 138rpx;
			.wrap {
				display: flex;
				justify-content: center;
				position: relative;
				width: 100%;
				height: 200rpx;
				overflow: hidden;
	
				.count {
					position: relative;
					top: 110rpx;
					width: 104rpx;
					height: 90rpx;
					image {
						position: absolute;
						width: 100%;
						height: 100%;
					}
					&-content {
						position: relative;
						z-index: 2;
						width: 100%;
						padding: 6rpx 0;
						text-align: center;
						color: #ffe0be;
						font-size: 28rpx;
					}
				}
				.envelope {
					position: relative;
					top: 70rpx;
					animation: envelope 1.8s 1;
					@keyframes envelope {
						0% {
							top: 120rpx;
							transform: scaleY(1);
						}
						20% {
							top: 20rpx;
							transform: scaleY(1);
						}
						70% {
							top: 20rpx;
							transform: scaleY(1);
						}
						80% {
							top: 20rpx;
							transform: scaleY(1);
						}
						90% {
							top: 70rpx;
							transform: scaleY(0.9);
						}
						100% {
							top: 70rpx;
							transform: scaleY(1);
						}
					}
					.cover {
						width: 104rpx;
						height: 126rpx;
					}
					.btn {
						position: absolute;
						top: 20rpx;
						left: calc(50% - 25rpx);
						width: 50rpx;
						height: 50rpx;
						animation: btn 0.3s 4;
						animation-direction: alternate;
						@keyframes btn {
							from {
								transform: scale(1);
							}
							to {
								transform: scale(0.6);
							}
						}
					}
				}
			}
			.bottom {
				display: flex;
				align-items: center;
				justify-content: center;
				position: absolute;
				left: 0;
				bottom: 0;
				width: 100%;
				height: 42rpx;
				background: linear-gradient(180deg, #ffe6ac 0%, #ffb66e 100%);
				border-radius: 92rpx;
				font-size: 20rpx;
				font-weight: 500;
				line-height: 34rpx;
				color: #854c00;
			}
		}
		.cl-popup {
			.main {
				position: relative;
				width: 580rpx;
				height: 770rpx;
				.top {
					position: absolute;
					top: 0;
					width: 100%;
					height: 560rpx;
				}
				.icon {
					position: absolute;
					top: 324rpx;
					left: calc(50% - 87rpx);
					width: 174rpx;
					height: 178rpx;
					z-index: 2;
				}
				.bottom {
					position: absolute;
					bottom: 0;
					width: 100%;
					height: 434rpx;
				}
				.content {
					display: flex;
					flex-direction: column;
					align-items: center;
					position: absolute;
					top: 0;
					left: 0;
					width: 100%;
					height: 100%;
					z-index: 5;
					.price {
						margin-top: 70rpx;
						margin-bottom: 300rpx;
						.num {
							font-size: 122rpx;
							font-weight: bold;
							color: #fc5c43;
						}
						.unit {
							position: relative;
							left: 10rpx;
							bottom: 10rpx;
							font-size: 50rpx;
							font-weight: 500;
							color: #fc5c43;
						}
					}
					.title {
						margin-bottom: 40rpx;
						font-size: 60rpx;
						font-weight: 400;
						color: #ffe0be;
					}
					.btn {
						/deep/.cl-button {
							width: 316rpx;
							height: 78rpx;
							background: linear-gradient(180deg, #fff7da 0%, #f3a160 100%);
							box-shadow: 0 3rpx 6rpx #d12200;
							&__text {
								font-size: 32rpx;
								font-weight: 500;
								color: #f74d2e;
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
	
</style>