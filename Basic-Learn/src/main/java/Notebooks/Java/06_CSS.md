HTML + CSS + JavaScript

结构 + 表现 + 交互

### 1. 什么是CSS

如何学习

1. CSS是什么
2. CSS怎么用（快速入门）
3. **CSS选择器（重点+难点）**
4. 美化网页（文字，阴影，超链接，列表，渐变...）
5. 盒子模型
6. 浮动
7. 定位
8. 网页动画（特效）

#### 1.1 什么是CSS

Cascading Style Sheet 层叠级联样式表

CSS：表现层（美化网页）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200806091351357.png" alt="image-20200806091351357" style="zoom:50%;" />

#### 1.2 CSS发展史

CSS1.0

CSS2.0  DIV（块）+ CSS，HTML与CSS结构分离的思想，SEO

CSS2.1  浮动，定位

CSS3.0  圆角，阴影，动画... 浏览器兼容性

#### 1.3 快速入门

Style

建议样式：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200806092711656.png" alt="image-20200806092711656" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!--规范，<style> 可以编写css的代码，每个声明最好使用分号结尾
    语法：
        选择器 {
            声明1;
            声明2;
            声明3;
        }
    -->

    <link rel="stylesheet" href="css/style.css">

</head>
<body>

<h1>标题</h1>

</body>
</html>
```

```css
h1 {
    color: red;
}
```

##### CSS的优势：

1. 内容和表现分离
2. 网页结构表现统一，可以实现复用
3. 样式丰富
4. 建议使用独立于HTML的CSS文件
5. 利用SEO，容易被搜索引擎收录

#### 1.4 CSS三种导入方式

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!--内部样式-->
    <style>
        h1 {
            color: green;
        }
    </style>
    
    <!--外部样式-->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!--优先级：就近原则-->

<!--行内样式：在标签元素中，编写一个style属性，编写样式即可-->
<h1 style="color: red">标题</h1>

</body>
</html>
```

扩展：外部样式两种写法

- 链接式

  Html

  ```html
      <!--外部样式-->
      <link rel="stylesheet" href="css/style.css">
  ```

- 导入式

  css2.1

  ```html
      <!--外部样式-->
      <style>
          @import url("css/style.css");
      </style>
  ```

  一般使用link较多，也推荐使用link。

### 2. 选择器

作用：选择页面上的某一个或者某一类元素

#### 2.1 基本选择器

##### 1. 标签选择器：选择一类标签  标签{}

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>标签选择器</title>

    <style>
        /* 标签选择器，会选择到页面上所有的这个标签的元素 */
        h1 {
            color: red;
            background: lightblue;
            border-radius: 24px;
        }

        p {
            font-size: 80px;
        }
    </style>

</head>
<body>

<h1>学Java</h1>
<h1>学Java</h1>
<p>Sugar</p>

</body>
</html>
```

##### 2. 类选择器 class：选中所有class属性一致的标签，跨标签   .class{}

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>类选择器</title>

    <style>
        /* 类选择器的样式  .class的名称{ }
        好处：可以多个标签归类，是同一个 class，可以复用
        */
        .sugar {
            color: blue;
        }

        .tang {
            color: red;
        }
    </style>

</head>
<body>

<h1 class="sugar">标题1</h1>
<h1 class="tang">标题2</h1>
<h1 class="sugar">标题3</h1>

<p class="sugar">P标签</p>

</body>
</html>
```

##### 3. id选择器：全局唯一  #id{}

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>id选择器</title>

    <style>
        /* id选择器 ： id必须保证全局唯一！
          #id {}
          不遵循就近原则，固定的
          id选择器 > class选择器 > 标签选择器
       */
        #sugar {
            color: red;
        }

        .style1 {
            color: blue;
        }

        h1 {
            color: green;
        }
    </style>

</head>
<body>

<h1 class="style1" id="sugar">标题1</h1>
<h1 class="style1">标题2</h1>
<h1 class="style1">标题3</h1>
<h1>标题4</h1>
<h1>标题5</h1>

