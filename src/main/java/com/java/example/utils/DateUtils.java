package com.java.example.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 *
 * @Auther: L.C
 * @Date: 2020-04-03 13:52
 */
public class DateUtils {

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文最简写（默认）如：20101201
     */
    public static String FORMAT_MORE_SHORT = "yyyyMMdd";

    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 英文全称 如：2010-12-01 23:15
     */
    public static String FORMAT_CAR_DATE = "yyyy-MM-dd HH:mm";

    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写 如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间 yyyy年MM月dd日 HH时mm分ss秒SSS毫秒
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 精确到秒的完整中文最简时间 yyyyMMddHHmmss
     */
    public static String FORMAT_FULL_SIMPLE = "yyyyMMddHHmmss";

    /**
     * 精确到秒的完整中文最简时间 HHmmss 如：100101
     */
    public static String FORMAT_TIME_FULL = "HHmmss";

    /**
     * 精确到毫秒的完整中文最简时间 如：20150304230516221
     */
    public static String FORMAT_TIME_FULL_MILLI = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式正则表达式
     */
    public static String DATA_PATTERN = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

    /**
     * 短格式日期变长格式日期附加字符串，用于补充开始查询时间
     */
    public static String ADDTIONAL_STR_BEGAIN = "2000-01-01 00:00:00";

    /**
     * 短格式日期变长格式日期附加字符串，用于补充结束查询时间
     */
    public static String ADDTIONAL_STR_END = "2000-01-01 23:59:59";


    /**
     * 按指定格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     * @Date: 2020-04-03 13:56
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     * @Date: 2020-04-03 13:56n
     */
    public static Date parse(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     * @Date: 2020-04-03 13:56
     */
    public static String date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return:
     * @Date: 2020-04-03 13:56
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return:
     * @Date: 2020-04-03 13:57
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 在当前日期上添加小时数
     *
     * @param date 当前日期
     * @param hour 增加的小时数
     * @return:
     * @Date: 2020-04-03 13:57
     */
    public static Date addHour(Date date, Double hour) {
        Long time = date.getTime();
        Double times = time + hour * 60 * 60 * 1000;
        return new Date(Math.round(times));
    }

    /**
     * 在当前日期上减去小时数
     *
     * @param date 当前日期
     * @param hour 增加的小时数
     * @return:
     * @Date: 2020-04-03 13:59
     */
    public static Date subHour(Date date, Double hour) {
        Long time = date.getTime();
        Double times = time - hour * 60 * 60 * 1000;
        return new Date(Math.round(times));
    }

    /**
     * 在当前日期上添加分钟数
     *
     * @param date 当前日期
     * @param mins 增加的分钟数
     * @return:
     * @Date: 2020-04-03 13:57
     */
    public static Date addMinute(Date date, int mins) {
        Long time = date.getTime();
        double times = time + (double) mins * 60 * 1000;
        return new Date(Math.round(times));
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return:
     * @Date: 2020-04-03 14:03
     */
    public static Date parse(String strDate) {
        return parse(strDate, FORMAT_LONG);
    }

    /**
     * 计算两个日期的月份数(格式:yyyy-MM-dd HH:mm:ss)
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return:
     * @Date: 2020-04-03 14:03
     */
    public static int countMonth(String start, String end) {
        int month = 0;
        int year = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(parse(start));
        cal2.setTime(parse(end));
        if (cal1.after(cal2)) {
            Calendar temp = cal1;
            cal1 = cal2;
            cal2 = temp;
        }
        year = Math.abs(cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR));
        month = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        return year * 12 + month;
    }

    /**
     * 计算两个日期的月份数(格式为yyyy-MM-dd)
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return:
     * @Date: 2020-04-03 14:04
     */
    public static int countMonth(Date start, Date end) {
        int month = 0;
        int year = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(start);
        cal2.setTime(end);
        if (cal1.after(cal2)) {
            Calendar temp = cal1;
            cal1 = cal2;
            cal2 = temp;
        }
        year = Math.abs(cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR));
        month = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        return year * 12 + month;
    }

    /**
     * 计算两个日期的间隔天数
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return:
     * @Date: 2020-04-03 14:02
     */
    public static int countDays(Date start, Date end) {
        long t1 = start.getTime();
        long t2 = end.getTime();
        return (int) ((t2 / 1000 - t1 / 1000) / 3600 / 24);
    }

    /**
     * 计算间隔小时数 -带半小时(不足半小时按照半小时 超过半小时按照一个小时算)
     *
     * @param start
     * @param end
     * @return:
     * @Date: 2020-04-03 14:05
     */
    public static double countHour(Date start, Date end) {
        long t1 = start.getTime();
        long t2 = end.getTime();
        int hour = (int) ((t2 / 1000 - t1 / 1000) / 3600);
        double halfHour = 0;
        if ((countMins(start, end) - hour * 60) > 30) {
            halfHour = 1;
        } else if ((countMins(start, end) - hour * 60) <= 30 && (countMins(start, end) - hour * 60) > 0) {
            halfHour = 0.5;
        }
        return hour + halfHour;
    }


