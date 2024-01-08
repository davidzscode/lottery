import request from '@/util/ajax'

/**
 * 彩票列表
 */
export function ballList() {
    return request({
        url: '/app/ball/list',
        method: 'get'
    })
}
