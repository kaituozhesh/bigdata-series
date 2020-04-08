package com.ktz.sh.bigdata.controller;

import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : CreateController
 * @Description :
 * @Author : kaituozhesh
 * @Date: 2020-04-08 21:15
 * @Version: 1.0.0
 */
@RestController
public class CreateController {

    @Autowired
    private BBossESStarter bbossESStarter;

    /**
     * 创建索引
     *
     * @return
     */
    @PostMapping("/create_index")
    public Object createIndex() {
        boolean exist = bbossESStarter.getRestClient().existIndiceType("twitter", "tweet");

        //判读索引是否存在，false表示不存在，正常返回true表示存在
        exist = bbossESStarter.getRestClient().existIndice("twitter");

        exist = bbossESStarter.getRestClient().existIndice("agentinfo");
        return exist ? "success" : "fail";
    }


}
