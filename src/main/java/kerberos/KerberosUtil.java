package kerberos;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

public class KerberosUtil {
    private static Configuration conf = new Configuration();

    public static Configuration getConf(){
        return  conf;
    }

    public static void setConf(Configuration conf){
        KerberosUtil.conf = conf;
    }

    public static void kerberosAuth(){
        System.setProperty("java.security.krb5.conf","/etc/bigf/krb5.conf");
        conf.set("hadoop.security.authentication","Kerberos");
        conf.set("java.security.krb5.relams","MCIPT.COM");
        UserGroupInformation.setConfiguration(conf);
        try {
            UserGroupInformation.loginUserFromKeytab("bigf_hdfs_admin","/etc/bigf/bigf_hdfs_admin.keytab");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
