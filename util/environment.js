//生产环境
const prod = {
	BASE_API: '/api',
}

//开发环境
const dev = {
	// BASE_API: 'http://192.168.2.3:48080',
	// BASE_API: 'http://192.168.0.100:48080',
	// BASE_API: 'http://192.168.101.3:48080',
	// BASE_API: 'http://127.0.0.1:48080',
	// BASE_API: 'http://172.247.222.162:48080',
	// BASE_API: 'http://43.139.186.187:48080',
	// BASE_API: 'http://192.168.13.88:48080',
	// BASE_API: 'http://27.50.63.27:48080',
	BASE_API: 'https://api.dingdong2.com'
}


/*
=================注意根据需要改变环境================
 */
const environment = dev;
export default {
	BASE_API: environment.BASE_API,
}


/* 后端源码请联系;QQ419367301
飞机Telegram账号:qihang9981 */