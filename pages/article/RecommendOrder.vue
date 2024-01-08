<template>
	<uni-card is-shadow class="card">
		<view>
			<view class="uni-card__content">
				精选推单
			</view>
			<ul class="articlelist-wrap">
				<li class="article-item">
					<view class="top-content">
						<view class="expert">
							<!-- 头像 -->
							<img src="data:image/webp;base64,UklGRsYBAABXRUJQVlA4ILoBAAAQDACdASpkAGQAPpFInkslpKKhppgIaLASCWcAVylcQLZcKS8obH/e/e85ad1CGyV8JGYngxH5V3B4tzTVbL6wqDRv9Q+H9nU9pXhMYXmQoB6L1zrREtYGv/rxpaH9lM+FAqtmxTOFI4AA/vooxFu9fUKWlFWHka8fr/A+cSUttMIvLY7rMCXMVwCxzNecbpySPVrO5sP7glyA4uAiBFfVqss+hascpyU818pJRzxxDqDHfLVAh7tUs+bHxoB+tnl8WkIQo7Ozzx/lwr8+wPjs9QkUYVE9qdXpjlag31OYqSfqEHOSTfk7vKY/VIjGJ8ow0z2IQjuo14zWwgPEvcl3cZz/oFCdthwokvDm702fm2i2XfH0dM5hc6GhDc+IzWidbSSoy2T9aFflDEZc9dyprhqqTKioWffgVMQti6HYqlw97uc4UP/IzdXaLJmf7UQOlrxj44fiUPJ2DRvR7+hfajEwBz9xkSKeYAlIztGwMCQQpd7iPxfYrM1fmJjRU4eiygM0MAl/w12hxKmAUaB4U4wS6bC2ouWRocgqxYZ8tS7VUVaFzOUDjVAlt2iXqZ1Wbklsw84ioAAA"
								alt="" />
							<!--  姓名和一些战绩 -->
							<view class="expert-name-box">
								<span class="expert-name">苏克说球</span>
								<view class="expert-icon-box">
									<view class="expert-icon">
										连中14场
									</view>
									<view class="expert-icon">
										近7单+653%
									</view>
								</view>
							</view>
						</view>
						<view class="right-desc">
							<view class="right-desc-inner red">
								<view class="num">
									<span>100</span>
								</view>
								<view class="num-txt">
									<span>%</span>
									<span>命</span>
									<span>中</span>
								</view>
							</view>
						</view>
					</view>
					<view class="middle-content" @click="navClick('/pages/article/article1')">
						<view class="article-tit">
							<em class="articletit-icon">竞足</em>
							<span>苏克说球：连中14场+32中27！长线稳定！英超西甲信心2场，附比分</span>
						</view>
					</view>
					<view class="bottom-content">
						<view class="article-datum">
							<span class="time">15:00</span>
							<span class="view">498</span>
							<span class="type">2串1</span>
						</view>
						<view class="article-state">
							<view class="special-tag">
				
							</view>
							<view class="price">
								<span>88米钻</span>
							</view>
						</view>
					</view>
				</li>
				
				
				<li class="article-item">
					<view class="top-content">
						<view class="expert">
							<!-- 头像 -->
							<img src="data:image/webp;base64,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"
								alt="" />
							<!--  姓名和一些战绩 -->
							<view class="expert-name-box">
								<span class="expert-name">林岳看盘</span>
								<view class="expert-icon-box">
									<view class="expert-icon">
										连中9场
									</view>
									<view class="expert-icon">
										近8单+541%
									</view>
								</view>
							</view>
						</view>
						<view class="right-desc">
							<view class="right-desc-inner red">
								<view class="num">
									<span>100</span>
								</view>
								<view class="num-txt">
									<span>%</span>
									<span>命</span>
									<span>中</span>
								</view>
							</view>
						</view>
					</view>
					<view class="middle-content"  @click="navClick('/pages/article/article')">
						<view class="article-tit">
							<em class="articletit-icon">竞足</em>
							<span>9场连红，近16中15！今晚精析：都灵+塞尔塔，信心强烈，附18倍单！</span>
						</view>
					</view>
					<view class="bottom-content">
						<view class="article-datum">
							<span class="time">15:00</span>
							<span class="view">498</span>
							<span class="type">2串1</span>
						</view>
						<view class="article-state">
							<view class="special-tag">

							</view>
							<view class="price">
								<span>88米钻</span>
							</view>
						</view>
					</view>
				</li>
				
			</ul>
			<p class="no-more">没有了</p>
		</view>


	</uni-card>

</template>

<script>
	import $ from 'jquery'
	export default {
		name: "RecommendOrder",
		// mounted() {
		// 	let width = $("body").width();
		// 	if (width >= 1200) {
		// 		let fontsize = width / 1920 * 40;
		// 		$("html").css("font-size", `${fontsize}px`)
		// 	}
		// 	window.onresize = () => {
		// 		return (() => {
		// 			let width = $("body").width();
		// 			if (width >= 1200) {
		// 				let fontsize = width / 1920 * 40;
		// 				$("html").css("font-size", `${fontsize}px`)
		// 			}
		// 		})
		// 	}
		// }
		methods: {
			navClick(url) {
				console.log('11111111111111111')
				uni.navigateTo({
					url: url,
				})
			},
		}

	}
</script>

