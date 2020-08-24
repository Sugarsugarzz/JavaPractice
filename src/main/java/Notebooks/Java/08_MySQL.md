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

### 4. DQL 查询数据（重点）

#### 4.1 DQL

DQL（Data Query Language）：数据查询语言

- 所有的查询操作都用它 `Select`
- 简单、复杂的查询都用它
- **数据库中最核心的语言，最重要的语言**
- 使用频率最高的语句

SELECT 完整语法：

![image-20200819103341415](/Users/sugar/Library/Application Support/typora-user-images/image-20200819103341415.png)

测试用SQL：

```sql
create database if not exists `school`;
-- 创建一个school数据库
use `school`;

-- 创建学生表
drop table if exists `student`;
create table `student`(
`studentno` int(4) not null comment '学号',
`loginpwd` varchar(20) default null,
`studentname` varchar(20) default null comment '学生姓名',
`sex` tinyint(1) default null comment '性别，0或1',
`gradeid` int(11) default null comment '年级编号',
`phone` varchar(50) not null comment '联系电话，允许为空',
`address` varchar(255) not null comment '地址，允许为空',
`borndate` datetime default null comment '出生时间',
`email` varchar (50) not null comment '邮箱账号允许为空',
`identitycard` varchar(18) default null comment '身份证号',
primary key (`studentno`),
unique key `identitycard`(`identitycard`),
key `email` (`email`)
)engine=myisam default charset=utf8;

-- 创建年级表
drop table if exists `grade`;
create table `grade`(
	`gradeid` int(11) not null auto_increment comment '年级编号',
  `gradename` varchar(50) not null comment '年级名称',
    primary key (`gradeid`)
) engine=innodb auto_increment = 6 default charset = utf8;

-- 创建科目表
drop table if exists `subject`;
create table `subject`(
	`subjectno`int(11) not null auto_increment comment '课程编号',
    `subjectname` varchar(50) default null comment '课程名称',
    `classhour` int(4) default null comment '学时',
    `gradeid` int(4) default null comment '年级编号',
    primary key (`subjectno`)
)engine = innodb auto_increment = 19 default charset = utf8;

-- 创建成绩表
drop table if exists `result`;
create table `result`(
	`studentno` int(4) not null comment '学号',
    `subjectno` int(4) not null comment '课程编号',
    `examdate` datetime not null comment '考试日期',
    `studentresult` int (4) not null comment '考试成绩',
    key `subjectno` (`subjectno`)
)engine = innodb default charset = utf8;

-- 插入学生数据 其余自行添加 这里只添加了2行
insert into `student` (`studentno`,`loginpwd`,`studentname`,`sex`,`gradeid`,`phone`,`address`,`borndate`,`email`,`identitycard`)
values
(1000,'123456','张伟',0,2,'13800001234','北京朝阳','1980-1-1','text123@qq.com','123456198001011234'),
(1001,'123456','赵强',1,3,'13800002222','广东深圳','1990-1-1','text111@qq.com','123456199001011233');

-- 插入成绩数据  这里仅插入了一组，其余自行添加
insert into `result`(`studentno`,`subjectno`,`examdate`,`studentresult`)
values
(1000,1,'2013-11-11 16:00:00',85),
(1000,2,'2013-11-12 16:00:00',70),
(1000,3,'2013-11-11 09:00:00',68),
(1000,4,'2013-11-13 16:00:00',98),
(1000,5,'2013-11-14 16:00:00',58);

-- 插入年级数据
insert into `grade` (`gradeid`,`gradename`) values(1,'大一'),(2,'大二'),(3,'大三'),(4,'大四'),(5,'预科班');

-- 插入科目数据
insert into `subject`(`subjectno`,`subjectname`,`classhour`,`gradeid`)values
(1,'高等数学-1',110,1),
(2,'高等数学-2',110,2),
(3,'高等数学-3',100,3),
(4,'高等数学-4',130,4),
(5,'C语言-1',110,1),
(6,'C语言-2',110,2),
(7,'C语言-3',100,3),
(8,'C语言-4',130,4),
(9,'Java程序设计-1',110,1),
(10,'Java程序设计-2',110,2),
(11,'Java程序设计-3',100,3),
(12,'Java程序设计-4',130,4),
(13,'数据库结构-1',110,1),
(14,'数据库结构-2',110,2),
(15,'数据库结构-3',100,3),
(16,'数据库结构-4',130,4),
(17,'C#基础',130,1);
```



