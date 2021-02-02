### 1 概述

> Linux 简介

Linux 内核最初只是由芬兰人Linus Torvalds 在赫尔辛基大学上学时编写的。

Linux 是一套免费使用、自由传播的类 Unix 操作系统，是一个基于 POSIX（可移植系统接口）和 UNIX 的多用户、多任务、支持多线程和多 CPU 的操作系统。

Linux 能够运行主要的 UNIX 工具软件、应用程序和网络协议。它支持 32位和 64位硬件，Linux继承了 Unix **以网络为核心**的设计思想，是一个性能稳定的多用户网络操作系统。

> Linux 发行版

Linux 的发行版，就是将 Linux内核与应用软件做一个打包。

Kali Linux：安全渗透测试使用

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210130222100858.png" alt="image-20210130222100858" style="zoom:50%;" />

目前市面上较为知名的发行版：Ubuntu、RedHat、CentOS、Debian、Defora、SuSE、OpenSUSE、Arch Linux、SolusOS等。

> Linux 应用领域

当今各种场合都使用各种 Linux发行版，从嵌入式设备到超级计算机，并且在服务器领域确定了地位，通常服务器使用 LAMP（Linux + Apache + MySQL + PHP）或 LNMP（Linux + Nginx + MySQL + PHP）组合。

### 2 环境搭建

阿里云，配置安全组开放端口。

- 获取公网IP地址。
- 修改服务器登录密码
- xshell
- xftp
  <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210130224502653.png" alt="image-20210130224502653" style="zoom:30%;" />

### 3 初识 Linux

> 开机登录

开机会启动许多程序，在Windows中叫做”服务”（Service），在Linux叫做“守护进程”（daemon）。

开机成功后，会显示一个文本登录页面，在登录界面中提示输入用户名，用户输入的用户名将作为参数传给login程序来验证用户的身份，密码是不显示的，输完回车即可。

三种登录方式：

- 命令行登录
- ssh登录
- 图形界面登录

**最高权限账户为 root，可以操作一切！**

> 关机

Linux大多用在服务器上，很少遇到关机操作。

关机指令为：shutdown

```bash
sync  # 将数据由内存 同步到硬盘中

shutdown  # 关机指令，可以man shutdown看一下帮助文档。例如可以运行如下命令关机

shutdown -h 10  # 计算机将在十分钟后关机

shutdown -h now  # 立刻关机

shutdown -h 20:25  # 在今天20:25关机

shutdown -h +10  # 十分钟后关机

shutdown -r now  # 系统立刻重启

shutdown -r +10  # 系统十分钟后重启

reboot  # 重启，等同于 shutdown -r now

halt  # 关闭系统，等同于 shutdown -h now 和 poweroff
```

总结：无论重启还是关闭系统，首先要运行 **sync** 命令，把内存中的数据写到磁盘中。

> 系统目录结构

1. 一切皆文件
2. 根目录 /，所有文件都挂载在这个节点下

登录系统后，在当前命令窗口输入命令：`ls /`

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210130230320845.png" alt="image-20210130230320845" style="zoom:40%;" />

树状目录结构：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210130230355040.png" alt="image-20210130230355040" style="zoom:40%;" />

- **/bin**：bin是Binary的缩写，这个目录存放最经常使用的命令。
- **/boot**：这里存放的是启动Linux时使用的一些核心文件，包括一些连接文件和镜像文件。
- **/dev**：dev是Device的缩写，存放的是Linux的外部设备，在Linux中访问设备的方式和访问文件的方式是相同的。
- **/etc：这个目录用来存放所有系统管理所需要的配置文件和子目录。**
- **/home：用户的主目录，在Linux中，每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的。**
- **/lib**：这个目录里存放着系统最基本的动态连接共享库，其作用类似于Windows里的DLL文件。（不要动）
- **/lost+found**：这个目录一般是空的，当系统非法关机后，这里将存放一些文件。
- **/media**：Linux系统会自动识别一些设备，例如U盘、光驱等，当识别后，Linux会将识别的设备挂载到这个目录下。
- **/mnt**：系统提供该目录是为了让用户临时挂载别的文件系统的，可以将光驱挂载在/mnt上，然后进入该目录就可以查看光驱里面的内容了。
- **/opt：这是给主机额外安装软件所摆放的目录，比如安装一个ORACLE数据库就可以放到这个目录下。默认为空。**
- **/proc**：这个目录是一个虚拟目录，它是系统内存的映射，可以通过直接访问这个目录来获取系统信息。
- **/root：该目录为系统管理员，也称作超级权限者的用户主目录。**
- **/sbin**：s就是Super User的意思，这里存放的是系统管理员使用的系统管理程序。
- **/srv**：该目录存放一些服务启动之后需要提取的数据。
- **/sys**：这是Linux2.6内核的一个很大的变化，该目录下安装了2.6内核中新出现的一个文件系统 sysfs。
- **/tmp：存放一些临时文件。**
- **/usr：这是一个非常重要的目录，用户的很多应用程序和文件都放在这个目录下，类似于windows下的program files目录。**
- **/usr/bin**：系统用户使用的应用程序。
- **/usr/sbin**：超级用户使用的比较高级的管理程序和系统守护程序。Super
- **/usr/src**：内核源代码默认的放置目录。
- **/var：这个目录中存放着不断扩充着的东西，习惯将那些经常被修改的目录放在这个目录下，包括各种日志文件。**
- **/run**：是一个临时文件系统，存储系统启动以来的信息。当系统重启时，这个目录下的文件应该被删除或清除。
- **/www：使用宝塔才有，存放服务器网站相关的资源（环境、网站项目）**

### 4 Linux 常用的基本命令

#### 目录管理

> 绝对路径、相对路径

绝对路径：路径全称，比如 C:\ProgramData\Tencent/xxx.xx

在Tencent目录下，这个xxx.xx文件的相对路径就是 /xxx.xx

cd：切换目录命令

./：当前目录

cd ..：返回上一级目录

##### ls（列出目录）

