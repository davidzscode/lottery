<template>
	<!-- 右上角菜单组件 -->
	<view class="mask" @tap="clickMask" :class="show?'mask-show':''">
		<view style="position: relative;top:0;left:0;text-align:right">
			<view class="triangle" :style="{'top':triangle_styles}"></view>
		</view>
		<view class="combox__selector" :style="{'top':__selector_styles}">
			<scroll-view :scroll-y="false" :scroll-x="false" class="combox__selector-scroll">
				<view class="u-flex u-row-between u-col-center" style="color: #ffffff;" v-for="(item, index) in list"
					:key="index" @tap.stop="onSelectorClick(index)" hover-class="select-hover-class">
					<view class="u-p-l-30 u-p-r-20">
						<u-icon size="48" :top="16" :name="item.icon" v-if="item.icon"></u-icon>
					</view>
					<view class="combox__selector-item" :style="index==list.length-1?'border:none':''">
						<text>{{ item[listKey] }}</text>
					</view>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default () {
					return [];
				}
			},
			listKey: {
				type: String,
				default: ''
			},
			show: {
				type: Boolean,
				default: false
			}
		},
		watch: {
			show(val) {
				val && this.$emit('on-open');
			}
		},
		data() {
			return {
				statusBarHeight: 0
			};
		},
		methods: {
			onSelectorClick(index) {
				setTimeout(() => {
					this.$emit('on-select', index)
					this.clickMask();
				}, 80)
			},
			clickMask() {
				this.$emit('update:show')
				this.$emit('close', false)
			}
		},
		created() {
			uni.getSystemInfo({
				success: (res) => {
					//状态栏的高度 将用来适配刘海屏
					this.statusBarHeight = res.statusBarHeight;
				}
			})
		},
		computed: {
			__selector_styles() {

				return this.statusBarHeight + 48 + uni.upx2px(10) + 'px'

			},
			triangle_styles() {

				return this.statusBarHeight + 48 + uni.upx2px(1) + 'px'

			}
		}
	};
</script>

<style lang="scss" scoped>
	/deep/img {
		width: 16px !important;
		height: 16px !important;
		display: inline-block !important;
		overflow: hidden !important;
		position: relative !important;
	}

	.u-flex {
		display: flex;
		flex-direction: row;
		align-items: center;
	}

/deep/.combox__selector-item{
	font-size: 16px!important;
}
	.u-p-l-30 {
		padding-left: 15px !important; 
	}

	.u-p-r-20 {
		padding-right: 10px !important;
	}

	.mask {
		width: 100%;
		height: 100%;
		overflow: hidden;
		position: absolute;
		top: 0;
		left: 0;
		z-index: 0;
		background-color: transparent;
		box-sizing: border-box;
		opacity: 0;
		transition: all 0.15s ease-in-out;
	}

	.mask-show {
		opacity: 1;
		z-index: 1000;
	}

	.combox__selector {
		position: absolute;
		top: 10rpx;
		right: 20rpx;
		/* #ifdef MP-WEIXIN */
		right: 40rpx;
		/* #endif */
		box-sizing: border-box;
		width: 130px; // 下拉框宽度
		background:#9A3B86;
		border-radius: 10rpx;
		box-shadow: #dddddd 2px 2px 4px, #dddddd -2px -2px 4px;
		z-index: 998;
		opacity: 0.9;
	}

	.triangle {
		position: absolute;
		width: 0;
		height: 0;
		// $u-main-color
		border-bottom: solid 5px #9A3B86;
		border-right: solid 5px transparent;
		border-left: solid 5px transparent;
		right: 30rpx;
		top: 0rpx;
		z-index: 999;
	}

	.combox__selector-scroll {
		height: 150px;
		box-sizing: border-box;
		//padding-top: 20rpx;
	}

	.combox__selector-item {
		text-align: left;
		padding: 26rpx 0rpx;
		width: 85px;
		border-bottom: 1rpx solid #c0c0c0;
		font-size: 34rpx;
	}

	/* 
.combox__selector-item:last-child {
	border-bottom: none;
} */
</style>
