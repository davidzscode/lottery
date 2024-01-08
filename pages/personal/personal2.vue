<!--本页面由uniapp切片工具生成，uni-app切片-可视化设计工具(一套代码编译到7个平台iOS、Android、H5、小程序)，软件下载地址：http://www.ymznkf.com/new_view_669.htm -->
<template>
	<view class="my">
		<!-- <hx-navbar :config="{title: '个人中心', backgroundColor: [1,'#9A3B86'], color: '#ffffff'}"/> -->		
		<view class="heade" style="position: relative; top: 20px;">
			<span style="left: 175px; position: relative; font-size: 18px; font-family: PingFangSC-Medium, &quot;PingFang SC&quot;; font-weight: 800; color: rgb(17, 17, 17); line-height: 18px;">
				我的
			</span>
		</view>
		<view class="my_header">
			<view class="myhd">
				<view class="left">
					<image src="@/static/images/personal/default_photo.jpg" draggable="false"></image>
					<view class="name">13333333333</view>
				</view>
				<view class="right"></view>
			</view>
		</view>
		

		<u-popup :show="isShow" mode="bottom" @close="isShow = false" closeable>
			<uni-card style="margin-top: 40px;">
				<view
					style="height: 450px;overflow-y: scroll;font-size: 14px;color: #6a6a6a;line-height: 22px;">
					您当前下单店铺是线下正规实体店出票，机器打票和兑奖都是人工操作，因大型赛事订单激增，店主需要时间处理您的订单和提现等问题，请您不必担心资金安全。 Q:为什么我下单了却没有出票?
					因大型赛事期间订单量暴增，店主会按先后顺序打票。开赛前一小时是打票高峰期，可能会存在订单量过多，来不及出票的情况，机器截止打票后，未出票订单会撤单并自动退回钱款自动至您的AP
					P，有问题可联系店主。另，因来不及出票而导致撤单的订单，店主与平台无任何责任赔付，请您知晓。为保障您的良好体验，热门赛事可提前两小时下单，避开打票高峰期。
					<br>
					<br>
					Q:为什么充值(扣款成功)没有到账?
					因大型赛事期间下单人数过多，支付易造成拥堵甚至触发风控警。告，提示异常等，扣款成功后也可能会存在延迟到账的正常情况。请您不必惊慌，资金安全，无需着急投诉，平台运作正常。未成功生成订单的钱款会在24小时内自动返还至您的app账户，若超过24小时未收到退款，可联系店主反馈。为了保证您的良好体验，您可在赛前两小时先充值，再下单，规避高峰充值期。
					<br>
					<br>
					Q:我中奖了什么时候可以兑奖?
					比赛结束后系统会自动计算结果，店主确认无误后即可派奖，届时您可提现，请您耐心等待。
					<br>
					<br>
					Q:我中奖了，为什么没有收到奖金?
					中奖后，您需申请提现，店主收到请求后，会尽快处理。但因大型赛事期间订单过多，可能会存在无法第一时间处理的正常情况，建议您:1.大额奖金可提前单独联系店主处理;2.小额奖金可累计下单，或避开提现高峰期时申请。店主收到您的提现请求后，若发现问题，如中奖金额不符，已私下转过账等，有权驳回;若您需要提现帮助，可提交证据，申请平台介入处理。另外，店主通过您的提现申请后，因支付宝/银行卡/微信转账需要时间，且有每日转账限额，可能24小
					内无法转账成功，请您无需着急，耐心等待即可。
				</view>
			</uni-card>
		</u-popup>
		
	</view>
</template>

