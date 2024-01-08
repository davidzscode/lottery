<template>
	<view class="box">
		<!-- <view class="status_bar"></view> -->
<!-- 		<cmd-nav-bar title="足球开奖" :back="true" font-color="#fff" background-color="#9A3B86">
		</cmd-nav-bar> -->
		<hx-navbar :config="{title: '足球开奖', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		
		<web-view src="https://m.sporttery.cn/mjc/zqsgkj/"></web-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		methods: {
		},
	}
</script>

<style lang="scss">
</style>
