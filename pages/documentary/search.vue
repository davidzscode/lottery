<template>
	<view class="search-box">
		<view class="search">
			<view style=";padding:0px 20px;" @click="back">
				<u-icon name="close-circle" size="24"></u-icon>
			</view>
			<view style="width: 100%;">
				<u-search animation searchIconSize="24" :clearabled="false" height="40" shape="square" focus
					:show-action="false" @change="searChchange"></u-search>
			</view>
		</view>
		<view>
			<u-empty icon="http://cdn.uviewui.com/uview/empty/search.png" v-show="isEmpt" mode="search" text="沒有数据哦~"></u-empty>
		</view>
		<scroll-view>
			<view v-for="user in userData" class="list" :key="user.id" @click="homepageBtn(user.id)">
				<div style="display: flex;align-items: center;justify-content: flex-start;padding: 10px; 0px">
					<image class="list__item__avatar" :src="user.avatar"></image>
					<text class="list__item__user-name">{{user.nickname}}</text>
				</div>
				<u-line></u-line>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import {
		getUserByNickName
	} from '@/api/user.js'
	export default {
		data() {
			return {
				isEmpt: true,
				userData: [],
				queryParam: {
					nickname: ""
				}
			}
		},
		methods: {
			back() {
				uni.navigateBack()
			},
			searChchange(data) {
				this.queryParam.nickname = data
				getUserByNickName(this.queryParam).then(res => {
					this.userData = res.voList
					if (res.voList.length > 0) {
						this.isEmpt = false;
					} else {
						this.isEmpt = true;
					}
				})
			},
			homepageBtn(uid) {
				uni.navigateTo({
					url: "/pages/documentary/homePage?uid=" + uid
				})
			}
		}
	}
</script>

<style scoped lang="less">
	page {
		background: #FAFAFA;
	}

	/deep/.u-empty {
		margin-top: 50% !important;
	}

	.search-box {
		padding-top: 10px;
	}

	.search {
		display: flex;
		align-items: center;
		justify-content: flex-start;
		background: #ffffff;
		height: 50px;
	}

	.list {
		background: #ffffff;

		&__item {
			&__avatar {
				height: 45px;
				width: 45px;
				border-radius: 3px;
			}

			&__user-name {
				font-size: 16px;
				margin-left: 10px;
				color: #000;
			}
		}
	}
</style>
