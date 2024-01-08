/**
 * 工具包
 */

function dateFormat(time) {
	let date = new Date(time);
	let year = date.getFullYear();
	// 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
	let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	// 拼接
	// return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
	return year + "-" + month + "-" + day;
}

function timeFormat(time) {
	let date = new Date(time);
	let year = date.getFullYear();
	// 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
	let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	// 拼接
	// return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
	return hours + ":" + minutes;
}

function dateTimeFormat(time) {
	let date = new Date(time);
	let year = date.getFullYear();
	// 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
	let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	// 拼接
	return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

function dateTimeFormat1(time) {
	let date = new Date(time);
	let year = date.getFullYear();
	// 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
	let month = date.getMonth() + 1 < 10 ? (date.getMonth() + 1) : date.getMonth() + 1;
	let day = date.getDate() < 10 ? date.getDate() : date.getDate();
	let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	// 拼接
	return month + "-" + day + " " + hours + ":" + minutes
}

/**生成随机值
 * @param {Object} m 位数
 * @param {Object} n 随机多少个
 */
function randomFromZero(m, n) {
	let exist = []
	for (var i = 0; i < m; i++) {
		exist[i] = 0
	}
	let list = []
	for (var i = 0; i < n;) {
		let index = Math.floor(Math.random() * m);
		if (exist[index] == 0) {
			list.push(index)
			i++;
			exist[index] = 1;
		}
	}
	return list
}

//注数计算规则
function math(m, n) {
	if (m < 0 || n < 0 || m < n) {
		return 0;
	}
	n = n < (m - n) ? n : m - n;
	smath(m, n);
	if (n == 0) {
		return 1;
	}
	var result = m;
	for (var i = 2, j = result - 1; i <= n; i++, j--) {
		result = result * j / i;
	}
	return result;
}

function smath(m, n) {
	if (m < 0 || n < 0 || m < n) {
		return 0;
	}
	n = n < (m - n) ? n : m - n;
	if (n == 0) {
		return 1;
	}
	n = n < (m - n) ? n : m - n;
}

//校验身份证号
function checkIdCard(idCard) {
	idCard = idCard.toUpperCase(); // 转大写
	let len = idCard.length;
	let city = {
		11: "北京",
		12: "天津",
		13: "河北",
		14: "山西",
		15: "内蒙古",
		21: "辽宁",
		22: "吉林",
		23: "黑龙江 ",
		31: "上海",
		32: "江苏",
		33: "浙江",
		34: "安徽",
		35: "福建",
		36: "江西",
		37: "山东",
		41: "河南",
		42: "湖北 ",
		43: "湖南",
		44: "广东",
		45: "广西",
		46: "海南",
		50: "重庆",
		51: "四川",
		52: "贵州",
		53: "云南",
		54: "西藏 ",
		61: "陕西",
		62: "甘肃",
		63: "青海",
		64: "宁夏",
		65: "新疆",
		71: "台湾",
		81: "香港",
		82: "澳门",
		91: "国外"
	};
	//1. 判断长度
	if (len !== 15 && len !== 18) {
		return false;
	}
	//2. 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
	if (!(/(^\d{15})|(^̲\d{17}([0-9]|X))/.test(idCard))) {
		return false;
	}
	//3. 地址码
	if (!city[idCard.substr(0, 2)]) {
		return false;
	}

	let re;
	//4. 生日
	if (len === 15) {
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		let arrSplit = idCard.match(re);

		//检查生日日期是否正确
		let birth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
		let isBirth = (birth.getYear() === Number(arrSplit[2])) && ((birth.getMonth() + 1) === Number(arrSplit[3])) && (
			birth.getDate() === Number(arrSplit[4]));
		if (!isBirth) {
			return false;
		}
	} else if (len === 18) {
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		let arrSplit = idCard.match(re);

		//检查生日日期是否正确
		let birth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		let isBirth = (birth.getFullYear() === Number(arrSplit[2])) && ((birth.getMonth() + 1) === Number(arrSplit[
				3])) &&
			(birth.getDate() === Number(arrSplit[4]) && (birth.getTime() < new Date().getTime()));
		if (!isBirth) {
			return false;
		} else {
			//检验18位身份证的校验码是否正确。
			//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			let valnum;
			let arrInt = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
			let arrCh = ['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'];
			let nTemp = 0,
				i;
			for (i = 0; i < 17; i++) {
				nTemp += idCard.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			if (valnum !== idCard.substr(17, 1)) {
				return false;
			}
		}
	}
	return true;
}

// 银行卡校验
function bankAccountValid(bankCard) {
	const strBin =
		'10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99'
	if (!bankCard) {
		return false;
	} else if (!Number.isInteger(+bankCard)) {
		return false;
	} else if (bankCard.trim().length < 12 || bankCard.trim().length > 19) {
		return false;
	} else if (strBin.indexOf(bankCard.substring(0, 2)) === -1) {
		return false;
	} else {
		return true;
	}
}

function formatNum(num) {
	if (num < 10) {
		return '0' + num;
	}
	return num;
}

module.exports = {
	formatNum: function(m) {
		return formatNum(m);
	},
	checkIdCard: function(m) {
		return checkIdCard(m);
	},
	randomFromZero: function(m, n) {
		return randomFromZero(m, n);
	},
	timeFormat: function(time) {
		return timeFormat(time);
	},
	dateFormat: function(time) {
		return dateFormat(time);
	},
	dateTimeFormat: function(time) {
		return dateTimeFormat(time);
	},
	dateTimeFormat1: function(time) {
		return dateTimeFormat1(time);
	},
	math: function(m, n) {
		return math(m, n);
	},
}