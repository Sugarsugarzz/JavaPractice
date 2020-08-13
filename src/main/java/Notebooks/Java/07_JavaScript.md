### 1. 什么是JavaScript

#### 1.1 概述

​	JavaScript是一门世界上最流行的脚本语言。

​	**合格的后端，必须精通JavaScript**

#### 1.2 发展史

​	https://blog.csdn.net/kese7952/article/details/79357868

​	**ECMAScript** 可以理解为是 JavaScript 的一个标准。

​	最新版本已经到 ES6 版本。

​	但大部分浏览器还只停留在支持 es5 代码上。

### 2. 快速入门

#### 2.1 引入 JavaScript

1. 内部标签

   ```html
       <!--script标签内，写 JavaScirpt 代码-->
       <script>
           alert("Hello World")
       </script>
   ```

2. 外部引入

   ```html
   		<!--外部引入-->
       <!--这种写法可能会有问题，不会运行，因为html5对标签有严格要求，必须成对出现-->
       <!--<script src="js/sugar.js"/>-->
       <script src="js/sugar.js"></script>
   ```

测试代码 

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>第一个JavaScript程序</title>

    <!--script标签内，写 JavaScirpt 代码-->
<!--    <script>-->
<!--        alert("Hello World")-->
<!--    </script>-->

    <!--外部引入-->
    <!--这种写法可能会有问题，不会运行，因为html5对标签有严格要求，必须成对出现-->
    <!--<script src="js/sugar.js"/>-->
    <script src="js/sugar.js"></script>

    <!--不用显示定义type，也默认是javascript-->
    <script type="text/javascript">

    </script>
</head>
<body>


<!--body底部也可以存放 script 代码-->
</body>
</html>
```

#### 2.2 基本语法入门

浏览器必备调试须知：

```
Chrome 中检查的各项介绍：
    Elements - 爬虫、复刻网站
    Console - 调试、写JavaScript
    Source - JavaScript 打断点
    Network - 抓包
    Application - 查看网站的Cookie
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200812095035576.png" alt="image-20200812095035576" style="zoom:50%;" />

```html
    <!--JavaScript 严格区分大小写-->
    <script>
        // 1.定义变量  变量类型 变量名 = 变量值；
        // 无论定义什么，变量类型都是 var
        var score = 70;

        // 2.条件控制
        if (score > 60 && score <= 70) {
            alert("60-70")
        } else if (score > 70 && score <= 80) {
            alert("70-80")
        } else {
            alert("other")
        }

        // 3.多行注释
        /*
        fdsafdsa
         */

        // 4.在浏览器的控制台打印变量
        console.log(score)

        // 5.在浏览器 检查 - Source 打断点调试
    </script>
```

#### 2.3 数据类型

数值、文本、图形、音频、视频......

- 变量

  变量名命名规范

```javascript
var a = 1;
```

- number

js不区分小数和整数，都用number

```javascript
123  // 整数 123
123.1  // 浮点数 123.1
1.234e3  // 科学计数法
-99  // 负数
NaN  // not a number
Infinity  // 表示无限大的数
```

- 字符串

```javascript
'abc'
"abc"
```

- 布尔值

```javascript
true
false
```

- 逻辑运算

```javascript
&&  两个都为真，结果为真
||  一个为真，结果为真
!  真即假，假即真
```

- 比较运算符（重要）

```javascript
=  赋值
== 等于（类型不一样，值一样，也会判断为true，如 1 和 "1"）
=== 绝对等于（类型一样，值一样，才为true）
```

这是JS的一个缺陷，坚持不要使用 == 比较。

**须知：**

- NaN == NaN    // false 
- NaN与所有的数值都不相等，包括它自己。
- 只能通过 isNaN(NaN) 来判断这个数是否是NaN。

**浮点数问题：**

```javascript
(1/3) === (1- 2/3)  // false
```

尽量避免使用浮点数进行运算，存在精度问题。

```javascript
Math.abs(1/3 - (1-2/3)) < 0.000000001  // true
```

可以使用绝对值进行比较。

- null 和 undefined
  - null  空
  - undefined  未定义

- 数组

  Java的数组必须是相同类型的对象，JS中不需要。

  ```javascript
          // 保证代码的可读性，尽量使用 []
          var arr = [1, 2, 3, 4, 5, 'hello', null, true];
          
          new Array(1, 2, 3, 4, 5, 'hello');
  ```

  取数组下标，如果越界了，就会

  ```javascript
  undefined
  ```

