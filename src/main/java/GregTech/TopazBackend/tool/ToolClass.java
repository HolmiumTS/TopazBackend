package GregTech.TopazBackend.tool;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public  abstract class  ToolClass {


    public static String stamp2time(Timestamp timestamp){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(timestamp.getTime()));
    }

    public static Timestamp time2stamp(String time){
        return new Timestamp( new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time,new ParsePosition(0)).getTime()) ;
    }
}

