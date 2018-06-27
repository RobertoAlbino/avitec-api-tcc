package com.roberto.cotaeasy.test.utils;

import com.roberto.cotaeasy.utils.DateUtils;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

public class DateUtilsTest {

    @Test
    public void garantirQueRemoveHoraDaData() {
        Date data = DateUtils.removerHorasData(DateTime.now().toDate());
        Assert.assertTrue( new DateTime(data).getHourOfDay() == 0);
    }
}
