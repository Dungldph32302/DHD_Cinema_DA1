package com.example.dhd_cinema_da1.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB="hh";
    public Dbhelper(@Nullable Context context) {
        super(context, DB,null,21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table NguoiDung(\n" +
                "      ID_ND integer primary key autoincrement,\n" +
                "      HoTen TEXT not null,\n" +
                "      Email TEXT not null,\n" +
                "      SDT TEXT not null,\n" +
                "      MatKhau TEXT not null)");

        db.execSQL("create table TheLoai(\n" +
                "      ID_TL integer primary key autoincrement,\n" +
                "      TenTheLoai TEXT not null)");

        db.execSQL("create table Phim(\n"+
                "      ID_Phim integer primary key autoincrement,\n" +
                "      ID_TL integer REFERENCES TheLoai(ID_TL),\n" +
                "      TenPhim TEXT not null,\n" +
                "      DaoDien TEXT not null,\n" +
                "      NgayPhatHanh TEXT ,\n" +
                "      Mota TEXT ,\n" +
                "      Anh TEXT)");

        db.execSQL("create table PhongChieu(\n" +
                "      ID_Phong integer primary key autoincrement,\n" +
                "      TenPhong TEXT not null,\n" +
                "      SoCho interger not null,\n" +
                "      LoaiPhong integer not null)");
        db.execSQL("create table Ghe(\n" +
                "      ID_Ghe integer primary key autoincrement,\n" +
                "      SoGhe TEXT not null)");

        db.execSQL("create table Phong_Ghe(\n" +
                "      ID integer primary key autoincrement,\n" +
                "      ID_Phong integer REFERENCES PhongChieu(ID_Phong),\n" +
                "      ID_Ghe integer REFERENCES Ghe(ID_Ghe),\n" +
                "      TrangThai integer not null)");
        db.execSQL("create table SuatChieu(\n" +
                "      ID_SC integer primary key autoincrement,\n" +
                "      ID_Phim integer REFERENCES Phim(ID_Phim),\n" +
                "      ID_Phong integer REFERENCES PhongChieu(ID_Phong),\n" +
                "      NgayChieu TEXT not null,\n" +
                "      GioChieu TEXT not null,\n" +
                "      Gia integer not null)");

        db.execSQL("create table Ve(\n" +
                "      ID_Ve integer primary key autoincrement,\n" +
                "      ID_ND integer REFERENCES NguoiDung(ID_ND),\n" +
                "      ID_SC integer REFERENCES SuatChieu(ID_SC),\n" +
                "      ID integer REFERENCES Phong_Ghe(ID),\n" +
                "      ThoiGian TEXT not null)");

        db.execSQL("create table DanhGiaPhim(\n" +
                "      ID_DG integer primary key autoincrement,\n" +
                "      ID_ND integer REFERENCES NguoiDung(ID_ND),\n" +
                "      ID_Phim integer REFERENCES Phim(ID_Phim),\n" +
                "      NoiDung TEXT,\n" +
                "      Sao int not null)");

        db.execSQL("create table HoaDon(\n" +
                "      ID_HD integer primary key autoincrement,\n" +
                "      ID_ND integer REFERENCES NguoiDung(ID_ND),\n" +
                "      ID_Ve integer REFERENCES Ve(ID_Ve),\n" +
                "      TongTien Integer not null,\n" +
                "      PhuongThuc integer not null,\n" +
                "      TrangThai int not null)");

// Thêm dữ liệu vào bảng NguoiDung
        db.execSQL("INSERT INTO NguoiDung (ID_ND, HoTen, Email, SDT, MatKhau) VALUES (1,'admin', 'hi@gmail.com', '123456789', 'admin')");

// Thêm dữ liệu vào bảng TheLoai
        db.execSQL("INSERT INTO TheLoai (TenTheLoai) VALUES ('TenTheLoai1')");

// Thêm dữ liệu vào bảng Phim
        db.execSQL("INSERT INTO Phim (ID_TL, TenPhim, DaoDien, NgayPhatHanh, Mota, Anh) VALUES ( 1,'TenPhim1', 'DaoDien1', '2023-11-12', 'Mo ta phim 1','android.resource://\" + context.getPackageName() + \"/drawable/img_3')");
        db.execSQL("INSERT INTO Phim (ID_TL,TenPhim, DaoDien, NgayPhatHanh, Mota, Anh) VALUES (1,'TenPhim2', 'DaoDien2', '2023-11-12', 'Mo ta phim 2', 'android.resource://\" + context.getPackageName() + \"/drawable/img_4')");
// Thêm dữ liệu vào bảng PhongChieu
        db.execSQL("INSERT INTO PhongChieu (TenPhong, SoCho, LoaiPhong) VALUES ('Phong1', 100, 1)");
        db.execSQL("INSERT INTO PhongChieu (TenPhong, SoCho, LoaiPhong) VALUES ('Phong2', 50, 2)");
// Thêm dữ liệu vào bảng Ghe
        for (int i=1;i<=10;i++){
            String soGhe = "A" + i;
            String soGheb = "B" + i;
            String soGhec = "C" + i;
            String soGhed = "D" + i;
            String soGhee = "E" + i;
            db.execSQL("INSERT INTO Ghe (SoGhe) VALUES (?)", new Object[]{soGhe});
            db.execSQL("INSERT INTO Ghe (SoGhe) VALUES (?)", new Object[]{soGheb});
            db.execSQL("INSERT INTO Ghe (SoGhe) VALUES (?)", new Object[]{soGhec});
            db.execSQL("INSERT INTO Ghe (SoGhe) VALUES (?)", new Object[]{soGhed});
            db.execSQL("INSERT INTO Ghe (SoGhe) VALUES (?)", new Object[]{soGhee});

        }
        db.execSQL("INSERT INTO Phong_Ghe (ID_Phong, ID_Ghe, TrangThai) VALUES (1, 1, 1)");
// Thêm dữ liệu vào bảng SuatChieu
        db.execSQL("INSERT INTO SuatChieu (ID_Phim, ID_Phong, NgayChieu,GioChieu, Gia) VALUES (1, 1, '10-20-2023','18:00', 100000)");
        db.execSQL("INSERT INTO SuatChieu (ID_Phim, ID_Phong, NgayChieu,GioChieu, Gia) VALUES (1, 2,'10-20-2023', '20:00', 100000)");
// Thêm dữ liệu vào bảng Ve
        db.execSQL("INSERT INTO Ve (ID_ND, ID_SC, ID, ThoiGian) VALUES (1, 1, 1, '2023-11-12 18:00')");
// Thêm dữ liệu vào bảng DanhGiaPhim
        db.execSQL("INSERT INTO DanhGiaPhim (ID_ND, ID_Phim, NoiDung, Sao) VALUES (1, 1, 'Danh gia phim 1', 5)");
// Thêm dữ liệu vào bảng HoaDon
        db.execSQL("INSERT INTO HoaDon (ID_ND, ID_Ve, TongTien, PhuongThuc, TrangThai) VALUES (1, 1, 100000, 1, 1)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS NguoiDung");
            db.execSQL("DROP TABLE IF EXISTS TheLoai");
            db.execSQL("DROP TABLE IF EXISTS Phim");
            db.execSQL("DROP TABLE IF EXISTS PhongChieu");
            db.execSQL("DROP TABLE IF EXISTS Ghe");
            db.execSQL("DROP TABLE IF EXISTS SuatChieu");
            db.execSQL("DROP TABLE IF EXISTS Ve");
            db.execSQL("DROP TABLE IF EXISTS DanhGiaPhim");
            db.execSQL("DROP TABLE IF EXISTS HoaDon");
            onCreate(db);// goi lai ham  onCreate


        }
    }
}
