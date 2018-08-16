package com.boot.commons.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class ApacheDateUtils {

    public static void main(String[] args) throws  Exception{
        Date date = DateUtils.parseDate("2018/08/09 11:22:33", new String[]{"yyyy/MM/dd HH:mm:ss"});
        System.out.println(date);

        // 10天后
        Date tenDaysAfter = DateUtils.addDays(date, 10); // => 2010/01/11 11:22:33
        System.out.println(DateFormatUtils.format(tenDaysAfter, "yyyy/MM/dd HH:mm:ss"));

        // 前一个月
        Date prevMonth = DateUtils.addMonths(date, -1); // => 2009/12/01 11:22:33
        System.out.println(DateFormatUtils.format(prevMonth, "yyyy/MM/dd HH:mm:ss"));


        // 判断是否是同一天
        Date date1 = DateUtils.parseDate("2010/01/01 11:22:33", new String[]{"yyyy/MM/dd HH:mm:ss"});
        Date date2 = DateUtils.parseDate("2010/01/01 22:33:44", new String[]{"yyyy/MM/dd HH:mm:ss"});
        System.out.println(DateUtils.isSameDay(date1, date2));// true


        // 日期格式化
        System.out.println(DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));


//        DateConverter converter = new DateConverter();
//        converter.setPattern("yyyy/MM/dd HH:mm:ss");
//
//        ConvertUtils.register(converter, Date.class);
//        ConvertUtils.register(converter, String.class);

        Person  bean = new Person();
        BeanUtils.setProperty(bean,"date","2010/01/01 11:22:33");
        String value9 = BeanUtils.getProperty(bean, "date");
        System.out.println(value9);//"2010/12/19 23:40:00"
    }

}
