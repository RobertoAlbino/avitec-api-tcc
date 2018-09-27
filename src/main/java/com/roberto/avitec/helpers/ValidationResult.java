package com.roberto.avitec.helpers;

import java.util.LinkedList;

public class ValidationResult {

    private LinkedList<String> errors;

    public ValidationResult() {
        errors = new LinkedList<>();
    }

    public void add(String error) {
        errors.push(error);
    }

    public boolean isValid() {
        return errors.size() == 0 ? true : false;
    }
}
