package com.ktz.sh.bigdata.controller;

import com.ktz.sh.bigdata.dto.SearchDto;
import com.ktz.sh.bigdata.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SearchController
 * @Description 搜索接口
 * @Author kaituozhesh
 * @Date 2020/4/14 14:47
 * @Version V1.0.0
 **/
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 搜索文档
     *
     * @param search
     * @return
     */
    @PostMapping("/document_list")
    public Object searchDocumentList(@RequestBody SearchDto search) {
        return searchService.searchDocumentList(search);
    }

    /**
     * 获取查询文档数量
     *
     * @return
     */
    @PostMapping("/document_count")
    public Object searchDocumentCount(@RequestBody SearchDto search) {
        return searchService.searchDocumentCount(search);
    }

    /**
     * 分页搜索条件
     *
     * @param search
     * @return
     */
    @PostMapping("/search_page")
    public Object searchPage(@RequestBody SearchDto search) {
        return searchService.searchPage(search);
    }
}
