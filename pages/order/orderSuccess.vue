<template>
	<view class="box">
		<view>
			<view>
				<u-navbar title="投注成功" :bg-color="bgColor" left-icon=""
					title-style="color:#FFF;font-size:18px"></u-navbar>
			</view>
			<!-- <hx-navbar :config="{title: '投注成功', backgroundColor: [1,'#8D3D87'], color: '#ffffff', backPage: '/pages/home/home'}"/> -->
			
			<view>
				<view class="cont">
					<view class="content" style="box-sizing: border-box;">
						<view class="success">
							<uni-icons type="checkbox" size="83" color="red"></uni-icons>
						</view>
						<text class="tit">
							投注成功
						</text>
					</view>

			<!-- 		<view class="des">
						订单号：
						<text class="order">
							BJDC875917299
						</text>
					</view> -->

					<view class="" style="display: flex;">
						<view @click="goDetail()" class="h-btn-empty-r-gray flex-sub" style="font-size: 14px;">
							查看订单
						</view>
					</view>

					<view class="" style="display: flex; margin-top: 20px;">
						<view @click="goHome()" class="h-btn-empty-r-gray flex-sub" style="font-size: 14px;">
							返回首页
						</view>
					</view>


				</view>
			</view>
		</view>

	</view>
</template>

<script>
import { router } from '../../util/router';
	export default {
		data() {
			return {
				bgColor: '#8d3d87',
				orderId: '',
			}
		},
		
		onLoad(option) {
			this.init(option.id);
		},
		onBackPress(options) {
			// this.goDetail();
			return true;
		},
		methods:{
			init(id){
				this.orderId = id;
			},
			
			goDetail(){
				uni.navigateTo({
					url: "/pages/order/lotteryOrderDetails?id=" + this.orderId,
				})
			},
			
			goHome(){
				// console.log('222222')
				uni.switchTab({
					url: "/pages/home/home"
				})
				// uni.redirectTo({
				// 	url: "/pages/order/lotteryOrder"
				// })
			}
		},
		
		// beforeRouteLeave(to, from, next) {
		// 	uni.navigateBack({
		// 	    delta: 2
		// 	});
		// }

	}
</script>

<style scoped>
	.box {
		width: 100%;
		height: 10vh;
		background-color: #fff;
		margin-top: -1px;
	}

	.tit {
		font-size: 19px;
		color: #303133;
	}

	.success {
		margin-top: 52px;
	}

	.cont {
		padding: 15px;
		background-color: #fff;
		/* min-height: 100vh; */
	}

	.content {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		margin-bottom: 52px;

	}

	.des {
		text-align: left;
		width: 100%;
		display: flex;
		align-items: center;
		margin-bottom: 7px;
	}

	.des .order {
		color: #f00702;
		font-weight: 700;
		font-size: 14px;
	}

	.h-btn-empty-r-gray {
		text-align: center;
		border-radius: 5px;
		padding: 10px;
		border: solid 0.5px #334154;
		color: #334154;
	}

	.flex-sub {
		flex: 1;
	}
</style>