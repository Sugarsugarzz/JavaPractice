## Vue

### 1. 概述

Vue 是一套用于构建用户界面的**渐进式框架**，发布与2014年2月，与其他大型框架不同的是，Vue被设计为可以字底向上逐层应用。**Vue的核心库只关心视图层**。不仅易于上手，还便于与第三方库（如：vue-router：跳转，vue-resoure：通信，vuex：管理）或既有项目整合。

官网：https://cn.vuejs.org/v2/guide/

SoC原则（关注度分离原则）

HTML + CSS + JS：视图 `给用户看，刷新后台给的数据`

网络通信：axios

页面跳转：vue-router

状态管理：vuex

Vue-UI：飞冰

### 2. 前端知识体系

#### 2.1 前端三要素

- HTML（结构）：超文本标记语言，决定网页的结构和内容
- CSS（表现）：层叠样式表，设定网页的表现样式
- JavaScript（行为）：弱类型脚本语言，源码不需经过编译，而是由浏览器解释运行，用于控制网页的行为

#### 2.2 结构层（HTML）

略

#### 2.3 表现层（CSS）

**CSS缺陷：**

- 语法不够强大，无法嵌套书写，模块化开发中需要写很多重复的选择器
- 没有变量和合理的样式复用机制，使逻辑上相关的属性值必须以字面量的形式重复输出，导致难以维护

**解决：CSS预处理器**

就是，用一种专门的编程语言，进行 Web 页面样式设计，再通过编译器转化为正常的 CSS 文件，以供项目使用

**常用的CSS预处理器**

- SASS：基于Ruby
- LESS：基于NodeJS（推荐）

#### 2.4 行为层（JavaScript）

无需编译，由浏览器解释执行。

##### JavaScript框架

- jQuery：简化DOM操作，但DOM操作频繁，影响前端性能
- Angular：将MVC模式搬到前端并增加模块发开发概念
- React：提出虚拟DOM，减少真实DOM操作
- **Vue**：渐进式框架，综合了Angular（模块化）和React（虚拟DOM）的有点
- **Axios**：前端通信框架，Vue的边界明确，就是为了处理DOM，所以不具备通信能力，需要额外使用一个通信框架与服务器交互。

##### UI框架

- Ant-Design：阿里，基于React
- ElementUI、iview、ice：饿了么，基于Vue
- Bootstrap：Twitter
- AmazeUI：妹子UI，HTML5跨屏

##### JavaScript构建工具

- Babel：JS编译工具，用于浏览器不支持的ES新特性，比如用于编译TypeScript
- WebPack：模块打包器，主要作用是打包、压缩、合并以及按序加载

### 2.5 三端统一

##### 混合开发（Hybrid App）

实现一套代码三端统一（PC、Android、IOS），并能够调用到设备底层硬件（如：传感器、GPS、摄像头等），打包方式主要有以下两种：

- 云打包：HBuild -> HBuildX，DCloud出品；API Cloud
- 本地打包：Cordova（前身PhoneGap）

##### 微信小程序

见微信官网，小程序UI开发框架：WeUI

#### 2.6 后端技术

NodeJS

- Express：NodeJS框架
- Koa：Express简化版
- NPM：项目综合管理工具，类似Maven
- YARN：NPM的替代方案，蕾西Maven与Gradle的关系

#### 2.7 主流前端框架

Vue.js

##### iView

##### ElementUI

### 3. 前后分离史

#### 3.1后端为主的MVC时代

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918100440600.png" alt="image-20200918100440600" style="zoom:25%;" />

缺点：

- 前端开发重度依赖开发环境，开发效率低下。前后协作有两种模式：
  - 第一种，前端写Demo，后端套模板。
  - 另一种，前端负责浏览器端所有开发和服务器端View层模板开发。
- 前后端职责纠缠不清。
- 对前端发挥有局限性。

#### 3.2 基于Ajax带来的SPA时代

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918100745140.png" alt="image-20200918100745140" style="zoom:25%;" />

**这个时代出现浏览器端的分层架构**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918100832459.png" alt="image-20200918100832459" style="zoom:25%;" />

缺点：

- 前后端接口的约定
- 前端开发的复杂度控制

#### 3.3 前端为主的MV*时代（大前端时代）

MV*模式如下：

- MVC（同步通信为主）：Model、View、Controller
- MVP（异步通信为主）：Model、View、Presenter
- MVVM（异步通信为主）：Model、View、ViewModel

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918101203082.png" alt="image-20200918101203082" style="zoom:25%;" />

缺点：

