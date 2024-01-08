<template>
	<view class="cl-input-number">
		<!-- 减 -->
		<button class="cl-input-number__minus" @tap="onMinus">
			<text class="cl-icon-minus"></text>
		</button>

		<!-- 值 -->
		<view class="cl-input-number__value" :style="{ width: width2 }">
			<input type="number" v-model="value2" @blur="onBlur" v-if="input" />
			<text v-else>{{ value2 }}</text>
		</view>

		<!-- 加 -->
		<button class="cl-input-number__plus" @tap="onPlus">
			<text class="cl-icon-plus"></text>
		</button>
	</view>
</template>

<script>
/**
 * input-number 计数器
 * @description 仅允许操作标准的数字值，可定义范围
 * @tutorial https://docs.cool-js.com/uni/components/form/input-number.html
 * @property {null} value 绑定值
 * @property {Numbder} step 步进，默认1
 * @property {Numbder} max 最大值，默认100
 * @property {Numbder} min 最小值，默认0
 * @property {Boolean} input 是否能编辑，默认false
 * @property {Numbder} width 输入框宽度，默认100
 * @property {Numbder} precision 数值精度
 * @event {Function} change 绑定值改变时触发
 * @example <cl-input-number v-model="val" />
 */

import { parseRpx, isString, isDecimal } from "../../utils";

export default {
	name: "cl-input-number",

	props: {
		// 绑定值
		value: {
			type: [String, Number],
			required: true,
		},
		// 步进
		step: {
			type: Number,
			default: 1,
		},
		// 最大值
		max: {
			type: Number,
			default: 100,
		},
		// 最小值
		min: {
			type: Number,
			default: 0,
		},
		// 是否能输入
		input: {
			type: Boolean,
			default: false,
		},
		// 宽度
		width: {
			type: Number,
			default: 100,
		},
		// 小数
		precision: Number,
	},

	data() {
		return {
			value2: 0,
		};
	},

	computed: {
		width2() {
			return this.input ? parseRpx(this.width) : "auto";
		},
	},

	watch: {
		value: {
			immediate: true,
			handler(val) {
				this.check(val);
			},
		},
		max: {
			handler() {
				this.update();
			},
		},
		min: {
			handler() {
				this.update();
			},
		},
	},

	methods: {
		// 验证值
		check(val) {
			if (val === undefined) {
				val = this.value2;
			}

			// 是否字符
			if (isString(val)) {
				val = Number(val);
			}

			// 是否小数
			if (isDecimal(val)) {
				val = Number(val.toFixed(this.precision || 0));
			}

			// 最小
			if (val < this.min) {
				val = this.min;
			}

			// 最大
			if (val > this.max) {
				val = this.max;
			}

			// 最小不能超过最大
			if (this.min > this.max) {
				val = this.max;
			}

			this.value2 = val;
		},

		// 更新值
		update() {
			this.check();
			this.$emit("input", this.value2);
			this.$emit("change", this.value2);
		},

		onPlus() {
			this.value2 += this.step;

			if (this.value2 > this.max && this.max !== Infinity) {
				this.value2 = this.max;
			}

			this.update();
		},

		onMinus() {
			this.value2 -= this.step;

			if (this.value2 < this.min && this.min !== Infinity) {
				this.value2 = this.min;
			}

			this.update();
		},

		onBlur() {
			this.update();
		},
	},
};
</script>
