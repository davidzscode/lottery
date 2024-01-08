import request from '@/util/ajax'

/**
 * 彩票列表
 */
export function getOmitByType(type) {
    return request({
        url: '/app/omit/get/'+type,
        method: 'get'
    })
}