- 代码不能复用。后端依旧需要对数据做各种校验，校验逻辑无法复用浏览器端的代码。
- 全异步，需要服务器做同步渲染的降级方案。
- 性能并非最佳。
- SPA不能满足所有需求。

#### 3.4 NodeJS全栈时代

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918101515408.png" alt="image-20200918101515408" style="zoom:25%;" />

### 4. Vue

#### 4.1 MVVM模式的实现者

- Model：模型层，在这里表示 JavaScript 对象
- View：视图层，在这里表示DOM（HTML操作的元素）
- ViewModel：连接视图和数据的中间件，Vue.js 就是MVVM中的ViewModel层的实现者

在MVVM架构中，不允许数据和视图直接通信，**只能通过ViewModel来通信**，而ViewModel就是定义了一个Observere观察者。

- ViewModel能够观察到数据的变化，并对视图对应的内容进行更新
- VIewModel能够监听到视图的变化，并能够通知数据发生变化。

至此，Vue.js 就是MVVM的实现者，核心就是：**实现了DOM监听与数据绑定**。

#### 4.2 第一个Vue程序

##### 下载地址

- 开发版本
  - 包含完整警告和调试模式：https://vuejs.org/js/vue.js
  - 删除警告：https://vuejs.org/js/vue.min.js
- CDN
  - `<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.js"></script>`
  - `<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>`

##### 什么是 MVVM

MVVM（Model-View-ViewModel）是一种软件架构设计模式，是一种简化用户界面的**事件驱动编程方式**。

MVVM源自于经典的MVC模式，**其核心是 ViewModel 层**，负责转换 Model 中的数据对象来让数据变得更容易管理和使用。其作用如下：

- 该层向上与视图层进行双向数据绑定
- 向下与Model层通过接口请求进行数据交互

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918103818502.png" alt="image-20200918103818502" style="zoom:25%;" />

##### MVVN的组成结构

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918103524020.png" alt="image-20200918103524020" style="zoom:20%;" />

##### Vue程序

1. 创建一个HTML文件
2. 引入Vue.js
3. 创建一个Vue实例
4. 将数据绑定到页面元素

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<!--View层，模板-->
<div id="app">
    {{message}}
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        /*Model层：数据*/
        data: {
            message: "hello, vue!",
        }
    });
</script>

</body>
</html>
```

##### v-bind

**简写 `:`**

目前已将数据和 Dom 建立了关联，所有东西都是响应式的。在控制台操作对象属性，界面可以实时更新。

可以使用 `v-bind` 来绑定元素特性。

v-bind等被称为指令。指令带有前缀 v-，以表示它们是Vue提供的特殊特性。

`v-bind:title`该指令的意思是：将这个元素节点的title特性与Vue实例的message属性保持一致。

```html
<!--View层，模板-->
<div id="app">
    <span v-bind:title="message">
        鼠标悬停几秒看此动态绑定的提示信息！
    </span>
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        /*Model层：数据*/
        data: {
            message: "hello, vue!",
        }
    });
</script>
```

##### v-if / v-else-if / v-else

条件判断语句

```html
<!--View层，模板-->
<div id="app">

    <h1 v-if="type==='A'">A</h1>
    <h1 v-else-if="type==='B'">B</h1>
    <h1 v-else-if="type==='D'">D</h1>
    <h1 v-else>C</h1>
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data: {
            type: 'B'
        }
    });
</script>
```

##### v-for

```html
<!--View层，模板-->
<div id="app">

    <li v-for="(item, index) in items">
        {{item.message}}  --  {{index}}
    </li>
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data: {
            items: [
                {message: 'Sugar-Java'},
                {message: 'Sugar-Python'}
            ]
        }
    });
</script>
```

##### v-on 绑定事件

**简写  `@`**

将时间绑定到Vue中的`methods`中的方法事件中。

```html
<!--View层，模板-->
<div id="app">
    <button v-on:click="sayHi">Click Me</button>
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data: {
            message: "Sugar"
        },
        methods: { // 方法必须定义在Vue的methods对象中，v-
            sayHi: function () {
                alert(this.message);
            }
        }
    });