- 对象

  对象是大括号，数组是中括号。

  每个属性之间使用逗号隔开，最后一个不需要添加。

  ```javascript
  // 对象
  // Person person = new Person();
  var person = {
      name: "sugar",
      age: 3,
      tags: ['js', 'java', 'web', '...']
  }
  ```

  取对象的值

  ```javascript
  person.name
  > "sugar"
  person.age
  > 3
  ```

#### 2.4 严格检查格式

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200812102727375.png" alt="image-20200812102727375" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>严格检查模式</title>

    <!--
    前提：IDEA 需要支持 ES6 语法。
    'use strict'：严格检查模式，预防 JavaScript 的随意性导致产生的一些问题
        必须写在 JavaScript 的第一行！！！
        局部变量：都建议使用 let 定义。
    -->
    <script>
        // 开启严格检查模式
        'use strict'
        // 局部变量
        let i = 1;
    </script>
</head>
<body>

</body>
</html>	
```

### 3. 数据类型

#### 3.1 字符串

1. 正常字符串使用 单引号 或 双引号 包裹。

2. 要包含特殊符号，需要使用转义字符 \

   ```javascript
   \'  点
   \n  换行
   \t  制表符
   \ue42d  '中'  \u#### unicode字符
   \x41		ascll字符
   ```

3. 多行字符串编写

   ```javascript
   // tab上面的的 ` 键
   var msg = `hello
   world
   ni hao
   a
   `;
   ```

4. 模板字符串

   ```javascript
   // 模板字符串
   let name = "sugar";
   let age = 3;
   let ms = `你好，${name}`;
   ```

5. 字符串长度

   ```javascript
   console.log(str.length)
   ```

6. 字符串的可变性 - 不可变

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200812104209253.png" alt="image-20200812104209253" style="zoom:50%;" />

7.  大小写转换

   ```javascript
   // 注意，这里是方法，不是属性
   student.toUpperCase()
   student.toLowerCase()
   ```

8. 获取字符下标

   ```javascript
   student.indexOf('t')
   ```

9. 获取子串

   ```javascript
   student.substring(1)  // 从第一个字符串截取到最后一个字符串
   student.substring(1, 3)  // [1, 3)
   ```

#### 3.2 数组

**Array 可以包含任意的数据类型。**

**数组**：存储数据（如何存，如何取，方法都可以自己实现）

```javascript
var arr = [1, 2, 3, 4, 5, 6];  // 通过下标取值和复制
arr[0]
arr[0] = 1
```

1. 长度

   ```javascript
   arr.length
   ```

   注意：假如给 arr.length 赋值，数组大小就会发生变化。如果赋值小，元素就会丢失；如果赋值大，多的部分以 undefined 补充。

2. indexOf，通过元素获得下标索引

   ```javascript
   arr.indexOf(2)
   > 1
   ```

   字符串的 "1" 和 数字 1 是不同的。

3. slice()  截取 Array 的一部分，返回一个新的数组，类似于String中的substring

4. push()、pop()  尾部操作

   ```javascript
   arr.push('b')  // 压入到尾部
   arr.pop()  // 弹出尾部的一个元素
   ```

5. unshift()、shift()  头部操作

   ```javascript
   arr.unshift('a', 'b')  // 压入到头部
   arr.shift()  // 弹出头部的一个元素
   ```

6. sort()  排序

   ```javascript
   arr = ['B', 'C', 'A']
   arr.sort()
   > ['A', 'B', 'C']
   ```

7. reverse()  元素反转

   ```javascript
   arr = ['B', 'C', 'A']
   arr.reverse()
   > ['A', 'C', 'B']
   ```

8. concat(数组)  数组拼接，返回新的数组

   ```javascript
   arr = ['B', 'C', 'A']
   arr.concat([1, 2, 3])
   > ['B', 'C', 'A', 1, 2, 3]
   ```

   **注意**：并没有修改数组，只是会返回一个新的数组。

9. join  连接符

   打印拼接数组，使用特定的字符串连接

   ```javascript
   arr = ['B', 'C', 'A']
   arr.join('-')
   > "B-C-A"
   ```

10. 多维数组

    ```javascript
    arr = [[1,2], [3, 4], ["5", "6"]];
    arr[1][1]
    > 1
    ```

#### 3.3 对象

若干个键值对

```javascript
var person = {
	属性名：属性值,
  属性名：属性值,
  属性名：属性值
}

