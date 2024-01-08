<template>
	<view class="box">
		<!-- <cmd-nav-bar title="个人中心" font-color="#fff" background-color="#9A3B86">	 -->
		<!-- </cmd-nav-bar> -->
		<hx-navbar :config="{title: '个人中心', backgroundColor: [1,'#FFF'], color: '#000'}" />

		<!-- <nav-bottom :current="3"></nav-bottom> -->
		<!-- <view class="status_bar"></view> -->

		<!-- 用户头像姓名部分 -->
		<view @click="navClick('/pages/personal/personalSettings')" class="my_header" style="border-radius: 10%;">
			<view class="myhd">
				<view class="left">
					<!-- <image
						style="width:42px;height: 42px; border-radius: 50%; margin-right: 5px; background-color: #fff;"
						mode="aspectFit" :src="user.avatar"></image> -->
					<view class="my_img" :style="{backgroundImage:'url('+user.avatar+')'}"
						style="background-position: center center;background-repeat: no-repeat;background-size: 100% 100%;">
					</view>
					<!-- mode="aspectFit" src="@/static/images/personal/touxiang.jpg"></image> -->
					<view class="name">
						<view class="" style="white-space: nowrap;">
							{{user.nickname}}
						</view>
						<view class="level">
						</view>
					</view>
				</view>
				<view class="right" @click="navClick('/pages/personal/personalSettings')"></view>
			</view>
		</view>
		<!-- 钱包部分 -->
		<view class="topBtn">
			<view class="">

			</view>
			<view class=""
				style="width: 84px; height: 32px; position: absolute; top: 10px; left: -6px; background: url(&quot;/static/images/personal/myqb.png&quot;) 0% 0% / 100% 100%;">

			</view>
			<view class="mybody">
				<view class="top">
					<view class="jymxBox">
						<view class="jymxLeft">
							我的钱包
						</view>
						<view class="jymxRight" @click="navClick('/pages/pay/wallet')">
							<view class="jymxRightLabel">
								交易明细
							</view>
							<view class="jymxRightIcon">

							</view>
						</view>
					</view>
				</view>

				<view class="center">
					<view class="bxx">

					</view>
					<view class="c-left">
						<view class="t1">
							{{user.gold}}
						</view>
						<view class="t2">
							账户总额（元）
						</view>
					</view>

					<view class="c-right">
						<view class="t1">
							{{user.price}}
						</view>
						<view class="t2">
							今日中奖（元）
						</view>
					</view>
				</view>

			</view>

			<view class="btn">
				<view class="b1" @click="navClick('/pages/pay/pay')">
					<span>充值</span>
				</view>

				<view class="b2" @click="navClick('/pages/pay/transfer')" style="position: relative; left: 10px;">
					<span>转账</span>
				</view>

				<view class="b2" style="position: relative; left: 20px;" @click="navClick('/pages/pay/earnings')">
					<span>提款</span>
				</view>

			</view>

		</view>
		<view class="mycenter">
			<view class="center">
				<view class="top">
					<view class="lsx"
						style="width: 5px; height: 18px; background: rgb(141, 61, 135); border-radius: 2px; margin-right: 3px;">
					</view>
					<view class="tzjlBox">
						<view class="tzjlLabel">
							投注记录
						</view>
						<view class="tzjlRight" @click="navClick('/pages/order/lotteryOrder')">
							<view class="tzjlRightLabel">
								全部记录
							</view>
							<view class="tzjlRightIcon">

							</view>
						</view>
					</view>
				</view>

				<view class="foot" v-if="this.user.extend">
					<view class="v1" @tap="goOrders(-1)">
						<view class="n1">
							{{this.user.extend.tobeIssued}}
						</view>
						<view class="n2">
							待接单
						</view>
					</view>
					<view class="v2" @tap="goOrders(1)">
						<view class="n1">
							{{this.user.extend.tobeAwarded}}
						</view>
						<view class="n2">
							待开奖
						</view>
					</view>
					<view class="v3" @tap="goOrders(3)">
						<view class="n1">
							{{this.user.extend.waitAward}}
						</view>
						<view class="n2">
							待派奖
						</view>
					</view>
				</view>
			</view>


		</view>

		<view class="container">
			<view class="top">
				<view class="lsxx">

				</view>
				<view class="gdfw">
					更多服务
				</view>
			</view>
			<view class="list">
				<view class="li" @click="navClick('/pages/personal/introduce')">
					<image style="width:42px;height: 42px;"
						src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAAXNSR0IArs4c6QAABWtJREFUaEPtmltsFFUYx3+7OzPbUuxyK7RLCxQqREkgXYQVUxOIQjBEH3ypD1Uw1lajQOIDiQ1aGoL1gaeaoKVGKZoikvggMV4aAwk1Sk23IAUrFkoLtlwKYbkt7G475kx3m0Ivs7dup8uet8nMd/md/znfnJlzTDxizfSI8ZIETnTFkwonFU6wHkgO6QQTdAhOUuFRFJaAPOBJIMMgI+EqcBpoA/yh5BSqwo8DFcDCUJyOwzNngHLgX73YoQAL2H2ArOdsnO/7gNf0oPWAxTAWsJqyNpuNwsJC7Hb7OLP1h+/q6uLAgQO43e5gPkJpAT3i8NYDfgL4Kgjb3NxMbm6uIWCDSbS3t5Ofnz8Y+lXg75GS1AN+GSgTxiUlJVRXVxsKNphMaWkpe/bsCV5+BHwXKXAp8KYw3r59O+Xloi4Yr1VUVGj5BVoNMKIyegongWOhr9/jR7JKYI6FNzCkwjfOuWn5thX/bS+WXhVFNqOkyaRnpzH3xUVI6ZG/9QwHfOpgKx2/dYGqIstmrJIJRbEgSyZk2YTVKpG9Po+0RdMjktxQwBePdXOi7jSofZgwI1sCkIoFyWImxSquLVgnK+QW54OsV1aG9olxgPvg562H8Xv9qL0qcopE3tpcMpfMxH/Xx+WGC9zrvNmvtmwmY1UO6Y6ssFU2DPDVUz00Vjej9qnQB853HGQsnvEA0D+fuuhz30dRzExfkcWUgjkTF7hFzN2jFzR1lVSZtbtWD4G5cKiN2y1XNeCpjkymrZ47cYEvt/Tw52cuMX2xOzJZVrxkCEzHwVbutl1DUaSJDyzo3B1u7lzxYF+eOQTWd8dHa1UTEv2vqRnPzsbmzJ64Co+Wed99Py2fNNF7y4diNZEySdaqtHly+O9jwxStkYBVv4prVyP+mx4UWSIlxULWc/OYsiL8Ci1iGB7YVdXE3c6byIoJRbYwa4Ud+/oFYQ/loIGhgTsPd3L2+zZthSVWWhlLZzG/UHx2R94MDXz4/SPg6dUWGlPn21j8tiNy0oClYYF7Wq/h2u3SlpKKbGL51mdQplkTF7jtp3Oc+7FdG8qT0hWc5QVRwxq6aJ354Sznfzmvzd/pc2ws2bwssYHvu70c//IEkgnyXliAbeG0xAaOCd0wTgxbtESunUc66fX6ySnIQZoU/qpquE4zLPDRyj/wXvegSBasaTKO956KCbQhgS/91cOJ2pPIFvo/+C1m7Cvt5KybH/VINy7wvpPaK0mRzNrCw+7MImdd5EtKwy8tGz4+hs/tQZYspKbJLN28LHGHdFANsZZWfb3MLsiOCWysFx4lYltJOJ1AWy1ik2lgo+nhAqH3T/Ql4ENhtGHDBvbu3Rt1gRkLBxs3bqS2tnZgSgOHRoqjByyOOHwjjFNTU2loaMDhiP7rJpbQLpeLgoICPB5P0O0rgSMQw4bRAxb3xU6cRqkoCmvWrCErK7w/E06nk+Li4hE5a2pqaGxsDLsfuru7qa+vx+v1Bm2bgLcANVKFhd1s4GvgsbAzGmSwbds2duzYMcRFWVkZlZWV0bgO2t4CioD/RnOmp3DQdibwAbAymsyqqqrYtGnTgAtxvWXLlmhcBm1/B0RvXtFzFipw0I/4vBHHlh7cPhg9yvPA0+IRk8nE/v37tXMidXV1FBUVoaoDo08k/atewg/d7wkcW7oeql24wKH6HfxcCrAb0P7CizoghvHOnTvx+cTBG60dB94F7kUSIBybeACLfNKBz4HhFs5nAVHRxBwc8xYvYAEi6sAXwOAtiEvA64A4UReXFk9gATQvoPQU4AbwBtARF9JAkHgDi7Bi82gVcAS4GE9YrXDGO+B4x0sCj7cCYx0/qfBY9/B4+/8f9DAsW23t87cAAAAASUVORK5CYII=">
					</image>
					<span>玩法介绍</span>
				</view>
				<view class="li" @click="navTap('/pages/personal/follow','1')">
					<image style="width:42px;height: 42px;"
						src="data:image/png;base64,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">
					</image>
					<span>我的粉丝</span>
				</view>
				<view class="li" @click="navClick('/pages/personal/hongbaoList')">
					<image style="width:42px;height: 42px;"
						src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAAXNSR0IArs4c6QAABS1JREFUaEPtml1MHFUUx3+z87F8ZZVVBI0pIRBMan0R+yKN8auBF201REiaaA0amzSFF3kBhbbaaqovrYZUUx58qWBIhfggaK0mQjSmRKMRIwUa2iIpJtBSgbZUMecuQxYozN3KLms7k2w2ZO78z/3d/7lnzg5jcIsdxi3Giw98szvuO+w7fJOtwI2m9GPAC8C9gJngNfkbOAd8BHwda+xYgbOBE8D6WAPFaXwv8DhwXlc/FmCBPQM4uuIJGncVWKcLHQvwr9HOlpSUUFRUhG3bCeKKhJmZmaGnp4fOzs7ouOL0/ToT0QWWPSuprI7m5mbKy8t19OM2pqWlhYqKimh9SW3PPa0LLAXieVEXZzs6OuIGEotwaWlptNMyx+1e1+sCHweeELHa2lr27dvnpZuQ83V1dezfv9+N9RXwpFdgXWBJlUdFrKGhgd27d3vpJuS8zGPPnj1urG8A2XorHj7wMsvzpZsuVVVVHDx40GshE3K+urqaQ4cOubFk2232Cqzr8DHgGRErLi6mq6vLS3fJ+St/XOJi1zm4cBlrdhY7aOLcnoKz/k6Mwjti1pMLNm3aRHd3t3vtp8CzXkK6wLKMu0QsLS2NwcFBsrOlD9E7Rj4f4NLPo1gG2GYA25aPgW0FcByTYE4GxiO5kGrpCUqXcf48eXl5TE9Pu9e8B1R5CegCS5VqcMW2bNlCW1ubl7Y6P9R+irGTI9imEflYEVjHNhW4I38HTYLhVHiqUEtTBm3dupX29vbo8VK9PKvpDQFLlJ07d3LgwAHl+HLHxdMX6D38I6Y4G4gCVs4G5hyOuGw7AZwHsuHBu1eEnpqaoqamhsbGxsXj4gss0QoKCqisrFQtZn5+PoaxcP3OHj3DP6NXFawlqWyCY5k4ks5zDgcFVAGbWCkBhh8OsfjB0+zsLAMDA6qlbGpqor+//3qLEh/gwsJC+vr6tFLv6LaPyQplYhpz7qqUlnSOpLKAzrsrRSxosfmtF+nu/0lLf9Fc4gNcX19Penq66rYmJiZWnNix7Z8RNE0scTgAjhmBVcVKvp0AQYEWWNvESTF56Ugdn5xc8MNgSYxQKIR0WZOTk+zdu9c9Hx9gt9MaGxujtbVV9bJDQ0OMj48jqbegimx8m1BKuoIVaMcSh83Ity2wAeWqcjxFiphJVeubfHf2lwU6slUyMzPJzc1VvXxZWRnhcFh1fFGdVnyBdXLu+GvfIoVLOexWaXHZLVoCLRU6aEUqdarFXTuKpMLpyCcf8JnuYb5/vweZ/nyVllQ2DQUacXkupYMmt23IIvy0/q0p6RwWm7re+YHhnpFI4ZJbk1u0bFOltLodBS3Swink7dqIoemuaCcl8MzUNQU92vvnguZD7sOqUDkBMrLSyNu2gdR1Ia1UdgclJbA7uf4vTvNb2ymuTVyJdFd2gLSQQ05RDvnPrceQvjPGI6mBXZbpsctMjU4RDDlk3JMRI+LC4f8L4P9EuOhiHzgePx6S+BHP2jceq5m+19PyU9pP6aV5oXvzm3/i4e/heG/UFfT9PezvYX8Pa7+n5RetNaxV86H9ouUXLb9o+UVruVr0OqCeeCfxKw/1wBtexVS3l34Z+FDEkvilFpnjkdUCzgfm/4OVpK8tFQADqwUsOoeBV1zBJHsx7QNghxesnNdNaRkrjxhPAvfpCCdwzO/AQ8BfOjFjAXah3412WidIHMeIs6/qwsbqcPS8ZU/Lq345a/T68Mjca4aee3bxYsfqcBzNSoy0D5yYdV67KL7Da7f2iYn8LzLb8EzbiItiAAAAAElFTkSuQmCC">
					</image>
					<span>我的红包</span>
				</view>
				<view class="li" @click="navClick('/pages/pay/withdrawalRecord')">
					<image style="width:42px;height: 42px;"
						src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD4AAAA8CAYAAAA+CQlPAAAAAXNSR0IArs4c6QAABD9JREFUaEPtmk1oHVUUgL87/y8xpDUqxFIXYo00bf1BanXVLqyxiAhC0RqQummhdeGi/iDiE7MwihsFXVUKKi7abgqhogWlVFMR6g8U0kWNjaCWggpiEm1K5by5L87ivczMe/fGgdyBx5v37p1zz3fPOfecmbmKFXqoFcqNA19plncWdxZfITPQjauvArYAtwM1S/P1J/AWcNm0/E7BnwDeBlabVqiFvI+BR4FZk2N1Ai7QH5hUooCsL4CHgD8K9C3UpSy4uPcPGUv/DXwDzIeEPUODt21WCjyl8BQoT74Vnqf/k99e+v/kuW9PLVy9srCEljcCt2bavwceAH4tRJbTqSz4CHBcyxTo6wGJQ16Nx4fueHLTVOgr4sAjjjyS0CeO5TwgiX0SOU8CksQnOP/LgKqP/Jaj32Ht5k09zwP3A9PdwpcFfw54TQ96Gri3qYCAbxrdOBUHikjAQwH2SWQCYj89rwXEMgGJTzg9O6Dq9+WBi/h3gL2wWGX+DGwHznYDXxa8DrysB/wc2JYF3/j48FQkwC3ABTaJU2uL1cMfC4PLEK8AL2XgZcJ2AF91Cm8e3PeIAkUS+cShasCK29e0izcsXgsIL8wVtXiTbb9ObU2d/wIeAU50Am8UfMNjw6mr+zrGG/ACLbHu/wcvFp8pDS58u4D3AU/D/qP/O1oW3ij48M71U+Lm4u6JfKI0zmsS40kKLt+pxS8WWdxa8Uh8TwCBbrwC7AEOloE3Ci4Wj2RVlzjXH3H5LPhijM90DC58m4GTQJyBfRZ4oyi8MfCx2ptrh3cOzWTBBXpxVdeu3rB8LbwaXFi4RtXv7qYakxx/BujNwErGeaEIvDFwGeyjh4+c7r+h556GtRu5PM3daQ5PV/TG+fzCRPjMFqnEuj0GdVrLls77dApcUrZR8PHrDvYN3hLu7u2P1vTGPj2JRxxH9CaeWJme2KPmq+n+xDuk6tvmu6XW1/fravJa/VtSn6Td5QPPG8xi+2fAVgfuLN7ex4zGuEVXzhPtXF3X9W5xa+cqztXzgki3t70tLXi9rW6ViHGppqRslMdUpg65C3sP+LKNwEqAy13SU6aIM3J+Am6qMvgY8KIFcHnaIs/xWx2VsLivHwuZdvVPgN+rDG7B2LkiK2HxXC0tdHDgrnJz9+OtZ8BWyXqXhQLma0BeW1U2nR0AXrewgH2qXx1VFlwe+I9aAJe3pFIOVxZ8nX6xaLqAeRc4VmVwC8bOFenyuMvjLo8vbx7PDUrDHazHeNutIIZByoqbzNyrPw+M5wkoW7m13fyTN5DF9j7gUuaV8YOA7I1b8igL3na7V95AltoT4M4MtDyouLnIfriy4KL//7HBr+i8ScX4YZHOnYA34ZdrS2cRDrH000WhRWCn4HLtcmzizYOeA74DZM9dqe2e3YDnKVXpdgdeafNYUM5Z3MKkVlqks3ilzWNBuX8BcCcgTLegGXgAAAAASUVORK5CYII=">
					</image>
					<span>提现记录</span>
				</view>
				<view class="li" @click="navClick('/pages/personal/share')">
					<image style="width:42px;height: 42px;" src="@/static/images/personal/share.png"></image>
					<span>邀请好友</span>
				</view>
				<view class="li" @click="navClick('pages/proxy/invitation')">
					<image style="width:42px;height: 42px;" src="@/static/images/personal/invitation.png"></image>
					<span>邀请统计</span>
				</view>
				<view class="li" @click="navClick('/pages/personal/join')">
					<image style="width:42px;height: 42px;" src="@/static/images/personal/shopinfo.png"></image>
					<span>申请加盟</span>
				</view>
				<view class="li" @click="logout">
					<image style="width:42px;height: 42px;"
						src="data:image/png;base64,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">
					</image>
					<span>退出登录</span>
				</view>

			</view>
		</view>
	</view>

