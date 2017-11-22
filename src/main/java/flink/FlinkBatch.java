package flink;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * Created by DELL on 2017/11/22.
 */
public class FlinkBatch {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.createLocalEnvironment();

        DataSet<String> data = env.readTextFile("D:\\data\\people.txt");

        data.filter(new FilterFunction<String>() {
            public boolean filter(String value) {
                return value.contains("Michael");
            }
        })
                .writeAsText("D:\\flink");

        JobExecutionResult res = env.execute();
    }
}
