<template>
	<view class="box">

		<nav-bar :title="'邀请好友'" :back="true"></nav-bar>
		<view style="background: transparent;">
			<u-popup :show="show" :round="10" mode="center" bgColor="transparent" duration="1500"
				style="position: relative;">
				<view style="width: 100%; display: flex; justify-content: center; align-items: center">
					<img style="height: 600px; width: 85%; border-radius: 10px;"
						src="/static/images/share/26fe0e92859faec91745b9bed893f87.jpg">
					</img>
					<view style="position: absolute; left: 40px; bottom: 35px; background: white;">
						<img style="width: 75px; height: 75px;" :src="user.qrCode">
					</view>
					<view style="position: absolute; bottom: -20px;">截图保存</view>
				</view>
			</u-popup>
		</view>
	</view>
</template>

<script>
	import {
		getUserInfo,
	} from "@/util/auth.js"
	export default {
		data() {
			return {
				user: {},
				shop:{},
				show: true
			}
		},
		onLoad() {
			this.init()
		},
		methods: {
			async init() {
				let user = await getUserInfo();
				this.user = user;
				this.shop=uni.getStorageSync("shop");
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
