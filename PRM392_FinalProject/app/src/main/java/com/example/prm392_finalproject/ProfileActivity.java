package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private DBContext dbContext;
    private List<User_Profile> userProfiles;

    public User_Profile user_profile;


    private void getInformationUser (){
        userProfiles = new ArrayList<>();
        Cursor ps = dbContext.getAllGun();
        if(ps == null){
            return;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int User_id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_PROFILE_COL_USER_ID));
                @SuppressLint("Range") String Server = ps.getString(ps.getColumnIndex(DBContext.TABLE_USER_PROFILE_COL_SERVER));
                @SuppressLint("Range") int Lever = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_PROFILE_COL_LEVEL));
                @SuppressLint("Range") int Exp = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_PROFILE_COL_EXP));
                @SuppressLint("Range") int Valorant_Point = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_PROFILE_COL_VALORANT_POINT));
                @SuppressLint("Range") int Radianite_Point = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_PROFILE_COL_RADIANITE_POINT));
                @SuppressLint("Range") int Free_Agent = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_PROFILE_COL_FREE_AGENT));
                User_Profile User_Profile = new User_Profile(User_id,Server,Lever,Exp,Valorant_Point,Radianite_Point,Free_Agent);
                userProfiles.add(User_Profile);
            } while (ps.moveToNext());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
    }
}