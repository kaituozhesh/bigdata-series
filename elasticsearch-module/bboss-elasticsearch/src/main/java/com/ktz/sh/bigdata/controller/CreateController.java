package com.ktz.sh.bigdata.controller;

import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
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
        ClientInterface client = bbossESStarter.getConfigRestClient("esmapper/Example.xml");
        try {
            boolean exist = client.existIndice("example");
            // 判断索引是否存在
            if (exist) {
                client.dropIndice("example");
            }
            client.createIndiceMapping("example", "create_example_index");
            return "success";
        } catch (ElasticSearchException e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
