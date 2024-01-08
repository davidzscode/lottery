<template>
	<view class="box">
		
			<!-- <nav-bar :title="'发起跟单'" :back="true"></nav-bar> -->
			<hx-navbar :config="{title: '发起跟单', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
			<view
				style="background-color: white; margin: 20px 15px; padding: 10px; border-radius: 10px; line-height: 40px;">
				<view
					style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid rgb(238, 238, 238);">
					<text><span>投注彩种</span></text>
					<text style="color: red;"><span>{{calculationParam.type=='0'?'竞彩足球':'竞彩篮球'}}</span></text>
				</view>
				<view style="display: flex; justify-content: space-between; align-items: center;">
					<text><span>订单金额</span></text>
					<text
						style="color: red;"><span>{{calculationParam.multiple * calculationParam.notes * 2}}元</span></text>
				</view>
			</view>
			<view
				style="background-color: white; margin: -10px 15px 0px; padding: 10px; border-radius: 10px; line-height: 40px;">
				<view style="display: flex; justify-content: space-between; align-items: center;">
					<text><span>是否公开</span></text>
					<u-radio-group v-model="documentaryParam.state">
						<u-radio activeColor="#9A3B86" :customStyle="{marginLeft: '20px'}" v-for="(item, index) in list"
							:key="index" :name="item.value" :label="item.name">
						</u-radio>
					</u-radio-group>
				</view>
				<view style="display: flex; justify-content: space-between; align-items: center;">
					<text><span>佣金</span></text>
					<u-radio-group v-model="documentaryParam.commission">
						<u-radio activeColor="#9A3B86" :customStyle="{marginLeft: '16px'}"
							v-for="(item, index) in stationList" :key="index" :name="item.value" :label="item.name">
						</u-radio>
					</u-radio-group>
				</view>
				<view style="width: 100%;">
					<u-textarea v-model="documentaryParam.describe" height="140"
						style="background-color: rgb(241, 241, 241); width: 100%; border-radius: 15px; padding: 5px;">
					</u-textarea>
				</view>
			</view>
			<view style="position: fixed; bottom: 10px; right: 5%; width: 90%;">
				<u-button color="#9A3B86" text="确认发单" style="border-radius: 10px;" @click="confirm"></u-button>
			</view>
		
	</view>
</template>

<script>
	import {
		createOrder,
	} from '@/api/racingball.js'
	import {
		createDocumentary,
		preOrder,
	} from '@/api/documentary.js'
	export default {
		data() {
			return {
				documentaryParam: {
					lotteryOrderId: "",
					state: "1",
					describe: "没有华丽的宣言,只有最稳的红单",
					commission: 5,
					maxPrice: 0, 
				},
				calculationParam: {
					basketballMatchList: [],
					footballMatchList: [],
					multiple: 0,
					pssTypeList: [],
					notes: "",
					type: "",
					forecast: "",
				},
				stationList: [{
					name: '2%',
					value: 2
				}, {
					name: '3%',
					value: 3
				}, {
					name: '4%',
					value: 4
				}, {
					name: '5%',
					value: 5
				}],
				list: [{
					name: "公开",
					value: "0"
				}, {
					name: "赛后可见",
					value: "1"
				}]
			}
		},
		onLoad(option) {
			let obj = JSON.parse(decodeURIComponent(option.obj));
			this.calculationParam = obj
			console.log(this.calculationParam.forecast);
			this.documentaryParam.maxPrice = this.calculationParam.forecast;
		},
		methods: {
			confirm() {
				if(this.calculationParam.multiple*this.calculationParam.notes*2<100){
					uni.showToast({
						title: '发布跟单最低100起投',
						icon: 'none'
					});
					return;
				}
				uni.showLoading();
				//先创建订单
				createOrder(this.calculationParam).then(res => {
					if (res.success) {
						//创建跟单数据
						this.documentaryParam.lotteryOrderId = res.id;
						createDocumentary(this.documentaryParam).then(response => {
							if (response.success) {
								//标识为已经下单了
								uni.setStorageSync('isPay', true);
								setTimeout(function() {
									uni.hideLoading();
								}, 500);
								// uni.navigateTo({
								// 	url: "/pages/order/lotteryOrderDetails?id=" + res.id,
								// })
								uni.navigateTo({
									url: "/pages/order/orderSuccess?id=" + res.id,
								})
							}
						})
					}
				})

			}
		}
	}
</script>

<style scoped lang="less">
	page {
		background: #fafafa;
	}

	.u-radio-group--row {
		justify-content: flex-end;
	}
</style>
