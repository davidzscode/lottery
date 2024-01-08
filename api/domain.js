import request from '@/util/ajax'

/**
 * 域名列表
 */
export function domainQuery() {
    return request({
        url: '/app/domain/query',
        method: 'get'
    })
}
