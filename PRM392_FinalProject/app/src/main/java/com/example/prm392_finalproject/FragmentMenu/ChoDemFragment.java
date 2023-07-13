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
import com.example.prm392_finalproject.AdapterMarket;
import com.example.prm392_finalproject.DBContext;
import com.example.prm392_finalproject.Gun_skin;
import com.example.prm392_finalproject.LoginActivity;
import com.example.prm392_finalproject.Nightmarket;
import com.example.prm392_finalproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoDemFragment extends Fragment {

    private RecyclerView rcvmarket;
    private TextView tvDate;
    private List<Gun_skin> gunSkins;
    private Nightmarket nightmarket;
    private DBContext dbContext;
    private String userid;
    private void getData(View view) {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) +1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int Useid = 1;
        nightmarket = new Nightmarket();
        Cursor ps = dbContext.getNightmarket(Useid,y,m,d);
        if (ps == null) {
            return;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_COL_USER_ID));
                @SuppressLint("Range") int gunskin1 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_GUN_SKIN_1));
                @SuppressLint("Range") int gunskin2 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_GUN_SKIN_2));
                @SuppressLint("Range") int gunskin3 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_GUN_SKIN_3));
                @SuppressLint("Range") int gunskin4 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_GUN_SKIN_4));
                @SuppressLint("Range") int gunskin5 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_GUN_SKIN_5));
                @SuppressLint("Range") int gunskin6 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_GUN_SKIN_6));
                @SuppressLint("Range") String datestart = ps.getString(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_START));
                @SuppressLint("Range") String dateend = ps.getString(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_END));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                @SuppressLint("Range") int discount1 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_DISCOUNT1));
                @SuppressLint("Range") int discount2 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_DISCOUNT2));
                @SuppressLint("Range") int discount3 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_DISCOUNT3));
                @SuppressLint("Range") int discount4 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_DISCOUNT4));
                @SuppressLint("Range") int discount5 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_DISCOUNT5));
                @SuppressLint("Range") int discount6 = ps.getInt(ps.getColumnIndex(DBContext.TABLE_NIGHT_MARKET_COL_DISCOUNT6));

                try {
                    Date date1 = dateFormat.parse(datestart);
                    Date date2 = dateFormat.parse(dateend);
                    nightmarket = new Nightmarket(id, gunskin1, gunskin2, gunskin3, gunskin4,gunskin5,gunskin6, date1, date2,discount1,discount2,discount3,discount4,discount5,discount6);
                } catch (ParseException e) {

                    e.printStackTrace();
                }
            } while (ps.moveToNext());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datestring = dateFormat.format(nightmarket.getDate_end());
        tvDate.setText("End date:" + datestring);
        gunSkins = new ArrayList<>();
        int id1 = nightmarket.getGun1();
        int id2 = nightmarket.getGun2();
        int id3 = nightmarket.getGun3();
        int id4 = nightmarket.getGun4();
        int id5 = nightmarket.getGun5();
        int id6 = nightmarket.getGun6();
        int dc1 = nightmarket.getDiscount1();
        int dc2 = nightmarket.getDiscount2();
        int dc3 = nightmarket.getDiscount3();
        int dc4 = nightmarket.getDiscount4();
        int dc5 = nightmarket.getDiscount5();
        int dc6 = nightmarket.getDiscount6();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(id1, dc1);
        map.put(id2, dc2);
        map.put(id3, dc3);
        map.put(id4, dc4);
        map.put(id5, dc5);
        map.put(id6, dc6);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            Cursor ps1 = dbContext.getGunByGunId(key);
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
                    int discount = value;
                    Gun_skin gun_skin = new Gun_skin(id, bundle, name, price, image,discount);
                    gunSkins.add(gun_skin);
                } while (ps1.moveToNext());
            }
            bindDataToRcvDictionary(view);
        }
    }
    public void bindingView(View view){
        tvDate = view.findViewById(R.id.txtDatemarket);
        rcvmarket = view.findViewById(R.id.rcvmarket);
        dbContext = new DBContext(view.getContext());
        getData(view);
    }
    private void bindDataToRcvDictionary(View view) {
        rcvmarket.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rcvmarket.setAdapter(new AdapterMarket(view.getContext(),gunSkins));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindDataToRcvDictionary(view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_nightmarket, container, false);
    }
}
