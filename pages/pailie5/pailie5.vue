<template>
	<view class="box">

<!-- 		<cmd-nav-bar back title="排列5" font-color="#fff" background-color="#9A3B86" right-text="排列5开奖"
			@rightText="rightBtn">
		</cmd-nav-bar> -->
		<hx-navbar :config="{title: '排列5', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		
		<div style="height:100%">
			<p class="fc_index">第{{issueNo}}期，21:40开奖</p>
			<div class="fc">
				<view
					style="display: flex;justify-content: space-between;align-items: center;width: 95%;margin: 0 auto;">
					<p class="tips"><span class="shake"></span>选择<span>5</span>个号码，中奖<span>100000</span>元
					</p>
					<u-checkbox-group @change="checkboxChange" size="15" shape="square" placement="column"
						style="margin-left: 20px;">
						<u-checkbox labelSize="14" activeColor="#9A3B86" :label="'显示遗漏'">
						</u-checkbox>
					</u-checkbox-group>
				</view>
				<ul>
					<p>万位</p>
					<li @click="check(1,1,index)" v-for="(item,index) in myriad" :class="item.active?'active':''">
						{{item.num}}
						<view v-if="omitData.record!=undefined&&omiIsShow"
							style="color: #A5A5A5;font-size: 13px;margin-top: -8px;">
							{{omitData.record[index]}}
						</view>
					</li>
				</ul>
				<u-divider></u-divider>
				<ul>
					<p>千位</p>
					<li @click="check(1,2,index)" v-for="(item,index) in kilo" :class="item.active?'active':''">
						{{item.num}}
						<view v-if="omitData.record!=undefined&&omiIsShow"
							style="color: #A5A5A5;font-size: 13px;margin-top: -8px;">
							{{omitData.record[index+10]}}
						</view>
					</li>
				</ul>
				<u-divider></u-divider>
				<ul>
					<p>百位</p>
					<li @click="check(1,3,index)" v-for="(item,index) in bai" :class="item.active?'active':''">
						{{item.num}}
						<view v-if="omitData.record!=undefined&&omiIsShow"
							style="color: #A5A5A5;font-size: 13px;margin-top: -8px;">
							{{omitData.record[index+20]}}
						</view>
					</li>
				</ul>
				<u-divider></u-divider>
				<ul>
					<p>十位</p>
					<li @click="check(1,4,index)" v-for="(item,index) in shi" :class="item.active?'active':''">
						{{item.num}}
						<view v-if="omitData.record!=undefined&&omiIsShow"
							style="color: #A5A5A5;font-size: 13px;margin-top: -8px;">
							{{omitData.record[index+30]}}
						</view>
					</li>
				</ul>
				<u-divider></u-divider>
				<ul>
					<p>个位</p>
					<li @click="check(1,5,index)" v-for="(item,index) in ge" :class="item.active?'active':''">
						{{item.num}}
						<view v-if="omitData.record!=undefined&&omiIsShow"
							style="color: #A5A5A5;font-size: 13px;margin-top: -8px;">
							{{omitData.record[index+40]}}
						</view>
					</li>
				</ul>
			</div>
			<Acount :total="total" :acount="acount" @clear="clear" @confirm="sure" />
		</div>

	</view>
