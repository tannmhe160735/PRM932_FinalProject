package com.example.prm392_finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBContext extends SQLiteOpenHelper {
    private static final String DB_NAME = "PRM392_FinalProject.db";
    private static final int DB_VERSION = 11;

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
    public static final String TABLE_USER_COL_REMEMBER = "Remember";

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUN_SKIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUNDLE);
        onCreate(db);
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
                "    Date_start   Date,\n" +
                "    Date_end     Date\n" +
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
                "    Date_start Date,\n" +
                "    Date_end   Date\n" +
                ");";
        String user = "create table User\n" +
                "(\n" +
                "    User_id   integer               not null\n" +
                "        primary key autoincrement\n" +
                "        unique,\n" +
                "    User_name text                  not null,\n" +
                "    Password  text                  not null,\n" +
                "    Role      integer               not null,\n" +
                "    Remember  integer default 0 not null\n" +
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
        String insertBundle1 = "INSERT INTO Bundle (Bundle_name, Bundle_price, Bundle_Image, Date_start, Date_end) VALUES ('Spectrum', 10700, 'https://static.wikia.nocookie.net/valorant/images/f/ff/Bundle_Spectrum.png/revision/latest/scale-to-width-down/1000?cb=20210908214612', '2023-07-01', '2023-08-01')";
        String insertBundle2 = "INSERT INTO Bundle (Bundle_name, Bundle_price, Bundle_Image, Date_start, Date_end) VALUES ('Elderflame', 9900, 'https://static.wikia.nocookie.net/valorant/images/f/fa/Bundle_Elderflame.png/revision/latest/scale-to-width-down/1000?cb=20200710132118', '2023-08-01', '2023-09-01')";
        String insertBundle3 = "INSERT INTO Bundle (Bundle_name, Bundle_price, Bundle_Image, Date_start, Date_end) VALUES ('Protocol 781-A', 9900, 'https://static.wikia.nocookie.net/valorant/images/7/76/Bundle_Protocol_781-A.png/revision/latest/scale-to-width-down/1000?cb=20220110184809', '2023-09-01', '2023-10-01')";
        String insertBundle4 = "INSERT INTO Bundle (Bundle_name, Bundle_price, Bundle_Image, Date_start, Date_end) VALUES ('Glitchpop', 8700, 'https://static.wikia.nocookie.net/valorant/images/1/10/Bundle_Glitchpop.png/revision/latest/scale-to-width-down/1000?cb=20200805001530', '2023-10-01', '2023-11-01')";
        String insertBundle5 = "INSERT INTO Bundle (Bundle_name, Bundle_price, Bundle_Image, Date_start, Date_end) VALUES ('Singularity', 8700, 'https://static.wikia.nocookie.net/valorant/images/e/e5/Bundle_Singularity.png/revision/latest?cb=20210715135127', '2023-11-01', '2023-12-01')";
        String insertGunSkin1 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Phantom Spectrum', 2675, 'https://static.wikia.nocookie.net/valorant/images/f/f4/SPECTRUM_Phantom.png/revision/latest?cb=20210908214445', 1)";
        String insertGunSkin2 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Classic Spectrum', 2675, 'https://static.wikia.nocookie.net/valorant/images/d/df/SPECTRUM_Classic.png/revision/latest?cb=20210908214458', 1)";
        String insertGunSkin3 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Bulldog Spectrum', 2675, 'https://static.wikia.nocookie.net/valorant/images/0/0d/SPECTRUM_Bulldog.png/revision/latest?cb=20210908214451', 1)";
        String insertGunSkin4 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Guardian Spectrum', 2675, 'https://static.wikia.nocookie.net/valorant/images/f/f5/SPECTRUM_Guardian.png/revision/latest?cb=20210908214456', 1)";
        String insertGunSkin5 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Waveform', 2675, 'https://static.wikia.nocookie.net/valorant/images/e/e7/SPECTRUM_Waveform.png/revision/latest?cb=20210908214448', 1)";
        String insertGunSkin6 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Vandal Elderframe', 2475, 'https://static.wikia.nocookie.net/valorant/images/d/d2/Elderflame_Vandal.png/revision/latest?cb=20210707203727', 2)";
        String insertGunSkin7 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Frenzy Elderframe', 2475, 'https://static.wikia.nocookie.net/valorant/images/c/c4/Elderflame_Frenzy.png/revision/latest?cb=20210707203656', 2)";
        String insertGunSkin8 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Judge Elderframe', 2475, 'https://static.wikia.nocookie.net/valorant/images/c/c6/Elderflame_Judge.png/revision/latest?cb=20210707203713', 2)";
        String insertGunSkin9 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Operator Elderframe', 2475, 'https://static.wikia.nocookie.net/valorant/images/e/ed/Elderflame_Operator.png/revision/latest?cb=20210707203739', 2)";
        String insertGunSkin10 = "INSERT INTO Gun_skin (Gun_skin_name, Gun_price, Gun_skin_image, Bundle) VALUES ('Dagger Elderframe', 4950, 'https://static.wikia.nocookie.net/valorant/images/a/a9/Elderflame_Dagger.png/revision/latest?cb=20210707185625', 2)";
        String insertUser1 = "INSERT INTO User (User_name, Password, Role) VALUES ('Tan3z', '123', 1)";
        String insertUser2 = "INSERT INTO User (User_name, Password, Role) VALUES ('Admin', '123', 2)";
        String insertUserProfile1 = "INSERT INTO User_Profile (User_id, Server, Level, Exp, Valorant_Point, Radianite_Point, Free_Agent) VALUES (1, 'Châu Á', 123, 435, 9999, 500, 0)";
        String insertUserProfile2 = "INSERT INTO User_Profile (User_id, Server, Level, Exp, Valorant_Point, Radianite_Point, Free_Agent) VALUES (2, 'Châu Á', 999, 999, 9999, 999, 0)";
        String insertShop1 = "INSERT INTO Shop (User_id, Gun_skin_1, Gun_skin_2, Gun_skin_3, Gun_skin_4, Date_start, Date_end) VALUES (1, 1, 6, 3, 10, '2023-07-01', '2023-07-02')";
        String insertShop2 = "INSERT INTO Shop (User_id, Gun_skin_1, Gun_skin_2, Gun_skin_3, Gun_skin_4, Date_start, Date_end) VALUES (2, 2, 7, 4, 9, '2023-07-01', '2023-07-02')";

        sqLiteDatabase.execSQL(bundle);
        sqLiteDatabase.execSQL(insertBundle1);
        sqLiteDatabase.execSQL(insertBundle2);
        sqLiteDatabase.execSQL(insertBundle3);
        sqLiteDatabase.execSQL(insertBundle4);
        sqLiteDatabase.execSQL(insertBundle5);
        sqLiteDatabase.execSQL(gunskin);
        sqLiteDatabase.execSQL(insertGunSkin1);
        sqLiteDatabase.execSQL(insertGunSkin2);
        sqLiteDatabase.execSQL(insertGunSkin3);
        sqLiteDatabase.execSQL(insertGunSkin4);
        sqLiteDatabase.execSQL(insertGunSkin5);
        sqLiteDatabase.execSQL(insertGunSkin6);
        sqLiteDatabase.execSQL(insertGunSkin7);
        sqLiteDatabase.execSQL(insertGunSkin8);
        sqLiteDatabase.execSQL(insertGunSkin9);
        sqLiteDatabase.execSQL(insertGunSkin10);
        sqLiteDatabase.execSQL(user);
        sqLiteDatabase.execSQL(insertUser1);
        sqLiteDatabase.execSQL(insertUser2);
        sqLiteDatabase.execSQL(userprofile);
        sqLiteDatabase.execSQL(insertUserProfile1);
        sqLiteDatabase.execSQL(insertUserProfile2);
        sqLiteDatabase.execSQL(shop);
        sqLiteDatabase.execSQL(insertShop1);
        sqLiteDatabase.execSQL(insertShop2);
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
        String sql = "select * from Gun_skin where Gun_skin_name LIKE ?";
        return this.getReadableDatabase().rawQuery(sql, new String[]{"%" + name + "%"});
    }

    public Cursor getAllBundle() {
        String sql = "select * from Bundle";
        return this.getReadableDatabase()
                .rawQuery(sql, new String[]{});
    }

    public Cursor getBundleByName(String name) {
        String sql = "select * from Bundle where Bundle_name = ?";
        return this.getReadableDatabase().rawQuery(sql, new String[]{name});
    }

    public Cursor Login(String username, String password) {
        String sql = "select * from User where User_name = ? and Password = ?";
        return this.getReadableDatabase().rawQuery(sql, new String[]{username, password});
    }

    public Cursor getAllUser() {
        String sql = "select * from User";
        return this.getReadableDatabase().rawQuery(sql, new String[]{});
    }

    public void updateUser_Remember(int remember, int id) {
        String sql = "update User set Remember = ? where User_id = ?;";
        this.getWritableDatabase().execSQL(sql, new Object[]{remember, id});
    }

    public Cursor getUserById(String user_id) {
        String sql = "select * from User where User_id = ?";
        return this.getReadableDatabase().rawQuery(sql, new String[]{user_id});
    }


}
