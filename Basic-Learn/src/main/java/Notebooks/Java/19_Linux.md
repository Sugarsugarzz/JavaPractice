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

#### 4 Linux 常用的基本命令

#### 目录管理

> 绝对路径、相对路径

绝对路径：路径全称