// 定义了一个person对象，有五个属性
var person = {
  name: "Sugar",
  age: 3,
  email: "406857586@qq.com",
  score: 0
}
```

JS 中的对象，用 {...} 表示一个对象，以键值对描述属性 xxx: xxx，多个属性之间使用逗号隔开，最后一个属性不加逗号！

JavaScript 中的所有**键都是字符串**，**值是任意对象**！

1. 对象赋值

   ```javascript
   person.name = "qinjiang"
   > "qinjiang"
   person.name
   > "qinjiang"
   ```

2. 使用一个不存在的对象属性，不会报错。 

   ```javascript
   person.haha
   > undefined
   ```

3. 动态的删减属性，通过 delete 删除对象的属性

   ```javascript
   delete person.name
   > true
   ```

4. 动态的添加属性，直接给新的属性添加值即可

   ```javascript
   person.haha = "haha"
   > "haha"
   ```

5. 判断属性值是否在这个对象中  xxx in xxx

   ```javascript
   'age' in person
   > true
   // 继承父类的属性
   'toString' in person
   > true
   ```

6. 判断一个属性是否是这个对象自身拥有的  hasOwnProperty()

   ```javascript
   person.hasOwnProperty('toString')
   > false
   person.hasOwnProperty('age')
   > true
   ```

#### 3.4 流程控制

**if 判断**

```javascript
var age = 3;
if (age > 3) {
    alert("haha");
} else if {
    alert("aaa");
} else {
    alert("kua");
}
```

**while do-while 循环**

```javascript
while(age < 100) {
    age = age + 1;
    console.log(age);
}

do {
    age = age + 1;
    console.log(age);
} while(age < 100) 
```

**for 循环**

```javascript
for (let i = 0; i < 100; i++) {
    console.log(i);
}
```

**forEach循环**

> ES 5.1 引入

```javascript
// 函数
var arr = [1, 2, 3, 4, 5, 6, 7, 8];
arr.forEach(function (value) {
    console.log(value);
})
```

**for...in循环**

```javascript
/*
for(Type str: Type[] strs)
 */
// for(var index in object) {}
// 打印下标
for (var num in arr) {
  console.log(arr[num]);
}
// 打印值
for (var num of arr) {
  console.log(num);
}
```

#### 3.5 Map 和 Set

**Map**

```javascript
// ES6  Map
// 学生的成绩，学生的名字
// var names = ["tom", "jack", "haha"];
// var scores = [100, 90, 80];