#### 4.2 查询所有的字段

```mysql
-- 查询全部的学生  SELECT 字段 FROM 表;
SELECT * FROM student;

-- 查询指定字段
SELECT `StudentNo`, `StudentName` FROM student;

-- 别名，给结果起一个名字  AS，可以给字段，也可以给表起别名
SELECT `StudentNo` AS 学号, `StudentName` AS 学生姓名 FROM student AS s;

-- 函数，拼接字符串 Concat(a,b)
SELECT CONCAT('姓名：', StudentName) AS 新名字 FROM student
```

语法：`SELECT 字段... FROM 表`

> 有的时候，列名不是那么见名知意，可以起别名 AS

- 去重 distinct

  作用：去除Select 查询出来的结果中重复的数据，重复的数据只显示一条。

  ```sql
  -- 查询一下那些同学参加了考试，成绩
  -- 查询全部的考试成绩
  SELECT * FROM result;  
  -- 查询那些同学参加了考试
  SELECT `StudentNo` FROM result;
  -- 发现重复数据，去重
  SELECT DISTINCT `StudentNo` FROM result;
  ```

- 数据库的列（表达式）

  ```sql
  SELECT VERSION();  -- 查看系统版本
  SELECT 100 * 3 - 1 AS 计算结果  -- 计算
  SELECT @@auto_increment_increment  -- 查询自增的步长（变量）
  
  -- 学员考试成绩 + 1分 查看
  SELECT `StudentNo`, `StudentResult`+1 AS '提分后' FROM result;
  ```

**总结：**

数据库中的表达式：文本值，列，null，函数，计算表达式，系统变量...

#### 4.3 where条件子句

作用：检索数据中**符合条件**的值

搜索的条件由一个或多个表达式组成！结果都是一个布尔值。

- 逻辑运算符

  | 运算符   | 语法             | 描述                           |
  | -------- | ---------------- | ------------------------------ |
  | and  &&  | a and b  a && b  | 逻辑与，两个为真，结果为真     |
  | or  \|\| | a or b  a \|\| b | 逻辑或，其中一个为真，结果为真 |
  | Not  !   | not a   ! a      | 逻辑非，真为假，假为真         |

  **尽量使用英文字母**

```sql
-- 查询所有成绩
SELECT `StudentNo`, `StudentResult` FROM result;

-- 查询考试成绩在 95 - 100分之间
SELECT `StudentNo`, `StudentResult` FROM result 
WHERE `StudentResult` >= 95 and `StudentResult` <= 100;

-- 模糊查询（区间）
SELECT `StudentNo`, `StudentResult` FROM result 
WHERE `StudentResult` BETWEEN 95 AND 100;

-- 除了1000号学生之外的同学的成绩
SELECT `StudentNo`, `StudentResult` FROM result 
WHERE `StudentNo` != 1000;
```

- 模糊查询：比较运算符

  | 运算符      | 语法                 | 描述                                     |
  | ----------- | -------------------- | ---------------------------------------- |
  | is null     | a is null            | 操作符为NULL，结果为真                   |
  | is not null | a is not null        | 操作符部位NULL，结果为真                 |
  | BETWEEN     | a between b and c    | 若a在b和c之间，结果为真                  |
  | like        | a like b             | SQL匹配，如果a匹配b，结果为真            |
  | in          | a in (a1, a2, a3...) | 假设a在a1或a2...其中某一个值中，结果为真 |

