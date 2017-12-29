package hive;

import kerberos.KerberosUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class HivePool {
    private static final String URLHIVE = "jdbc:hive2://vmsit-master3:10000/default;principal=hive/vmsit-master.spdbbiz.com@MCIPT.COM";
    private static Connection connection = null;

    public static Connection getHiveConnection(){
        if(null == connection){
            synchronized (HivePool.class){
                try {
                    Class.forName("org.apache.hive.jdbc.HiveDriver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }
    
    public static void main(String[] args){
        KerberosUtil.kerberosAuth();
        String sql = "load data inpath 'hdfs://nameservice1/user/batch/........'";

        try {
//            PreparedStatement preparedStatement = getHiveConnection().prepareStatement(sql);
            Statement statement = getHiveConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
