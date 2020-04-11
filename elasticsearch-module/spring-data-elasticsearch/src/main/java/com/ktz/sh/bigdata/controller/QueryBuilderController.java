package com.ktz.sh.bigdata.controller;

import com.ktz.sh.bigdata.document.Item;
import com.ktz.sh.bigdata.repository.ItemRepository;
import org.elasticsearch.index.query.*;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : QueryBuilderController
 * @Description :
 * NativeSearchQueryBuilder：主要的组成部分就是
 * QueryBuilder：
 * SortBuilder：
 * filter：
 * match：模糊匹配
 * term：精确匹配
 * @Author : kaituozhesh
 * @Date: 2020-04-07 22:12
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/query")
public class QueryBuilderController {

    @Resource
    private ItemRepository itemRepository;

    private void location() {
        /*查询全部*/ matchAllQuery();
        /*单值模糊匹配*/ matchQuery();
        /*多字段模糊匹配*/ multiMatchQuery();
        /*短语匹配*/ matchPhraseQuery();
        /*前缀短语匹配*/ matchPhrasePrefixQuery();
        /*精确匹配*/ termQuery();
        /*单字段多值匹配*/ termsQuery();
        /*前缀查询*/ prefixQuery();
        /*通配符查询*/ wildcardQuery();
        /*bool查询*/ boolQuery();
    }

    /**
     * 查询全部
     *
     * @return
     */
    @PostMapping("/match_all_query")
    public Object matchAllQuery() {
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        return itemRepository.search(matchAllQueryBuilder);
    }

    /**
     * 单值匹配
     *
     * @return
     */
    @PostMapping("/match_query")
    public Object matchQuery() {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "小米");
        return itemRepository.search(matchQueryBuilder);
    }

    /**
     * 多字段匹配
     *
     * @return
     */
    @PostMapping("/multi_match_query")
    public Object multiMatchQuery() {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("小米", "title", "brand");
        return itemRepository.search(multiMatchQueryBuilder);
    }

    /**
     * 短语匹配
     *
     * @return
     */
    @PostMapping("/match_phrase_query")
    public Object matchPhraseQuery() {
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("title", "小米");
        return itemRepository.search(matchPhraseQueryBuilder);
    }

    /**
     * 前缀短语匹配
     *
     * @return
     */
    @PostMapping("/match_phrase_prefix_query")
    public Object matchPhrasePrefixQuery() {
        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("title", "小米");
        return itemRepository.search(matchPhrasePrefixQueryBuilder);
    }

    /**
     * 精确查询
     *
     * @return
     */
    @PostMapping("/term_query")
    public Object termQuery() {
        // 对于字段类型为text的是不能进行精确匹配的
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小米手机");
        return itemRepository.search(termQueryBuilder);
    }

    /**
     * 一个字段做多值匹配  类似于IN
     *
     * @return
     */
    @PostMapping("/terms_query")
    public Object termsQuery() {
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("brand", "华为", "小米");
        return itemRepository.search(termsQueryBuilder);
    }

    /**
     * 前缀查询
     *
     * @return
     */
    @PostMapping("/prefix_query")
    public Object prefixQuery() {
        // 不能作用于text类型字段
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("category", "手");
        return itemRepository.search(prefixQueryBuilder);
    }

    /**
     * 通配符查询
     * * 匹配任何字符
     * ? 匹配任何单个字符
     *
     * @return
     */
    @PostMapping("/wildcard_query")
    public Object wildcardQuery() {
        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("brand", "小?");
        return itemRepository.search(wildcardQuery);
    }

    /**
     * bool查询
     *
     * @return
     */
    @PostMapping("/bool_query")
    public Object boolQuery() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("brand", "小米"))
                .must(QueryBuilders.termQuery("category", "手机"))
                .mustNot(QueryBuilders.termQuery("id", 5))
                .should(QueryBuilders.termQuery("price", 4299.0));
        return itemRepository.search(boolQuery);
    }


}
