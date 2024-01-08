<template>
	<view class="my">
		<view class="wrap wrap-fixed l-full l-flex-column">
			<view>
				<header id="uiHead" class="ui-head">
					<view class="ui-head-in">
						<view class="ui-head-l">
							<span class="ui-head-btn1" @click="navClick('/pages/home/home')"></span>
						</view>
						<view class="ui-head-m">
							<h2 class="ui-head-tit" style="font-size: 18.72px;">文章详情</h2>
						</view>
					</view>
				</header>
			</view>
			
			<!-- 标题部分 -->
			<view class="news-detail l-flex-1 l-relative">
				<view class="l-full l-scroll-y">
					<view id="slidediv">
						<view class="art-tit">
							苏克说球：连中14场+32中27！长线稳定！英超西甲信心2场，附比分
						</view>
						<view class="art-subtit">
							<span class="art-subtit-publishtime">2023-12-05 15:11:41</span>
							<span class="art-subtit-hits">77</span>
						</view>
					</view>
					<!-- 头像部分 -->
					<view class="zj-list zjwz-info">
						<ul>
							<li class="l-flex-row expertlist-row">
								<view class="icon-pro zjwz-user-l">
									<img src="data:image/webp;base64,UklGRsYBAABXRUJQVlA4ILoBAAAQDACdASpkAGQAPpFInkslpKKhppgIaLASCWcAVylcQLZcKS8obH/e/e85ad1CGyV8JGYngxH5V3B4tzTVbL6wqDRv9Q+H9nU9pXhMYXmQoB6L1zrREtYGv/rxpaH9lM+FAqtmxTOFI4AA/vooxFu9fUKWlFWHka8fr/A+cSUttMIvLY7rMCXMVwCxzNecbpySPVrO5sP7glyA4uAiBFfVqss+hascpyU818pJRzxxDqDHfLVAh7tUs+bHxoB+tnl8WkIQo7Ozzx/lwr8+wPjs9QkUYVE9qdXpjlag31OYqSfqEHOSTfk7vKY/VIjGJ8ow0z2IQjuo14zWwgPEvcl3cZz/oFCdthwokvDm702fm2i2XfH0dM5hc6GhDc+IzWidbSSoy2T9aFflDEZc9dyprhqqTKioWffgVMQti6HYqlw97uc4UP/IzdXaLJmf7UQOlrxj44fiUPJ2DRvR7+hfajEwBz9xkSKeYAlIztGwMCQQpd7iPxfYrM1fmJjRU4eiygM0MAl/w12hxKmAUaB4U4wS6bC2ouWRocgqxYZ8tS7VUVaFzOUDjVAlt2iXqZ1Wbklsw84ioAAA"
										alt="" />
								</view>
								<view class="zjwz-user-r l-flex-1 l-flex-column">
									<p>苏克说球</p>
									<view class="zj-mzl-box">
										<view class="zj-mzl">
											连中14场
										</view>
										<view class="zj-mzl">
											近7单+653%
										</view>
			
									</view>
			
								</view>
			
							</li>
						</ul>
			
					</view>
			
			
					<!-- 文章部分 -->
					<view class="article-body">
						<view class="pay-before-area">
							<view class="jcsq-box">
								<div class="tit">
									<span>赛事</span>
									<span>对阵</span>
									<span>推荐玩法</span>
								</div>
								<div class="game">
									<span>
										<em>
											周六009
											<br />
										</em>
										12-16 23:00
										<br />
										英超
									</span>
									<span>切尔西 VS 谢菲联</span>
									<span>胜平负/让球</span>
								</div>
								<div class="game">
									<span>
										<em>
											周六018
											<br />
										</em>
										12-17 04:00
										<br />
										西甲
									</span>
									<span>巴伦西 VS 巴  萨</span>
									<span>胜平负/让球</span>
								</div>
							</view>
						</view>
			
			
						<view class="art-txt-wrap">
							<view class="art-txt-tit">
								公开区
								<span class="btn-artclose">
									收起
								</span>
							</view>
			
							<view class="art-txt">
								<p>切尔西这个赛季表现相当糟糕，虽然大范围伤病是一个重要因素，还有就是目前这批阵容偏年轻，赛事经验不算很足，而波切蒂诺始终无法调教好球队的攻防，如今锋线恩昆库有机会复出，对于切尔西而言进攻端有较大的提升，加上帕尔默以及斯特林在边路的创造力，切尔西想要获得欧战资格，需要在弱旅身上拿到该拿的分数。</p>
								<p>周三001场&nbsp;亚冠&nbsp;武汉三镇&nbsp;VS&nbsp;浦项制铁</p>
								<p>武汉三镇是上赛季中场冠军，球队本赛季表现一般，最终排名联赛第7名。武汉三镇火力不过，加纳前锋雅库布打进15球，巴西的戴维斯打进6球，但是球队防守一般。亚冠球队1胜1平3负，落后第二名浦和红钻3分，出线基本没有希望，毕竟浦和最后一场对阵实力最弱的河内。武汉三镇主场2:2浦和，2:1河内，三个客场都输球，1:3浦项，1:2河内，1:2浦和。
								</p>
								<p>周三007场&nbsp;英超&nbsp;水晶宫&nbsp;VS&nbsp;伯恩茅斯</p>
								<p>水晶宫上赛季排名联赛第11名，本赛季，球队4胜4平6负，目前积16分排名第13名，其中主场1胜2平3负，主场只有5分排名第16名。球队上轮客场1:1西汉姆，结束联赛两连败，不过球队近期主场4场不胜，主场表现欠佳。
								</p>
							</view>
						</view>
			
						<view class="art-txt-wrap">
							<view class="art-txt-tit">
								解锁区
							</view>
							<view class="art-txt-pay">
								<span class="pay-slogan">14连中+32中27！长线稳定！英超西甲</span>
								<p>距比赛时间:<span>03:50:07</span></p>
							</view>
						</view>
						<p class="bbzq">观点建议仅供参考 / 解锁遇到问题？</p>
					</view>
			
				</view>
			
			
				<section class="btn-pay-wrap">
					<p class="btn-pay-state">
						<span>仅提供观点参考而非购彩，购彩请往彩票店</span>
					</p>
					<view class="btn-pay-left">
						<view class="price">
							<span class="IconMiZuan"></span>
							<em>68.00</em>
						</view>
						<view class="pay-agreement">
							购买即同意
							<em>《付费阅读协议》</em>
						</view>
					</view>
					<view class="btn-pay">
						<span>等待更新</span>
						<p class="pay-time">
							<!-- <span>03:25:22</span> -->
							<span>预计1月15日上线</span>
						</p>
					</view>
				</section>
			</view>
		</view>

		
	
	
	
	</view>





