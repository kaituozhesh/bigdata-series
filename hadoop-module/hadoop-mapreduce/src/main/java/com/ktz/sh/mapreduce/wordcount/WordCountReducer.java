package com.ktz.sh.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @ClassName : WordCountReducer
 * @Description :
 * 参数一： 输入数据key类型
 * 参数二： 输入数据value类型
 * 参数三： 最终输出数据key类型
 * 参数四： 最终输出数据value类型
 * @Author : kaituozhesh
 * @Date: 2020-08-02 22:02
 * @Version: 1.0.0
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    IntWritable v = new IntWritable();

    /**
     *
     * @param key
     * @param values key相同的 同一组数据
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        // 1. 累加求和
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }

        // 2. 输出
        v.set(sum);
        context.write(key, v);
    }
}