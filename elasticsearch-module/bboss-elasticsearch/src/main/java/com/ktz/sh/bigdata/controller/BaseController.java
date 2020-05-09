package com.ktz.sh.bigdata.controller;

import com.ktz.sh.bigdata.document.Example;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BaseController
 * @Description
 * @Author kaituozhesh
 * @Date 2020/4/10 9:04
 * @Version V1.0.0
 **/
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private BBossESStarter bbossESStarter;

    private final static String mappingPath = "esmapper/Example.xml";

    /**
     * 创建文档
     *
     * @return
     */
    @PostMapping("/create_document")
    public Object createDocument() {
        ClientInterface client = bbossESStarter.getRestClient();
        Example example = new Example(1L, new Date(), "blackcatdemo1", "this-is content body2", "|刘德华", "NFZF15045871807281445364228", 2);
        // 添加文档,根据@ESId作为文档ID,如果存在做修改,否则添加
//        String response = client.addDocument("example", example);

        // 指定文档ID 进行添加
        String response = client.addDocumentWithId("example", example, 2L);
        return response;
    }

    /**
     * 删除文档
     *
     * @return
     */
    @PostMapping("/delete_document")
    public Object deleteDocument() {
        ClientInterface client = bbossESStarter.getRestClient();
        // _doc是默认的
        String response = client.deleteDocument("example", "_doc", "2");
        return response;
    }

    /**
     * 批量创建文档
     *
     * @return
     */
    @PostMapping("/batch_create_document")
    public Object batchCreateDocument() {
        ClientInterface client = bbossESStarter.getRestClient();
        List<Example> exampleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            exampleList.add(new Example((long) i, new Date(), "blackcatdemo" + i, "this-is content body" + i, "刘德华" + i, "NFZF15045871807281445364228" + i, 2));
        }
        String response = client.addDocuments("example", exampleList, "refresh");
        return response;
    }

    /**
     * 根据ID获取文档对象
     *
     * @return
     */
    @PostMapping("/find_document")
    public Object findDocument() {
        ClientInterface client = bbossESStarter.getRestClient();
        Example example = client.getDocument("example", "_doc", "2", Example.class);
        return example;
    }

}
