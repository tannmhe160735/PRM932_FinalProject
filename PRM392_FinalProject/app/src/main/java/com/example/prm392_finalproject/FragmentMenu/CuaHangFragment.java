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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.AdapterGunSkin;
import com.example.prm392_finalproject.DBContext;
import com.example.prm392_finalproject.Gun_skin;
import com.example.prm392_finalproject.LoginActivity;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Shop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CuaHangFragment extends Fragment {
    private RecyclerView rcvShop;
    private TextView tvDate;
    private List<Gun_skin> gunSkins;
    private Shop shop;
    private DBContext dbContext;
    private String userid;

    private void getData(View view) {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) +1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int Useid = Integer.parseInt(userid);
        shop = new Shop();
        Cursor ps = dbContext.getShopByUser(Useid,y,m,d);
        if (ps.getCount() < 1) {
            CreateNewShopForUser(Useid);
            ps = dbContext.getShopByUser(Useid,y,m,d);
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_SHOP_COL_SHOP_ID));
                @SuppressLint("Range") int gunskin1 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_SHOP_COL_GUN_SKIN_1));
                @SuppressLint("Range") int gunskin2 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_SHOP_COL_GUN_SKIN_2));
                @SuppressLint("Range") int gunskin3 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_SHOP_COL_GUN_SKIN_3));
                @SuppressLint("Range") int gunskin4 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_SHOP_COL_GUN_SKIN_4));
                @SuppressLint("Range") String datestart = ps.getString(ps.getColumnIndex(DBContext.TABLE_SHOP_COL_START));
                @SuppressLint("Range") String dateend = ps.getString(ps.getColumnIndex(DBContext.TABLE_SHOP_COL_END));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date date1 = dateFormat.parse(datestart);
                    Date date2 = dateFormat.parse(dateend);
                    shop = new Shop(id, gunskin1, gunskin2, gunskin3, gunskin4, date1, date2);
                } catch (ParseException e) {

                    e.printStackTrace();
                }
            } while (ps.moveToNext());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datestring = dateFormat.format(shop.getDate_end());
        tvDate.setText("End date:" + datestring);
        gunSkins = new ArrayList<>();
        int id1 = shop.getGun1();
        int id2 = shop.getGun2();
        int id3 = shop.getGun3();
        int id4 = shop.getGun4();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(id1);
        list.add(id2);
        list.add(id3);
        list.add(id4);
        for (Integer item : list) {
            Cursor ps1 = dbContext.getGunByGunId(item);
            if (ps1 == null) {
                return;
            }
            if (ps1.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = ps1.getInt(ps1.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_ID));
                    @SuppressLint("Range") int bundle = ps1.getInt(ps1.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_BUNDLE));
                    @SuppressLint("Range") String name = ps1.getString(ps1.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_NAME));
                    @SuppressLint("Range") int price = ps1.getInt(ps1.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_PRICE));
                    @SuppressLint("Range") String image = ps1.getString(ps1.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_IMAGE));
                    Gun_skin gun_skin = new Gun_skin(id, bundle, name, price, image);
                    gunSkins.add(gun_skin);
                } while (ps1.moveToNext());
            }
            bindDataToRcvDictionary(view);
        }
    }
    public void bindingView(View v){
        tvDate = v.findViewById(R.id.txtDateShop);
        rcvShop = v.findViewById(R.id.rcvShop);
        dbContext = new DBContext(v.getContext());
        //get Userid session
        SharedPreferences sharedpreferences = v.getContext().getSharedPreferences(LoginActivity.MyPREFERENCES, v.getContext().MODE_PRIVATE);
        userid = sharedpreferences.getString("Userid", null);
        //
        getData(v);
    }
    private void bindDataToRcvDictionary(View v) {
        rcvShop.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rcvShop.setAdapter(new AdapterGunSkin(v.getContext(),gunSkins));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindDataToRcvDictionary(view);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_shop, container, false);
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
