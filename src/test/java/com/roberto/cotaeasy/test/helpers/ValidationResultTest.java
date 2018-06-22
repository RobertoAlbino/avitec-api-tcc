package com.roberto.cotaeasy.test.helpers;

import com.roberto.cotaeasy.helpers.ValidationResult;

import org.junit.Assert;
import org.junit.Test;

public class ValidationResultTest {

    private ValidationResult validation = new ValidationResult();

    @Test
    public void garantirQueErrosSaoConsiderados() {
        validation.add("teste");
        Assert.assertFalse(validation.isValid());
    }
}