</template>
<script>
	import {
		getIssueNo
	} from '@/api/pailie.js'
	import {
		getOmitByType
	} from '@/api/omit.js'
	import Acount from '../common/Acount'
	export default {
		data() {
			return {
				total: 0,
				acount: 0,
				issueNo: "",
				omiIsShow:false,
				ge: [{
						num: 0,
						active: false
					}, {
						num: 1,
						active: false
					}, {
						num: 2,
						active: false
					}, {
						num: 3,
						active: false
					}, {
						num: 4,
						active: false
					},
					{
						num: 5,
						active: false
					}, {
						num: 6,
						active: false
					}, {
						num: 7,
						active: false
					}, {
						num: 8,
						active: false
					}, {
						num: 9,
						active: false
					}
				],
				shi: [{
						num: 0,
						active: false
					}, {
						num: 1,
						active: false
					}, {
						num: 2,
						active: false
					}, {
						num: 3,
						active: false
					}, {
						num: 4,
						active: false
					},
					{
						num: 5,
						active: false
					}, {
						num: 6,
						active: false
					}, {
						num: 7,
						active: false
					}, {
						num: 8,
						active: false
					}, {
						num: 9,
						active: false
					}
				],
				bai: [{
						num: 0,
						active: false
					}, {
						num: 1,
						active: false
					}, {
						num: 2,
						active: false
					}, {
						num: 3,
						active: false
					}, {
						num: 4,
						active: false
					},
					{
						num: 5,
						active: false
					}, {
						num: 6,
						active: false
					}, {
						num: 7,
						active: false
					}, {
						num: 8,
						active: false
					}, {
						num: 9,
						active: false
					}
				],
				kilo: [{
						num: 0,
						active: false
					}, {
						num: 1,
						active: false
					}, {
						num: 2,
						active: false
					}, {
						num: 3,
						active: false
					}, {
						num: 4,
						active: false
					},
					{
						num: 5,
						active: false
					}, {
						num: 6,
						active: false
					}, {
						num: 7,
						active: false
					}, {
						num: 8,
						active: false
					}, {
						num: 9,
						active: false
					}
				],
				myriad: [{
						num: 0,
						active: false
					}, {
						num: 1,
						active: false
					}, {
						num: 2,
						active: false
					}, {
						num: 3,
						active: false
					}, {
						num: 4,
						active: false
					},
					{
						num: 5,
						active: false
					}, {
						num: 6,
						active: false
					}, {
						num: 7,
						active: false
					}, {
						num: 8,
						active: false
					}, {
						num: 9,
						active: false
					}
				],
				gearr: [],
				shiarr: [],
				baiarr: [],
				kiloarr: [],
				myriadarr: [],
				omitData: {}
			}
		},
		components: {
			Acount
		},
		onLoad() {
			this.init()
		},
		methods: {
			checkboxChange(item) {
				if (item[0] == "") {
					this.omiIsShow=true;
				}else{
					this.omiIsShow=false;
				}
			},
			init() {
				getIssueNo("4").then(res => {
					this.issueNo = res.stageNumber
				})
				getOmitByType("4").then(res => {
					this.omitData = res
					this.omitData.record = res.record.split(",")
				})
			},
			rightBtn() {
				uni.navigateTo({
					url: "/pages/pailie5/openPrize"
				})
			},
			check(type, wei = 0, index) {
				switch (type) {
					case 1:
						if (wei == 1) {
							this.myriad[index].active = !this.myriad[index].active;
						};
						if (wei == 2) {
							this.kilo[index].active = !this.kilo[index].active;
						};
						if (wei == 3) {
							this.bai[index].active = !this.bai[index].active;
						};
						if (wei == 4) {
							this.shi[index].active = !this.shi[index].active;
						};
						if (wei == 5) {
							this.ge[index].active = !this.ge[index].active;
						};
						this.gearr = this.ge.filter(v => {
							return v.active
						})
						this.shiarr = this.shi.filter(v => {
							return v.active
						})
						this.baiarr = this.bai.filter(v => {
							return v.active
						})
						this.kiloarr = this.kilo.filter(v => {
							return v.active
						})
						this.myriadarr = this.myriad.filter(v => {
							return v.active
						})
						this.acount = this.globalUtil.math(this.myriadarr.length, 1) * this.globalUtil.math(this.kiloarr
							.length, 1) * this.globalUtil.math(this.baiarr.length, 1) * this.globalUtil.math(this
							.shiarr
							.length, 1) * this.globalUtil.math(this.gearr.length, 1)
						break;
				}
				this.total = this.acount * 2;
			},
			clear() {
				this.ge.map(v => {
					v.active = false;
				});
				this.shi.map(v => {
					v.active = false;
				});
				this.bai.map(v => {
					v.active = false;
				});
				this.kilo.map(v => {
					v.active = false;
				});
				this.myriad.map(v => {
					v.active = false;
				});
				this.total = 0
				this.acount = 0
				this.gearr = []
				this.shiarr = []
				this.baiarr = []
				this.kiloarr = []
				this.myriadarr = []
			},
			//机选
			randomSelect() {
				this.clear()
				let numberArr = this.globalUtil.randomFromZero(10, 5);
				this.myriad[numberArr[0]].active = true
				this.kilo[numberArr[1]].active = true
				this.bai[numberArr[2]].active = true
				this.shi[numberArr[3]].active = true
				this.ge[numberArr[4]].active = true
				this.shiarr = this.shi.filter(v => {
					return v.active
				})
				this.baiarr = this.bai.filter(v => {
					return v.active
				})
				this.gearr = this.ge.filter(v => {
					return v.active
				})
				this.kiloarr = this.kilo.filter(v => {
					return v.active
				})
				this.myriadarr = this.myriad.filter(v => {
					return v.active
				})
				this.acount = this.globalUtil.math(this.myriadarr.length, 1) * this.globalUtil.math(this.kiloarr
					.length, 1) * this.globalUtil.math(this.baiarr.length, 1) * this.globalUtil.math(this
					.shiarr
					.length, 1) * this.globalUtil.math(this.gearr.length, 1)
				this.total = this.acount * 2;
			},
			sure() {
				if (this.total == 0) {
					this.randomSelect();
					return;
				}
				//随机数id用户传到购物车进行去重处理
				let uid = Math.ceil(Math.random() * 9999999999999999)
				let data = {
					uid: uid,
					mode: 0,
					notes: this.acount,
					total: this.total,
					individual: this.gearr.map(v => {
						return v.num
					}),
					ten: this.shiarr.map(v => {
						return v.num
					}),
					hundred: this.baiarr.map(v => {
						return v.num
					}),
					kilo: this.kiloarr.map(v => {
						return v.num
					}),
					myriad: this.myriadarr.map(v => {
						return v.num
					})

				}
				uni.navigateTo({
					url: "/pages/pailie5/buyShoppingCar?obj=" + encodeURIComponent(JSON.stringify(data)),
				})
			},
		},
	}