```bash
# all，查看全部的文件，包括隐藏文件
ls -a
# 列出所有的文件，包含文件的属性和权限，没有隐藏文件
ls -l
# 所有Linux可以组合使用 ls -al
ls -al
```

##### cd（切换目录）

cd 目录名（绝对路径都是以 / 开头的，相对路径基于当前目录 ../../）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210131235237207.png" alt="image-20210131235237207" style="zoom:30%;" />

##### pwd（显示当前用户所在的目录）

##### mkdir（创建一个目录）

```bash
# 创建一个目录
mkdir test
# 递归创建多级目录
mkdir -p test1/test2/test3
```

##### rmdir（删除一个目录）

仅能够删除空的目录，如果下面存在文件，需要先删除文件，递归删除多个目录加 -p 参数。

```bash
# 删除一个目录
rmdir test1
# 删除多级目录
rmdir -p test1/test2/test3
```

##### cp（复制文件或目录）

cp 原来的地方 新的地方

如果文件重复，会提示是否覆写（y）或放弃（n）

```bash
# 拷贝文件至目录
cp 文件or目录 目录
```

##### rm（移除文件或目录）

```bash
# 忽略不存在的文件，不会出现警告，强制删除
rm -f 文件or目录
# 递归删除目录
rm -r 文件or目录
# 互动，删除询问是否删除
rm -rf 文件or目录
```

##### mv（移动文件或目录 | 重命名文件）

```bash
# 强制移动
mv -f 文件or目录
# 只替换已经更新过的文件
mv -u 文件or目录
# 重命名文件
mv 文件or目录 文件or目录
```

#### 基本属性

> 文件属性

如下，从左到右依次是 文件属性、文件数量、属主、属组，文件大小、时间、文件名

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210201002311814.png" alt="image-20210201002311814" style="zoom:50%;" />

Linux系统是一种典型的多用户系统，不同的用户处于不同的地位，拥有不同的权限。为了保护系统的安全性，Linux系统对不同的用户访问同一文件（包括目录文件）的权限做了不同的规定。

在Linux中可以使用 `ll` 或者 `ls -l` 命令来显示一个文件的属性以及文件所属的用户和组，如：

实例中，boot文件的第一个属性用”d“表示，”d”在Linux中代表该文件是一个目录文件。

在Linux中第一个字符代表这个文件是目录、文件或链接文件等等：

- **当为 [ d ] 则是目录**
- **当为 [ - ] 则是文件**
- **当为 [ I ] 则为链接文档（link file）**
- 当为 [ **b** ] 则为装置文件里面的可供储存的接口设备（可随机存取装置）
- 当为 [ **c** ] 则为装置文件里面的串行端口设备，例如键盘、鼠标（一次性读取装置）

接下来的字符中，以三个为一组，且均为 [ **rwx** ] 的三个参数的组合。

其中，[ **r** ] 代表可读（read），[ **w** ] 代表可写（write），[ **x** ] 代表可执行（execute）。

注意：这三个权限的位置不会改变，如果没有权限，就会出现 [ **-** ] 而已。

每个文件的属性由左边第一部分的10个字符来确定（如下）。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210201003255918.png" alt="image-20210201003255918" style="zoom:40%;" />

从左至右 0~9 这些数字来表示。

第 0 位确定文件类型，第 1~3 位确定属主（该文件的所有者）拥有该文件的权限，第 4~6 位确定属组（所有者的同组用户）拥有该文件的权限，第 7~9 位确定其他用户拥有该文件的权限。

其中：

第1、4、7位表示读权限，如果用“r”字符表示，则有读权限，如果用“-”表示，则没有读权限；

第2、5、8位表示写权限，如果用“w”字符表示，则有写权限，如果用“-”表示，则没有写权限；

第3、6、9位表示可执行权限，如果用“x”字符表示，则有执行权限，如果用“-”表示，则没有执行权限。

对于文件来说，它都有一个特定的所有者，也就是对该文件具有所有权的用户。

同时，在Linux系统中，用户是按组分类的，一个用户属于一个或多个组。

文件所有者以外的用户又可以分为文件所有者的同组用户和其他用户。

因此，Linux系统按**文件所有者**、**文件所有者同组用户**和**其他用户**来规定了不同的文件访问权限。

在以上实例中，boot文件是一个目录文件，属主和属组都为 root。

> 修改文件属性

##### chgrp（更改文件属组）

```bash
# -R：递归更改文件属组，就是在更改某个目录文件的属组时，如果加上 -R 的参数，那么该目录下的所有文件的属组都会更改
chgrp [-R] 属组名 文件名
```

##### chown（更改文件属主，也可以同时更改文件属组）

```bash
chown [-R] 属主名 文件名
chown [-R] 属主名：属组名 文件名
```

##### chmod（更改文件9个属性）（重要）

```bash
chmod [-R] xyz 文件或目录
```

Linux文件属性由两种设置方法，一种是**数字（常用）**，一种是**符号**。

Linux文件的基本权限就有九个，分别是 **owner/group/others** 三种身份各自的 **read/write/execute** 权限。

文件的权限字符为：[ -rwxrwxrwx ]，这九个权限是三个三个一组的！其中，可以使用数字来代表各个权限，各权限的分数对照表如下：

```bash
r:4			w:2			x:1  --->>>  chmod 777 filename  赋予所有用户可读可写可执行
可读可写不可执行		rw-  6
可读可写可执行			rwx  7
```

每种身份（owner/group/others）各自的三个权限（r/w/x）分数是需要累加的，例如当权限为：[ -rwxrwx--- ] 分数则是：

- owner = rwx = 4 + 2 + 1 = 7
- group = rwx = 4 + 2 + 1 = 7
- others = --- = 0 + 0 + 0 = 0

```bash
chmod 770 文件or目录
```

#### 文件内容查看

（CentOS 7 的网络配置目录：`cd /etc/sysconfig/network-scripts`）

Linux系统中使用以下命令来查看文件的内容：

##### cat（由第一行开始显示文件内容）

```bash
cat nl ifcfg-eth0
```

