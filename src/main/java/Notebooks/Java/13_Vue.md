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

#### 5.1 双向数据绑定

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
2. 修改 todo-items 待办内容组件的代码，增加一个删除按钮，并且绑定时间。