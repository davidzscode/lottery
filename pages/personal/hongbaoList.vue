<template>
	<view class="box" style="background-color: #f9f9f9;">
		<view class="">
			<view>
<!-- 				<u-navbar :autoBack="true" title="我的红包" :safeAreaInsetTop="false" :bg-color="bgColor"
					left-icon-color="#FFF" title-style="color:#FFF;font-size:20px">
					<view class="u-nav-slot" slot="right">
						<u-icon name="question-circle" size="23" color="#FFF"></u-icon>
					</view>
				</u-navbar> -->
				<hx-navbar :config="{title: '我的红包', backgroundColor: [1,'#8D3D87'], color: '#ffffff'}"/>
				
				<view class="u-tabs" style="background: rgb(255, 255, 255);">
					<view class="">
						<scroll-view scroll-x="true">
							<view class="u-scroll-box u-tabs-scorll-flex">
								<view class="u-tab-item u-line-1"
									style="height: 40px; line-height: 40px; font-size: 15px; transition-duration: 0.5s; flex: 1 1 0%; width: auto; color: rgb(48, 49, 51);">
									未使用</view>
								<view class="u-tab-item u-line-1"
									style="height: 40px; line-height: 40px; font-size: 15px; transition-duration: 0.5s; flex: 1 1 0%; width: auto; color: rgb(48, 49, 51);">
									已使用</view>
								<view class="u-tab-item u-line-1"
									style="height: 40px; line-height: 40px; font-size: 15px; transition-duration: 0.5s; flex: 1 1 0%; width: auto; color: rgb(48, 49, 51);">
									已过期</view>
								<view class="u-tab-bar"
									style="width: 20px; transform: translate(1588.32px, -100%); transition-duration: 0.5s; background-color: rgb(141, 61, 135); height: 3px; border-radius: 3px;">
								</view>
							</view>

						</scroll-view>
					</view>
				</view>

				<view class="noData">
					<view class="info" style="box-sizing: border-box;">
						<image class="myImg" style="width: 36px; height: 36px;"></image>
						<text>您还没有红包哦</text>
					</view>
				</view>

			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				bgColor: '#8d3d87',
			}
		}
	}
</script>

<style scoped>
	.box {
		width: 100%;
		height: 86.3vh;
		background-color: #fff;
		margin-top: -1px;
	}

	.u-scroll-box {
		position: relative;
	}

	.u-tabs-scorll-flex {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.u-tab-item {
		position: relative;
		display: inline-block;
		text-align: center;
		font-family: PingFangSC-Medium, PingFang SC;
		transition-property: background-color, color;
	}

	.u-line-1 {
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}

	.u-tab-bar {
		position: absolute;
		bottom: 0;
	}

	.noData {
		margin-top: 15px;
		background-color: #fff;
		text-align: center;
		padding: 25px 0;
	}

	.info {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-evenly;
		color: #999;
		box-sizing: border-box;
		font-size: 14px;
		height: 65px;
	}

	.myImg {
		background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEYAAABGCAMAAABG8BK2AAAAM1BMVEUAAACZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZmZlkqvaGAAAAEHRSTlMAFERUjb5kNvAk3Myvf5xz5H7VxgAAAm5JREFUWMOtWFtywyAMNEJgwJBw/9O2g0ukAWSb0P1pk8gLeiwS3i6wWzQuFziDdt/moWzMHYz1zUIa8YJdmyzA6NPCQ3r97RQEEuvyBVwqrhKO8U7yJMyAxBtugBq8+oUHjaKf6XIr2PkMOKTRHcvHLmgh9uFjEZP25b8uVaGawCYCEiYLXpUPxVo1pSLsUsZezMcsUW1PoQf5zife23OkkopRXGCbQOzWfeWCOfmFNpKaWCbQPqPIowmoNt9H+cJuc/BNvoEkNk8DTa7nOPrnNNXuFOhBInXbFwhsO0B5m8XOomNoM9MIn9wo5uBDVZIpfErHkn+Y81s94nhXSZ4FV31CCjz623pBcgCrVxQlm0+89suwvvIJS0GufylgBUZUF5hqE7g8zy0cXaML+vpEd1WAhdaeziGtdpCh3E0P3Z6AphUC0AwA4xYWof3abK4vYZ/qA57L+Q/J94XsNjInqOrZzsyrP2p46PQdC8ywyZtxGtWYxjopxvwXiYaKvE/GII05KZkGc8VrF0u4AnsaT73qTlYeP0vxEFPCT8Z7kauqPZZwXn5JEIEgCuTlx8Vg8XHDA7RcDCTNARQwCK6W5NnhqENdkAMu+7hs4pqBWF6KDtEBzP38S4eoFb3ygbMEL/pkWYMZQTFctF+12u4cebvefFdGAcrlPw4m62PSytBGdQWLI6Slu93CQAu51szKeL0Pbzs4ybPTgbxw9QA2VXx/EXrz0/jba5mKmcIr8mR7l2hiGeJ4cmUNuY3LwgWa52jhOn8jZB8z4eAvF45MiP5e/S7fwNFWronWSIgoSiRRr7+UivayNNdfkf0AwKlXUO4tix4AAAAASUVORK5CYII=");
		background-position: 0% 0%;
		background-size: 100% 100%;
		background-repeat: no-repeat;
	
	}
</style>