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

**MySQL在物理文件上的区别：**

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

#### 2.6 修改删除表

- 修改

  ```sql
  -- 修改表名  ALTER TABLE 旧表名 RENAME AS 新表名
  ALTER TABLE teacher RENAME AS teacher1
  -- 增加表的字段  ALTER TABLE 表名 ADD 字段名 列属性
  ALTER TABLE teacher1 ADD age INT(11)
  -- 修改表的字段（重命名，修改约束）
  -- ALTER TABLE 表名 MODIFY 字段名 列属性[可选]
  ALTER TABLE teacher1 MODIFY age VARCHAR(11)  -- 修改约束
  -- ALTER TABLE 表名 CHANGE 旧字段名 新字段名 列属性[可选]
  ALTER TABLE teacher1 CHANGE age age1 INT(11)  -- 字段重命名
  -- 删除表的字段
  -- ALTER TABLE 表名 DROP 字段名
  ALTER TABLE teacher1 DROP age1
  ```

- 删除

  ```sql
  -- 删除表
  DROP TABLE IF EXISTS teacher
  ```

  **所有的创建和删除操作尽量加上判断，以免报错**

注意点：

- ``  字段名使用这个包裹！
- 注释  /* */
- SQL 关键字大小写不敏感，建议写小写。
- 所有符号全部用英文！

### 3. MySQL数据管理

#### 3.1 外键（了解）

- 方式一：在创建表的时候，增加约束（比较复杂）

```sql
-- 年级表
CREATE TABLE `grade` (
	`gradeid` INT(10) NOT NULL AUTO INCREMENT COMMENT '年级id',
  `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

```sql
-- 学生表
-- 学生表的 gradeid 字段去引用年级表的 gradeid
-- 1、定义外键 key
-- 2、给这个外键添加约束（执行引用）
CREATE TABLE IF NOT EXISTS `student` (
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
  `pwd` VAHCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `gender` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
  `birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
  `gradeid` INT(10) NOT NULL COMMENT '年级id',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`),
  KEY `FK_gradeid` (`gradeid`),
  CCONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`
) ENGINE INNODB DEFAULT CHARSET=utf8
```

删除有外键关系的表的时候，必须要先删除被引用的表，即外键在的表（从表），再删除引用的表（主表）。

- 方式二：创建表成功后，添加外键约束

```sql
-- 创建表的时候没有外键关系
-- ALTER TABLE 表 ADD CONSTRAINT 约束名 FOREIGN KEY(作为外键的列) REFERENCES 被引用的表（被引用的字段）;
ALTER TABLE `student` 
ADD CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`);
```

以上的操作都是物理外键，数据库级别的外键，**不建议使用**。（避免数据库过多造成困扰，了解即可）

##### **最佳实践：**

- 数据库就是单纯的表，只用来存数据，只有行（数据）和列（字段）。
- 想使用多张表的数据，想使用外键，使用程序来实现。

#### 3.2 DML语言（重要）

**数据库的意义**：数据存储，管理。

DML 语言：数据操作语言

- INSERT
- UPDATE
- DELETE

#### 3.3 添加 INSERT

```sql
-- 插入语句（添加）
-- INSERT INTO 表名 [(字段名1，字段名2，...)] VALUES ('值1'，'值2'，...), ('值1'，'值2'，...), ('值1'，'值2'，...)
INSERT INTO `grade` (`gradename`) VALUES (`大四`)

-- 由于主键自增，所以可以省略（如果不写表的字段，就会按序一一匹配！）
INSERT INTO `grade` VALUES (`大四`)

-- 一般写插入语句，一定要数据和字段一一对应！

-- 插入多个字段
INSERT INTO `grade` (`gradename`) VALUES ('大二'), ('大三')

INSERT INTO `student` (`name`) VALUES ('张三')
INSERT INTO `student` (`name`, `pwd`, `gender`) VALUES ('张三', 'aaaaa', '男'), ('老五', 'abbbb', '女')
```

语法：`INSERT INTO 表名 [(字段名1，字段名2，...)] VALUES ('值1'，'值2'，...), ('值1'，'值2'，...), ('值1'，'值2'，...)`