</script>
```

### 5. 表单双向绑定、组件

#### 5.1 v-model 双向数据绑定

Vue.js 是一个 MVVM 框架，即数据双向绑定，即**当数据发生变化的时候，视图也就发生变化，当视图发生变化的时候，数据也会跟着同步变化**。

数据双向绑定，一定是对于 UI 控件来说的，非 UI 控件不会涉及到数据双向绑定。单向数据绑定是使用状态管理工具的前提。如果使用`vuex`，那么数据流也是单项的，这时就会和双向数据绑定有冲突。

#### 5.2 在表单中使用双向数绑定

可以用`v-model`指令在表单`<input>`、`<textarea>`以及`<select>`元素上创建双向数据绑定。它会根据控件类型自动选取正确的方法来更新元素。

**注：v-model会忽略所有表单元素的 value、checked、selected 特性的初始值而总是将 Vue 实例的数据作为数据来源。应通过 JavaScript 在组件的 data 选项中声明初始值！**

再注：如果`v-model`表达式的初始值未能匹配任何选项，`<select>`元素将被渲染为”未选中“状态。在iOS中，会使用户无法选择第一个选项。因为这种情况下，iOS不会触发change时间。因此，推荐像代码中提供一个值为空的禁用选项。

```html
<!--View层，模板-->
<div id="app">
    
    输入的文本: <input type="text" v-model="message">
    {{message}}

    <br>

    性别：
    <input type="radio" name="sex" value="男" v-model="sugar"> 男
    <input type="radio" name="sex" value="女" v-model="sugar"> 女

    <p>
        选中了：{{sugar}}
    </p>

    <br>
    下拉框：
    <select v-model="selected">
        <option value="" disabled>--请选择--</option>
        <option>A</option>
        <option>B</option>
        <option>C</option>
    </select>
    <p>{{selected}}</p>
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data: {
            message: "123",
            sugar: "",
            selected: "B",
        }
    });
</script>
```

#### 5.3 组件

组件是可复用的`Vue`实例，说白了就是一组可以重复使用的模板，跟 JSTL 的自定义标签、Thymeleaf的 `th:fragment`等框架有异曲同工之妙。通常一个应用会以一棵嵌套的组件树的形式来组织。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918124445705.png" alt="image-20200918124445705" style="zoom:25%;" />

**场景**：例如，可能会有页头、侧边栏、内容区等组件，每个组件又包含了其他的像导航链接、博文之类的组件。

##### 第一个Vue组件

在实际开发中，不会以一下方式开发组件，而是采用 `vue-cli`创建 `.vue`模板文件的方式开发。以下方式仅供理解。

**使用 Vue.component() 方法注册组件，格式如下：**

```html
<!--View层，模板-->
<div id="app">
    <sugar></sugar>
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    // 定义一个Vue组件component
    Vue.component("sugar", {
        template: '<li>Hello</li>'
    });
</script>
```

说明：

- Vue.component()：注册组件
- sugar：自定义组件的名字
- template：组件的模板

##### 使用`props`属性传递参数

传递参数为组件，需要使用`props`属性！

注：默认规则下`props`属性里的值不能为大写。

```html
<!--View层，模板-->
<div id="app">
    <!--组件：传递给组件中的值：props-->
    <sugar v-for="item in items" v-bind:item="item"></sugar>
</div>

<!--1. 导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    // 定义一个Vue组件component
    Vue.component("sugar", {
        props: ['item'],
        template: '<li>{{item}}</li>'
    });
    // Vue和组件定义是同级的，所以组件不能直接拿到items数组，需要通过props再传递回组件
    var vm = new Vue({
        el: "#app",
        data: {
            items: ["Java", "Linux", "Python"]
        }
    })
</script>
```

说明：

- `v-for="item in items"`：遍历 Vue 实例中定义的名为`items`的数组，并创建同等数量的组件。
- `v-bind:item="item"`：将遍历的`item`项绑定到组件中`props`定义的名为`item`属性上，=号左边的item为props定义的属性名，右边的为`item in items`中遍历的item项的值。

### 6. Axios异步通信

#### 6.1 Axios

Axios 是一个开源的，可以用在浏览器端和`NodeJS`的异步通信框架，它的主要作用就是实现Ajax异步通信，其功能特点如下：

- 从浏览器中创建 `XMLHttpRequests`
- 从 node.js 创建 http 请求
- 支持 Promise API
- 拦截请求和响应
- 转换请求数据和响应数据
- 取消请求
- 自动转换 JSON 数据
- 客户端支持防御 XSRF（跨站请求伪造）

GitHub：https://github.com/axios/axios

中文文档：http://www.axios-js.com/

使用Axios的原因：

- Vue.js是一个视图层框架，作者严格准守Soc（关注度分离原则），所以Vue不包含Ajax通信功能。
- 为了解决通信问题，作者单独开发了一个名为`uve-resource`的插件，在2.0版本之后停止了对该插件的维护。
- 作者推荐了`Axios`框架
- 少用jQuery，因为操作DOM太频繁。

#### 6.2 第一个Axios应用程序

1. 首先在项目中模拟一段JSON数据  data.sjon
2. 引入 Vue 和 axios

```html
<div id="vue" v-clock>
    <div>{{info.name}}</div>
    <div>{{info.address}}</div>

    <a v-bind:href="info.url">点击跳转</a>
