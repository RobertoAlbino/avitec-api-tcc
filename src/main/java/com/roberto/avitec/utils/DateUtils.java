package com.roberto.avitec.utils;

import org.joda.time.DateTime;
import java.text.DateFormat;
import java.util.Date;

public class DateUtils {

    public static Date removerHorasData(Date data) {
        return new DateTime(data).withTime(0,0,0,0).toDate();
    }
}