```sql
-- 模糊查询

-- ========== like ==========
-- 查询姓刘的同学
-- like 结合 %（代表0到任意一个字符）  _（一个字符）
SELECT `StudentNo`, `StudentName` FROM student
WHERE `StudentName` like '刘%';

-- 查询姓刘的同学，姓后面只有一个字的
SELECT `StudentNo`, `StudentName` FROM student
WHERE `StudentName` like '刘_';

-- 查询姓刘的同学，姓后面只有两个字的
SELECT `StudentNo`, `StudentName` FROM student
WHERE `StudentName` like '刘__';

-- 查询名字中间有嘉字的同学
SELECT `StudentNo`, `StudentName` FROM student
WHERE `StudentName` like '%嘉%';

-- ========== in（具体的一个或多个值） ==========
-- 查询 1001，1002，1003号学员
SELECT `StudentNo`, `StudentName` FROM student
WHERE StudentNo IN (1001, 1002, 1003);

-- 查询再北京的学生
SELECT `StudentNo`, `StudentName` FROM student
WHERE `Address` IN ('北京', '安徽');

-- ========== null 和 not null ==========
-- 查询地址为空的学生  null ''
SELECT `StudentNo`, `StudentName` FROM student
WHERE `Address`='' OR `Address` IS NULL

-- 查询有出生日期的同学  不为空
SELECT `StudentNo`, `StudentName` FROM student
WHERE `BornDate` IS NOT NULL 
```

#### 4.4 联表查询

> JOIN 对比

SQL 七种 JOINS：

<img src="https://img-blog.csdnimg.cn/20190726112038591.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODcxMDMz,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:80%;" />

**如果不用 JOIN 进行联表查询的话，默认是 INNER JOIN！**

- join（连接的表） on（判断的条件） 连接查询
- where 等值查询

```sql
-- ======================  联表查询 join  ======================

-- 查询参加了考试的同学（学号，姓名，科目编号，分数）
/*
思路：
1. 分析需求，分析查询的字段来自哪些表。（连接查询）
2. 确定使用哪种连接查询？  七种
确定交叉点：两个表中哪个数据是相同的
判断的条件：学生表中的 studentNo = 成绩表 studentNo
*/
SELECT s.studentNo, studentName, subjectNo, studentResult
FROM student AS s
INNER JOIN result AS r
WHERE r.studentNo = r.studentNo

-- Right Join
SELECT s.studentNo, studentName, subjectNo, studentResult
FROM student AS s
RIGHT JOIN result AS r
ON r.studentNo = r.studentNo

-- Left Join
SELECT s.studentNo, studentName, subjectNo, studentResult
FROM student AS s
LEFT JOIN result AS r
ON r.studentNo = r.studentNo
```

| 操作       | 描述                                         |
| ---------- | -------------------------------------------- |
| inner join | 如果表中至少有一个匹配，就返回行             |
| left join  | 即使右表中没有匹配，也会从左表中返回所有的值 |
| right join | 即使左表中没有匹配，也会从右表中返回所有的值 |

```sql
-- 查询缺考的同学
-- Left Join
SELECT s.studentNo, studentName, subjectNo, studentResult
FROM student AS s
LEFT JOIN result AS r
ON r.studentNo = r.studentNo
WHERE studentResult IS NULL
```

```sql
-- 思考题（查询所有参加考试的同学信息、学号、姓名、科目名、分数）
/*
思路：
1. 分析需求，分析查询的字段来自哪些表。student、result、subject（连接查询）
2. 确定使用哪种连接查询？  七种
确定交叉点：两个表中哪个数据是相同的
判断的条件：学生表中的 studentNo = 成绩表 studentNo
*/
SELECT s.studentNo, studentName, subjectName, StudentResult
FROM student s
RIGHT JOIN result r
ON r.sutndetNo = s.studentNo
INNER JOIN subject sub
ON r.subjectNo = sub.subjectNo

-- 要查询哪些数据 select ...
-- 从哪几个表中查 FROM 表  XXX JOIN 连接的表 ON 交叉条件
-- 假设存在一种多张表查询，先查询两张表，然后再慢慢增加

-- FROM a LEFT JOIN b
```