var map = new Map([['tom', 100], ['jack', 90], ['haha', 80]]);
var name = map.get('tom');  // 通过 key 获得 value
map.set('admin', 123456);  // 新增或修改
console.log(map);
map.delete('tom');  // 删除
console.log(map);
```

**Set**：无序不重复的集合

```javascript
var set = new Set([3, 2, 2, 1, 1]);  // set 可以去重
set.add(3);  // 添加
console.log(set);
set.delete(1);  // 删除
console.log(set);
console.log(set.has(1)) // 判断是否包含某个元素
```

#### 3.6 iterator

> ES6 新特性

**遍历数组**

```javascript
// 通过 for of 打印值
var arr = [3, 4, 5];
for (var x of arr) {
  console.log(x)
}
```

**遍历map**

```javascript
var map = new Map([['tom', 100], ['jack', 90], ['haha', 80]]);
for (var x of map) {
    console.log(x);
}
```

**遍历set**

```javascript
// 遍历set
var set = new Set([5, 6, 7]);
for (var x of set) {
    console.log(x);
}
```

### 4. 函数

#### 4.1 定义函数

- **定义方式一**

```javascript
// **绝对值函数**
function abs(x) {
  if (x >= 0) {
    return x;
  } else {
    return -x;
  }
}
```

一旦执行到 return 代表函数结束，返回结果。

如果没有执行，函数执行完也会返回结果，结果就是 **undefined**.

- **定义方式二**

```javascript
var abs = function (x) {
  if (x >= 0) {
    return x;
  } else {
    return -x;
  }
}
```

function(x) {...}  这是一个**匿名函数**，但是可以把结果赋值给 abs，通过 abs 就可以调用函数！

方式一和方式二等价！

- **调用函数**

```javascript
abs(10) // 10
abs(-10)  // 10
```

参数问题： JavaScript 可以传任意个参数，也可以不传递参数

参数进来是否存在问题？假设不存在参数，如何规避？

```javascript
var abs = function (x) {
    // 手动抛出异常
    if (typeof x !== 'number') {
        throw 'Not a number';
    }
    
    if (x >= 0) {
        return x;
    } else {
        return -x;
    }
}
```

- **arguments**

arguments 是一个JS免费赠送的关键字；

代表，传递进来的所有参数，是一个数组。

```javascript
var abs = function (x) {
    // 手动抛出异常
    // if (typeof x !== 'number') {
    //     throw 'Not a number';
    // }

    console.log("x=>" + x);

    for (var i = 0; i < arguments.length; i++) {
        console.log(arguments[i]);
    }

    if (x >= 0) {
        return x;
    } else {
        return -x;
    }
}
```

问题：arguments 包含所有的参数，有时候想使用多余的参数来进行附加操作，需要排序已有的参数。

- **rest**

以前：

```javascript
function aaa(a, b) {
    console.log("a" + a);
    console.log("b" + b);
    if (arguments.length > 2) {
        for (var i = 2; i < arguments.length; i++) {
            // ...
        }
    }
}
```

ES6 引入的新特性，获取除了已经定义的参数之外的所有参数~ ...

现在：

```javascript
function aaa(a, b, ...rest) {
    console.log("a" + a);
    console.log("b" + b);
    console.log("rest" + rest);
}
```

rest 参数只能写在最后面，必须用 ... 标识。

#### 4.2 变量的作用域

在 JavaScript 中，var 定义变量实际是有作用域的。

假设在函数体中声明，则在函数体外不可以使用。（非要想实现的话，可以研究下**闭包**）

```javascript
function sugar() {
    var x = 1;
    x = x + 1;
}

x = x + 2;  // Uncaught ReferenceError: x is not defined
```

如果两个函数使用相同的变量名，只要在函数内部，就不冲突。

```javascript
function sugar() {
    var x = 1;
    x = x + 1;
}

function sugar2() {
    var x = 1;
    x = x + 1;
}
```

内部函数可以访问外部函数的成员，反之则不行。

```javascript
function sugar() {
    var x = 1;
    // 内部函数可以访问外部函数的成员，反之则不行。
    function sugar3() {
        var y = x + 1;  // 2
    }
    var z = y + 1;  // Uncaught ReferenceError: y is not defined
}
```

假设，内部函数变量和外部函数变量，重名！

假设在 JavaScript 中，函数查找变量从自身函数开始，由“**内**”向“**外**”查找。如果外部存在这个同名的函数变量，则内部函数会屏蔽外部的函数变量。（就近原则）

```javascript
function sugar4() {
    var x = 1;

    function sugar5() {
        var x = 'A';
        console.log('inner' + x);  // interA
    }
    console.log('outer' + x)  // outer1
    sugar5()
}
```

- **提升变量的作用域**

说明，JavaScript 的执行引擎，自动提升了 y 声明，但是不会提升 y 的赋值。

这个是在 JavaScript 建立之初就存在的特性。

**养成规范**：所有的变量定义都放在函数的头部，不要乱放，便于代码的维护。

```javascript
function sugar6() {
    var x = "x" + y;
    console.log(x);  //xundefined
    var y = "y";
}

// 等价于 sugar6
function sugar7() {
    // 因为会自动提升声明，所以都会把变量声明写到最前面
  	// var x, y, z, a, b, c, ...  
    var y;
    var x = "x" + y;
    console.log(x);
    y = "y";
}
```

- **全局函数**

```javascript
// 全局变量
var a = 1;
function f() {
    console.log(x);
}
console.log(x);
```

全局变量 window

```javascript
var b = 'xxx';
alert(b);
alert(window.b);  // 默认所有的全局变量，都会自动绑定到 window 对象下。
window.alert(window.b);
```

alert() 这个函数本身也是一个 window 变量。

```javascript
var c = 'xxx';
window.alert(c);

var old_alert = window.alert;
// old_alert(c);

window.alert = function () {
    
};
// 发现 alert() 失效了
window.alert(123);

