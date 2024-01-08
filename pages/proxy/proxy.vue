<template>
	<view class="box">
		<nav-bar :title="'代理管理'" :back="true"></nav-bar>
		<scroll-view scroll-y class="page">
			<image src="@/static/images/proxy/componentBg.png" mode="widthFix" class="response"></image>
			<view class="nav-list">
				<navigator hover-class='none' :url="'/pages/proxy/' + item.name" class="nav-li" navigateTo
					:class="'bg-'+item.color" :style="[{animation: 'show ' + ((index+1)*0.2+1) + 's 1'}]"
					v-for="(item,index) in elements" :key="index">
					<view class="nav-title">{{item.title}}</view>
					<view class="nav-name">{{item.name}}</view>
					<u-icon class="icon" :name="item.cuIcon" size="31" color="#fff"></u-icon>
				</navigator>
			</view>
			<view class="cu-tabbar-height"></view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				elements: [{
						title: '用户管理',
						name: 'invitation',
						color: 'purple',
						cuIcon: 'account-fill'
					},
					{
						title: '日志管理',
						name: 'log',
						color: 'brown',
						cuIcon: 'chat-fill'
					},
				],
			};
		}
	}
</script>

<style scoped lang="scss">
	.box {
		padding-bottom: 0px;
	}

	.bg-brown {
		background-image: linear-gradient(45deg, #9A3B86, #7D39B4);
		color: #ffffff;
	}

	.bg-purple {
		background-image: linear-gradient(45deg, #E12070, #8D09CC);
		color: #ffffff;
	}

	.response {
		width: 100%;
	}

	uni-view,
	uni-scroll-view,
	uni-navigator,
	uni-image {
		box-sizing: border-box;
	}

	.nav-list {
		display: flex;
		flex-wrap: wrap;
		padding: 0px 40upx 0px;
		justify-content: space-between;
	}

	.nav-li {
		padding: 30upx;
		border-radius: 12upx;
		width: 45%;
		margin: 0 2.5% 40upx;
		/* background-image: url(https://i.imgtg.com/2022/12/15/Dsef6.jpg); */
		background-size: cover;
		background-position: center;
		position: relative;
		z-index: 1;
	}

	.nav-li::after {
		content: "";
		position: absolute;
		z-index: -1;
		background-color: inherit;
		width: 100%;
		height: 100%;
		left: 0;
		bottom: -10%;
		border-radius: 10upx;
		opacity: 0.2;
		transform: scale(0.9, 0.9);
	}

	.nav-li.cur {
		color: #fff;
		background: rgb(94, 185, 94);
		box-shadow: 4upx 4upx 6upx rgba(94, 185, 94, 0.4);
	}

	.nav-title {
		font-size: 26upx;
		font-weight: 300;
	}

	.nav-title::first-letter {
		font-size: 34upx;
		margin-right: 4upx;
	}

	.nav-name {
		font-size: 28upx;
		text-transform: Capitalize;
		margin-top: 20upx;
		position: relative;
	}

	.nav-name::before {
		content: "";
		position: absolute;
		display: block;
		width: 40upx;
		height: 6upx;
		background: #fff;
		bottom: 0;
		right: 0;
		opacity: 0.5;
	}

	.nav-name::after {
		content: "";
		position: absolute;
		display: block;
		width: 100upx;
		height: 1px;
		background: #fff;
		bottom: 0;
		right: 40upx;
		opacity: 0.3;
	}

	.nav-name::first-letter {
		font-weight: bold;
		font-size: 36upx;
		margin-right: 1px;
	}

	.icon {
		position: absolute;
		right: 30upx;
		top: 30upx;
		font-size: 52upx;
		width: 60upx;
		height: 60upx;
		text-align: center;
		line-height: 60upx;
	}

	.text-light {
		font-weight: 300;
	}
</style>
