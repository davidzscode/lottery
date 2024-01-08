<template>
	<view class="box">
		<!-- <nav-bar :title="'玩法介绍'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '玩法介绍', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		<view>
			<u-cell-group>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_1.png" title="竞彩足球" isLink
					url="/pages/common/rule?type=0"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_2.png" title="竞彩篮球" isLink
					url="/pages/common/rule?type=1"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_4.png" title="北京单场" isLink
					url="/pages/common/rule?type=2"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_7.png" title="足球胜负" isLink
					url="/pages/common/rule?type=3"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_8.png" title="任选九" isLink
					url="/pages/common/rule?type=4"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_15.png" title="排列3" isLink
					url="/pages/common/rule?type=5"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_16.png" title="排列5" isLink
					url="/pages/common/rule?type=6"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_14.png" title="大乐透" isLink
					url="/pages/common/rule?type=7"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_23.png" title="七星彩" isLink
					url="/pages/common/rule?type=8"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_12.png" title="福彩3D" isLink
					url="/pages/common/rule?type=9"></u-cell>
				<u-cell size="large" clickable icon="https://m.310win.com/Trade/Images/ico_13.png" title="双色球" isLink
					url="/pages/common/rule?type=10"></u-cell>
		<!-- 		<u-cell size="large" clickable icon="https://ppm-pics-resource.s3.us-east-1.amazonaws.com/cms/qlc.jpg"
					title="七乐彩" isLink url="/pages/common/rule?type=11"></u-cell>
				<u-cell size="large" clickable icon="https://ppm-pics-resource.s3.us-east-1.amazonaws.com/cms/kl8.jpg"
					title="快乐8" isLink url="/pages/common/rule?type=12"></u-cell> -->
			</u-cell-group>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		}
	}
</script>

<style scoped lang="scss">
	/deep/.u-icon__img {
		width: 35px !important;
		height: 35px !important;
	}
</style>