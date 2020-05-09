package com.ktz.sh.bigdata.controller;

import com.ktz.sh.bigdata.service.MeetElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : MeetElasticsearchController
 * @Description :
 * @Author : kaituozhesh
 * @Date: 2020-04-19 19:18
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/meet")
public class MeetElasticsearchController {

    @Autowired
    private MeetElasticsearchService meetElasticsearchService;

    @PostMapping("/init")
    public String initElasticsearch() {
        meetElasticsearchService.initEs();
        return "Init Elasticsearch Over!";
    }

    @PostMapping("/request")
    public String executeRequest() {
        return meetElasticsearchService.executeRequest();
    }

    @PostMapping("/request_async")
    public String executeRequestAsync() {
        return meetElasticsearchService.executeRequestAsync();
    }

    @PostMapping("/parse_response")
    public String parseElasticsearchResponse() {
        meetElasticsearchService.parseElasticsearchResponse();
        return "Parse Elasticsearch Response Is Over!";
    }


}
