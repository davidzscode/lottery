<template>
	<view class="box">
		<view class="status_bar"></view>
	</view>
</template>

<script>
	import {
		ranking,
		queryDocumentaryByType,
	} from '@/api/documentary.js'
	export default {
		data() {
			return {
				tabsList: [{
					name: '人气跟单',
				}, {
					name: '跟单总额',
				}, {
					name: '我的关注',
				}],
				rankingData: {},
				documentaryData: [],
				type: 0
			}
		},
		methods: {
			documentaryInfoBtn(documentaryId, userId) {
				uni.navigateTo({
					url: "/pages/documentary/documentaryInfo?id=" + documentaryId + "&uid=" + userId
				})
			},
			iconOneBtn() {
				uni.navigateTo({
					url: "/pages/documentary/search"
				})
			},
			onClickBtn(data){
				uni.navigateTo({
					url: "/pages/documentary/documentaryhelp"
				})
			},
			init() {
				uni.showLoading();
				ranking().then(res => {
					this.rankingData = res;
				})
				queryDocumentaryByType(this.type).then(res => {
					this.documentaryData = res.voList;
					uni.hideLoading();
				})
			},
			tabsBtn(item) {
				this.type = item.index;
				this.init();
			},
			queryInfo(id) {
				uni.navigateTo({
					url: "/pages/documentary/homePage?uid=" + id
				})
			}
		},
		onLoad() {
			this.init();
		},
		onPullDownRefresh() {
			this.init();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
		},
		onTabItemTap(e) {
		    // tab 点击时执行，此处直接接收单击事件
		    this.init();
		},
	}
</script>

<style scoped lang="less">
	/deep/.u-count-down__text {
		color: #9A3B86;
	}

	.img {
		width: 31px;
		height: 31px;
	}

	page {
		background: #FAFAFA;
		font-size: 15px;
		color: #333;
		font-family: Helvetica Neue, Helvetica, sans-serif;
	}

	.title {
		// color: rgb(255, 83, 89);
		color:#b480be;
		font-size: 11px;
	}

	/deep/.desc .uni-card__content {
		display: flex;
		flex-direction: row;
		align-items: center;
		padding: 10px 0px !important;
	}

	.topic {
		display: flex;
		align-items: center;
		flex-direction: column;
		justify-content: space-around;
		width: 20%;
	}

	/deep/.desc {
		border-radius: 10px;
		justify-content: space-around;
		margin: 5px 5px !important;
		padding: 0px !important;
	}

	.item-title {
		padding: 10px;
		background-color: white;
		margin-bottom: 10px;
	}
	
	.userInfo{
		display: flex;
		.statusOne{
			background-color: #f00702;
			color: #fff;
			padding: 4px;
			border-radius: 5px;
			font-size: 11px;
			margin-left: 5px;
		}
		.statusTwo{
			background-color: #fef0ee;
			color: #fb5b43;
			padding: 4px;
			border-radius: 5px;
			font-size: 11px;
			margin-left: 5px;
		}	
	}
</style>