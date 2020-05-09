package com.ktz.sh.bigdata.service.impl;

import com.ktz.sh.bigdata.document.Example;
import com.ktz.sh.bigdata.dto.SearchDto;
import com.ktz.sh.bigdata.service.SearchService;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName SearchServiceImpl
 * @Description
 * @Author kaituozhesh
 * @Date 2020/4/14 14:52
 * @Version V1.0.0
 **/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private BBossESStarter bbossESStarter;
    private final static String mappingPath = "esmapper/Example.xml";

    @Override
    public List<Example> searchDocumentList(SearchDto search) {
        ClientInterface client = bbossESStarter.getConfigRestClient(mappingPath);
        ESDatas<Example> search_document = client.searchList("example/_search", "search_document", search, Example.class);
        return search_document.getDatas();
    }

    @Override
    public long searchDocumentCount(SearchDto searchDto) {
        ClientInterface client = bbossESStarter.getConfigRestClient(mappingPath);
        long cont = client.count("example", "search_document", searchDto);
        return cont;
    }

    @Override
    public List<Example> searchPage(SearchDto search) {
        ClientInterface client = bbossESStarter.getConfigRestClient(mappingPath);
        ESDatas<Example> exampleESDatas = client.searchList("example/_search", "search_page", search, Example.class);
        return exampleESDatas.getDatas();
    }
}
