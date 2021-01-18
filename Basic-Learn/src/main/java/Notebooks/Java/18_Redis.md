### 1 NoSQL概述

#### Why NoSQL？

大数据时代，一般的数据库无法进行分析处理了！

> 1、单机MySQL时代

网站访问量不大，单个数据库完全足够！更多的使用静态网页，服务器没有压力！

整个网站的瓶颈？

1. 数据量如果太大，一个机器放不下。
2. 数据的索引（B+Tree），一个机器内存放不下。
3. 访问量（读写混合），一个服务器承受不了。

> 2、Memcached（缓存） + MySQL + 垂直拆分（读写分离）

网站80%的情况都是在读，每次都去查询数据库的话就十分麻烦！ -> 减轻数据压力，使用缓存保证效率！

发展过程：优化数据结构和索引 -> 文件缓存（IO） -> Memcached（当时最热门的技术）

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210112000158075.png" alt="image-20210112000158075" style="zoom:30%;" />

> 3、分库分表 + 水平拆分 + MySQL集群

本质：数据库（读，写）

MyISAM：表锁，影响效率！高并发下出现严重的锁问题。

Innodb：行锁

慢慢的开始使用分库分表来解决写的压力。

MySQL集群在当时基本解决了所有需求。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210112000849754.png" alt="image-20210112000849754" style="zoom:30%;" />

> 4、如今

定位、音乐、热榜！MySQL等关系型数据库不够用了！数据量很多，变化很快！

MySQL 有的使用它来存储一些比较大的文件、博客、图片！数据库表很大，效率就很低！如果有一种数据库来专门处理这种数据，MySQL的压力就变得十分小。

===> 研究如果处理这些问题！

> 目前一个基本的互联网项目！

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210112144838726.png" alt="image-20210112144838726" style="zoom:40%;" />

> 为什么要用NoSQL？

用户的个人信息、社交网络、地理位置，用户自己产生的数据，用户日志等等爆发式增长！

需要NoSQL数据库，可以很好处理以上情况！

#### What is NoSQL？

> NoSQL

NoSQL = Not Only SQL

泛指非关系型数据库。传统的关系型数据库无法应对web2.0时代，尤其是超大规模的高并发的社区。NoSQL在大数据时代发展迅速，Redis是发展对快的。

很多数据类型，用户的个人信息、社交网络、地理位置。这些数据类型的存储不需要一个固定的格式，不需要多余的操作就可以横向扩展！

> NoSQL特点

1. 方便扩展（数据之间没有关系，容易扩展）

2. 大数据量高性能（Redis一秒读写8万次，NoSQL的缓存记录级，细粒度缓存，性能高！）

3. 数据类型多样（不需要事先设计数据库）

4. 传统 RDMS 和 NoSQL 的区别

   **传统的 RDMS**

   - 结构化组织
   - SQL
   - 数据和关系都存在单独的表中
   - 数据操作、定义的语言9
   - 严格的一致性
   - 基础的事务
   - ...

   **NoSQL**

   - 不仅仅是数据
   - 没有固定的查询语言
   - 键值对存储，列存储，文档存储，图形数据库（社交关系）
   - 最终一致性
   - CAP定理 和 BASE理论 （异地多活）
   - 高性能、高可用、高可扩展
   - ...

> 3V + 3高

大数据时代的3V：主要是描述问题

1. 海量 Volume
2. 多样 Variety
3. 实时 Velocity

大数据时代的3高：主要是对程序的要求

1. 高并发
2. 高可拓（随时水平拆分）
3. 高性能

#### 阿里巴巴演进分析

```bash
# 1、商品的基本信息
		名称、价格、商家信息
		关系型数据库足以！ MySQL / Oracle（淘宝早年就去IOE了）
		
# 2、商品的描述、评论（文字比较多）
		文档型数据库中：MongoDB

# 3、图片
		分布式文件系统：FastFDS
		- 淘宝的 TFS
		- Google 的 GFS
		- Hadoop 的 HDFS
		- 阿里云的 OSS

# 4、商品的关键字（搜索）
		- 搜索引擎  solr  elasticsearch
		- 淘宝 的 ISearch
		
# 5、商品热门的波动信息
		- 内存数据库
		- Redis、Tair、Memcache
		
# 6、商品的交易、外部的支付宝
		- 第三方应用
```

大型互联网应用问题：

- 数据类型太多
- 数据源繁多，经常重构
- 数据要改造，大面积改造

解决问题：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210112152235287.png" alt="image-20210112152235287" style="zoom:40%;" />

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210112152350399.png" alt="image-20210112152350399" style="zoom:40%;" />

#### NoSQL的四大分类

**KV键值对：**

- 新浪：Redis
- 美团：Redis + Tair
- 阿里、百度：Redis + memcache

**文档型数据库（BSON格式，和JSON一样）**

- MongoDB
  - 基于分布式文件存储的数据库，主要用来处理大量的文档！
  - MongoDB是介于关系型数据库和非关系型数据库中间的产品！
- ConthDB

**列存储数据库**

- HBase
- 分布式文件系统

**图关系数据库**

- Neo4j，InfoGrid

#### NoSQL四大分类对比

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210112153751487.png" alt="image-20210112153751487" style="zoom:40%;" />

### 2 Redis入门

#### 概述

> Redis是什么？

Redis（Remote Dictionary Server），即远程字段服务。

Redis会周期性的把更新的数据写入磁盘或者把修改写

开源的、支持网络、可基于内存也可持久化的日志型、Key-Value数据库，提供多种语言的API。

> Redis能做什么？

1. 内存存储、持久化，内存中是断电即失，所以持久化很重要（RDB，AOF）
2. 效率高，可以用于高速缓存
3. 发布订阅系统
4. 地图信息分析
5. 计时器、计数器（浏览量）
6. ...

> Redis的特性