</template>

<script>
	import {
		logout,
		getUser,
	} from "@/api/user.js"
	import {
		getUserInfo,
		setUserInfo,
		removeAuthtoken,
		removeUserInfo,
		removeExternalLogintoken
	} from "@/util/auth.js"
	export default {
		name: "Mine",
		data() {
			return {
				user: {},
				isShow: false,
			}
		},
		onLoad() {
			this.init()
		},
		onPullDownRefresh() {
			this.init();
			setTimeout(function() {
				uni.stopPullDownRefresh()
			}, 500);
		},
		onTabItemTap(e) {
			// tab 点击时执行，此处直接接收单击事件
			this.init();
		},
		methods: {
			rightText() {
				this.isShow = true;
			},
			kefu() {
				window.location.href =
					'mqqwpa://im/chat?chat_type=wpa&uin=QQ号&version=1&src_type=web&web_src=oicqzone.com'
			},
			init() {
				getUser().then(res => {
					if (res.status == "1") {
						this.logout();
					}
					setUserInfo(res);
					this.user = res
				})
			},
			navClick(url) {
				uni.navigateTo({
					url: url,
				})
			},
			navTap(url, type) {
				var obj = {};
				if (type == "0") {
					obj.title = "我的关注"
				} else {
					obj.title = "我的粉丝"
				}
				obj.type = type;
				uni.navigateTo({
					url: url + "?obj=" + encodeURIComponent(JSON.stringify(obj)),
				})
			},
			goOrders(state) {
				uni.navigateTo({
					url: "/pages/order/lotteryOrder?state=" + state,
				})
			},
			logout() {
				uni.showLoading();
				logout().then(res => {
					if (res.success) {
						removeAuthtoken();
						removeExternalLogintoken();
						removeUserInfo();
						setTimeout(() => {
							uni.redirectTo({
								url: "/pages/user/login"
							});
							this.btnDisabled = false;
							uni.hideLoading();
						}, 500)
					}
				})
			}
		}
	};