<script>
	import {
		logout,
		getUser
	} from "@/api/user.js"
	import {
		getUserInfo,
		setUserInfo,
		removeAuthtoken,
		removeUserInfo,
		removeExternalLogintoken
	} from "@/util/auth.js"
	export default {
		data() {
			return {
				user: {},
				isShow: false,
			}
		},
		onLoad() {
			this.init()
		},
		methods: {
			rightText() {
				this.isShow = true;
			},
			kefu() {
				window.location.href =
					'mqqwpa://im/chat?chat_type=wpa&uin=419367301&version=1&src_type=web&web_src=oicqzone.com'
			},
			init() {
				getUser().then(res => {
					if(res.status=="1"){
						this.logout();
					}
					setUserInfo(res);
					this.user = res
				})
			},
			navClick(url) {
				uni.navigateTo({
					url: url,
				})
			},
			navTap(url, type) {
				var obj = {};
				if (type == "0") {
					obj.title = "我的关注"
				} else {
					obj.title = "我的粉丝"
				}
				obj.type = type;
				uni.navigateTo({
					url: url + "?obj=" + encodeURIComponent(JSON.stringify(obj)),
				})
			},
			logout() {
				uni.showLoading();
				logout().then(res => {
					if (res.success) {
						removeAuthtoken();
						removeExternalLogintoken();
						removeUserInfo();
						setTimeout(() => {
							uni.redirectTo({
								url: "/pages/user/login"
							});
							this.btnDisabled = false;
							uni.hideLoading();
						}, 500)
					}
				})
			}
		}

	}
</script>

