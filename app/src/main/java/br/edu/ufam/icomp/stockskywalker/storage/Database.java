package br.edu.ufam.icomp.stockskywalker.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StockSkywalker.db";

    private static final String SQL_CREATE_USER = "CREATE TABLE user ( " +
            "idUser INTEGER  PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "email TEXT NOT NULL UNIQUE," +
            "password TEXT NOT NULL," +
            "category TEXT NOT NULL)";
    private static final String SQL_CREATE_LOCAL = "CREATE TABLE local (" +
            "idLocal INTEGER PRIMARY KEY AUTOINCREMENT," +
            "category TEXT NOT NULL," +
            "address TEXT NOT NULL," +
            "width INTEGER NOT NULL," +
            "height INTEGER NOT NULL," +
            "depth INTEGER NOT NULL," +
            "price INTEGER NOT NULL," +
            "available BOOLEAN DEFAULT 1," +
            "idUser INTEGER NOT NULL," +
            "idTenant INTEGER," +
            "CONSTRAINT fkIdUser FOREIGN KEY (idUser) REFERENCES user(idUser) ON DELETE CASCADE ON UPDATE CASCADE)";
    private static final String SQL_CREATE_RENT = "CREATE TABLE rent (" +
            "idRent INTEGER PRIMARY KEY AUTOINCREMENT," +
            "startDate DATE NOT NULL," +
            "endDate DATE NOT NULL," +
            "security BOOLEAN NOT NULL," +
            "keyExtra BOOLEAN NOT NULL," +
            "controlWeather BOOLEAN NOT NULL," +
            "idLocal INTEGER NOT NULL," +
            "CONSTRAINT fkIdLocal FOREIGN KEY (idLocal) REFERENCES local(idLocal) ON DELETE CASCADE ON UPDATE CASCADE)";

    private static final String SQL_POPULATE_USER_ADMIN = "INSERT INTO user (name, email, password, category) VALUES (" +
            "'admin', 'admin@live.com', 'admin123', 'admin')";
    private static final String SQL_POPULATE_USER_CLIENT = "INSERT INTO user (name, email, password, category) VALUES (" +
            "'client', 'client@live.com', 'client123', 'client')";
    private static final String SQL_POPULATE_LOCAL_1 = "INSERT INTO local (category, address, width, height, depth, price, available, idUser) VALUES (" +
            "'Guarda-Volumes', 'Av. São José', 150, 150, 150, 1000, 1, 1)";
    private static final String SQL_POPULATE_LOCAL_2 = "INSERT INTO local (category, address, width, height, depth, price, available, idUser, idTenant) VALUES (" +
            "'Contêiner', 'Av. Autaz Mirim', 50, 30, 150, 500, 0, 1, 2)";
    private static final String SQL_POPULATE_LOCAL_3 = "INSERT INTO local (category, address, width, height, depth, price, available, idUser) VALUES (" +
            "'Quarto', 'Av. Constantino Nery', 120, 10, 130, 500, 1, 1)";
    private static final String SQL_POPULATE_LOCAL_4 = "INSERT INTO local (category, address, width, height, depth, price, available, idUser) VALUES (" +
            "'Galpão', 'Av. Buriti', 50, 50, 50, 10, 1, 1)";
    private static final String SQL_POPULATE_RENT = "INSERT INTO rent (startDate, endDate, security, keyExtra, controlWeather, idLocal) VALUES (" +
            "'2020-02-15', '2020-03-15', 1, 1, 0, 2)";

    private static final String SQL_DELETE_USER = "DROP TABLE user";
    private static final String SQL_DELETE_LOCAL = "DROP TABLE local";
    private static final String SQL_DELETE_RENT = "DROP TABLE RENT";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_LOCAL);
        db.execSQL(SQL_CREATE_RENT);

        db.execSQL(SQL_POPULATE_USER_ADMIN);
        db.execSQL(SQL_POPULATE_USER_CLIENT);
        db.execSQL(SQL_POPULATE_LOCAL_1);
        db.execSQL(SQL_POPULATE_LOCAL_2);
        db.execSQL(SQL_POPULATE_LOCAL_3);
        db.execSQL(SQL_POPULATE_LOCAL_4);

        db.execSQL(SQL_POPULATE_RENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_RENT);
        db.execSQL(SQL_DELETE_LOCAL);
        db.execSQL(SQL_DELETE_USER);

        onCreate(db);
    }
}
