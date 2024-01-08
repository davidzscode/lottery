import request from '@/util/ajax'

export function addRelationship(type,id) {
    return request({
        url: '/app/follow/relationship/'+type+"/"+id,
        method: 'put',
    })
}

export function followList(type) {
    return request({
        url: '/app/follow/list/'+type,
        method: 'get',
    })
}