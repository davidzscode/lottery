<template>
	<view>
		
			<nav-bar :title="'大乐透开奖'" :back="true"></nav-bar>
			<div class="box">
				<web-view src="https://m.310win.com/Trade/DrawInfo/OtherDrawList.aspx?lotteryType=10051"></web-view>
			</div>
		
	</view>
</template>

<script>
	export default {
		name: 'qxc',
		data() {
			return {
			}
		},
		onLoad() {
		},
		methods: {
		},
	}
</script>

<style scoped lang="scss">
</style>
