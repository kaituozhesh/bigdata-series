package com.ktz.sh.bigdata.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName : Item
 * @Description :
 *  @Document:
 *      indexName: 索引库名称
 *      type: 类型  默认""
 *      shards: 分区数  默认5
 *      replicas: 每个分区默认的备份数
 *      refreshInterval: 刷新间隔 默认1s
 *      indexStoreType: 索引文件存储类型  默认 fs
 *      createIndex:
 *      versionType:
 *  @Field:
 *      type: 属性类型，默认自动检测，可以根据实际情况自己设置
 *          Text、Byte、Short、Integer、Long、Date、Half_Float、Float、Double、Boolean、Object、Auto、Nested、Ip、Attachment、Keyword
 *      index: 是否索引
 *      format: 指定日期格式
 *      pattern:
 *      store: 是否存储  默认不存储
 *      fielddata:
 *      searchAnalyzer: 指定字段搜索时使用的分词器
 *      analyzer: 指定字段建立索引时指定的分词器
 *      normalizer:
 *      ignoreFields: 是否忽略字段
 *      includeInParent: 是否解析
 *      copyTo:
 * @Author : kaituozhesh
 * @Date: 2020-04-07 21:41
 * @Version: 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "item", type = "docs", shards = 1, replicas = 0)
public class Item {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String title; //标题

    @Field(type = FieldType.Keyword)
    private String category;// 分类

    @Field(type = FieldType.Keyword)
    private String brand; // 品牌

    @Field(type = FieldType.Double)
    private Double price; // 价格

    @Field(index = false, type = FieldType.Keyword)
    private String images; // 图片地址
}
