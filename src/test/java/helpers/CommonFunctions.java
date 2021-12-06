package helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonFunctions {

    public static String getCurrentDate(String format){
        String date;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        date = formatter.format(calendar.getTime());
        System.out.println(" Current date " + date);
        return date;
    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
