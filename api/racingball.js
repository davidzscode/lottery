import request from '@/util/ajax'

/**
 * 竞球下单接口
 */
export function createOrder(data) {
    return request({
        url: '/app/racing/order/create',
        method: 'post',
		data
    })
}
