package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_username;
    private EditText edt_password;
    private CheckBox checkBox;
    private ImageButton btn_login;
    private DBContext dbContext;

    private void bindingView(){
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        checkBox = findViewById(R.id.checkBox);
        btn_login = findViewById(R.id.btn_login);
        dbContext = new DBContext(this);
        for (User item: getAllUser()) {
            if(item.getRemember() == true){
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
    private void bindingAction(){
        btn_login.setOnClickListener(this::onBtnLoginClick);
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
            Intent i = new Intent(this, MenuActivity.class);
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