<template>
	<view >
		<nav-bar :title="'官方开奖结果'" :back="true" style="z-index: 999;"></nav-bar>
		        <!-- <u-navbar :height="$navHeight" class="navbar" title-color="white" back-icon-color="white" :is-back="true" :title="title" -->
		         <!-- :background="navbg" :border-bottom="false" :title-width="650"></u-navbar> -->
		<!-- <nav-bottom :current="1"></nav-bottom> -->
			<!-- <web-view src="https://m.310win.com/Trade/DrawInfo/DrawInfoMain.aspx?type=1"></web-view> -->
			<!-- <div class="overlay"></div> -->
		<web-view style="margin-top: 0%;" ref="webView" :src="kaijiangUrl" @message="handleWebViewLoad"></web-view>

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
			this.kaijiangUrl = `/hybrid/html/remen.html`;
        },
		onReady() {
			// this.setWebview(40);
			    // this.$nextTick(() => {
			    //   const webView = this.$refs.webView;
			    //   const contentHeight = webView.getContentHeight(); // 获取 WebView 内容的高度
			    //   webView.setHeight(contentHeight); // 设置 WebView 的高度，确保内容在遮罩层下方显示
			    // });
				this.setWebviewHeight()
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
				  height = sysinfo.windowHeight - 65; //自行修改，自己需要的高度
				},
			  });
			  const currentWebview = this.$scope.$getAppWebview(); //获取当前web-view
			  setTimeout(function() {
				  const wv = currentWebview.children()[0];
				  wv.setStyle({
				  // top: 92, //设置web-view距离顶部的距离以及自己的高度，单位为px
				  height: height
				});
			  }, 100); //如页面初始化调用需要写延迟
			},
			    handleWebViewLoad(event) {
			      // 在加载完成时，检查加载的URL是否需要拦截
			      const webViewContext = event.detail.webViewContext;
			      const currentUrl = webViewContext.src;
			      
			      // 进行URL拦截的判断
			      if (currentUrl === 'https://m.310win.com/Trade/DrawInfo/DrawInfoMain.aspx') {
			        // 需要拦截的URL，可以在这里处理拦截逻辑
			        // 例如显示提示信息，或者跳转到其他页面
			        uni.showToast({
			          title: '此URL已被拦截',
			          icon: 'none'
			        });
			        
			        // 停止加载页面
			        webViewContext.stop();
			        
			        // 可选：跳转到其他页面
			        // uni.navigateTo({
			        //   url: '/pages/other-page.vue'
			        // });
			      }
			    }
		},
	}
</script>

<style lang="scss">
	
	uni-web-view.uni-webview--fullscreen {
		margin-top: 3rpx;
		margin-bottom: 146rpx;
	}
	
</style>
