package timetable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;

/**
 * @author DELL
 */
public class HolidayTbl {

    private static Logger logger = LogManager.getLogger(HolidayTbl.class);

    private static List<String> hlist;
    private static List<String> wlist;

    public static void main(String[] args) {
        logger.info("--------START--------");
        InitArgs argv = new InitArgs();
        /*String[] arg = { "-conffilepath", "D:\\utms.properties", "-starttime", "20180101", "-endtime", "20181231",
				"-lastworkdate", "20171229", "-nextworkdate", "20190102", "-dburl", "jdbc:mysql://bourne:3306/holiday",
				"-dbuser", "root", "-dbpwd", "root123", "-dbname", "mysql" };*/
        JCommander.newBuilder().addObject(argv).build().parse(args);

        ReadFile rf = new ReadFile(argv.FILEPATH);
        ExternalDataEntity ede = rf.readFileByChars();
        hlist = ede.getHlist();
        wlist = ede.getWlist();
        holidayDate(argv);
        logger.info("--------END--------");
    }

    /**
     * 计算节假日，写入数据库
     *
     * @param args
     */
    public static void holidayDate(InitArgs args) {
        String driver = "";
        if ((args.DBNAME.toLowerCase()).equals("mysql")) {
            logger.info("--------Create_MysqlDatabase_Connection--------");
            driver = args.MYSQLDBDRIVER;
        } else if ((args.DBNAME.toLowerCase()).equals("oracle")) {
            logger.info("--------Create_OracleDatabase_Connection--------");
            driver = args.ORACLEDBDRIVER;
        }

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(args.DBURL, args.DBUSER, args.DBPWD);
            if (!conn.isClosed())
                logger.info("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            Date start = sdf.parse(args.STARTTIME);// 开始时间
            Date end = sdf.parse(args.ENDTIME);// 结束时间

            List<Date> lists = dateSplit(start, end);

            Date nextWorkDate = sdf.parse(args.NEXTWORKDATE);// 下一个工作日，默认1月2日，1日为元旦
            Date lastWorkDate = sdf.parse(args.LASTWORKDATE);// 上一个工作日

            logger.info("--------StartTime:" + args.STARTTIME + "--------");
            logger.info("--------EndTime:" + args.ENDTIME + "--------");

            logger.info("--------LastWorkDate:" + args.LASTWORKDATE + "--------");
            logger.info("--------NextWorkDate:" + args.NEXTWORKDATE + "--------");

            logger.info("--------Holidays:" + hlist.toString() + "--------");
            logger.info("--------WorkDays:" + wlist.toString() + "--------");

            if (!lists.isEmpty()) {
                for (Date date : lists) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    int day = cal.get(Calendar.DATE);
                    int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

                    String monthStr = "", dayStr = "";
                    if (month / 10 == 0) {
                        monthStr = "0" + String.valueOf(month);
                    } else {
                        monthStr = String.valueOf(month);
                    }
                    if (day / 10 == 0) {
                        dayStr = "0" + String.valueOf(day);
                    } else {
                        dayStr = String.valueOf(day);
                    }

                    lastWorkDate = getLastWorkDay(date);

                    // T_CMM_TCLD表字段：年，月，日，日期，标识（1为假日，0为工作日），周几，上一个工作日，下一个工作日
                    String insertSql = "INSERT INTO HOLIDAY_TBL (HOL_YEAR, HOL_MONTH, HOL_DAY, HOL_DATE, HOL_FLAG, HOL_WEEK, LAST_WK_DT, NEXT_WK_DT) "
                            + "VALUES('" + year + "','" + monthStr + "','" + dayStr + "','" + sdf.format(date) + "','"
                            + isHoliday(date) + "','" + week + "','" + sdf.format(lastWorkDate) + "','"
                            + sdf.format(nextWorkDate) + "')";
                    // logger.info(year+","+monthStr+","+dayStr+","+sdf.format(date));
                    // logger.info(insertSql);
                    statement.execute(insertSql);
                    if (isHoliday(date) == 0) {
                        nextWorkDate = date;
                    }
                }
            }
            conn.close();
        } catch (Exception e) {
            logger.error("Calculation_And_WriteToDatabase:" + e);
        }
    }

    /**
     * 根据开始时间和结束时间计算天数
     *
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    private static List<Date> dateSplit(Date start, Date end) throws Exception {
        if (!start.before(end))
            throw new Exception("开始时间应该在结束时间之后");
        Long spi = end.getTime() - start.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(end);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime() - (24 * 60 * 60 * 1000)));// 比上一天减一
        }
        return dateList;
    }

    /**
     * 判断是否为节假日，若是返回1，否则返回0
     *
     * @param date
     * @return
     */
    private static int isHoliday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        List<String> holidays = getHolidays();
        List<String> workdays = getWorkDays();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // System.out.println(sdf.format(date));
        if (((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                && !workdays.contains(sdf.format(date))) || holidays.contains(sdf.format(date))) {
            return 1;
        }
        return 0;
    }

    /**
     * 获取上一个工作日
     *
     * @param date
     * @return
     */
    private static Date getLastWorkDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date dateTemp = cal.getTime();

        while (isHoliday(dateTemp) != 0) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            dateTemp = cal.getTime();
        }
        return dateTemp;
    }

    /**
     * 获取节假日日期
     *
     * @return
     */
    private static List<String> getHolidays() {
        List<String> holidays = new ArrayList<String>();
        holidays.addAll(hlist);
        return holidays;
    }

    /**
     * 获取补班日期
     *
     * @return
     */
    private static List<String> getWorkDays() {
        List<String> workDays = new ArrayList<String>();
        workDays.addAll(wlist);
        return workDays;
    }
}
