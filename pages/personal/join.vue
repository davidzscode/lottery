<template>

	<view class="md">
		<view class="dom">
<!-- 			<view>
				<u-navbar title="转账" :autoBack="true" :bg-color="bgColor" left-icon-color="#FFF"
					title-style="color:#FFF;font-size:18px"></u-navbar>
			</view> -->
			<hx-navbar :config="{title: '申请加盟', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
			

			<view class="u-p-t-40" style="display: flex; justify-content: center;">
				<view
					style="width: 95%; height: 80vh; background-color: rgb(255, 255, 255); border-radius: 12px 12px 0px 0px; padding: 40px 20px; position: relative;box-sizing: border-box;">
					<view style="position: relative;">
						<view style="padding-bottom: 10px; font-size: 14px;">
							请输入手机号
						</view>
						<view class="">
							<input class="u-input--border" type="number" value=""
								style="padding: 0px 10px; border-color: rgb(220, 223, 230); text-align: left;"
								placeholder="请输入手机号" placeholder-class="uni-input-placeholder"
								placeholder-style="font-size:14px" maxlength="11" step="0.000000000000000001"
								enterkeyhint="done" pattern="[0-9]*" autocomplete="off" v-model="joinData.phone" />
						</view>
						<view style="padding-bottom: 10px; font-size: 14px;padding-top: 20px;">
							请输入微信号
						</view>
						<view class="">
							<input class="u-input--border" value=""
								style="padding: 0px 10px; border-color: rgb(220, 223, 230); text-align: left;"
								placeholder="请输入微信号" placeholder-class="uni-input-placeholder"
								placeholder-style="font-size:14px" maxlength="11" step="0.000000000000000001"
								enterkeyhint="done" autocomplete="off"  v-model="joinData.weixin"/>
						</view>
						<view class="u-p-t-20" style="font-size: 13px; color: rgb(136, 136, 136);">
							请确保联系方式可用，平台将在提交后12小时内尽快与您联系，请耐心等待。
						</view>
						<view style="margin-top: 80px;">
							<button type="default"
								style="overflow: visible; background-color: rgb(141, 61, 135); font-size:15px;color: white;" @click="submit()">提交申请</button>
						</view>
					</view>
				</view>
			</view>
		</view>


	</view>


</template>

<script>
	import {
		join
	} from "@/api/user.js"
	export default {
		data() {
			return {
				bgColor: '#8d3d87',
				 joinData: {
					phone: '',
					weixin: ''
				 }
			}
		},
		methods: {
			submit(){
				if(this.joinData.phone=='' || this.joinData.weixin==''){
					uni.showToast({
						title: '请完整填写联系方式',
						icon: 'none',
						duration: 3000
					});
					return;
				}
				join(this.joinData).then(res => {
					if(res.success){
						uni.navigateBack(1);
						uni.showToast({
							title: '已提交，请耐心等待',
							icon: 'none',
							duration: 3000
						});
					}else{
						uni.showToast({
							title: '提交失败，请稍后再试',
							icon: 'none',
							duration: 3000
						});
					}
				})
			}
		}
	}
</script>

<style scoped>
	.md {
		width: 100%;
		height: 100vh;
		background-color: #fff;
		margin-top: 44px;
	}

	.dom {
		width: 100%;
		height: 50vh;
		background: linear-gradient(0deg, #fff, #8d3d87);
	}

	.u-p-t-40 {
		padding-top: 20px !important;
	}

	.u-input--border {
		height: 40px !important;
		border-radius: 8px !important;
		border: 1px solid #ebe8e8 !important;
	}

	.uni-input-placeholder {
		position: absolute;
		top: auto !important;
		left: 0;
		color: rgb(192, 196, 204);
		overflow: hidden;
		text-overflow: clip;
		white-space: pre;
		word-break: keep-all;
		pointer-events: none;
		line-height: inherit
	}

	.u-p-t-20 {
		padding-top: 10px !important;
	}
</style>