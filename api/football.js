import request from '@/util/ajax'

export function footballWinEvenLoseList() {
    return request({
        url: '/football/list',
        method: 'get'
    })
}


export function calculation(data) {
    return request({
        url: '/football/calculation',
        method: 'post',
		data
    })
}


export function eventList(data) {
    return request({
        url: '/football/eventList',
        method: 'post',
		data
    })
}