import request from '@/util/ajax'

/**
 * 提现申请
 */
export function add(data) {
	return request({
		url: '/app/withdrawal/add',
		method: 'post',
		data
	})
}

/**
 * 提现记录列表
 */
export function withdrawalRecord() {
	return request({
		url: '/app/withdrawal/list',
		method: 'get'
	})
}