</template>


<script>
	export default {
		name: 'Article',
		data() {
			return {

			};
		},
		methods: {
			navClick(url) {
				uni.navigateTo({
					url: url,
				})
			},
		}
	}
</script>

<style scoped>
	.my {
		font-size: 39px;
		width: 100%;
		height: 100%;
	}

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
		font-weight: 400
	}

	li,
	ul {
		list-style: none
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


	.wrap,
	.wrap_home {
		height: 100%;
		overflow: hidden;
		background: #f4f4f4;
		font-size: 1em;
	}

	.wrap {
		position: relative;
		margin: 0 auto;
		width: 10em
	}

	.wrap-fixed {
		overflow-x: hidden;
		overflow-y: auto;
		-webkit-overflow-scrolling: touch;
	}


	.l-full {
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0
	}

	.l-flex-column {
		display: flex;
		flex-flow: column;
		height: 100%
	}

	.l-scroll-y {
		overflow: auto;
		-webkit-overflow-scrolling: touch
	}

	.ui-head {
		z-index: 9
	}
	
	
	.wrap-fixed .ui-head-in {
	    position: relative;
	    left: 0;
	    top: 0;
	}


	.ui-head,
	.ui-head-in {
		width: 100%;
		height: 1.17333em;
		position: relative
	}

	.ui-head-in {
		background: #fff !important;
		overflow: hidden
	}

	.ui-head-l,
	.ui-head-m {
		height: 100%;
		text-align: center;
	}

	.ui-head-l {
		position: absolute;
		top: 0;
		left: 0;
		width: .93333em;
		z-index: 5
	}

	.ui-head .ui-head-btn1:after {
		position: absolute;
		top: 50%;
		background: url(@/static/images/article/oldIcon.png) no-repeat -3.42667em -.53333em;
		width: .32em;
		height: .50667em;
		content: "";
		-webkit-transform: translateY(-50%);
		transform: translateY(-50%);
		background-size: 3.84em
	}

	.ui-head-m {
		line-height: 1.17333em;
		height: 1.17333em;
	}

	.ui-head-tit {
		display: block;
		margin: 0;
		padding: 0;
		font-size: .48em;
		font-weight: 700;
		color: #111 !important
	}



	/* 标题 */

	.news-detail {
		width: 100%;
		background: #f4f4f4;
		overflow-x: hidden
	}

	.l-flex-1 {
		-webkit-box-flex: 1;
		-webkit-flex: 1;
		flex: 1;
		overflow: hidden
	}

	.l-relative {
		position: relative
	}



	.art-tit {
		font-size: .50667em;
		color: #111;
		line-height: 1.5;
		text-align: justify;
		background: #fff;
		padding: 1rem 1rem 0;
		min-height: .66667em;
		font-weight: 700;
		word-break: break-all;
	}

	.art-subtit {
		color: #a0a8ac;
		font-size: 12.48px;
		height: 15.594px;
		line-height: 15.594px;
		background: #fff;
		padding: 10.4px 15.6px 15.6px;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		justify-content: space-between;
	}

	.art-subtit span {
		position: relative;
		padding-left: 1rem
	}

	.art-subtit span:before {
		content: "";
		display: block;
		position: absolute;
		left: 0;
		top: 50%;
		-webkit-transform: translateY(-50%);
		transform: translateY(-50%);
		width: 15.5938px;
		height: 13.5156px;
		background: url(/static/images/article/allIcon.png) no-repeat;
		background-size: 312px;
		background-position: -114.4px -20.7999px
	}

	.art-subtit span.art-subtit-hits:before {
		background-position: -7.125rem 0
	}

	/* 头像部分 */


	.news-detail .zj-list {
		margin-top: 0;
		margin-bottom: .324rem;
		padding: .65rem 0 .48rem
	}

	.zj-list {
		padding-top: 0;
		margin-top: .26667rem;
		position: relative
	}

	.zjwz-info {
		margin-bottom: .324rem;
		background: #fff;
		min-height: 1.14667rem
	}



	.zjwz-info:before {
		content: "";
		position: absolute;
		top: 0;
		left: .975rem;
		right: .975rem;
		height: 1px;
		background: #dedede
	}

	.zj-list ul {
		padding: 0
	}

	.l-flex-column,
	.l-flex-row {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex
	}

	.l-flex-row {
		-webkit-box-orient: horizontal;
		-webkit-flex-flow: row;
		flex-flow: row;
		width: 100%;
		margin: .26667rem 0 .53333rem
	}

	.zj-list ul li {
		position: relative;
		height: 2.66rem
	}

	.zjwz-info ul li {
		padding: 0
	}

	.news-detail .expertlist-row {
		margin: 0
	}




	.icon-pro {
		width: 2rem;
		line-height: 1.33333rem;
		margin-bottom: .13333rem;
		position: relative
	}

	.icon-pro,
	.icon-pro img {
		height: 1.33333rem;
		-webkit-border-radius: 50%;
		border-radius: 50%
	}

	.icon-pro img {
		width: 1.33333rem;
		position: absolute;
		top: 50%;
		margin-top: -.66667rem;
		left: 50%;
		margin-left: -.66667rem;
		-webkit-box-shadow: 0 0 .13333rem #ccc;
		box-shadow: 0 0 .13333rem #ccc
	}

	.zjwz-user-l img {
		-webkit-box-shadow: 0 0 0 #fff;
		box-shadow: 0 0 0 #fff
	}

	.zjwz-user-l {
		width: 1.33333rem;
		float: left;
		position: relative;
		margin-bottom: 0
	}

	.zjwz-user-l,
	.zjwz-user-l img {
		height: 2.599rem;
		-webkit-box-sizing: border-box;
		box-sizing: border-box
	}

	.zjwz-user-l img {
		width: 1.06667rem;
		-webkit-border-radius: 100%;
		border-radius: 100%;
		position: absolute;
		top: 50%;
		margin-top: -.53333rem;
		left: 0;
		margin-left: 0;
		padding: 0.13rem;
		border: 1px solid #ff1e13
	}

	.zj-list .zjwz-user-l img {
		left: 1rem
	}


	.zj-list .zjwz-user-l {
		padding-left: .975rem;
		width: 1.73333rem
	}

	.news-detail .zjwz-user-l {
		width: 4.2243rem
	}

	.news-detail .zjwz-user-l img {
		width: 2.599rem;
		height: 2.599rem;
		margin-top: -1.3rem
	}


	.l-flex-column {
		-webkit-box-orient: vertical;
		-webkit-flex-flow: column;
		flex-flow: column;
		height: 100%;
	}

	.l-flex-column,
	.l-flex-row {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
	}

	.l-flex-1 {
		-webkit-box-flex: 1;
		-webkit-flex: 1;
		flex: 1;
		overflow: hidden
	}

	.zjwz-user-r {
		position: relative;
		height: 2.599rem
	}

	.zj-list .zjwz-user-r {
		overflow: visible;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-pack: center;
		-webkit-justify-content: center;
		justify-content: center;
		-webkit-box-orient: vertical;
		-webkit-box-direction: normal;
		-webkit-flex-direction: column;
		flex-direction: column
	}

	.zjwz-user-r p {
		font-size: .32rem;
		color: #a0a8ac;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		line-height: normal
	}

	.zjwz-user-r p:first-child {
		font-size: .975rem;
		color: #111;
		width: 100%;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis
	}

	.zjwz-user-r p:nth-child(2) {
		margin-top: .13333rem
	}

	.zjwz-user-r .zj-mzl-box {
		margin-top: .325rem;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center
	}

	.mzl-icon3,
	.zj-mzl {
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		-webkit-box-pack: center;
		-webkit-justify-content: center;
		justify-content: center;
		line-height: normal
	}

	.zj-mzl {
		position: relative;
		display: -webkit-inline-box;
		display: -webkit-inline-flex;
		display: inline-flex;
		padding: 0 .195rem;
		height: .78rem;
		color: #ff6f68;
		font-size: .5193rem;
		border: 1px solid #ff6f68;
		-webkit-border-radius: .32rem;
		border-radius: .32rem
	}

	.zj-mzl:first-child {
		-webkit-border-radius: 0 .78rem .78rem .78rem;
		border-radius: 0 .78rem .78rem .78rem
	}

	.expertlist-row .zj-mzl {
		margin-right: .325rem
	}

	.article-body {
		background: #fff;
		min-height: 6rem;
		padding-bottom: 2.66667rem
	}

	@supports (bottom: constant(safe-area-inset-bottom)) or (bottom:env(safe-area-inset-bottom)) {
		.btn-pay-wrap {
			padding-bottom: env(safe-area-inset-bottom)
		}

		.article-body {
			padding-bottom: calc(env(safe-area-inset-bottom) + 6.48375rem);
		}
	}

	.pay-after-area,
	.pay-before-area {
		overflow: hidden
	}

	.jcsq-box {
		width: 22.424rem;
		-webkit-border-radius: .324rem;
		border-radius: .324rem;
		border: 1px solid #c6ced2;
		margin: .875rem auto;
		font-size: .78rem;
		text-align: center
	}

	.jcsq-box>div {
		height: 2.274rem;
		border-bottom: 1px solid #c6ced2;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		justify-content: space-evenly;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		color: #a0a8ac
	}

	.jcsq-box>div span:first-child {
		width: 24%;
		line-height: 1.17rem
	}

	.jcsq-box>div span:nth-child(2) {
		line-height: 2.274rem;
		width: 40%;
		border-left: 1px solid #c6ced2
	}

	.jcsq-box>div span:nth-child(3) {
		width: 22%;
		line-height: 2.274rem
	}

	.jcsq-box>div.game {
		height: 3.899rem;
		color: #333
	}

	.jcsq-box>div.game span:nth-child(2),
	.jcsq-box>div.game span:nth-child(3) {
		line-height: 4.07rem;
	}

	.jcsq-box>div:last-child {
		border-bottom: 0
	}


	.art-txt-wrap {
		overflow: hidden
	}

	.art-txt-wrap .art-txt-tit {
		position: relative;
		width: 22.125rem;
		height: 2.209rem;
		line-height: 2.209rem;
		background: #f4f4f4;
		-webkit-border-radius: .13333rem;
		border-radius: .324rem;
		margin: 1rem auto;
		font-size: 1.10rem;
		font-weight: 700;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		padding-left: 1.25rem
	}

	.art-txt-wrap .art-txt-tit:before {
		content: "";
		position: absolute;
		width: .199rem;
		height: 0.97rem;
		left: .5rem;
		top: 50%;
		margin-top: -.43rem;
		background: #ff1e13
	}

	.art-txt-wrap .btn-artclose {
		position: absolute;
		right: 0;
		top: 0;
		line-height: 2.209rem;
		color: #a0a8ac;
		font-size: .909rem;
		font-weight: 400;
		padding: 0 1.625rem
	}

	.art-txt-wrap .btn-artclose:after {
		content: "";
		position: absolute;
		top: 50%;
		margin-top: -.29rem;
		right: .324rem;
		width: 0.974rem;
		height: 0.584rem;
		background: url(/static/images/article/allIcon.png) no-repeat;
		background-size: 19.5rem;
		background-position: -15.5625rem -1.3125rem;
		-webkit-transform: rotate(180deg);
		transform: rotate(180deg);
		-webkit-transition: .4s;
		transition: .4s
	}

	.news-detail .art-txt {
		color: #111;
		font-size: 1.104rem;
		text-align: justify;
		line-height: 1.884rem;
		padding: 0 .975rem;
		background: #fff
	}

	.news-detail .art-txt p {
		word-break: break-all;
		margin-bottom: .975rem
	}

	.art-txt-wrap .art-txt-pay {
		width: 22.424rem;
		height: 10.399rem;
		-webkit-border-radius: .13333rem;
		border-radius: .3249rem;
		margin: .974rem auto;
		background: url(/static/images/article/lock.png) no-repeat;
		background-size: 100% 100%;
		color: #fff;
		font-size: .975rem;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-orient: vertical;
		-webkit-box-direction: normal;
		-webkit-flex-direction: column;
		flex-direction: column;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		-webkit-box-pack: center;
		-webkit-justify-content: center;
		justify-content: center;
		line-height: 2
	}

	.art-txt-wrap .art-txt-pay .pay-slogan {
		color: #ffd24a
	}

	.art-txt-wrap .art-txt-pay .pay-slogan:before {
		content: "- "
	}

	.art-txt-wrap .art-txt-pay .pay-slogan:after {
		content: " -"
	}

	.bbzq {
		font-size: .78rem;
		color: #a0a8ac;
		line-height: 2;
		width: 100%;
		text-align: center;
		margin-top: .65rem
	}

	.btn-pay-wrap {
		position: fixed;
		bottom: 0;
		left: 50%;
		width: 24.375rem;
		margin-left: -12.1875rem;
		height: 3.8349rem;
		background: #fff;
		z-index: 99;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		justify-content: space-between
	}

	.btn-pay-wrap:before {
		content: "";
		position: absolute;
		left: 0;
		top: 0;
		width: 100%;
		height: 3.8349rem;
		background: #ffebe8;
		z-index: 1
	}

	.btn-pay-wrap .btn-pay-state {
		position: absolute;
		left: 0;
		top: -1.95rem;
		width: 100%;
		height: 1.949rem;
		background: #fff;
		font-size: .78rem;
		color: #e3b439;
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		line-height: normal;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		padding: 0 .975rem;
		z-index: 1
	}

	.btn-pay-wrap .btn-pay-state:before {
		content: "";
		width: .909rem;
		height: .909rem;
		display: block;
		background: url(/static/images/article/allIcon.png) no-repeat;
		background-size: 19.5rem;
		background-position: -16.875rem -1.25rem;
		margin-right: .195rem
	}

	.btn-pay-wrap .btn-pay-left {
		position: relative;
		z-index: 2;
		font-size: 1.235rem;
		color: #ff1e13;
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
		padding-left: 0.975rem
	}

	.btn-pay-wrap .btn-pay-left .price {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		margin-bottom: .325rem;
		line-height: 1
	}


	.IconMiZuan {
		display: inline-block;
		width: 1.1045rem;
		height: 0.8115rem;
		background: url(data:image/png;base64,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) no-repeat;
		background-size: 100% 100%
	}

	.btn-pay-wrap .btn-pay-left .price .IconMiZuan {
		margin-right: 0.195rem
	}

	.btn-pay-wrap .btn-pay-left .pay-agreement {
		font-size: 0.78rem;
		color: #a0a8ac
	}

	.btn-pay-wrap .btn-pay-left .pay-agreement:before {
		content: "";
		width: 0.7793125rem;
		height: 0.7793125rem;
		color: #a0a8ac;
		display: inline-block;
		background: url(/static/images/article/allIcon.png) no-repeat;
		background-position: 0 -1.625rem;
		background-size: 19.5rem;
		position: relative;
		top: 0.13rem;
		margin-right: 0.26rem
	}

	.btn-pay-wrap .btn-pay {
		position: relative;
		z-index: 2;
		width: 9.229rem;
		/* background: -webkit-gradient(linear, left top, right top, from(#ff5547), to(#ff1e13)); */
		/* background: linear-gradient(90deg, #ff5547, #ff1e13); */
		background: linear-gradient(90deg, #919191, #636363);
		color: #fff;
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
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		font-size: 1.235rem
	}

	.btn-pay-wrap .btn-pay .pay-time {
		font-size: 0.78rem
	}

	.btn-pay-wrap .btn-pay-left .pay-agreement em {
		color: #333
	}
</style>