> 自连接（了解）

自己的表和自己的表连接，核心：**一张表折为两张一样的表即可**。

```sql
-- 创建数据库
CREATE TABLE `school`.`category`( 
	`categoryid` INT(3) NOT NULL COMMENT 'id', 
  `pid` INT(3) NOT NULL COMMENT '父id 没有父则为1', 
  `categoryname` VARCHAR(10) NOT NULL COMMENT '种类名字', 
  PRIMARY KEY (`categoryid`) 
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `school`.`category` (`categoryid`, `pid`, `categoryname`) VALUES ('2', '1', '信息技术');
insert into `school`.`CATEGOrY` (`categoryid`, `pid`, `categoryname`) values ('3', '1', '软件开发');
insert into `school`.`category` (`categoryid`, `PId`, `categoryname`) values ('5', '1', '美术设计');
insert iNTO `school`.`category` (`categoryid`, `pid`, `categorynamE`) VAlUES ('4', '3', '数据库');
insert into `school`.`category` (`CATEgoryid`, `pid`, `categoryname`) values ('8', '2', '办公信息');
insert into `school`.`category` (`categoryid`, `pid`, `CAtegoryname`) values ('6', '3', 'web开发');
inserT INTO `school`.`category` (`categoryid`, `pid`, `categoryname`) valueS ('7', '5', 'ps技术');
```

**父类：**

| categoryid | categoryName |
| ---------- | ------------ |
| 2          | 信息技术     |
| 3          | 软件开发     |
| 5          | 美术设计     |

**子类：**

| pid  | categoryid | categoryName |
| ---- | ---------- | ------------ |
| 3    | 4          | 数据库       |
| 2    | 8          | 办公信息     |
| 3    | 6          | web开发      |
| 5    | 7          | ps设计       |

**操作：查询父类对应的子类关系**

| 父类     | 子类     |
| -------- | -------- |
| 信息技术 | 办公信息 |
| 软件开发 | 数据库   |
| 软件开发 | web开发  |
| 美术设计 | ps设计   |

```sql
-- 查询父子信息：把一张表看做两张一模一样的表
SELECT a.categoryName AS parent, b.categoryName AS child
FROM category AS a, category AS b
WHERE a.categoryid = b.pid

-- 查询学生所属的年级（学号，姓名，年级名称）
SELECT studentNo, studentName, gradeName
FROM student s
INNER JOIN grade g
ON s.gradeid = g.gradeid
```

#### 4.5 分页和排序

```sql
-- ================  分页 limit 和 排序 order by ================
-- 排序： 升序 ASC 和 降序 DESC
-- ORDER BY 通过哪个字段排序，怎么排
ORDER BY StudentResult DESC

-- 分页
-- 缓解数据库压力，瀑布流
-- 每页只显示五条数据
-- limit 起始值，页面的大小
-- LIMIT 0,5  1 ~ 5
-- LIMIT 1,5  2 ~ 6
-- LIMIT 6,5  7 ~ 12
LIMIT 0,5

-- 第一页 limit 0,5
-- 第二页 limit 5,5
-- 第三页 limit 10,5
-- 第N页 limit (N-1) * pageSize, pageSize
-- pageSize：代表页面大小
-- (N-1) * pageSize：起始值
-- N：当前页
-- 数据总数/页面大小 = 总页数
```

#### 4.6 子查询

WHERE（值是计算出来的）

本质：在Where语句中，嵌套一个子查询语句。

WHERE （SELECT * FROM xxx）

