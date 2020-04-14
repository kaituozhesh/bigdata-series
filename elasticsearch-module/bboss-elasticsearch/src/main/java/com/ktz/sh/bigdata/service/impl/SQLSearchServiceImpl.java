package com.ktz.sh.bigdata.service.impl;

import com.ktz.sh.bigdata.document.Example;
import com.ktz.sh.bigdata.dto.SearchDto;
import com.ktz.sh.bigdata.service.SQLSearchService;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SQLSearchServiceImpl
 * @Description
 * @Author kaituozhesh
 * @Date 2020/4/14 15:25
 * @Version V1.0.0
 **/
@Service
public class SQLSearchServiceImpl implements SQLSearchService {

    @Autowired
    private BBossESStarter bbossESStarter;


    @Override
    public List<Example> searchAll() {
        ClientInterface client = ElasticSearchHelper.getRestClientUtil();
        List<Example> sql = client.sql(Example.class, "{\"query\": \"SELECT * FROM example\"}");
        return sql;
    }

    @Override
    public List<Example> conditionSearch(SearchDto search) {
        ClientInterface client = bbossESStarter.getConfigRestClient("esmapper/SQL.xml");
        List<Example> list = client.sql(Example.class, "sql_query", search);
        return list;
    }
}

