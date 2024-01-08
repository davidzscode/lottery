import request from '@/util/ajax'

/**
 * 公告列表
 */
export function noticeList() {
    return request({
        url: '/app/notice/list',
        method: 'get'
    })
}