<!--本页面由uniapp切片工具生成，uni-app切片-可视化设计工具(一套代码编译到7个平台iOS、Android、H5、小程序)，软件下载地址：http://www.ymznkf.com/new_view_669.htm -->
<template>
	<view class="YmContent box">

		<!-- <nav-bar :title="'实名认证'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '实名认证', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
		<view class="real_name_authentication_1">
			<view class="real_name_authentication_41">
				<text decode="true" class="real_name_authentication_2">*为了您的资金安全,请提交以下信息进行身份核实</text>
			</view>
			<view class="real_name_authentication_42">
				<view class="real_name_authentication_7">
					<text decode="true" class="real_name_authentication_8">姓名</text>
					<input :disabled="user.isReal=='1'" placeholder="请输入姓名" v-model="user.name" class="full_name" />
				</view>
				<view class="real_name_authentication_10">
				</view>
				<view class="real_name_authentication_11">
					<text decode="true" class="real_name_authentication_12">身份证号码</text>
					<input :disabled="user.isReal=='1'" placeholder="请填写身份证号码" v-model="user.idCard"
						class="please_fill_in_the_id_card_number_or_take_photos" />

					<image v-on:click="real_name_authentication_14_14_click()"
						src="/static/real_name_authentication/images/real_name_authentication_14_14.jpg"
						mode="scaleToFill" border="0" class="real_name_authentication_14"></image>
				</view>
			</view>
			<view v-show="user.isReal=='0'" style="font-size: 12px;text-align: center;color: #9A3B86;">温馨提醒，请使用真实姓名和身份证，不然将会影响提现</view>
			<button v-show="user.isReal=='0'" @tap="btSubmit()" class="btSubmit">确认</button>
		</view>

	</view>
</template>

<script>
	import {
		getUserInfo,
		setUserInfo
	} from "@/util/auth.js"
	import {
		real,
		getUser
	} from '@/api/user.js'
	export default {
		data() {
			return {
				user: {
					name: "",
					idCard: "",
					isReal: ""
				},
			}
		},
		onLoad() {
			this.init()
		},
		methods: {
			async init() {
				let user = await getUserInfo();
				this.user = user;
			},
			btSubmit() {
				if (this.user.name == null) {
					uni.showToast({
						title: '姓名不能为空',
						icon: 'none'
					});
					return;
				} else {
					var reg = /^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/;
					if (!reg.test(this.user.name)) {
						uni.showToast({
							title: '姓名有误',
							icon: 'none'
						});
						return;
					}
				}

				if (this.user.idCard == null) {
					uni.showToast({
						title: '身份证号不能为空',
						icon: 'none'
					});
					return;
				} else {
					if (!this.globalUtil.checkIdCard(this.user.idCard)) {
						uni.showToast({
							title: '身份证号不正确',
							icon: 'none'
						});
						return;
					}
				}
				real(this.user).then(res => {
					if (res.success) {
						uni.showToast({
							title: '实名认证成功',
							icon: 'none'
						});
						getUser().then(res => {
							setUserInfo(res);
							this.user = res;
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '../../style/real/real_name_authentication.scss'
</style>
