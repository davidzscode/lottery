<template>
	<view class="box">

		<cmd-nav-bar back title="大乐透" font-color="#fff" background-color="#9A3B86" right-text="大乐透开奖"
			@rightText="rightBtn">
		</cmd-nav-bar>
		<div style="height:100%">
			<p class="fc_index">第{{issueNo}}期，每周一、三、六 21:35开奖</p>
			<div class="fc">
				<view
					style="display: flex;justify-content: space-between;align-items: center;width: 95%;margin: 0 auto;">
					<p class="tips">奖池<span>7.5亿</span></p>
					<u-checkbox-group @change="checkChange" size="15" shape="square" placement="column"
						style="margin-left: 20px;">
						<u-checkbox labelSize="14" activeColor="#9A3B86" :label="'显示遗漏'">
						</u-checkbox>
					</u-checkbox-group>
				</view>
				<u-divider text="前区(至少5个)"></u-divider>
				<ul>
					<li @click="check(1,1,index)" v-for="(item,index) in  shi" :class="item.active?'active':''">
						{{item.num}}<span style="font-size: 15px;" v-if="item.isGallbladder">胆</span>
						<view style="display: flex;justify-content: center;align-items: center;">
							<view v-if="omitData.record!=undefined&&omiIsShow"
								style="color: #A5A5A5;font-size: 13px;margin-top: 6px;">
								{{omitData.record[index]}}
							</view>
							<u-checkbox-group shape="square" @change="checkboxChange($event,index,1)"
								:disabled="redLength>=4&&item.isGallbladder==false">
								<u-checkbox activeColor="#9A3B86" :name="item.isGallbladder"
									:checked="item.isGallbladder">
								</u-checkbox>
							</u-checkbox-group>
						</view>
					</li>
				</ul>
				<u-divider text="后区(至少2个)"></u-divider>
				<ul style="margin-bottom: 30px;">
					<li id="last" @click="check(1,2,index)" v-for="(item,index) in ge"
						:class="item.active?'active1':''">
						{{item.num}}<span style="font-size: 15px;" v-if="item.isGallbladder">胆</span>
						<view style="display: flex;justify-content: center;align-items: center;">
							<view v-if="omitData.record!=undefined&&omiIsShow"
								style="color: #A5A5A5;font-size: 13px;margin-top: 6px;">
								{{omitData.record[index+35]}}
							</view>
							<u-checkbox-group shape="square" @change="checkboxChange($event,index,2)"
								:disabled="blueLength>=1&&item.isGallbladder==false">
								<u-checkbox activeColor="#007BED" :name="item.isGallbladder"
									:checked="item.isGallbladder">
								</u-checkbox>
							</u-checkbox-group>
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
	import {
		calculation
	} from '@/api/dlt.js'
	import Acount from '../common/Acount'
	export default {
		data() {
			return {
				omiIsShow: false,
				omitData: {},
				total: 0,
				acount: 0,
				issueNo: "",
				gearr: [],
				shiarr: [],
				ge: [],
				shi: [],
				redLength: 0,
				blueLength: 0,
				schemeDetails:[]
			}
		},
		components: {
			Acount
		},
		onLoad() {
			let ge = [],
				shi = [];
			for (var i = 1; i < 36; i++) {
				let obj = {}
				obj.active = false;
				obj.isGallbladder = false;
				obj.num = this.globalUtil.formatNum(i);
				shi.push(obj)
			}
			for (var i = 1; i < 13; i++) {
				let obj = {}
				obj.active = false;
				obj.isGallbladder = false;
				obj.num = this.globalUtil.formatNum(i);
				ge.push(obj)
			}
			this.ge = ge;
			this.shi = shi
			this.init()
		},
		methods: {
			checkChange(item) {
				if (item[0] == "") {
					this.omiIsShow = true;
				} else {
					this.omiIsShow = false;
				}
			},
			checkboxChange(item, index, type) {
				if (type == 1) {
					this.shi[index].isGallbladder = !this.shi[index].isGallbladder;
					this.gallbladderStatistics(type);
				} else if (type == 2) {
					this.ge[index].isGallbladder = !this.ge[index].isGallbladder;
					this.gallbladderStatistics(type);
				}
				if (item[0] != undefined) {
					if (type == 1) {
						this.shi[index].active = true;
					} else if (type == 2) {
						this.ge[index].active = true;
					}
				}
				this.gearr = this.ge.filter(v => {
					return v.active
				})
				this.shiarr = this.shi.filter(v => {
					return v.active
				})
				if (this.shiarr.length >= 5 && this.gearr.length >= 2) {
					calculation({
						redList: this.shiarr,
						blueList: this.gearr
					}).then(res => {
						this.acount = res.notes;
						this.schemeDetails=res.permutationList
						this.total = this.acount * 2;
					})
				} else {
					this.acount = 0;
					this.total = 0;
				}
			},
			init() {
				getIssueNo("8").then(res => {
					this.issueNo = res.stageNumber
				})
				getOmitByType("8").then(res => {
					this.omitData = res
					this.omitData.record = res.record.split(",")
				})
			},
			gallbladderStatistics(type) {
				if (type == 1) {
					let s1 = this.shi.filter(item => {
						return item.isGallbladder;
					})
					this.redLength = s1.length
				} else if (type == 2) {
					let s2 = this.ge.filter(item => {
						return item.isGallbladder;
					})
					this.blueLength = s2.length
				}
			},
			rightBtn() {
				uni.navigateTo({
					url: "/pages/dlt/openPrize"
				})
			},
			check(type, wei = 0, index) {
				switch (type) {
					case 1:
						if (wei == 1) {
							this.shi[index].active = !this.shi[index].active;
							this.shi[index].isGallbladder = false;
							this.gallbladderStatistics(wei)
						};
						if (wei == 2) {
							this.ge[index].active = !this.ge[index].active;
							this.ge[index].isGallbladder = false;
							this.gallbladderStatistics(wei)
						};
						this.gearr = this.ge.filter(v => {
							return v.active
						})
						this.shiarr = this.shi.filter(v => {
							return v.active
						})
						if (this.shiarr.length >= 5 && this.gearr.length >= 2) {
							calculation({
								redList: this.shiarr,
								blueList: this.gearr
							}).then(res => {
								this.acount = res.notes;
								this.schemeDetails=res.permutationList
								this.total = this.acount * 2;
							})
						} else {
							this.acount = 0;
							this.total = 0;
						}
						break;
				}
			},
			clear() {
				this.ge.map(v => {
					v.active = false;
					v.isGallbladder = false;
				});
				this.shi.map(v => {
					v.active = false;
					v.isGallbladder = false;
				});
				this.redLength = 0;
				this.blueLength = 0;
				this.total = 0
				this.acount = 0
				this.gearr = []
				this.shiarr = []
			},
			//机选
			randomSelect() {
				this.clear()
				let numberArr = this.globalUtil.randomFromZero(35, 5);
				for (var i = 0; i < numberArr.length; i++) {
					let j = numberArr[i];
					this.shi[j].active = true;
				}
				let numArr = this.globalUtil.randomFromZero(12, 2);
				for (var i = 0; i < numArr.length; i++) {
					let j = numArr[i];
					this.ge[j].active = true;
				}
				this.shiarr = this.shi.filter(v => {
					return v.active
				})
				this.gearr = this.ge.filter(v => {
					return v.active
				})
				if (this.shiarr.length >= 5 && this.gearr.length >= 2) {
					calculation({
						redList: this.shiarr,
						blueList: this.gearr
					}).then(res => {
						this.acount = res.notes;
						this.schemeDetails=res.permutationList
						this.total = this.acount * 2;
					})
				} else {
					this.acount = 0;
					this.total = 0;
				}
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
					//方案组合
					schemeDetails:this.schemeDetails,
					individual: this.gearr,
					ten: this.shiarr,
				}
				uni.navigateTo({
					url: "/pages/dlt/buyShoppingCar?obj=" + encodeURIComponent(JSON.stringify(data)),
				})
			},
		},
	}
</script>
<style scoped>
	/deep/.cmd-nav-bar-right-text {
		font-size: 16px !important;
	}

	/deep/.u-checkbox-group--row {
		justify-content: center;
		align-items: center;
		margin-top: 5px;
		margin-left: 6px;
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

	#last {
		color: #007BED;
	}

	.fc ul li.active {
		background: #9A3B86;
		color: #fff;
	}

	.fc ul li.active1 {
		background: #007BED;
		color: #fff !important;
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