<template>
	<view class="detail">
		<hx-navbar :config="{title: '他的主页', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}" />

		<view class="myInfo">

			<view class="t">
				<view class="left">
					<u-avatar size="55" :src="userData.avatar"></u-avatar>
					<view class="t1">
						<view style="display: flex; flex-flow: row; align-items: center;">
							{{userData.nickname}}
						</view>
						<view
							style="font-weight: bold; font-size: 11px; margin-top: 4px; display: flex; align-items: center;">
							<text>粉丝{{userData.fans}}</text>
							<view
								style="height: 10px; width: 1px; border-left: 1px solid rgb(0, 0, 0); margin: 0px 15px;">
							</view>
							<text>发单{{userData.issueCount}}</text>
						</view>
					</view>
				</view>
<!-- 				<view class="right">
					<text>+关注</text>
				</view> -->
				
				<view class="right" v-if="!userData.isFollow" @tap="followBtn('0',userData.id)">
					+关注
				</view>
				<view class="right" v-else @tap="followBtn('1',userData.id)">
					取消关注
				</view>

			</view>
			<view class="c">
				<view class="c1">
					<text>{{userData.bonus}}</text>
					<view>
						累计中奖
					</view>
				</view>
				<view class="c1">
					<text>{{userData.profit}}%</text>
					<view>
						7日盈利
					</view>
				</view>
				<view class="c1">
					<text>{{userData.hit}}</text>
					<view>
						7日命中
					</view>
				</view>

			</view>
			
			<view class="f">
				<image src="@/static/images/documentary/fiveRes.png" mode="aspectFit"
					style="width: 35px; height: 14px;"></image>
				<view v-if="userData.recordList!=undefined&&userData.recordList.length<=0">暂无数据</view>
				<view v-else style="display: flex;">
					<view style="display: flex; align-items: center;"
						v-for="(record,index) in userData.recordList" :key="index">
						<view v-if="record"
							style="background: rgb(255, 83, 89); color: white; height: 20px; width: 20px; line-height: 20px; text-align: center; border-radius: 20px; font-size: 10px;">
							红</view>
						<view v-else
							style="background: rgb(151, 151, 151); color: white; height: 25px; width: 25px; line-height: 25px; text-align: center; border-radius: 25px; font-size: 14px;">
							黑</view>
						<view v-if="userData.recordList.length-1>index"
							style="width: 15px; border-top: 1px solid rgb(151, 151, 151);"></view>
					</view>
				</view>
			</view>

		</view>

		<view class="container">
			
			<view class="item"  v-for="(item,index) in userData.documentaryInfoList" :key="index" @tap="documentaryInfoBtn(item.documentaryId,userData.id)">
				<view class="ct">
					<view class="left">
						<image src="@/static/images/documentary/football.png" mode="aspectFit"
							style="width: 21px; height: 21px;margin-right: 10px;"></image>
						<text>{{item.name}}</text>
					</view>
					<view class="right">
						{{item.createTime|formatDate(that)}}截止
					</view>
				</view>
				<view class="cc">
					<view class="left">
						<view class="l1">
							竞彩足球 &nbsp;2串1 &nbsp; &nbsp;预计回报：
							<text>3.51</text>
							倍
						</view>
						<view class="l2">
							自购：
							<text>{{item.price}}</text>
							元 跟单：
							<text>{{item.quantity}}</text>
							人
						</view>
					</view>
					
					<view
						style="display: flex; align-items: center; justify-content: space-between; margin-top: 7px;">
						<view v-if="item.type=='3'"
							style="color: white;); padding: 4px 15px; font-size: 11px; border-radius: 5px;">
							<!-- 中奖{{item.winPrice}} -->
							<view class="right">
								<view class="win">
									<image class="win" src="@/static/images/documentary/win.png" mode="aspectFit"></image>
									<text class="money">￥{{item.winPrice}}元</text>
								</view>
							</view>
						</view>
						<view v-if="item.type=='2'"
							style="color: rgb(151, 151, 151); padding: 4px 15px; font-size: 11px; border-radius: 5px;">
							<view class="right">
								<image class="unwin" src="@/static/images/documentary/fail.png" mode="aspectFit"></image>
							</view>
						</view>
						<view v-if="item.type=='1'"
							style="color: rgb(151, 151, 151); background-color: rgb(243, 243, 243); padding: 4px 15px; font-size: 11px; border-radius: 5px;">
							待开奖
						</view>
						<view v-if="item.type=='0'">
							<view class="right">
								<text class="hall-btn">立即跟单</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			
			
			<!-- 跟单 -->
			<view class="item">
				<view class="ct">
					<view class="left">
						<image src="@/static/images/documentary/football.png" mode="aspectFit"
							style="width: 21px; height: 21px;margin-right: 10px;"></image>
						<text>精彩赛事</text>
					</view>
					<view class="right">
						12-20 21:50 截止
					</view>

				</view>
				<view class="cc">
					<view class="left">
						<view class="l1">
							竞彩足球 &nbsp;2串1 &nbsp; &nbsp;预计回报：
							<text>3.51</text>
							倍
						</view>
						<view class="l2">
							自购：
							<text>16000</text>
							元 跟单：
							<text>128</text>
							人
						</view>
					</view>
					<view class="right">
						<text class="hall-btn">立即跟单</text>
					</view>
				</view>
			</view>
			<!-- 中奖 -->
			<view class="item">
				<view class="ct">
					<view class="left">
						<image src="@/static/images/documentary/football.png" mode="aspectFit"
							style="width: 21px; height: 21px;margin-right: 10px;"></image>
						<text>精彩赛事</text>
					</view>
					<view class="right">
						12-20 21:50 截止
					</view>

				</view>
				<view class="cc">
					<view class="left">
						<view class="l1">
							竞彩足球 &nbsp;2串1 &nbsp; &nbsp;预计回报：
							<text>3.51</text>
							倍
						</view>
						<view class="l2">
							自购：
							<text>16000</text>
							元 跟单：
							<text>128</text>
							人
						</view>
					</view>
					<view class="right">
						<view class="win">
							<image class="win" src="@/static/images/documentary/win.png" mode="aspectFit"></image>
							<text class="money">￥70560元</text>
						</view>
					</view>
				</view>
			</view>

			<!-- 未中奖 -->
			<view class="item">
				<view class="ct">
					<view class="left">
						<image src="@/static/images/documentary/football.png" mode="aspectFit"
							style="width: 21px; height: 21px;margin-right: 10px;"></image>
						<text>精彩赛事</text>
					</view>
					<view class="right">
						12-20 21:50 截止
					</view>

				</view>
				<view class="cc">
					<view class="left">
						<view class="l1">
							竞彩足球 &nbsp;2串1 &nbsp; &nbsp;预计回报：
							<text>3.51</text>
							倍
						</view>
						<view class="l2">
							自购：
							<text>16000</text>
							元 跟单：
							<text>128</text>
							人
						</view>
					</view>
					<view class="right">
						<image class="unwin" src="@/static/images/documentary/fail.png" mode="aspectFit"></image>
					</view>
				</view>
			</view>

			<!-- 结尾 -->
			<view class="u-load-more-wrap"
				style="background-color: transparent; margin-bottom: 0px; margin-top: 0px; height: auto; padding: 20px;">
				<view class="u-line"
					style="margin: 0px; border-bottom: 1px solid rgb(212, 212, 212); width: 25px; transform: scaleY(0.5); border-top-color: rgb(212, 212, 212); border-right-color: rgb(212, 212, 212); border-left-color: rgb(212, 212, 212);">
				</view>
				<view class="u-load-more-inner u-more">
					<view
						style="color: rgb(153, 160, 168); font-size: 14px; position: relative; z-index: 1; background-color: transparent;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
						没有更多了
					</view>
				</view>
				<view class="u-line"
					style="margin: 0px; border-bottom: 1px solid rgb(212, 212, 212); width: 25px; transform: scaleY(0.5); border-top-color: rgb(212, 212, 212); border-right-color: rgb(212, 212, 212); border-left-color: rgb(212, 212, 212);">
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	import {
		documentaryDetails
	} from '@/api/documentary.js'
	import {
		addRelationship
	} from '@/api/follow.js'
	export default {
		data() {
			return {
				userData: {},
				that: this,
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
		onLoad(option) {
			this.init(option.uid)
		},
		methods: {
			documentaryInfoBtn(documentaryId, userId) {
				uni.navigateTo({
					url: "/pages/documentary/documentaryInfo?id=" + documentaryId + "&uid=" + userId
				})
			},
			followBtn(type, id) {
				uni.showLoading();
				addRelationship(type, id).then(res => {
					if (res.success) {
						this.init(id);
						setTimeout(() => {
							uni.hideLoading();
						}, 500)
					}
				})
			},
			init(id) {
				uni.showLoading();
				documentaryDetails(id).then(res => {
					this.userData = res;
					setTimeout(() => {
						uni.hideLoading();
					}, 500)
				})
			}
		}
	}
</script>

<style scoped lang="less">
	.detail {
		font-size: 15px;
		color: #333;
		font-family: Helvetica Neue, Helvetica, sans-serif;
		width: 100vw;
		min-height: 100vh;
		padding-bottom: 80px;

		// position: relative;
		.myInfo {
			background-color: #fff;

			.t {
				display: flex;
				justify-content: space-between;
				align-items: center;
				padding: 10px;

				.left {
					display: flex;
					align-items: center;

					.t1 {
						margin-left: 10px;
					}
				}

				.right {
					border: 1px solid #8d3d87;
					padding: 3px 8px;
					border-radius: 7px;
				}
			}

			.c {
				font-size: 15px;
				padding: 11px;
				display: flex;
				justify-content: space-around;
				text-align: center;
				border-top: 1px solid #e5e5e5;
				border-bottom: 1px solid #e5e5e5;

				.c1 {
					uni-view {
						font-size: 12px;
						color: #999;
					}

					uni-text {
						color: #8d3d87;
					}
				}
			}

			.f {
				padding: 15px 10px;
				display: flex;
				justify-content: space-around;
				align-items: center;
				text-align: center;

				.fbox {
					width: 80%;
					display: flex;
					align-items: center;
					justify-content: space-between;
					position: relative;

					.b1 {
						position: absolute;
						left: 0;
						top: 50%;
						height: 1px;
						content: '';
						width: 100%;
						display: block;
					}

					.f2 {
						z-index: 3;
						text-align: center;
						color: #fff;
						width: 24px;
						height: 24px;
						line-height: 24px;
						border-radius: 50%;
						background-color: #999;

					}

					.active {
						background-color: #8d3d87;
					}
				}
			}
		}

		.container {
			margin-top: 10px;
			margin-bottom: 5vh;

			.item {
				padding: 10px 10px 2px;
				background-color: #fff;
				border-bottom: 1px solid #e5e5e5;

				.ct {
					display: flex;
					justify-content: space-between;
					align-items: center;

					.left {
						display: flex;
						font-size: 15px;
						align-items: center;

					}

					.right {
						color: #999;
					}

				}

				.cc {
					display: flex;
					justify-content: space-between;
					align-items: center;
					font-size: 13px;

					.left {
						.l1 {
							margin-top: 10px;
						}

						.l2 {
							margin: 5px 0 10px;
						}

						uni-text {
							color: #8d3d87;
						}
					}

					.right {
						.hall-btn {
							color: #8d3d87;
							padding: 4px 12px;
							border: 1px solid #8d3d87;
							border-radius: 12px;
						}

						.win {
							position: relative;
							width: 75px;
							height: 66px;
							border-radius: 50%;
						}

						.money {
							position: absolute;
							font-size: 10px;
							color: #8d3d87;
							left: 0;
							top: 40%;
							width: 100%;
							text-align: center;
							transform: rotate(15deg);
						}

						.unwin {
							width: 75px;
							height: 66px;
							border-radius: 50%;
						}
					}
				}
			}

			.u-load-more-wrap {
				display: flex;
				flex-direction: row;
				justify-content: center;
				align-items: center;


				.u-line {
					vertical-align: middle;
				}
			}

			.u-load-more-inner {
				display: flex;
				flex-direction: row;
				justify-content: center;
				align-items: center;
				padding: 0 6px;
			}

			.u-more {
				position: relative;
				display: flex;
				flex-direction: row;
				justify-content: center;
			}
		}

	}
</style>