##### tac（从最后一行开始显示）

```bash
nl ifcfg-eth0
```

##### nl（显示的时候，顺道输出行号）

```bash
# 看代码的时候希望显示行号
nl ifcfg-eth0
```

##### more（一页一页的显示文件内容）

```bash
# 空格代表翻页，回车代表向下看一行，:f显示行号，q退出
more filename
```

##### less（与more类似，但比more更好的是可以往前翻页）

/ 向下查找，? 向上查找，n继续搜寻下一个，N向上寻找

```bash
# 同上，/xxx 可以查找字符串xxx（向下查询），如果向上查询，使用 ?xxx
less filename
```

##### head（只看头几行）

```bash
# -n参数控制显示行数
head -n 20 filename
```

##### tail（只看尾几行）

```bash
# -n你参数控制显示行数
tail -n 20 filename
```

可以使用 man [命令] 来查看各个命令的使用文档，如 `man cp`。

#### 拓展：Linux链接的概念

**只要链接过去的新文件，源文件内容发生变化，链接文件的内容同步变化！**

使用链接 `ln` 命令！

`touch` 命令创建文件！

```bash
touch filename
```

`echo` 命令写入字符串到文件！

```bash
echo "sugar" >> filename
```

Linu的链接分为两种：

- 硬链接 `ln 源文件 新文件`

  A -- B，假设 B 是 A 的硬链接，那么它们两个指向同一个文件！相当于复制了一个文件，但内容根据源文件即时同步更新。

  删了A，B依然能访问。即允许一个文件拥有多个路径，用户可以通过这种机制建立硬链接到一些重要文件上，防止误删！

- 软链接 `ln -s 源文件 新文件`

  类似Windows下的快捷方式，删除了源文件，快捷方式也访问不了！

#### Vim 编辑器

> What is Vim

Vim 是由 vi 发展出来的一个文本编辑器。代码补全、编译及错误跳转等方便编程的功能特别丰富。

简单来说，vi 是老式的字处理器，功能齐全，仍可改进。

vim 是程序开发者的一项好用工具。

所有的 Unix Like 系统都会内建 vi 文书编辑器，其他的文书编辑器则不一定会存在。

vim 的官方网站（http://www.vim.org）表示 vim 是一个程序开发工具而非文字处理软件。

vim 键盘图：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210201182637523.png" alt="image-20210201182637523" style="zoom:30%;" />

> 三种使用模式

vi/vim 分为三种模式：**命令模式（Command mode）**、**输入模式（Insert mode）**和**底线命令模式（Last line mode）**。

**命令模式：**

用户刚启动vi/vim，默认进入命令模式。

此状态下敲击键盘动作都会被Vim识别为命令，而非输入字符。

常用命令：

- **i**：切换到输入模式，以输入字符。
- **x**：删除当前光标所在处的字符。
- **：**（英文冒号）：切换到底线命令模式，以在最底一行输入命令。（如果是编辑模式，需要先按 **ESC** 退出编辑模式）

若需要编辑文本：启动Vim，进入命令模式，按下i，切换到输入模式。

命令模式只有一些最基本的命令，因此仍要依靠底线命令模式输入更多命令。

**输入模式：**

在命令模式下按下 **i** 进入输入模式。

在输入模式中，可以使用以下按键：

- **字符按键以及Shift组合**：输入字符
- **ENTER**：换行
- **BACKSPACE**：退格键，删除光标前一个字符
- **DEL**：删除键，删除光标后一个字符
- **方向键**：在文本中移动光标
- **HOME/END**：移动光标到行首/行尾
- **Page Up/Page Down**：上/下翻页
- **Insert**：切换光标为输入/替换模式，光标将变成竖线/下划线
- **ESC**：退出输入模式，切换到命令模式

**底线命令模式：**

在命令模式下按 **:**（英文冒号）进入底线命令模式，光标就移动到最底下，在这里输入底线命令！

底线命令模式可以输入单个或多个字符的命令，可用命令非常多。

在底线命令模式中，基本的命令有：

- **q**：退出程序
- **w**：保存文件

按 **ESC** 键可随时退出底线命令模式。



可以将这三个模式想成如下图标来表示：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210202212014943.png" alt="image-20210202212014943" style="zoom:30%;" />

> Vim 按键说明

**第一部分：一般模式可用的光标移动、复制粘贴、搜索替换等**

| 移动光标的方法         |                                                              |
| :--------------------- | ------------------------------------------------------------ |
| ==h 或 向左箭头键(←)== | 光标向左移动一个字符                                         |
| ==j 或 向下箭头键(↓)== | 光标向下移动一个字符                                         |
| ==k 或 向上箭头键(↑)== | 光标向上移动一个字符                                         |
| ==l 或 向右箭头键(→)== | 光标向右移动一个字符                                         |
| [Ctrl] + [f]           | 屏幕『向下』移动一页，相当于 [Page Down]按键 (常用)          |
| [Ctrl] + [b]           | 屏幕『向上』移动一页，相当于 [Page Up] 按键 (常用)           |
| [Ctrl] + [d]           | 屏幕『向下』移动半页                                         |
| [Ctrl] + [u]           | 屏幕『向上』移动半页                                         |
| +                      | 光标移动到非空格符的下一行                                   |
| -                      | 光标移动到非空格符的上一行                                   |
| ==数字< space>==       | 那个 n 表示『数字』，例如 20 。按下数字后再按空格键，光标会向右移动这一行的 n 个字符。 |
| 0 或功能键[Home]       | 这是数字『 0 』：移动到这一行的最前面字符处 (常用)           |
| $ 或功能键[End]        | 移动到这一行的最后面字符处(常用)                             |
| H                      | 光标移动到这个屏幕的最上方那一行的第一个字符                 |
| M                      | 光标移动到这个屏幕的中央那一行的第一个字符                   |
| L                      | 光标移动到这个屏幕的最下方那一行的第一个字符                 |
| G                      | 移动到这个档案的最后一行(常用)                               |
| nG                     | n 为数字。移动到这个档案的第 n 行。例如 20G 则会移动到这个档案的第 20 行(可配合 :set nu) |
| gg                     | 移动到这个档案的第一行，相当于 1G 啊！(常用)                 |
| ==数字< Enter>==       | n 为数字。光标向下移动 n 行(常用)                            |