</script>

<style lang="scss" scoped>
	.my_header {
		color: #303133;
		font-size: 14px;
		margin: 0;
		-webkit-tap-highlight-color: transparent;
		display: block;
		box-sizing: border-box;
		padding: 16px 16px 0 16px;

		.myhd {
			height: 50px;
			padding: 5px;
			display: flex;
			text-align: center;

			.left {
				text-align: center;
				display: flex;
				align-items: center;
				width: 50%;

				.my_img {
					width: 42px;
					height: 42px;
					border-radius: 50%;
					margin-right: 5px;
					background-color: #fff;
				}

				.name {
					box-sizing: border-box;
					display: inline-block;
					text-align: center;
					align-items: center;
					font-size: 16px;

					.level {
						text-align: center;
						font-size: 16px;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						margin-top: 5px;
						width: 63px;
						height: 23px;
						color: #fff;
						background: url(/static/images/personal/vip0.png) no-repeat;
						background-size: 100% 100%;
					}
				}
			}

			.right {
				color: #303133;
				font-size: 14px;
				text-align: center;
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				display: block;
				box-sizing: border-box;
				width: 20px;
				height: 20px;
				background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAflJREFUSEu91stu00AUBuD/5KIs8hpdsOI5qoamCBA8QHLGUXZAoRRKaSmUS1VlESlIXkSRsvQKIVSh0gJFPAdiycZZcHGTWDlo0FRigezxOKpXXljz+Z9zztiEc77onD2kgo1GY0FEvvf7/R/zeLlEUCl1AcAxgK9xHC/OA00EW62WN5vNXgEgEflSLpcXe73ezzxJU7dUKXUTwL5GiehzsVhcyoOmgjqNUuo2gD2NAjiJomhpOBz+cklqBRr0DoCXBvkURVHNBbUGDXoXwAt9T0QfRaTm+/7vLEkzgQa9B+CZQY9FZDkLmhk06H0AT02yo2q1utzpdCKbpE6gQR8AeGKQ96PRqB4EQSrqDBr0IYDH+l5EDqfTaX0wGJwmJc0FGvQRgC1T03fj8fhyEpobZOZ1Ito1qU5KpVIt6WDIBTLzBhHtZJlNZ5CZN4lo22AfAFyyGQ8nkJm3iWjzbCwA1G2wv3W2mZ1/n2HmHSLaME1yGIbhis04nK2RCWTmXSJat+3I/4WxBpn5ORGtmUUOJpPJlbSZcwY9z9sTkVWzwNtKpXK12+2Os5bDqoae5+2LyC2zjW/CMLwWBMHEBUsFlVL62NLHl/4cvQ7D8HoeLBVsNpsXC4XCkf61EJEbvu9PXZNZd2m73V6I4/jbPLDUhHnTOHfpPOE/AKzQHVugRd0AAAAASUVORK5CYII=) no-repeat;
				background-size: 100% 100%;
				position: relative;
				top: 10px;
				left: 152px;
			}
		}
	}

	.topBtn {
		display: block;
		box-sizing: border-box;
		margin: 0 15px;
		position: relative;
		background: linear-gradient(90deg, #d85eab, #8d3d87);
		box-shadow: inset 0px 1px 7px 0px hsla(0, 0%, 100%, .5);
		border-radius: 14px;

		.wqb {
			color: #303133;
			font-size: 14px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			display: block;
			box-sizing: border-box;
			width: 84px;
			height: 32px;
			position: absolute;
			top: 10px;
			left: -6px;
			background: url("/static/images/personal/myqb.png") 0% 0% / 100% 100%;
		}

		.mybody {
			color: #303133;
			font-size: 14px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			display: block;
			box-sizing: border-box;
			border-top-left-radius: 8px;
			border-top-right-radius: 8px;
			margin-top: 5px;
			height: 104px;
			opacity: 1;

			.top {
				box-sizing: border-box;
				position: relative;
				right: 10px;

				.jymxBox {
					color: #303133;
					font-size: 14px;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					box-sizing: border-box;
					position: absolute;
					top: 10px;
					left: 0;
					width: 100%;
					display: flex;
					flex-direction: row;
					flex-wrap: nowrap;
					justify-content: space-between;
					align-items: center;
					line-height: 26px;
					padding: 0 12px;

					.jymxLeft {
						line-height: 26px;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						font-size: 15px;
						font-family: PingFangSC-Regular, PingFang SC;
						font-weight: 400;
						color: #8d3d87;
					}

					.jymxRight {

						color: #303133;
						font-size: 14px;
						line-height: 26px;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						box-sizing: border-box;
						display: flex;
						align-items: center;

						.jymxRightLabel {
							line-height: 26px;
							margin: 0;
							-webkit-tap-highlight-color: transparent;
							display: block;
							box-sizing: border-box;
							font-size: 13px;
							font-family: PingFangSC-Regular, PingFang SC;
							font-weight: 400;
							color: #fff;
							margin-right: 8px;
						}

						.jymxRightIcon {
							color: #303133;
							font-size: 14px;
							line-height: 26px;
							margin: 0;
							-webkit-tap-highlight-color: transparent;
							display: block;
							box-sizing: border-box;
							width: 14px;
							height: 14px;
							background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAYVJREFUSEu91jdOBEEQheG/7kJAxDkQXoDgCMR47z2ImHMghBDCgzgHIiTGQ/BQoSJB7E6P0Xa8M99W672eNhq8rMEemaCkJuDRzJ6q+HN1QUnNwAVwD7RWgWaBQ8A+/OzEXaDPZSZN2dJhYC/QW6DNzAqjmaBPI2kU2A30JtCXIpMmgYGOAzuBXAPtZpYbTQYDnQC2A70K9DXPpLnAQKeAzUA8wZ1mlozmBgOdAdYDPQ/0LWXSQmCgs8BaIGdAl5llooXBQOeBlUBPA32vN2kpMNBFYCmQE6DHzGqiVYDTwEaA3lGvS82DoRQoaQ5YzdPNwqCkBWA5sEugI6UehUBJDjnoy2vhCU3qYm5Qkm+hb6UvT2Z3Sh1+k5sLlOTh8JD4ykzkf/VIBiVtAZPxkmOgt178a3UxCZTkn6axeMkR0GdmHylH2d/fZIKS/OM7Eg8eAv1m9lkE82eyrhh+bPnx5esAGCiDpYAtEXu/Wgya2VfRyZJTGtfEhyqwzAnLTlOqFlXh3/TGjh0qRJyzAAAAAElFTkSuQmCC) no-repeat;
							background-size: 100% 100%;
						}
					}
				}
			}

			.center {
				color: #303133;
				font-size: 14px;
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				box-sizing: border-box;
				display: flex;
				justify-content: center;
				padding: 60px 45px 10px;

				.bxx {
					color: #303133;
					font-size: 14px;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					display: block;
					box-sizing: border-box;
					width: 1px;
					height: 42px;
					opacity: 0.4;
					background-color: white;
					position: relative;
					left: 130px;
				}

				.c-left {
					color: #303133;
					font-size: 14px;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					box-sizing: border-box;
					flex: 50%;
					margin-left: -5px;
					display: flex;
					align-items: center;
					flex-direction: column;

					.t1 {
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						width: 127px;
						height: 33px;
						font-size: 24px;
						font-family: PingFangSC-Medium, PingFang SC;
						font-weight: 500;
						color: #fff;
						line-height: 33px;
						position: relative;
						bottom: 12px;
						text-align: center;
					}

					.t2 {
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						font-size: 12px;
						font-family: PingFangSC-Regular, PingFang SC;
						font-weight: 400;
						color: #fff;
						line-height: 17px;
						position: relative;
						bottom: 5px;
						text-align: center;
					}
				}

				.c-right {
					color: #303133;
					font-size: 14px;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					box-sizing: border-box;
					flex: 50%;
					display: flex;
					align-items: center;
					flex-direction: column;

					.t1 {
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						width: 127px;
						height: 33px;
						font-size: 24px;
						font-family: PingFangSC-Medium, PingFang SC;
						font-weight: 500;
						color: #fff;
						line-height: 33px;
						bottom: 12px;
						text-align: center;
						position: relative;
						left: 25px;
					}

					.t2 {
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						font-size: 12px;
						font-family: PingFangSC-Regular, PingFang SC;
						font-weight: 400;
						color: #fff;
						line-height: 17px;
						bottom: 5px;
						text-align: center;
						position: relative;
						left: 25px;
					}
				}
			}
		}

		.btn {
			color: #303133;
			font-size: 14px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			box-sizing: border-box;
			border-bottom-left-radius: 8px;
			border-bottom-right-radius: 8px;
			padding: 16px 16px;
			height: 85px;
			display: flex;

			.b1 {
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				box-sizing: border-box;
				width: 92px;
				height: 34px;
				border-radius: 4px;
				margin-top: 10px;
				background: linear-gradient(180deg, #fff, #ffd0f7);
				box-shadow: inset 0px 3px 5px 0px #fff;
				border-image: linear-gradient(180deg, #fff, hsla(0, 0%, 100%, 0)) 2 2;
				font-size: 14px;
				font-family: PingFangSC-Medium, PingFang SC;
				font-weight: 800;
				color: #8d3d87;
				text-align: center;
				line-height: 34px;
			}

			.b2 {
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				box-sizing: border-box;
				width: 92px;
				height: 34px;
				border-radius: 4px;
				margin-top: 10px;
				background: linear-gradient(180deg, #fff, #ffd0f7);
				box-shadow: inset 0px 3px 5px 0px #fff;
				border-image: linear-gradient(180deg, #fff, hsla(0, 0%, 100%, 0)) 2 2;
				margin-left: 8px;
				font-size: 14px;
				font-family: PingFangSC-Medium, PingFang SC;
				font-weight: 800;
				color: #8d3d87;
				text-align: center;
				line-height: 34px;
				position: relative;
				left: 10px;
			}
		}
	}

	.mycenter {
		color: #303133;
		font-size: 14px;
		margin: 0;
		-webkit-tap-highlight-color: transparent;
		display: block;
		box-sizing: border-box;
		position: relative;
		bottom: 15px;

		.center {
			color: #303133;
			font-size: 14px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			display: block;
			box-sizing: border-box;
			background: #fff;

			.top {
				color: #303133;
				font-size: 14px;
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				display: flex;
				flex-direction: row;
				align-items: center;
				padding: 10px 15px 16px;
				box-sizing: border-box;

				.lsx {
					user-select: none;
					color: #303133;
					font-size: 14px;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					display: block;
					box-sizing: border-box;
					width: 5px;
					height: 18px;
					background: rgb(141, 61, 135);
					border-radius: 2px;
					margin-right: 3px;
				}

				.tzjlBox {
					color: #303133;
					font-size: 14px;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					box-sizing: border-box;
					display: flex;
					flex-direction: row;
					flex-wrap: nowrap;
					justify-content: space-between;
					align-items: center;
					flex: 1;

					.tzjlLabel {
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						font-size: 17px;
						font-weight: 800;
						color: #000;
					}

					.tzjlRight {
						color: #303133;
						font-size: 14px;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						box-sizing: border-box;
						display: flex;
						align-items: center;

						.tzjlRightLabel {
							margin: 0;
							-webkit-tap-highlight-color: transparent;
							display: block;
							box-sizing: border-box;
							font-size: 13px;
							font-family: PingFangSC-Regular, PingFang SC;
							font-weight: 400;
							color: #666;
						}

						.tzjlRightIcon {
							color: #303133;
							font-size: 14px;
							margin: 0;
							-webkit-tap-highlight-color: transparent;
							display: block;
							box-sizing: border-box;
							width: 15px;
							height: 15px;
							background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAflJREFUSEu91stu00AUBuD/5KIs8hpdsOI5qoamCBA8QHLGUXZAoRRKaSmUS1VlESlIXkSRsvQKIVSh0gJFPAdiycZZcHGTWDlo0FRigezxOKpXXljz+Z9zztiEc77onD2kgo1GY0FEvvf7/R/zeLlEUCl1AcAxgK9xHC/OA00EW62WN5vNXgEgEflSLpcXe73ezzxJU7dUKXUTwL5GiehzsVhcyoOmgjqNUuo2gD2NAjiJomhpOBz+cklqBRr0DoCXBvkURVHNBbUGDXoXwAt9T0QfRaTm+/7vLEkzgQa9B+CZQY9FZDkLmhk06H0AT02yo2q1utzpdCKbpE6gQR8AeGKQ96PRqB4EQSrqDBr0IYDH+l5EDqfTaX0wGJwmJc0FGvQRgC1T03fj8fhyEpobZOZ1Ito1qU5KpVIt6WDIBTLzBhHtZJlNZ5CZN4lo22AfAFyyGQ8nkJm3iWjzbCwA1G2wv3W2mZ1/n2HmHSLaME1yGIbhis04nK2RCWTmXSJat+3I/4WxBpn5ORGtmUUOJpPJlbSZcwY9z9sTkVWzwNtKpXK12+2Os5bDqoae5+2LyC2zjW/CMLwWBMHEBUsFlVL62NLHl/4cvQ7D8HoeLBVsNpsXC4XCkf61EJEbvu9PXZNZd2m73V6I4/jbPLDUhHnTOHfpPOE/AKzQHVugRd0AAAAASUVORK5CYII=) no-repeat;
							background-size: 100% 100%;
						}
					}
				}
			}

			.foot {
				color: #303133;
				font-size: 14px;
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				box-sizing: border-box;
				display: flex;
				justify-content: space-around;
				text-align: center;

				.v1 {
					color: #303133;
					font-size: 14px;
					text-align: center;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					display: block;
					box-sizing: border-box;

					.n1 {
						text-align: center;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						font-size: 21px;
						color: #8d3d87;
						margin-bottom: 8px;
					}

					.n2 {
						color: #303133;
						font-size: 14px;
						text-align: center;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						margin-bottom: 16px;
					}
				}

				.v2 {
					color: #303133;
					font-size: 14px;
					text-align: center;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					display: block;
					box-sizing: border-box;

					.n1 {
						text-align: center;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						font-size: 21px;
						color: #8d3d87;
						margin-bottom: 8px;
					}

					.n2 {
						color: #303133;
						font-size: 14px;
						text-align: center;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						margin-bottom: 16px;
					}
				}

				.v3 {
					color: #303133;
					font-size: 14px;
					text-align: center;
					margin: 0;
					-webkit-tap-highlight-color: transparent;
					display: block;
					box-sizing: border-box;

					.n1 {
						text-align: center;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						font-size: 21px;
						color: #8d3d87;
						margin-bottom: 8px;
					}

					.n2 {

						color: #303133;
						font-size: 14px;
						text-align: center;
						margin: 0;
						-webkit-tap-highlight-color: transparent;
						display: block;
						box-sizing: border-box;
						margin-bottom: 16px;
					}
				}
			}
		}
	}

	.container {
		color: #303133;
		font-size: 14px;
		margin: 0;
		-webkit-tap-highlight-color: transparent;
		display: block;
		box-sizing: border-box;
		background: #fff;
		opacity: 1;

		.top {
			color: #303133;
			font-size: 14px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			box-sizing: border-box;
			display: flex;
			align-items: center;
			padding-top: 10px;
			padding-left: 15px;

			.lsxx {
				color: #303133;
				font-size: 14px;
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				display: block;
				box-sizing: border-box;
				width: 5px;
				height: 18px;
				background: rgb(141, 61, 135);
				border-radius: 2px;
				margin-right: 3px;
			}

			.gdfw {
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				display: block;
				box-sizing: border-box;
				font-size: 17px;
				font-weight: 800;
				color: rgb(0, 0, 0);
			}
		}

		.list {
			color: #303133;
			font-size: 15px;
			margin: 0;
			-webkit-tap-highlight-color: transparent;
			box-sizing: border-box;
			padding: 0 17px 17px;
			display: flex;
			flex-wrap: wrap;

			.li {
				color: #303133;
				font-size: 14px;
				margin: 0;
				-webkit-tap-highlight-color: transparent;
				box-sizing: border-box;
				width: 25%;
				margin-top: 16px;
				flex-direction: column;
				display: flex;
				align-items: center;
				position: relative;
			}
		}
	}

	.status_bar {
		height: var(--status-bar-height);
		width: 100%;
		// background-color: #f2d7ff;
		background-color: #8D3D87;
	}
</style>