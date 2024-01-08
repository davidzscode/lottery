<template>
	<view class="box">
		<nav-bar :title="'APP下载'" :back="true"></nav-bar>
		<!-- 下载页 -->
		<view class="page">
			<view class="header">
				<image :src="appLogo" mode=""></image>
				<view>
					<view>
						{{appName}}
					</view>
					<view>
						{{appDes}}
					</view>
				</view>
			</view>
			<view class="bg">

			</view>
			<view class="content">
				<image src="@/static/images/download/content.png" mode=""></image>
			</view>
			<view class="btns">
				<view class="fang1">
					<view>
						1、下载并安装APP(推荐)
					</view>
					<view>
						<u-button text="下 载" @click="toLink" color="#9A3B86" style="width: 100px;"></u-button>
					</view>
				</view>
				<view class="fang1" style="margin-top: 20px;">
					<view>
						2、在浏览器中复制链接（进入网页版）
						<u-link v-if="domain.length>0" :href="domain[0].appUrl" :text="domain[0].appUrl" style="margin-top: 20px;"></u-link>
					</view>
					<view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		domainQuery
	} from '@/api/domain.js'
	export default {
		data() {
			return {
				appLogo: '', //app logo图片路径
				appName: '', //app名称
				appDes: '专业的竞彩赛事分析平台', //app简述
				domain:[]
			}
		},
		onLoad() {
			let shop=uni.getStorageSync("shop");
			this.appName=shop.name
			this.appLogo=shop.logo
			this.init();
		},
		methods: {
			toLink(){
				uni.showLoading()
				setTimeout(() => {
					location.href = this.domain[0].downloadUrl
					uni.hideLoading();
				}, 500)
			},
			init(){
				domainQuery().then(res=>{
					this.domain=res.voList
				})
			}
		}
	}
</script>

<style lang="less">
	@BgColor: #9A3B86; //主题背景色
.box {
    padding-top: 0px;
}
	page {
		background-color: #f8f8f8;
	}

	.page {
		.header {
			width: 100vw;
			height: 350upx;
			background-color: @BgColor;
			padding-top: 155upx;
			display: flex;
			justify-content: center;

			image {
				width: 120upx;
				height: 120upx;
				margin-right: 30upx;
			}

			>view {
				height: 120upx;
				color: #fff;
				font-weight: 700;
				display: flex;
				flex-direction: column;
				justify-content: center;

				view:nth-child(1) {
					font-size: 34upx;
				}

				view:nth-child(2) {
					font-size: 28upx;
				}
			}
		}

		.bg {
			width: 100vw;
			height: 210upx;
			background-image: url(@/static/images/download/bg.png);
			background-size: 100%;
			background-position: center bottom;
			background-repeat: no-repeat;
			position: absolute;
			left: 0;
			top: 300upx;
		}

		.content {
			width: 750upx;
			height: 750upx;
			position: absolute;
			top: 50%;
			left: 0upx;
			transform: translateY(-54%);
			overflow: hidden;

			image {
				width: 750upx;
				height: 750upx;
			}
		}

		.btns {
			color: #999;
			font-size: 16px;
			width: 100vw;
			height: 300upx;
			position: absolute;
			bottom: 3%;
			left: 0;
			.fang1{
				display: flex;
				align-items: center;
				justify-content: space-around;
			}
		}
	}
</style>
