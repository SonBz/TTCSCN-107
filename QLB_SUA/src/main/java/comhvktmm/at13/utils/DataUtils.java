package comhvktmm.at13.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    SimpleDateFormat FD=new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat full=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat full1=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final String Date_time_String="yyyy-MM-dd HH:mm:ss";
    public static final String Date_time_String1="yyyy-MM-dd";
    public static final String Date_time_String2="yyyy-MM-dd HH:mm";

    public static final String yy="yyyy";
    public static final String mm="MM";
    public static final String đ="yyyy-MM-dd";

    public static String getCurrtentTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(Date_time_String);
        String date=simpleDateFormat.format(new Date());
        return date;
    }

    public static String getCurrtentTime2(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(Date_time_String2);
        String date=simpleDateFormat.format(new Date());
        return date;
    }

    public static String fd(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(Date_time_String1);
        String date=simpleDateFormat.format(new Date());
        return date;
    }

    public static Long getCurrentTime1(String date)throws ParseException {
        Date date2=new SimpleDateFormat(Date_time_String1).parse(date);
        long time = date2.getTime();
        return time;
    }

    public static Long getCurrentTime2(String date)throws ParseException {
        Date date2=new SimpleDateFormat(Date_time_String).parse(date);
        long time = date2.getTime();
        return time;
    }
}
