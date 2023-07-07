package com.example.prm392_finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBContext extends SQLiteOpenHelper {
    private static final String DB_NAME = "PRM392_FinalProject.db";
    private static final int DB_VERSION = 1;

    public DBContext(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String TABLE_BUNDLE = "Bundle";
    public static final String TABLE_BUNDLE_COL_BUNDLE_ID = "Bundle_id";
    public static final String TABLE_BUNDLE_COL_BUNDLE_NAME = "Bundle_name";
    public static final String TABLE_BUNDLE_COL_BUNDLE_PRICE = "Bundle_price";
    public static final String TABLE_BUNDLE_COL_BUNDLE_IMAGE = "Bundle_Image";
    public static final String TABLE_BUNDLE_COL_BUNDLE_START = "Date_start";
    public static final String TABLE_BUNDLE_COL_BUNDLE_END = "Date_end";

    public static final String TABLE_GUN_SKIN = "Gun_skin";
    public static final String TABLE_GUN_SKIN_COL_GUN_ID = "Gun_id";
    public static final String TABLE_GUN_SKIN_COL_GUN_NAME = "Gun_skin_name";
    public static final String TABLE_GUN_SKIN_COL_GUN_PRICE = "Gun_price";
    public static final String TABLE_GUN_SKIN_COL_GUN_IMAGE = "Gun_skin_image";
    public static final String TABLE_GUN_SKIN_COL_BUNDLE = "Bundle";

    public static final String TABLE_SHOP = "Shop";
    public static final String TABLE_SHOP_COL_SHOP_ID = "Shop_id";
    public static final String TABLE_SHOP_COL_USER_ID = "User_id";
    public static final String TABLE_SHOP_COL_GUN_SKIN_1 = "Gun_skin_1";
    public static final String TABLE_SHOP_COL_GUN_SKIN_2 = "Gun_skin_2";
    public static final String TABLE_SHOP_COL_GUN_SKIN_3 = "Gun_skin_3";
    public static final String TABLE_SHOP_COL_GUN_SKIN_4 = "Gun_skin_4";
    public static final String TABLE_SHOP_COL_START = "Date_start";
    public static final String TABLE_SHOP_COL_END = "Date_end";

    public static final String TABLE_USER = "User";
    public static final String TABLE_USER_COL_USER_ID = "User_id";
    public static final String TABLE_USER_COL_USER_NAME = "User_name";
    public static final String TABLE_USER_COL_PASSWORD = "Password";
    public static final String TABLE_USER_COL_ROLE = "Role";

    public static final String TABLE_USER_PROFILE = "User_Profile";
    public static final String TABLE_USER_PROFILE_COL_USER_ID = "User_id";
    public static final String TABLE_USER_PROFILE_COL_SERVER = "Server";
    public static final String TABLE_USER_PROFILE_COL_LEVEL = "Level";
    public static final String TABLE_USER_PROFILE_COL_EXP = "Exp";
    public static final String TABLE_USER_PROFILE_COL_VALORANT_POINT = "Valorant_Point";
    public static final String TABLE_USER_PROFILE_COL_RADIANITE_POINT = "Radianite_Point";
    public static final String TABLE_USER_PROFILE_COL_FREE_AGENT = "Free_Agent";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String bundle = "create table Bundle\n" +
                "(\n" +
                "    Bundle_id    integer       not null\n" +
                "        primary key autoincrement\n" +
                "        unique,\n" +
                "    Bundle_name  nvarchar(100) not null,\n" +
                "    Bundle_price integer       not null,\n" +
                "    Bundle_Image text,\n" +
                "    Date_start   text,\n" +
                "    Date_end     text\n" +
                ");";
        String gunskin = "create table Gun_skin\n" +
                "(\n" +
                "    Gun_id         integer       not null\n" +
                "        primary key autoincrement\n" +
                "        unique,\n" +
                "    Gun_skin_name  nvarchar(100) not null,\n" +
                "    Gun_price      integer       not null,\n" +
                "    Gun_skin_image text          not null,\n" +
                "    Bundle         integer\n" +
                "        constraint Gun_skin_Bundle_Bundle_id_fk\n" +
                "            references Bundle\n" +
                ");";
        String shop = "create table Shop\n" +
                "(\n" +
                "    Shop_id    integer not null\n" +
                "        primary key autoincrement\n" +
                "        unique,\n" +
                "    User_id    integer not null\n" +
                "        constraint Shop_User_User_id_fk\n" +
                "            references User,\n" +
                "    Gun_skin_1 integer not null\n" +
                "        constraint Shop_Gun_skin_Gun_id_fk\n" +
                "            references Gun_skin,\n" +
                "    Gun_skin_2 integer not null\n" +
                "        constraint Shop_Gun_skin_Gun_id_fk_2\n" +
                "            references Gun_skin,\n" +
                "    Gun_skin_3 integer not null\n" +
                "        constraint Shop_Gun_skin_Gun_id_fk_3\n" +
                "            references Gun_skin,\n" +
                "    Gun_skin_4 integer not null\n" +
                "        constraint Shop_Gun_skin_Gun_id_fk_4\n" +
                "            references Gun_skin,\n" +
                "    Date_start text,\n" +
                "    Date_end   text\n" +
                ");";
        String user = "create table User\n" +
                "(\n" +
                "    User_id   integer not null\n" +
                "        primary key autoincrement\n" +
                "        unique,\n" +
                "    User_name text    not null,\n" +
                "    Password  text    not null,\n" +
                "    Role      integer not null\n" +
                ");";
        String userprofile = "create table User_Profile\n" +
                "(\n" +
                "    User_id         integer\n" +
                "        constraint User_Profile_User_User_id_fk\n" +
                "            references User,\n" +
                "    Server          text,\n" +
                "    Level           integer default 1,\n" +
                "    Exp             integer default 0 not null,\n" +
                "    Valorant_Point  integer default 0,\n" +
                "    Radianite_Point integer default 0,\n" +
                "    Free_Agent      integer default 0\n" +
                ");";
        sqLiteDatabase.execSQL(bundle);
        sqLiteDatabase.execSQL(gunskin);
        sqLiteDatabase.execSQL(shop);
        sqLiteDatabase.execSQL(user);
        sqLiteDatabase.execSQL(userprofile);
    }

    public void insertGun_Skin(String name, String price, String img_url, String bundle) {
        String sql = "insert into Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) values (?,?,?,?)";
        this.getWritableDatabase().execSQL(sql, new Object[]{name, Integer.parseInt(price), img_url, Integer.parseInt(bundle)});
    }

    public void updateGun_Skin(String name, String price, String img_url, String bundle, String id) {
        String sql = "update Gun_skin set Gun_skin_name = ?, Gun_price = ?, Gun_skin_image = ? , Bundle = ?  where Gun_id = ?;";
        this.getWritableDatabase().execSQL(sql, new Object[]{name, Integer.parseInt(price), img_url, Integer.parseInt(bundle), Integer.parseInt(id)});
    }

    public void deleteGun_Skin(String id) {
        String sql = "delete from Gun_skin where Gun_id = ?";
        this.getWritableDatabase().execSQL(sql, new Object[]{Integer.parseInt(id)});
    }

    public Cursor getAllGun() {
        String sql = "select * from Gun_skin";
        return this.getReadableDatabase()
                .rawQuery(sql, new String[]{});
    }

    public Cursor getGunByName(String name) {
        String sql = "select * from Gun_skin where Gun_skin_name = ?";
        return this.getReadableDatabase().rawQuery(sql, new String[]{name});
        }

    public Cursor getGunLikeName(String name) {
        String sql = "select * from Gun_skin where Gun_skin_name LIKE '%?%'";
        return this.getReadableDatabase().rawQuery(sql, new String[]{name});
    }



}