| 搜索替换  |                                                              |
| :-------- | ------------------------------------------------------------ |
| ==/word== | 向光标之下寻找一个名称为 word 的字符串。例如要在档案内搜寻 vbird 这个字符串，就输入 /vbird 即可！(常用) |
| ==?word== | 向光标之上寻找一个字符串名称为 word 的字符串。               |
| n         | 这个 n 是英文按键。代表重复前一个搜寻的动作。举例来说， 如果刚刚我们执行 /vbird 去向下搜寻 vbird 这个字符串，则按下 n 后，会向下继续搜寻下一个名称为 vbird 的字符串。如果是执行 ?vbird 的话，那么按下 n 则会向上继续搜寻名称为 vbird 的字符串！ |
| N         | 这个 N 是英文按键。与 n 刚好相反，为『反向』进行前一个搜寻动作。例如 /vbird 后，按下 N 则表示『向上』搜寻 vbird 。 |

| 删除、复制与粘贴 |                                                              |
| :--------------- | ------------------------------------------------------------ |
| x, X             | 在一行字当中，x 为向后删除一个字符 (相当于 [del] 按键)， X 为向前删除一个字符(相当于 [backspace] 亦即是退格键) (常用) |
| nx               | n 为数字，连续向后删除 n 个字符。举例来说，我要连续删除 10 个字符， 『10x』。 |
| dd               | 删除游标所在的那一整行(常用)                                 |
| ndd              | n 为数字。删除光标所在的向下 n 行，例如 20dd 则是删除 20 行 (常用) |
| d1G              | 删除光标所在到第一行的所有数据                               |
| dG               | 删除光标所在到最后一行的所有数据                             |
| d$               | 删除游标所在处，到该行的最后一个字符                         |
| d0               | 那个是数字的 0 ，删除游标所在处，到该行的最前面一个字符      |
| yy               | 复制游标所在的那一行(常用)                                   |
| nyy              | n 为数字。复制光标所在的向下 n 行，例如 20yy 则是复制 20 行(常用) |
| y1G              | 复制游标所在行到第一行的所有数据                             |
| yG               | 复制游标所在行到最后一行的所有数据                           |
| y0               | 复制光标所在的那个字符到该行行首的所有数据                   |
| y$               | 复制光标所在的那个字符到该行行尾的所有数据                   |
| p, P             | p 为将已复制的数据在光标下一行贴上，P 则为贴在游标上一行！举例来说，我目前光标在第 20 行，且已经复制了 10 行数据。则按下 p 后， 那 10 行数据会贴在原本的 20 行之后，亦即由 21 行开始贴。但如果是按下 P 呢？那么原本的第 20 行会被推到变成 30 行。(常用) |
| J                | 将光标所在行与下一行的数据结合成同一行                       |
| c                | 重复删除多个数据，例如向下删除 10 行，[ 10cj ]               |
| u                | 复原前一个动作。(常用)                                       |
| [Ctrl]+r         | 重做上一个动作。(常用)                                       |

**第二部分：一般模式切换到编辑模式的可用按钮说明**

| 进入输入或取代的编辑模式 |                                                              |
| :----------------------- | ------------------------------------------------------------ |
| ==i, I==                 | 进入输入模式(Insert mode)：i 为『从目前光标所在处输入』， I 为『在目前所在行的第一个非空格符处开始输入』。(常用) |
| a, A                     | 进入输入模式(Insert mode)：a 为『从目前光标所在的下一个字符处开始输入』， A 为『从光标所在行的最后一个字符处开始输入』。(常用) |
| o, O                     | 进入输入模式(Insert mode)：这是英文字母 o 的大小写。o 为『在目前光标所在的下一行处输入新的一行』；O 为在目前光标所在处的上一行输入新的一行！(常用) |
| r, R                     | 进入取代模式(Replace mode)：r 只会取代光标所在的那一个字符一次；R会一直取代光标所在的文字，直到按下 ESC 为止；(常用) |
| ==[Esc]==                | 退出编辑模式，回到一般模式中(常用)                           |

**第三部分：一般模式切换到指令行模式的可用按钮说明**

| 指令行的储存、离开等指令                              |                                                              |
| :---------------------------------------------------- | ------------------------------------------------------------ |
| ==:w==                                                | 将编辑的数据写入硬盘档案中(常用)                             |
| :w!                                                   | 若文件属性为『只读』时，强制写入该档案。不过，到底能不能写入， 还是跟你对该档案的档案权限有关啊！ |
| ==:q==                                                | 离开 vi (常用)                                               |
| :q!                                                   | 若曾修改过档案，又不想储存，使用 ! 为强制离开不储存档案。    |
| 注：那个感叹号 (!) 在 vi 当中，常常具有『强制』的意思 |                                                              |
| ==:wq==                                               | 储存后离开，若为 :wq! 则为强制储存后离开 (常用)              |
| ZZ                                                    | 这是大写的 Z 喔！若档案没有更动，则不储存离开，若档案已经被更动过，则储存后离开！ |
| :w [filename]                                         | 将编辑的数据储存成另一个档案（类似另存新档）                 |
| :r [filename]                                         | 在编辑的数据中，读入另一个档案的数据。亦即将 『filename』 这个档案内容加到游标所在行后面 |
| :n1,n2 w [filename]                                   | 将 n1 到 n2 的内容储存成 filename 这个档案。                 |
| :! command                                            | 暂时离开 vi 到指令行模式下执行 command 的显示结果！例如 『:! ls /home』即可在 vi 当中看 /home 底下以 ls 输出的档案信息！ |
| ==:set nu==                                           | 显示行号，设定之后，会在每一行的前缀显示该行的行号           |
| :set nonu                                             | 与 set nu 相反，为取消行号！                                 |



