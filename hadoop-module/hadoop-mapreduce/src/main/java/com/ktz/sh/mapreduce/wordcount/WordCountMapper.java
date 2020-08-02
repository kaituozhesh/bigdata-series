package com.ktz.sh.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @ClassName : WordCountMapper
 * @Description :
 * @Author : kaituozhesh
 * @Date: 2020-08-02 22:00
 * @Version: 1.0.0
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text k = new Text();
    IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1. 获取一行数据
        String line = value.toString();

        // 2. 切割
        String[] words = line.split(" ");

        // 3. 输出
        for (String word : words) {
            k.set(word);
            context.write(k, v);
        }


    }
}
