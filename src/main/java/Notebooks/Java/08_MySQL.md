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

### 2. 操作数据库

操作数据库 > 操作数据库中的表 > 操作数据库中表的数据

**Mysql 关键字不区分大小写**

#### 2.1 操作数据库

1. 创建数据库

   ```sql
   CREATE DATABASE [IF NOT EXISTS] westos;
   ```

2. 删除数据库

   ```sql
   DROP DATABASE [IF EXISTS] hello;
   ```

3. 使用数据库

   ```sql
   USE school;  -- 如果是关键字，需要加个`，即`school`
   ```

4. 查看数据库

   ```sql
   SHOW DATABASES;  -- 查看所有数据库
   ```

#### 2.2 数据库的数据类型

1. 数值
   - tinnyint	十分小的数据（1个字节）
   - smallint    较小的数据（2个字节）
   - mediumint    中等大小的数据（3个字节）
   - **int    标准的整数（4个字节）** 
   - bigint    较大的数据（8个字节）
   - float  浮点数（4个字节）
   - **double  浮点数（8个字节）**（精度问题！）
   - decimal  字符串形式的浮点数，一般用于金融计算

2. 字符串
   - char  字符串，固定大小的（0~255）
   - **varchar  可变字符串（0~65535）**  常用的变量 String
   - tinytext  微型文本（2^8 - 1）
   - **text  文本串（2^16 - 1）**  保存大文本

3. 时间日期

   java.util.Date

   - date  YYYY-mm-dd 日期格式
   - time HH:mm:ss 时间格式
   - **datetime  YYYY-mm-dd HH:mm:ss 最常用的时间格式**
   - **timestamp  时间戳  1970.1.1到现在的毫秒数，全世界统一** 也较为常用！
   - year  年份表示

4. null
   - 没有值，未知
   - **注意：不要使用 NULL 进行运算，结果一定为 NULL**

#### 2.3 数据库的字段属性（重点）

**Unsigned：**

- 无符号的整数
- 声明了该列不能为负数

**zerofill：**

- 0填充的
- 不足的位数使用 0 来填充

**自增：**

- 通常理解为自增，自动在上一条记录的基础上 + 1
- 通常用来设计唯一的主键，index，必须是主键类型
- 可以自定义设计主键自增的起始值和步长

**非空：**NULL and NOT NULL

- 如果设置为not null，不给它赋值就会报错。
- 如果设置为null，不给它赋值，默认就是null。

**默认：**

- 设置默认的值
- 如果不指定该列的值，就会有默认的值

**扩展：**

> 项目中，每一个表，都必须存在以下五个字段，来表示每一个记录存在的意义！
>
> - id 主键
> - `version`  乐观锁
> - is_delete  伪删除
> - qmt_create  创建时间
> - gmt_update  修改时间

#### 2.4 创建数据库表

```sql
-- 目标：创建学生表（列，字段）
-- 学号int 登录密码varchar(20) 姓名，性别varchar(2) 出生日期(datetime) 家庭住址 email
-- 注：使用英文()，表的 名称 和 字段 尽量使用 `` 括起来
-- AUTO_INCREMENT 自增
-- 字符串使用单引号 ''
-- 所有的语句后面加,(英文的)，最后一个不用加
-- PRIMARY KEY 主键，一般一个表只有一个唯一的主键
CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
  `pwd` VAHCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `gender` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
  `birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`)
) ENGINE INNODB DEFAULT CHARSET=utf8
```

**格式：**

```sql
CREATE TABLE [IF NOT EXISTS] `表名` (
	`字段名` 列类型 [属性] [索引] [注释],
  `字段名` 列类型 [属性] [索引] [注释],
  ......
  `字段名` 列类型 [属性] [索引] [注释]
)[表类型][字符集设置][注释]
```

**常用命令：**

```sql
SHOW CREATE DATABASE school  -- 查看创建数据库的语句
SHOW CREATE TABLE student  -- 查看创建表的语句
DESC student  -- 显示表的结构
```

#### 2.5 数据表的类型

```sql
-- 关于数据库引擎
/*
INNODB  默认使用
MYISAM  早些年使用的
*/
```

|              | MYISAM | INNODB        |
| ------------ | ------ | ------------- |
| 事务支持     | 不支持 | 支持          |
| 数据行锁定   | 不支持 | 支持          |
| 外键约束     | 不支持 | 支持          |
| 全文索引     | 支持   | 不支持        |
| 表空间的大小 | 较小   | 较大，约为2倍 |

**常规使用操作：**

- MYSIAM  节约空间，速度较快
- INNODB  安全性高，事务的处理，多表多用户操作

> **在物理空间存在的位置：**
>
> 所有的数据库文件都存在 data 目录下，一个文件夹就对应一个数据库。
>
> 本质还是文件的存储！

**MySQL引起咋物理文件上的区别：**

- InnoDB 在数据库表中只有一个 *.frm 文件，以及上级目录下的 ibdata1 文件。
- MYISAM 对应文件
  - *.frm  表结构的定义文件
  - *.MYD  数据文件（data）
  - *.MYI  索引文件（index）

**设置数据库表的字符集编码：CHARSET=utf8**

- 不设置的话，会使用 mysql 默认的字符集编码 **不支持中文！**

- 默认编码是 Latin1，不支持中文。

- 在 my.ini 中配置默认的编码

  ```sql
  character-set-server=utf8
  ```

  