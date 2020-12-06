package br.edu.ufam.icomp.stockskywalker.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
    private Context context;
    private SQLiteDatabase database;

    public UserDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public User get(String email, String password) {
        String sql = "SELECT * FROM user WHERE email='" + email + "' AND password='"+ password  +"'";
        try{
            Cursor cursor = database.rawQuery(sql, null);
            if(cursor.moveToNext()){
                int idUser = cursor.getInt(0);
                String name = cursor.getString(1);
                String emailUser = cursor.getString(2);
                String category = cursor.getString(4);
                return new User(idUser, name, emailUser, category);
            }

            return null;
        } catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean add(String name, String email, String password, String category){
        String sql = "INSERT INTO user (name, email, password, category) VALUES ('" + name + "', '" + email + "', '"+ password + "', '" + category + "')";
        try {
            database.execSQL(sql);
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }
}