    /**
     * 计算两个日期的间隔分钟数
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return:
     * @Date: 2020-04-03 14:06
     */
    public static int countMins(Date start, Date end) {
        start = parse(format(start, FORMAT_CAR_DATE) + ":00", FORMAT_LONG);
        end = parse(format(end, FORMAT_CAR_DATE) + ":00", FORMAT_LONG);
        long t1 = start.getTime();
        long t2 = end.getTime();
        return (int) ((t2 - t1) / 1000 / 60);
    }

    /**
     * 获取日期的前num天日期
     * 如：2016-6-17 HH:mm:ss 前30天 2016-5-18 HH:mm:ss
     *
     * @param date 日期
     * @param num  天数
     * @return:
     * @Date: 2020-04-03 14:09
     */
    public static Date getDateBefore(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - num);
        return calendar.getTime();
    }

    /**
     * 获取日期的后num天日期
     * 如：2016-5-18 HH:mm:ss 后30天 2016-6-17 HH:mm:ss
     *
     * @param date 日期
     * @param num  天数
     * @return:
     * @Date: 2020-04-03 14:09
     */
    public static Date getDateAfter(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + num);
        return calendar.getTime();
    }

    /**
     * 两个时间点的时间差(秒)
     *
     * @return
     * @throws ParseException
     * @param: mintime  最小时间
     * @param: maxtime  最大时间
     * @return:
     * @Date: 2020-04-03 14:09
     */
    public static int diffSecond(String mintime, String maxtime) throws ParseException {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = dfs.parse(mintime);
        Date end = dfs.parse(maxtime);
        // 除以1000是为了转换成秒
        long between = (end.getTime() - begin.getTime()) / 1000;
        return new Long(between).intValue();
    }

    /**
     * 获取指定日期的0:0:0
     *
     * @param date 日期
     * @return
     * @Date: 2020-04-03 14:06
     */
    public static Date getYesterFastDate(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.HOUR_OF_DAY, 0);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        cl.set(Calendar.MILLISECOND, 0);
        return cl.getTime();
    }

    /**
     * 获取指定天数的23:59:59.999
     *
     * @param date 日期
     * @return
     * @Date: 2020-04-03 14:06
     */
    public static Date getYesterLastDate(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.SECOND, 59);
        cl.set(Calendar.MILLISECOND, 999);
        return cl.getTime();
    }

    /**
     * 昨天0点
     *
     * @return:
     * @Date: 2020-04-03 14:06
     */
    public static Date getYester0Date() {
        return parse(format(addDay(new Date(), -1), FORMAT_SHORT), FORMAT_SHORT);
    }

    /**
     * 昨天24点
     *
     * @return:
     * @Date: 2020-04-03 14:07
     */
    public static Date getYester24Date() {
        return parse(format(new Date(), FORMAT_SHORT), FORMAT_SHORT);
    }


    /**
     * 验证字符串是否为日期格式
     *
     * @param strDate 字符串日期
     * @return:
     * @Date: 2020-04-03 14:08
     */
    public static boolean isDateFormat(String strDate) {
        if (strDate == null)
            return Boolean.FALSE;
        Pattern pattern = Pattern.compile(DATA_PATTERN);
        Matcher matcher = pattern.matcher(strDate);
        return matcher.matches();
    }

    /**
     * 判断thisDate 与 thatDate 大小关系
     *
     * @param thisDate 第一个日期
     * @param thatDate 第二个日期
     * @return:
     * @Date: 2020-04-03 14:23
     */
    public static boolean greaterThan(Date thisDate, Date thatDate) {
        if (thisDate.getTime() > thatDate.getTime()) {
            return true;
        }

        if (thisDate.getTime() == thatDate.getTime()) {
            return false;
        }
        return false;
    }

    /**
     * 获取本月第一天
     *
     * @return:
     * @Date: 2020-04-24
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @return:
     * @Date: 2020-04-24
     */
    public static Date getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取下个月第一天
     *
     * @return:
     * @Date: 2020-04-24
     */
    public static Date getNextMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获得某天最大时间 2020-02-19 23:59:59
     *
     * @return:
     * @Date: 2020-07-30
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2020-02-17 00:00:00
     *
     * @return:
     * @Date: 2020-07-30
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取给定日期的明天凌晨时间
     * 如: 2020-07-30 23:22:34 -> 2020-07-31 00:00:00
     *
     * @return:
     * @Date: 2020-07-30
     */
    public static Date getTomorrowStart(Date date) {
        date = addDay(date, 1);
        return getStartOfDay(date);
    }

    /**
     * 当前日期前几天
     *
     * @return:
     * @Date: 2020-08-17
     */
    public static Date getBeforeDays(Date date, int day) {
        Calendar no = Calendar.getInstance();
        no.setTime(date);
        no.set(Calendar.DATE, no.get(Calendar.DATE) - day);
        return no.getTime();
    }


}
