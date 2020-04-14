package com.ktz.sh.bigdata.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frameworkset.orm.annotation.Column;
import com.frameworkset.orm.annotation.ESId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frameworkset.elasticsearch.entity.ESBaseData;

import java.util.Date;

/**
 * @ClassName Example
 * @Description
 * @Author kaituozhesh
 * @Date 2020/4/10 9:05
 * @Version V1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Example{

    @ESId(readSet = true, persistent = false)
    private Long demoId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(dataformat = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date agentStartTime;

    private String applicationName;

    private String contentBody;

    private String name;

    private String orderId;

    private int contrastStatus;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//    @Column(dataformat = "yyyy-MM-dd HH:mm:ss.SSS")
//    protected Date agentStarttimezh;




}
