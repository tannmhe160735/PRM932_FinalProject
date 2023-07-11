package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_username;
    private EditText edt_password;
    private CheckBox checkBox;
    private ImageButton btn_login;
    private Button btn_forgotpassword;
    private Button btn_create_account;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private DBContext dbContext;

    public static final String Userid = "Userid";
    public static final String MyPREFERENCES = "MyPrefs" ;

    private void bindingView(){
        edt_username = findViewById(R.id.edt_register_username);
        edt_password = findViewById(R.id.edt_register_password);
        checkBox = findViewById(R.id.checkBox);
        btn_login = findViewById(R.id.btn_register);
        btn_forgotpassword = findViewById(R.id.btn_forgotpassword);
        btn_create_account = findViewById(R.id.btn_back_login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, this.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        dbContext = new DBContext(this);
        for (User item: getAllUser()) {
            if(item.getRemember() == true){
                editor.putString(Userid, String.valueOf(item.getUser_id()));
                editor.commit();
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
    private void bindingAction(){
        btn_login.setOnClickListener(this::onBtnLoginClick);
        btn_create_account.setOnClickListener(this::onBtnCreateClick);
        
    }

    private void onBtnCreateClick(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private void onBtnLoginClick(View view) {
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();
        User user = Login(username, password);
        if(user != null){
            if(checkBox.isChecked()){
                dbContext.updateUser_Remember(1, user.getUser_id());
            }else{
                dbContext.updateUser_Remember(0, user.getUser_id());
            }
            //add userid like session
            editor.putString(Userid, String.valueOf(user.getUser_id()));
            editor.commit();
            //
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindingView();
        bindingAction();
    }

    private User Login(String username, String password){
        User user = new User();
        Cursor ps = dbContext.Login(username, password);

        if(ps.getCount() < 1){
            return null;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_USER_ID));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_USER_COL_USER_NAME));
                @SuppressLint("Range") String pass = ps.getString(ps.getColumnIndex(DBContext.TABLE_USER_COL_PASSWORD));
                @SuppressLint("Range") int role = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_ROLE));
                @SuppressLint("Range") boolean remember = (ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_REMEMBER)) > 0);
                user = new User(id, name, pass, role, remember);

            } while (ps.moveToNext());
        }
        return user;
    }

    private ArrayList<User> getAllUser(){
        ArrayList<User> users = new ArrayList<User>();
        Cursor ps = dbContext.getAllUser();

        if(ps.getCount() < 1){
            return null;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_USER_ID));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_USER_COL_USER_NAME));
                @SuppressLint("Range") String pass = ps.getString(ps.getColumnIndex(DBContext.TABLE_USER_COL_PASSWORD));
                @SuppressLint("Range") int role = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_ROLE));
                @SuppressLint("Range") boolean remember = (ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_REMEMBER)) > 0);
                User user = new User(id, name, pass, role, remember);
                users.add(user);
            } while (ps.moveToNext());
        }
        return users;
    }

}