<template>
	<view class="bigView">
		<!-- <nav-bar :title="'首页'"></nav-bar> -->
		<view class="status_bar"></view>
		<view class="home">
			<view class="topBox">
				<view class="title">
					<view class="title-top">
						<view class="shopName">
							欢迎加入<span style="color:#8D3D87">{{shop.name}}</span>
						</view>
						<view @tap="openContact" class="relation">联系我们</view>
					</view>
					<view v-if="showContact" class="arrivalNavigation">
						<view class="d4"></view>
						<view class="sideNavigation">
							<view @tap="copyWechat" class="sideNavigationItem">
								<image style="width: 20px; height: 20px;"
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAA2RJREFUSEvNll9sU1Ucxz+nt7dlXdetnd3ocLJ/bLHMSGYi808cEqPgi2MxEhEwGv9gNJIsKhKSBdzDRBMVMDEKDxoThAcghAc0arYHdBjiNBEojK1zlq1z0E66teu63V7Tq23A265jGY3n7fx+v/P9nN/vnt+5RwCs/+nBWgxShwqPALaEbQFHSMB3xJXth1ee6hUJmCpJp1GxLyBELyUYE4rSKNafaTqiQssthf0rLuCoeOpM07VbUMZM+w8lgGousksy/n9ACYkaq5sqSy0FxkJi8SjDUR/nx39lQhm/6eJkzFAgeNTZTLNrAw6TUyc8HY/xQ/B7Dg0dYGw6MGdwWqAsZFqr22koaswqND5zjfcv7eBi+GzW2ERAWuArFdtYddtaTWBocpDByX7usz+MEEKzBWNX+S30M/c7ViMbZMIz47zteZnRqeGsUB2w0rKMjjs/S4m3eV7Tdr+3/iCli8o0wU8GdtMVOMn2Ze+xovBezdYd7OQj766bBz695EWaXc+kFvqjPgYjXhodTSlb4pudC/XQaF/FaGyE/vAFJmZCfOH7GJXZu0yX4etVbTzgWJ11p/3hi5wKfEtVfh31BQ0UyQ4UVeFy9HdOB7v45soxIkpYp6MDPle+lTWl62YF9vzVjX/qMo+XPJkq/X8XBGJX+KCvjb6I5waXDriy6CFaa97JCByLXaUr8DXrXBu1mCklitEgIwnpn3k8ilEYkYRRy3DbuRcYjflTejqgAQPvuvez1FKdFnrc/xVrS1swGcyav/Xss9TmL2dL5VvafIdnCyWmMrZWt2nzH4Od7LnuMKVti1JzGbvq9mE3Feugx/0HecK1IWXvm/BQKNtxmhdrNm+4F4uUz+JFS1IV2PzLmswZJj0O2UmH+1PtMFw/jg5/SUvZpqyHKhkQV+Ns6nmMGXVaM2W82iySlf13H2NkaoiByCXK8yopNNo5MXKIzXe8OmfgHxEvb55/PnuGddZ6iuUSusc6db21232ACkvNnKB7ve3anZsc8/o9ledVsbNuD1ZjwazQk38e4XPfvhti5gVMKLjMt/PS0jdw21akhSaa/0PvTp1v3sCkUkVeDcttDRSbnFpP+iYHKDG7uMt2D+29rQsPzFRTq2RjQgmlBeb2EZXzZ2LOH8KJIufyqf83NMpYWgWrJ8wAAAAASUVORK5CYII=">
								</image>
								<view class="text">微信</view>
							</view>
							<view @tap="callPhone(123)" class="sideNavigationItem">
								<image style="width: 20px; height: 20px;"
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAoxJREFUSEvtlk1IVFEUx3/3vZmcGXVytAksyXAhUkHkB7aZaCFBJhXhQtSIchMFmrYyijami0CEQmhhkhglfdjG2lgGQUgfthBaiIvCwk8cZ5wZNZ158Ryf9ZoZR2WaTZ3l453/755z//feI1CjbjIbRWkCikCxLn+LWQg30IsQ9TTbh8QKrB8UW8wYYYWEEyEOCmonnoBy6u/CNHXxVAW6Yt/GSMsXbkHtuBKf6oKU/8DwfgPSrRJjswECG9yQNVuas11myhtgyqtXTbMIGo8lkWmTGBxd4mafjwnP+sgRgcU5RrqrUhh1BTjc6uTLdEBXrQBURH6GgYbiRKq7PQxN+qP6LyzQKMP362nYk+Rlga/Tfh10W6LgfoUV36JCfY8X55xCe1kyx9tcLOnXFbKAsMDcDAMf61J1P6tQx20nIzO/FHfbJDrKrZTec1GyJ2EZ1vFhfs0qwwIP7DQwcFkPVFWqHrq5+24ercJLzzxk2mQKdhm49WaO1tJkyjvVqzNyhAUmGGDmhh2TUd2pYPR8XuBEmwv/ijey7TKOLCNdnxaodphpfOmj4aiFqy98GweqGQ8qrZTlmlaT77z1cf6xJ6opov0Q0aX7d8gM1KUiScEq534oFLZMMzgW3YkbbqmW0HIyiZpDltX8Mbefik43r4YXOVtg4nS+iXNd7pAjs2mgyQCvL9oozDTqNEZm/GRslRBChByZTbdUS1Qd2XchhX3peujvwn+e001XqCWmmAWPzlgpyk6IqDXwbZG8Zme0Atf/PKneqXGYuXYkEZtFChFu6vVy5bk3dkBNyWYWVOaZKNm7haw0mfHZAG3987S/X/uG0fL/iQc47kNUvMfE4NQdx0FYtU8cR/2fCBUSzkc9vyQAAAAASUVORK5CYII=">
								</image>
								<view class="text">电话</view>
							</view>
							<view class="sideNavigationItem">
							</view>
						</view>
					</view>
				</view>
				<view @tap="showShop" class="Box">
					<!-- 	<view class="right-icon ">
						<span style="color: #8d3c86;font-size: 14px;line-height:30px">分享好友</span>
					</view> -->
				</view>

				<view
					style="width: 100%; display: flex; justify-content: center; padding: 0px 16px; box-sizing: border-box;">
					<view v-if="this.notice.staticStatus == 1" class="shopnotice">
						<view class="gonggao">
							<view class="gonggao-icon"></view>
							<view class="gonggao-text">{{this.notice.staticMsg}}</view>
						</view>
						<!-- 显示近期中奖人 -->
						<!-- 						<view class="bobao">  
							<swiper style="height: 38px;"  height="40px" circular="true" vertical="true" autoplay="true" interval="3988"
								duration="2000">
								<swiper-item class="bbItem" v-for="item in noticeList" :key="item.id" @click="handleInfo(item.id)">
									<span style="color: #8D3D87;">恭喜：</span>
									{{ item.msg }}
								</swiper-item>
							</swiper>
						</view> -->
					</view>
				</view>
			</view>
		</view>

		<!-- 		<uni-notice-bar :speed="30" color="#fff" showIcon background-color="#9A3B86" scrollable single
			:text="noticeMsg">
		</uni-notice-bar> -->
		<!-- 轮播图 -->
		<!-- 		<view>
			<u-swiper :list="list1" indicator indicatorMode="dot" height="200" duration="800" radius="0"></u-swiper>
		</view> -->
		<uni-card is-shadow class="card">
			<view style="display: flex;justify-content:flex-start;align-items: center;">
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
						<view class="image-container">
							<!-- 使用 CSS 控制图片大小 -->
							<!-- <image src="@/static/images/home/chongzhi.png" mode="aspectFit" class="responsive-image" /> -->
							<image src="@/static/images/home/bobao.png" style="width: 76px; height: 31px;"
								mode="aspectFit">
							</image>

						</view>
						<!-- 显示的内容 -->
						<p style="margin-top: 5%; font-size: 18px; ">{{notice.msg}}</p>
						<!-- 关闭按钮 -->
						<button :class="[isCountdownFinish?'button-active':'button-disactive']"
							@click="closeCustomModal">我知道了{{this.noticeCountdownText}}</button>
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
		<!-- <uni-card is-shadow class="card"> -->

		<!-- 			<view>
				<span>精选推单</span>
				<view class="articlelist-wrap-new">
					<view class="article-item-new">
						<view class="top-content">
							<view class="expert">
							</view>
						</view>
					</view>
				</view>
			</view> -->
		<!-- 			<view class="sai">
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
			</view> -->
		<!-- </uni-card> -->

		<!-- <image style="width: 100%; height: 90px;" src="../../static/images/home/QQ图片20230329193628.png"></image> -->
		<!-- <u-popup :show="show" round bgColor="transparent" @close="show=false" mode="center">
			<image src="@/static/images/home/lianxi.png"></image>
		</u-popup> -->
		<view class="page-browse-collar">
			<!-- 悬浮块 -->
			<!-- <view class="flot" v-if="isShowHongbao"> -->
			<view class="flot" v-if="true">
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
			<cl-popup :visible.sync="prizeVisible" direction="center" :padding="90" background-color="transparent"
				display:flex>
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
							<!-- <text class="num">{{hongbao.amount}}</text> -->
							<text class="num">388</text>
							<text class="unit">元</text>
						</view>

						<!-- 标题 -->
						<!-- <view class="title"> {{hongbao.hbName}} </view> -->
						<view class="title"> 立减红包 </view>
						<!-- 领取按钮 -->
						<view class="btn">
							<cl-button round @tap="closePopup">立即领取</cl-button>
						</view>
					</view>
				</view>
			</cl-popup>
		</view>

		<RecommendOrder />

	</view>

