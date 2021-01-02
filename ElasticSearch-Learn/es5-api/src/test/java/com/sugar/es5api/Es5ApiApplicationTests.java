package com.sugar.es5api;


import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
class Es5ApiApplicationTests {

	@Autowired
	@Qualifier("restHighLevelClient")
	RestHighLevelClient client;

	@Test
	void createIndex() throws IOException {

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		// 时间范围
		boolQueryBuilder.must(QueryBuilders.rangeQuery("pubtime").gte("2020-12-13 00:00:00").lt("2020-12-15 00:00:00"));
		// 大于游标id
		boolQueryBuilder.must(QueryBuilders.rangeQuery("auto_id").gt(0));
		// 关键词模糊匹配
		boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery("content", "省份"));
		// 不匹配的关键词
		boolQueryBuilder.mustNot(QueryBuilders.matchPhrasePrefixQuery("content", "哈哈"));

		sourceBuilder.query(boolQueryBuilder);
		// 排序
		sourceBuilder.sort(new FieldSortBuilder("auto_id").order(SortOrder.ASC));
		// 发起请求
		SearchRequest sq = new SearchRequest();
		sq.indices("facebook_info");  // 查询的索引
		sq.source(sourceBuilder);  // 组合条件
		System.out.println(sq.source().toString());

		// 获取source
		SearchResponse rp = client.search(sq);
		System.out.println(rp);
		System.out.println(Arrays.toString(rp.getHits().getHits()));

		for (SearchHit hit : rp.getHits().getHits()) {
			System.out.println(hit.getSourceAsMap().get("auto_id"));
		}

	}

}