1. 多样的数据类型
2. 持久化
3. 集群
4. 事务
5. ...

> 学习网站

1. 官网：https://redis.io/
2. 中文网：http://www.redis.cn/
3. 官网下载

#### Windows安装

1. 下载地址：https://github.com/dmajkic/redis/releases
2. 下载完毕得到压缩包
3. 解压到环境目录下即可
4. 开启Redis，双击运行 `redis-server.exe`
5. 使用Redis客户端 `redis-cli.exe` 来连接Redis
6. 输入 `ping` 显示 `PONG`，则连接成功

#### Linux安装

1. 官网下载 `redis-6.0.10.tar.gz`

2. 解压Redis安装包。

3. 进入目录，可以看到Redis的配置文件
    `redis.conf`

4. 基本环境安装

   ```bash
   yum install gcc-c++
   make
   make install # 安装确认
   ```

5. 默认安装路径 `/usr/local/bin`

6. 将Redis配置文件复制到安装目录下

7. Redis默认不是后台启动的，修改配置文件

   ```bash
   daemonize yes
   ```

8. 启动Redis服务！

   ```bash
   redis-server redis.conf  # 通过指定的配置文件启动Redis服务
   ```

9. 使用Redis客户端连接测试

   ```bash
   redis-cli -p 6379
   set name sugar
   get name
   keys *
   ```

10. 查看Redis进程是否开启

    ```bash
    ps -ef|grep redis
    ```

11. 关闭Redis服务

    ```bash
    客户端连接后
    shutdown
    ```

**注**：出现（error）NOAUTH Authentication required.
认证问题，因为设置了认证密码。 `auth "password"`

#### 性能测试

**redis-benchmark**是一个压力测试工具。

```bash
# 测试：100个并发连接，100000个请求
redis-benchmark -h localhost -p 6379 -c 100 -n 100000
```

#### 基础知识

Redis默认有16个数据库，默认使用第 0 个数据库，可以使用 `select` 进行切换。

```bash
127.0.0.1:6379> select 3		# 切换到第3个数据库
OK
127.0.0.1:6379[3]> dbsize		# 查看DB大小
(integer) 0
127.0.0.1:6379> keys *			# 查看数据库所有的key
1) "name"
2) "proxies"
127.0.0.1:6379> flushdb			# 清空数据库
OK
127.0.0.1:6379> flushdb			# 清空全部数据库
OK
```

>  Redis是单线程的！（最新版本6.0以上为多线程）

Redis是基于内存操作的，CPU不是Redis的性能瓶颈，而是根据机器的内容和网络带宽。

Redis是C语言写的，达到100000+的QPS，不比Memcache差

**Redis为什么单线程还这么快？**

1. 误区1：高性能的服务器一定是多喜爱昵称的？
2. 误区2：多线程（CPU）一定比单线程效率高？

**核心：Redis是将所有的数据全部放在内存中的，所以使用单线程操作效率就是最高的，多线程（CPU上下文切换，耗时操作！），对于内存系统，没有上下文切换效率就是最高的！多次读写都是在一个CPU上的，在内存情况下，这个就是最佳的方案。**



### 3 五大数据类型

**常用API在Redis官方文档中都有！**

Redis 是一个开源的，内存中的数据结构存储系统，可以用作数据库、缓存和消息中间件。它支持多种类型的数据结构，如字符串、散列、列表、集合、有序集合和范围查询，bitmaps、hyperloglogs和geopatial索引半径查询。Redis内置了 replication、Lua scripting、LRU eviction、transactions 和不同级别的 persistence，并通过 Redis哨兵和自动分区提供高可用性。

#### Redis-Key

```bash
127.0.0.1:6379> set name sugar  # 设置Key-Value
OK
127.0.0.1:6379> keys *					# 查看所有Key
1) "name"
127.0.0.1:6379> get name
"sugar"
127.0.0.1:6379> EXISTS name			# 查看某个Key是否存在
(integer) 1
127.0.0.1:6379> move name 1			# 移动某个Key到另一个数据库
(integer) 1
127.0.0.1:6379> del name				# 删除某个Key
OK
127.0.0.1:6379> keys *
(empty array)
127.0.0.1:6379> set name sugar
OK
127.0.0.1:6379> EXPIRE name 10	# 为某个Key设置过期时间
(integer) 1
127.0.0.1:6379> ttl name				# 查看某个Key剩余时间
(integer) 8
127.0.0.1:6379> type name				# 查看数据类型
string
```

### String（字符串）

String类似的使用场景：value除了是字符串，还可以是数字

- 计数器
- 统计数
- 粉丝数
- 对象缓存

