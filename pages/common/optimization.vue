<template>
	<!-- 奖金优化 -->
	<view class="box">
		<!-- <nav-bar :title="'奖金优化'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '奖金优化', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		<view class="body">
			<u-sticky style="margin-top: 30px;">
				<u-subsection :list="list" mode="subsection" inactiveColor="#9A3B86" fontSize="14" :current="current"
					activeColor="#9A3B86" @change="subsectionChange">
				</u-subsection>
			</u-sticky>
			<uni-table border stripe emptyText="暂无更多数据" style="margin-top: 30px;" v-if="current==0">
				<!-- 表头行 -->
				<uni-tr>
					<uni-th width="75px" align="center">过关</uni-th>
					<uni-th align="center" width="127px">单注组合</uni-th>
					<uni-th align="center" width="1px">注数分布</uni-th>
					<uni-th align="center" width="80px">预测奖金</uni-th>
				</uni-tr>
				<!-- 表格数据行 -->
				<tbody v-for="(item,index) in averageOptimizationList" :key="index">
					<uni-tr>
						<uni-td align="center">{{item.type}}</uni-td>
						<uni-td align="center">
							<view @click="open(index,item,current)"
								style="display: flex;justify-content: center;align-items: center;flex-direction: column;font-size: 12px;">
								<span v-for="(ball,idx) in item.ballCombinationList" :key="idx">
									{{ball.homeTeam}}|{{ball.content}}<br>
								</span>
								<u-icon name="arrow-down"></u-icon>
							</view>
						</uni-td>
						<uni-td align="center">
							<u-number-box v-model="item.notes"
								@change="valChange($event,item.ballCombinationList,index,current)"></u-number-box>
						</uni-td>
						<uni-td align="center">{{item.forecastBonus}}</uni-td>
					</uni-tr>

					<uni-tr v-if="item.isShow" style="background: #FAF9DE">
						<uni-th width="75px" align="center">场次</uni-th>
						<uni-th align="center" width="127px">主队</uni-th>
						<uni-th align="center" width="1px">客队</uni-th>
						<uni-th align="center" width="80px">投注内容</uni-th>
					</uni-tr>
					<uni-tr v-for="(data,idx) in item.ballCombinationList" :key="idx" v-if="item.isShow"
						style="background: #FAF9DE">
						<uni-td align="center">{{data.number}}</uni-td>
						<uni-td align="center">{{data.homeTeam}}</uni-td>
						<uni-td align="center">{{data.visitingTeam}}</uni-td>
						<uni-td align="center">{{data.content}}</uni-td>
					</uni-tr>
				</tbody>
			</uni-table>

			<uni-table border stripe emptyText="暂无更多数据" style="margin-top: 30px;" v-if="current==1">
				<!-- 表头行 -->
				<uni-tr>
					<uni-th width="75px" align="center">过关</uni-th>
					<uni-th align="center" width="127px">单注组合</uni-th>
					<uni-th align="center" width="1px">注数分布</uni-th>
					<uni-th align="center" width="80px">预测奖金</uni-th>
				</uni-tr>
				<!-- 表格数据行 -->
				<tbody v-for="(item,index) in heatOptimizationList" :key="index">
					<uni-tr>
						<uni-td align="center">{{item.type}}</uni-td>
						<uni-td align="center">
							<view @click="open(index,item,current)"
								style="display: flex;justify-content: center;align-items: center;flex-direction: column;font-size: 12px;">
								<span v-for="(ball,idx) in item.ballCombinationList" :key="idx">
									{{ball.homeTeam}}|{{ball.content}}<br>
								</span>
								<u-icon name="arrow-down"></u-icon>
							</view>
						</uni-td>
						<uni-td align="center">
							<u-number-box v-model="item.notes"
								@change="valChange($event,item.ballCombinationList,index,current)"></u-number-box>
						</uni-td>
						<uni-td align="center">{{item.forecastBonus}}</uni-td>
					</uni-tr>

					<uni-tr v-if="item.isShow" style="background: #FAF9DE">
						<uni-th width="75px" align="center">场次</uni-th>
						<uni-th align="center" width="127px">主队</uni-th>
						<uni-th align="center" width="1px">客队</uni-th>
						<uni-th align="center" width="80px">投注内容</uni-th>
					</uni-tr>
					<uni-tr v-for="(data,idx) in item.ballCombinationList" :key="idx" v-if="item.isShow"
						style="background: #FAF9DE">
						<uni-td align="center">{{data.number}}</uni-td>
						<uni-td align="center">{{data.homeTeam}}</uni-td>
						<uni-td align="center">{{data.visitingTeam}}</uni-td>
						<uni-td align="center">{{data.content}}</uni-td>
					</uni-tr>
				</tbody>
			</uni-table>


			<uni-table border stripe emptyText="暂无更多数据" style="margin-top: 30px;" v-if="current==2">
				<!-- 表头行 -->
				<uni-tr>
					<uni-th width="75px" align="center">过关</uni-th>
					<uni-th align="center" width="127px">单注组合</uni-th>
					<uni-th align="center" width="1px">注数分布</uni-th>
					<uni-th align="center" width="80px">预测奖金</uni-th>
				</uni-tr>
				<!-- 表格数据行 -->
				<tbody v-for="(item,index) in coldOptimizationList" :key="index">
					<uni-tr>
						<uni-td align="center">{{item.type}}</uni-td>
						<uni-td align="center">
							<view @click="open(index,item,current)"
								style="display: flex;justify-content: center;align-items: center;flex-direction: column;font-size: 12px;">
								<span v-for="(ball,idx) in item.ballCombinationList" :key="idx">
									{{ball.homeTeam}}|{{ball.content}}<br>
								</span>
								<u-icon name="arrow-down"></u-icon>
							</view>
						</uni-td>
						<uni-td align="center">
							<u-number-box v-model="item.notes"
								@change="valChange($event,item.ballCombinationList,index,current)"></u-number-box>
						</uni-td>
						<uni-td align="center">{{item.forecastBonus}}</uni-td>
					</uni-tr>

					<uni-tr v-if="item.isShow" style="background: #FAF9DE">
						<uni-th width="75px" align="center">场次</uni-th>
						<uni-th align="center" width="127px">主队</uni-th>
						<uni-th align="center" width="1px">客队</uni-th>
						<uni-th align="center" width="80px">投注内容</uni-th>
					</uni-tr>
					<uni-tr v-for="(data,idx) in item.ballCombinationList" :key="idx" v-if="item.isShow"
						style="background: #FAF9DE">
						<uni-td align="center">{{data.number}}</uni-td>
						<uni-td align="center">{{data.homeTeam}}</uni-td>
						<uni-td align="center">{{data.visitingTeam}}</uni-td>
						<uni-td align="center">{{data.content}}</uni-td>
					</uni-tr>
				</tbody>
			</uni-table>
		</view>
		<view style="position: fixed;bottom: 0px; left: 0;width: 100%;">
			<u-line></u-line>
			<p style="text-align: center;padding: 5px 0px;">单注预测奖金:<span style="color: #9A3B86;">{{this.min}}~{{this.max}}</span></p>
			<u-line></u-line>
			<view style="display: flex;justify-content: space-between;align-items: center;height: 50px;">
				<view style="margin-left: 10px;">
					方案共:<span
						style="color: #9A3B86;">{{this.calculationParam.notes*this.calculationParam.multiple*2}}元</span>
				</view>
				
				<view style="position: fixed;right: 0;bottom: 0px;">
					<u-button style="background-color: #9A3B86; color: #ffffff;border-radius: 10px 0px 0px 10px;padding: 10px 35px;"
						text="提交" @tap="confirmIsShow=true"></u-button>
				</view>
			</view>
		</view>
		<u-modal title="支付给店主" :show="confirmIsShow" :zoom="false" confirmText="立即支付" showCancelButton
			confirmColor="#9A3B86" @confirm="betting" @cancel="() => confirmIsShow = false">
			<view class="tip">
				<p style="text-align: center;">{{this.calculationParam.type==0?'竞彩足球':'竞彩篮球'}}</p>
				<p style="margin-top: 15px;">
					共{{this.calculationParam.notes}}注{{this.calculationParam.multiple}}倍，您需要支付{{this.calculationParam.notes*2*this.calculationParam.multiple}}元
				</p>
			</view>
		</u-modal>
	</view>
