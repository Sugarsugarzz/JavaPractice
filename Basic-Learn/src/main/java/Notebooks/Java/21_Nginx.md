### 1 Nginx

> 瓶颈

项目刚上线的时候，并发量小，用户规模小，所以在低并发的情况下，一个 jar 包启动应用就足够，由内部 tomcat 返回内容给用户。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128225752059.png" alt="image-20210128225752059" style="zoom:40%;" />

随着用户越来越多，并发量慢慢增大，一台服务器无法满足需求了。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128225834017.png" alt="image-20210128225834017" style="zoom:40%;" />

于是横向扩展，增加服务器。这时几个项目启动在不同的服务器上，用户访问，就需要增加一个代理服务器，通过代理服务器来转发和处理请求。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128230046906.png" alt="image-20210128230046906" style="zoom:40%;" />

通过这个代理服务器可以帮助接收用户的请求，然后将用户的请求按照规则转发到不同的服务器节点上，这个过程用户是无感的，并不知道是哪个服务器返回的结果，我们可以按照服务器的性能提供不同的权重选择，保证最佳的用户体验。所以选择 Nginx。

> 什么是Nginx？

Nginx（engine x）是一个高性能的HTTP和反向代理web服务器，同时也提供了 IMAP/POP3/SMTP 服务。Nginx是由伊尔格·塞索耶夫为俄罗斯访问第二的Rambler.ru站点开发的，第一个公开版本0.1.0发布于2004年10月4日， nginx 1.0.4 发布。

其特点是占有内存少，并发能力强，事实上 nginx 的并发能力在同类型的网页服务器中表现较好，大陆使用 nginx 网站用户有：百度、京东、新浪、网易、腾讯、淘宝等。在全球活跃的网站中有 12.18% 的使用比率，大约为2220万个站点。

Nginx 是一个安装简单、配置文件简洁（能够支持 perl 语法）、Bug非常少的服务。Nginx启动特别容易，并且几乎可以做到 7*24 不间断运行，即使运行数个月也不需要重新启动。还能够不间断服务的情况下进行版本升级。

Nginx完全用 C语言写成。官方数据测试表明能够支持高达 **50000 个并发连接数的响应**。

> Nginx作用？

Http代理，反向代理：作为web服务器最常用的功能之一。尤其是反向代理。

**正向代理**

代理客户端的

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128230747048.png" alt="image-20210128230747048" style="zoom:40%;" />

**反向代理**

代理服务器的

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128230849903.png" alt="image-20210128230849903" style="zoom:40%;" />

> Nginx负载均衡：两种
>
> 	- 内置策略：轮询、加权轮询、Ip Hash
> 	- 扩展策略：。。。只有想不到，没有做不到

**轮询**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128231107444.png" alt="image-20210128231107444" style="zoom:40%;" />

**加权轮询**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128231133097.png" alt="image-20210128231133097" style="zoom:40%;" />

**IP HASH：对客户端请求的ip进行hash操作，然后根据hash结果将同一个客户端ip的请求分发给同一台服务器进行处理，可以解决session不共享的问题。**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128231244268.png" alt="image-20210128231244268" style="zoom:40%;" />

> 动静分离，软件开发中，有些请求需要后台处理的，有些请求是不需要后台处理的（如：css、html、jpg、js等文件），这些不需要经过后台处理的文件称为静态文件。让动态网站里的动态网页根据一定规则把不变的资源和经常变的资源区分开来，动静资源做好了拆分之后，就可以根据静态资源的特点将其做缓存操作，提高资源响应的速度。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210128231635193.png" alt="image-20210128231635193" style="zoom:40%;" />

### 2 Nginx的安装

> Windows安装

http://nginx.org/en/download.html

下载稳定版，解压，启动 nginx.exe。

访问 http://localhost:80，出现 Welcome to Nginx！表明启动成功！

配置文件是 conf 目录下的 nginx.conf，默认监听80端口。

关闭nginx：(1) `nginx -s stop`（快速停止nginx）或`nginx -s quit`（完整有序的停止nginx） (2) `taskkill /f /t /im nginx.exe`

> Linux安装

1. 安装gcc
2. `tar -zxvf nginx-1.18.0.tar.gz`
3. `./configure`
4. `make`
5. `make install`
6. `whereis nginx`，到 sbin 目录启动 nginx。 `./nginx`

### 3 Nginx常用命令

```bash
cd /usr/local/nginx/sbin/
./nginx  启动
./nginx -s stop  停止
./nginx -s quit  安全退出
./nginx -s reload  重新加载配置文件
ps aux|grep nginx  查看nginx进程
```

启动成功，访问服务器ip:80。

**注意：如果连接不上，查看阿里云安全组是否开放端口，或者服务器防火墙是否开放端口！**

相关命令：

```bash
# 开启防火墙
service firwalld start
# 重启防火墙
service firewalld restart
# 关闭防火墙
service firewalld stop
# 查看防火墙规则
firewall-cmd --list-all
# 查看端口是否开放
firewall-cmd  --query-port=8080/tcp
# 开放80端口
firewall-cmd --permanent --add-port=80/tcp
# 移除端口
firewall-cmd --permanent --remove-port=8080/tcp

# 重启防火墙（修改配置后要重启防火请）
firewall-cmd --reload

# 参数解释
1. firewall-cmd：是Linux提供操作firewall的一个工具
2. --permanent：表示设置为持久
3. --add-port：标识添加的端口
```

### 4 Nginx配置

假设已经在 8080 和 8081 启动两个Web服务。

```bash
# 全局配置
events {
	worker_connections 1024;
}

http {
	# http 配置
	
	upstream sugar {
		# 负载均衡配置，命名任意，但要与 proxy_pass 一致
		server 127.0.0.1:8080 weight=1;		# weight一致则轮询
		server 127.0.0.1:8081 weight=1;
	}
	
	server {
		# http
		listen			80;
		server_name	localhost;
		
		location / {
			root		html;
			index		index.html index.htm;
      proxy_pass	http://sugar;  # 反向代理
		}
	}
	
	server {
		# https
		listen			443;
		server_name	localhost;
	}

}
```

