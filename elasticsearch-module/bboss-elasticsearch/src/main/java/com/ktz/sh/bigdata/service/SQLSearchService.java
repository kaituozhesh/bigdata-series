package com.ktz.sh.bigdata.service;

import com.ktz.sh.bigdata.document.Example;
import com.ktz.sh.bigdata.dto.SearchDto;

import java.util.List;

public interface SQLSearchService {

    /**
     * 查询全部
     *
     * @return
     */
    List<Example> searchAll();

    /**
     * 条件搜索
     *
     * @param search
     * @return
     */
    List<Example> conditionSearch(SearchDto search);
}
