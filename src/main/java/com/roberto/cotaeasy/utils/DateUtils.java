package com.roberto.cotaeasy.utils;

import org.joda.time.DateTime;
import java.text.DateFormat;
import java.util.Date;

public class DateUtils {

    public static Date removerHorasData(Date data) {
        return DateTime.parse(DateFormat.getDateInstance().format(data)).withTime(0,0,0,0).toDate();
    }
}
