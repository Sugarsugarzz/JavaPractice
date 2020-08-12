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

**定义方式一**

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

**定义方式二**

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

**调用函数**

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

**arguments**

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

**rest**

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









