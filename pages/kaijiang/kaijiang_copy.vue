<template>
	<view >
		<nav-bar :title="'官方开奖结果'" :back="true" style="z-index: 999;"></nav-bar>
		        <!-- <u-navbar :height="$navHeight" class="navbar" title-color="white" back-icon-color="white" :is-back="true" :title="title" -->
		         <!-- :background="navbg" :border-bottom="false" :title-width="650"></u-navbar> -->
		<!-- <nav-bottom :current="1"></nav-bottom> -->
			<!-- <web-view src="https://m.310win.com/Trade/DrawInfo/DrawInfoMain.aspx?type=1"></web-view> -->
			<div class="overlay"></div>
			<web-view ref="webView" :src="kaijiangUrl"></web-view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				kaijiangUrl: '',
			}
		},
		onLoad(options) {
			// setWebview(40); // 调用,假设导航栏高度为40，传入40即可
			this.kaijiangUrl = `https://m.310win.com/Trade/DrawInfo/DrawInfoMain.aspx?type=1`;
			this.setWebviewHeight()
        },
		onReady() {
			// this.setWebview(40);
			    this.$nextTick(() => {
			      const webView = this.$refs.webView;
			      const contentHeight = webView.getContentHeight(); // 获取 WebView 内容的高度
			      webView.setHeight(contentHeight); // 设置 WebView 的高度，确保内容在遮罩层下方显示
			    });
		},
		onShow() {
			// setTimeout(()=>{
			// 	const subNVue = uni.getCurrentSubNVue();
			// 	subNVue.show()
			// },800)
		},
		
		methods: {
			// 自定义webview高度
			setWebviewHeight() {
			  let height = 0; //定义动态的高度变量，如高度为定值，可以直接写
			  uni.getSystemInfo({
				//成功获取的回调函数，返回值为系统信息
				success: sysinfo => {
				  height = sysinfo.windowHeight - 118; //自行修改，自己需要的高度
				},
			  });
			  const currentWebview = this.$scope.$getAppWebview(); //获取当前web-view
			  setTimeout(function() {
				  const wv = currentWebview.children()[0];
				  wv.setStyle({				
				  // top: 50, //设置web-view距离顶部的距离以及自己的高度，单位为px
				  height: height
				});
			  }, 500); //如页面初始化调用需要写延迟
			},
		},
	}
</script>

<style lang="scss">
	
	uni-web-view.uni-webview--fullscreen {
		margin-top: 3rpx;
		margin-bottom: 146rpx;
	}
	
	
	.overlay {
	  position: absolute;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 20%; /* 控制遮罩层的高度，裁剪 WebView 内容的上部分 */
	  background-color: white; /* 设置遮罩层颜色，可以根据需求调整 */
	  z-index: 999; /* 确保遮罩层位于最上层 */
	}
</style>
