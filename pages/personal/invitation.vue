<template>
	<view class="box">
		
		<nav-bar :title="'我的邀请'" :back="true"></nav-bar>
		<view>
			<u-search @search="init" shape="square" margin="16px" height="40" animation :showAction="false" @clear="init"
				placeholder="请输入用户昵称或者手机号" v-model="queryParam.param"></u-search>
		</view>
		<view class="invitation">
			<p v-if="userShow" style="font-size: 14px;">共邀请：<span class="font">{{registerUserList.length}}</span>个客户</p>
			<p v-else style="font-size: 14px;">总投注金额：<span class="font">{{total}}</span>元</p>
			<uni-datetime-picker type="daterange" @change="change" />
		</view>
		<view>
			<u-tabs activeStyle="color:#9A3B86;" :scrollable="false" lineWidth="45" lineColor="#9A3B86" :list="tabList"
				@click="selectBtn">
			</u-tabs>
		</view>
		<view class="table" v-if="userShow">
			<uni-table stripe emptyText="暂无更多数据">
				<!-- 表头行 -->
				<uni-tr>
					<uni-th align="center">昵称|手机号</uni-th>
					<uni-th align="center">注册时间</uni-th>
					<uni-th align="center" width="100">账户余额</uni-th>
				</uni-tr>
				<!-- 表格数据行 -->
				<uni-tr v-for="item in registerUserList" :key="item.id">
					<uni-td align="center">{{item.nickname}}<br>{{item.phone}}</uni-td>
					<uni-td align="center">{{item.createTime|formatDate(that)}}</uni-td>
					<uni-td align="center">{{parseFloat(item.gold+item.price).toFixed(2)}}</uni-td>
				</uni-tr>
			</uni-table>
		</view>

		<view class="table" v-if="orderShow">
			<uni-table stripe emptyText="暂无更多数据">
				<!-- 表头行 -->
				<uni-tr>
					<uni-th align="center">昵称|手机号</uni-th>
					<uni-th align="center">投注金额</uni-th>
					<uni-th align="center" width="100">详情</uni-th>
				</uni-tr>
				<!-- 表格数据行 -->
				<uni-tr v-for="item in bettingUserList" :key="item.id">
					<uni-td align="center">{{item.nickname}}<br>{{item.phone}}</uni-td>
					<uni-td align="center">{{item.bettingPrice}}</uni-td>
					<uni-td align="center">
						<span style="color: #2979FF;" @click="info(item)">详情</span>
					</uni-td>
				</uni-tr>
			</uni-table>
		</view>
		
	</view>
</template>


<script>
	import {
		agent
	} from '@/api/user.js'
	export default {
		data() {
			return {
				total: 0,
				that: this,
				queryParam: {
					param: "",
					startTime: "",
					endTime: ""
				},
				userShow: true,
				orderShow: false,
				registerUserList: [],
				bettingUserList: [],
				tabList: [{
					name: '注册数据',
				}, {
					name: '投注数据',
				}]
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
		methods: {
			selectBtn(item) {
				if (item.index == 0) {
					this.userShow = true;
					this.orderShow = false;
				} else if (item.index == 1) {
					this.orderShow = true;
					this.userShow = false;
				}
			},
			change(option) {
				this.queryParam.startTime = option[0];
				this.queryParam.endTime = option[1];
				this.init()
			},
			init() {
				uni.showLoading();
				this.registerUserList = [];
				this.bettingUserList = [];
				this.total = 0;
				agent(this.queryParam).then(res => {
					this.registerUserList = [...this.registerUserList, ...res.registerUserList]
					this.bettingUserList = [...this.bettingUserList, ...res.bettingUserList]
					this.bettingUserList.map(item => {
						this.total += item.bettingPrice
					})
					setTimeout(function() {
						uni.hideLoading();
					}, 500);
				})
			},
			info(item) {
				uni.navigateTo({
					url: "/pages/order/lotteryOrder?uid=" + item.id
				});
			},
		},
		onLoad() {
			this.init()
		}
	}
</script>

<style lang="scss" scoped>
	.box {
		padding-bottom: 0px;
	}

	/deep/.u-tabs__wrapper__nav__item__text {
		font-size: 16px !important;
	}

	.table {
		width: 98%;
		margin: auto;
		margin-top: 30px;
	}

	.invitation {
		margin: 15px;
		display: flex;
		align-items: center;
		justify-content: center;
		margin-bottom: 30px;

		.font {
			color: #9A3B86;
		}
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

