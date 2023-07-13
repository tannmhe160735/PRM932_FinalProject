package com.example.prm392_finalproject.FragmentMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.AdapterGunSkin;
import com.example.prm392_finalproject.AddGunActivity;
import com.example.prm392_finalproject.DBContext;
import com.example.prm392_finalproject.Gun_skin;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.VideoActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BoFragment extends Fragment {

    private TextView tvName;
    private TextView tvPrice;
    private ImageView img;
    private TextView tvDate;
    private RecyclerView rcvgun;
    private List<Gun_skin> gunSkins;
    private com.example.prm392_finalproject.Bundle bundle;
    private DBContext dbContext;
    private Context context;

    private void getData(View view) {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) +1;
        bundle = new com.example.prm392_finalproject.Bundle();
        Cursor ps = dbContext.getBundleByDate(y,m);
        if(ps == null){
            return;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_ID));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_NAME));
                @SuppressLint("Range") int price = ps.getInt(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_PRICE));
                @SuppressLint("Range") String image = ps.getString(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_IMAGE));
                @SuppressLint("Range") String datestart = ps.getString(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_START));
                @SuppressLint("Range") String dateend = ps.getString(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_END));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date date1 = dateFormat.parse(datestart);
                    Date date2 = dateFormat.parse(dateend);
                    bundle = new com.example.prm392_finalproject.Bundle(id,name, price, image,date1,date2);
                } catch (ParseException e) {

                    e.printStackTrace();
                }
            } while (ps.moveToNext());
        }
        tvName.setText(bundle.getName());
        tvPrice.setText(String.valueOf(bundle.getPrice()));
        Picasso.get().load(bundle.getImage()).into(img);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datestring = dateFormat.format(bundle.getDate_end());
        tvDate.setText("End date:" + datestring);
        gunSkins = new ArrayList<>();
        int id1 = bundle.getId();
        Cursor ps1 = dbContext.getGunBybundle(id1);
        if(ps1 == null){
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

    public void bindingView(View view){
        tvName = view.findViewById(R.id.TitleBunder);
        tvPrice = view.findViewById(R.id.BunderPrice);
        img =  view.findViewById(R.id.imgbundle);
        tvDate = view.findViewById(R.id.txtDate);
        rcvgun = view.findViewById(R.id.rcvGun);
        dbContext = new DBContext(view.getContext());
        getData(view);
    }

    private void bindingAction(){
        tvName.setOnClickListener(this::onBtnTitleClick);
    }

    private void onBtnTitleClick(View view) {
        Intent i = new Intent(view.getContext(), VideoActivity.class);
        startActivity(i);
    }

    private void bindDataToRcvDictionary(View v) {
        rcvgun.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rcvgun.setAdapter(new AdapterGunSkin(v.getContext(),gunSkins));
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
        return inflater.inflate(R.layout.activity_bundle, container, false);
    }
}
