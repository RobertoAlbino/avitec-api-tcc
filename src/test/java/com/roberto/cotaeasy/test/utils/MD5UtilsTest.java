package com.roberto.cotaeasy.test.utils;

import org.junit.Assert;
import org.junit.Test;
import com.roberto.cotaeasy.utils.MD5Utils;

public class MD5UtilsTest {

    @Test
    public void garantirTamanhoMD5Maior10() throws Exception {
        Assert.assertTrue(MD5Utils.encriptarSenha("Roberto").length() > 10);
    }

    @Test
    public void garantirTamanhoMD5Menor60() throws Exception {
        Assert.assertTrue(MD5Utils.encriptarSenha("Roberto").length() < 60);
    }

    @Test
    public void garantirQueUmaStringSempreGeraMesmoMD5() throws Exception {
        Assert.assertEquals(MD5Utils.encriptarSenha("Roberto"),MD5Utils.encriptarSenha("Roberto"));
    }
}
