<template>
	<view>
		
			<div class="bot-acount">
				<view style="display: flex;justify-content: space-between;align-items: center;">
					<button @click="clearNum">清空</button>
					<view>
						<view>共{{acount}}注，{{total}}元</view>
					</view>
					<button @click="confirm">确定</button>
				</view>
			</div>
		
	</view>
</template>
<script>
	export default {
		data() {
			return {}
		},
		props: ['acount', 'total'],
		methods: {
			clearNum() {
				this.$emit("clear")
			},
			confirm() {
				this.$emit("confirm")
			}
		}
	}
</script>
<style>
	page {
		background-color: #f5f5f5 !important;
	}

	.u-number-box {
		padding: 0px 10px 5px 10px;
	}

	.bot-acount {
		display: block;
		background: #9A3B86;
		color: #fff;
		text-align: center;
		position: fixed;
		bottom: 0;
		width: 100%;
		font-size: 32rpx;
		z-index: 100;
	}

	.bot-acount button {
		height: 78rpx;
		border: none;
		border-radius: 5px;
		padding: 0 30rpx;
		line-height: 70rpx;
		margin: 30rpx 16rpx;
		color: #fff;
		font-size: 32rpx;
	}

	.bot-acount button:nth-of-type(1) {
		float: left;
		background-color: #9A3B86;
		border: 1px solid #9A3B86;
	}

	.bot-acount button:nth-of-type(2) {
		float: right;
		background-color: #9A3B86;
		border: 1px solid #9A3B86;
	}
</style>
