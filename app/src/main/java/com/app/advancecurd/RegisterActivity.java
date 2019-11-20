package com.app.advancecurd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.app.advancecurd.database.DataBaseHelper;
import com.app.advancecurd.dialogboxs.MessageDialog;
import com.app.advancecurd.validation.Validation;

import java.util.ArrayList;

import static com.app.advancecurd.database.MyUtil.keyFileName;
import static com.app.advancecurd.database.MyUtil.keyUsernameName;

public class RegisterActivity extends AppCompatActivity {

    EditText edt_firstname,edt_lastname, edt_email, edt_password;
    Switch switch_ce_it;
    Spinner spinner;
    RadioGroup radioGroup;
    Button btn_register;
    CheckBox checkBox_status;
    DataBaseHelper dataBaseHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registration");

        btn_register = findViewById(R.id.btn_register);
        edt_firstname = findViewById(R.id.edt_firstname);
        edt_lastname = findViewById(R.id.edt_lastname);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        switch_ce_it = findViewById(R.id.switch_ce_it);
        radioGroup = findViewById(R.id.radiogroup);
        spinner = findViewById(R.id.spinner);
        checkBox_status = findViewById(R.id.chkbox_status);
        dataBaseHelper = new DataBaseHelper(this);
        sharedPreferences = getSharedPreferences(keyFileName, MODE_PRIVATE);
        if (!sharedPreferences.getString(keyUsernameName, "").isEmpty()) {
            setTitle("Update Information");
            ArrayList<String> arrayList = dataBaseHelper.getSelectedUserData(sharedPreferences.getString(keyUsernameName, ""));
            String fullname = arrayList.get(0);
            String[] data = fullname.split(" ");
            edt_firstname.setText(data[0]);
            edt_lastname.setText(data[1]);
            edt_email.setText(arrayList.get(1));
            edt_password.setText(arrayList.get(2));
            if (arrayList.get(3).equals("IT"))
                switch_ce_it.setChecked(true);
            else
                switch_ce_it.setChecked(false);
            switch (arrayList.get(4)) {
                case "Diu":
                    spinner.setSelection(0);
                    break;
                case "Rajkot":
                    spinner.setSelection(1);
                    break;
                case "Junaghard":
                    spinner.setSelection(2);
                    break;
                case "Ahemdabad":
                    spinner.setSelection(3);
                    break;
                case "Jamnagar":
                    spinner.setSelection(4);
                    break;
                case "Porbandar":
                    spinner.setSelection(5);
                    break;

            }
            if (arrayList.get(5).equals("male"))
                radioGroup.check(R.id.rad_male);
            else
                radioGroup.check(R.id.rad_female);

            if (arrayList.get(6).equals("Active"))
                checkBox_status.setChecked(true);
            else
                checkBox_status.setChecked(false);
        }
    }

    public void registerMe(View view) {
        String firstname = edt_firstname.getText().toString();
        String lastname = edt_lastname.getText().toString();
        String fullname = firstname + " " + lastname;

        String email = edt_email.getText().toString();
        String password = edt_password.getText().toString();
        String branch = "CE";
        if (switch_ce_it.isChecked()) {
            branch = "IT";
        }
        String gender;
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radiobutton = findViewById(radioButtonId);
        gender = radiobutton.getText().toString();
        String city = spinner.getSelectedItem().toString();
        String status = "InActive";
        if (checkBox_status.isChecked()) {
            status = "Active";
        }

        Validation validation = new Validation();

        if (validation.checkEmail(email) && validation.checkTextViewData(password) && validation.checkTextViewData(firstname) && validation.checkTextViewData(lastname) && validation.checkTextViewData(lastname)) {
            SharedPreferences sharedPreferences = getSharedPreferences(keyFileName, MODE_PRIVATE);
            if (!sharedPreferences.getString(keyUsernameName, "").isEmpty()) {
                if (dataBaseHelper.update(fullname, email, password, gender, city, status, branch) != -1)
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            } else {
                if (dataBaseHelper.insert(fullname, email, password, gender, city, status, branch) != -1) {
                    Toast.makeText(this, "Successfull inserted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                } else{
                    MessageDialog messageDialog =new MessageDialog("unsuccessfull") ;
                    messageDialog.show(getSupportFragmentManager(),"unknown");
                }
            }



        } else {
            if (!validation.checkTextViewData(password))
                edt_password.setError("enter valid email");
            if (!validation.checkTextViewData(firstname))
                edt_firstname.setError("fill firstname");
            if (!validation.checkTextViewData(lastname))
                edt_lastname.setError("fill lastname");
            if (!validation.checkTextViewData(password))
                edt_lastname.setError("fill password");
            if (!validation.checkEmail(email))
                edt_email.setError("enter valid email");
        }


    }

    @Override
    protected void onDestroy() {
        dataBaseHelper.close();
        super.onDestroy();
    }


}
