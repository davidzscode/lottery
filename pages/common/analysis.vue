<template>
	<view class="box">
		<nav-bar :title="'对局分析'" :back="true"></nav-bar>
		<!-- <nav-bottom :current="0"></nav-bottom> -->
			<web-view :src="url"></web-view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				url: ""
			}
		},
		methods: {

		},
		onLoad(option) {
			this.url =decodeURIComponent(option.url)
		},
	}
</script>

<style lang="scss">
	
	uni-web-view.uni-webview--fullscreen {
		margin-top: 40rpx;
		margin-bottom: 146rpx;
	}
</style>
