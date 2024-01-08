<template>
	<view class="box">

		<nav-bar :title="'日志管理'" :back="true"></nav-bar>
		<view>
			<u-search @search="init" shape="square" margin="16px" height="40" animation :showAction="false"
				@clear="init" placeholder="请输入手机号" v-model="queryParam.phone"></u-search>
		</view>
		<view class="table">
			<u-empty icon="http://cdn.uviewui.com/uview/empty/data.png" :show="dataList.length<=0" mode="data"
				text="暂无日志"></u-empty>
			<uni-card v-for="(item,index) in dataList" :key="index" :border="true" :title="item.nickname"
				:style="[{animation: 'show ' + ((index+1)*0.2+1) + 's 1'}]"
				:sub-title="item.createTime|formatDate(that)" :thumbnail="item.avatar">
				<view>手机号：<span class="uni-body font">{{item.phone}}</span></view>
				<view>操作方式：<span class="uni-body">{{item.descriptor}}</span></view>
			</uni-card>
		</view>
	</view>
</template>


<script>
	import {
		agentLogList
	} from '@/api/log.js'
	export default {
		data() {
			return {
				that: this,
				queryParam: {
					phone: "",
					pageNo: 1,
					pageSize: 10,
				},
				dataList: [],
			}
		},
		filters: {
			formatDate(data, that) {
				if (null == data) {
					return null;
				}
				return that.globalUtil.dateTimeFormat(data)
			},
		},
		//滚动到底部进行分页事件
		onReachBottom() {
			if (this.dataList.length < this.queryParam.pageNo * 10) {
				return;
			}
			this.queryParam.pageNo++;
			this.init("loading");
		},
		methods: {
			init(type) {
				uni.showLoading();
				agentLogList(this.queryParam).then(res => {
					if (type === 'loading') {
						this.dataList = [...this.dataList, ...res.voList]
					} else {
						this.dataList = res.voList;
					}
					uni.hideLoading();
				})
			},
		},
		onLoad() {
			this.init()
		},
		onPullDownRefresh() {
			this.init();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
		},
	}
</script>

<style lang="scss" scoped>
	/deep/.uni-card__header-extra-text {
		font-size: 14px !important;
		color: #9A3B86 !important;
	}

	.box {
		padding-bottom: 0px;
	}

	.font {
		color: #9A3B86;
	}

	/deep/.uni-date-editor--x {
		width: 88% !important;
		float: right;
	}

	/deep/.uni-date-editor--x .uni-date__icon-clear {
		position: absolute !important;
		right: 0px !important;
	}

	/deep/.uni-datetime-picker--btn,
	/deep/.uni-calendar-item--after-checked,
	/deep/.uni-calendar-item--before-checked {
		background: #9A3B86 !important;
	}
</style>