<style scoped>
	body,
	dd,
	dl,
	dt,
	fieldset,
	form,
	h1,
	h2,
	h3,
	h4,
	h5,
	h6,
	hr,
	html,
	input,
	label,
	li,
	ol,
	p,
	ul {
		margin: 0;
		padding: 0;
		font-weight: 400;
	}

	a {
		text-decoration: none;
	}

	em,
	i,
	s,
	strong {
		font-style: normal;
	}

	li,
	ul {
		list-style: none;
	}

	body,
	html {
		position: relative;
		width: 100%;
		height: 100%;
	}

	.articlelist-wrap {
		overflow: hidden
	}

	.article-item {
		position: relative;
		margin: 0 .4rem;
		padding: .4rem 0;
		border-bottom: 1px solid #dedede
	}

	.article-item:last-child {
		border-bottom: 0
	}

	/* 上部 */
	.article-item .top-content {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		justify-content: space-between;
		margin-bottom: .7rem
	}

	/* 上左 */
	.article-item .expert {
		font-size: .9rem;
		color: #111
	}

	.article-item .bottom-content,
	.article-item .expert {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center
	}

	/* 头像 */
	.article-item .expert img {
		width: 2.5rem;
		height: 2.5rem;
		-webkit-border-radius: 100%;
		border-radius: 100%;
		margin-right: .24rem
	}

	/* 姓名部分 */
	.article-item .expert .expert-name-box {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-orient: vertical;
		-webkit-box-direction: normal;
		-webkit-flex-direction: column;
		flex-direction: column;
		-webkit-box-pack: center;
		-webkit-justify-content: center;
		justify-content: center;
		-webkit-box-align: start;
		-webkit-align-items: flex-start;
		align-items: flex-start
	}

	/* 战绩框 */
	.article-item .expert .expert-icon-box {
		margin-top: .2rem
	}

	.article-item .expert .expert-icon-box,
	.article-item .expert .expert-icon-box .expert-icon-tit {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		line-height: normal
	}

	.article-item .expert .expert-icon {
		margin-right: .35rem
	}

	.expert-icon:first-child {
		-webkit-border-radius: 0 .32rem .32rem .32rem;
		border-radius: 0 .32rem .32rem .32rem
	}

	.expert-icon {
		display: -webkit-inline-box;
		display: -webkit-inline-flex;
		display: inline-flex;
		color: #ff6f68;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		-webkit-box-pack: center;
		-webkit-justify-content: center;
		justify-content: center;
		height: .8rem;
		line-height: normal;
		padding: 0 .21rem;
		border: 1px solid #ff6f68;
		-webkit-border-radius: .32rem;
		border-radius: .32rem;
		font-size: .55rem
	}

	/* 上右 */
	.article-item .right-desc,
	.article-item .right-desc .right-desc-inner {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center
	}

	.article-item .right-desc .right-desc-inner.red {
		color: #ff1e13
	}

	.article-item .right-desc .num {
		font-size: 1.9rem;
		margin-right: .02667rem;
		font-family: Bebas;
		-webkit-box-pack: center;
		-webkit-justify-content: center;
		justify-content: center
	}

	.article-item .right-desc .num,
	.article-item .right-desc .num-txt {
		line-height: 1;
		height: 1;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-orient: vertical;
		-webkit-box-direction: normal;
		-webkit-flex-direction: column;
		flex-direction: column;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center
	}

	.article-item .right-desc .num-txt {
		font-size: .52rem;
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		justify-content: space-between
	}




	/* 中间 */

	.article-item .middle-content {
		margin-bottom: .7rem
	}



	.article-item .article-tit {
		font-size: 1rem;
		color: #111;
		line-height: 1.5rem;
		display: -webkit-box;
		height: 3rem;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		overflow: hidden;
		text-overflow: ellipsis;
		word-break: break-word;
		word-wrap: break-word
	}

	.articletit-icon {
		display: -webkit-inline-box;
		display: -webkit-inline-flex;
		display: inline-flex;
		color: #2d72ff;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		-webkit-box-pack: center;
		-webkit-justify-content: center;
		justify-content: center;
		height: 1.16rem;
		line-height: normal;
		padding: 0 .2rem;
		background: #d2e1ff;
		font-size: .83rem;
		-webkit-border-radius: .05333rem;
		border-radius: .05333rem;
	}

	.article-item .article-tit .articletit-icon {
		vertical-align: top;
		margin-top: .1rem;
	}


	/* 底部 */

	.article-item .bottom-content {
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		justify-content: space-between
	}

	.article-item .article-datum {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		line-height: normal;
		color: #a0a8ac;
		font-size: .7rem;
	}

	.article-item .article-datum .time,
	.article-item .article-datum .view {
		position: relative;
		padding-left: 1rem;
		margin-right: .7rem;
	}

	.article-item .article-state {
		-webkit-box-pack: end;
		-webkit-justify-content: flex-end;
		justify-content: flex-end
	}

	.article-item .article-datum .time::after,
	.article-item .article-datum .view::after {
		content: "";
		display: block;
		position: absolute;
		left: 0;
		top: 50%;
		-webkit-transform: translateY(-50%);
		transform: translateY(-50%);
		width: 16.5469px;
		height: 14.3438px;
		background: url(/static/images/article/allIcon.png) no-repeat;
		background-size: 331.2px;
		background-position: -121.44px -22.0799px;
	}

	.article-item .article-datum .view::after {
		background-position: -121.44px 0;
	}

	.article-item .article-datum .type::before {
		content: "";
		position: absolute;
		left: 0%;
		top: 40%;
		margin-top: -.10667rem;
		width: 1px;
		height: .5rem;
		background: #a0a8ac
	}

	.article-item .article-state,
	.article-item .special-tag {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center
	}

	.article-item .article-state .price {
		color: #ff1e13;
		margin-left: .26667rem
	}

	.article-item .article-datum .type {
		position: relative;
		padding-left: .7rem
	}


	.no-more {
		padding: 1rem 0 3.4rem
	}

	.intro-txt,
	.no-more {
		color: #a0a8ac;
		text-align: center;
		font-size: .8rem
	}
</style>
