### 1. MySQL

JavaEE：企业级Java开发 Web

前端（页面：展示，数据！）

后台 （连接点：连接数据库 JDBC，连接前端（控制，控制视图的跳转，给前端传递数据））

数据库 （存数据，txt，excel，word）

> 只会写代码，学好数据库，基本混饭吃；
>
> 操作系统，数据结构与算法，不错的程序员；
>
> 离散数学，数字电路，体系结构，编译原理，实战，优秀程序员。

#### 1.1 为什么学习数据库

1. 岗位需求
2. 大数据时代
3. 被迫需求：存数据
4. **数据库是软件体系最核心的存在。**  DBA

#### 1.2 什么是数据库

数据库（DB，DataBase）

**概念**：数据仓库，**软件**，安装在操作系统之上。SQL，可以存储大量的数据，500万！

**作用**：存储数据，管理数据 

#### 1.3 数据库分类

**关系型数据库：**（SQL）

- MySQL，Oracle，Sql Server，DB2，SQLite
- 通过表和表之间，行和列之间的关系进行数据存储

**非关系型数据库：**（No SQL）

- Redis，MongoDB
- 以对象存储，通过对象自身的属性来决定。

**DBMS（数据库管理系统）**

- 数据库的管理软件，科学有效管理、维护和获取数据。
- MySQL 本质是一个数据库管理系统。

#### 1.4 MySQL简介

MySQL是一个**关系型数据库管理系统**。

之前属于 瑞典的MySQL AB公司，现属于 Oracle。

开源的数据库软件。

体积小，速度快，总体拥有成本低。

中小型网站或者大型网站，集群！

官网：https://www.mysql.com

5.7 稳定

8.0 最新的

​	**安装建议：**

	- 不要使用exe，影响注册表		

	- 尽可能使用压缩包安装

#### 1.5 安装MySQL

教程：网上找

1. 解压
2. 把解压得到的包放在自己的环境位置
3. 配置环境变量
4. 新建 mysql.ini 配置文件（windows）
5. 管理员模型下运行cmd，执行所有命令
6. 安装mysql服务
7. 初始化数据库文件
8. 启动mysql
9. 进入mysql 命令：-u root -p密码（不能加空格），修改密码（sql语句后面一定要加；）
10. 注掉 ini 中的跳过密码验证
11. 重启mysql，连接测试。

#### 1.6 安装SQLyog

还是navicat吧

1. 新建数据库 school

2. 新建一张表 student

   ```sql
   字段：id，name，age
   ```

3. 查看表

#### 1.7 连接数据库

命令行连接！

```sql
mysql -u root -p123456  -- 连接数据库

update mysql.user set authentication_string=password('123456') where user='root' and Host = 'localhost';  -- 修改用户密码

flush privileges;  -- 刷新权限
---------------------------------------------
-- 所有的语句都使用;结尾。
show databases; -- 查看所有的数据库
use school;  -- 切换数据库
show tables;  -- 查看所有的表
describe student;  -- 显示表结构
create database westos;  -- 创建一个数据库

exit;  -- 退出连接

-- 单行注释（sql 本来的注释）
/* 多行注释
1
2
*/
```