```bash
127.0.0.1:6379> set key1 v1
OK
127.0.0.1:6379> get key1
"v1"
127.0.0.1:6379> append key1 "hello"  # 追加字符串，如果key不存在，则相当于set
(integer) 7
127.0.0.1:6379> get key1
"v1hello"
127.0.0.1:6379> strlen key1  # 获取字符串的长度
(integer) 7
127.0.0.1:6379> append key1 ",sugar"  
(integer) 13
127.0.0.1:6379> get key1
"v1hello,sugar"
#############################################################################  
# 自增自减，步长
127.0.0.1:6379> set views 0  # 初始浏览量为0
OK
127.0.0.1:6379> incr views  # 自增
(integer) 1
127.0.0.1:6379> incr views  # 自减
(integer) 2
127.0.0.1:6379> get views
"2"
127.0.0.1:6379> decr views
(integer) 1
127.0.0.1:6379> incrby views 10  # 自增，步长10
(integer) 11
127.0.0.1:6379> decrby views 5  # 自减，步长5
(integer) 6
#############################################################################  
# 字符串范围 getrange
127.0.0.1:6379> set key1 "hello,sugar"
OK
127.0.0.1:6379> get key1
"hello,sugar"
127.0.0.1:6379> getrange key1 0 3  # 字符串截取
"hell"
127.0.0.1:6379> getrange key1 0 -1
"hello,sugar"
# 替换
127.0.0.1:6379> set key2 abcdefg
OK
127.0.0.1:6379> get key2
"abcdefg"
127.0.0.1:6379> setrange key2 1 xx  # 替换指定位置开始的字符串
(integer) 7
127.0.0.1:6379> get key2
"axxdefg"
#############################################################################  
# setex (set with expire)  # 设置过期时间
# setnx (set if not exist)  # 不存在时设置（在分布式锁中常用）
127.0.0.1:6379> setex key3 30 "hello"  # 设置key3的值为hello，30秒后过期
OK
127.0.0.1:6379> ttl key3
(integer) 28
127.0.0.1:6379> setnx mykey "redis"  # mykey不存在则创建mykey
(integer) 1
127.0.0.1:6379> keys *
1) "key1"
2) "mykey"
3) "key2"
127.0.0.1:6379> setnx mykey "MongoDB"  # 存在则创建失败
(integer) 0
127.0.0.1:6379> get mykey
"redis"
#############################################################################  
# mset  # 批量设置
# msetnx  # 不存在时批量设置
# mget  # 批量获取
127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3  # 批量设置
OK
127.0.0.1:6379> keys *
1) "k2"
2) "k3"
3) "k1"
127.0.0.1:6379> mget k1 k2 k3  # 批量后去
1) "v1"
2) "v2"
3) "v3"
127.0.0.1:6379> msetnx k1 v1 k4 v4  # msetnx院子操作，k1存在，则k4创建失败
(integer) 0
127.0.0.1:6379> get k4
(nil)
# 对象
set user:1 {name:sugar, age:3}  # 设置一个user:1 对象，值为JSON字符串保存
# 这里的key是巧妙设置：  user:{id}:{field}
127.0.0.1:6379> mset user:1:name sugar user:1:age 2
OK
127.0.0.1:6379> mget user:1:name user:1:age
1) "sugar"
2) "2"
#############################################################################  
# getset  # 先get再set
127.0.0.1:6379> getset db redis  # 如果不存在值，则返回nil
(nil)
127.0.0.1:6379> get db
"redis"
127.0.0.1:6379> getset db mongodb  # 如果存在值，获取原来的值，并设置新的值
"redis"
127.0.0.1:6379> get db
"mongodb"
```

### List

基本的数据类型，列表。

在Redis中，可以将List实现为栈、队列、阻塞队列。

所有的List命令都是 `l` 开头的。

**小结**

- 列表实际上是一个链表，node的left和right都可以插入值
- 如果 key 不存在，创建新的链表
- 如果 key 存在，新增内容
- 如果移除了所有值（空链表），也代表不存在
- 在两边插入或者改动值，效率最高！中间元素，相对来说效率会低一点！

消息排队！消息队列（Lpush  Rpop）、栈（Lpush  Lpop）

```bash
#############################################################################  
# lpush 左添加
# rpush 右添加
127.0.0.1:6379> lpush list one  # 将一个或多个值插入列表的头部
(integer) 1
127.0.0.1:6379> lpush list two
(integer) 2
127.0.0.1:6379> lpush list three
(integer) 3
127.0.0.1:6379> lrange list 0 -1  # 获取List中所有值
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> lrange list 0 1  # 获取List中具体的值
1) "three"
2) "two"
127.0.0.1:6379> rpush list right  # 将一个或多个值插入列表的尾部
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
4) "right"
#############################################################################  
# lpop 左移除
# rpop 右移除
127.0.0.1:6379> lrange list 0 -1  
1) "three"
2) "two"
3) "one"
4) "right"
127.0.0.1:6379> lpop list  # 移除列表头部元素
"three"
127.0.0.1:6379> rpop list  # 移除列表尾部元素 
"right"
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "one"
#############################################################################  
# lindex  # 通过下标获取值
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "one"
127.0.0.1:6379> lindex list 0  # 通过下标获取List中的某一个值
"two"
127.0.0.1:6379> lindex list 1
"one"
#############################################################################  
# llen  # 获取长度
127.0.0.1:6379> lpush list one
(integer) 1
127.0.0.1:6379> lpush list two
(integer) 2
127.0.0.1:6379> lpush list three
(integer) 3
127.0.0.1:6379> llen list  # 获取列表的长度
(integer) 3
#############################################################################  
# lrem  # 移除指定的值，精确匹配
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "three"
3) "two"
4) "one"
127.0.0.1:6379> lrem list 1 one  # 移除List中指定个数的value，精确匹配
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "three"
3) "two"
127.0.0.1:6379> lrem list 1 three
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
127.0.0.1:6379> lpush list three
(integer) 3
127.0.0.1:6379> lrem list 2 three
(integer) 2
127.0.0.1:6379> lrange list 0 -1
1) "two"
#############################################################################  
# trim # 截取List中的元素
127.0.0.1:6379> rpush list "hello"
(integer) 1
127.0.0.1:6379> rpush list "hello1"
(integer) 2
127.0.0.1:6379> rpush list "hello2"
(integer) 3
127.0.0.1:6379> rpush list "hello3"
(integer) 4
127.0.0.1:6379> lrange list 0 -1  
1) "hello"
2) "hello1"
3) "hello2"
4) "hello3"
127.0.0.1:6379> ltrim list 1 2  # 通过下标，截取指定长度的元素
OK
127.0.0.1:6379> lrange list 0 -1
1) "hello1"
2) "hello2"
#############################################################################  
# rpoplpush  # 移除列表的最后一个元素，到某个列表（可以是当前列表）
127.0.0.1:6379> rpush list "hello"
(integer) 1
127.0.0.1:6379> rpush list "hello1"
(integer) 2
127.0.0.1:6379> rpush list "hello2"
(integer) 3
127.0.0.1:6379> lrange list 0 -1
1) "hello"
2) "hello1"
3) "hello2"
127.0.0.1:6379> rpoplpush list otherlist  # 右移除列表元素，左添加到另一列表中
"hello2"
127.0.0.1:6379> lrange list 0 -1  # 原列表元素已被移除
1) "hello"
2) "hello1"
127.0.0.1:6379> lrange otherlist 0 -1  # 不存在的列表则会创建
1) "hello2"
#############################################################################
# lset  # 将列表中指定下标的值替换为另一个值
127.0.0.1:6379> exists list  # 判断列表是否存在
(integer) 0
127.0.0.1:6379> lset list 0 item  # 如果不存在列表，更新会报错
(error) ERR no such key
127.0.0.1:6379> lpush list value1
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "value1"
127.0.0.1:6379> lset list 0 item  # 如果存在，更新当前下标的值
OK
127.0.0.1:6379> lrange list 0 -1
1) "item"
127.0.0.1:6379> lset list 1 other  # 如果不存在，则会报错
(error) ERR index out of range
#############################################################################
# linsert  # 将某个具体的value插入到列表中某个元素的前面或者后面
127.0.0.1:6379> rpush list "hello"
(integer) 1
127.0.0.1:6379> rpush list "world"
(integer) 2
127.0.0.1:6379> linsert list before "world" "other"  # 在某个元素前面插入值
(integer) 3
127.0.0.1:6379> lrange list 0 -1
1) "hello"
2) "other"
3) "world"
127.0.0.1:6379> linsert list after "world" new  # 在某个元素后面插入值
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "hello"
2) "other"
3) "world"
4) "new"

```