</template>

<script>
	import {
		createOrder
	} from '@/api/racingball.js'
	export default {
		data() {
			return {
				confirmIsShow: false,
				list: ['奖金平均', '博热优化', '博冷优化'],
				current: 0,
				value: 1,
				min:"",
				max:"",
				calculationParam: {},
				averageOptimizationList: [],
				heatOptimizationList: [],
				coldOptimizationList: []
			}
		},
		methods: {
			betting(){
				if(this.calculationParam.notes*2*this.calculationParam.multiple<10){
					uni.showToast({
						title: '下单金额最低10元起投',
						icon: 'none'
					});
					return;
				}
				uni.showLoading();
				this.confirmIsShow = false;
				this.calculationParam.schemeDetails=JSON.stringify(this.averageOptimizationList)
				createOrder(this.calculationParam).then(res => {
					if (res.success) {
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
			},
			//展开
			open(index, item,type) {
				if (type == 0) {
					this.$set(this.averageOptimizationList[index], "isShow", !item.isShow)
				} else if (type == 1) {
					this.$set(this.heatOptimizationList[index], "isShow", !item.isShow)
				} else if (type == 2) {
					this.$set(this.coldOptimizationList[index], "isShow", !item.isShow)
				}
			},
			subsectionChange(index) {
				this.current = index;
				if (this.current == 0) {
					// 最大值
					this.max=Math.max.apply(null, this.averageOptimizationList.map(function (item) {return item.forecastBonus}))
					// 最小值
					this.min=Math.min.apply(null, this.averageOptimizationList.map(function (item) {return item.forecastBonus}))
				}else if(this.current == 1){
					// 最大值
					this.max=Math.max.apply(null, this.heatOptimizationList.map(function (item) {return item.forecastBonus}))
					// 最小值
					this.min=Math.min.apply(null, this.heatOptimizationList.map(function (item) {return item.forecastBonus}))
				}else if(this.current == 2){
					// 最大值
					this.max=Math.max.apply(null, this.coldOptimizationList.map(function (item) {return item.forecastBonus}))
					// 最小值
					this.min=Math.min.apply(null, this.coldOptimizationList.map(function (item) {return item.forecastBonus}))
				}
			},
			valChange(e, item, index, type) {
				let odds = 1;
				item.map(d => {
					let str = d.content.substring(d.content.indexOf("(") + 1, d.content.indexOf(")"))
					odds = odds * str
				})
				if (type == 0) {
					this.$set(this.averageOptimizationList[index], "forecastBonus", (odds * 2 * e.value).toFixed(2))
					// 最大值
					this.max=Math.max.apply(null, this.averageOptimizationList.map(function (item) {return item.forecastBonus}))
					// 最小值
					this.min=Math.min.apply(null, this.averageOptimizationList.map(function (item) {return item.forecastBonus}))
				} else if (type == 1) {
					this.$set(this.heatOptimizationList[index], "forecastBonus", (odds * 2 * e.value).toFixed(2))
					// 最大值
					this.max=Math.max.apply(null, this.heatOptimizationList.map(function (item) {return item.forecastBonus}))
					// 最小值
					this.min=Math.min.apply(null, this.heatOptimizationList.map(function (item) {return item.forecastBonus}))
				} else if (type == 2) {
					this.$set(this.coldOptimizationList[index], "forecastBonus", (odds * 2 * e.value).toFixed(2))
					// 最大值
					this.max=Math.max.apply(null, this.coldOptimizationList.map(function (item) {return item.forecastBonus}))
					// 最小值
					this.min=Math.min.apply(null, this.coldOptimizationList.map(function (item) {return item.forecastBonus}))
				}
			}
		},
		onLoad(option) {
			this.calculationParam = JSON.parse(decodeURIComponent(option.calculationParam));
			let obj = JSON.parse(decodeURIComponent(option.obj));
			this.averageOptimizationList = obj.averageOptimizationList
			// 最大值
			this.max=Math.max.apply(null, this.averageOptimizationList.map(function (item) {return item.forecastBonus}))
			// 最小值
			this.min=Math.min.apply(null, this.averageOptimizationList.map(function (item) {return item.forecastBonus}))
			this.heatOptimizationList = obj.heatOptimizationList
			this.coldOptimizationList = obj.coldOptimizationList
		}
	}
</script>

<style scoped lang="less">
	.body {
		width: 96%;
		margin: auto;
	}

	/deep/.uni-table-td {
		padding: 8px 5px !important;
	}
</style>