<style lang="scss">

	// @import '@/style/personal/personal.scss';
	.heade{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    position: relative;
	    top: 20px;
	}
	.name{
	    user-select: none;
	    color: #303133;
	    margin: 0;
	    box-sizing: border-box;
	    display: inline-block;
	    text-align: center;
	    align-items: center;
	    font-size: 16px;
	}
	.right{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    text-align: center;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    width: 20px;
	    height: 20px;
	    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAflJREFUSEu91stu00AUBuD/5KIs8hpdsOI5qoamCBA8QHLGUXZAoRRKaSmUS1VlESlIXkSRsvQKIVSh0gJFPAdiycZZcHGTWDlo0FRigezxOKpXXljz+Z9zztiEc77onD2kgo1GY0FEvvf7/R/zeLlEUCl1AcAxgK9xHC/OA00EW62WN5vNXgEgEflSLpcXe73ezzxJU7dUKXUTwL5GiehzsVhcyoOmgjqNUuo2gD2NAjiJomhpOBz+cklqBRr0DoCXBvkURVHNBbUGDXoXwAt9T0QfRaTm+/7vLEkzgQa9B+CZQY9FZDkLmhk06H0AT02yo2q1utzpdCKbpE6gQR8AeGKQ96PRqB4EQSrqDBr0IYDH+l5EDqfTaX0wGJwmJc0FGvQRgC1T03fj8fhyEpobZOZ1Ito1qU5KpVIt6WDIBTLzBhHtZJlNZ5CZN4lo22AfAFyyGQ8nkJm3iWjzbCwA1G2wv3W2mZ1/n2HmHSLaME1yGIbhis04nK2RCWTmXSJat+3I/4WxBpn5ORGtmUUOJpPJlbSZcwY9z9sTkVWzwNtKpXK12+2Os5bDqoae5+2LyC2zjW/CMLwWBMHEBUsFlVL62NLHl/4cvQ7D8HoeLBVsNpsXC4XCkf61EJEbvu9PXZNZd2m73V6I4/jbPLDUhHnTOHfpPOE/AKzQHVugRd0AAAAASUVORK5CYII=) no-repeat;
	    background-size: 100% 100%;
	    position: relative;
	    top: 10px;
	    left: 152px;
	}
	.left{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    text-align: center;
	    margin: 0;
	    box-sizing: border-box;
	    display: flex;
	    align-items: center;
	    // width: 40px;
		// height: 40px;
		
	}
	.myhd{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    box-sizing: border-box;
	    height: 50px;
	    padding: 5px;
	    display: flex;
	    text-align: center;
	}
	.my_header{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    padding: 16px 16px 0 16px;
	}
	.my{
		user-select: none;
		color: #303133;
		font-size: 14px;
		margin: 0;
		display: block;
		box-sizing: border-box;
		width: 100vw;
		min-height: 100vh;
	}
	.jymxRightLabel{
	    user-select: none;
	    line-height: 26px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    font-size: 13px;
	    font-family: PingFangSC-Regular,PingFang SC;
	    font-weight: 400;
	    color: #fff;
	    margin-right: 8px;
	}
	.jymxRightIcon{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    line-height: 26px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    width: 14px;
	    height: 14px;
	    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAYVJREFUSEu91jdOBEEQheG/7kJAxDkQXoDgCMR47z2ImHMghBDCgzgHIiTGQ/BQoSJB7E6P0Xa8M99W672eNhq8rMEemaCkJuDRzJ6q+HN1QUnNwAVwD7RWgWaBQ8A+/OzEXaDPZSZN2dJhYC/QW6DNzAqjmaBPI2kU2A30JtCXIpMmgYGOAzuBXAPtZpYbTQYDnQC2A70K9DXPpLnAQKeAzUA8wZ1mlozmBgOdAdYDPQ/0LWXSQmCgs8BaIGdAl5llooXBQOeBlUBPA32vN2kpMNBFYCmQE6DHzGqiVYDTwEaA3lGvS82DoRQoaQ5YzdPNwqCkBWA5sEugI6UehUBJDjnoy2vhCU3qYm5Qkm+hb6UvT2Z3Sh1+k5sLlOTh8JD4ykzkf/VIBiVtAZPxkmOgt178a3UxCZTkn6axeMkR0GdmHylH2d/fZIKS/OM7Eg8eAv1m9lkE82eyrhh+bPnx5esAGCiDpYAtEXu/Wgya2VfRyZJTGtfEhyqwzAnLTlOqFlXh3/TGjh0qRJyzAAAAAElFTkSuQmCC) no-repeat;
	    background-size: 100% 100%;
	}
	.jymxRight{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    line-height: 26px;
	    margin: 0;
	    box-sizing: border-box;
	    display: flex;
	    align-items: center;
	}
	.jymxLeft{
		    user-select: none;
		    line-height: 26px;
		    margin: 0;
		    display: block;
		    box-sizing: border-box;
		    font-size: 15px;
		    font-family: PingFangSC-Regular,PingFang SC;
		    font-weight: 400;
		    color: #8d3d87;
	}
	.jymxBox{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    box-sizing: border-box;
	    position: absolute;
	    top: 10px;
	    left: 0;
	    width: 100%;
	    display: flex;
	    flex-direction: row;
	    flex-wrap: nowrap;
	    justify-content: space-between;
	    align-items: center;
	    line-height: 26px;
	    padding: 0 12px;
	}
	.top{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    position: relative;
	    right: 10px;
	}

	.topBtn{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    display: block;
	    box-sizing: border-box;
	    margin: 0 15px;
	    position: relative;
	    background: linear-gradient(90deg,#d85eab,#8d3d87);
	    box-shadow: inset 0px 1px 7px 0px hsla(0,0%,100%,.5);
	    border-radius: 14px;
	}
	
	.wqb{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    width: 84px;
	    height: 32px;
	    position: absolute;
	    top: 10px;
	    left: -6px;
	    background: url("@/static/images/personal/myqb.png") 0% 0% / 100% 100%;
	}
	
	.mybody{
	    user-select: none;
	    color: #303133;
	    font-size: 14px;
	    margin: 0;
	    display: block;
	    box-sizing: border-box;
	    border-top-left-radius: 8px;
	    border-top-right-radius: 8px;
	    margin-top: 5px;
	    height: 104px;
	    opacity: 1;
	}
	
	.kefu {
		padding: 30px;
		border-radius: 30px;
		display: flex;
		align-items: center;
		justify-content: space-around;
		border: none;
		background: linear-gradient(90deg, rgba(131, 58, 180, 0.5830707282913166) 0%, rgba(255, 63, 67, 0.9500175070028011) 50%, rgba(252, 176, 69, 0.835171568627451) 100%);
	}

	#fen {
		font-size: 12px;
		letter-spacing: 0.2rem;
		background-image: -webkit-linear-gradient(left, #fff, #6665f6 25%, #fff 50%, #6665f6 75%, #fff);
		-webkit-text-fill-color: transparent;
		-webkit-background-clip: text;
		-webkit-background-size: 200% 100%;
		-webkit-animation: maskedAnimation 4s infinite linear;

		@keyframes maskedAnimation {
			0% {
				background-position: 0 0;
			}

			100% {
				background-position: -100% 0;
			}
		}
	}
</style>