#### Set（集合）

Set是**无序不重复**集合。

```bash
#############################################################################
127.0.0.1:6379> sadd myset "hello"  # 添加元素
(integer) 1
127.0.0.1:6379> sadd myset "sugar"  
(integer) 1
127.0.0.1:6379> sadd myset "heihei"
(integer) 1
127.0.0.1:6379> smembers myset  # 查看指定set的所有元素
1) "sugar"
2) "hello"
3) "heihei"
127.0.0.1:6379> sismember myset hello  # 判断某个元素是否在set中
(integer) 1
127.0.0.1:6379> sismember myset world
(integer) 0
#############################################################################
# scard
127.0.0.1:6379> scard myset  # 获取set集合中元素的个数
(integer) 3
#############################################################################
# srem
127.0.0.1:6379> srem myset hello  # 删除set中的指定个元素
(integer) 1
127.0.0.1:6379> scard myset
(integer) 2
127.0.0.1:6379> smembers myset
1) "sugar"
2) "heihei"
#############################################################################
# 随机抽取元素
127.0.0.1:6379> srandmember myset  # 随机抽取一个元素
"heihei"
127.0.0.1:6379> srandmember myset 2  # 随机抽取指定个数元素
1) "sugar"
2) "heihei"
#############################################################################
# 随机删除key
127.0.0.1:6379> smembers myset
1) "sugar"
2) "hello"
3) "heihei"
127.0.0.1:6379> spop myset  # 随机删除一个元素
"sugar"
127.0.0.1:6379> spop myset
"heihei"
127.0.0.1:6379> smembers myset
1) "hello"
#############################################################################
# 将一个指定的key移动到另外一个set中
127.0.0.1:6379> smove myset myset2 sugar  # 移动到另外一个set中
(integer) 1
127.0.0.1:6379> smembers myset
1) "hello"
2) "world"
127.0.0.1:6379> smembers myset2
1) "sugar"
2) "set2"
#############################################################################
# 微博中共同关注、二度好友等功能的实现（并集）
# 数字集合类：交集、并集、差集
127.0.0.1:6379> sadd key1 a b c
(integer) 3
127.0.0.1:6379> sadd key2 c d e
(integer) 3
127.0.0.1:6379> sdiff key1 key2  # key1与key2不同的元素
1) "a"
2) "b"
127.0.0.1:6379> sinter key1 key2  # 交集
1) "c"
127.0.0.1:6379> sunion key1 key2  # 并集
1) "c"
2) "e"
3) "a"
4) "b"
5) "d"
```

#### Hash（哈希）

Map集合，key-Map集合！本质和String类型没有太大区别，还是简单的 key-value。

Hash可以存user对象的name、age，用户信息之类的，尤其是经常变动的信息。

```bash
#############################################################################
127.0.0.1:6379> hset myhash field1 sugar  # 添加一个key-value
(integer) 0
127.0.0.1:6379> hget myhash field1  # 获取一个字段值
"sugar"
127.0.0.1:6379> hmset myhash field1 hello field2 world  # 添加多个key-value
OK
127.0.0.1:6379> hmget myhash field1 field2  # 获取多个字段值
1) "hello"
2) "world"
127.0.0.1:6379> hgetall myhash  # 获取全部数据，以键值形式展示
1) "field1"
2) "hello"
3) "field2"
4) "world"
127.0.0.1:6379> hdel myhash field1  # 删除指定字段
(integer) 1
127.0.0.1:6379> hgetall myhash
1) "field2"
2) "world"
#############################################################################
# hlen  # 获取长度
127.0.0.1:6379> hlen myhash  # 获取哈希长度
(integer) 1
#############################################################################
# hexists 
127.0.0.1:6379> hexists myhash field1  # 判断指定字段是否存在
(integer) 0
127.0.0.1:6379> hexists myhash field2
(integer) 1
#############################################################################
# 只获取所有field
# 只获取所有vale
127.0.0.1:6379> hkeys myhash  # 获取所有字段
1) "field2"
127.0.0.1:6379> hvals myhash  # 获取所有值
1) "world"
#############################################################################
# incr  decr  setnx
127.0.0.1:6379> hset myhash field3 5
(integer) 1
127.0.0.1:6379> hincrby myhash field3 1  # 自增
(integer) 6
127.0.0.1:6379> hincrby myhash field3 -1  # 自减
(integer) 5
127.0.0.1:6379> hsetnx myhash field4 hello  # 如果不存在则可以set
(integer) 1
127.0.0.1:6379> hsetnx myhash field4 hello
(integer) 0
```

