<template>
	<view class="box">
<!-- 		<cmd-nav-bar title="北单开奖" :back="true" font-color="#fff" background-color="#9A3B86">
		</cmd-nav-bar> -->
		<hx-navbar :config="{title: '北单开奖', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		<web-view src="https://m.okooo.com/kaijiang/sport.php?LotteryType=WDL"></web-view>
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