</div>


<!--引入JS文件-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
    var vm = new Vue({
        el: "#vue",
        // data属性：vm
        data() {
            return {
                // 请求的返回参数格式，必须和 JSON字符串一样
                info: {
                    name: null,
                    address: {
                        street: null,
                        city: null,
                        country: null
                    },
                    url: null
                }
            }
        },
        mounted() {  // 钩子函数  链式编程
            axios
                .get('../data.json')
                .then(response => (this.info=response.data));
        }
    });
</script>
```

### 7. 计算属性、内容分发和自定义事件

#### 7.1 计算属性

计算属性重点在于`属性`二字，首先它是个`属性`,其次这个水泥杆有`计算`的能力。这里的`计算`就是一个函数，能够讲讲计算结果缓存起来的属性（将行为转化成了静态的属性），可以想象为缓存。

即：**计算出来的结果，保存在属性中。（内存中运行，虚拟DOM）**

methods 里的调用**需要带括号**，computed 里的调用**不需要带括号**。

**计算属性的主要特征就是为了将不经常变化的计算结果进行换内存，以节约系统的开销。**

```html
<div id="app">
    <!--属性方法一定要带括号-->
    <p>{{currentTime1()}}</p>
    <p>{{currentTime2}}</p>
</div>


<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data: {
            message: "hello, sugar"
        },
        methods: {
            currentTime1: function () {
                return Date.now();  // 返回一个时间戳
            }
        },
        // 计算属性：methods、computed 方法名不能重名，重名之后，只会优先调用methods的方法
        computed: {
            currentTime2: function () {
                return Date.now();  // 返回一个时间戳
            }
        }
    })
</script>
```

#### 7.2 内容分发

在`Vue.js`中使用 `<slot>` 元素作为承载分发内容的出口，称为**插槽**，可以应用在组合组件的场景中。

**测试场景**：准备制作一个待办事项组件（todo），该组件由待办标题（todo-title）和待办内容（todo-items）组成，而这三个组件又是相互独立的。

1. 第一步：定义一个待办事项组件
2. 第二步：让待办事项的标题和值实现动态绑定

```html
<div id="app">
    <todo>
        <todo-title slot="todo-title" :title="title"></todo-title>
        <todo-items slot="todo-items" v-for="item in todoItems" :item="item"></todo-items>
    </todo>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    // slot：插槽
    Vue.component("todo", {
        template:
            '<div>\
                <slot name="todo-title"></slot>\
                <ul>\
                    <slot name="todo-items"></slot>\
                </ul>\
             </div>'
    });

    Vue.component("todo-title", {
        props: ['title'],
        template: '<div>{{title}}</div>'
    });

    Vue.component("todo-items", {
        props: ['item'],
        template: '<li>{{item}}</li>'
    });

    var vm = new Vue({
        el: "#app",
        data: {
            title: "Sugar",
            todoItems: ['Java', 'Python', 'Linux']
        }
    });
</script>
```

#### 7.3 自定义事件

数据项在 Vue 的实例中，但删除操作要在组件中完成，那么组件如何才能删除 Vue 实例中的数据？这一过程就涉及到参数传递与事件分发，Vue提供了自定义事件的功能能够解决该问题，使用 `this.$emit('自定义事件名', 参数)` ，操作过程如下：

1. 在Vue的实例中，增加了一个 methods 对象并定义了一个名为 **removeTodoItems** 的方法。
2. 修改 todo-items 待办内容组件的代码，增加一个删除按钮，并且绑定事件。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div id="app">
    <todo>
        <todo-title slot="todo-title" :title="title"></todo-title>
        <todo-items slot="todo-items" v-on:remove="removeItems(index)"
                    v-for="(item,index) in todoItems" :item="item" :index="index"></todo-items>
    </todo>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    // slot：插槽
    Vue.component("todo", {
        template:
            '<div>\
                <slot name="todo-title"></slot>\
                <ul>\
                    <slot name="todo-items"></slot>\
                </ul>\
             </div>'
    });

    Vue.component("todo-title", {
        props: ['title'],
        template: '<div>{{title}}</div>'
    });

    Vue.component("todo-items", {
        props: ['item', 'index'],
        // 只能绑定当前组件内的方法
        template: '<li>{{index}} - {{item}}  <button @click="remove">删除</button></li> ',
        methods: {
            remove: function (index) {
                // 自定义事件，
                this.$emit('remove', index);
            }
        }
    });

    var vm = new Vue({
        el: "#app",
        data: {
            title: "Sugar",
            todoItems: ['Java', 'Python', 'Linux']
        },
        methods: {
            // 根据下标删除数组元素
            removeItems: function (index) {
                this.todoItems.splice(index, 1);  // 一次只删除一个元素
            }
        }
    });
</script>

</body>
</html>
```

