# hx-navbar 适用于 uni-app 项目的头部导航组件

导航栏组件，主要用于头部导航，组件名：hx-navbar

本组件目前兼容微信小程序、H5、5+APP。

### 本组件支持模式：
1. 普通固定顶部导航  
2. 透明导航  
3. 透明固定顶部导航 
4. 不固定普通导航
5. 背景颜色线性渐变
6. 滑动显示背景
7. 左、中、右3个插槽；可关闭左右插槽使中间插槽铺满导航，实现高度自定义的导航需求

### 提示
因为有的用户还在使用v1.0，建议前往[v1.0项目示例及文档](https://gitee.com/code_yu/hx-navbar/tree/V1.0)

### QQ交流群 954035921
如有问题可进群发图讨论

### 使用前提

建议下载 示例项目 运行

### 使用方式	
页面使用需在 ``` script ``` 中引用组件
``` javascript
import hxNavbar from "@/components/hx-navbar/hx-navbar"
export default {
    components: {hxNavbar}
}
```

全局使用需在 ``` main.js ```  中注册组件
``` javascript
import hxNavbar from "./components/hx-navbar/hx-navbar"
Vue.component('hx-navbar',hxNavbar)

```

### 滑动切换注意事项
必须使用 `ref` 和 `onPageScroll` 事件，否则滑动切换无效

``` html
<hx-navbar ref="hxnb" :config="config"/>
```

``` javascript
export default {
	onPageScroll(e) {
		// 重点，用到滑动切换必须加上
		this.$refs.hxnb.pageScroll(e);
	}
}

```


### 属性
#### 基本属性 
| 名称                        | 类型            | 默认值                | 描述                                               |
| ----------------------------|--------------- | ---------------------- | ---------------------------------------------------|
| back                   	  | Boolean         | true          | 返回上一页，如果为首页请设置为`false`|
| backTxt                     | String         | 返回           | 返回图标边的文字,小程序无效 |
| backTabPage                 | String         | ''           | 返回指定的 tab 页面 |
| backPage                    | String         | ''           | 返回指定的普通页面 |
| height                   	  | String         | 44px          | 导航栏高度（不包含状态栏高度）|
| fixed                       | Boolean        | true         | 固定头部 |
| barPlaceholder              | Boolean        | true         | 导航栏占位符，一般使用滑动显示导航栏时才为false |
| title                       | String,Array    | -             | 导航标题,有两种写法 `title:'我爱新疆'` 和 `title:['我爱新疆','欢迎您']`,数组可满足滑动显示，滑动隐藏，滑动切换                                   |
| font                        | String         | ''       | 图标字体Class名，如何使用自定义图标请查看 `示例项目1`                                       |
| fontSize                    | String         | '18px'       | 文字大小，一般不需要啊调整                          |
| color                       | String,Array    | #333333       | 文字颜色（如果需要屏幕滑动后变色，参数则为数组，例子：`['#000000','#ffffff']`）  |
| backgroundColor             | Array          | #ffffff          | 背景颜色;参数一：透明度;参数二：背景颜色（array则为```线性渐变[1,['#24bdab','#80c54c']]```） |
| slideBackgroundColor        | Array          | null          | 滑动后背景颜色,参数一：透明度;参数二：背景颜色,长度必须和上一个一样长（array则为```线性渐变[1,['#24bdab','#80c54c']]```） |
| backgroundColorLinearDeg    | String         | 45             | 导航背景线性渐变角度                                       |
| backgroundImg   			  | String         | null             | 背景图片,单一背景图`['/static/xj.jpg']`, 滑动切`['/static/xj.jpg','/static/xk.jpg',1,1]` 参数说明：第一张图片，第二张图，第一张图透明度，第二张图透明度 |
| leftButton   			      | Obj            | null             | 左侧按钮，详细查看下方 ```leftButton```|
| rightButton   			  | Obj            | null             | 右侧按钮，详细查看下方 ```rightButton```|
| search   			          | Obj            | null             | 搜索栏，详细查看下方 ```search```|
| shadow                      | Boolean         | false         | 导航栏阴影          |
| border                      | Boolean         | false         | 导航栏边框                           |

#### 关于状态栏的属性
| 名称                        | 类型            | 默认值                | 描述                                               |
| ----------------------------|--------------- | ---------------------- | ---------------------------------------------------|
| statusBar                   | Boolean         | true       		   | 包含状态栏												|
| statusBarFontColor          | Array,String   | #000000               | 状态栏字体颜色，只支持```#000000 ```和```#FFFFFF```（如果需要屏幕滑动变色，参数则为数组，例子：```['#000000','#ffffff']```）|
| statusBarBackground          | Array,String   | null               | 状态栏背景色，（如果需要屏幕滑动变色，参数则为数组，例子：```['#24bdab','#80c54c']```）|

``` javascript
// statusBarBackground 多种用法
// 普通用法
statusBarBackground: '#80c54c',
// 滑动后由透明转为有颜色 '#80c54c'
statusBarBackground: ['', '#80c54c'],
// 滑动变色，一种颜色变另一种颜色
statusBarBackground: ['#24bdab','#80c54c'],
```

### 插槽属性
> 可下载项目示例查看

| 名称                | 类型      | 默认值      | 描述                                               |
| -------------------|-----------| -----------| ---------------------------------------------------|
| leftSlot           | bool      | false      | 左插槽	         |
| leftSlotSwitch     | bool      | false      | 左滑动切换插槽             |
| centerSlot         | bool   	 | false      | 中间插槽（注：设置后标题`title`属性失效）			 |
| centerSlotSwitch   | bool   	 | false      | 中间滑动切换插槽 			|
| rightSlot      	 | bool		 | false      | 右插槽 |
| rightSlotSwitch    | bool 	 | false      | 右滑动切换插槽           |
| maxSlot      		 | bool		 | false      | 填充满导航栏的插槽，使用该插槽就不要用其他插槽 （我认为是个无意义的插槽）|
| maxSlotSwitch 	 | bool 	 | false      | 填充满导航栏的滑动切换插槽          |



### leftButton 和 rightButton 属性
| 名称         | 类型            | 默认值                | 描述                                               |
| -------------|--------------- | ---------------------- | ---------------------------------------------------|
| key           | num,string         | null       		   | 唯一标识，按钮点击事件会用到	         |
| icon          | String  			 | null               | 字体图标，如果使用自己的请先设置 `font` 属性             |
| txt         	| String   		| null               | 图标旁的文字，一般地址功能和城市功能才用到 |
| color         | String   			| 随导航栏字体颜色      | icon 图标的颜色，一般动态设置如收藏功能 |
| position      | left, right 		| right               |  图标居于文字的 left 或 right |
| @clickBtn     | 按钮点击事件 		|  返回被点击对象    |  按钮点击事件，可根据key来识别，进行操作                |

可以设置多个按钮，建议每边不超过3个按钮，带文字的不超过2个按钮，也就是两边总共不超过6个按钮
``` html
<hx-navbar ref="hxnb" :config="config" @clickBtn="onClickBtn" />
```

``` javascript

export default {
		data() {
			return {
				config:{
					title: '这里是新疆',
					color: '#ffffff',
					//背景颜色;参数一：透明度（0-1）;参数二：背景颜色（array则为线性渐变，string为单色背景）
					backgroundColor: [1,['#a9a1ff','#6970ff','#ff55ff','#ff9999']],
					// 滑动屏幕后切换颜色，注意颜色为数组时长度必须一样，还有使用滑动切换必须监听 onPageScroll 事件
					slideBackgroundColor: [0,['#a9a1ff','#6970ff','#ff55ff','#ff9999']],
					// 状态栏 ，数组则为滑动变色
					statusBarBackground:['','#ffffff'],
					rightButton:[{
						key: 'btn1',
						icon: '&#xe651;',
						position: 'left'
					},{
						key: 'btn2',
						icon: '&#xe64c;',
						position: 'left'
					},{
						key: 'btn3',
						icon: '&#xe6eb;',
						position: 'left'
					}],
				},
			}
		},
		onPageScroll(e) {
			// 重点，用到滑动切换必须加上
			this.$refs.hxnb.pageScroll(e);
		},
		methods: {
			onClickBtn(data){
				//console.log(data);
				uni.showToast({
				    title: `key为 ${data.key} 的按钮`,
					icon:'none',
				    duration: 1300
				});
			},
		}
	}
```


### search 属性

设置后 title 将失效

| 名称         | 类型            | 默认值                | 描述                                               |
| -------------|--------------- | ---------------------- | ---------------------------------------------------|
| value         | string         | null       		   | 搜索框默认值	         |
| placeholder   | String  			 | 请输入搜索内容    | 搜索框提示信息             |
| disabled      | bool   		| false 没禁止     | 是否禁止输入， 一般会禁止输入然后跳转到指定的搜索页|
| @searchClick   | 输入框点击事件 	|      | 当点击了搜索框时触发，这时候可以进行操作，如跳转到指定的搜索页         |
| @searchConfirm   | 输入后点完成的事件 	| {value: txt}     | 当点击了完成或回车则会触发，返回输入的内容         |

``` html
<hx-navbar ref="hxnb" :config="config" @searchConfirm="searchConfirm" @searchClick="searchClick"/>

```

``` javascript

export default {
		data() {
			return {
				config:{
					back: false,
					title: '这里是新疆',
					color: '#ffffff',
					search: {
						value:'',
						placeholder: '',
						disabled: false
					},
					leftButton:[{
						key: 'btn1',
						icon: '&#xe651;',
						txt: '新疆',
						position: 'left'
					}],
				},
			}
		},
		methods: {
			// 当输入完点击完成时触发
			searchConfirm(data){
				//console.log(data.value);
				uni.showToast({
				    title: `输入了${data.value}`,
					icon:'none',
				    duration: 1300
				});
			},
			// 当点击了搜索框时触发
			searchClick(data){
				uni.showToast({
				    title: `点击了搜索框`,
					icon:'none',
				    duration: 1300
				});
			},
		}
	}
```

## 一些简单使用例子

### 简单使用
``` html
<hx-navbar :config="{title: '这里是新疆'}"/>

<hx-navbar :config="{title: ['这里是新疆', '新疆欢迎你']}"/>

<hx-navbar :config="{title: ['', '这里是新疆']}"/>

<hx-navbar :config="{title: ['这里是新疆', '']}"/>
```


### 背景颜色线性渐变、头部固定
``` html
<hx-navbar :config="config"/>
```

``` javascript
export default {
	data() {
		return {
			config:{
				title: '这里是新疆',
				color: '#ffffff',
				//背景颜色;参数一：透明度（0-1）;参数二：背景颜色（array则为线性渐变，string为单色背景）
				backgroundColor: [1,['#a9a1ff','#6970ff','#ff55ff','#ff9999']],
			}
		}
	}
}
```

### 滑动切换背景
``` html
<hx-navbar ref="hxnb" :config="config" />
```

``` javascript
export default {
	data() {
		return {
			config:{
				title: '这里是新疆',
				color: '#ffffff',
				//背景颜色;参数一：透明度（0-1）;参数二：背景颜色（array则为线性渐变，string为单色背景）
				backgroundColor: [1,['#a9a1ff','#6970ff','#ff55ff','#ff9999']],
				// 滑动屏幕后切换颜色，注意颜色为数组时长度必须一样，还有使用滑动切换必须监听 onPageScroll 事件
				slideBackgroundColor: [1,['#24bdab','#24bdab','#80c54c','#80c54c']],
			},
		}
	},
	onPageScroll(e) {
		// 重点，用到滑动切换必须加上
		this.$refs.hxnb.pageScroll(e);
	}
}
```

### 滑动隐藏背景

``` html
<hx-navbar ref="hxnb" :config="config" />
```

``` javascript
export default {
	data() {
		return {
			config:{
				title: '这里是新疆',
				color: '#ffffff',
				//背景颜色;参数一：透明度（0-1）;参数二：背景颜色（array则为线性渐变，string为单色背景）
				backgroundColor: [1,['#a9a1ff','#6970ff','#ff55ff','#ff9999']],
				// 滑动屏幕后切换颜色，注意颜色为数组时长度必须一样，还有使用滑动切换必须监听 onPageScroll 事件
				slideBackgroundColor: [0,['#a9a1ff','#6970ff','#ff55ff','#ff9999']],
			},
		}
	},
	onPageScroll(e) {
		// 重点，用到滑动切换必须加上
		this.$refs.hxnb.pageScroll(e);
	}
}
```

### 设置背景图
``` html
<hx-navbar :config="config" />
```

``` javascript
export default {
	data() {
		return {
			config:{
				title: '这里是新疆',
				color: '#ffffff',
				// 背景图片（array则为滑动切换背景图，string为单一背景图）
				// backgroundImg: ['/static/xj.jpg','/static/logo.png'],
				backgroundImg: '/static/xj.jpg',
			},
		}
	}
}
```

### 滑动切换背景图
``` html
<hx-navbar ref="hxnb" :config="config" />
```

``` javascript
export default {
	data() {
		return {
			config:{
				title: '这里是新疆',
				color: '#ffffff',
				// 背景图片（array则为滑动切换背景图，string为单一背景图）
				backgroundImg: ['/static/xj.jpg','/static/xk.jpg',1,0.4],
			},
		}
	},
	onPageScroll(e) {
		// 重点，用到滑动切换必须加上
		this.$refs.hxnb.pageScroll(e);
	}
}
```