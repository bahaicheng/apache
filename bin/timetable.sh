#!/bin/sh

java -cp /opt/jars/timetable-0.0.1-SNAPSHOT.jar spdb.timetable.entrance.Holiday \
-conffilepath /opt/jars/utms.properties \
-starttime 20180101 \
-endtime 20181231 \
-lastworkdate 20171229 \
-nextworkdate 20190102 \
-dburl jdbc:mysql://bourne:3306/holiday \
-dbuser root \
-dbpwd root123 \
-dbname mysql