```sql
-- ============  WHERE ============
-- 1、查询 数据库结构-1 的所有考试结果（学号，科目编号，成绩），降序排列
-- 方式一：使用连接查询
SELECT StudentNo, r.SubjectNo, StudentResult
FROM result r
INNER JOIN subject sub
ON r.SubjectNo = sub.SubjectNo
WHERE SubjectName = '数据库结构-1'
ORDER BY StudentResult DESC

-- 方式二：使用子查询（由里及外）
SELECT StudentNo, SubjectNo, StudentResult
FROM result 
WHERE SubjectNo = (
	SELECT SubjectNo FROM subject
  WHERE SubjectName = '数据库结构-1'
)

-- 2、查询 分数不小于80分的学生和姓名
SELECT DISTINCT s.StudentNo, StudentName
FROM student s
INNER JOIN result r
ON r.StudentNo = s.StudentNo
WHERE StudentResult >= 80

-- 查询 高等数学-2 且分数不小于80的学号和姓名 
-- 查询 高等数学-2 的编号
-- 联表查询
SELECT s.StudentNo, StudentName
FROM student s
INNER JOIN result r
ON s.StudentNo = r.StudentNo
INNER JOIN subject sub
ON r.SubjectNo = sub.SubjectNo
WHERE SubjectName = '高等数学-2' and StudentResult >= 80

-- 子查询
SELECT DISTINCT s.StudentNo, StudentName
FROM student s
INNER JOIN result r
ON r.StudentNo = s.StudentNo
WHERE StudentResult >= 80 AND SubjectNo = (
	SELECT SubjectNo FROM subject WHERE SubjectName = '高等数学-2'
)

-- 子查询2
SELECT StudentNo, StudentName FROM student WHERE StudentNo IN (
	SELECT StudentNo FROM result WHERE StudentReuslt > 80 AND SubjectNO = (
  	SELECT SubjectNo FROM subject WHERE SubjectName = '高等数学-2'
  )
)

```

### 5. MySQL 函数

官网：https://dev.mysql.com/doc/refman/5.7/en/func-op-summary-ref.html

#### 5.1 常用函数

```sql 
-- ============== 常用函数 ==============
-- 数学运算
SELECT ABS(-8)  -- 绝对值
SELECT CEILING(9.4)  -- 向上取整
SELECT FLOOR(9.4)  -- 向下取整
SELECT RAND()  -- 返回0~10之间的随机数
SELECT SIGN()  -- 判断一个数的负号  0-0 负数--1 正数-1

-- 字符串函数
SELECT CHAR_LENGTH('哈哈哈')  -- 字符串长度
SELECT CONCAT('我', '爱', '国家')  -- 拼接字符串
SELECT INSERT('helloworld', 1, 2, '超级')  -- 从某个位置开始替换某个长度
SELECT LOWER('Sugar')  -- 转小写
SELECT UPPER('sugar')  -- 转大写
SELECT INSTR('sugar', 's')  -- 返回第一次出现的子串索引
SELECT REPLACE('sugar', 'su', 'hh')  -- 替换出现的指定字符串
SELECT SUBSTR('sugar', 4, 5)  -- 返回指定的子字符串，（起始位置，长度）
SELECT REVERSE('Sugar')  -- 反转字符串

-- 查询姓周的同学，将姓改为 轴
SELECT REPLACE(studentName, '周', '轴') FROM student WHERE studentname like '周%'

-- 时间和日期函数（重要）
SELECT CURRENT_DATE()  -- 获取当前日期
SELECT CURDATE()  -- 获取当前日期
SELECT NOW()  -- 获取当前时间
SELECT LOCALTIME()  -- 本地时间
SELECT SYSDATE()  -- 系统时间
SELECT YEAR(NOW())  -- 年

-- 系统
SELECT SYSTEM_USER()
SELECT USER()
SELECT VERSION()
```

#### 5.2 聚合函数（常用）

|-|-|