### 8. Vue 小结

**核心**：数据驱动，组件化

**优点**：借鉴了AngularJS的模块化开发和React的虚拟Dom，虚拟Dom就是把Dom操作放到内存中执行。

**常用属性**：

- v-if
- v-else-if
- v-else
- v-for
- v-on  绑定事件，简写`@`
- v-model  数据双向绑定
- v-bind  给组件绑定参数，简写 `:`

**组件化**：

- 组合组件 slot 插槽
- 组件内部绑定事件需要使用 `this.$emit('事件名', 参数)`
- 计算属性的特色：缓存计算数据

遵循SoC关注度分离原则，Vue是纯粹的视图框架，并不包含如Ajax之类的通信功能，为了解决通信问题，使用 Axios 框架做异步通信。

**说明**：

Vue的开发都是要基于**NodeJS**，实际开发采用 **vue-cli** 脚手架开发，**vue-router** 路由，**vuex** 做状态管理，Vue UI界面一般使用 **ElementUI**（饿了么出品）或者**ICE**（阿里巴巴出品）来快速搭建前端项目。

### 9. Vue正式入门

#### 9.1 vue-cli

vue-cli 是官方提供的脚手架，用于快速生成一个 vue 项目模板。

预先定义好的目录结构及基础代码，好比Maven创建的骨架项目。

**主要功能**：

- 统一的目录结构
- 本地调试
- 热部署
- 单元测试
- 集成打包上线

##### 基本环境搭建

- Node.js：http://nodejs.cn/download
- Git：https://git-scm.com/downloads

**确认nodejs安装成功：**

- cmd下输入 `node -v`，查看是否能够正确打印版本号即可
- cmd下输入 `npm -v`，查看是否能够正常打印版本号即可

**安装 Node.js 淘宝镜像加速器（cnpm）**

```cmd
# -g 就是全局安装
npm install cnpm -g
# 或者 使用如下语句解决npm速度慢的问题(每次安装的时候都带上)
npm install --registry=https://registry.npm.taobao.org
```

##### 安装 vue-cli

```cmd
cnpm install vue-cli -g

# 测试是否安装成功
# 查看可以基于哪些模板创建 vue 应用程序，通常选择 webpack
vue list
```

##### 第一个 vue-cli 应用程序

1. 创建一个Vue项目，随便建立一个空文件夹

2. 创建一个基于 webpack 模板的 vue 应用程序

   ```cmd
   # 这里的 myvue 是项目名称，根据需求起名
   vue init webpack myvue
   # 一路都选择no即可。（for 学习这个过程，其实也可以yes）
   ```

   说明：

   - Project name：项目名称，默认回车即可
   - Project description：项目描述，默认回车即可

3. 初始化并执行

   ```cmd
   cd myvue
   npm install
   npm run dev
   ```

   启动成功后：

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200918174825523.png" alt="image-20200918174825523" style="zoom:50%;" />

#### 9.2 Webpack

webpack 是现代 JavaScript 应用程序的静态模块打包器（module bundler），当 webpack 处理应用程序时，它会递归地构建一个依赖关系图（dependency graph），其中包含应用程序需要的每个模块，然后将所有这些模块打包成一个或多个bundle。

Webpack 是当下最热门的前端资源模块化管理和打包工具，可以将许多松散耦合的模块按照依赖和规则打包成符合生产环境部署的前端资源，还可以将按需加载的模块进行代码分离，等到时机需要时再异步加载。通过loader转换，任何形式的资源都可以当做模块，比如 CommonsJS、AMD、ES6、CSS、JSON、LESS等。

当今越来越多网站已从网页模式进化到了WebApp模式。它们运行在现代浏览器中，使用HTML5、CSS3、ES6等新的技术来开发丰富的功能，网页已经不仅仅是完成浏览器的基本需求，WebApp 通常是一个**SPA（单页面应用）**，每一个视图通过异步的方式加载，导致页面初始化和使用过程中会加载越来越多的JS代码，给前端开发流程和资源组织带来巨大挑战。

##### 安装Webpack

```cmd
npm install webpack -g
npm install webpack-cli -g
```

测试

```cmd
webpack -v
webpack-cli -v
```

##### 配置(了解即可)

创建 `webpack.config.js` 配置文件

