import request from '@/util/ajax'

/**
 * 店铺列表
 */
export function shopList(data) {
	return request({
		url: '/app/shop/list',
		method: 'post',
		data
	})
}




/* 后端源码请联系;QQ419367301
飞机Telegram账号:qihang9981 */