package com.magooup.learn.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * Created by Magoo on 2016/8/24.
 */
public class LearnSpark {
    public static void main(String[] args) {
        String logFile = "/home/workspace/data/spark/test.txt";
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("spark://192.168.80.131:7077");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter((Function<String, Boolean>) s -> s.contains("a")).count();

        long numBs = logData.filter((Function<String, Boolean>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
