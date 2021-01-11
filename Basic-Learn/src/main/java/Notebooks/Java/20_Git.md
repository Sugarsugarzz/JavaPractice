### 1 版本控制

版本控制（Version Control）是一种在开发过程中用于管理我们对文件、目录或工程等内容的修改历史，方便查看更改历史记录，备份以便恢复以前的版本的软件工程技术。

- 实现跨区域多人协同开发
- 追踪和记载一个或多个文件的历史记录
- 组织和保护源代码和文档
- 统计工作量
- 并行开发、提高开发效率
- 跟踪记录整个软件的开发过程
- 减轻开发人员负担，节省时间，降低人为错误

即，**用于管理多人协同开发项目的技术**。

没有进行版本控制或版本控制本身缺乏正确的流程管理，在软件开发过程中会引入很多问题，如代码的一致性、软件内容的冗余、软件过程的事物性、软件开发过程中的并发性、源代码的安全性，以及软件的整合等问题。

#### 常见的版本控制工具

- Git
- SVN
- CVS
- VSS
- TFS
- Visual Studio Online

#### 版本控制分类

1. **本地版本控制（RCS）**

   ​	记录文件的每次更新，每个版本做一个快照，适合个人。

2. **集中版本控制（SVN、CVS）**

   ​	所有版本数据保存在服务器上，协同开发者从服务器上同步更新或上传自己的修改。

   ​	用户不联网的话，看不到历史版本，也无法切换版本验证问题，或在不同分支工作。所有数据保存在单一服务器上，如果服务器损坏，则所有数据丢失，需要定时备份。

3. **分布式版本控制（Git）**

   ​	所有版本信息仓库全部同步到本地的每个用户，在本地可以查看所有版本历史，可以离线在本地提交，只需要在联网时PUSH到相应服务器上。

   ​	由于每个用户保存的都是所有版本数据，只要有一个用户的设备没有问题就可以恢复所有的数据，但增加本地存储空间的占用。

   ​	不会因为服务器损坏或网络问题，造成不能工作的情况。但每个人拥有全部的代码，可能有一些安全隐患。SVN可以做权限管理。

#### Git和SVN最主要的区别

​	SVN是集中式版本控制系统，版本库是集中放在中央服务器的，而工作的时候，用的都是自己的电脑，所以首先要从中央服务器得到最新的版本，然后工作，完成工作后，需要把自己做完的活推送到中央服务器。集中式版本控制系统是必须联网才能工作的，对网络带宽有要求。

​	Git是分布式版本控制系统，没有中央服务器，每个人的电脑都是一个完整的版本库，工作的时候不需要联网，因为版本都在自己的电脑上。协同的方法是：比如自己电脑上修改了文件A，其他人也在电脑上修改了文件A，这时，只需要把各自修改推送给对方，就可以相互看到对方的修改了。Git可以直接看到更新了哪些代码和文件。



### 2 Git历史

Linux之父两周开发Git，用来替代**BitKeeper**。



### 3 Git环境配置

#### Git下载

​	官网：http://git-scm.com/

​	淘宝镜像：http://npm.taobao.org/mirrors/git-for-windows/

​	无脑下一步即可，配置环境变量。

#### Windows中启动Git

- **Git Bash**：Linux风格的命令行，使用最多
- **Git CMD**：Windows风格的命令行
- **Git GUI**：图形界面Git

#### Git配置

​	配置文件在本地保存。

​	查看配置 `git config -l`

​	查看系统配置 `git config --system --list`

​	查看用户配置 `git config --global --list`

#### 设置用户名与邮箱（用户标识，必要）

​	安装Git后，首要做的是设置**用户名**和**邮箱地址**。非常重要，因为Git每次提交都会使用该信息，该信息被永远嵌入到提交中。

```shell
git config --global user.name "sugar"  # 用户名
git config --global user.email 406857586@qq.com  # 邮箱
```

​	只需要做一次这个设置，如果传递了 `--global` 选项，因为 Git 将总是会使用该信息来处理系统中的一切操作。如果希望在一个特定项目中使用不同的名称或邮箱地址，可以在该项目中运行该命令而不要 `--global` 选项。总之，global 为全局配置，不加为某个项目的特定配置。



### 4 Git基本理论（核心）

#### 工作区域

​	Git本地有三个工作区域：工作目录（Working Directory）、暂存区（Stage/Index）、资源库（Repository或Git Directory）。如果在加上远程的 git 仓库（Remote Directory）就可以分为四个工作区域。文件在这四个区域之间的转换关系如下：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210111085309093.png" alt="image-20210111085309093" style="zoom:40%;" />

