package com.veso.util;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Nguyen Trong Tho
 * @version 1.0
 */

public class DateTime {
    private Timestamp ts;
    private Calendar cal;

    private int dd,mm,yyyy,hh,mi,sec;

    private String dayOfWeek; //Thu Hai,..

    public String getDayOfWeek() {return dayOfWeek; }
    public int getDay()    { return dd; }
    public int getMonth()  { return mm; }
    public int getYear()   { return yyyy; }
    public int getHour()   { return hh; }
    public int getMinute() { return mi; }
    public int getSecond() { return sec; }
    public Calendar getCalendar() { return cal; }

    public Timestamp getTimestamp() {
        return this.ts;
    }
    public void setTimestamp(Timestamp ts) {
        if (ts != null) {
            this.ts = ts; //millisec = tu 1970
            this.cal.setTime(new java.util.Date(ts.getTime()));
            //this.extractInfo();
        }
    }


    /**
     *  Note: call refresh whenever you change values of DataTime object
     */
    public void refresh() {
        this.ts = new Timestamp((cal.getTime()).getTime());
        this.extractInfo();
    }

    public void setDay(int day)     {
        cal.set(Calendar.DAY_OF_MONTH, day);
    }
    public void setMonth(int month) {
        cal.set(Calendar.MONTH, month-1);
    }
    public void setYear(int year)   {
        cal.set(Calendar.YEAR, year);
    }
    public void setHour(int hour)   {
        cal.set(Calendar.HOUR_OF_DAY, hour);
    }
    public void setMinute(int min)  {
        cal.set(Calendar.MINUTE, min);
    }
    public void setSecond(int sec)  {
        cal.set(Calendar.SECOND, sec);
    }

    public DateTime() { // use current date and time
        this.cal = Calendar.getInstance();
        this.ts  = new Timestamp((cal.getTime()).getTime());
        this.extractInfo();
    }

    public DateTime(Timestamp ts) {
        this.ts  = ts;
        this.cal = Calendar.getInstance();
        this.cal.setTime(new java.util.Date(ts.getTime()));
        this.extractInfo();
    }

    public DateTime(Calendar cal) {
        this.cal = cal;
        this.ts  = new Timestamp((cal.getTime()).getTime());
        this.extractInfo();
    }


    public static DateTime getInstance() {
        return new DateTime();
    }

    public static DateTime getInstance(Timestamp ts) {
        return new DateTime(ts);
    }

    public static DateTime getInstance(int year, int month, int day) {
        if (month > 0) month -= 1; // Month begin from 0 value
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return new DateTime(calendar);
    }


    // next day from day of this object
    public DateTime getNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, iDay + 1);
        return new DateTime(calendar);
    }


    // next day from day/month/year
    public static DateTime getNextDay(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day+1);
        return new DateTime(calendar);
    }

    public DateTime getPreviousDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, iDay - 1);
        return new DateTime(calendar);
    }

    // next day from day/month/year
    public static DateTime getPreviousDay(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day-1);
        return new DateTime(calendar);
    }

    private void extractInfo() {
        int d = cal.get(Calendar.DAY_OF_WEEK);
        switch (d) {
            case Calendar.MONDAY:
                dayOfWeek = "Thu Hai";
                break;
            case Calendar.TUESDAY:
                dayOfWeek = "Thu Ba";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = "Thu Tu";
                break;
            case Calendar.THURSDAY:
                dayOfWeek = "Thu Nam";
                break;
            case Calendar.FRIDAY:
                dayOfWeek = "Thu Sau";
                break;
            case Calendar.SATURDAY:
                dayOfWeek = "Thu Bay";
                break;
            case Calendar.SUNDAY:
                dayOfWeek = "Chu Nhat";
                break;
            default:
                dayOfWeek = "";
        }
        this.dd = cal.get(Calendar.DAY_OF_MONTH);
        this.mm = cal.get(Calendar.MONTH) + 1;
        this.yyyy = cal.get(Calendar.YEAR);

        this.hh = cal.get(Calendar.HOUR_OF_DAY);
        this.mi = cal.get(Calendar.MINUTE);
        this.sec = cal.get(Calendar.SECOND);
    }
    
    public static String extractInfo(Timestamp ts) {
        int d = ts.getDay()+1;
        String dayOfWeek = "";
        switch (d) {
        case Calendar.MONDAY:
            dayOfWeek = "Thứ Hai";
            break;
        case Calendar.TUESDAY:
            dayOfWeek = "Thứ Ba";
            break;
        case Calendar.WEDNESDAY:
            dayOfWeek = "Thứ Tư";
            break;
        case Calendar.THURSDAY:
            dayOfWeek = "Thứ Năm";
            break;
        case Calendar.FRIDAY:
            dayOfWeek = "Thứ Sáu";
            break;
        case Calendar.SATURDAY:
            dayOfWeek = "Thứ Bảy";
            break;
        case Calendar.SUNDAY:
            dayOfWeek = "Chủ Nhật";
            break;
        default:
            dayOfWeek = "";
        }
        return dayOfWeek;
    }
    
    public String FormatDate(String strDate){
        String strTemp = "";
        String[] arr = null;
        
        arr= strDate.split("-");
        strTemp = arr[2] + "/" + arr[1] + "/" + arr[0];
        
        return strTemp;
    }
    
    public static void main(String[] args) {
        DateTime dt = DateTime.getInstance(13, 4, 2007);
        System.out.println(dt.getDayOfWeek());
        System.out.println(dt.getDay());
        System.out.println(dt.getMonth());
        
        //String str = "";
        //str= FormatDate("2007-04-13");
    }
}
