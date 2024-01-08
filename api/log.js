import request from '@/util/ajax'

/**
 * 日志列表
 */
export function agentLogList(data) {
    return request({
        url: '/app/log/list',
        method: 'post',
		data
    })
}


/* 后端源码请联系;QQ419367301
飞机Telegram账号:qihang9981 */