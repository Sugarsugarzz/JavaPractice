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
  `pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
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

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200825092318508.png" alt="image-20200825092318508" style="zoom:50%;" />

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

#### 4.7 分组和过滤

**HAVING**：GROUP BY分组后满足的次要条件。  

```sql
-- 查询不同课程的平均分，最高分和最低分
-- 核心：根据不同的课程分组
SELECT SubjectName AVG(StudentResult) AS 平均分, MAX(StudentResult),MIN(StudentResult)
FROM result r
INNER JOIN subject sub
ON r.SubjectNo = SubjectNo 
GROUP BY r.SubjectNo  -- 通过什么字段来分组
HAVING 平均分 > 80  -- 分组后满足的次要条件
```

#### 4.8 Select小结

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200825092635488.png" alt="image-20200825092635488" style="zoom:50%;" />

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

| 函数名称       | 描述   |
| -------------- | ------ |
| COUNT()        | 计数   |
| SUM()          | 求和   |
| AVG()          | 平均值 |
| MAX()          | 最大值 |
| MIN()          | 最小值 |
| ......（官网） |        |

```sql
-- ================ 聚合函数 ================
-- 都能够统计表中的数据
SELECT COUNT(StudentName) FROM student;  -- COUNT(指定列），会忽略所有的null值
SELECT COUNT(*) FROM student;  -- COUNT(*），不会忽略null值，本质计算行数
SELECT COUNT(1) FROM student;  -- COUNT(1），不会忽略null值，本质计算行数

SELECT SUM(StudentResult) AS 总和 FROM result
SELECT AVG(StudentResult) AS 平均分 FROM result
SELECT MAX(StudentResult) AS 最高分 FROM result
SELECT MAX(StudentResult) AS 最低分 FROM result
```

#### 5.3 数据库级别的MD5加密（扩展）

**MD5**：增加算法复杂度和不可逆。

MD5破解网站的原理，背后有一个字典，MD5加密后的值，加密前的值，遍历得到。

```sql
CREATE TABLE `testmd5` (
	`id` INT(4) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `pwd` VARCHAR(50)	NOT NULL,
  PRIMARY KEY(`id)
)ENGINE=INNODB DEFAULT CHARSET=utf8

-- 明文密码
INSERT INTO testmd5 VALUES(1, `zhangsan`, `123456`), (2, `zhangsan`, `123456`), (3, `zhangsan`, `123456`)

-- 加密
UPDATE testmd5 SET pwd=MD%(pwd) WHERE id = 1  -- 条件加密

UPDATE testmd5 SET pwd=MD%(pwd)  -- 全部加密

-- 插入的时候加密
INSERT INTO testmd5 VALUES(1, `zhangsan`, MD5(`123456`))

-- 如何校验：将用户传递进来的密码，进行加密，然后比对加密后的值
SELECT * FROM testmd5 WHERE `name`='xiaoming' AND pwd=MD5('123456')
```

### 6. 事务

#### 6.1 什么是事务

要么都成功，要么都失败

1、SQL执行   A 给 B 转账  A  1000 --> 200   B 200

2、SQL执行   B 收到 A 的钱  A 800 --> B 400

将一组 SQL 放在一个批次中去执行

InnoDB支持事务。

> 事务原则：ACID原则（原子性，一致性，隔离性，持久性） （脏读、幻读...）

参考博客链接：https://blog.csdn.net/dengjili/article/details/82468576

**原子性（Atomicity）**

要么都成功，要么都失败

**一致性（Consistency）**

事务前后的数据完整性要保持一致。（两人总共1200块，不能多不能少）

**隔离性（Isolation）**

事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务的操作数据所干涉，事务之间要相互隔离。

**持久性（Durability）**

事务一旦提交则不可逆，被持久化到数据库中！

> 隔离所导致的一些问题

**脏读：**

指一个事务读取了另一个事务未提交的数据。

**不可重复读：**

在一个事务内读取表中的某一行数据，多次读取结果不同（这个不一定是错误，只是在某些场合不对）

**虚读（幻读）：**

是指在一个事务内读取到了别的事务新插入的数据，导致前后读取不一致。

#### 6.2 执行事务

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200825095224650.png" alt="image-20200825095224650" style="zoom:50%;" />

```mysql
-- ==================== 事务 ====================
-- mysql 是默认开启事务自动提交的
SET autocommit = 0  /* 关闭 */
SET autocommit = 1  /* 开启（默认） */
-- ============================================= 
-- 手动处理事务
SET autocommit = 0  -- 关闭自动提交
-- 事务开启
START TRANSACTION  -- 标记一个事务的开始，从这个之后的sql都在同一个事务内
-- 或者用 BEGIN;
BEGIN;
INSERT xx
INSERT xx

-- 提交：持久化（成功！）
COMMIT
-- 回滚：回到原来的样子（失败！）
ROLLBACK
-- 事务结束
SET autocommit = 1  -- 开启自动提交

-- 了解
SAVEPOINT 保存点名 -- 设置一个事务的保存点，如果事务比较长
ROLLBACK TO SAVEPOINT 保存点名  -- 回滚到保存点，比如游戏存档
RELEASE SAVEPOINT 保存点名  -- 撤掉保存点
```

#### 6.3 模拟转账事务场景

```mysql
-- 转账
CREATE DATABASE shop CHARACTER utf8 COLLATE utf_8_general_ci;
USE shop;

CREATE TABLE `account` (
	`id` INT(3) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `money` DECIMAL(9, 2) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO account(`name`, `money`)
VALUES('A', 2000.00), ('B', 10000.00)

-- 模拟转账：事务  TRANSACTION
SET autocommit = 0;  -- 关闭自动提交
START TRANSACTION  -- 开启一个事务（一组事务）
UPDATE account SET money=money-500 WHERE `name` = 'A'  -- A减去500
UPDATE account SET money=money+500 WHERE `name` = 'B'  -- B加上500

COMMIT;  -- 提交事务，就被持久化了
ROLLBACK;  -- 回滚

SET autocommit = 1;  -- 恢复默认开启自动提交

-- 模拟转账：事务  BEGIN
BEGIN;  -- 开启一个事务（一组事务）
UPDATE account SET money=money-500 WHERE `name` = 'A'  -- A减去500
UPDATE account SET money=money+500 WHERE `name` = 'B'  -- B加上500

COMMIT;  -- 提交事务，就被持久化了
ROLLBACK;  -- 回滚
```

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200826142801256.png" alt="image-20200826142801256" style="zoom:50%;" />

### 7. 索引

> MySQL官方对索引的定义为：**索引（Index）是帮助MySQL高效获取数据的数据结构。**提取句子主干，就可以得到索引的本质：索引就是数据结构。

#### 7.1 索引的分类

> 在一个表中，主键索引只能有一个，唯一索引可以有多个。

- 主键索引（PRIMARY KEY）
  - 唯一的标注，主键不可重复，只能有一个列作为主键。
- 唯一索引（UNIQUE KEY）
  - 避免重复的列出现，唯一索引可以重复，多个列都可以标识为唯一索引。
- 常规索引（KEY / INDEX）
  - 默认的，可以用 INDEX 或 KEY 关键字来设置。
- 全文索引（FullText）
  - 在特定的数据库引擎下才有，MyISAM
  - 快速定位数据

```mysql
-- ================ 索引的使用 ================
-- 1、在创建表的时候给字段增加索引
-- 2、创建完毕后，增加索引

-- 显示所有的索引信息
SHOW INDEX FROM student;
-- 增加一个全文索引  索引名（列名）
ALTER TABLE `student` ADD FULLTEXT INDEX `studentName`(`studentName`);
CREATE INDEX id_app_user_name ON app_user(`name`);

-- EXPLAIN 分析 SQL 执行的状况
EXPLAIN SELECT * FROM student;  -- 非全文索引
EXPLAIN SELECT * FROM student WHERE MATCH(studentName) AGAINST('刘')

```

#### 7.2 测试索引

```mysql
CREATE TABLE `app_user` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(50) DEFAULT '',
`email` varchar(50) NOT NULL,
`phone` varchar(20) DEFAULT '',
`gender` tinyint(4) unsigned DEFAULT '0',
`password` varchar(100) NOT NULL DEFAULT '',
`age` tinyint(4) DEFAULT NULL,
`create_time` datetime DEFAULT CURRENT_TIMESTAMP,
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-- 插入100万数据
-- MySQL 编程
DELIMITER $$  -- 写函数之前必须要写，标志
CREATE FUNCTION mock_data()
RETURN INT
BEGIN 
	DECLARE num INT DEFAULT 1000000;
	DECLARE i INT DEFAULT 0;
	WHILE i < num DO
		-- 插入语句
		INSERT INTO app_user(`name`, `email`, `phone`, `gender`, `password`, `age`) VALUES (CONCAT('用户', i), '406857586@qq.com', CONCAT('18', FLOOR(RAND() * (99999999 - 10000000) + 10000000)), FLOOR(RAND() * 2), UUID(), FLOOR(RAND() * 100))
		SET i = i + 1;
  END WHILE;
END;

-- 创建索引  id_表名_字段名
-- CREATE INDEX 索引名 on 表 （列名）
CREATE INDEX id_app_user_name ON app_user(`name`);

-- 查看
SELECT * FROM app_user WHERE `name` = '用户9999';  -- 0.993 sec
SELECT * FROM app_user WHERE `name` = '用户9999';  -- 索引后 0.001 sec

```

**索引在小数据量时候，用处不大，但是在大数据的时候，区别十分明显。**

#### 7.3 索引原则

- 索引不是越多越好。
- 不要对经常变动的数据加索引。
- 小数据量的表不需要加索引。
- 索引一般加在常用来查询的字段上。

> **索引的数据结构**
>
> 阅读：http://blog.codinglabs.org/articles/theory-of-mysql-index.html

Hash 类型的索引

**BTREE**：InnoDB 的默认数据结构

### 8. 权限管理和备份

#### 8.1 用户管理

一般可以可视化操作，在Linux服务器上需要命令行操作。

- SQL命令操作

**用户表**：mysql.user

**本质**：对这张表进行增删改查

```mysql
-- 创建用户  CREATE USER 用户名 IDENTIFIED BY '密码'
CREATE USER sugar IDENTIFIED BY '123456'

-- 修改密码（修改当前用户密码）
SET PASSWORD = PASSWORD('111111')

-- 修改密码（修改指定用户密码）
SET PASSWORD FROM sugar = PASSWORD('123456')

-- 重命名  RENAME USER 原命名 TO 新名字
RENAME USER sugar TO sugar2

-- 用户授权  ALL PRIVILEGES（全部的权限）ON 库.表 TO 用户名
-- ALL PRIVILEGES 除了给别人授权，其他都能干（只有ROOT能授权）
GRANT ALL PRIVILEGES ON *.* TO sugar

-- 查看权限
SHOW GRANTS FOR sugar  -- 查看指定用户的权限
SHOW GRANTS FOR root@localhost  
-- ROOT用户的权限：GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION

-- 撤销权限  REVOKE 哪些权限，在哪个库撤销，给谁撤销
REVOKE ALL PRIVILEGES ON *.* FROM sugar

-- 删除用户
DROP USER sugar 
```

#### 8.2 MySQL备份

为什么要备份：

- 保证重要的数据不丢失
- 数据转移 A --> B

MySQL数据库备份的方式：

- 直接拷贝物理文件。（mysql目录下的data文件夹）

- 在数据库可视化工具（Navicat）中手动导出。

- 使用命令行导出 mysqldump

  ```bash
  # mysqldump -h 主机 -u 用户名 -p 密码 数据库 表名 > 物理磁盘位置/../文件名		
  mysqldump -hlocalhost -uroot -p123456 school student > D:/a.sql

  # mysqldump -h 主机 -u 用户名 -p 密码 数据库 表1 表2 表3 > 物理磁盘位置/../文件名		
  mysqldump -hlocalhost -uroot -p123456 school student teacher parents > D:/a.sql
  
  # mysqldump -h 主机 -u 用户名 -p 密码 数据库 > 物理磁盘位置/../文件名		
  mysqldump -hlocalhost -uroot -p123456 school > D:/a.sql
  
  # --------------------------------------------
  # 导入
  # 登录的情况下，切换到指定的数据库
  mysql -uroot -p123456
  use school;
  # source 备份文件
  source D:/a.sql
  ```

假设要备份数据库，防止数据丢失，把数据库sql文件给别人即可。

### 9. 规范数据库设计

#### 9.1 为什么需要设计

**当数据库比较复杂的时候，需要设计**

**糟糕的数据库设计：**

- 数据冗余，浪费空间。
- 设置物理外键多，导致数据库插入和删除都会麻烦，产生异常【屏蔽使用物理外键】。
- 程序的性能差

**良好的数据库设计：**

- 节省内存空间。
- 保证数据库的完整性。
- 方便系统开发。

**软件开发中，关于数据库的设计：**

- 分析需求：分析业务和需要处理的数据库需求。
- 概要设计：设计关系图 E-R 图。

**设计数据库的步骤：（个人博客为例）**

- 收集信息，分析需求
  - 用户表（用户登录注销，个人信息，写博客，创建分类）
  - 分类表（文章分类，谁创建的）
  - 文章表（文章的信息）
  - 评论表（文章下的评论信息）
  - 友链表（友链信息）
  - 自定义表（系统信息，某个关键的字，或者一些主字段）key：value
- 标识实体（把需求落地到每个字段）**（数据库不区分大小写，用下划线命名字段！）**
  - **user** - id, username, password, gender, age, sign
  - **category** - id, category_name, create_user_id
  - **blog** - id, title, content, author_id, category_id, content, create_time, update_time, love(点赞)
  - **comment** - id, blog_id, user_id, content, create_time, user_id_parent(回复的人的id)
  - **links** - id, links_name, href, sort
- 标识实体之间的关系
  - user -> blog  写博客
  - user -> category  创建分类
  - user -> user  关注
  - user -> user  -> blog  评论

#### 9.2 三大范式

**为什么需要数据规范化？**

- 信息重复
- 更新导致异常
- 插入异常
  - 无法正常显示信息
- 删除异常
  - 丢失有效的信息

> 三大范式（了解）
>
> 阅读：https://www.cnblogs.com/wsg25/p/9615100.html

**第一范式（1NF）**

原子性：保证每一列都不可再分。

**第二范式（2NF）**

前提：满足第一范式。

每张表只描述一件事情。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827094731373.png" alt="image-20200827094731373" style="zoom:50%;" />

**第三范式（3NF）**

前提：满足第一范式和第二范式。

需要确保数据表中的每一列数据都喝主键直接相关，而不能间接相关。

**规范性 和 性能的问题**

关联查询的表不得超过三张表（阿里要求）

- 考虑商业化的需求和目标。（成本，用户体验）数据库的性能更加重要

- 在规范性能的问题时，需要适当的考虑下规范性

- 故意给某些表增加一些冗余的字段（从夺标查询中变为单表查询）

  订单-商品id-商品信息

- 故意增加一些计算列（从大数据量降低为小数据量的查询：索引）

### 10. JDBC（重点）

#### 10.1 数据库驱动

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827101315982.png" alt="image-20200827101315982" style="zoom:50%;" />

程序会通过数据库驱动，和数据库打交道。

#### 10.2 JDBC

SUN 公司为了简化开发人员（对数据库的统一）操作，提供了一个（Java操作数据库的）规范 JDBC。

这些规范的实现由具体的厂商去做，对于开发人员，只需要掌握 JDBC 接口即可。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827101556366.png" alt="image-20200827101556366" style="zoom:50%;" />

java.sql

javax.sql

还需要导入一个数据库驱动包 mysql-connector-java.jar。

#### 10.3 第一个 JDBC 程序

1. 创建一个普通项目。
2. 导入数据库驱动。
3. 编写测试代码。

```java
package Learn_MySQL.lesson01;

import java.sql.*;

public class JDBCFirstDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1、加载驱动
        Class.forName("com.mysql.jdbc.Driver");  // 固定写法，加载驱动

        // 2、用户信息和URL
        // MySQL版本高于JDBC时，useSSL=true会报错，改成false即可。
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "123456";

        // 3、连接成功，数据库对象  Connection 代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 4、执行SQL的对象  Statement  执行sql的对象
        Statement statement = connection.createStatement();

        // 5、执行SQL的对象 去 执行SQL，可能存在结果，查看返回结果
        String sql = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);  // 返回的结果集，封装了全部的查询出来的结果

        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("password=" + resultSet.getObject("password"));
        }

        // 6、释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

**步骤总结：**

1. 加载驱动
2. 连接数据库  DriverManager
3. 获取执行sql的对象  Statement
4. 获得返回的结果集
5. 释放连接

> DriverManager

```java
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
Class.forName("com.mysql.jdbc.Driver");  // 固定写法，加载驱动
Connection connection = DriverManager.getConnection(url, username, password);

// connection 代表数据库
// 数据库设置自动提交
// 事务提交
// 事务回滚
connection.rollback();
connection.commit();
connection.setAutoCommit();
```

> URL

```java
String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=false";

// mysql -- 3306
// jdbc:mysql://主机地址:端口号/数据库名?参数1&参数2&参数3

// oracle  -- 1521
// jdbc:oracle:thin:@localhost:1521:sid
```

> Statement  执行SQL的对象   /   PrepareStatement  执行SQL的对象

```java
String sql = "SELECT * FROM users";  // 编写sql

statement.executeQuery();  // 查询操作，返回 ResultSet
statement.execute();  // 执行任何SQL
statement.executeUpdate();  // 更新、插入、删除，都用这个，返回一个受影响的行数
statement.executeBatch();  // 执行多个SQL
```

> ResultSet  查询的结果集，封装了所有的查询结果

获得指定的数据类型

```java
resultSet.getObject();  // 不知道列类型的情况下使用 
resultSet.getString();  // 如果知道列的类型，就使用指定类型
resultSet.getInt();
resultSet.getFloat();
resultSet.getDate();
```

遍历，指针

```java
resultSet.beforeFirst();  // 移动到最前面
resultSet.afterLast();  // 移动到最后面
resultSet.next();	 // 移动到下一个数据
resultSet.previous();  // 移动到前一行
resultSet.absolute(ro);  // 移动到指定行
```

> 释放资源

```java
resultSet.close();
statement.close();
connection.close();  // 耗用资源，用完关掉
```

#### 10.4 Statement 对象

**JDBC中的 Statement 对象用于向数据库发送 SQL 语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。**

Statment 对象的executeUpdate方法，用于向数据库发送增、删、改的SQL语句，executeUpdate执行完后，将会返回一个整数（影响行数）。

Statement.executeQuery方法用于向数据库发送查询语句，返回代表查询结果的 ResultSet 对象。

> CRUD操作-create

使用 executeUpdate(String sql) 方法完成数据添加操作，示例：

```java
Statement st = conn.createStatement();
String sql = "INSERT INTO user (...) VALUES (...)";
int num = st.executeUpdate(sql);
if (num > 0) {
  System.out.println("插入成功！")
}
```

> CRUD操作-delete

使用 executeUpdate(String sql) 方法完成数据删除操作，示例：

```java
Statement st = conn.createStatement();
String sql = "DELETE FROM user WHERE id = 1";
int num = st.executeUpdate(sql);
if (num > 0) {
  System.out.println("删除成功！")
}
```

> CRUD操作-update

使用 executeUpdate(String sql) 方法完成数据修改操作，示例：

```java
Statement st = conn.createStatement();
String sql = "UPDATE user SET name = '' WHERE id = 1";
int num = st.executeUpdate(sql);
if (num > 0) {
  System.out.println("修改成功！")
}
```

> CRUD操作-query

使用 executeQuery(String sql) 方法完成数据查询操作，示例：

```java
Statement st = conn.createStatement();
String sql = "SELECT * FROM user WHERE id = 1";
ResutlSet rs = st.executeQuery(sql);
while (rs.next()) {
  //根据获取列的数据类型，分别调用rs相应的映射方法到Java对象中
}
```

> 代码实现

1. 提取工具类

   ```java
   package Learn_MySQL.lesson02.utils;
   
   import com.mysql.cj.protocol.Resultset;
   import sun.jvm.hotspot.runtime.ResultTypeFinder;
   
   import java.io.IOException;
   import java.io.InputStream;
   import java.sql.*;
   import java.util.Properties;
   
   public class JdbcUtils {
   
       private static String driver = null;
       private static String url = null;
       private static String username = null;
       private static String password = null;
   
   
       static {
   
           try {
               InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
               Properties properties = new Properties();
               properties.load(in);
   
               driver = properties.getProperty("driver");
               url = properties.getProperty("url");
               username = properties.getProperty("username");
               password = properties.getProperty("password");
   
               // 1、驱动只用加载一次
               Class.forName(driver);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   
       // 获取连接
       public static Connection getConnection() throws SQLException {
           return DriverManager.getConnection(url, username, password);
       }
   
       // 释放连接资源
       public static void release(Connection conn, Statement st, ResultSet rs) {
           try {
               if (rs != null)
                   rs.close();
               if (st != null)
                   st.close();
               if (conn != null)
                   conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
   
       }
   }
   ```

   

2. 编写增删改的方法 `executeUpdate`

   ```java
   package Learn_MySQL.lesson02;
   
   import Learn_MySQL.lesson02.utils.JdbcUtils;
   
   import java.sql.Connection;
   import java.sql.ResultSet;
   import java.sql.SQLException;
   import java.sql.Statement;
   
   public class TestInsert {
       public static void main(String[] args) {
   
           Connection conn = null;
           Statement st = null;
           ResultSet rs = null;
           
           try {
               conn = JdbcUtils.getConnection();  // 获取数据库连接
               st = conn.createStatement();  // 获取SQL的执行对象
               String sql = "INSERT INTO users(id, `name`, `password`, `email`, `birthday`)" +
                       "VALUES (4, 'sugar', '123456', '406857586@qq.com', '2020-01-01')";
               int i = st.executeUpdate(sql);
               if (i > 0) {
                   System.out.println("插入成功！");
               }
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               JdbcUtils.release(conn, st, rs);
           }
       }
   }
   ```

#### 10.5 SQL注入的问题

SQL存在漏洞，会被攻击导致数据泄露。**SQL会被恶意拼接**

```java
package Learn_MySQL.lesson02;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// SQL注入问题
public class SqlInjection {
    public static void main(String[] args) {

        login("zhangsan", "123456");
        login(" ' or '1=1", " ' or '1=1");  // 查询出所有用户信息
    }
    
    // 登录业务
    public static void login(String username, String password) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();  // 获取数据库连接
            st = conn.createStatement();  // 获取SQL的执行对象
            // SELECT * FROM users WHERE name='username' AND password='password'
            // SELECT * FROM users WHERE name='' or 1=1' AND password='' or 1=1'
            String sql = "SELECT * FROM users WHERE name='" + username + "' AND password='" + password + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

#### 10.6 PreparedStatement 对象

可以防止SQL注入，并且效率更高！

1. 新增
2. 删除
3. 更新
4. 查询

```java
package Learn_MySQL.lesson03;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.*;

public class TestInsert {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            // 区别
            // 使用 ? 占位符代替参数
            String sql = "INSERT INTO users(id, name, password, email, birthday) " +
                    "VALUES (?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql);  // 预编译sql，先写sql，然后不执行

            // 手动给参数赋值
            st.setInt(1, 4);
            st.setString(2, "sugar");
            st.setString(3, "123123");
            st.setString(4, "406857586@qq.com");
            st.setString(5, "2020-02-02");
            // 注意 sql.Date 和 util.Date 的区别，getTime()获得时间戳

            // 执行
            int i = st.executeUpdate();
            if (i > 0)
                System.out.println("插入成功");
            
            // 如果是查询，则 executeQuery();
            // 返回的依然是 ResultSet

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, null);
        }

    }
}
```

#### 10.7 PreparedStatement 防止SQL注入

```java
package Learn_MySQL.lesson03;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.*;

// 使用PreparedStatment防止SQL注入
public class SqlInjection {
    public static void main(String[] args) {

        login("zhangsan", "123456");
        login(" ' or '1=1", " ' or '1=1");
    }

    // 登录业务
    public static void login(String username, String password) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            // PreparedStatement 防止 SQL 注入的本质，把传递进来的参数当做字符
            // 假设其中存在转义字符没救直接忽略， 比如 ' 会被直接转义
            String sql = "SELECT * FROM users WHERE name= ? AND password= ?";  // Mybatis
            st = conn.prepareStatement(sql);

            st.setString(1, "sugar");
            st.setString(2, "123123");

            rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

#### 10.8 使用IDEA连接数据库

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827130422442.png" alt="image-20200827130422442" style="zoom:50%;" />

连接成功后，选择数据库。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827130511525.png" alt="image-20200827130511525" style="zoom:50%;" />

双击数据库，查看内容。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827130625875.png" alt="image-20200827130625875" style="zoom:50%;" />

更新数据，必须点这个按钮才会更新。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827130737958.png" alt="image-20200827130737958" style="zoom:50%;" />

写SQL的地方。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827131030441.png" alt="image-20200827131030441" style="zoom:50%;" />

**坑点：**

连接失败，查看原因

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20200827131238306.png" alt="image-20200827131238306" style="zoom:50%;" />

#### 10.9 JDBC事务

**要么都成功，要么都失败**

> ACID原则

原子性：要么都完成，要么都不完成

一致性：总数不变

**隔离性：多个进程互不干扰**

持久性：一旦提交不可逆



**隔离性的问题：**

脏读：一个事务读取了另一个没有提交的事务

不可重复读：在同一个事务内，重复读取表中的数据，表数据发生了改变

虚读（幻读）：在一个事务内，读取到了别人插入的数据，导致前后读出来结果不一致



**代码实现**

1. 开启事务  `conn.setAutoCommit(false);`
2. 一组业务执行完毕，提交事务
3. 可以在 catch 语句中显式定义回滚语句，默认失败就会回滚。

```java
package Learn_MySQL.lesson04;

import Learn_MySQL.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction1 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            // 关闭数据库的自动提交，自动会开启事务
            conn.setAutoCommit(false);  // 关闭同时自动开启事务

            String sql = "UPDATE account SET money = money - 100 WHERE name = 'A'";
            st = conn.prepareStatement(sql);
            st.executeUpdate();

            String sql2 = "UPDATE account SET money = money + 100 WHERE name = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            // 业务完毕，提交事务
            conn.commit();
            System.out.println("事务提交成功");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();  // 如果提交失败，则回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

    }
}
```

#### 10.10 数据库连接池

存数据连接  -- 执行完毕  -- 释放  

连接  --  释放   **十分浪费系统资源**

**池化技术**：准备一些预先的资源，过来就连接预先准备好的

最小连接数，根据常用连接数设定。

最大连接数，为业务最高承载上限。

排队等待。

等待超时。



**编写连接池，实现一个接口 DataSource。**

> 开源数据源实现（拿来即用）

DBCP

C3P0

Druid：阿里巴巴

使用这些数据库连接池之后，在项目开发中就不需要编写连接数据库代码了。

> DBCP

需要用到的 jar 包

commons-dbcp-1.4、commons-pool-1.6

```java
package Learn_MySQL.lesson05.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class dbcpUtils {

    private static DataSource dataSource = null;

    static {

        try {
            InputStream in = dbcpUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
            Properties properties = new Properties();
            properties.load(in);

            // 创建数据源  工厂模式 --> 创建
            dataSource = BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 释放连接资源
    public static void release(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

> C3P0

需要用到的 jar 包

c3p0-0.9.5.5、mchange-commons-java-0.2.19

```java
package Learn_MySQL.lesson05.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class C3p0Utils {

    private static ComboPooledDataSource dataSource = null;

    static {

        try {
            // 创建数据源  工厂模式 --> 创建
            // 配置文件写法
            dataSource = new ComboPooledDataSource("MySQL");

            // 代码版配置写法
//            dataSource = new ComboPooledDataSource();
//            dataSource.setDriverClass();
//            dataSource.setUser();
//            dataSource.setPassword();
//            dataSource.setJdbcUrl();
//            dataSource.setMaxPoolSize();
//            dataSource.setMinPoolSize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 释放连接资源
    public static void release(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

>结论

无论使用什么数据源，本质都是一样的。 DataSource 接口不会变，方法就不会变。

