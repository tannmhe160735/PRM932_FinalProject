package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_register_username;
    private EditText edt_register_password;
    private EditText edt_register_repassword;
    private ImageButton btn_register;
    private Button btn_back_login;
    private List<Gun_skin> gunSkins;

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
                    dbContext.createNewProfile(String.valueOf(getUserByName(username).getUser_id()));
                    CreateNewShopForUser(getUserByName(username).getUser_id());
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

    private User getUserByName(String username){
        User user = new User();
        Cursor ps = dbContext.getUserByName(username);

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

    private List<Gun_skin> get4GunSkin() {
        List<Gun_skin> gunSkins = new ArrayList<>();
        Cursor ps = dbContext.getRandom4Gun();
        if(ps.getCount() < 1){
            return null;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_ID));
                Gun_skin gun_skin = new Gun_skin();
                gun_skin.setId(id);
                gunSkins.add(gun_skin);
            } while (ps.moveToNext());
        }
        return gunSkins;
    }

    private void CreateNewShopForUser(int user_id){
        gunSkins = get4GunSkin();
        int[] arr = new int[30];
        for (int i = 0; i < gunSkins.size() ; i++ ) {
            arr[i] = gunSkins.get(i).getId();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        String todaydate = dateFormat.format(today);
        String tomorrowdate = dateFormat.format(tomorrow);
        dbContext.createNewShop(String.valueOf(user_id), String.valueOf(arr[0]), String.valueOf(arr[1]), String.valueOf(arr[2]), String.valueOf(arr[3]), todaydate, tomorrowdate);
    }
}