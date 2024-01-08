<template>
	<!--购买彩票底部-->
	<div class="buyFooter_wrap">
		<!--垃圾箱-->
		<div class="buyFooter_trash">
			<div>
				<i class="iconfont icon-lajixiang"></i>
				<span @tap="clean">清空</span>
			</div>
		</div>
		<!--购买总数金额-->
		<div class="buyFooter_count">
			<strong v-show="!isShowCount">您已选择{{this.count}}场比赛</strong>
			<span v-show="isShowCount">共计<b>{{count}}</b>注,<b>{{total}}</b>元</span>
			<span>
				<i class="iconfont icon-quxian"></i>
				<b>开奖结果不包含加时赛及点球大战</b>
			</span>
		</div>
		<!--确定-->
		<button class="buyFooter_buyBtn" type="button" @tap="confirm">确定</button>
	</div>
</template>

<script>
	export default {
		name: 'BuyFooterSport',
		props: ['isShowCount', 'count', 'total'],
		data() {
			return {

			}
		},
		methods: {
			clean() {
				this.$emit("clean")
			},
			confirm() {
				this.$emit("confirm")
			}
		}
	}
</script>

<style scoped lang="less">
	.buyFooter_wrap {
		width: 100%;
		height: 13.33333vmin;
		position: fixed;
		bottom: 0;
		left: 0;
		display: flex;
		align-items: center;
		border-top: 1px solid #e6e6e6;
		background-color: #f7f7f7;
		color: #8f9090;
		z-index: 30;

		/*垃圾桶*/
		.buyFooter_trash {
			width: 13.33333vmin;
			height: 100%;
			display: flex;
			align-items: center;

			div {
				width: 100%;
				border-right: .26667vmin solid #e6e6e6;
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;

				.icon-lajixiang {
					font-size: 5.86667vmin;
					margin-bottom: .86667vmin;
				}

				span {
					font-size: 2.66667vmin;
				}
			}
		}

		/*确定*/
		.buyFooter_buyBtn {
			border: none;
			text-align: center;
			background: #9A3B86;
			color: #FFFFFF;
			font-size: 4.8vmin;
			width: 31.46667vmin;
			border-radius: 4px;
			height: 100%;
		}

		/*购买金额*/
		.buyFooter_count {
			width: 56.46667vmin;
			height: 100%;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			/*margin-left: 4vmin;*/
			font-size: 4vmin;
			color: #333;

			span:first-child {
				b {
					font-weight: 400;
					color: #9A3B86;
				}
			}

			span:last-child {
				margin-top: 1.5vmin;
				color: #999;
				display: flex;
				align-items: center;
				justify-content: center;

				b {
					font-weight: 700;
					font-size: 2.93333vmin;
				}

				i {
					font-size: 3.73333vmin;
				}
			}
		}
	}
</style>