- **Workspace**：工作区，平时存放项目代码的地方
- **Index / Stage**：暂存区，用于临时存放你的改动，事实上它只是一个文件，保存即将提交到文件列表信息
- **Repository**：仓库区（或本地仓库），就是安全存放数据的地方，这里有提交的所有版本的数据。其中HEAD指向最新放入仓库的版本。
- **Remote**：远程仓库，托管代码的服务器，可以认为是项目组中的一台电脑用于远程数据交换。

本地的三个区域确切的说就是 Git 仓库中 HEAD 指向的版本。

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210111085840534.png" alt="image-20210111085840534" style="zoom:30%;" />

- Directory：使用Git管理的一个木库，也就是一个仓库，包含工作空间和Git的管理空间。
- WorkSpace：需要通过Git进行版本控制的目录和文件，这些目录和文件组成了工作空间。
- .git：存放Git管理信息的目录，初始化仓库的时候自动创建。
- Index/Sage：暂存区，或交待提交更新区，在提交进入repo之前，可以把所有的更新放在暂存区。
- Local Repo：本地仓库，一个存放在本地的版本库；HEAD会只是当前的开发分支（branch）。
- Stash：隐藏，是一个工作状态保存栈，用于保存/恢复WorkSpace中的临时状态。

### 工作流程

git的工作流程如下：

1. 在工作目录中添加、修改文件；
2. 将需要进行版本管理的文件放入暂存区域
3. 将暂存区域的文件提交到git仓库。

因此，git管理的文件由三种状态：**已修改（modified）、已暂存（staged）、已提交（committed）**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210111090512030.png" alt="image-20210111090512030" style="zoom:30%;" />



### 5 Git项目搭建

#### 创建工作目录与常用命令

​	工作目录（WorkSpace）一般是Git管理的文件夹，可以是项目目录，也可以是一个空目录。日常使用以下6个命令：

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210111090707794.png" alt="image-20210111090707794" style="zoom:30%;" />

#### 本地仓库搭建

创建本地仓库的方法有两种：一种是**创建全新的仓库**，另一种是**克隆远程仓库**。

##### 1 创建全新的仓库

1. 创建全新的仓库，需要在用 Git 管理的项目根目录执行：

   ```shell
   # 在当前目录新建一个Git代码库
   git init
   ```

2. 执行后可以看到，在项目目录多处一个隐藏的 **.git目录** ，关于版本等的所有信息都在这个目录里面。

##### 2 克隆远程仓库

1. 将远程服务器上的仓库完全镜像一份到本地

   ```shell
   # 克隆一个项目和它的整个代码历史（版本信息）
   git clone [url]
   ```

2. 从 gitee 或 github 上克隆项目



### 6 Git文件操作

#### 文件4种状态

版本控制就是对文件的版本控制，要对文件进行修改、提交等操作，首先要知道文件当前在什么状态，不然可能会提交了现在还不想提交的文件，或者要提交的文件没有提交上。

- **Untracked**：未跟踪，此文件在文件夹中，但并没有加入到Git库中，不参与版本控制，通过 `git add` 状态变为 `Staged`
- **Unmodify**：文件已经入库，未修改，即版本库中的文件快照内容与文件夹中完全一致。这种类型的文件由两种去处，如果它被修改，则变为 `Modified`。如果使用 `git rm` 移出版本库，则成为 `Untracked` 文件。
- **Modified**：文件已修改，仅仅是修改，并没有进行其他的操作。这个文件也有两个去处，通过 `git add` 可进入暂存 `Staged` 状态，使用 `git checkout` 则丢弃修改过，返回到 `unmodify` 状态，这个 `git checkout` 即从库中取出文件，覆盖当前修改！
- **Staged**：暂存状态。执行 `git commit` 则将修改同步到库中，这是库中的文件和本地文件又变为一致，文件为 `Unmodify` 状态。执行 `git reset HEAD filename` 取消暂存，文件状态变为 `Modified`。

#### 查看文件状态

以上四种状态，通过如下命令可以查看到文件的状态：

```shell
# 查看指定文件状态
git status [filename]

# 查看所有文件状态
git status

# git add .  			 				   添加所有文件到暂存区
# git commit -m "消息内容" 	  提交暂存区中的内容到本地仓库  -m 提交信息
```

#### 忽略文件

