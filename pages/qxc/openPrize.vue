<template>
	<view>
		
			<nav-bar :title="'七星彩开奖'" :back="true"></nav-bar>
			<div class="box">
				<web-view src="https://m.okooo.com/kaijiang/number.php?LotteryType=P7"></web-view>
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