#### 账号管理

> 简介

Linux是一个多用户多任务的分时操作系统，任何一个要使用系统资源的用户，都必须向系统管理员申请一个账号，然后以这个账号的身份进入系统。

用户的账号一方面可以帮助系统管理员对使用系统的用户进行跟踪，并控制用户对系统资源的访问；另一方面也可以帮助用户组织文件，并为用户提供安全性保护。

每个用户账号都拥有一个唯一的用户名和各自的密码。

用户在登录时需要键入正确的用户名和密码，才能够进入系统和自己的主目录。

实现用户账号的管理，需要完成的工作主要如下：

- 用户账号的添加、删除与修改。
- 用户密码的管理。
- 用户组的管理。

> 用户账号的管理

用户账号的管理工作主要涉及到用户账户的添加、修改和删除。

添加用户账号就是在系统中创建一个新账号，然后为新账号分配用户号、用户组、主目录和登录Shell等资源。

##### useradd（添加用户）

```bash
useradd -参数 用户名
	
	-c comment 指定一段注释性描述。
	-d 目录 指定用户主目录，如果此目录不存在，则同时使用-m选项，可以创建主目录。
	-g 用户组 指定用户所属的用户组。
	-G 用户组，用户组 指定用户所属的附加组。
	-m　使用者目录如不存在则自动建立。
	-s Shell文件 指定用户的登录Shell。
	-u 用户号 指定用户的用户号，如果同时有-o选项，则可以重复使用其他用户的标识号。
```

==Linux中一切皆文件，这里的添加用户就是往某个文件中写入了用户的信息！： /etc/passwd==

##### userdel（删除用户）

```bash
# 删除用户时，一并删除主目录
userdel -r 用户名 
```

##### usermod（修改用户）

修改用户有关属性，包括用户号、主目录、用户组、登录Shell等。

修改完毕后，记得查看用户配置文件（/etc/passwd）。

```bash
usermod -参数 用户名
# 常用的选项包括-c, -d, -m, -g, -G, -s, -u以及-o等，这些选项的意义与useradd命令中的选项一样，可以为用户指定新的资源值

usermod -s /bin/ksh -d /home/z -g developer sugar
此命令是将用户sugar的登录Shell修改为sugar，主目录修改为/home/z，用户组修改为developer。
```

##### su（切换用户）

1.切换用户的命令为：`su username` 【username是用户名】

2.从普通用户切换到root用户，还可以使用命令：sudo su

3.在终端输入exit或logout或使用快捷方式ctrl+d，可以退回到原来用户，其实ctrl+d也是执行的exit命令

4.在切换用户时，如果想在切换用户之后使用新用户的工作环境，可以在su和username之间加-，例如：【su - root】

$表示普通用户

\#表示超级用户，也就是root用户

##### hostname（修改主机名）

```bash
# 查看主机名
hostname
# 修改主机名
hostname sugar
```

> 用户密码设置问题

**一般通过root创建用户时，需要配置密码！**

##### passwd（修改指定用户密码，如果是超级用户）

```bash
passwd 用户名
```

**passwd（修改自己密码，如果是普通密码）**

```bash
passwd 即可，先输入当前密码，再输入新密码
```

> 锁定账户

冻结的账号，使其无法进入系统

```bash
passwd -l 用户名  # 锁定后无法登陆
passwd -d 用户名  # 清空密码，也无法登陆
```



#### 用户组管理

每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。不同Linux系统对用户组的规定有所不同，如**Linux下的用户属于与它同名的用户组**，这个用户组在创建用户时同时创建。

用户组的管理涉及用户组的添加、删除和修改。**组的增加、删除和修改实质上就是对 `/etc/group` 文件的更新。**

##### groupadd（创建用户组）

```bash
groupadd sugar
cat /etc/group
```

创建完用户组，可以得到组的id，这个id可以指定！如果不指定，自增1.

```bash
groupadd -g 520 sugar2
```

##### groupdel（删除用户组）

```bash
groupdel sugar
```

##### groupmod（修改用户组的权限信息和名字）

```bash
# 修改ID为666，组名为sugarnew
groupmod -g 666 -n sugarnew sugar
```

##### newgrp（切换用户的用户组）

```bash
# 登录当前用户 sugar
$ newgrp root
```

#### 拓展：/etc/passwd、/etc/shadow、/etc/group

> /etc/passwd

完成用户管理的工作有许多种方法，但是每一种方法实际上都是对有关的系统文件进行修改。

与用户和用户组相关的信息都存放在一些系统文件中，这些文件包括/etc/passwd, /etc/shadow, /etc/group等。

**/etc/passwd文件是用户管理工作涉及的最重要的一个文件。**

Linux系统中的每个用户都在/etc/passwd文件中有一个对应的记录行，它记录了这个用户的一些基本属性。

这个文件对所有用户都是可读的。它的内容类似下面的例子：

```
＃ cat /etc/passwd

root:x:0:0:Superuser:/:
daemon:x:1:1:System daemons:/etc:
bin:x:2:2:Owner of system commands:/bin:
sys:x:3:3:Owner of system files:/usr/sys:
adm:x:4:4:System accounting:/usr/adm:
uucp:x:5:5:UUCP administrator:/usr/lib/uucp:
auth:x:7:21:Authentication administrator:/tcb/files/auth:
cron:x:9:16:Cron daemon:/usr/spool/cron:
listen:x:37:4:Network daemon:/usr/net/nls:
lp:x:71:18:Printer administrator:/usr/spool/lp:
```

从上面可以看到，/etc/passwd中一行记录对应着一个用户，每行记录又被冒号(:)分隔为7个字段，其格式和具体含义如下：

```
用户名:口令:用户标识号:组标识号:注释性描述:主目录:登录Shell
```

1）"用户名"是代表用户账号的字符串。

通常长度不超过8个字符，并且由大小写字母和/或数字组成。登录名中不能有冒号(:)，因为冒号在这里是分隔符。

