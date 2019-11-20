package com.app.advancecurd.validation;

import android.util.Patterns;

public class Validation {
    public boolean checkTextViewData(String data){
        return !data.isEmpty();
    }
    public boolean checkEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
