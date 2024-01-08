import request from '@/util/ajax'


/**
 * 排列下单
 * @param {Object} data
 */
export function place(data,type) {
	return request({
		url: '/app/permutation/place/'+type,
		method: 'post',
		data
	})
}

/**
 * 历史排列开奖记录
 */
export function record(type) {
	return request({
		url: '/app/permutation/record/'+type,
		method: 'get',
	})
}

/**
 * 获取排列期号接口
 */
export function getIssueNo(type) {
	return request({
		url: '/app/permutation/issue/'+type,
		method: 'get',
	})
}