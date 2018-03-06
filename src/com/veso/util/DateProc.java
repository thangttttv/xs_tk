package com.veso.util;

/**
 * @author Nguyen Trong Tho
 * @version 1.0
 */

import java.sql.Timestamp;
import java.util.Calendar;

public class DateProc {
    public DateProc() {}

    public static java.sql.Timestamp createTimestamp() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        return new java.sql.Timestamp( (calendar.getTime()).getTime());
    }

    public static java.sql.Timestamp createDateTimestamp(java.util.Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        return new java.sql.Timestamp( (calendar.getTime()).getTime());
    }

    public static java.sql.Timestamp String2Timestamp(String strInputDate) {
        String strDate = strInputDate;
        int i, nYear, nMonth, nDay;
        String strSub = null;
        i = strDate.indexOf("/");
        if (i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if (i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        nMonth = (new Integer(strSub.trim())).intValue() - 1; // Month begin from 0 value
        strDate = strDate.substring(i + 1);
        if (strDate.length() < 4) {
            if (strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        }
        nYear = (new Integer(strDate)).intValue();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new java.sql.Timestamp( (calendar.getTime()).getTime());
    }

    //dd/mm/yyyy h24:mi
    public static java.sql.Timestamp buildTimestamp(int year, int month, int day) {
        if (month > 0) month -= 1; // Month begin from 0 value
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month, day);
        return new java.sql.Timestamp( (calendar.getTime()).getTime());
    }

    //dd/mm/yyyy h24:mi
    public static java.sql.Timestamp buildTimestamp(int year, int month, int day, int hour24, int minute) {
        if (month > 0) month -= 1; // Month begin from 0 value
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month, day, hour24, minute);
        return new java.sql.Timestamp( (calendar.getTime()).getTime());
    }

    public static String getDateTimeString(java.sql.Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    /*return date with format: dd/mm/yyyy */
    public static String getDateString(java.sql.Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts);
    }

    public static String getTimeString(java.sql.Timestamp ts) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" +
            calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }

    /* Format date : yyyymmdd
     * by Nguyen Trong Tho
     */
    public static String Timestamp2YYYYMMDD(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // DD
        sDay = "" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
            sDay = "0" + sDay;
            // MM
        if (calendar.get(Calendar.MONTH) + 1 < 10) {
            sMonth = "0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            sMonth = ""  + (calendar.get(Calendar.MONTH) + 1);
        }
        // YYYY
        sYear = "" + calendar.get(Calendar.YEAR);

        return sYear + sMonth + sDay;
    }

    public static String vnTimestamp2YYYYMMDD(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // DD
        sDay = "" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
            sDay = "0" + sDay;
            // MM
        if (calendar.get(Calendar.MONTH) + 1 < 10) {
            sMonth = "0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            sMonth = ""  + (calendar.get(Calendar.MONTH) + 1);
        }
        // YYYY
        sYear = "" + calendar.get(Calendar.YEAR);
        sYear = "" + sYear.substring(sYear.length()-2,sYear.length());

        return sDay + "/" + sMonth + "/" + sYear;
    }
    
    /* Format date : yyyy<seperator>mm<seperator>dd
     * by Nguyen Trong Tho
     */
    public static String Timestamp2YYYYMMDD(java.sql.Timestamp ts, String seperator) {
        if (ts == null) {
            return "";
        }
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // DD
        sDay = "" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
            sDay = "0" + sDay;
            // MM
        if (calendar.get(Calendar.MONTH) + 1 < 10) {
            sMonth = "0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            sMonth = ""  + (calendar.get(Calendar.MONTH) + 1);
        }
        // YYYY
        sYear = "" + calendar.get(Calendar.YEAR);

        return sYear + seperator + sMonth + seperator + sDay;
    }


    /*return date with format: dd/mm/yyyy */
    public static String Timestamp2DDMMYYYY(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(Calendar.
                DAY_OF_MONTH));
            if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
                strTemp = "0" + strTemp;
            if (calendar.get(Calendar.MONTH) + 1 < 10) {
                return strTemp + "/0" + (calendar.get(Calendar.MONTH) + 1) +
                    "/" + calendar.get(Calendar.YEAR);
            } else {
                return strTemp + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" +
                    calendar.get(Calendar.YEAR);
            }
        }
    }

    /*return date with format: dd/mm/yyyy */
    public static String Timestamp2DDMM(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
                strTemp = "0" + strTemp;
            
            if (calendar.get(Calendar.MONTH) + 1 < 10) {
                return strTemp + "/0" + (calendar.get(Calendar.MONTH) + 1);
            } else {
                return strTemp + "/" + (calendar.get(Calendar.MONTH) + 1);
            }
        }
    }

    /*return date with format: dd/mm/yy */
    public static String Timestamp2DDMMYY(java.sql.Timestamp ts) {
        int endYear;
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(Calendar.
                DAY_OF_MONTH));
            endYear = calendar.get(Calendar.YEAR) % 100;
            if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
                strTemp = "0" + strTemp;
            if (calendar.get(Calendar.MONTH) + 1 < 10) {
                if (endYear < 10)
                    return strTemp + "/0" + (calendar.get(Calendar.MONTH) + 1) +
                        "/0" + endYear;
                else
                    return strTemp + "/0" + (calendar.get(Calendar.MONTH) + 1) +
                        "/" + endYear;
            } else {
                if (endYear < 10)
                    return strTemp + "/" + (calendar.get(Calendar.MONTH) + 1) +
                        "/0" + endYear;
                else
                    return strTemp + "/" + (calendar.get(Calendar.MONTH) + 1) +
                        "/" + endYear;
            }
        }
    }

    /**
     * Format time: HH:MM (24H)
     * by Nguyen Thien Phuong
     */
    public static String Timestamp2HHMM(java.sql.Timestamp ts) {
        if (ts == null)
            return "";

        String sHour = "";
        String sMinunute = "";

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // HH
        if (calendar.get(Calendar.HOUR_OF_DAY) < 10)
            sHour = "0" + calendar.get(Calendar.HOUR_OF_DAY);
        else
            sHour = "" + calendar.get(Calendar.HOUR_OF_DAY);
        //MM
        if (calendar.get(Calendar.MINUTE) < 10)
            sMinunute = "0" + calendar.get(Calendar.MINUTE);
        else
            sMinunute = "" + calendar.get(Calendar.MINUTE);
        return (sHour + ":"+ sMinunute);
    }
    
    /**
     * 24 hour time: HHMMSS
     * by Nguyen Thien Phuong
     */
    public static String Timestamp2HHMMSS(java.sql.Timestamp ts) {
        if (ts == null)
            return "";

        String sHour = "";
        String sMinunute = "";
        String sSecond = "";

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // HH
        if (calendar.get(Calendar.HOUR_OF_DAY) < 10)
            sHour = "0" + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        else
            sHour =  "" + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        //MM
        if (calendar.get(Calendar.MINUTE) < 10)
            sMinunute = "0" + calendar.get(Calendar.MINUTE);
        else
            sMinunute = "" + calendar.get(Calendar.MINUTE);
        //SS
        if (calendar.get(Calendar.SECOND) < 10)
            sSecond = "0" + calendar.get(Calendar.SECOND);
        else
            sSecond = "" + calendar.get(Calendar.SECOND);

        return (sHour + ":" +sMinunute + ":" + sSecond);
    }


    /**
     *   Author: toantt
     * @param ts          Timestapm to convert
     * @param iStyle      0: 24h,  otherwise  12h clock
     * @return
     */
    public static String Timestamp2HHMMSS(java.sql.Timestamp ts, int iStyle) {
        if (ts == null)
            return "";

        String sHour = "";
        String sMinunute = "";
        String sSecond = "";
        String strTemp = "";

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // HH
        if (iStyle == 0)
            sHour = "" + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        else
            sHour = "" + Integer.toString(calendar.get(Calendar.HOUR));
        if (sHour.length() < 2)
            sHour = "0" + sHour;
        //MM
        if (calendar.get(Calendar.MINUTE) < 10)
            sMinunute = "0" + calendar.get(Calendar.MINUTE);
        else
            sMinunute = "" + calendar.get(Calendar.MINUTE);
        //SS
        if (calendar.get(Calendar.SECOND) < 10)
            sSecond = "0" + calendar.get(Calendar.SECOND);
        else
            sSecond = "" + calendar.get(Calendar.SECOND);

        strTemp = sHour + ":" + sMinunute + ":" + sSecond;

        if (iStyle != 0) {
            if (calendar.get(Calendar.AM_PM) == Calendar.AM)
                strTemp += " AM";
            else
                strTemp += " PM";
        }
        return strTemp;
    }

    /**
     *   Author: toantt
     * @param ts          Timestapm to convert
     * @param iStyle      0: 24h,  otherwise  12h clock
     * @return
     */
    public static String Timestamp2HHMMSS(java.sql.Timestamp ts, int iStyle, String separator) {
        if (ts == null)
            return "";

        String sHour = "";
        String sMinunute = "";
        String sSecond = "";
        String strTemp = "";

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // HH
        if (iStyle == 0)
            sHour = "" + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        else
            sHour = "" + Integer.toString(calendar.get(Calendar.HOUR));
        if (sHour.length() < 2)
            sHour = "0" + sHour;
        //MM
        if (calendar.get(Calendar.MINUTE) < 10)
            sMinunute = "0" + calendar.get(Calendar.MINUTE);
        else
            sMinunute = "" + calendar.get(Calendar.MINUTE);
        //SS
        if (calendar.get(Calendar.SECOND) < 10)
            sSecond = "0" + calendar.get(Calendar.SECOND);
        else
            sSecond = "" + calendar.get(Calendar.SECOND);

        strTemp = sHour + separator + sMinunute + separator + sSecond;

        if (iStyle != 0) {
            if (calendar.get(Calendar.AM_PM) == Calendar.AM)
                strTemp += " AM";
            else
                strTemp += " PM";
        }
        return strTemp;
    }


    /**
     * Return date time used for 24 hour clock: YYYYMMDDHHMM
     * by Nguyen Trong Tho
     */
    public static String getYYYYMMDDHHMMString(java.sql.Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2YYYYMMDD(ts) + Timestamp2HHMM(ts);
    }

    /**
     * Return date time used for 24 hour clock: YYYYMMDDHHMM
     * by Nguyen Trong Tho
     */
    public static String getYYYYMMDDHHMMStringBD(java.sql.Timestamp ts) {
        if (ts == null)
            return "";
        return vnTimestamp2YYYYMMDD(ts) + " " + Timestamp2HHMM(ts);
    }
    
    /**
     * Return date time used for 24 hour clock: YYYYMMDDHHMMSS
     * by Nguyen Trong Tho
     */
    public static String getYYYYMMDDHHMMSSString(java.sql.Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2YYYYMMDD(ts) + Timestamp2HHMMSS(ts);
    }



    /**
     *  return date time used for 24 hour clock
     */
    public static String getDateTime24hString(java.sql.Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 0);
    }

    /**
     *  return date time used for 12 hour clock
     */
    public static String getDateTime12hString(java.sql.Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    /**
     *   return string dd/mm/yyyy from a Timestamp + a addtional day
     * @param ts
     * @param iDayPlus    number of day to add
     * @return
     */
    public static String TimestampPlusDay2DDMMYYYY(java.sql.Timestamp ts,
        int iDayPlus) {
        if (ts == null)
            return "";
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, iDay + iDayPlus);

        java.sql.Timestamp tsNew = new java.sql.Timestamp( (calendar.getTime()).
            getTime());
        return Timestamp2DDMMYYYY(tsNew);
    }

    public static Timestamp getPreviousDate(Timestamp ts) {
        if (ts == null)
            return null;
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, iDay - 1);

        java.sql.Timestamp tsNew = new java.sql.Timestamp( (calendar.getTime()).
            getTime());
        return tsNew;
    }
    public static Timestamp getNextDate(Timestamp ts) {
		if (ts == null)
			return null;
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(new java.util.Date(ts.getTime()));
		int iDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, iDay + 1);

		java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime())
				.getTime());
		return tsNew;
	}

    /**
     * return the dd/mm/yyyy of current month
     *   eg:   05/2002  -->   31/05/2002
     * @author Nguyen Thien Phuong
     * @param strMonthYear  : input string mm/yyyy
     * @return
     */
    public static String getLastestDateOfMonth(String strMonthYear) {
        String strDate = strMonthYear;
        int i, nYear, nMonth;
        String strSub = null;

        i = strDate.indexOf("/");
        if (i < 0)
            return "";
        strSub = strDate.substring(0, i);
        nMonth = (new Integer(strSub)).intValue(); // Month begin from 0 value
        strDate = strDate.substring(i + 1);
        nYear = (new Integer(strDate)).intValue();

        boolean leapyear = false;
        if (nYear % 100 == 0) {
            if (nYear % 400 == 0)
                leapyear = true;
        } else
        if ( (nYear % 4) == 0)
            leapyear = true;

        if (nMonth == 2) {
            if (leapyear) {
                return "29/" + strDate;
            } else
                return "28/" + strDate;
        } else {
            if ( (nMonth == 1) || (nMonth == 3) || (nMonth == 5) ||
                (nMonth == 7) || (nMonth == 8) || (nMonth == 10) ||
                (nMonth == 12))
                return "31/" + strDate;
            else
            if ( (nMonth == 4) || (nMonth == 6) || (nMonth == 9) ||
                (nMonth == 11))
                return "30/" + strDate;
        }
        return "";
    }

    public static java.sql.Timestamp String2Timestamp(String strInputDate,int hh,int mm,int ss) {
        String strDate = strInputDate;
        int i, nYear, nMonth, nDay;
        String strSub = null;
        i = strDate.indexOf("/");
        if (i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if (i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        nMonth = (new Integer(strSub.trim())).intValue() - 1; // Month begin from 0 value
        strDate = strDate.substring(i + 1);
        if (strDate.length() < 4) {
            if (strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        }
        nYear = (new Integer(strDate)).intValue();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay,hh,mm,ss);
        return new java.sql.Timestamp( (calendar.getTime()).getTime());
    }
    
    public static void main (String args[]) {
       System.out.println(DateProc.getYYYYMMDDHHMMString(DateProc.createTimestamp()));
       System.out.println(DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp(), "-"));
    }
}
