import request from '@/util/ajax'

/**
 * 支付订单记录
 */
export function getPayOrderPage(data) {
	return request({
		url: '/app/pay/order/list',
		method: 'post',
		data
	})
}
/**
 * 创建订单
 * @param {Object} data
 */
export function createOrder(data) {
	return request({
		url: '/app/pay/order/create',
		method: 'post',
		data
	})
}