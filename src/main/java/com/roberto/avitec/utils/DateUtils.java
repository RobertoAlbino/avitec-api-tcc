package com.roberto.avitec.utils;

import org.joda.time.DateTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date now() {
        return Date.from(LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date removerHorasData(Date data) {
        return new DateTime(data).withTime(0,0,0,0).toDate();
    }
}
