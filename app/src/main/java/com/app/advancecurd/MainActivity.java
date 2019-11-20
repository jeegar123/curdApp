package com.app.advancecurd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.advancecurd.database.DataBaseHelper;
import com.app.advancecurd.validation.Validation;

import static com.app.advancecurd.database.MyUtil.keyFileName;
import static com.app.advancecurd.database.MyUtil.keyUsernameName;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    DataBaseHelper dataBaseHelper;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login Page");
        sharedPreferences=getSharedPreferences(keyFileName,MODE_PRIVATE);
        if(!sharedPreferences.getString(keyUsernameName,"").equals("")){
            startActivity(new Intent(MainActivity.this,DisplayActivity.class));
            finish();
        }
        edtUsername=findViewById(R.id.edt_username);
        edtPassword=findViewById(R.id.edt_password);
        dataBaseHelper=new DataBaseHelper(this);
    }

    public void registerMe(View view) {
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
        finish();
    }

    public void login(View view) {
        String username=edtUsername.getText().toString();
        String password=edtPassword.getText().toString();

        Validation validation=new Validation();
        if(validation.checkEmail(username) && validation.checkTextViewData(password)){

            if(dataBaseHelper.checkAuthentication(username,password)){
                editor=sharedPreferences.edit();
                editor.putString(keyUsernameName,username);
                editor.commit();
                startActivity(new Intent(this,DisplayActivity.class));
                finish();
            }
            else{
                Toast.makeText(this, "invalid user", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "please enter data", Toast.LENGTH_SHORT).show();
        }


    }
}