#### Zset（有序集合）

在Set的基础上，增加了一个值，set k1 v1，zset k1 score1 v1

案例思路：set 排序，存储班级成绩，工资表排序，带权重判断，排行榜应用实现，取TOP N...

```bash
#############################################################################
127.0.0.1:6379> zadd myset 1 one  # 添加一个值
(integer) 1
127.0.0.1:6379> zadd myset 2 two 3 three  # 添加多个值
(integer) 2
127.0.0.1:6379> zrange myset 0 -1  # 
1) "one"
2) "two"
3) "three"
#############################################################################
# 实现排序
127.0.0.1:6379> zadd salary 2500 xiaoming  # 添加三个用户
(integer) 1
127.0.0.1:6379> zadd salary 5000 zhangsan
(integer) 1
127.0.0.1:6379> zadd salary 500 wangwu
(integer) 1
127.0.0.1:6379> zrangebyscore salary -inf +inf  # 显示全部的用户，从小到大排序
1) "wangwu"
2) "xiaoming"
3) "zhangsan"
127.0.0.1:6379> zrevrange salary 0 -1  # 从大到小排序
1) "zhangsan"
2) "wangwu"
127.0.0.1:6379> zrangebyscore salary -inf +inf withscores  # 带成绩返回
1) "wangwu"
2) "500"
3) "xiaoming"
4) "2500"
5) "zhangsan"
6) "5000"
127.0.0.1:6379> zrangebyscore salary -inf 2500 withscores  # 限定scores范围
1) "wangwu"
2) "500"
3) "xiaoming"
4) "2500"
#############################################################################
# zrem
127.0.0.1:6379> zrange salary 0 -1
1) "wangwu"
2) "xiaoming"
3) "zhangsan"
127.0.0.1:6379> zrem salary xiaoming  # 移除指定元素
(integer) 1
127.0.0.1:6379> zrange salary 0 -1
1) "wangwu"
2) "zhangsan"
#############################################################################
# zcard
127.0.0.1:6379> zcard salary  # 获取元素个数
(integer) 2
#############################################################################
# 区间计算
127.0.0.1:6379> zadd myset 1 hello 2 world 3 sugar
(integer) 3
127.0.0.1:6379> zcount myset 1 3  # 获取指定区间的元素数量
(integer) 3
```

### 4 三种特殊数据类型

#### geospatial 地理位置

案例：朋友定位、附近的人、打车距离计算

Reids 的 GEO，可以推荐地理位置的信息、两地之间的距离、方圆几公里的人。

可以查询一些测试数据：http://www.jsons.cn/lngcodeinfo/0706D99C19A781A3/

只有六个命令：

- GEOADD
- GEODIST
- GEOHASH
- GEOPOS
- GEORADIUS
- GEORADIUSBYMEMBER

> geoadd

```bash
# geoadd 添加地理位置
# 规则：地球两极无法直接添加，一般会下载城市数据通过程序直接导入
# 参数 key 值（纬度、经度、名称）
# 有效的精度从-180度到180度，有效的纬度从-85度到85度。
127.0.0.1:6379> geoadd china:city 116.40 39.90 beijing
(integer) 1
127.0.0.1:6379> geoadd china:city 121.47 31.23 shanghai
(integer) 1
127.0.0.1:6379> geoadd china:city 106.50 29.53 chognqing
(integer) 1
127.0.0.1:6379> geoadd china:city 114.05 22.52 shenzhen 120.16 30.24 hangzhou 108.96 34.26 xian
(integer) 3
```

> geopos

```bash
127.0.0.1:6379> geopos china:city beijing  # 获取指定城市的精度和纬度
1) 1) "116.39999896287918091"
   2) "39.90000009167092543"
```

> geodist

应用：计算两人之间的直线距离

单位：

- **m** 米
- **km** 千米
- **mi** 英里
- **ft** 英尺

```bash
127.0.0.1:6379> geodist china:city beijing shanghai  # 默认单位米
"1067378.7564"
127.0.0.1:6379> geodist china:city beijing shanghai km  # 设定单位千米
"1067.3788"
```

> georadius  以给定的经纬度为中心，找出某一半径内的元素

应用：附近的人？获得所有附近的人的地址定位，通过半径来查询。

```bash
127.0.0.1:6379> georadius china:city 110 30 1000 km  # 获取 110 30 这个经纬度为中心，方圆1000km内的城市
1) "chognqing"
2) "xian"
3) "shenzhen"
4) "hangzhou"
127.0.0.1:6379> georadius china:city 110 30 500 km  # 方圆500km内的城市
1) "chognqing"
2) "xian"
127.0.0.1:6379> georadius china:city 110 30 500 km withdist  # 显示到中心的直线距离
1) 1) "chognqing"
   2) "341.9374"
2) 1) "xian"
   2) "483.8340"
127.0.0.1:6379> georadius china:city 110 30 500 km withcoord  # 显示他人的定位信息
1) 1) "chognqing"
   2) 1) "106.49999767541885376"
      2) "29.52999957900659211"
2) 1) "xian"
   2) 1) "108.96000176668167114"
      2) "34.25999964418929977"
127.0.0.1:6379> georadius china:city 110 30 500 km withdist withcoord count 1  # 显示指定数量的
1) 1) "chognqing"
   2) "341.9374"
   3) 1) "106.49999767541885376"
      2) "29.52999957900659211"
```

> georadiusbymember 以某个元素为中心做计算

