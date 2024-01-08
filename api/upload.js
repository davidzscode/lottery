import request from '@/util/ajax'

/**
 * 文件上传
 * @param {*} data 
 */
export function upload(data) {
    return request({
        url: '/file/upload',
        method: 'post',
        data
    })
}