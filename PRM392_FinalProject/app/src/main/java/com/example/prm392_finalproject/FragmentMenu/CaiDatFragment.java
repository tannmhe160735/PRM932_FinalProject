package com.example.prm392_finalproject.FragmentMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.prm392_finalproject.AddGunActivity;
import com.example.prm392_finalproject.DBContext;
import com.example.prm392_finalproject.LoginActivity;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.User;

public class CaiDatFragment extends Fragment {

    private TextView tvLogout;
    private DBContext dbContext;
    private String userid;

    private void bindingView(View v){
        tvLogout = v.findViewById(R.id.tvLogout);
    }

    private void bindingAction(){
        tvLogout.setOnClickListener(this::onTextLogoutClick);
    }

    private void onTextLogoutClick(View v) {
        dbContext = new DBContext(v.getContext());
        //get Userid session
        SharedPreferences sharedpreferences = v.getContext().getSharedPreferences(LoginActivity.MyPREFERENCES, v.getContext().MODE_PRIVATE);
        userid = sharedpreferences.getString("Userid", null);
        //
        User user = getUser(userid);
        dbContext.updateUser_Remember(0, user.getUser_id());
        sharedpreferences.edit().clear();
        Intent i = new Intent(v.getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
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
        return inflater.inflate(R.layout.activity_setting, container, false);
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
