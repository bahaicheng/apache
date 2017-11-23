package flink;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * Created by DELL on 2017/11/22.
 */
public class FlinkBatch {
    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.createLocalEnvironment();
//        readTextFile(env);

    }


    public static void readTextFile(ExecutionEnvironment env) {
        DataSet<String> data = env.readTextFile("D:\\data\\people.txt");

        data.filter(new FilterFunction<String>() {
            public boolean filter(String value) {
                return value.contains("Michael");
            }
        })
                .writeAsText("D:\\flink");
        try {
            JobExecutionResult res = env.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