</body>
</html>
```

**优先级：id > class > 标签**

#### 2.2 层次选择器

##### 1. 后代选择器：在某个元素的后面所有元素

```css
/*后代选择器*/
body p {
  background: red;
}
```

##### 2. 子选择器：儿子一代

```css
/*子选择器*/
body > p {
  background: blueviolet;
}
```

##### 3. 相邻兄弟选择器：同辈，相邻，对下不对上

```css
/*相邻兄弟选择器，只有一个，相邻向下*/
.active + p {
  background: gray;
}
```

##### 4. 通用选择器

```css
/*通用选择器，当前选中玄素的向下的所有兄弟元素*/
.active ~ p {
  background: lightblue;
}
```

#### 2.3 结构伪类选择器

伪类：条件

```css
/*ul的第一个子元素*/
ul li:first-child {
  background: red;
}

/*ul的最后一个子元素*/
ul li:last-child {
  background: blue;
}

/*选中p1 ：定位到父元素，选择当前的第一个元素
选择当前p元素的父级元素，选中父级元素的第一个元素，并且是当前类型（p）元素才生效！
*/
p:nth-child(2) {
  background: lightblue;
}
/*选中父元素下的同类型（p）元素的第二个*/
p:nth-of-type(2) {
  background: yellow;
}

/*鼠标悬浮变色*/
a:hover {
  background: antiquewhite;
}
```

#### 2.4 属性选择器（常用）

把 id + class 结合了

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>属性选择器</title>

    <style>
        .demo a {
            float: left;
            display: block;
            height: 50px;
            width: 50px;
            border-radius: 10px;
            background: cornflowerblue;
            text-align: center;
            color: gainsboro;
            text-decoration: none;
            margin-right: 5px;
            font: bold 20px/50px Arial;
        }

        /*属性名，属性名 = 属性值（正则）
        = 绝对等于
        *= 包含元素，类似 contains
        ^= 以...开头的，类似 startswith
        $= 以...结尾的，类似endswith
        */

        /*存在id属性的元素  a[]{} */
        /*a[id]*/
        a[id] {
            background: yellow;
        }
        /*a[id=first]*/
        a[id=first] {
            background: greenyellow;
        }

        /*class 中带有 links 的元素*/
        a[class*="links"] {
            background: yellow;
        }

        /*选中 href 中以 http 开头的元素*/
        a[href^=http] {
            background: red;
        }

        a[href$=pdf] {
            background: red;
        }
    </style>
</head>
<body>

<p class="demo">

    <a href="http://www.baidu.com" class="links item first" id="first">1</a>
    <a href="http://www.sugar.com" class="links item active" target="_blank" title="test">2</a>
    <a href="images/123.html" class="links item">3</a>
    <a href="images/123.png" class="links item">4</a>
    <a href="images/123.jpg" class="links item">5</a>
    <a href="abc" class="links item">6</a>
    <a href="/a.pdf" class="links item">7</a>
    <a href="/abc" class="links item">8</a>
    <a href="abc.doc" class="links item">9</a>
    <a href="abcd.doc" class="links item last">10</a>

</p>

</body>
</html>
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200810125639909.png" style="zoom:40%;" />

### 3. 美化网页元素

#### 3.1 为什么要美化网页

1. 有效的传递页面信息
2. 美化网页、页面漂亮才能吸引用户
3. 凸显页面的主题
4. 提高用户体验

**span标签**：重点要突出的字，使用 span 标签套起来。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>span</title>

    <style>
        #title1 {
            font-size: 50px;
        }
    </style>
</head>
<body>
欢迎学习 <span id="title1">Java</span>
</body>
</html>
```

#### 3.2 字体样式

```html
<!--
font-family：字体
font-size：字体大小
font-weight：字体的粗细
color：字体颜色
-->
<style>
  body {
    font-family: "Courier New", 楷体 ;
    color: gray;
  }
  h1 {
    font-size: 50px;
  }
  .p1 {
    font-weight: bold;
  }
</style>
```

#### 3.3 文本样式

