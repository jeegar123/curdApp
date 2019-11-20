package com.app.advancecurd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.advancecurd.adapters.UserAdapter;
import com.app.advancecurd.database.DataBaseHelper;
import com.app.advancecurd.dialogboxs.InputDialogBox;
import com.app.advancecurd.dialogboxs.MessageDialog;
import com.app.advancecurd.dialogboxs.SetDeleteOperation;

import static com.app.advancecurd.database.MyUtil.keyFileName;
import static com.app.advancecurd.database.MyUtil.keyUsernameName;

public class DisplayActivity extends AppCompatActivity implements SetDeleteOperation {


    SharedPreferences sharedPreferences;
    ListView listView;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Welcome");
        sharedPreferences = getSharedPreferences(keyFileName, MODE_PRIVATE);
        listView = findViewById(R.id.lstdata);
        dataBaseHelper = new DataBaseHelper(this);
        UserAdapter userAdapter = new UserAdapter(this, dataBaseHelper.getRecordsInList());
        listView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplicationContext());
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MessageDialog messageDialog;
        switch (item.getItemId()) {

            case R.id.logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(keyUsernameName);
                editor.commit();
                startActivity(new Intent(DisplayActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.update_option:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.delete_option:
                InputDialogBox inputDialogBox = new InputDialogBox();
                inputDialogBox.show(getSupportFragmentManager(), "remove");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void getData(boolean check) {
        if (check) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

            if (dataBaseHelper.delete(sharedPreferences.getString(keyUsernameName, ""))) {
                MessageDialog messageDialog = new MessageDialog("delete successfull");
                messageDialog.show(getSupportFragmentManager(), "done");
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(keyUsernameName);
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
