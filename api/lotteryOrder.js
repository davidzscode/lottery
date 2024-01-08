
import request from '@/util/ajax'

/**
 * 购彩订单记录
 */
export function getLotteryOrderPage(data) {
	return request({
		url: '/app/lottery/order/list',
		method: 'post',
		data
	})
}

/**
 * 根据id订单订单记录
 */
export function getLotteryOrderById(id) {
	return request({
		url: '/app/lottery/order/get/'+id,
		method: 'get',
	})
}

/**
 * 获取昨日下单中奖的订单提示信息
 */
export function centre() {
	return request({
		url: '/app/lottery/order/centre',
		method: 'get',
	})
}