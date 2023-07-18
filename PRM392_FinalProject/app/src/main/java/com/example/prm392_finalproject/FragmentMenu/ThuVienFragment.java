package com.example.prm392_finalproject.FragmentMenu;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.AdapterLibrary;
import com.example.prm392_finalproject.AddGunActivity;
import com.example.prm392_finalproject.DBContext;
import com.example.prm392_finalproject.Gun_skin;
import com.example.prm392_finalproject.LoginActivity;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.User;
import com.example.prm392_finalproject.User_Profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ThuVienFragment extends Fragment {

    private EditText edtSearch;
    private RecyclerView rcvLibrary;
    private ImageButton igBtnSearch;
    private List<Gun_skin> gunSkins;
    private FloatingActionButton add_gun_button;
    private DBContext dbContext;
    private String userid;
    private static final int REQUEST_CODE_FOR_OPEN_ADDGUNACTIVITY = 2;
    private void getData() {
        gunSkins = new ArrayList<>();
        Cursor ps = dbContext.getAllGun();
        if(ps == null){
            return;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_ID));
                @SuppressLint("Range") int bundle = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_BUNDLE));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_NAME));
                @SuppressLint("Range") int price = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_PRICE));
                @SuppressLint("Range") String image = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_IMAGE));
                Gun_skin gun_skin = new Gun_skin(id, bundle, name, price, image);
                gunSkins.add(gun_skin);
            } while (ps.moveToNext());
        }
    }

    public void bindingView(View v){
        edtSearch = v.findViewById(R.id.edtSearch);
        rcvLibrary = v.findViewById(R.id.rcvLibrary);
        igBtnSearch = v.findViewById(R.id.igBtnSearch);
        add_gun_button = v.findViewById(R.id.add_gun_button);
        dbContext = new DBContext(v.getContext());
        //get Userid session
        SharedPreferences sharedpreferences = v.getContext().getSharedPreferences(LoginActivity.MyPREFERENCES, v.getContext().MODE_PRIVATE);
        userid = sharedpreferences.getString("Userid", null);
        User user = getUser(userid);

        if(user.getRole() == 1){
            add_gun_button.setVisibility(View.INVISIBLE);
        }
        //
        getData();
    }

    public void bindingAction(){
        add_gun_button.setOnClickListener(this::onBtnAddGunClick);
        igBtnSearch.setOnClickListener(this::onBtnSearchGunClick);
    }

    private void onBtnSearchGunClick(View view) {
        gunSkins = new ArrayList<>();
        String name = edtSearch.getText().toString().trim();
        Cursor ps = dbContext.getGunLikeName(name);
        if(ps == null){
            return;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_ID));
                @SuppressLint("Range") int bundle = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_BUNDLE));
                @SuppressLint("Range") String gun_name = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_NAME));
                @SuppressLint("Range") int price = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_PRICE));
                @SuppressLint("Range") String image = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_IMAGE));
                Gun_skin gun_skin = new Gun_skin(id, bundle, gun_name, price, image);
                gunSkins.add(gun_skin);
            } while (ps.moveToNext());
        }
        bindDataToRcvDictionary(view);
    }

    private void onBtnAddGunClick(View view) {
        Intent i = new Intent(view.getContext(), AddGunActivity.class);
        startActivityForResult(i, REQUEST_CODE_FOR_OPEN_ADDGUNACTIVITY);
    }

    private void bindDataToRcvDictionary(View v) {
        rcvLibrary.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rcvLibrary.setAdapter(new AdapterLibrary(v.getContext(),gunSkins));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindingAction();
        bindDataToRcvDictionary(view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_library, container, false);
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
