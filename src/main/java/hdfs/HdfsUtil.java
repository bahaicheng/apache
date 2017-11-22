package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by DELL on 2017/11/22.
 */
public class HdfsUtil {
    public static void main(String[] args){
        readHdfsFile();
    }

    public static void readHdfsFile(){
        String filePath ="hdfs://bourne:8020/text/json.txt";
        Configuration conf = new Configuration();
        Path srcPath = new Path(filePath);
        FileSystem fs = null;
        URI uri;
        try {
            uri = new URI(filePath);
            fs = FileSystem.get(uri, conf);
            InputStream in = null;
            in = fs.open(srcPath);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