为了兼容起见，登录名中最好不要包含点字符(.)，并且不使用连字符(-)和加号(+)打头。

2）“口令”一些系统中，存放着加密后的用户口令字。

虽然这个字段存放的只是用户口令的加密串，**不是明文**，但是由于/etc/passwd文件对所有用户都可读，所以这仍是一个安全隐患。因此，现在许多Linux 系统（如SVR4）都使用了shadow技术，把真正的加密后的用户口令字存放到/etc/shadow文件中，而在/etc/passwd文件的口令字段中只存放一个特殊的字符，例如“x”或者“*”。

3）“用户标识号”是一个整数，系统内部用它来标识用户。

一般情况下它与用户名是一一对应的。如果几个用户名对应的用户标识号是一样的，系统内部将把它们视为同一个用户，但是它们可以有不同的口令、不同的主目录以及不同的登录Shell等。

通常用户标识号的取值范围是0～65 535。0是超级用户root的标识号，1～99由系统保留，作为管理账号，普通用户的标识号从100开始。在Linux系统中，这个界限是500。

4）“组标识号”字段记录的是用户所属的用户组。

它对应着/etc/group文件中的一条记录。

5)“注释性描述”字段记录着用户的一些个人情况。

例如用户的真实姓名、电话、地址等，这个字段并没有什么实际的用途。在不同的Linux 系统中，这个字段的格式并没有统一。在许多Linux系统中，这个字段存放的是一段任意的注释性描述文字，用作finger命令的输出。

6)“主目录”，也就是用户的起始工作目录。

它是用户在登录到系统之后所处的目录。在大多数系统中，各用户的主目录都被组织在同一个特定的目录下，而用户主目录的名称就是该用户的登录名。各用户对自己的主目录有读、写、执行（搜索）权限，其他用户对此目录的访问权限则根据具体情况设置。

7)用户登录后，要启动一个进程，负责将用户的操作传给内核，这个进程是用户登录到系统后运行的命令解释器或某个特定的程序，即Shell。

Shell是用户与Linux系统之间的接口。Linux的Shell有许多种，每种都有不同的特点。常用的有sh(Bourne Shell), csh(C Shell), ksh(Korn Shell), tcsh(TENEX/TOPS-20 type C Shell), bash(Bourne Again Shell)等。

系统管理员可以根据系统情况和用户习惯为用户指定某个Shell。如果不指定Shell，那么系统使用sh为默认的登录Shell，即这个字段的值为/bin/sh。

用户的登录Shell也可以指定为某个特定的程序（此程序不是一个命令解释器）。

利用这一特点，我们可以限制用户只能运行指定的应用程序，在该应用程序运行结束后，用户就自动退出了系统。有些Linux 系统要求只有那些在系统中登记了的程序才能出现在这个字段中。

8)系统中有一类用户称为伪用户（pseudo users）。

这些用户在/etc/passwd文件中也占有一条记录，但是不能登录，因为它们的登录Shell为空。它们的存在主要是方便系统管理，满足相应的系统进程对文件属主的要求。

常见的伪用户如下所示：

```
伪 用 户 含 义
bin 拥有可执行的用户命令文件
sys 拥有系统文件
adm 拥有帐户文件
uucp UUCP使用
lp lp或lpd子系统使用
nobody NFS使用
```

> /etc/shadow

**1、除了上面列出的伪用户外，还有许多标准的伪用户，例如：audit, cron, mail, usenet等，它们也都各自为相关的进程和文件所需要。**

由于/etc/passwd文件是所有用户都可读的，如果用户的密码太简单或规律比较明显的话，一台普通的计算机就能够很容易地将它破解，因此对安全性要求较高的Linux系统都把加密后的口令字分离出来，单独存放在一个文件中，这个文件是/etc/shadow文件。有超级用户才拥有该文件读权限，这就保证了用户密码的安全性。

**2、/etc/shadow中的记录行与/etc/passwd中的一一对应，它由pwconv命令根据/etc/passwd中的数据自动产生**

它的文件格式与/etc/passwd类似，由若干个字段组成，字段之间用":"隔开。这些字段是：

```
登录名:加密口令:最后一次修改时间:最小时间间隔:最大时间间隔:警告时间:不活动时间:失效时间:标志
```

1. "登录名"是与/etc/passwd文件中的登录名相一致的用户账号
2. "口令"字段存放的是加密后的用户口令字，长度为13个字符。如果为空，则对应用户没有口令，登录时不需要口令；如果含有不属于集合 { ./0-9A-Za-z }中的字符，则对应的用户不能登录。
3. "最后一次修改时间"表示的是从某个时刻起，到用户最后一次修改口令时的天数。时间起点对不同的系统可能不一样。例如在SCO Linux 中，这个时间起点是1970年1月1日。
4. "最小时间间隔"指的是两次修改口令之间所需的最小天数。
5. "最大时间间隔"指的是口令保持有效的最大天数。
6. "警告时间"字段表示的是从系统开始警告用户到用户密码正式失效之间的天数。
7. "不活动时间"表示的是用户没有登录活动但账号仍能保持有效的最大天数。
8. "失效时间"字段给出的是一个绝对的天数，如果使用了这个字段，那么就给出相应账号的生存期。期满后，该账号就不再是一个合法的账号，也就不能再用来登录了。

> /etc/group

用户组的所有信息都存放在/etc/group文件中。

将用户分组是Linux 系统中对用户进行管理及控制访问权限的一种手段。

每个用户都属于某个用户组；一个组中可以有多个用户，一个用户也可以属于不同的组。

当一个用户同时是多个组中的成员时，在/etc/passwd文件中记录的是用户所属的主组，也就是登录时所属的默认组，而其他组称为附加组。

用户要访问属于附加组的文件时，必须首先使用newgrp命令使自己成为所要访问的组中的成员。

用户组的所有信息都存放在/etc/group文件中。此文件的格式也类似于/etc/passwd文件，由冒号(:)隔开若干个字段，这些字段有：

```
组名:口令:组标识号:组内用户列表
```

