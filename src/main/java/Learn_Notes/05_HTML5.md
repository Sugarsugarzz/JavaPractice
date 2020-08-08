### 1. 初识HTML

#### 1.1 什么是HTML

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805091108716.png" alt="image-20200805091108716" style="zoom:40%;" />

#### 1.2 W3C标准

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805091323301.png" alt="image-20200805091323301" style="zoom:40%;" />

#### 1.3 HTMl基本结构

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805091612382.png" alt="image-20200805091612382" style="zoom:50%;" />

### 2. 网页基本标签

#### 2.1 网页基本信息

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805091708229.png" alt="image-20200805091708229" style="zoom:40%;" />

```html
<!-- DOCTYPE： 告诉浏览器使用什么规范-->
<!DOCTYPE html>
<html lang="en">

<!-- head标签：代表网页的头部 -->
<head>
    <!--  meta描述性标签，用来描述网站的一些信息  -->
    <!--  meta一般用来做SEO  -->
    <meta charset="UTF-8">
    <meta name="keywords" content="Sugar">
    <meta name="description" content="学习Java">
    <!--  title：网页的标题  -->
    <title>第一个网页</title>
</head>

<!-- body标签：代表网页的主体 -->
<body>
Hello World
</body>

</html>
```

#### 2.2 网页基本标签

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805092119467.png" alt="image-20200805092119467" style="zoom:40%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>基本标签</title>
</head>
<body>

<!--标题标签-->
<h1>一级标签</h1>
<h2>二级标签</h2>
<h3>三级标签</h3>
<h4>四级标签</h4>
<h5>五级标签</h5>

<!--段落标签-->
<p>Sugar1,1234</p>
<p>Sugar2</p>
<p>Sugar3</p>
<p>Sugar4,567</p>

<!--水平线标签-->
<hr/>

<!--换行标签-->
<!--行间隔比p小-->
换行1 <br>
换行2
<br>
<!--粗体、斜体-->
粗体：<strong>粗体</strong> <br>
斜体：<em>斜体</em>
<br>
<!--特殊符号-->
空格：空&nbsp;&nbsp;&nbsp;&nbsp;格 <br>
大于：&gt; <br>
小于：&lt; <br>
版权：&copy;

<!--
特殊符号记忆方式
&  ;
查百度
-->

</body>
</html>
```

### 3. 图像，超链接，网页布局

#### 3.1 图像标签

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805093031380.png" alt="image-20200805093031380" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图像标签</title>
</head>
<body>
<!--
img学习
src：图片地址
    绝对地址，相对地址
    ../     -- 上一级目录

-->
<img src="resources/image1.png" alt="Sugar" title="悬停文字" width="300" height="300">

<a href="04_linked.html#down">跳转04的底部</a>
</body>
</html>
```

#### 3.2 链接标签

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805093535195.png" alt="image-20200805093535195" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805094701469.png" alt="image-20200805094701469" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>链接标签</title>
</head>
<body>
<!--使用name作为标记-->
<a name="top">顶部</a>


<!--
a标签
href：必填，表示要跳转到哪个页面
target：表示窗口在哪里打开
    _blank：在新标签打开
    _self：在自己的网页中打开
-->
<a href="01_first_page.html" target="_blank">点击我跳转到页面一</a>
<a href="https://www.baidu.com" target="_self">点击我跳转到百度</a>
<br>
<!--图片链接-->
<a href="01_first_page.html">
    <img src="resources/image1.png" alt="Sugar" title="悬停文字" width="300" height="300">
</a>


<!--锚链接
1. 需要一个锚标记
2. 跳转到标记
-->
<a href="#top">回到顶部</a>

<a name="down">底部</a>


<!--功能性链接

邮件链接：mailto
-->
<a href="mailto:406857586@qq.com">点击给我发邮件</a>


</body>
</html>
```

#### 3.3 布局元素

##### 行内元素和块元素

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805094813939.png" alt="image-20200805094813939" style="zoom:50%;" />

### 4. 列表，表格，媒体元素

#### 4.1 列表标签

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>列表学习</title>
</head>
<body>

<!--有序列表
应用范围：试卷，问答 ...
-->
<ol>
    <li>Java</li>
    <li>Java</li>
    <li>Java</li>
    <li>Java</li>
    <li>Java</li>
</ol>
<hr>

<!--无序列表
应用范围：导航，侧边栏 ...
-->
<ul>
    <li>Java</li>
    <li>Java</li>
    <li>Java</li>
    <li>Java</li>
    <li>Java</li>
</ul>
<hr>

<!--定义列表
dl：标签
dt：列表名称
dd：列表内容
应用范围：网站底部 ...
-->
<dl>
    <dt>学科</dt>
    <dd>Java</dd>
    <dd>Java</dd>
    <dd>Java</dd>
    <dd>Java</dd>

    <dt>城市</dt>
    <dd>北京</dd>
    <dd>北京</dd>
    <dd>北京</dd>
    <dd>北京</dd>
</dl>
</body>
</html>
```

#### 4.2 表格标签

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805095655207.png" alt="image-20200805095655207" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>表格学习</title>
</head>
<body>

<!--表格table
行：tr
列：td
-->

<table border="1px">
    <tr>
        <!-- colspan 跨列 -->
        <td colspan="4">1-1</td>
    </tr>
    <tr>
        <!-- rowspan 跨行 -->
        <td rowspan="2">2-1</td>
        <td>2-2</td>
        <td>2-3</td>
        <td>2-4</td>
    </tr>
    <tr>
        <td>3-2</td>
        <td>3-3</td>
        <td>3-4</td>
    </tr>