1. 颜色  color  rbg  rgba
2. **文本对齐的方式  text-align = center**
3. **首行缩进  text-indent: 2em;**
4. **行高  line-height**  单行文字上下居中！line-height = height
5. 装饰  text-decoration
6. 文本图片水平对齐 vertical-align: middle;

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文本样式</title>

    <!--
    颜色：
        单词
        RGB 0~F
        RGBA A：0~1
        text-align：排版，居中
        text-indent: 2em;  段落首行缩进
        height: 300px;
        line-height: 300px;
            行高 和 块 的高度一致，就可以实现上下居中。
    -->
    <style>
      h1 {
          color: rgba(0, 255, 255, 0.3);
          text-align: center;
      }
      .p1 {
          text-indent: 2em;
      }
      .p2 {
          background: blue;
          height: 300px;
          line-height: 300px;
      }
      /*上划线*/
      .l1 {
          text-decoration: underline;
      }
      /*中划线*/
      .l2 {
          text-decoration: line-through;
      }
      /*下划线*/
      .l3 {
          text-decoration: overline;
      }
      /*a标签去下划线*/
      a {
          text-decoration: none;
      }
      /*水平对齐 参照物， a, b*/
      p {
              vertical-align: middle;
          }
    </style>
</head>
<body>

<a href="">123</a>

<p class="l1">123123</p>
<p class="l2">123123</p>
<p class="l3">123123</p>

<h1>故事介绍</h1>

<p class="p1">TDP-43是一种重要的RNA结合蛋白，其基因突变可引起肌萎缩性侧索硬化症（ALS，俗称“渐冻人症”）。</p>

<p class="p2">最近，中科院上海有机所揭示RNA调控渐冻人症致病蛋白TDP-43形成应激核体。</p>

<p>
    <img src="images/image1.png" alt="">
    <span>sfdsafdsa</span>
</p>

</body>
</html>
```

#### 3.4 阴影

```css
/*text-shadow: 阴影颜色，水平偏移，垂直偏移，阴影半径*/
#price {
    text-shadow: lightblue 10px 10px 5px;
}
```

#### 3.5 超链接伪类

​	正常情况下，使用 a 和 a:hover

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>超链接伪类</title>

    <style>
        /*默认的颜色*/
        a {
            text-decoration: none;
            color: black;
        }
        /*鼠标悬浮的颜色*/
        a:hover {
            color: orange;
            font-size: 50px;
        }
        /*鼠标按住未释放的状态*/
        a:active {
            color: green;
        }
        /*访问后的状态*/
        a:visited {
            color: mediumvioletred;
        }
        /*text-shadow: 阴影颜色，水平偏移，垂直偏移，阴影半径*/
        #price {
            text-shadow: lightblue 10px 10px 5px;
        }
    </style>
</head>
<body>

<a href="#">
    <img src="images/image1.png" alt="">
</a>
<p>
    <a href="#">Java开发手册</a>
</p>
<p>
    <a href="#">作者：Sugar</a>
</p>
<p id="price">
    $99
</p>
</body>
</html>
```

#### 3.6 列表

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>列表样式</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>

<div id="nav">
    <h2 class="title">全部商品分类</h2>
    <ul>
        <li>
            <a href="#">图书</a>
            <a href="#">音像</a>
            <a href="#">数字商品</a>
        </li>
        <li>
            <a href="#">家用电器</a>
            <a href="#">手机</a>
            <a href="#">数码</a>
        </li>
        <li>
            <a href="#">电脑</a>
            <a href="#">办公</a>
        </li>
        <li>
            <a href="#">家居</a>
            <a href="#">家装</a>
            <a href="#">厨具</a>
        </li>
        <li>
            <a href="#">服饰鞋帽</a>
            <a href="#">个性化妆</a>
        </li>
        <li>
            <a href="#">礼品箱包</a>
            <a href="#">钟表</a>
            <a href="#">珠宝</a>
        </li>
        <li>
            <a href="#">食品饮料</a>
            <a href="#">保健食品</a>
        </li>
        <li>
            <a href="#">彩票</a>
            <a href="#">旅行</a>
            <a href="#">充值</a>
            <a href="#">票务</a>
        </li>
    </ul>
</div>

</body>
</html>
```

```css
#nav {
    width: 300px;
    background: gray;
}

.title {
    font-size: 18px;
    font-weight: bold;
    text-indent: 1em;
    line-height: 35px;
    /*颜色，图片，图片位置，平铺方式*/
    background: red url("../images/image1.png") 10px 10px no-repeat;
}

/*ul li*/
/*
list-style: none;
    none  去掉原点，数字
    circle  空心圆
    decimal  数字
    square  正方形
*/
ul {
    background: gray;
}
ul li {
    height: 30px;
    list-style: none;
    text-indent: 1em;
    background-image: url("../images/image1.png");
    background-repeat: no-repeat;
    background-position: 10px 2px;
}

