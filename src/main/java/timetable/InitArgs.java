package timetable;

import com.beust.jcommander.Parameter;

public class InitArgs {
    /**
     * required设置为true则表示此选项为必须的，否则为可选
     */
    @Parameter(names = "-conffilepath", required = true)
    public String FILEPATH;

    /**
     * 开始时间为新一年的第一天,例：20180101
     */
    @Parameter(names = "-starttime", required = true)
    public String STARTTIME;

    /**
     * 开始时间为新一年的最后一天,例：20181231
     */
    @Parameter(names = "-endtime", required = true)
    public String ENDTIME;

    /**
     * 上一个工作日,以2018为例：20171229
     */
    @Parameter(names = "-lastworkdate", required = true)
    public String LASTWORKDATE;

    /**
     * 下一个工作日,以2018为例：20190102
     */
    @Parameter(names = "-nextworkdate", required = true)
    public String NEXTWORKDATE;

    /**
     * DB_URL
     * String url = "jdbc:mysql://bourne:3306/holiday";
     * String  url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
     */
    @Parameter(names = "-dburl", required = true)
    public String DBURL;

    /**
     * DB_USER
     */
    @Parameter(names = "-dbuser", required = true)
    public String DBUSER;

    /**
     * DB_PASSWORD
     */
    @Parameter(names = "-dbpwd", required = true)
    public String DBPWD;

    /**
     * MYSQL_DRIVER
     */
    @Parameter(names = "-mysqldriver", required = false)
    public String MYSQLDBDRIVER = "com.mysql.jdbc.Driver";

    /**
     * ORACLE_DRIVER
     */
    @Parameter(names = "-oracledriver", required = false)
    public String ORACLEDBDRIVER = "oracle.jdbc.driver.OracleDriver";

    /**
     * DB_NAME 例：mysql or oracle
     */
    @Parameter(names = "-dbname", required = true)
    public String DBNAME;
}
