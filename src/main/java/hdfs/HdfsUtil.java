package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.glassfish.jersey.process.internal.Stages;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/11/22.
 */
public class HdfsUtil {
    public static void main(String[] args) {
        readHdfsFile();
    }

    public static void readHdfsFile() {
        String filePath = "hdfs://bourne:8020/file/json.txt";
        Configuration conf = new Configuration();
        Path srcPath = new Path(filePath);
        FileSystem fs = null;
        URI uri;
        try {
            uri = new URI(filePath);
            fs = FileSystem.get(uri, conf);
            InputStream in = fs.open(srcPath);
            /**
             * 第二个参数可以是输出流FSDataOutputStream out=null;
             * Path block=new Path("hdfs://mycentos4:9000/tv/"+nameString.replace("-", "")+".txt");
             *  //打开输出流
             if(!(fs.exists(block))){
             out=fs.create(block);
             }else {
             out=fs.append(block);
             }
             *  System.out可替换成out
             */
            byte[] bytes = new byte[2048];
            List<String> list = new ArrayList<>();
            while (in.read(bytes) != -1) {
                String s = new String(bytes, "gbk");
                list.add(s);

            }
            for (String l : list) {
                System.out.println(l);
            }

//            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
