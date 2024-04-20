package com.rakteamentertainment.arrozaplication.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    String Usuario = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT , nombre TEXT, apellido TEXT, correo_electronico TEXT, celular TEXT, img_foto TEXT)";

    String Producto = "CREATE TABLE IF NOT EXISTS producto (id INTEGER PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER REFERENCES usuario(id), linea1 REAL, linea2 REAL, linea3 REAL, linea4 REAL, linea5 REAL, linea6 REAL, linea7 REAL, linea8 REAL, linea9 REAL, linea10 REAL, linea11 REAL, linea12 REAL, linea13 REAL, linea14 REAL, linea15 REAL, linea16 REAL, linea17 REAL, linea18 REAL, linea19 REAL, linea20 REAL, linea21 REAL, linea22 REAL, linea23 REAL, linea24 REAL, linea25 REAL, linea26 REAL, linea27 REAL, linea28 REAL, linea29 REAL, linea30 REAL, linea31 REAL, linea32 REAL, linea33 REAL, linea34 REAL, linea35 REAL, linea36 REAL, linea37 REAL, linea38 REAL, linea39 REAL, linea40 REAL, linea41 REAL, linea42 REAL, linea43 REAL, linea44 REAL, linea45 REAL, linea46 REAL, linea47 REAL, linea48 REAL, linea49 REAL, linea50 REAL, linea51 REAL, linea52 REAL, linea53 REAL, linea54 REAL, linea55 REAL, linea56 REAL, linea57 REAL, linea58 REAL, linea59 REAL, linea60 REAL, linea61 REAL, linea62 REAL, linea63 REAL, linea64 REAL, linea65 REAL, linea66 REAL, linea67 REAL, linea68 REAL, linea69 REAL, linea70 REAL, linea71 REAL, linea72 REAL, linea73 REAL, linea74 REAL, linea75 REAL, linea76 REAL, linea77 REAL, linea78 REAL, linea79 REAL, linea80 REAL, linea81 REAL, linea82 REAL, linea83 REAL, linea84 REAL, linea85 REAL, linea86 REAL, linea87 REAL, linea88 REAL, linea89 REAL, linea90 REAL, linea91 REAL, linea92 REAL, linea93 REAL, linea94 REAL, linea95 REAL, linea96 REAL, linea97 REAL, linea98 REAL, linea99 REAL, linea100 REAL, linea101 REAL, linea102 REAL, linea103 REAL, linea104 REAL, linea105 REAL, linea106 REAL, linea107 REAL, linea108 REAL, linea109 REAL, linea110 REAL, linea111 REAL, linea112 REAL, linea113 REAL, linea114 REAL, linea115 REAL, linea116 REAL, linea117 REAL, linea118 REAL, linea119 REAL, linea120 REAL, linea121 REAL, linea122 REAL, linea123 REAL, linea124 REAL, linea125 REAL, linea126 REAL, linea127 REAL, linea128 REAL, linea129 REAL, linea130 REAL, linea131 REAL, linea132 REAL, linea133 REAL, linea134 REAL, linea135 REAL, linea136 REAL, linea137 REAL, linea138 REAL, linea139 REAL, linea140 REAL, linea141 REAL, linea142 REAL, linea143 REAL, linea144 REAL, linea145 REAL, linea146 REAL, linea147 REAL, linea148 REAL, linea149 REAL, linea150 REAL)";

    String Resumen = "CREATE TABLE IF NOT EXISTS resumen (id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, hora TEXT, id_usuario INTEGER REFERENCES usuario(id), id_producto INTEGER REFERENCES producto(id), total_dinero REAL, total_sacos REAL, tara REAL, suma_total_sacos REAL, precio REAL)";

    String Dato2 = "insert into usuario values(101,'Maria', 'Albines', 'Maria@gmail.com','999999','https://scontent.ftru4-2.fna.fbcdn.net/v/t1.6435-9/94038636_147668050098722_4920007701833973760_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=7f8c78&_nc_eui2=AeFgsJt3nCXbFcJJujuGK1FnxnjlKMEH39nGeOUowQff2WxGjYm7lQkGiSFouX3IzUgkX4nzz1invwuekGSwcwBj&_nc_ohc=SJEdmEq3Xo0AX9o4ldQ&_nc_ht=scontent.ftru4-2.fna&cb_e2o_trans=q&oh=00_AfBY6McchZKyAUAlFr4x2ggW2uCNIDtqb3Gz70nr99Il2Q&oe=65B99BD1')";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context){
        super(context, "BDARROZ.db", null, DATABASE_VERSION);
    }


    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Usuario);
        db.execSQL(Producto);
        db.execSQL(Resumen);
        db.execSQL(Dato2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