// 恢复
window.alert = old_alert;
window.alert(456);
```

JavaScript 实际上只有一个全局作用域，任何变量（函数也可以视为变量），假设没有在函数作用范围内找到，就会向外查找，如果全局作用域都没有找到，报错 《RefrenceError》。

- **规范**

由于所有的全局变量都会绑定到 window 上，如果不同的JS文件使用相同的全局变量，则会产生**冲突**，如何减少冲突？

- 把自己的代码全部放入自己定义的唯一命名空间中，降低全局命名冲突的问题。
- JQuery 就是这么做的，为了简化，JQuery 将自己简化为 $ 符号。

```javascript
// 避免绑定全局变量冲突，自定义一个唯一全局变量
var SugarApp = {};
// 定义全局变量
SugarApp.name = "sugar";
SugarApp.add = function (a, b) {
    return a + b;
}
```

- **局部作用域 let**

```javascript
function aaa() {
    for (var i = 0; i < 100; i++) {
        console.log(i);
    }
    console.log(i+1);  // 问题：i 出了这个作用域还可以使用，能够打印出 101
}
```

ES6 let 关键字，解决局部作用域冲突的问题！

```javascript
function bbb() {
    for (let i = 0; i < 100; i++) {
        console.log(i);
    }
    console.log(i+1);  // Uncaught ReferenceError: i is not defined
}
```

**建议使用 let 去定义局部作用域的变量。**

- **常量 const**

在 ES6 之前，定义常量的方法：只有用全部大写字母命名的变量就是常量，建议不要修改这样的值。

```javascript
var PI = '3.14';
console.log(PI);
PI = '213';  // 可以修改这个常量的值
console.log(PI);
```

在 ES6 引入常量关键字，**const**

```javascript
const PI = '3.14';  // 只读常量
console.log(PI);
PI = '213';  // 报错 TypeError: Assignment to constant variable.
consolo.log(PI);
```

#### 4.3 方法

- **定义方法**

  方法就是把函数放在对象的里面，对象只有两个东西：**属性和方法**。

  ```javascript
  var sugar = {
      name: 'sugar',
      birth: 1997,
      // 方法
      age: function () {
          // 今年 - 出生的年
          var now = new Date().getFullYear();
          return now - this.birth;
      }
  }
  
  // 属性
  sugar.name
  // 方法（一定要带括号）
  sugar.age()
  ```

  this. 代表什么？拆开上面的代码看看。

  ```javascript
  function getAge() {
      // 今年 - 出生的年
      var now = new Date().getFullYear();
      return now - this.birth;
  }
  
  var sugar = {
      name: 'sugar',
      birth: 1997,
      // 方法
      age: getAge
  }
  // sugar.age()  ok
  // getAge()  Nan
  ```

  this 是无法指向的，是默认指向调用它的那个对象。

- **apply**

  apply 可以在 JS 中控制 this 的指向。8

  ```javascript
  function getAge() {
    // 今年 - 出生的年
    var now = new Date().getFullYear();
    return now - this.birth;
  }
  
  var sugar = {
    name: 'sugar',
    birth: 1997,
    // 方法
    age: getAge
  }
  // sugar.age()  ok
  // getAge()  Nan
  
  getAge.apply(sugar, [])  // this，指向 sugar，参数为空
  ```

### 5. 内部对象

- 标准对象

  ```javascript
  typeof 123
  "number"
  typeof '123'
  "string"
  typeof true
  "boolean"
  typeof NaN
  "number"
  typeof []
  "object"
  typeof {}
  "object"
  typeof Math.abs
  "function"
  typeof undefined
  "undefined"
  ```

#### 5.1 Date

- 基本使用

  ```javascript
  var now = new Date();  // Date Thu Aug 13 2020 10:30:29 GMT+0800 (中国标准时间)
  now.getFullYear();  // 年
  now.getMonth();  // 月  0~11 下标从0开始
  now.getDate();  // 日
  now.getDay();  // 星期几
  now.getHours();  // 时
  now.getMinutes();  // 分
  now.getSeconds();  // 秒
  
  now.getTime()  // 时间戳，全世界统一， 1970.1.1 0:00:00 到现在的毫秒数
  
  console.log(new Date(1597286292465)); // 时间戳 转 时间
  ```

- 转换

  ```javascript
  now = new Date(1597286292465);
  Date Thu Aug 13 2020 10:38:12 GMT+0800 (中国标准时间)
  
  now.toLocaleString()
  "2020/8/13 上午10:38:12"
  now.toGMTString()
  "Thu, 13 Aug 2020 02:38:12 GMT"
  ```

#### 5.2 JSON

> - JSON（JavsScript Object Notation，JS对象简谱）是一种轻量级的数据交换格式。
> - 简洁和清晰的层次结构使得 JSON 成为理想的数据交换语言。
> - 易于人阅读和编写，同时也易于机器解析和生成，并有效地提升网络传输效率。

早期，所有数据传输习惯使用 XML 文件。

在 JavaScript 中，一切皆为对象，任何 JS 支持的类型都可以用 JSON 来表示。

格式：

- 对象都用 {}
- 数组都用 []
- 所有的键值对 都是用 key: value

JSON字符串和 JS对象的转化：

```javascript
var user = {
    name: "sugar",
    age: 3,
    sex: '男'
}

