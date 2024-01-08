<template>
	<view
		class="sticky"
		:style="{
			position: 'sticky',
			top: top2,
			zIndex
		}"
	>
		<slot></slot>
	</view>
</template>

<script>
export default {
	props: {
		top: {
			type: Number,
			default: 0
		},
		zIndex: {
			type: Number,
			default: 99
		}
	},

	computed: {
		top2() {
			let t = "0px";

			// #ifdef H5
			t = "44px";
			// #endif

			return `calc(${t} + ${this.top}rpx)`;
		}
	}
};
</script>
