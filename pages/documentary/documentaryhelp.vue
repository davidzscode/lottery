<template>

	<view class="">
		<hx-navbar :config="config" />
		<view class="about">
			<view class="list">
				<view class="item" id="one" @click="showText($event)">
					<view class="li">
						<view class="title">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>什么是复制跟单？</text>
						</view>
						<view class="textBox" v-show="status==='one'">
							<view class="text">
								<p>复制跟单是指方案发起人将投注方案以公开或保密的形式展示出来，其他彩民按照此投注方案来购买的一种购彩方式。</p>
							</view>
						</view>
					</view>
				</view>
				<view class="item">
					<view class="li">
						<view class="title" id="two" @click="showText">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>复制跟单为彩民带来什么利益？</text>
						</view>
						<view class="textBox">
							<view class="text" v-show="status==='two'">
								<p>方案发起人：可获得投注方案的中奖奖金，以及跟单人中奖奖金的提成。</p>
								<p>复制跟单人：不用自己研究，可获得投注方案的中奖奖金，需扣除小部分的佣金给方案发起人。</p>
							</view>
						</view>
					</view>
				</view>
				<view class="item">
					<view class="li">
						<view class="title" id="three" @click="showText">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>复制跟单的佣金比例是怎么样的？</text>
						</view>
						<view class="textBox">
							<view class="text" v-show="status==='three'">
								<p>1)奖金抽成规则说明:</p>
								<p>①跟单用户税后奖金乘以佣金比例，作为发单人中奖后的佣金金额；</p>
								<p>②由于赔率变动，跟投订单的赔率与推荐订单相比可能略有变动，以实际出票的赔率为准；</p>
								<p>③佣金比例由方案发起人在发单时自行设置比例（最高为5%，公开单为0%）,佣金金额=中奖金额*佣金比例，此外店主与平台共抽取中奖金额的4%作为手续费；</p>
								<p>④当奖金小于本金的1.6倍时，将不收取佣金。</p>
								<p>2)预计回报说明：</p>
								<p>预计回报是该用户当前最新推荐方案的最高回报倍数，是根据推荐订单实单支付时的最高赔率计算而来，跟单时赔率如有变化，以实际出票的赔率为准。当奖金小于本金的1.6倍时，将不收取佣金。
								</p>
							</view>
						</view>
					</view>
				</view>
				<view class="item">
					<view class="li">
						<view class="title" id="four" @click="showText">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>方案发起人如何发单？</text>
						</view>
						<view class="textBox">
							<view class="text" v-show="status==='four'">
								<p>1)立即发单的功能说明：</p>
								<p>①方案发起人选择好投注内容后，在投注时，选择“我要发单”，进行保密或公开设置、填写完方案宣言，投注支付成功后即生成并发起跟单。</p>
								<p>②方案发起人可以在底部导航菜单中点击“跟单”，在跟单大厅的左上角点击“发单”、“立即发单”，选择好投注内容后，选择“立即发单”，进行保密设置、填写完方案宣言，投注支付成功后即会生成跟单方案。
								</p>
								<p>保密设置：设置为“截止后公开”，跟单人中奖后需根据佣金比例支付相应的佣金；设置为“公开”，跟单人中奖后不需要支付佣金。</p>
								<p>2)预约发单的功能说明：</p>
								<p>预约发单功能是先宣传后上传方案的特殊玩法，预约单可获得更长的展示时间来吸引跟单，旨在优化发单推荐及跟单的体验，此功能可以达到整体提高跟单数量及推荐单质量的目的。</p>
								<p>如何预约发单？</p>
								<p>①推荐彩种仅限竞足，发单人需在发单后的36小时内上传方案，超过36小时未上传方案，预约发单将会超时撤单；</p>
								<p>②预约单只可选择上传，比赛投注截止30分钟前的方案（多场比赛以最早截止的场次为准）；</p>
								<p>③同时发布的预约单只可存在1单；</p>
								<p>④预约发单若没有上传方案，则当月不能再发起预约发单。累计3次没有上传方案，则永久取消预约发单的权限。</p>
								<p>什么情况下会出现撤单？</p>
								<p>①发单人在方案未上传前也可主动联系客服申请撤单，跟单人每月有三次撤单权限，超过三次则不可撤单；</p>
								<p>②超过截止时间未上传方案将自动撤消推荐单及跟投单全部退款；</p>
								<p>③已上传的方案没有撤单的权限。</p>
							</view>
						</view>
					</view>
				</view>
				<view class="item">
					<view class="li">
						<view class="title" id="five" @click="showText">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>彩民如何复制跟单？</text>
						</view>
						<view class="textBox">
							<view class="text" v-show="status==='five'">
								<p>1)在跟单页面选定方案后，设置跟单投注倍数，投注成功后，即该方案复制跟单成功。另外，跟单用户根据自己的跟单习惯，设置相应条件，对自己信赖的大咖进行自动跟单。</p>
								<p>2)发起跟投则证明同意此跟投协议，跟投方案中奖后，方案推荐人有权按照已设置好的佣金比例对跟单中奖利润抽成，跟投方案未中奖的一切损失由跟投人承担。</p>
								<p>3)奖金派发时将自动减去方案推荐人抽成的金额，并在我的交易明细中显示。</p>
								<p>4)计算公式：方案推荐人的佣金金额=跟单人税后奖金*佣金比例；</p>
								<p>5)推荐订单显示的回报倍数为推荐用户实单支付时的赔率，如跟单时赔率略有变动，以实际赔率为准；</p>
								<p>6)本规则在法律允许的情况下最终解释权归本平台所有。</p>
								<p>郑重声明：本平台严禁用户通过任何方式建立私下联系并脱离本平台进行任何金钱类交易。此类行为不会受到本平台的保护。若已经有用户上当受骗请立刻报警，本平台将会配合警方进行调查，有必要时会向警方提供所需相关的资料。
								</p>
							</view>
						</view>
					</view>
				</view>
				<view class="item" :current=current>
					<view class="li">
						<view class="title" id="six" :current=current @click="showText">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>发单战绩统计是怎么样的？</text>
						</view>
						<view class="textBox">
							<view class="text" v-show="status==='six'">
								<p>1)若方案未中奖，该方案统计发单数、命中数。</p>
								<p>2)若方案中奖，中奖金额<=本金，该方案统计发单数，不统计命中数。本金<中奖金额<本金1.6倍，该方案统计不统计发单数、命中数。中奖金额>
										=1.6倍，该方案统计发单数、命中数。</p>
							</view>
						</view>
					</view>
				</view>
				<view class="item">
					<view class="li">
						<view class="title" id="seven" @click="showText">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>发单有哪些限制条件？</text>
						</view>
						<view class="textBox">
							<view class="text" v-show="status==='seven'">
								<p>1)任何用户皆可发起推荐；</p>
								<p>2)推荐的比赛适用于竞彩足球的一系列玩法；</p>
								<p>3)推荐方案的自购金额需大于等于100元；</p>
								<p>4)投注截止十分钟之前停止推荐；</p>
								<p>5)同场次比赛，只能推荐一次，如出现破坏平台机制的情况，平台有权终止该用户的推荐权限并封闭其账号；</p>
								<p>6)推荐用户有权决定推荐方案的保密状态以及中奖后税后奖金的佣金比例10%</p>
								<p>7)推荐订单的跟投奖励在跟投用户方案派奖后的24小时之内派发，在我的历史战绩中查看明细；</p>
								<p>8)每个推荐用户一天只能推荐3单，如出现恶意刷单情况本平台有权利暂停其推荐权限。</p>
							</view>
						</view>
					</view>
				</view>
				<view class="item">
					<view class="li">
						<view class="title" id="eight" @click="showText">
							<uni-icons type="star-filled" size="20" color="rgb(199, 199, 199)"></uni-icons>
							<text>刷单惩罚机制？</text>
						</view>
						<view class="textBox">
							<view class="text" v-show="status==='eight'">
								<p>若有以下情况，则视为刷单：</p>
								<p>1)同一用户，使用多个账号对相同场次赛果全包；</p>
								<p>2)同一用户，相同方案内容重复发单 。</p>
								<p>刷单用户将被取消奖励、屏蔽发单方案、封停账号。</p>
							</view>
						</view>
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
				config: {
					title: '跟单规则',
					backgroundColor: [1, '#8D3D87'],
					color: '#ffffff',
					back: true,
				},
				status: false,
				current: -1,
			}
		},
		methods: {
			showText(e) {
				// console.log('显示' + e.target.id)
				// if(e.target.id)
				// this.status=e.target.id
				// console.log(e.currentTarget.id + '@@@@' + this.status)
				if (this.status === e.currentTarget.id) {
					this.status = ""
				} else {
					this.status = e.currentTarget.id
				}

			}

		}
	}
</script>

<style lang="scss" scoped>
	.about {
		padding: 16px;

		.list {
			.item {
				.li {
					.title {
						padding: 8px;
						color: #8f8f8f;
						border-bottom: 1px solid #e1d9d9;
						font-size: 16px;
					}

					.textBox {
						.text {
							padding: 16px;
							font-size: 16px;
							color: #8f8f8f;
							line-height: 40px;
						}
					}
				}

			}
		}
	}
</style>