- entry：入口文件，指定Webpack用哪个文件作为项目入口
- output：输出，指定Webpack把处理完成的文件放置到指定路径
- module：模块，用于处理各种类型的文件
- plugins：插件，如：热更新，代码复用等
- resolve：设置路径指向
- watch：监听，用于设置文件改动后直接打包

##### 使用Webpack

1. 创建项目

2. 创建一个名为 modules 的目录，用于放置 JS 模块等资源文件

3. 在 modules 下创建模块文件，如 hello.js，用于编写 JS 模块相关代码

   ```javascript
   // 暴露一个方法
   exports.sayHi = function () {
       document.write("<h1>Sugar</h1>")
   };
   ```

4. 在 modules 下创建一个名为 main.js 的入口文件，用于打包时设置 entry 属性

   ```js
   // require 导入一个模块，就可以调用该模块下的方法了
   var hello = require("./hello");
   hello.sayHi()
   ```

5. 在项目目录下创建 **webpack.config.js** 配置文件，使用 webpack 命令打包

   ```javascript
   module.exports = {
       entry: './modules/main.js',
       output: {
           filename: "./js/bundle.js"
       }
   };
   ```

6. 在项目目录下创建 HTML 页面，导入 WebPack 打包后的JS文件

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>webpack</title>
   </head>
   <body>
   
   <!--前端的模块化开发-->
   <script src="dist/js/bundle.js"></script>
   </body>
   </html>
   ```

7. 在项目根目录下，执行 **webpack**  命令（失败的话，使用管理员权限运行）

8. 打开页面浏览效果

#### 9.3 vue-router 路由

Vue Router 是 Vue.js 官方的路由管理器。与 Vue.js 的核心深度集成，让构建单页面应用变得简单。包含的功能有：

- 嵌套的路由/视图表
- 模块化的、基于组件的路由配置
- 路由参数、查询、通配符
- 基于Vue.js过滤系统的视图过滤效果
- 粗粒度的导航控制
- 带有自动激活的 CSS class 的链接
- HTML5 历史模式或hash模式，在 IE9 中自动降级
- 自定义的滚动条行为

##### 安装

**基于第一个 `vue-cli` 进行测试学习，先查看 node_modules 中是否存在 vue-router**

vue-router 是一个插件包，需要使用 npm/cnpm 来进行安装。

进行项目目录，打开命令行工具，输入下面命令，vue-router 将被安装至 node_modules下。

```cmd
npm install vue-router --save-dev
```

在模块化工程中使用，必须要通过 Vue.use() 显式声明使用路由功能：

```js
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);
```

##### 使用

1. 删除无用的东西

2. `components` 目录下存放编写的组件

3. 定义一个 `Content.vue` 的组件

   ```vue
   <template>
     <h1>内容页</h1>
   </template>
   
   <script>
   export default {
     name: "Content"
   }
   </script>
   ```

4. 安装路由，在 src 目录下，新建一个文件夹：`router`，专门存放路由，新建 `index.js`写配置

   **注意**：是 **routes**，不是routers！

   ```js
   import Vue from 'vue'
   import VueRouter from 'vue-router'
   
   import Content from '../components/Content'
   import Main from '../components/Main'
   
   // 安装路由
   Vue.use(VueRouter);
   
   // 配置导出路由
   export default new VueRouter({
     routes: [
       {
         // 路由路径
         path: '/content',
         name: 'content',
         // 跳转的组件
         component: Content
       },
       {
         path: '/main',
         name: 'main',
         component: Main
       }
     ]
   });
   
   ```

5. 在 `main.js` 中配置路由

   ```js
   import Vue from 'vue'
   import App from './App'
   import router from './router'  // 自动扫描里面的路由配置
   
   Vue.config.productionTip = false
   
   /* eslint-disable no-new */
   new Vue({
     el: '#app',
     // 配置路由
     router,
     components: { App },
     template: '<App/>',
   })
   ```

6. 在 `App.vue` 中使用路由

   ```vue
   <template>
     <div id="app">
   
       <h1>Hello World</h1>
       <router-link to="/main">首页</router-link>
       <router-link to="/content">内容页</router-link>
       <router-view></router-view>
   
     </div>
   </template>
   ```

#### 9.4 ElementUI（快速入门实现登录页面）

##### 创建工程

1. 创建一个名为 `hello-vue`的工程，

   ```cmd
   vue init webpack hello-vue
   ```

2. 安装依赖，需要安装 **vue-router**、**element-ui**、**sass-loader** 和 **node-sass** 四个插件。

   ```cmd
   # 进入工程目录
   cd hello-vue
   # 安装 vue-router
   npm install vue-router --save-dev
   # 安装 element-ui
   npm i element-ui -S
   # 安装依赖
   npm install
   # 安装 SPSS 加载器
   cnpm install sass-loader node-sass --save-dev
   # 启动测试
   npm run dev
   ```

##### 创建登录页面

1. 编写登录页和首页 Login.vue  Main.vue

   ```vue
   <template>
     <div>
       <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
         <h3 class="login-title">登录</h3>
         <el-form-item label="账号" prop="username">
           <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
         </el-form-item>
         <el-form-item label="密码" prop="password">
           <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
         </el-form-item>
         <el-form-item>
           <el-button type="primary" v-on:click="onSubmit('loginForm')">登录</el-button>
         </el-form-item>
       </el-form>
   
       <el-dialog
         title="温馨提示"
         :visible.sync="dialogVisible"
         width="30%"
         :before-close="handleClose">
         <span>请输入账号和密码</span>
         <span slot="footer" class="dialog-footer">
           <el-button type="primary" @click="dialogVisible = false">确定</el-button>
         </span>
       </el-dialog>
     </div>
   </template>
   
   <script>
   export default {
     name: "Login",
     data() {
       return {
         form: {
           username: '',
           password: ''
         },
         // 表单验证，需要在 el-form-item 元素中增加 prop 属性
         rules: {
           username: [
             {required:true, message:'账号不能为空', trigger:'blur'}
           ],
           password: [
             {required:true, message:'密码不能为空', trigger:'blur'}
           ]
         },
         // 对话框显示和隐藏
         dialogVisible: false
       }
     },
     methods: {
       onSubmit(formName) {
         // 为表单绑定验证功能
         this.$refs[formName].validate((valid) => {
           if (valid) {
             // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
             this.$router.push("/main");
           } else {
             this.dialogVisible = true;
             return false;
           }
         });
       }
     }
   }
   </script>
   
   <style scoped>
     .login-box {
       border: 1px solid #DCDFE6;
       width: 350px;
       margin:180px auto;
       padding: 35px 35px 15px 35px;
       border-radius: 5px;
       -webkit-border-radius: 5px;
       -moz-border-radius: 5px;
       box-shadow: 0 0 25px #909399;
     }
   
     .login-title {
       text-align: center;
       margin: 0 auto 40px auto;
       color: #303133;
     }
   </style>
   ```

   ```vue
   <template>
     <h1>首页</h1>
   </template>
   
   <script>
   export default {
     name: "Main"
   }
   </script>
   
   <style scoped>
   </style>
   ```

2. 编写主页  App.vue

   ```vue
   <template>
     <div id="app">
       <router-link to="/login">login</router-link>
       <router-link to="/main">main</router-link>
       <router-view></router-view>
     </div>
   </template>
   
   <script>
   export default {
     name: 'App'
   }
   </script>
   ```

3. 编写router 路由配置 index.js

   **注意**：是 **component**，不是components！

   ```js
   import Vue from 'vue'
   import Router from 'vue-router'
   
   import Main from '../views/Main'
   import Login from '../views/Login'
   
   Vue.use(Router);
   
   export default new Router({
   
     routes: [
       {
         path: '/main',
         component: Main
       },
       {
         path: '/login',
         component: Login
       },
     ]
   });
   
   ```

4. 编写主配置文件 main.js

   ```js
   import Vue from 'vue'
   import App from './App'
   import router from './router'
   import Element from 'element-ui'
   import 'element-ui/lib/theme-chalk/index.css'  // ElementUI需要导入一个CSS
   
   Vue.use(router);
   Vue.use(Element);
   
   new Vue({
     el: '#app',
     router,
     render: h => h(App)  // ElementUI
   })
   ```

#### 9.5 嵌套路由

嵌套路由又称为子路由，在实际应用中，通常由多层嵌套的组件组合而成。同样地，URL中各段动态路径也按某种结构对应嵌套的各层组件，如下：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200921122817728.png" alt="image-20200921122817728" style="zoom:30%;" />

1. 在 views 目录下新建一个 user 目录，再新建两个子页面  List.vue  Profile.vue

2. 修改主页来展示这两个子页面  Main.vue

   ```vue
   <template>
     <div>
       <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
         <h3 class="login-title">登录</h3>
         <el-form-item label="账号" prop="username">
           <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
         </el-form-item>
         <el-form-item label="密码" prop="password">
           <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
         </el-form-item>
         <el-form-item>
           <el-button type="primary" v-on:click="onSubmit('loginForm')">登录</el-button>
         </el-form-item>
       </el-form>
   
       <el-dialog
         title="温馨提示"
         :visible.sync="dialogVisible"
         width="30%"
         :before-close="handleClose">
         <span>请输入账号和密码</span>
         <span slot="footer" class="dialog-footer">
           <el-button type="primary" @click="dialogVisible = false">确定</el-button>
         </span>
       </el-dialog>
     </div>
   </template>
   
   <script>
   export default {
     name: "Login",
     data() {
       return {
         form: {
           username: '',
           password: ''
         },
         // 表单验证，需要在 el-form-item 元素中增加 prop 属性
         rules: {
           username: [
             {required:true, message:'账号不能为空', trigger:'blur'}
           ],
           password: [
             {required:true, message:'密码不能为空', trigger:'blur'}
           ]
         },
         // 对话框显示和隐藏
         dialogVisible: false
       }
     },
     methods: {
       onSubmit(formName) {
         // 为表单绑定验证功能
         this.$refs[formName].validate((valid) => {
           if (valid) {
             // 使用 vue-router 路由到指定页面，该房事称之为编程式导航
             this.$router.push("/main");
           } else {
             this.dialogVisible = true;
             return false;
           }
         });
       }
     }
   }
   </script>
   
   <style scoped>
     .login-box {
       border: 1px solid #DCDFE6;
       width: 350px;
       margin:180px auto;
       padding: 35px 35px 15px 35px;
       border-radius: 5px;
       -webkit-border-radius: 5px;
       -moz-border-radius: 5px;
       box-shadow: 0 0 25px #909399;
     }
   
     .login-title {
       text-align: center;
       margin: 0 auto 40px auto;
       color: #303133;
     }
   </style>
   ```

3. 配置子路由 router  index.js

   ```js
     routes: [
       {
         path: '/main',
         component: Main,
         // 嵌套路由
         children: [
           {path: '/user/profile', component: UserProfile},
           {path: '/user/list', component: UserList},
         ]
       },
   ```

#### 9.6 参数传递及重定向

##### 参数传递

1. 修改页面中 router-link 标签 的 **:to**。

   ```vue
   <!--name：传组件名，params：传递参数，需要对象：v-bind-->
   <router-link :to="{name: 'UserProfile', params: {id: 1}}">个人信息</router-link>
   ```

2. 修改 router 的配置，带上参数

   ```js
   // 如果传递了参数，必须写 name，开启参数传递 props:true
   {path: '/user/profile/:id', name: 'UserProfile', component: UserProfile, props: true},
   ```

3. 展示传递的参数

   ```html
   <template>
     <!--所有元素，不能直接写在根节点下-->
     <div>
       <h1>个人信息</h1>
       {{id}}
     </div>
   </template>
   
   <script>
   export default {
     props: ['id'],
     name: "UserProfile"
   }
   </script>
   ```

##### 重定向

1. 在 router 配置 index.js

   ```js
   {
   	  path: '/goHome',
       redirect: '/main'
   }
   ```

#### 9.7 404和路由钩子

##### 路由模式与404

路由模式有两种：

- hash：路径带 # 符号
- history：路径不带 # 符号

修改路由配置，如下：

```js
export default new Router({
  mode: 'history',
  routes: []
});
```

404：

1. 新建一个404页面  NotFound.vue

2. 配置路由

   ```js
   {
   	  path: '*',
       component: NotFound
   }
   ```

##### 路由钩子与异步请求

**beforeRouteEnter**：在进入路由前执行

**beforeRouteLeave**：在离开路由前执行

```js
export default {
  props: ['id'],
  name: "UserProfile",
  // 过滤器 chain
  beforeRouteEnter: (to, from, next) => {
    console.log("进入路由之前");
    next();
  },
  beforeRouteLeave: (to, from, next) => {
    console.log("进入路由之后");
    next();
  },
}
```

参数说明：

- to：路由将要跳转的路径信息
- from：路由跳转前的路径信息
- next：路由的控制参数
  - next() 跳入下一个页面
  - next('/path') 改变路由的跳转方向，使其跳到另一个路由
  - next(false)  返回原来的页面
  - next((vm) => {})  仅在 beforeRouteEnter 中可用，vm是组件实例

##### 在钩子函数中使用异步请求

1. 安装 Axios

   ```cmd
   npm install --save axios
   npm install --save vue-axios
   ```

2. `main.js`引用 Axios

   ```js
   import axios from 'axios';
   import VueAxios from 'vue-axios'
   
   Vue.use(VueAxios, axios);
   ```

3. 准备数据：只有 static 目录下的文件是可以被访问到的，所以把静态文件放在该目录下。

4. 在 beforeRouteEnter 中进行异步请求。