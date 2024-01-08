import request from '@/util/ajax'

/**
 * 登录
 * @param {*} data 
 */
export function login(data) {
    return request({
        url: '/app/user/login',
        method: 'post',
        data
    })
}

/**
 * 注册
 * @param {*} data 
 */
export function register(data) {
    return request({
        url: '/app/user/register',
        method: 'post',
		data
    })
}

/**
 * 发送短信
 * @param {*} data 
 */
export function send(data) {
    return request({
        url: '/app/user/send',
        method: 'post',
		data
    })
}

/**
 * 退出
 * @param {*} data 
 */
export function logout() {
    return request({
        url: '/user/logout',
        method: 'get',
    })
}

/**
 * 好友列表
 * @param {*} id 
 */
export function friendList(id) {
    return request({
        url: '/app/user/friend/'+id,
        method: 'get'
    })
}

/**
 * 修改密码
 * @param {*} data 
 */
export function changePwd(data) {
    return request({
        url: '/app/user/change/pwd',
        method: 'put',
		data
    })
}
/**
 * 修改密码
 * @param {*} data 
 */
export function changeUser(data) {
    return request({
        url: '/app/user/change/info',
        method: 'put',
		data
    })
}

/**
 * 实名
 * @param {Object} data
 */
export function real(data) {
    return request({
        url: '/app/user/real',
        method: 'put',
		data
    })
}

/**
 * 获取用户信息
 */
export function getUser() {
    return request({
        url: '/app/user/get',
        method: 'get',
    })
}

/**
 * 代理
 */
export function agent(data) {
    return request({
        url: '/app/user/agent',
        method: 'post',
		data
    })
}

export function getUserByNickName(data) {
    return request({
        url: '/app/user/get/nickname',
        method: 'post',
		data
    })
}

export function getTenantId(uid) {
    return request({
        url: '/app/user/get/tenant/'+uid,
        method: 'get',
    })
}

export function binding(data) {
    return request({
        url: '/app/user/binding',
        method: 'post',
		data
    })
}

export function checkPhoneIsExist(data) {
    return request({
        url: '/app/user/phone/exist',
        method: 'post',
		data
    })
}

export function getHongbao(data){
	return request({
		url: '/app/hongbao/list',
		method:'post',
		data
	})
}

export function recvHongbao(data){
	return request({
		url: '/app/hongbao/recv',
		method:'post',
		data
	})
}

/**
 * 打开app时初始化
 * @param {*} data 
 */
export function initConfig() {
	return request({
	    url: '/app/user/initConfig',
	    method: 'post',
	})
}

/**
 * 用户分享app
 * @param {*} data 
 */
export function share() {
	return request({
	    url: '/app/user/share',
	    method: 'post',
	})
}


/**
 * 申请加盟
 * @param {*} data 
 */
export function join(data) {
	return request({
	    url: '/app/user/join',
	    method: 'post',
		data
	})
}

/**
 * 获取代理的推广数据
 * @param {*} data
 */
export function agentData(data) {
    return request({
        url: '/app/user/agentData',
        method: 'post',
		data
    })
}