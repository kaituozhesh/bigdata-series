package com.ktz.sh.bigdata.service;

import com.ktz.sh.bigdata.document.Example;
import com.ktz.sh.bigdata.dto.SearchDto;

import java.util.List;

public interface SearchService {

    /**
     * 搜索文档
     *
     * @param search
     * @return
     */
    List<Example> searchDocumentList(SearchDto search);

    /**
     * 搜索对应文档数量
     *
     * @param searchDto
     * @return
     */
    long searchDocumentCount(SearchDto searchDto);

    /**
     * 分页搜索文档
     *
     * @param search
     * @return
     */
    List<Example> searchPage(SearchDto search);
}
