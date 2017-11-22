package hdfs

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.IOUtils

/**
 * Created by DELL on 2017/11/22.
 */
object HdfsUtilfs {
    def main(args: Array[String]) {
        readHdfs()
    }

    def readHdfs(): Unit ={
        val path = "hdfs://bourne:8020/text/json.txt"
        val conf = new Configuration()
        val srcPath = new Path(path)
        val fs = null;
        try {
            val uri =  new URI(path)
            val fs = FileSystem.get(uri,conf)
            val in = fs.open(srcPath)
            IOUtils.copyBytes(in, System.out, 4096, false);
        }
        catch {
            case ex : Exception => {
                println(ex)
            }
        }

    }
}
