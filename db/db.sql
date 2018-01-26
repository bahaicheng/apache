#mysql 普通索引
create table HOLIDAY_TBL(
HOL_YEAR varchar(32),
HOL_MONTH varchar(32),
HOL_DAY varchar(32),
HOL_DATE varchar(32),
HOL_FLAG varchar(32),
HOL_WEEK varchar(32),
LAST_WK_DT varchar(32),
NEXT_WK_DT varchar(32),
INDEX (HOL_DATE,HOL_FLAG)
);

#唯一性索引
create table HOLIDAY_TBL(
HOL_YEAR varchar(32),
HOL_MONTH varchar(32),
HOL_DAY varchar(32),
HOL_DATE varchar(32) UNIQUE,
HOL_FLAG varchar(32),
HOL_WEEK varchar(32),
LAST_WK_DT varchar(32),
NEXT_WK_DT varchar(32),
UNIQUE INDEX HOLIDAY_TBL_HOL_DATE (HOL_DATE)
);

#全局索引、单列索引、多列索引


//T_CMM_TCLD表字段：年，月，日，日期，标识(Identification)（1为假日，0为工作日），周几，上一个工作日，下一个工作日
String insertSql = "INSERT INTO T_CMM_TCLD (CLD_YEAR, CLD_MONTH, CLD_DAY, CLD_DATE, CLD_FLG, WEEK, LAST_WK_DT, NEXT_WK_DT) " +
 "VALUES('"+year+"','"+monthStr+"','"+dayStr+"','"+sdf.format(date)+"','"+isHoliday(date)+"','"+week+"','"+sdf.format(lastWorkDate)+"','"+sdf.format(nextWorkDate)+"')";