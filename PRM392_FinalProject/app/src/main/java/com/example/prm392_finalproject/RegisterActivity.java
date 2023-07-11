package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_register_username;
    private EditText edt_register_password;
    private EditText edt_register_repassword;
    private ImageButton btn_register;
    private Button btn_back_login;
    private DBContext dbContext;

    private void bindingView(){
        edt_register_username = findViewById(R.id.edt_register_username);
        edt_register_password = findViewById(R.id.edt_register_password);
        edt_register_repassword = findViewById(R.id.edt_register_repassword);
        btn_register = findViewById(R.id.btn_register);
        btn_back_login = findViewById(R.id.btn_back_login);
        dbContext = new DBContext(this);
    }

    private void bindingAction(){
        btn_register.setOnClickListener(this::onBtnRegisterClick);
        btn_back_login.setOnClickListener(this::onBtnBackClick);
    }

    private void onBtnBackClick(View view) {
        finish();
    }

    private void onBtnRegisterClick(View view) {
        String username = edt_register_username.getText().toString();
        String password = edt_register_password.getText().toString();
        String repassword = edt_register_repassword.getText().toString();
        boolean checkUser = getUser(username);
        if(!checkUser) {
            if(password.equals(repassword)){
                try{
                    dbContext.registerUser(username, password);
                    Toast.makeText(this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception ex){
                    Toast.makeText(this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "2 Mật Khẩu Không Giống Nhau", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Người Dùng Này Đã Tồn Tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindingView();
        bindingAction();
    }

    private boolean getUser(String username){
        Cursor ps = dbContext.getUserByName(username);
        if(ps.getCount() < 1){
            return false;
        }else{
            return true;
        }
    }
}