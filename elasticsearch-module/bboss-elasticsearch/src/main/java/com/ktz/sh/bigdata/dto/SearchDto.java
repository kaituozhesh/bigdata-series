package com.ktz.sh.bigdata.dto;

import lombok.Data;

/**
 * @ClassName SearchDto
 * @Description
 * @Author kaituozhesh
 * @Date 2020/4/14 14:51
 * @Version V1.0.0
 **/
@Data
public class SearchDto {

    private String name;

    private Integer contrastStatus;

    private Integer from;

    private Integer size;
}