</table>

</body>
</html>
```

#### 4.3 媒体元素

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805100645015.png" alt="image-20200805100645015" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>媒体元素学习</title>
</head>
<body>

<!--音频和视频
src：资源路径
controls：控制进度条
autoplay：自动播放
-->
<video src="" controls autoplay></video>

<audio src="" controls autoplay></audio>

</body>
</html>
```

### 5. 表单及表单应用（重点）

#### 5.1 页面结构分析

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805101153078.png" alt="image-20200805101153078" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>页面结构分析</title>
</head>
<body>

<header>
    <h2>网页头部</h2>
</header>

<section>
    <h2>网页主体</h2>
</section>

<footer>
    <h2>网页脚部</h2>
</footer>

</body>
</html>
```

#### 5.2 iframe内联框架

在一个网站中嵌套另一个网站。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805101807465.png" alt="image-20200805101807465" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>iframe</title>
</head>
<body>

<!--iframe内联框架
src：地址
width：宽度
height：高度
-->
<iframe src="https://www.baidu.com" name="hello" frameborder="0" width="1000px" height="800px"></iframe>
<!--直接在iframe里生成新网页-->
<a href="01_first_page.html" target="hello">点击跳转</a>
<!--<iframe src="//player.bilibili.com/player.html?aid=55631961&bvid=BV1x4411V75C&cid=97257967&page=11" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true">-->
<!--</iframe>-->

</body>
</html>
```

#### 5.3 表单post和get及表单基本元素

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805102451268.png" alt="image-20200805102451268" style="zoom:50%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805103426101.png" alt="image-20200805103426101" style="zoom:50%;" />

#### 5.4 表单的应用

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805110634077.png" alt="image-20200805110634077" style="zoom:50%;" />

### 6. 表单初级验证（重点）

将大量的验证请求，现在前端验证，减轻后端的压力，也为了安全。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200805111220780.png" alt="image-20200805111220780" style="zoom:50%;" />

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录注册</title>
</head>
<body>

<h1>注册</h1>

<!--表单form
action：表单提交的位置，可以是网站，也可以是请求处理的地址
method：post，get 提交方式
    get方式提交：可以在URL看到提交的信息，不安全，高效
    post方式提交：安全，可以提交大文件
-->
<form action="01_first_page.html" method="get">
    <!--文本输入框：input type="text"
     value="用户名"  默认初始值
     maxlength="8"  最多能写几个字符
     size="30"  文本框长度
     readonly 只读，不能修改
    -->
<!--    <p>名字：<input type="text" name="username" value="admin" readonly></p>-->
    <p>名字：<input type="text" name="username" placeholder="请输入用户名" required></p>
    <!--密码框：input type="password"-->
    <p>密码：<input type="password" name="pwd" hidden value="123456"></p>

    <!--单选框标签
    input type="radio"
    value="boy"  单选框的值
    name="sex"  表示组
    disabled 不可选
    -->
    <p>
        <input type="radio" value="boy" name="sex" disabled>男
        <input type="radio" value="girl" name="sex" checked>女

    </p>

    <!--多选框
    input type="checkbox"
    -->
    <p>
        <input type="checkbox" value="sleep" name="hobby">睡觉
        <input type="checkbox" value="code" name="hobby" checked>敲代码
        <input type="checkbox" value="chat" name="hobby">聊天
    </p>

    <!--按钮
    input type="button"  普通按钮
    input type="image"  图像按钮
    input type="submit"  提交按钮
    input type="reset"  重置
    -->
    <p>
        <input type="button" name="btn1" value="点击">
        <input type="image" src="resources/image1.png">
    </p>

    <!--下拉框，列表框
    -->
    <p>国家：
        <select name="列表名称">
            <option value="cn" selected>中国</option>
            <option value="usa">美国</option>
            <option value="eth">瑞士</option>
            <option value="indian">印度</option>
        </select>
    </p>

    <!--文本域
    name="textarea" cols="30" rows="5"
    -->
    <p>反馈：
        <textarea name="textarea" cols="30" rows="5">文本内容</textarea>
    </p>

    <!--文本域
    input type="file" name="files"
    -->
    <p>文件：
        <input type="file" name="files">
        <input type="button" value="上传" name="upload">
    </p>

    <!--邮件验证
    -->
    <p>邮件验证：
        <input type="email" name="email">
    </p>

    <!--URL验证
    -->
    <p>URL验证：
        <input type="url" name="url">
    </p>

    <!--数字验证
    -->
    <p>商品数量：
        <input type="number" name="num" max="100" min="0" step="1">
    </p>

    <!--滑块
    input type="range"
    -->
    <p>音量：
        <input type="range" name="voice" min="0" max="100" step="2">
    </p>

    <!--搜索框
    -->
    <p>搜索框：
        <input type="search" name="search">
    </p>

    <!--增强鼠标可用性-->
    <p>
        <label for="mark">点我</label>
        <input type="text" id="mark">
    </p>

    <!--自定义邮箱
    正则表达式大全：https://www.jb51.net/tools/regexsc.htm-->
    <p>自定义邮箱：
        <input type="text" name="diymail" pattern="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$">
    </p>

    <p>
        <input type="submit" value="提交">
        <input type="reset" value="清空表单">
    </p>
</form>

</body>
</html>
```