```bash
# 找出位于指定元素周围的其他元素
127.0.0.1:6379> georadiusbymember china:city beijing 1000 km
1) "beijing"
2) "xian"
127.0.0.1:6379> georadiusbymember china:city shanghai 400 km
1) "hangzhou"
2) "shanghai"
```

> geohash 返回一个或多个位置元素的geohash表示

该命名将返回11个字符的Geohash字符串。

```bash
# 将二维的经纬度转换为一维的字符串，如果两个字符串越接近，则距离越近
127.0.0.1:6379> geohash china:city beijing chognqing  
1) "wx4fbxxfke0"
2) "wm5xzrybty0"
```

> 底层实现原理：Zset！可以使用Zset命令来操作geo

```bash
# zrem 移除元素
127.0.0.1:6379> zrange china:city 0 -1
1) "chognqing"
2) "xian"
3) "shenzhen"
4) "hangzhou"
5) "shanghai"
6) "beijing"
127.0.0.1:6379> zrem china:city beijing  # 移除指定元素
(integer) 1
127.0.0.1:6379> zrange china:city 0 -1
1) "chognqing"
2) "xian"
3) "shenzhen"
4) "hangzhou"
5) "shanghai"
```

#### hyperloglog

> 基数

A {1, 3, 5, 7, 8, 9, 7}  

B{1, 3, 5, 7, 8}

基数（不重复的元素）：5，可以接收误差！

> hyperloglog简介

Hyperloglog数据结构：做基数统计的算法！

优点：占用的内存是固定的。2^64 不同的元素的技术，只需要占用 12KB 内存。如果要从内存角度比较，Hyterlolog是首选！

**网页的 UV（一个人多次访问一个网站，但还是算作一个人！）**

传统的方式，set保存用户的id，然后就可以统计 set 中元素的数量作为标准判断！

如果保存大量的用户id，就会占用大量内存！目的是为了计数，而不是保存用户id。

0.81%的错误率！在UV统计任务中，可以忽略不计！

如果不允许容错，就使用 set 或自己的数据类型即可！

```bash
127.0.0.1:6379> pfadd mykey a b c d e f g h i j  # 创建第一组元素
(integer) 1
127.0.0.1:6379> pfcount mykey  # 统计 mykey 元素的基数数量
(integer) 10
127.0.0.1:6379> pfadd mykey2 i j z x c v b n m  
(integer) 1
127.0.0.1:6379> pfcount mykey2
(integer) 9
127.0.0.1:6379> pfmerge mykey3 mykey mykey2  # 合并两组 mykey + mykey2 => mykey3 并集
OK
127.0.0.1:6379> pfcount mykey3  # 查看并集的数量
(integer) 15
```

#### bitmaps

> 位存储

统计用户信息，活跃，不活跃！登录，未登录！打卡，未打卡！两个状态的，都可以使用 Bitmaps（位图）！

都是操作二进制位来进行记录，就只有 0 和 1 两个状态！

365天 = 365bit，1 字节 = 8 bit，46个字节左右！

```bash
# 使用 bitmap 来记录周一到周日的打卡
# 构造一周的打卡记录
127.0.0.1:6379> setbit sign 0 1
(integer) 0
127.0.0.1:6379> setbit sign 1 0
(integer) 0
127.0.0.1:6379> setbit sign 2 0
(integer) 0
127.0.0.1:6379> setbit sign 3 1
(integer) 0
127.0.0.1:6379> setbit sign 4 1
(integer) 0
127.0.0.1:6379> setbit sign 5 0
(integer) 0
127.0.0.1:6379> setbit sign 6 0
(integer) 0
# 查看某一天是否有打卡
127.0.0.1:6379> getbit sign 3
(integer) 1
127.0.0.1:6379> getbit sign 6
(integer) 0
# 统计打卡的天数
127.0.0.1:6379> bitcount sign
(integer) 3
```

### 5 事务

要么同时成功，要么同时失败，**原子性**！

Redis事务的本质：一组命令的集合！一个事务中的所有命令都会被序列化，在事务执行过程中，会按照顺序执行！

一次性，顺序性，排他性！执行一些列的命令！

```bash
---------  队列 set set set 执行 ---------
```

Redis事务没有隔离级别的概念！

所有命令在事务中，并没有直接被执行！只有发起执行命令的时候，才会执行！ `exec命令`

Redis单条命令是保存原子性的，但是事务不保证原子性！

Redis事务流程：

- 开启事务（`multi`）
- 命令入队（...）
- 执行事务（`exec`）

> 正常执行事务

```bash
127.0.0.1:6379> multi  # 开启事务
OK
127.0.0.1:6379> set k1 v1  # 命令入队
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> exec  # 执行事务
1) OK
2) OK
3) "v2"
4) OK
```

> 放弃事务

```bash
127.0.0.1:6379> multi  # 开启事务
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> discard  # 取消事务
OK
127.0.0.1:6379> get k4  # 事务队列中的命令都不会执行
(nil)
```

> 编译型异常（代码有问题，命令有错！）事务中的所有命令都不会执行！

```bash
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> getset k3  # 错误的命令
(error) ERR wrong number of arguments for 'getset' command
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> exec  # 执行事务报错
(error) EXECABORT Transaction discarded because of previous errors.
127.0.0.1:6379> get k4  # 所有命令都不会执行
(nil)
```

> 运行时异常，如果事务队列中存在语法性问题，那么执行命令的时候，其他命令可以正常执行的，错误命令抛出异常！

```bash
127.0.0.1:6379> set k1 v1
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> incr k1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> get k3
QUEUED
127.0.0.1:6379> exec  # 虽然第一条命令报错了，但是其他命令依然执行成功，不保证整个事务的原子性
1) (error) ERR value is not an integer or out of range
2) OK
3) OK
4) "v3"
127.0.0.1:6379> get k1
"v1"
```

> 监控！ Watch

**悲观锁：**

- 认为什么时候都会出问题，无论做什么都会加锁！

