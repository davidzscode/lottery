<template>
	<view class="box">
		
			<nav-bar :title="'设置'" :back="true"></nav-bar>
			<view class="list-cell m-t">
				<text class="cell-tit">消息通知</text>
				<switch checked color="#9A3B86" @change="switchChange" />
			</view>
			<view class="list-cell m-t b-b" @tap="clearStorage()" hover-class="cell-hover" :hover-stay-time="50">
				<text class="cell-tit">清除缓存</text>
				<text class="cell-tip">{{storageSize}}</text>
			</view>
			<view class="list-cell">
				<text class="cell-tit">检查更新</text>
				<text class="cell-tip">当前版本 1.0.0</text>
			</view>
			<view class="list-cell log-out-btn" @click="toLogout">
				<text class="cell-tit">退出登录</text>
			</view>
		
	</view>
</template>

<script>
	import {
		logout
	} from "@/api/user.js"
	import {
		removeAuthtoken,
		removeUserInfo,
		removeExternalLogintoken
	} from "@/util/auth.js"
	export default {
		data() {
			return {
				storageSize: ""
			};
		},
		onLoad() {
			this.getStorageSize();
		},
		methods: {
			//计算app缓存
			getStorageSize() {
				let that = this;
				plus.cache.calculate(function(size) {
					let sizeCache = parseInt(size);
					if (sizeCache == 0) {
						that.storageSize = "0B";
					} else if (sizeCache < 1024) {
						that.storageSize = sizeCache + "B";
					} else if (sizeCache < 1048576) {
						that.storageSize = (sizeCache / 1024).toFixed(2) + "KB";
					} else if (sizeCache < 1073741824) {
						that.storageSize = (sizeCache / 1048576).toFixed(2) + "MB";
					} else {
						that.storageSize = (sizeCache / 1073741824).toFixed(2) + "GB";
					}
				});
			},
			//清理缓存
			clearStorage() {
				let that = this;
				uni.showModal({
					title: '提示',
					content: '确定清除缓存吗?',
					confirmText: '立即清除',
					success(res) {
						if (res.confirm) {
							let os = plus.os.name;
							if (os == 'Android') {
								let main = plus.android.runtimeMainActivity();
								let sdRoot = main.getCacheDir();
								let files = plus.android.invoke(sdRoot, "listFiles");
								let len = files.length;
								for (let i = 0; i < len; i++) {
									let filePath = '' + files[i]; // 没有找到合适的方法获取路径，这样写可以转成文件路径  
									plus.io.resolveLocalFileSystemURL(filePath, function(entry) {
										if (entry.isDirectory) {
											entry.removeRecursively(function(entry) { //递归删除其下的所有文件及子目录  
												uni.showToast({
													title: '清除成功',
													duration: 2000
												});
												that.getStorageSize(); // 重新计算缓存  
											}, function(e) {
												console.log(e.message)
											});
										} else {
											entry.remove();
										}
									}, function(e) {
										console.log('文件路径读取失败')
									});
								}
							} else { // ios  
								plus.cache.clear(function() {
									uni.showToast({
										title: '清除成功',
										duration: 2000
									});
									that.getStorageSize();
								});
							}
						}
					}
				})
			},
			//跳转
			navTo(url) {
				uni.navigateTo({
					url: url,
				})

			},
			//退出登录
			toLogout() {
				uni.showModal({
					content: '确定要退出登录么',
					success: (e) => {
						if (e.confirm) {
							logout().then(res => {
								if (res.success) {
									removeAuthtoken();
									removeExternalLogintoken();
									removeUserInfo();
									setTimeout(() => {
										uni.redirectTo({
											url: "/pages/user/login"
										});
										uni.hideLoading();
										this.btnDisabled = false;
									}, 1000)
								}
							})
						}
					}
				});
			},
			//switch
			switchChange(e) {
				let statusTip = e.detail.value ? '打开' : '关闭';
				this.$api.msg(`${statusTip}消息推送`);
			},

		}
	}
</script>

<style scoped lang='scss'>
	page {
		background: #f8f8f8;
	}

	.my_65 {
		white-space: normal;
		width: 14px;
		height: 15px;
		padding: 0px;
		margin-top: 9px;
		margin-left: 54px;
		float: left;
		border-radius: 0px;
		font-size: 14px;
		line-height: 15px;
	}

	.list-cell {
		display: flex;
		align-items: baseline;
		padding: 20upx 30upx;
		line-height: 60upx;
		position: relative;
		background: #fff;
		justify-content: center;

		&.log-out-btn {
			margin: 0 auto;
			margin-top: 40upx;
			border-radius: 6px;
			width: 85%;
			line-height: 22px;
			background: #9A3B86 !important;

			.cell-tit {
				font-size: 16px !important;
				color: #fff !important;
				text-align: center;
				margin-right: 0;
			}
		}

		&.cell-hover {
			background: #fafafa;
		}

		&.b-b:after {
			left: 30upx;
		}

		&.m-t {
			margin-top: 16upx;
		}

		.cell-more {
			align-self: baseline;
			font-size: 32upx;
			color: #909399;
			margin-left: 10upx;
		}

		.cell-tit {
			flex: 1;
			font-size: 28upx + 2upx;
			color: #303133;
			margin-right: 10upx;
		}

		.cell-tip {
			font-size: 28upx;
			;
			color: #909399;
		}

		switch {
			transform: translateX(16upx) scale(.84);
		}
	}
</style>