1. "组名"是用户组的名称，由字母或数字构成。与/etc/passwd中的登录名一样，组名不应重复。
2. "口令"字段存放的是用户组加密后的口令字。一般Linux 系统的用户组都没有口令，即这个字段一般为空，或者是*。
3. "组标识号"与用户标识号类似，也是一个整数，被系统内部用来标识组。
4. "组内用户列表"是属于这个组的所有用户的列表/b]，不同用户之间用逗号(,)分隔。这个用户组可能是用户的主组，也可能是附加组。



#### 磁盘管理

##### df（列出文件系统整体的磁盘使用量）

df命令参数功能：检查文件系统的磁盘空间占用情况。可以利用该命令来获取硬盘被占用了多少空间，目前还剩下多少空间等信息。

语法：

```
df [-ahikHTm] [目录或文件名]
```

选项与参数：

- -a ：列出所有的文件系统，包括系统特有的 /proc 等文件系统；
- -k ：以 KBytes 的容量显示各文件系统；
- -m ：以 MBytes 的容量显示各文件系统；
- -h ：以人们较易阅读的 GBytes, MBytes, KBytes 等格式自行显示；
- -H ：以 M=1000K 取代 M=1024K 的进位方式；
- -T ：显示文件系统类型, 连同该 partition 的 filesystem 名称 (例如 ext3) 也列出；
- -i ：不用硬盘容量，而以 inode 的数量来显示

测试：

```bash
# 将系统内所有的文件系统列出来！
# 在 Linux 底下如果 df 没有加任何选项
# 那么默认会将系统内所有的 (不含特殊内存内的文件系统与 swap) 都以 1 Kbytes 的容量来列出来！
[root@sugar /]# df
Filesystem     1K-blocks   Used Available Use% Mounted on
devtmpfs          889100       0    889100   0% /dev
tmpfs             899460     704    898756   1% /dev/shm
tmpfs             899460     496    898964   1% /run
tmpfs             899460       0    899460   0% /sys/fs/cgroup
/dev/vda1       41152812 6586736  32662368  17% /
tmpfs             179896       0    179896   0% /run/user/0

# 将容量结果以易读的容量格式显示出来
[root@sugar /]# df -h
Filesystem     Size Used Avail Use% Mounted on
devtmpfs       869M     0 869M   0% /dev
tmpfs           879M 708K 878M   1% /dev/shm
tmpfs           879M 496K 878M   1% /run
tmpfs           879M     0 879M   0% /sys/fs/cgroup
/dev/vda1       40G  6.3G   32G  17% /
tmpfs           176M     0 176M   0% /run/user/0

# 将系统内的所有特殊文件格式及名称都列出来
[root@sugar /]# df -aT
Filesystem     Type       1K-blocks   Used Available Use% Mounted on
sysfs         sysfs               0       0         0    - /sys
proc           proc                0       0         0    - /proc
devtmpfs       devtmpfs       889100       0    889100   0% /dev
securityfs     securityfs          0       0         0    - /sys/kernel/security
tmpfs         tmpfs          899460     708    898752   1% /dev/shm
devpts         devpts              0       0         0    - /dev/pts
tmpfs         tmpfs          899460     496    898964   1% /run
tmpfs         tmpfs          899460       0    899460   0% /sys/fs/cgroup
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/systemd
pstore         pstore              0       0         0    - /sys/fs/pstore
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/freezer
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/cpuset
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/hugetlb
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/blkio
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/net_cls,net_prio
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/memory
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/pids
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/cpu,cpuacct
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/devices
cgroup         cgroup              0       0         0    - /sys/fs/cgroup/perf_event
configfs       configfs            0       0         0    - /sys/kernel/config
/dev/vda1     ext4         41152812 6586748  32662356  17% /
systemd-1      -                   -       -         -    - /proc/sys/fs/binfmt_misc
mqueue         mqueue              0       0         0    - /dev/mqueue
debugfs       debugfs             0       0         0    - /sys/kernel/debug
hugetlbfs     hugetlbfs           0       0         0    - /dev/hugepages
tmpfs         tmpfs          179896       0    179896   0% /run/user/0
binfmt_misc   binfmt_misc         0       0         0    - /proc/sys/fs/binfmt_misc

# 将 /etc 底下的可用的磁盘容量以易读的容量格式显示
[root@sugar /]# df -h /etc
Filesystem     Size Used Avail Use% Mounted on
/dev/vda1       40G  6.3G   32G  17% /
```

##### du（检查当前磁盘空间使用量）

Linux du命令也是查看使用空间的，但是与df命令不同的是Linux du命令是对文件和目录磁盘使用的空间的查看，还是和df命令有一些区别的，这里介绍Linux du命令。

语法：

```
du [-ahskm] 文件或目录名称
```

选项与参数：

- -a ：列出所有的文件与目录容量，因为默认仅统计目录底下的文件量而已。
- -h ：以人们较易读的容量格式 (G/M) 显示；
- -s ：列出总量而已，而不列出每个各别的目录占用容量；
- -S ：不包括子目录下的总计，与 -s 有点差别。
- -k ：以 KBytes 列出容量显示；
- -m ：以 MBytes 列出容量显示；

测试：

```bash
# 只列出当前目录下的所有文件夹容量（包括隐藏文件夹）:
# 直接输入 du 没有加任何选项时，则 du 会分析当前所在目录的文件与目录所占用的硬盘空间。
[root@sugar home]# du
16./redis
8./www/.oracle_jre_usage  # 包括隐藏文件的目录
24./www
48.                        # 这个目录(.)所占用的总量

# 将文件的容量也列出来
[root@sugar home]# du -a
4./redis/.bash_profile
4./redis/.bash_logout    
....中间省略....
4./kuangstudy.txt # 有文件的列表了
48.

# 检查根目录底下每个目录所占用的容量
[root@sugar home]# du -sm /*
0/bin
146/boot
.....中间省略....
0/proc
.....中间省略....
1/tmp
3026/usr  # 系统初期最大就是他了啦
513/var
```

通配符 * 来代表每个目录。

