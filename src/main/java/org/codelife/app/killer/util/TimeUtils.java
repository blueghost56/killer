package org.codelife.app.killer.util;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author csl
 * @create 07/26/2017 14:58
 **/
public final class TimeUtils {
    private TimeUtils(){}

    public static Timestamp getNowTimeStamp(){
        return Timestamp.from(Calendar.getInstance().toInstant());
    }
}