a {
    text-decoration: none;
    font-size: 14px;
    color: black;
}

a:hover {
    color: orange;
    text-decoration: underline;
}

```

#### 3.7 背景

背景颜色

背景图片

```css
    <style>
        div {
            width: 1000px;
            height: 700px;
            border: 1px solid red;
            background-image: url("images/image1.png");
            /*默认是全部平铺的*/
        }

        .div1 {
            background-repeat: repeat-x;
        }
        .div2 {
            background-repeat: repeat-y;
        }
        .div3 {
            background-repeat: no-repeat;
        }
    </style>
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200810233258378.png" alt="image-20200810233258378" style="zoom:50%;" />

#### 3.8 渐变

> www.grabient.com

```css
    <!--径向渐变，圆形渐变-->
    <style>
        body {
            /*background-color: #21D4FD;*/
            background-image: linear-gradient(19deg, #21D4FD 0%, #B721FF 100%);
        }
    </style>
```

### 4. 盒子模型

#### 4.1 什么是盒子

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200810233911264.png" alt="image-20200810233911264" style="zoom:50%;" />

**margin**：外边距

**padding**：内边距

**border**：边框

#### 4.2 边框

1. 边框的粗细
2. 边框的样式
3. 边框的颜色

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>盒子模型</title>

    <style>
        /*body 总有一个默认的外边距margin：0，常见初始化操作*/
        /*h1, ul, li, a, body {*/
        /*    margin: 0;*/
        /*    padding: 0;*/
        /*    text-decoration: none;*/
        /*}*/
        /*border: 粗细，样式，颜色*/
        #box {
            width: 300px;
            border: 1px solid red;
        }
        h2 {
            font-size: 16px;
            background-color: green;
            line-height: 30px;
            color: white;
        }
        form {
            background: #21D4FD;
        }
        div:nth-of-type(1) input {
            border: 3px solid black;
        }

    </style>
</head>
<body>

<div id="box">
    <h2>会员登录</h2>
    <form action="#">
        <div>
            <span>用户名：</span>
            <input type="text">
        </div>
        <div>
            <span>密码：</span>
            <input type="text">
        </div>
        <div>
            <span>邮箱：</span>
            <input type="text">
        </div>
    </form>
</div>

</body>
</html>
```

#### 4.3 内外边距

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>外边距</title>

    <!--外边距的妙用：居中元素
    margin: 0 auto;
    -->
    <style>
        #box {
            width: 300px;
            border: 1px solid red;
            margin: 0 auto;
        }

        /*
        外间距，顺时针
        margin: 0
        margin: 0 1px 上下 和 左右
        margin 0 1px 2px 3px  上右下左
         */
        h2 {
            font-size: 16px;
            background-color: green;
            line-height: 30px;
            color: white;
            margin: 0 1px;

        }
        form {
            background: #21D4FD;
        }
        input {
            border: 1px solid black;
        }
        div:nth-of-type(1) {
            padding: 10px 2px;
        }

    </style>
</head>
<body>

<div id="box">
    <h2>会员登录</h2>
    <form action="#">
        <div>
            <span>用户名：</span>
            <input type="text">
        </div>
        <div>
            <span>密码：</span>
            <input type="text">
        </div>
        <div>
            <span>邮箱：</span>
            <input type="text">
        </div>
    </form>
</div>

</body>
</html>
```

盒子的计算方式：你这个元素到底多大？

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200811001447575.png" alt="image-20200811001447575" style="zoom:50%;" />

**margin + border +padding + 内容宽度**

#### 4.4 圆角边框

​	4 个角

```css
    <!--
    左上  右上  右下  左下（顺时针方向）
    -->
    <!--
       圆圈：  圆角 = 半径！
    -->
    <style>
        div {
            width: 100px;
            height: 100px;
            border: 10px solid red;
            border-radius: 100px;
        }
    </style>
```

#### 4.5 盒子阴影

```css
    <style>
        /*margin: 0 auto;  居中
        要求：块元素，有固定的宽度
        */
        div {
            width: 100px;
            height: 100px;
            margin: 0 auto;
            border: 10px solid red;
            box-shadow: 10px 10px 100px yellow;
        }
    </style>
```

### 5. 浮动

#### 5.1 标准文档流

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200811090847470.png" alt="image-20200811090847470" style="zoom:50%;" />