</script>
<style scoped>
	/deep/.cmd-nav-bar-right-text {
		font-size: 16px !important;
	}

	.tips {
		padding: 20rpx;
		color: #999;
		font-size: 28rpx;
	}

	.tips span {
		color: #9A3B86;
	}

	.fc {
		margin-bottom: 30rpx;
	}

	.fc ul {
		margin-top: 12rpx;
		padding-left: 36rpx;
	}

	.fc ul p {
		height: 50rpx;
		line-height: 50rpx;
		color: #fff;
		background: #9A3B86;
		width: 100rpx;
		font-size: 30rpx;
		padding-left: 20rpx;
		margin-left: -36rpx;
		border-top-right-radius: 30rpx;
		border-bottom-right-radius: 30rpx;
	}

	.fc ul li {
		display: inline-block;
		width: 74rpx;
		color: #9A3B86;
		background: #fff;
		height: 74rpx;
		text-align: center;
		line-height: 74rpx;
		font-size: 30rpx;
		border: 1px solid #e2e2e2;
		border-radius: 50%;
		margin: 18rpx
	}

	.fc ul li.active {
		background: #9A3B86;
		color: #fff;
	}

	.fc_index {
		padding-top: 40rpx;
		height: 60rpx;
		line-height: 30rpx;
		color: #999;
		font-size: 28rpx;
		background: #fff;
		text-align: center;
	}
</style>