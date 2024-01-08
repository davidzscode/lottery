<template>
	<view class="box">
		<!-- <cmd-nav-bar :title="'介绍'" :back="true" font-color='#000' background-color='#FFF'></cmd-nav-bar> -->
		<hx-navbar :config="{title: '流程介绍', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		<view class="image_wrapper photo">
			<image class="img" src="/static/images/introduce/introduceBg.jpg" mode="aspectFit" style="width: 100%; height: 100%;"></image>
		</view>
		<video class="my_video"
			src="https://static.sporttery.cn/res_1_0/tcw/upload/202307/rang yundong chushoukeji.mp4"></video>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {

		}
	}
</script>

<style>
	.box {
		/* padding: 5px 5upx; */
		position: relative;
		background: #55ffff;
		height: 100vh;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
		/* background: linear-gradient(to bottom, #9A3B86, #ffffff); */
		background-color: aliceblue;
	}

	.photo {
		background-image: url('/static/images/home/introduce.png');
		background-size: 89%; // 背景图片等比例缩放并铺满整个页面
		background-repeat: no-repeat; // 不重复平铺背景图片
		background-position: center center; // 背景图片居中显示
	}

	.image_wrapper {
		width: 100%;
		height: 74%;
		margin: 0 auto;
		.img {
			width: 100%;
			height: 100%;
		}
	}

	.my_video {
		width: 89%;
		height: 20%;
	}
</style>