// 对象转化为json字符串
var jsonUser = JSON.stringify(user);

// json字符串转化为对象  参数为 json字符串
var obj = JSON.parse('{"name": "sugar", "age":3, "sex": "男"}');
```

JSON 和 JS对象 的区别

```javascript
var obj = {a: 'hello', b: 'hellob'};
var json = '{"a": "hello", "b": "hellob"}'
```

#### 5.3 Ajax

- 原生的JS写法 xhr 异步请求
- JQuery 封装好的方法 $("#name").ajax("")
- axios 请求

### 6. 面向对象编程

#### 6.1 什么是面向对象

​	JavaScript、Java、C#。。。

​	面向对象：JavaScript有一些区别！

**类**：模板 原型对象

**对象**：具体的实例

在JavaScript中，这个需要更换一下思维方式。

- 原型

```javascript
var Student = {
    name: "student",
    age: 3,
    run: function () {
        console.log(this.name + " run...");
    }
};

var xiaoming = {
    name: "xiaoming"
}

// 小明的原型是 Student
xiaoming.__proto__ = Student;

var Bird = {
    fly: function () {
        console.log(this.name + " fly...");
    }
}
// 能随时修改其继承的原型
xiaoming.__proto__ = Bird;
```

```javascript
function Student(name) {
    this.name = name;
    
}
// 给Student新增一个方法
Student.prototype.hello = function () {
    alert("hello");
};
```

- class 继承

  class 关键字是在 ES6 引入的。

  1. 定义一个类，属性和方法

     ```javascript
     // ES6 以后
     // 定义一个学生的类
     class Student {
         constructor(name) {
             this.name = name;
         }
     
         hello() {
             alert("hello");
         }
     }
     
     var xiaoming = new Student("xiaoming");
     var xiaohong = new Student("xiaohong");
     xiaoming.hello();
     ```

  2. 继承

     ```javascript
     // 定义一个学生的类
     class Student {
       constructor(name) {
         this.name = name;
       }
     
       hello() {
         alert("hello");
       }
     }
     // 定义一个继承的子类
     class JuniorStudent extends Student {
       constructor(name, grade) {
         super(name);
         this.grade = grade;
       }
     
       myGrade() {
         alert("我是小学生");
       }
     }
     
     var xiaoming = new Student("xiaoming");
     var xiaohong = new JuniorStudent("xiaohong", 1);
     ```

     本质：查看对象原型

     <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200813123237216.png" alt="image-20200813123237216" style="zoom:50%;" />

- 原型链

  __proto__：

  <img src="https://picb.zhimg.com/80/v2-4374168403bf51faa50176cf1e7067ce_720w.jpg" alt="img" style="zoom:50%;" />

### 7. 操作BOM对象（重点）

- 浏览器介绍

  JavaScript 和 浏览器的关系？

  为了能够让JavaScript在浏览器中运行！

  BOM：浏览器对象模型

  - IE 6~11
  - Chrome
  - Safari
  - FireFox（Linux默认）

  第三方浏览器：

  - QQ浏览器
  - 360浏览器

- **window对象**

  window 代表浏览器窗口。

  ```javascript
  window.alert(1)  // 弹窗
  window.innerHeight // 内高
  window.innerWidth  // 内宽
  window.outerHeight  // 外高
  window.outerWidth  // 外宽
  // 可以调整浏览器窗口调节
  ```

- Navigator（不建议使用，可人为修改）

  Navigator 封装了浏览器的信息。

  ```javascript
  navigator.appName
  "Netscape"
  navigator.appVersion
  "5.0 (Macintosh)"
  navigator.userAgent
  "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:79.0) Gecko/20100101 Firefox/79.0"
  navigator.platform
  "MacIntel"
  ```

  大多数时候，不会使用 navigator 对象，因为会被人为修改。

  不建议使用这些属性来判断和编写代码。

- screen

  screen 获取显示器的信息。

  ```javascript
  screen.width
  2520
  screen.height
  1575
  ```

- **location**

  locatoin代表当前页面的 URL 信息。

  ```javascript
  host:"www.baidu.com"
  href:"https://www.baidu.com"
  protocol:"https:"
  reload:f reload()  // 刷新网页
  // 设置新的地址
  location.assign("https://www.tzchuan.com")
  ```

- document

  document代表当前的页面。HTMl、DOM文档树。

  ```javascript
  document.title
  "百度一下，你就知道"
  document.title = 'sugar'
  "sugar"
  ```

  获取具体的文档树节点。

  ```html
  <dl id="app">
      <dt>Java</dt>
      <dt>JavaSE</dt>
      <dt>JavaEE</dt>
  </dl>
  
  <script>
      var dl = document.getElementById('app')
  </script>
  ```

  获取 cookie

  ```javascript
  document.cookie
  "BIDUPSID=6B64B237F51CAC4AECBFC6DC757DAFA3; BAIDUID=C23D35B861059E4DBA023B5123E4D529:FG=1; PSTM=1585555733; BD_HOME=1; H_PS_PSSID=1428_32301_32046_32394_32116_26350_32503; BD_UPN=133252"
  ```

  劫持 cookie 的原理

  www.baidu.com

  ```html
  <script src="aa.js"></script>
  <!--恶意人员，获取你的cookie上传到他的服务器-->
  ```

  服务器端可以设置 cookie:httpOnly，只读，保证cookie安全

- history	

  hisotry 代表浏览器的历史记录。

  ```javascript
  history.back()  // 后退
  history.forward()  // 前进
  ```

### 8. 操作DOM对象（重点）

DOM：文档对象模型

**核心**

浏览器网页就是一个 DOM 树形结构！

- 更新：更新DOM节点
- 遍历DOM节点：得到DOM节点
- 删除：删除一个DOM节点
- 添加：添加一个新的DOM节点

要操作一个DOM节点，就必须先获得这个DOM节点。

- 获得DOM节点

  ```html
  
  <div id="father">
      <h1>标题一</h1>
      <p id="p1">p1</p>
      <p class="p2">p2</p>
  </div>
  
  <script>
      // 对应 css 选择器
      var h1 = document.getElementsByTagName("h1");
      var p1 = document.getElementById("p1");
      var p2 = document.getElementsByClassName("p2");
      var father = document.getElementById("father");
      
      // 获取父节点的所有子节点
      var children = father.children;  
      // father.firstChild
      // father.lastChild
      
  </script>
  ```

  这是原生代码，之后尽量使用 JQuery。

- 更新节点

  ```html
  <div id="id1">
      
  </div>
  
  <script>
      var id1 = document.getElementById("id1");
      // 操作文本
      id1.innerText = '123';  // 修改文本的值
      id1.innerHTML = '<strong>123</strong>';  // 可以解析 HTML 文本标签
      // 操作css
      id1.style.color = 'red';  // 属性使用 字符串 包裹
      id1.style.fontSize = '200px';  // 驼峰命名
      id1.style.padding = '2em';
  </script>
  ```

- 删除节点

  **删除节点的步骤：**先获取父节点，再通过父节点删除自己。

  ```html
  <div id="father">
      <h1>标题一</h1>
      <p id="p1">p1</p>
      <p class="p2">p2</p>
  </div>
  
  <script>
      // 只能通过父类删除自己
      var self = document.getElementById("p1");
      var father = self.parentElement;
      father.removeChild(self);
  
      // 删除是一个动态的过程
      father.removeChild(father.children[0])
      father.removeChild(father.children[1])
      father.removeChild(father.children[2])
  </script>
  ```

  注意：删除多个节点的时候，children 是在时刻变化的，删除节点的时候一定要注意！

- 插入节点

  获得了某个 DOM 节点，假设这个 DOM 节点是空的，通过 innerHTML 就可以增加一个元素了。但是如果这个 DOM 节点已经存在元素了，就不能这样做，会产生覆盖！

  可以通过追加操作解决。

  ```html
  <p id="js">JavaScript</p>
  <div id="list">
      <p id="se">JavaSE</p>
      <p id="ee">JavaEE</p>
      <p id="me">JavaME</p>
  </div>
  
  <script>
      var js = document.getElementById("js");
      var list = document.getElementById("list");
  		// 追加一个已经存在的节点
      list.appendChild(js);
  </script>
  ```

  效果：

  <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200814001311684.png" alt="image-20200814001311684" style="zoom:50%;" />

- 创建一个新的标签，实现插入

  ```html
  <p id="js">JavaScript</p>
  <div id="list">
      <p id="se">JavaSE</p>
      <p id="ee">JavaEE</p>
      <p id="me">JavaME</p>
  </div>
  
  <script>
      var js = document.getElementById("js");
      var list = document.getElementById("list");
      // 追加一个已经存在的节点
      list.appendChild(js);
  
      // 通过 JS 创建一个新的节点
      var newP = document.createElement("p");  // 创建一个 p 标签
      newP.id = "newP";
      newP.innerText = "Hello, Sugar";
      list.appendChild(newP);
  
      // 创建一个标签节点（通过setAttribute 可以设置任意的值）
      var myScript = document.createElement("script");
      myScript.setAttribute('type', 'text/javascript');
      list.appendChild(myScript);
  
      // 可以创建一个 Style 标签
      var myStyle = document.createElement("style");  // 创建一个空的 style 标签
      myStyle.setAttribute('type', 'text/css');
      myStyle.innerHTML = 'body {background-color: chartreuse;}';  // 设置标签内容
  
      document.getElementsByTagName("head")[0].appendChild(myStyle);
  
  </script>
  ```

- insertBefore

  ```javascript
  // insert，前插
  var ee = document.getElementById("ee");
  var js = document.getElementById("js");
  var list = document.getElementById("list");
  // 要包含的节点.insertBefore(newNode, targetNode);
  list.insertBefore(js, ee);
  ```

### 9. 操作表单（验证）

表单是什么？

form、DOM树

- 文本框  text
- 下拉框  <select>
- 单选框  radio
- 多选框  checkbox
- 隐藏域  hidden
- 密码框  password
- ...

表单的目的：提交信息

- 获取要提交的信息

  ```html
  <form action="post">
      <p>
          <span>用户名：</span> <input type="text" id="username">    
      </p>
      <!--多选框的值，就是定义好的value-->
      <p>
          <span>性别：</span>
          <input type="radio" name="sex" value="man" id="boy">男
          <input type="radio" name="sex" value="women" id="girl">女
      </p>
  
  </form>
  
  <script>
      var input_text = document.getElementById("username");
      var boy_radio = document.getElementById("boy");
      var girl_radio = document.getElementById("girl");
      
      // 得到输入框的值
      input_text.value
      // 修改输入框的值
      input_text.value = '123'
      
      // 对于单选框，多选框等等固定的值， boy_radio.value 只能取到固定的值
      boy_radio.checked;  // 查看返回的结果，是否为true，如果为true，则被选中
      girl_radio.checked = true;  // 赋值
  </script>
  ```

- 提交表单

  md5加密密码，表单优化

  ```html
  <!--
  表单绑定提交事件
  onsubmit= 绑定一个提交检测的函数， true， false
  将这个结果返回给表单，使用 onsubmit 接收！
  -->
  <form action="https://www.baidu.com/" method="post" onsubmit="return aaa()">
      <p>
          <span>用户名：</span> <input type="text" id="username" name="username">
      </p>
      <p>
          <span>密码：</span> <input type="password" id="input-password">
      </p>
  
      <input type="hidden" id="md5-password" name="password">
      <!--绑定事件-->
  <!--    <button type="submit" onclick="aaa()">提交</button>-->
      <!--或者给表单绑定事件-->
      <button type="submit">提交</button>
  </form>
  
  <script>
      function aaa() {
          var username = document.getElementById("username");
          var password = document.getElementById("input-password");
          var md5Password = document.getElementById("md5-password");
          // console.log(username.value);
          // console.log(password.value);
          // // MD5 算法
          // password.value = md5(password.value);
          // console.log(password.value);
  
          md5Password.value = md5(password.value);
          // 可以校验判断表单内容，true就是提交通过，false就是阻止提交
          return true;
      }
  </script>
  ```

  