块级元素：独占一行

> H1-h6   p  div 列表...

行内元素：不独占一行

> span a img strong...

行内元素可以被包含在块级元素中，反之则不可以。

#### 5.2 display

```css
    <!--
    display
        block 块元素
        inline 行内元素
        inline-block  是块元素，但是可以内联，在一行！保持块的大小，但是能在行内内联。
        none  消失
    -->
    <style>
        div {
            width: 100px;
            height: 100px;
            border: 1px solid red;
            display: inline-block;
        }
        span {
            width: 100px;
            height: 100px;
            border: 1px solid red;
            display: inline;
        }
    </style>
```

1. 这也是一种实现行内元素排列的方式，但是很多情况都是用 float。

#### 5.3 float

1. 左右浮动 float

   float: right / left  元素都浮动在一层，在改变页面大小时位置会改变。

   clear: both  加一句这个，能让其在另起一行浮动。

```css
div {
    margin: 10px;
    padding: 5px;
}

#father {
    border: 1px #000 solid;
}

.layer01 {
    border: 1px #F00 dashed;
    display: inline-block;
    float: left;
}
.layer02 {
    border: 1px #00F dashed;
    display: inline-block;
    float: right;
}
.layer03 {
    border: 1px #060 dashed;
    display: inline-block;
    float: right;
}
.layer04 {
    border: 1px #666 dashed;
    font-size: 12px;
    line-height: 23px;
    display: inline-block;
}
```

#### 5.4 父级边框塌陷的问题

clear

```css
/*
clear: right;  右侧不允许有浮动元素
clear: left;  左侧不允许有浮动元素
clear: both;  两侧不允许有浮动元素
clear: none;  两侧不允许有浮动元素
*/
```

解决方案：

1. 增加父级元素的高度

   ```css
   #father {
       border: 1px #000 solid;
       height: 800px;
   }
   ```

2. 增加一个空的div标签，然后清除浮动

   ```html
       <div class="clear"></div>
   
   /*防止父级元素塌陷*/
   <style>
     .clear {
       clear: both;
       margin: 0;
       padding: 0;
   	}
   </style>
   ```

3. overflow
   在父级元素中增加一个overflow属性。比较常用。

   ```css
   #father {
       border: 1px #000 solid;
       /*在父级元素定义overflow：hidden，同样可以解决父级元素塌陷的问题*/
       overflow: hidden;
   }
   ```

4. 父类添加一个伪类：after

   ```css
   /*给父级添加伪类，解决父级元素塌陷问题（最常用的方法）*/
   #father:after {
       content: '';
       display: block;
       clear: both;
   }
   ```

**小结**：

1. 浮动元素后面增加空的div
   简单，代码中尽量避免空的 div
2. 设置父元素的高度
   简单，元素假设有个固定的高度，就会被限制
3. overflow 
   简单，下拉的一些场景避免使用
4. 父类添加伪类：after，推荐使用
   写法稍微复杂，但是没有副作用，推荐！

#### 5.5 对比

- display

  方向不可控制，没有父级边框塌陷的问题

- float

  浮动起来的话会脱离标准文档流，所以要解决父级边框塌陷的问题

#### 6. 定位

#### 6.1 相对定位

​	相对于原来的位置，进行指定的偏移。

​	相对定位后，它仍然在标准文档流中，原来的位置会被保留。

​	先带上 position: relative，然后设置方向，负数为同方向，正数为相反方向。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>相对定位</title>
    <!--相对定位
    相对于自己原来的位置进行偏移
    -->
    <style>
        body {
            padding: 20px;
        }
        div {
            margin: 10px;
            padding: 5px;
            line-height: 25px;
        }
        #father {
            border: 1px #666 solid;
            padding: 0;
        }
        #first {
            border: 1px #644 dashed;
            background-color: cornflowerblue;
            position: relative;  /*相对定位，上下左右*/
            top: -20px;
            left: 20px;
        }
        #second {
            border: 1px #611 dashed;
            background-color: indianred;
        }
        #third {
            border: 1px #622 dashed;
            background-color: yellow;
            position: relative;
            bottom: 10px;
        }
    </style>
</head>
<body>

<div id="father">
    <div id="first">第一个盒子</div>
    <div id="second">第二个盒子</div>
    <div id="third">第三个盒子</div>
</div>

