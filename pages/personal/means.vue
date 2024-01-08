<template>
	<view class="box">

		<!-- <nav-bar :title="'个人资料'" :back="true"></nav-bar> -->
		<hx-navbar :config="{title: '个人资料', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}" />
		<u-cell-group>
			<u-cell clickable center title="头像" @click='changeHead'>
				<u-image width='100rpx' height='100rpx' slot="right-icon" :src="user.avatar" shape="circle">
				</u-image>
			</u-cell>
			<u-cell clickable center title="昵称">
				<template slot="value">
					<u-input border="none" v-model="user.nickname" inputAlign="right"></u-input>
					<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_57" />
				</template>
			</u-cell>
			<u-cell clickable center title="手机号">
				<template slot="value">
					<u-input readonly border="none" v-model="user.phone" inputAlign="right"></u-input>
					<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_57" />
				</template>
			</u-cell>
			<u-cell clickable center title="性别" @click='selectSex'>
				<template slot="value">
					<span>{{user.sex|formatSex}}</span>
					<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_57" />
				</template>
			</u-cell>
			<u-cell clickable center title="出生日期" @click='show=true'>
				<template slot="value">
					<span>{{user.birthday|formatBirthday(that)}}</span>
					<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_57" />
				</template>
			</u-cell>
			<a @click="routeBtn" v-show="user.isReal=='0'">
				<u-cell clickable center title="实名认证">
					<template slot="value">
						<span>{{user.isReal|formatIsReal}}</span>
						<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_57" />
					</template>
				</u-cell>
			</a>
			<u-cell clickable center title="实名认证" v-show="user.isReal=='1'">
				<template slot="value">
					<span>{{user.isReal|formatIsReal}}</span>
					<image src="/static/images/personal/my_40_40.jpg" mode="scaleToFill" border="0" class="my_57" />
				</template>
			</u-cell>
			<u-datetime-picker @close="show=false" closeOnClickOverlay :minDate="-2209017600000" @cancel="show=false"
				:show="show" v-model="defaultDate" mode="date" @confirm="confirm">
			</u-datetime-picker>
		</u-cell-group>
		<view style="width: 90%;margin:0 auto;margin-top: 20px;">
			<u-button @click="sava" color="#9A3B86" customStyle="fontSize:16px">
				提交
			</u-button>
		</view>

	</view>
</template>

<script>
	import {
		getUserInfo,
		setUserInfo
	} from "@/util/auth.js"
	import {
		changeUser,
		getUser
	} from "@/api/user.js"
	import cfg from '@/util/environment.js';
	export default {
		data() {
			return {
				that: this,
				user: {},
				show: false,
				defaultDate: Number(new Date()),
			}
		},
		onLoad() {
			this.init();
		},
		filters: {
			formatSex(data) {
				if (data == 0) {
					return "男"
				} else if (data == 1) {
					return "女"
				} else if (data == 2) {
					return "未知"
				}
			},
			formatBirthday(data, that) {
				if (null == data) {
					return null;
				}
				return that.globalUtil.dateFormat(data)
			},
			formatIsReal(data) {
				if (data == "0") {
					return "未实名";
				} else if (data == "1") {
					return "已实名";
				}
			},
		},
		methods: {
			sava() {
				//如果修改了头像就上传到S3
				if (this.user.avatar.startsWith("blob:") || this.user.avatar.startsWith("file")) {
					//文件上传
					uni.uploadFile({
						url: cfg.BASE_API + "/file/upload",
						filePath: this.user.avatar,
						name: 'file',
						header: {
							'x-access-token': uni.getStorageSync("vue_authtoken"),
							'x-tenant-id': uni.getStorageSync("shop").id,
						},
						success: (uploadFileRes) => {
							if (uploadFileRes.statusCode == 200) {
								this.user.avatar = (JSON.parse(uploadFileRes.data)).url;
								this.updateUser(this.user);
							}
						}
					});
				} else {
					this.updateUser(this.user);
				}
			},
			updateUser(data) {
				uni.showLoading();
				changeUser(data).then(res => {
					if (res.success) {
						getUser().then(res => {
							setUserInfo(res);
						})
						setTimeout(function() {
							uni.showToast({
								title: "修改成功",
								icon: "none"
							})
							uni.hideLoading();
						}, 500);
					}
				})
			},
			routeBtn() {
				uni.navigateTo({
					url: "/pages/personal/real",
				})
			},
			async init() {
				let user = await getUserInfo();
				this.user = user;
			},
			changeHead() {
				uni.chooseImage({
					count: 1,
					success: (res) => {
						const tempFilePaths = res.tempFilePaths[0];
						this.user.avatar = tempFilePaths
					}
				});
			},
			selectSex() {
				const arr = ['男', '女', '未知']
				uni.showActionSheet({
					itemList: arr,
					success: (res) => {
						let index = null;
						if (arr[res.tapIndex] == "男") {
							index = 0;
						} else if (arr[res.tapIndex] == "女") {
							index = 1;
						} else if (arr[res.tapIndex] == "未知") {
							index = 2;
						}
						this.user.sex = index
					},
					fail: function(res) {
						console.log(res.errMsg);
					}
				});
			},
			confirm(e) {
				this.user.birthday = this.globalUtil.dateFormat(e.value)
				this.show = false;
			}
		}
	}
</script>

<style scoped lang="scss">
	.my_57 {
		white-space: normal;
		width: 20px;
		height: 20px;
		padding: 0px;
		margin-left: 20rpx;
		float: left;
		border-radius: 0px;
		font-size: 20px;
		line-height: 20px;
	}

	uni-button {
		height: 44px !important;
		border-radius: 6px !important;
	}
</style>