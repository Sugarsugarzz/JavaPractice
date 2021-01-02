### 集成 SpringBoot

1. 使用 Java REST Client （High Level），导入依赖（7.6.4）

2. 初始化

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210101233906555.png" alt="image-20210101233906555" style="zoom:30%;" />

3. 分析类中的方法

   > 配置项目

   <img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210101234133973.png" alt="image-20210101234133973" style="zoom:30%;" />

#### 1 导入依赖

**注意：一定要保证，导入的依赖和es版本一致。**

<img src="/Users/sugar/Library/Application Support/typora-user-images/image-20210101234827519.png" alt="image-20210101234827519" style="zoom:50%;" />

#### 2 集成ES

```java
@Configuration
public class ElasticSearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.16.123", 9200, "http")
                )
        );
        return client;
    }
}
```

#### 3 测试索引 API

##### 3.1 创建索引

```java
void testCreateIndex() throws IOException {
   // 1、创建索引请求
   CreateIndexRequest request = new CreateIndexRequest("test_index");
   // 2、客户端执行创建请求  IndicesClient，请求后获得相应
   CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
   System.out.println(createIndexResponse);
}
```

##### 3.2 





#### 4 测试文档 API