**乐观锁：**

- 认为什么时候都不会出问题，所以不会上锁！更新数据的时候去判断一下，在此期间是否有人修改过这个数据。`通过versioin字段`
- 获取version
- 更新的时候比较version

> Redis的监视测试

正常执行成功！

```bash
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> watch money  # 监视 money 对象
OK
127.0.0.1:6379> multi  # 事务正常执行，数据期间没有发生变动，这个时候正常执行成功！
OK
127.0.0.1:6379> decrby money 20
QUEUED
127.0.0.1:6379> incrby out 20
QUEUED
127.0.0.1:6379> exec
1) (integer) 80
2) (integer) 20
```

测试多线程修改值，监控失败！使用 `watch` 可以当做 Redis 的**乐观锁**操作！

```bash
127.0.0.1:6379> watch money  # 监视 money 
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> decrby money 10
QUEUED
127.0.0.1:6379> incrby out 10
QUEUED
127.0.0.1:6379> exec  # 执行之前，另外一个线程修改了值，则事务执行失败
(nil)
```

解决：重新监视，再执行

```bash
127.0.0.1:6379> unwatch  # 1. 如果发现事务执行失败，就先解锁
OK
127.0.0.1:6379> watch money  # 2. 获取最新的version，再次监视
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> decrby money 10
QUEUED
127.0.0.1:6379> incrby money 10
QUEUED
127.0.0.1:6379> exec  # 3. 比对监视的值是否发生了变化，如果没有变化，那么执行成功，否则执行失败
1) (integer) 990
2) (integer) 1000
```

### 6 Jedis

使用 Java 来操作 Redis。

> 什么是 Jedis

Redis 官方推荐的 Java 连接开发工具！使用 Java 操作 Redis 中间件，需要对 Redis十分熟悉！

> 测试

1. 导入对应的依赖

   ```xml
   <dependency>
     <groupId>redis.clients</groupId>
     <artifactId>jedis</artifactId>
     <version>3.4.1</version>
   </dependency>
   <dependency>
     <groupId>com.alibaba</groupId>
     <artifactId>fastjson</artifactId>
     <version>1.2.73</version>
   </dependency>
   ```

2. 编码测试

   - 连接数据库
   - 操作命令
   - 断开连接

```java
public class TestPing {
    public static void main(String[] args) {
        // 1、new Jedis
        Jedis jedis = new Jedis("localhost", 6379);
        // jedis 所有命令即 Redis所有指令
        // NOAUTH Authentication required.
        jedis.auth("123456");
        // 连接测试
        System.out.println(jedis.ping());
    }
}
```

#### 常用的APi

##### 基本操作

```java
Jedis jedis = new Jedis("localhost", 6379);
jedis.auth("123456");

System.out.println("清空数据：" + jedis.flushDB());
System.out.println("判断某个键是否存在：" + jedis.exists("username"));
System.out.println("新增 <username, sugar> 的键值对：" + jedis.set("username", "sugar"));
System.out.println("新增 <password, 123456> 的键值对：" + jedis.set("password", "123456"));
System.out.println("系统中所有的键如下：\n " + jedis.keys("*"));
System.out.println("删除键password：" + jedis.del("password"));
System.out.println("判断键password是否存在：" + jedis.exists("password"));
System.out.println("查看键username所储值的类型：" + jedis.type("username"));
System.out.println("随机返回key空间的一个：" + jedis.randomKey());
System.out.println("重命名key：" + jedis.rename("username", "name"));
System.out.println("取出改名后的name：" + jedis.get("name"));
System.out.println("按索引查询：" + jedis.select(0));
System.out.println("删除当前选择数据库中的所有key：" + jedis.flushDB());
System.out.println("返回当前数据库中key的数量：" + jedis.dbSize());
System.out.println("删除所有数据库中的所有key：" + jedis.flushAll());
```

##### String

```java
jedis.flushDB();
System.out.println("========= 增加数据 =========");
System.out.println(jedis.set("key1", "value1"));
System.out.println(jedis.set("key2", "value2"));
System.out.println(jedis.set("key3", "value3"));
System.out.println("删除键key2：" + jedis.del("key2"));
System.out.println("获取键key2：" + jedis.get("key2"));
System.out.println("修改key1：" + jedis.set("key1", "value1Changed"));
System.out.println("获取key1的值：" + jedis.get("key1"));
System.out.println("在key3后面加入值：" + jedis.append("key3", "End"));
System.out.println("获取key3的值：" + jedis.get("key3"));
System.out.println("增加多个键值对：" + jedis.mset("key01", "value01", "key02", "value02", "key03", "value03"));
System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));
System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03", "key04"));
System.out.println("删除多个键值对：" + jedis.del("key01", "key02"));
System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));


jedis.flushDB();
System.out.println("========= 新增键值对防止覆盖原先值 =========");
System.out.println(jedis.setnx("key1", "value1"));
System.out.println(jedis.setnx("key2", "value2"));
System.out.println(jedis.setnx("key2", "value2-new"));
System.out.println(jedis.get("key1"));
System.out.println(jedis.get("key2"));

System.out.println("========= 新增键值对并设置有效时间 =========");
System.out.println(jedis.setex("key3", 2, "value3"));
System.out.println(jedis.get("key3"));
try {
  Thread.sleep(2000);
} catch (InterruptedException e) {
  e.printStackTrace();
}
System.out.println(jedis.get("key3"));

System.out.println("========= 获取原值，更新为新值 =========");
System.out.println(jedis.getSet("key2", "key2Getset"));
System.out.println(jedis.get("key2"));

System.out.println("获取key2的子串：" + jedis.getrange("key2", 2, 4));
```

##### List

