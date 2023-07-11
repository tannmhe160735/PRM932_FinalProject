package com.example.prm392_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.prm392_finalproject.FragmentMenu.BoFragment;
import com.example.prm392_finalproject.FragmentMenu.CaiDatFragment;
import com.example.prm392_finalproject.FragmentMenu.HosoFragment;
import com.example.prm392_finalproject.FragmentMenu.ThuVienFragment;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_BO = 0;
    private static final int FRAGMENT_CUAHANG = 1;
    private static final int FRAGMENT_CHODEM = 2;
    private static final int FRAGMENT_HOSO = 3;
    private static final int FRAGMENT_THUVIEN = 4;
    private static final int FRAGMENT_CAIDAT = 5;
    private int mCurrentFragment =  FRAGMENT_THUVIEN;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new ThuVienFragment());
        navigationView.getMenu().findItem(R.id.nav_thuvien).setChecked(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_bo){
            if(mCurrentFragment !=  FRAGMENT_BO){
                replaceFragment(new BoFragment());
                mCurrentFragment = FRAGMENT_BO;
            }

        }else if (id == R.id.nav_cuahang){

        }else if (id == R.id.nav_chodem){

        }else if (id == R.id.nav_hoso){
            if(mCurrentFragment !=  FRAGMENT_HOSO){
                replaceFragment(new HosoFragment());
                mCurrentFragment = FRAGMENT_HOSO;
            }

        }else if (id == R.id.nav_thuvien){
            if(mCurrentFragment !=  FRAGMENT_THUVIEN){
                replaceFragment(new ThuVienFragment());
                mCurrentFragment = FRAGMENT_THUVIEN;
            }

        }else if (id == R.id.nav_caidat){
            if(mCurrentFragment !=  FRAGMENT_CAIDAT){
                replaceFragment(new CaiDatFragment());
                mCurrentFragment = FRAGMENT_CAIDAT;
            }
        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private  void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}