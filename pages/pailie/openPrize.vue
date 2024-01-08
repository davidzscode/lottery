<template>
	<view>
		
			<!-- <nav-bar :title="'排列3开奖'" :back="true"></nav-bar> -->
			<hx-navbar :config="{title: '排列3开奖', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
			
			<div class="box">
				<web-view src="https://m.310win.com/Trade/DrawInfo/OtherDrawList.aspx?lotteryType=10053"></web-view>
			</div>
	</view>
</template>

<script>
	export default {
		name: 'pailie3',
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