</body>
</html>
```

##### 练习题：方块定位练习

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200811102730646.png" alt="image-20200811102730646" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>方块定位练习</title>

    <style>
        #box {
            width: 300px;
            height: 300px;
            padding: 10px;
            border: 1px solid red;
        }
        a {
            width: 100px;
            height: 100px;
            text-decoration: none;
            background: pink;
            line-height: 100px;
            text-align: center;
            color: white;
            display: block;
        }
        a:hover {
            background: lightskyblue;
        }
        .a2, .a4 {
            position: relative;
            right: -200px;
            top: -100px;
        }
        .a5 {
            position: relative;
            top: -300px;
            right: -100px;
        }

    </style>
</head>
<body>

<div id="box">
    <a class="a1" href="#">链接1</a>
    <a class="a2" href="#">链接2</a>
    <a class="a3" href="#">链接3</a>
    <a class="a4" href="#">链接4</a>
    <a class="a5" href="#">链接5</a>
</div>

</body>
</html>
```

#### 6.2 绝对定位

定位：基于xxx定位，上下左右。

1. 没有父级元素定位的前提下，相对于浏览器定位。
2. 假设父级元素存在定位（position：relative），我们通常会相对于父级元素进行偏移。
3. 在父级元素范围内移动。
   相对于父级或浏览器的位置，进行指定的偏移，绝对定位后，它不在标准文档流中，原来的位置不会被保留。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>默认情况</title>

    <style>
        div {
            margin: 10px;
            padding: 5px;
            line-height: 25px;
        }
        #father {
            border: 1px #666 solid;
            padding: 0;
            /*父级元素定位*/
            position: relative;
        }
        #first {
            border: 1px #644 dashed;
            background-color: cornflowerblue;
        }
        #second {
            border: 1px #611 dashed;
            background-color: indianred;
            /*绝对定位相对于父级元素，父级元素无定位则相对于浏览器*/
            position: absolute;
            right: 30px;
            top: -10px;
        }
        #third {
            border: 1px #622 dashed;
            background-color: yellow;
        }
    </style>
</head>
<body>

<div id="father">
    <div id="first">第一个盒子</div>
    <div id="second">第二个盒子</div>
    <div id="third">第三个盒子</div>
</div>

</body>
</html>
```

#### 6.3 固定定位  fixed

​	可以用在 返回顶部 按钮。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>固定定位</title>

    <style>
        body {
            height: 3000px;
        }
        div:nth-of-type(1) {  /*绝对定位：相对于浏览器*/
            width: 100px;
            height: 100px;
            background: red;
            position: absolute;
            right: 0;
            bottom: 0;
        }
        div:nth-of-type(2) {  /*固定定位*/
            width: 50px;
            height: 50px;
            background: yellow;
            position: fixed;
            right: 0;
            bottom: 0;
        }
    </style>
</head>
<body>

<div>div1</div>
<div>div2</div>

</body>
</html>
```

#### 6.4 z-index和透明度

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200811104456792.png" alt="image-20200811104456792" style="zoom:50%;" />

图层

Z-index：默认是0（最低），最高无限 999

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Z-index</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div id="content">
    <ul>
        <li><img src="images/image1.png" alt=""></li>
        <li class="tipText">学习Java</li>
        <li class="tipBg"></li>
        <li>时间：2020</li>
        <li>地点：北京</li>
    </ul>
</div>

</body>
</html>
```

```css
#content {
    width: 380px;
    padding: 0px;
    margin: 0px;
    overflow: hidden;
    font-size: 12px;
    line-height: 25px;
    border: 1px black solid;
}
ul, li {
    padding: 0px;
    margin: 0px;
    list-style: none;
}
/*父级元素相对定位*/
#content ul {
    position: relative;
}
.tipText, .tipBg {
    position: absolute;
    width: 380px;
    height: 25px;
    top: 310px;
}
.tipText {
    color: white;
    z-index: 999;  /*层级，0最低，999最高*/
}
.tipBg {
    background: black;
    opacity: 0.5;  /*透明度*/
    /*filter: Alpha(opacity=50); !*透明度另一种写法*!*/
}
```

### 7. 动画

**less自动生成css。**

**菜鸟教程**的**CSS动画**教程。

一般动画都是由 **JavaScript + canvas** 写的。

### 8. 总结

见对应Xmind。