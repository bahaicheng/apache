package common;

import com.beust.jcommander.Parameter;

/**
 * Created by DELL on 2017/11/22.
 */
public class Args {
    /**
     * required设置为true则表示此选项为必须的，否则为可选
     */
    @Parameter(names = "-kafkabrokerlist" ,required = true)
    public String brokerList;

    @Parameter(names = "-topic" ,required = false)
    public String topic = "kafkatopic";
}
