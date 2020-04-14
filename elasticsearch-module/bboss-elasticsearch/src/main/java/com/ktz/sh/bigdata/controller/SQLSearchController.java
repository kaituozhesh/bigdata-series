package com.ktz.sh.bigdata.controller;

import com.ktz.sh.bigdata.dto.SearchDto;
import com.ktz.sh.bigdata.service.SQLSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SQLSearchController
 * @Description
 * @Author kaituozhesh
 * @Date 2020/4/14 15:25
 * @Version V1.0.0
 **/
@RestController
@RequestMapping("/sql")
public class SQLSearchController {

    @Autowired
    private SQLSearchService sqlSearchService;

    @PostMapping("/search_all")
    public Object searchAll() {
        return sqlSearchService.searchAll();
    }

    /**
     * 条件搜索
     *
     * @return
     */
    @PostMapping("/condition_search")
    public Object conditionSearch(@RequestBody SearchDto search) {
        return sqlSearchService.conditionSearch(search);
    }


}
