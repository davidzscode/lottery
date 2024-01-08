/**
 * 路由守卫
 */
import {
	RouterMount,
	createRouter
} from 'uni-simple-router';

const router = createRouter({
	platform: process.env.VUE_APP_PLATFORM,
	routes: [...ROUTES]
});
//全局路由前置守卫
//配置白名单
const whiteList = ['/', '/pages/user/login', '/pages/user/register', '/pages/user/recover','/pages/personal/download']
router.beforeEach((to, from, next) => {
	
	if (uni.getStorageSync("vue_authtoken")) {
		if (to.path === '/pages/user/login') {
			next({
				path: '/pages/home/home'
			})
		} 
		else {
			next()
		}
	} else {
		if (whiteList.indexOf(to.path) !== -1) {
			next()
			console.log('next2');
		} else {
			console.log('next3');
			next('/pages/user/login')
		}
	}
});
// 全局路由后置守卫
router.afterEach((to, from) => {

})

export {
	router,
	RouterMount
}