与 df 不一样的是，du 这个命令其实会直接到文件系统内去搜寻所有的文件数据。

##### mount和umount（磁盘挂载与卸除）

根文件系统之外的其他文件要想能够被访问，都必须通过“关联”至根文件系统上的某个目录来实现，此关联操作即为“挂载”，此目录即为“挂载点”,解除此关联关系的过程称之为“卸载”

Linux 的磁盘挂载使用mount命令，卸载使用umount命令。

磁盘挂载语法：

```
mount [-t 文件系统] [-L Label名] [-o 额外选项] [-n] 装置文件名 挂载点
```

测试：

```bash
# 将 /dev/hdc6 挂载到 /mnt/hdc6 上面！
[root@www ~]# mkdir /mnt/hdc6
[root@www ~]# mount /dev/hdc6 /mnt/hdc6
[root@www ~]# df
Filesystem           1K-blocks     Used Available Use% Mounted on
/dev/hdc6              1976312     42072   1833836   3% /mnt/hdc6

```

磁盘卸载命令 umount 语法：

```
umount [-fn] 装置文件名或挂载点
```

选项与参数：

- -f ：强制卸除！可用在类似网络文件系统 (NFS) 无法读取到的情况下；
- -n ：不升级 /etc/mtab 情况下卸除。

卸载/dev/hdc6

```
[root@www ~]# umount /dev/hdc6
```



#### 进程管理

> What is 进程

1. 在Linux中，每一个程序都有自己的一个进程，每个进程都有一个 pid 号。
2. 每一个进程，都有一个父进程。
3. 进程可以有两种存在方式：前台和后台。
4. 一般服务都是在后台运行的，基本的程序都是前台运行的。

> 命令

##### ps（查看当前系统中正在执行的各种进程信息）

ps -参数：

- -a：显示当前终端运行的所有进程信息（当前的进程）
- -u：以用户的信息显示进程
- -x：显示后台运行进程的参数

```bash
# Linux中，|叫做管道符，如A|B，就是B将A的结果作为输入

# ps -aux
# grap 查找文件中符合条件的字符串
ps -aux|grep mysql
```

一般只需要记住：ps -xx|grep 进程名，过滤进程信息

##### ps -ef（查看到父进程的信息）

```bash
ps -ef|grep mysql
# 看父进程，一般通过目录树结构来查看
pstree
	-p：显示父id
	-u：显示用户组
```

##### kill（结束进程）

kill -9 pid



### 5 环境安装

三种方式：rpm、压缩包、yum在线安装！

#### JDK安装（RPM）

1. 下载JDK（*.rpm)

2. 安装Java环境

   ```bash
   # 检查当前系统是否存在Java环境 java -version
   # 卸载
   # rpm -qa|grep jdk  # 检查JDK版本信息
   # rpm -e --nodeps 查出来的jdk名  # 强制卸载
   
   # 安装JDK
   # rpm -ivk rpm包
   
   # 配置环境变量
   ```

3. 配置环境变量  `/etc/profile`，对所有用户生效（RPM安装的不需要配置环境）`~/.bashrc`是某个用户的环境变量，只对该用户生效。

   ```shell
   JAVA_HOME=/usr/java/jdk1.8.0_221-amd64
   CLASSPATH=$JAVA_HOME%/lib;%JAVA_HOME%/jre/lib
   PATH=$JAVA_HOME$/bin;%JAVA_HOME%/jre/bin
   export PATH CLASSPATH JAVA_HOME
   ```

4. 使配置文件生效  `source /etc/profile`

5. 部署项目，防火墙相关命令

   ```shell
   # 查看防火墙服务状态
   systemctl status firewalld
   
   # 开启
   service firewalld start
   # 重启
   service firewalld restart
   # 关闭
   service firewalld stop
   
   # 查看防火墙规则
   firewall-cmd --list-all  # 查看全部信息
   firewall-cmd --list-ports # 只看端口信息
   
   # 开启端口
   firewall-cmd --zone=public --add-port=9000/tcp --permanent
   # 重启防火墙才生效
   systemctl restart firewalld.service
   
   # 阿里云需要配置安全组规则
   
   # 命令含义
   --zone  # 作用域
   --add-port=80/tcp  # 添加端口，格式为：端口/通讯协议
   --permanent  # 永久生效，没有此参数重启后失效
   ```

#### Tomcat安装（压缩包）

ssm war需要放到 tomcat 中运行。

1. 下载tomcat。`apache-tomcat-9.0.22.tar.gz`

2. 解压压缩包。 `tar -zxvf apache-tomcat-9.0.22.tar.gz`

3. 启动 tomcat 测试。

   ```shell
   cd bin
   # 执行 ./startup.sh
   # 停止 ./shutdown.sh
   ```

#### Docker安装（yum）

官方安装手册：https://docs.docker.com/install/linux/docker-ce/centos/

yum安装，需要联网。

> 安装

1. 检测CentOS版本。 `cat /etc/redhat-release`

2. 安装准备环境。

   ```shell
   yum -y install 包名  # yum install 在线安装，-y自动确定所有提示
   yum -y install gcc
   yum -y install gcc-c++
   ```

3. 清除以前的Docker版本

   ```shell
   yum remove docker \
   	docker-client \
   	docker-client-latest \
   	docker-common \
   	docker-latest-logrotate \
   	docker-logrotate \
   	docker-engine 
   ```

4. 安装基本工具包

   ```shell
   yum install -y yum-utils \
   	device-mapper-persistent-data \
   	lvm2
   ```

5. 下载Docker（阿里云镜像）

   ```shell
   yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
   ```

6. 更新yum软件包索引

   ```shell
   yum makecache fast
   ```

7. 安装Docker CE

   ```shell
   yum -y install docker-ce docker-ce-cli containerd.io
   ```

8. 启动Docker

   ```shell
   systemctl start docker
   ```

9. 测试

   ```shell
   docker version
   
   docker run hello-world
   
   docker images
   ```

#### 宝塔面板（懒人式安装）



