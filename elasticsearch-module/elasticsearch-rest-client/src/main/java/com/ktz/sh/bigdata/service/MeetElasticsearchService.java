package com.ktz.sh.bigdata.service;

public interface MeetElasticsearchService {
    /**
     * 初始化Elasticsearch
     */
    void initEs();

    /**
     * 关闭Elasticsearch
     */
    void closeEs();

    /**
     * 服务请求
     *
     * @return
     */
    String executeRequest();

    /**
     * 异步服务请求
     *
     * @return
     */
    String executeRequestAsync();

    /**
     * 解析服务返回结果
     */
    void parseElasticsearchResponse();

    /**
     * 设置超时
     */
    void initEsWithTimeout();

    /**
     * 设置节点选择器
     */
    void setNodeSelector();
}
