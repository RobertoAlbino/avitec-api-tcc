package com.roberto.cotaeasy.test.utils;

import com.roberto.cotaeasy.utils.DateUtils;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

public class DateUtilsTest {

    @Test
    public void garantirDataNaoPossuiHora() {
        Date dataFormatada = DateUtils.removerHorasData(DateTime.now().toDate());
        System.out.println(dataFormatada.toString());
        Assert.assertTrue(DateFormat.getDateInstance().format(DateTime.now().toDate()) == "26/06/2018");
    }
}
