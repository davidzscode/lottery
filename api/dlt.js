import request from '@/util/ajax'

export function calculation(data) {
    return request({
        url: '/grand/calculation',
        method: 'post',
		timeout:5000000,
		data
    })
}