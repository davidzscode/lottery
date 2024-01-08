<template>
	
	<view class="box">
		<hx-navbar :config="{title: '资料修改', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
			<nav-bar :title="'设置'" :back="true"></nav-bar>
			<view class="list-cell b-b m-t" @click="navTo('./means')" hover-class="cell-hover" :hover-stay-time="50">
				<text class="cell-tit">个人资料</text>
				<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_65"></image>
			</view>
			<view class="list-cell b-b" @click="navTo('./changePwd')" hover-class="cell-hover" :hover-stay-time="50">
				<text class="cell-tit">修改密码</text>
				<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_65"></image>
			</view>
			<view class="list-cell" @click="navTo('./real')" hover-class="cell-hover" :hover-stay-time="50">
				<text class="cell-tit">实名认证</text>
				<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_65"></image>
			</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {};
		},
		onLoad() {},
		methods: {
			//跳转
			navTo(url) {
				uni.navigateTo({
					url: url,
				})

			},
		}
	}
</script>

<style scoped lang='scss'>
	page {
		background: #f8f8f8;
	}

	.my_65 {
		white-space: normal;
		width: 14px;
		height: 15px;
		padding: 0px;
		margin-top: 9px;
		margin-left: 54px;
		float: left;
		border-radius: 0px;
		font-size: 14px;
		line-height: 15px;
	}

	.list-cell {
		display: flex;
		align-items: baseline;
		padding: 20upx 30upx;
		line-height: 60upx;
		position: relative;
		background: #fff;
		justify-content: center;

		&.log-out-btn {
			margin: 0 auto;
			margin-top: 40upx;
			border-radius: 6px;
			width: 85%;
			line-height: 22px;
			background: #9A3B86 !important;

			.cell-tit {
				font-size: 16px !important;
				color: #fff !important;
				text-align: center;
				margin-right: 0;
			}
		}

		&.cell-hover {
			background: #fafafa;
		}

		&.b-b:after {
			left: 30upx;
		}

		&.m-t {
			margin-top: 16upx;
		}

		.cell-tit {
			flex: 1;
			font-size: 28upx + 2upx;
			color: #303133;
			margin-right: 10upx;
		}

	}
</style>