比如前端项目中的 npm_modules

有些时间不想把某些文件纳入版本控制中，比如数据库文件、临时文件、设计文件等

在主目录下建立 ”**.gitignore**“ 文件，此文件有如下限制：

1. 忽略文件中的空行或以井号（#）开始的行将会被忽略。
2. 可以使用Linux通配符。例如：星号（*）代表任意多个字符，问号（？）代表一个字符，方括号（[abc]）代表可选字符范围，大括号（{string1, string2...}）代表可选的字符串等。
3. 如果名称的最前面有一个感叹号（！），表示例外规则，将不被忽略。
4. 如果名称的最前面是一个路径分隔符（/），表示要忽略的文件在此目录下，而子目录中的文件不忽略。
5. 如果名称的最后面是一个路径分隔符（/），表示要忽略的是此目录下该名称的子目录，而非文件（默认文件或目录都忽略）。

```shell
# 举例
*.txt			# 忽略所有 .txt结尾的文件
!lib.txt	# 但lib.txt除外
/temp			# 仅忽略项目根目录下的temp文件，但不包括其他目录的temp
build/		# 忽略build/目录下的所有文件
doc/*.txt	# 忽略 doc/notes.txt 但不包括 doc/server/arch.txt
```

```shell
# .gitignore

# Java Ignore
*.class
*.log
*.lock

# Package Files #
*.jar
*.war
*.ear
target/

# IDEA #
.idea/
*.iml
*.ipr
*.iws
.classpath
.project
.settings/
bin/
tmp/

# rebel
*rebel.xml*
```



### 7 使用码云

1. 注册登录码云 gitee

2. 设置本机绑定SSH公钥，实现免密码登录

   ```shell
   # cd ~/.ssh 目录
   # 生成公钥
   ssh-keygen [-t rsa]
   ```

3. 将公钥信息 public key 添加到码云账号中即可。

4. 使用码云创建仓库

   - 克隆到本地

   

### 8 IDEA中集成Git

1. 新建IDEA项目，绑定Git。
   - 将远程的git文件目录拷贝到项目中即可。
2. 修改文件，使用IDEA操作Git。
   - add添加到暂存区
   - commit提交到本地
   - push到远程仓库
3. 提交测试。



### 9 Git分支

分支之间相互不影响，但如果在某个时间点，两个分支合并了，就需要处理一些问题了！

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210111140935654.png" alt="image-20210111140935654" style="zoom:40%;" />

Git分支中常用命令：

```shell
# 列出所有本地分支
git branch

# 列出所有远程分支
git branch -r

# 新建一个分支，但依然停留在当前分支
git branch [branch-name]

# 新建一个分支，并切换到该分支
git checkout -b [branch]

# 合并指定分支到当前分支
git merge [branch]

# 删除分支
git branch -d [branch-name]

# 删除远程分支
git push origin --delete [branch-name]
git branch -dr [remote/branch]
```

多个分支如果并行执行，就会导致我们代码不冲突，同时存在多个版本！

web-aip 		A组

web-admin	B组：B会调用A（修改了A的代码）

web-app	    C组：C会调用B和A的代码



**如果同一个文件在合并分支时都被修改了则会引起冲突：解决办法是可以修改冲突后的文件后重新提交！选择要保留他的代码还是你的代码！**

**冲突了需要协商解决！**

master主分支应该非常稳定，用来发布新版本，一般情况下不允许在上面工作，工作一般情况下在新建的dev上开发后，比如要发布，或者说dev分支代码稳定后可以合并到主分支master上来。

**Git命令学习网站：https://oschina.gitee.io/learn-git-branching/**

---

### 10 新建分支合并

#### 1 从master新建出dev分支，然后合并回master

1. 切换到master分支：`git checkout master`
2. 创建并切换到dev分支：`git checkout -b dev`
3. 将dev分支push到远程仓库（与本地仓库同步）：`git push origin dev`
4. 修改dev分支上的内容并提交到本地仓库：`git commit -a -m "内容"`
5. 将dev分支上的修改提交到远程仓库：`git push origin dev`
6. dev分支上修改完成后，切换到master分支：`git checkout master`
7. 将dev分支合并到master分支：`git merge origin/dev`
8. 将合并之后的代码push到远程仓库：`git push origin master`

#### 2 将master分支内容合并到dev分支

1. 切换到所在分支dev：`git checkout dev`
2. 合并：`git merge master`
3. 将本地内容push到dev分支：`git push`