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

### String

### List

#### Set

#### Hash

#### Zset



### 4 三种特殊数据类型

#### geospatial

#### hyperloglog

#### bitmaps

