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

MySQL 有的使用它来存储一些比较大的文件、博客、图片！数据库表很大，效率就很低！