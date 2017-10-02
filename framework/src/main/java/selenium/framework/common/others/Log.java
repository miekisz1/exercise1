package selenium.framework.common.others;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public static void message(String message){
       System.out.println(String.format("%s, %s", timeFormat.format(new Date()), message));
    }

    public static void message(String message, Object...parameteres){
        message(String.format(message, parameteres));
    }
}