</template>

<script>
	import RecommendOrder from '@/pages/article/RecommendOrder.vue'
	import checkUpdate from '@/uni_modules/uni-upgrade-center-app/utils/check-update-nvue.js'
	import {
		ballList
	} from '@/api/ballgame.js'
	import {
		noticeList
	} from '@/api/notice.js'
	import {
		centre
	} from '@/api/lotteryOrder.js'
	import {
		getHongbao,
		recvHongbao,
		initConfig,
	} from '@/api/user.js'
	export default {
		components: {
			RecommendOrder
		},
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
				queryHongbao: {
					'pageNo': 1,
					'pageSize': 20,
					'status': 1
				},
				isShowHongbao: false,
				hongbao: {},
				showContact: false,
				noticeCountdown: 5, //倒计时时间5秒
				noticeCountdownText: '(5)',
				isCountdownFinish: false,
				timer: null, //倒计时
				noticeButtonAble: false, // 公告按钮是否可点击
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
		onPullDownRefresh() {
			this.init()
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 1000);
		},
		onTabItemTap(e) {
			// tab 点击时执行，此处直接接收单击事件
			this.init();
		},
		methods: {
			showCustomModal() {
				this.isModalVisible = true;
				// 提交报名按钮倒计时
				this.timer = setInterval(() => {
					if (this.noticeCountdown <= 0) {
						this.noticeCountdown = ""
						this.noticeCountdownText = ""
						this.isCountdownFinish = true
						this.noticeButtonAble = true
						clearInterval(this.timer);
					} else {
						console.log(this.noticeCountdown);
						this.noticeCountdown--
						this.noticeCountdownText = '(' + this.noticeCountdown + ')'
					}
				}, 1000);
			},
			closeCustomModal() {
				// 如果倒计时结束
				if (this.isCountdownFinish) {
					// 关闭提示
					this.isModalVisible = false;
					// 更新点击时间
					uni.setStorageSync("noticeUpdateTime", this.notice.updateTime);
				}

			},
			openContact() {
				console.log('点击电话');
				if (this.showContact == true) {
					this.showContact = false;
				} else {
					this.showContact = true;
				}
			},
			copyWechat() {
				const shopInfo = uni.getStorageSync('shopInfo');
				if (shopInfo.shopWechat == undefined) {
					uni.showToast({
						title: '获取微信失败，请联系管理员',
						icon: 'none'
					});
				} else {
					this.showContact = false;
					uni.setClipboardData({
						data: shopInfo.shopWechat,
						success: function() {
							uni.hideToast();
							uni.showModal({
								showCancel: false,
								title: '复制成功',
								content: '使用微信搜索复制的微信号，即可添加店主好友',
								success: function(res) {
									if (res.confirm) {
										console.log('用户点击确定');
									} else if (res.cancel) {
										console.log('用户点击取消');
									}
								}
							});
						},
						fail: function(error) {
							uni.showToast({
								title: '复制失败，请电话联系店主'
							})
						}
					});
				}
			},
			callPhone(phone) {
				this.showContact = false;
				const shopInfo = uni.getStorageSync('shopInfo');
				if (shopInfo.shopMobile == undefined) {
					uni.showToast({
						title: '获取手机号失败，请联系管理员',
						icon: 'none'
					});
				} else {
					uni.makePhoneCall({
						phoneNumber: shopInfo.shopMobile,
						success: (res) => {
							console.log('调用成功!')
						},
						fail: (res) => {
							console.log('调用失败!')
							this.callPhone(phone); //重复调用一次
						}
					});
				}
			},
			complaint() {
				uni.showToast({
					title: '请联系店主投诉',
					content: "sads",
					icon: 'none'
				});
			},
			invitation() {
				console.log('邀请好友');
				uni.navigateTo({
					url: '/pages/personal/share',
				})
			},
			showShop() {
				console.log('展示店铺');
				uni.navigateTo({
					url: '/pages/home/showShop',
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
					console.log(this.notice);
					if (this.notice != {}) {
						// 未开启直接不处理
						if (this.notice.status != 1) {

						} else {
							// 开启后根据更新时间判断有没有显示过
							var lastUdpateTime = uni.getStorageSync("noticeUpdateTime")
							if (lastUdpateTime != this.notice.updateTime) {
								// this.isModalVisible=true;
								this.showCustomModal();
							}
						}
					}
				})
				initConfig().then(res => {
					if (res.success) {
						uni.setStorageSync("backUrl", res.backUrl.api)
						uni.setStorageSync("shopInfo", res.shopInfo)
					}
				})
				centre().then(res => {
					this.noticeList = res.voList;
				})
				getHongbao(this.queryHongbao).then(res => {
						if (res.total > 0) {
							this.isShowHongbao = true;
							this.hongbao = res.voList[0];
						} else {
							this.isShowHongbao = false;
							this.hongbao = {};
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
				uni.showToast({
					title: '红包活动1月1日开始，敬请期待',
					icon: 'none',
					duration: 4289,
				})
				// recvHongbao({'id':this.hongbao.id}).then(res=>{
				// 	if(res.success==true){
				// 		uni.showToast({
				// 			title:'领取成功',
				// 			icon:'none'
				// 		})
				// 		getHongbao(this.queryHongbao).then(res=>{
				// 			if(res.total>0){
				// 				this.isShowHongbao=true;
				// 				this.hongbao=res.voList[0];
				// 			}else{
				// 				this.isShowHongbao=false;
				// 				this.hongbao={};
				// 			}
				// 			console.log(res);
				// 		});
				// 	}else{
				// 		uni.showToast({
				// 			title:'领取失败',
				// 			icon:'none'
				// 		})
				// 	}
				// })
			},

			startCountdown() {

			}

		},
		onLoad() {
			this.init();
			//如果登录过的直接显示从缓存中取出店铺信息的logo
			this.shop = uni.getStorageSync("shop");
			checkUpdate();
			console.log('checkUpdate调用');
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
	.expert-name-box {
		--swiper-theme-color: #007aff;
		--swiper-navigation-size: 44px;
		user-select: none;
		-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
		text-size-adjust: none;
		font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Helvetica Neue, STHeiti, Microsoft Yahei, sans-serif;
		font-weight: 400;
		list-style: none;
		font-size: .32rem;
		color: #111;
		display: flex;
		-webkit-box-orient: vertical;
		-webkit-box-direction: normal;
		flex-direction: column;
		-webkit-box-pack: center;
		justify-content: center;
		-webkit-box-align: start;
		align-items: flex-start;
	}

	.expert {
		user-select: none;
		text-size-adjust: none;
		font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Helvetica Neue, STHeiti, Microsoft Yahei, sans-serif;
		font-weight: 400;
		list-style: none;
		display: flex;
		align-items: center;
		font-size: .32rem;
		color: #111;
	}

	.top-content {
		user-select: none;
		text-size-adjust: none;
		font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Helvetica Neue, STHeiti, Microsoft Yahei, sans-serif;
		color: #111;
		font-size: .4rem;
		font-weight: 400;
		list-style: none;
		display: flex;
		align-items: center;
		-webkit-box-pack: justify;
		justify-content: space-between;
		margin-bottom: .22667rem;
	}

	.article-item-new {
		user-select: none;
		text-size-adjust: none;
		font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Helvetica Neue, STHeiti, Microsoft Yahei, sans-serif;
		color: #111;
		font-size: .4rem;
		font-weight: 400;
		list-style: none;
		position: relative;
		margin: 0 .4rem;
		padding: .26667rem;
		box-sizing: border-box;
		background: #fff;
		border-radius: .13333rem;
		margin-top: .18667rem;
	}

	.articlelist-wrap-new {
		user-select: none;
		text-size-adjust: none;
		font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Helvetica Neue, STHeiti, Microsoft Yahei, sans-serif;
		color: #111;
		font-size: .4rem;
		margin: 0;
		padding: 0;
		font-weight: 400;
		list-style: none;
		overflow: hidden;
	}

	.text {
		user-select: none;
		margin: 0;
		display: block;
		box-sizing: border-box;
		font-size: 14px;
		font-family: PingFangSC-Regular, PingFang SC;
		font-weight: 400;
		color: #000;
	}

	.d4 {
		user-select: none;
		color: #303133;
		font-size: 15px;
		margin: 0;
		display: block;
		box-sizing: border-box;
		width: 0;
		height: 0;
		margin-left: 55px;
		margin-top: -22px;
		border-width: 5px;
		border-style: solid;
		border-color: transparent #fff transparent transparent;
		transform: rotate(90deg)
			/*顺时针旋转90°*/
		;
	}

	.sideNavigationItem {
		user-select: none;
		font-size: 15px;
		color: #eee;
		margin: 0;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		flex-direction: row;
		justify-content: space-evenly;
	}

	.sideNavigation {
		user-select: none;
		font-size: 15px;
		margin: 0;
		box-sizing: border-box;
		width: 82px;
		background: #fff;
		color: #eee;
		border-radius: 5px;
		height: 77px;
		display: flex;
		flex-direction: column;
		flex-wrap: nowrap;
		justify-content: space-evenly;
	}

	.arrivalNavigation {
		user-select: none;
		color: #303133;
		font-size: 15px;
		margin: 0;
		display: block;
		box-sizing: border-box;
		width: 82px;
		position: absolute;
		bottom: -91px;
		right: 0;
		z-index: 99;
		height: 77px;
		background: #fff;
		box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, .06);
		border-radius: 5px;
	}

	.bbItem {
		user-select: none;
		font-size: 14px;
		font-family: PingFangSC-Regular, PingFang SC;
		margin: 0;
		line-height: 33px;
		height: 33px;
		box-sizing: border-box;
		padding: 6px 11px;
		display: flex;
		align-items: center;
		white-space: nowrap;
		font-weight: 400;
		color: #8d3d87;
	}

	.bobao {
		user-select: none;
		font-size: 14px;
		font-family: PingFangSC-Regular, PingFang SC;
		font-weight: 400;
		color: #222;
		display: block;
		overflow: hidden;
		line-height: 33px;
		white-space: nowrap;
		margin: 0 11px;
		box-sizing: border-box;
		height: 33px;
		background: #ffeefe;
		border-radius: 6px;
		margin-top: 11px;
	}

	.gonggao-text {
		user-select: none;
		font-family: PingFangSC-Regular, PingFang SC;
		margin: 0;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		text-align: left;
		flex: 1;
		font-weight: 400;
		color: #000;
		font-size: 14px;
	}

	.gonggao-icon {
		user-select: none;
		font-size: 14px;
		font-family: PingFangSC-Regular, PingFang SC;
		font-weight: 400;
		color: #222;
		width: 25px;
		height: 25px;
		background: url(@/static/images/home/gonggao.jpg) no-repeat;
		background-size: 100% 100%;
		margin: 0 6px 0 12px;
	}

	.gonggao {
		user-select: none;
		font-size: 14px;
		font-family: PingFangSC-Regular, PingFang SC;
		font-weight: 400;
		color: #222;
		margin: 0;
		box-sizing: border-box;
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: space-between;
	}

	.shopnotice {
		user-select: none;
		margin: 0;
		width: 100%;
		height: 40px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		background: #fff;
		border-radius: 6px;
		font-size: 14px;
		font-family: PingFangSC-Regular, PingFang SC;
		font-weight: 400;
		color: #222;
	}

	.right-icon {
		user-select: none;
		color: #303133;
		font-size: 15px;
		margin: 0;
		background: linear-gradient(180deg, #fee8bf, #ffd882);
		opacity: 1;
		border-radius: 50px;
		width: 88px;
		height: 30px;
		position: absolute;
		bottom: 15px;
		left: 19px;
		text-align: center;
	}

	.bigView {
		user-select: none;
		color: #303133;
		font-size: 15px;
		margin: 0;
		display: block;
		box-sizing: border-box;
		width: 100vw;
		min-height: 100vh;
		background-color: #eee;
	}

	.home {
		user-select: none;
		// color: #303133;
		background-color: #f6e5fd;
		font-size: 15px;
		margin: 0;
	}

	.topBox {
		user-select: none;
		// color: #303133;
		font-size: 15px;
		margin: 0;
		// background: linear-gradient(180deg,#f6e5fd,rgba(198,224,255,0));
		background-image: linear-gradient(#f6e5fd, #f5f5f5, #ececec);
		position: relative;
	}

	.Box {
		user-select: none;
		color: #303133;
		font-size: 15px;
		margin: 17px 16px 16px;
		height: 165px;
		border-radius: 10px;
		background: url(@/static/images/home/154924.png) no-repeat;
		background-size: 100% 100%;
		position: relative;
	}

	.relation {
		user-select: none;
		margin: 0;
		font-size: 15px;
		font-weight: 400;
		color: #000;
	}

	.title {
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

	.title-top {
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

	.shopName {
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
	.share {
		margin-top: 31%;
		margin-left: 3%;
	}

	.share button {
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
		background-image: url('/static/images/home/154924.png');
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
		background-color: rgba(0, 0, 0, 0.5);
		/* 半透明背景 */
		justify-content: center;
		align-items: center;
		z-index: 9999;
	}

	.show-modal {
		display: flex;
		/* 显示弹窗内容 */
	}

	.modal-content {
		border-radius: 10%;
		width: 65%;
		background-color: white;
		padding: 20px;
		box-shadow: 0px 0px 10px #FF9A0D;
		display: flex;
		justify-content: center;
		/* 水平居中对齐 */
		align-items: center;
		/* 垂直居中对齐 */
		flex-direction: column;
		/* 垂直排列子元素 */
		text-align: center;
		/* 水平居中对齐文本 */
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
		max-width: 30%;
		/* 最大宽度为容器的宽度 */
		overflow: hidden;
		/* 隐藏溢出部分 */
	}

	/* 响应式图片样式 */
	.responsive-image {
		width: 100%;
		/* 图片宽度为容器的宽度 */
		height: auto;
		/* 高度自动根据宽度调整，保持宽高比例 */
		display: block;
		/* 去除图片下方空白 */
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
			z-index: 1;

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

	.button-active {
		// font-weight: bold; 
		font-size: 15px;
		margin-top: 10%;
		color: #FF9A0D;
		background-color: #fff;
		border: 1px solid #FF9A0D;
	}

	.button-disactive {
		// font-weight: bold; 
		font-size: 15px;
		margin-top: 10%;
		color: #000000;
		background-color: #fff;
		border: 1px solid #2f2f2f;
	}
</style>