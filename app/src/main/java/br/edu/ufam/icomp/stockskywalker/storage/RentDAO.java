package br.edu.ufam.icomp.stockskywalker.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RentDAO {
    private Context context;
    private SQLiteDatabase database;

    public RentDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public Rent getRent(int id){
        System.out.println("ID para rent " + id);
        String sql = "SELECT * FROM rent WHERE idLocal=" + id;
        try{
            Cursor cursor = database.rawQuery(sql, null);
            if(cursor.moveToNext()){
                int idRent = cursor.getInt(0);
                String startDate = cursor.getString(1);
                String endDate = cursor.getString(2);
                int security = cursor.getInt(3);
                int controlWeather = cursor.getInt(4);
                int keyExtra = cursor.getInt(5);
                int idLocal = cursor.getInt(6);
                return new Rent(idRent, startDate, endDate, security, keyExtra, controlWeather, idLocal);
            }

            return null;
        } catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public boolean createRent(String startDate, String endDate, boolean security, boolean keyExtra, boolean controlWeather, int idLocal){
        int securitySQL = security ? 1 : 0;
        int keyExtraSQL = keyExtra ? 1 : 0;
        int controlWeatherSQL = controlWeather ? 1 : 0;

        String sql = "INSERT INTO rent (startDate, endDate, security, keyExtra, controlWeather, idLocal) VALUES ('" +
                startDate + "', '" + endDate + "', " + securitySQL + ", " + keyExtraSQL + ", " + controlWeatherSQL + ", " +  idLocal + ")";

        try{
            database.execSQL(sql);
            return true;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
}