注意事项：

1. 字段和字段之间使用 **英文逗号** 隔开。
2. 字段是可以省略的，但是后面的值必须一一对应，不能少。
3. 可以同时插入多条数据，VALUES 后面的值，需要使用 , 隔开即可。 `VALUES()， （）...`

#### 3.4 修改 UPDATE

> UPDATE  修改谁 （条件）  SET  原来的值  =  新值

```sql
-- 语法
-- UPDATE 表名 SET 字段名1 = 值1,[字段名2 = 值2, 字段名3 = 值3, ...] [WHERE 条件]

-- 修改学生名字，带了条件
UPDATE `student` SET `name`= 'Sugar' WHERE id = 1;

-- 不指定条件的情况下，会改动表的所有数据。
UPDATE `student` SET `name` = '长江七号';

-- 修改多个属性，逗号隔开
UPDATE `student` SET `name` = 'Sugar', `email` = '406857586@qq.com' WHERE id = 1;

-- 通过多个条件定位数据，条件无上限
UPDATE `student` SET `birthday` = CURRENT_TIME WHERE `name` = 'Sugar' and `gender` = '男';
```

语法：`UPDATE 表名 SET 字段名1 = 值1 [字段名2 = 值2, 字段名3 = 值3, ...] [WHERE 条件]`

条件：WHERE 子句 运算符  id 等于某个值，大于某个值，在某个区间内修改...

操作符会返回布尔值。

| 操作符              | 含义         | 范围        | 结果  |
| ------------------- | ------------ | ----------- | ----- |
| =                   | 等于         | 5=6         | false |
| <> 或 !=            | 不等于       | 5<>6        | true  |
| >                   | 大于         | 5>6         | false |
| <                   | 小于         | 5<6         | true  |
| >=                  | 大于等于     | 5>=6        | false |
| <=                  | 小于等于     | 5<=6        | true  |
| BETWEEN ... AND ... | 在某个范围内 | [2,5]       |       |
| AND                 | &&           | 5>1 and 1>2 | false |
| OR                  | \|\|         | 5>1 or 1>2  | true  |

语法：`UPDATE 表名 SET 字段名1 = 值1,[字段名2 = 值2, 字段名3 = 值3, ...] [WHERE 条件]`

注意：

- colnum_name：是数据库的列，尽量带上 ``

- 条件，筛选的条件，如果没有指定，则会修改所有的行。

- value，是一个具体的值，也可以是一个变量。

  ```sql
  UPDATE `student` SET `birthday` = CURRENT_TIME WHERE `name` = 'Sugar' and `gender` = '男';
  ```

- 多个设置的属性之间，使用英文逗号隔开。

#### 3.5 删除 DELETE and TRUNCATE

> DELETE 命令

```sql
-- 删除数据（避免这样写，会全部删除）
DELETE FROM `student`;

-- 删除指定数据
DELETE FROM `student` WHERE id = 1;
```

语法：`DELETE FROM 表名 [WHERE 条件]`

> TRUNCATE 命令

作用：完全清空一个数据库表，表的结构和索引约束不会变！

```sql
-- 清空 student 表
TRUNCATE `student`;
```

> DELETE 和 TRUNCATE 的区别

- 相同点：都能删除数据，都不会删除表结构。
- 不同点：
  - TRUNCATE 重新设置自增列，计数器会归零。
  - TRUNCATE 不会影响事务。

```sql
-- 测试 DELETE 和 TRUNCATE 的区别
CREATE TABLE `test` (
	`id` INT(4) NOT NULL AOTU_INCREMENT,
  `col` VARCHAR(20) NOT NULL,
  PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `test`(`col`) VALUES ('1'), ('2'), ('3');

DELETE FROM `test`;  -- 不会影响自增

TRUNCATE TABLE `test`;  -- 自增归零
```

了解即可：`DELETE删除的问题`,重启数据库，现象

- INNODB  自增列会从 1 开始（存在内存中，断电即失）
- MyISAM  继续从上一个自增量开始（存在文件中，不会丢失）

#### 4. DQL 查询数据（重点）
