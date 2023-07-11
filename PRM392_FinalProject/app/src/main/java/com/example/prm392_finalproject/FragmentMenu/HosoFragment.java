package com.example.prm392_finalproject.FragmentMenu;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prm392_finalproject.DBContext;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.User_Profile;

import java.util.ArrayList;
import java.util.List;

public class HosoFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_profile, container, false);
    }
}
