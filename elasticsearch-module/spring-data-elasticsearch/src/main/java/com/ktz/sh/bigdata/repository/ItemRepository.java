package com.ktz.sh.bigdata.repository;

import com.ktz.sh.bigdata.document.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 继承子接口就能具备各种基本的CRUD功能
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    /**
     * 自定义查询：根据价格区间查询
     *
     * @param price1
     * @param price2
     * @return
     */
    List<Item> findByPriceBetween(double price1, double price2);
}
