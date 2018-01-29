package hive;

import kerberos.KerberosUtil;

import java.sql.*;

public class HivePool {
    private static final String URLHIVE = "jdbc:hive2://192.168.121.66:10000/default";
    private static String drive = "org.apache.hive.jdbc.HiveDriver";
    private static String userName = "";
    private static String passWord = "";
    private static Connection connection = null;

    public static Connection getHiveConnection(){
        if(null == connection){
            synchronized (HivePool.class){
                try {
                    Class.forName(drive);
                    connection = DriverManager.getConnection(URLHIVE,userName,passWord);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }
    
    public static void main(String[] args){
//        KerberosUtil.kerberosAuth();
        String sql = "load data inpath 'hdfs://nameservice1/user/batch/........'";
        String querysql = "select * from new_emp";
        try {
//            PreparedStatement preparedStatement = getHiveConnection().prepareStatement(sql);
            Statement stmt = getHiveConnection().createStatement();
//            boolean execute = stmt.execute(sql);
            ResultSet rs = stmt.executeQuery(querysql);
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
