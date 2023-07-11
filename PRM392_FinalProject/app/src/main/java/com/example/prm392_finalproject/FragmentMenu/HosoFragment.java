package com.example.prm392_finalproject.FragmentMenu;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prm392_finalproject.DBContext;
import com.example.prm392_finalproject.LoginActivity;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.User;
import com.example.prm392_finalproject.User_Profile;

import java.util.ArrayList;
import java.util.List;

public class HosoFragment extends Fragment {
    private TextView tvProfile_Name;
    private TextView tvServer;
    private TextView tvLevel;
    private TextView tvExp;
    private TextView tvValP;
    private TextView tvRadP;
    private TextView tvAgentFree;
    private DBContext dbContext;
    private List<User_Profile> userProfiles;

    public User_Profile user_profile;
    private String userid;

    private void bindingView(View v){
        tvProfile_Name = v.findViewById(R.id.tvProfile_Name);
        tvServer = v.findViewById(R.id.tvServer);
        tvExp = v.findViewById(R.id.tvExp);
        tvLevel = v.findViewById(R.id.tvLevel);
        tvValP = v.findViewById(R.id.tvValP);
        tvRadP = v.findViewById(R.id.tvRadP);
        tvAgentFree = v.findViewById(R.id.tvAgentFree);

        dbContext = new DBContext(v.getContext());
        //get Userid session
        SharedPreferences sharedpreferences = v.getContext().getSharedPreferences(LoginActivity.MyPREFERENCES, v.getContext().MODE_PRIVATE);
        userid = sharedpreferences.getString("Userid", null);
        //
        User_Profile userProfile = getInformationUser(userid);
        User user = getUser(userid);
        tvProfile_Name.setText(user.getUser_name());
        tvServer.setText(userProfile.getServer());
        tvExp.setText(String.valueOf(userProfile.getExp()));
        tvLevel.setText(String.valueOf(userProfile.getLevel()));
        tvValP.setText(String.valueOf(userProfile.getValorant_Point()));
        tvRadP.setText(String.valueOf(userProfile.getRadianite_Point()));
        tvAgentFree.setText(String.valueOf(userProfile.getFree_Agent()));
    }

    private void bindingAction(){

    }

    private User_Profile getInformationUser (String userid){
        Cursor ps = dbContext.getUserProfileById(userid);
        if(ps.getCount() < 1){
            return null;
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
                user_profile = new User_Profile(User_id,Server,Lever,Exp,Valorant_Point,Radianite_Point,Free_Agent);
            } while (ps.moveToNext());
        }
        return user_profile;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindingAction();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_profile, container, false);
    }

    private User getUser(String userid){
        User user = new User();
        Cursor ps = dbContext.getUserById(userid);

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
}
