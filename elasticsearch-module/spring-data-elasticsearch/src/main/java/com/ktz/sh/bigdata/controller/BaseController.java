package com.ktz.sh.bigdata.controller;

import com.ktz.sh.bigdata.document.Item;
import com.ktz.sh.bigdata.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName : ElasticController
 * @Description : 测试ElasticsearchTemplate
 * @Author : kaituozhesh
 * @Date: 2020-04-07 21:43
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private ItemRepository itemRepository;

    /**
     * 创建索引
     *
     * @return
     */
    @PostMapping("/create_index")
    public Object createIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        boolean index = elasticsearchTemplate.createIndex(Item.class);

        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        boolean mapping = elasticsearchTemplate.putMapping(Item.class);
        return index && mapping ? "success" : "fail";
    }

    /**
     * 往索引中添加数据
     *
     * @return
     */
    @PostMapping("/import_data")
    public Object importData() {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        Item save = itemRepository.save(item);
        return save;
    }

    /**
     * 根据ID修改数据   没有对应ID的数据就是新增，  有对应的就是修改
     *
     * @return
     */
    @PostMapping("/update_data")
    public Object updateData() {
        Item item = new Item(1L, "小米手机7777", " 手机",
                "小米", 9499.00, "http://image.leyou.com/13123.jpg");
        Item save = itemRepository.save(item);
        return save;
    }

    /**
     * 批量新增
     *
     * @return
     */
    @PostMapping("/import_list")
    public Object indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        Iterable<Item> items = itemRepository.saveAll(list);
        return items;
    }

    /**
     * 根据ID删除
     *
     * @return
     */
    @PostMapping("/delete_data")
    public Object deleteData() {
        itemRepository.deleteById(1L);
        return "success";
    }

    /**
     * 根据ID查询
     *
     * @return
     */
    @PostMapping("/find_data")
    public Object findData() {
        Optional<Item> optional = itemRepository.findById(2L);
        return optional.get();
    }


    /**
     * 查询全部
     *
     * @return
     */
    @PostMapping("/find_list")
    public Object findList() {
        Iterable<Item> all = itemRepository.findAll();
        return all;
    }

    /**
     * 自定义查询
     *
     * @return
     */
    @PostMapping("/find_price_between")
    public Object findPriceBetween() {
        return itemRepository.findByPriceBetween(2000.00, 3500.00);
    }


}