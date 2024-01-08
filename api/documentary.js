import request from '@/util/ajax'

/**
 * 发单前判断店主余额
 * @param {Object} data
 */
export function preOrder(data) {
	return request({
		url: '/app/documentary/preOrder',
		method: 'post',
		data
	})
}

/**
 * 创建跟单
 * @param {Object} data
 */
export function createDocumentary(data) {
	return request({
		url: '/app/documentary/create',
		method: 'post',
		data
	})
}

/**
 * 跟单排行榜
 */
export function ranking() {
	return request({
		url: '/app/documentary/ranking',
		method: 'get',
	})
}

export function documentaryDetails(id) {
	return request({
		url: '/app/documentary/details/' + id,
		method: 'get',
	})
}

export function queryDocumentaryByType(type) {
	return request({
		url: '/app/documentary/by/' + type,
		method: 'get',
	})
}

export function queryDocumentaryById(id, userId) {
	return request({
		url: '/app/documentary/get/' + id + "/" + userId,
		method: 'get',
	})
}

export function createDocumentaryUser(data) {
	return request({
		url: '/app/documentary/add',
		method: 'post',
		data
	})
}