```java
jedis.flushDB();

System.out.println("========= 添加一个List =========");
jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap");
jedis.lpush("collections", "HashSet");
jedis.lpush("collections", "TreeSet");
jedis.lpush("collections", "TreeMap");
System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
System.out.println("collections区间0-3的元素：" + jedis.lrange("collections", 0, 3));

System.out.println("===============================");
System.out.println("删除指定元素个数：" + jedis.lrem("collections", 2, "HashMap"));
System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
System.out.println("删除下标0-3区间之外的元素：" + jedis.ltrim("collections", 0, 3));
System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
System.out.println("collections列表出栈（左端）：" + jedis.lpop("collections"));
System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
System.out.println("collections添加元素，从列表右端，与lpush相对应：" + jedis.rpush("collections", "HashMap"));
System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
System.out.println("collections列表出栈（右端）：" + jedis.rpop("collections"));
System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
System.out.println("修改collections指定下标为1的内容：" + jedis.lset("collections", 1, "LinkedList"));
System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));

System.out.println("===============================");
System.out.println("collections的长度：" + jedis.llen("collections"));
System.out.println("获取collections下标为2的元素：" + jedis.lindex("collections", 2));

System.out.println("===============================");
jedis.lpush("sortedList", "3", "6", "2", "0", "7", "4");
System.out.println("sortedList排序前：" + jedis.lrange("sortedList", 0, -1));
System.out.println(jedis.sort("sortedList"));
System.out.println("sortedList排序后：" + jedis.lrange("sortedList", 0, -1));
```

##### Set

```java
System.out.println("======== 向集合中添加元素（不重复） ========");
System.out.println(jedis.sadd("eleSet", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
System.out.println(jedis.sadd("eleSet", "e6"));
System.out.println(jedis.sadd("eleSet", "e6"));
System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));
System.out.println("删除一个元素e0：" + jedis.srem("eleSet", "e0"));
System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));
System.out.println("删除两个元素e6和e7：" + jedis.srem("eleSet", "e7", "e6"));
System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));
System.out.println("随机移除集合中的一个元素：" + jedis.spop("eleSet"));
System.out.println("随机移除集合中的一个元素：" + jedis.spop("eleSet"));
System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));
System.out.println("eleSet中包含元素的个数：" + jedis.scard("eleSet"));
System.out.println("e3是否在eleSet中：" + jedis.sismember("eleSet", "e3"));
System.out.println("e1是否在eleSet中：" + jedis.sismember("eleSet", "e1"));
System.out.println("e5是否在eleSet中：" + jedis.sismember("eleSet", "e5"));

System.out.println("===============================");
System.out.println(jedis.sadd("eleSet1", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
System.out.println(jedis.sadd("eleSet2", "e1", "e2", "e4", "e3", "e0"));
System.out.println("将eleSet1中删除e1并存入eleSet3中：" + jedis.smove("eleSet1", "eleSet3", "e1"));
System.out.println("将eleSet1中删除e2并存入eleSet3中：" + jedis.smove("eleSet1", "eleSet3", "e2"));
System.out.println("eleSet1中的元素：" + jedis.smembers("eleSet1"));
System.out.println("eleSet3中的元素：" + jedis.smembers("eleSet3"));

System.out.println("=============== 集合运算 ===============");
System.out.println("eleSet1中的元素：" + jedis.smembers("eleSet1"));
System.out.println("eleSet2中的元素：" + jedis.smembers("eleSet2"));
System.out.println("eleSet1和eleSet2的交集：" + jedis.sinter("eleSet1", "eleSet2"));
System.out.println("eleSet1和eleSet2的并集：" + jedis.sunion("eleSet1", "eleSet2"));
System.out.println("eleSet1和eleSet2的差集：" + jedis.sdiff("eleSet1", "eleSet2"));  // eleSet1有，而eleSet2中没有的元素
jedis.sinterstore("eleSet4", "eleSet1", "eleSet2");  // 将交集结果保存到 eleSet4 中
System.out.println("eleSet4中的元素：" + jedis.smembers("eleSet4"));
```

##### Hash

```java
Map<String, String> map = new HashMap<>();
map.put("key1", "value1");
map.put("key2", "value2");
map.put("key3", "value3");
map.put("key4", "value4");
jedis.hmset("hash", map);
jedis.hset("hash", "key5", "value5");
System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));  // return Map<String, String>
System.out.println("散列hash的所有键为：" + jedis.hkeys("hash"));  // return Set<String>
System.out.println("散列hash的所有值为：" + jedis.hvals("hash"));  // return List<String>
System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6：" + jedis.hincrBy("hash", "key6", 1));
System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6：" + jedis.hincrBy("hash", "key6", 1));
System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
System.out.println("删除一个或者多个键值对：" + jedis.hdel("hash", "key2"));
System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
System.out.println("散列hash中键值对的个数：" + jedis.hlen("hash"));
System.out.println("判断hash中是否存在key2：" + jedis.hexists("hash", "key2"));
System.out.println("判断hash中是否存在key3：" + jedis.hexists("hash", "key3"));
System.out.println("获取hash中的值：" + jedis.hmget("hash", "key3"));
System.out.println("获取hash中的值：" + jedis.hmget("hash", "key3", "key4"));
```

##### Zset

同 Reids 的命令。

#### 事务

```java
Jedis jedis = new Jedis("localhsot", 6379);
jedis.auth("123456");

JSONObject obj = new JSONObject();
obj.put("hello", "world");
obj.put("name", "sugar");
// 开启事务
Transaction multi = jedis.multi();

try {
  multi.set("user1", obj.toJSONString());
  multi.set("user2", obj.toJSONString());
  multi.exec();  // 执行事务
} catch (Exception e) {
  multi.discard();  // 放弃事务
} finally {
  System.out.println(jedis.get("user1"));
  System.out.println(jedis.get("user2"));
  jedis.close();  // 关闭连接
}
```

### 7 SpringBoot整合

SpringBoot操作数据：Spring-Data（JPA、JDBC、MongoDB、Redis）