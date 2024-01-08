<template>
	<view class="box">

		<nav-bar :title="'邀请好友'" :back="true"></nav-bar>
		<view style="background: transparent;">
			<u-popup :show="show" :round="10" mode="center" bgColor="transparent" duration="1500"
				style="position: relative;">
				<view style="width: 100%; display: flex; justify-content: center; align-items: center;">
					<image src="@/static/images/share/share.jpg" style="border-radius: 10px;height: 650px;width: 80%;">
					</image>
					<!-- <img style="width: 85%; border-radius: 10px;" src="/static/images/share/share.jpg"> -->
					</img>
					<!-- <view style="position: absolute; right: 80px; bottom: 78px; background: white;">
						<img style="width: 112px; height: 112px;" :src="user.qrCode">
					</view> -->
					<view style="position: absolute; top: 160px; background: white;">
						<img style="width: 130px; height: 130px;" :src="shareUrl">
						<!-- <image :src="shareUrl" style="width: 130px; height: 130px;"></image> -->
					</view>
					<view style="position: absolute; top: 120px;">截图发送给好友</view>
				</view>
			</u-popup>
		</view>

	</view>
</template>

<script>
	import {
		getUserInfo,
	} from "@/util/auth.js"
	import {
		share
	} from "@/api/user.js"
	export default {
		data() {
			return {
				user: {},
				shop: {},
				show: true,
				shareUrl: '',
			}
		},
		onLoad() {
			this.init()
		},
		methods: {
			init() {
				uni.showLoading();
				share().then(res => {
					console.log(res);
					this.shareUrl = res.shareUrl;
					uni.hideLoading();
				})

				// let user = await getUserInfo();
				// this.user = user;
				// this.shop=uni.getStorageSync("shop");
			},
		}
	}
</script>

<style lang="scss" scoped>
	.box {
		padding-bottom: 0px;
		background: transparent;
	}

	/deep/.u-transition {
		z-index: -999 !important;
	}

	/deep/.u-popup__content {
		width: 100%;
	}
</style>