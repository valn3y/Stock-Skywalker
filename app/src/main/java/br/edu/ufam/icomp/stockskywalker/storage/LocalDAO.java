package br.edu.ufam.icomp.stockskywalker.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LocalDAO {
    private Context context;
    private SQLiteDatabase database;

    public LocalDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Local> getLocals(){
        ArrayList<Local> locals =  new ArrayList<Local>();
        String sql = "SELECT * FROM local WHERE available=1";
        try{
            Cursor cursor = database.rawQuery(sql, null);
            while(cursor.moveToNext()){
                int idLocal = cursor.getInt(0);
                String category = cursor.getString(1);
                String address = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                int depth = cursor.getInt(5);
                int price = cursor.getInt(6);
                int available = cursor.getInt(7);
                int idUser = cursor.getInt(8);
                int idTenant = cursor.getInt(9);
                locals.add(new Local(idLocal, category, address, width, height, depth, price, available, idUser, idTenant));
            }
            return locals;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Local> getLocalsRent(int id){
        ArrayList<Local> locals =  new ArrayList<Local>();
        String sql = "SELECT * FROM local WHERE idTenant=" + id;
        try{
            Cursor cursor = database.rawQuery(sql, null);
            while(cursor.moveToNext()){
                int idLocal = cursor.getInt(0);
                String category = cursor.getString(1);
                String address = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                int depth = cursor.getInt(5);
                int price = cursor.getInt(6);
                int available = cursor.getInt(7);
                int idUser = cursor.getInt(8);
                int idTenant = cursor.getInt(9);
                locals.add(new Local(idLocal, category, address, width, height, depth, price, available, idUser, idTenant));
            }
            return locals;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Local> getLocalByAdmin(int id){
        ArrayList<Local> locals = new ArrayList<Local>();
        String sql = "SELECT * FROM local WHERE idUser=" + id + " AND available=1";
        try{
            Cursor cursor = database.rawQuery(sql, null);
            while(cursor.moveToNext()){
                int idLocal = cursor.getInt(0);
                String category = cursor.getString(1);
                String address = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                int depth = cursor.getInt(5);
                int price = cursor.getInt(6);
                int available = cursor.getInt(7);
                int idUser = cursor.getInt(8);
                int idTenant = cursor.getInt(9);
                locals.add(new Local(idLocal, category, address, width, height, depth, price, available, idUser, idTenant));
            }
            return locals;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Local> getLocalRentedByAdmin(int id){
        ArrayList<Local> locals = new ArrayList<Local>();
        String sql = "SELECT * FROM local WHERE idUser=" + id + " AND available=0";
        try{
            Cursor cursor = database.rawQuery(sql, null);
            while(cursor.moveToNext()){
                System.out.println("ID: " + cursor.getInt(0));
                int idLocal = cursor.getInt(0);
                String category = cursor.getString(1);
                String address = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                int depth = cursor.getInt(5);
                int price = cursor.getInt(6);
                int available = cursor.getInt(7);
                int idUser = cursor.getInt(8);
                int idTenant = cursor.getInt(9);
                locals.add(new Local(idLocal, category, address, width, height, depth, price, available, idUser, idTenant));
            }
            return locals;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public boolean createLocal(String category, String address, int width, int height, int depth, int price, Boolean available, int idUser){
        int newAvailable = available ? 1 : 0;
        String sql = "INSERT INTO local (category, address, width, height, depth, price, available, idUser) VALUES ('"
                + category + "', '" + address + "', " + width + ", " + height + ", " + depth + ", " + price + ", " + newAvailable + ", " + idUser + ")";
        try{
            database.execSQL(sql);
            return true;
        }catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    public boolean updateLocal(String category, String address, int width, int height, int depth, int price, int idLocal){
        String sql = "UPDATE local SET category='" + category + "', address='" + address + "', width=" + width + ", height=" + height + ", depth=" + depth + ", price=" + price + " WHERE idLocal=" + idLocal;
        try{
            database.execSQL(sql);
            return true;
        }catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteLocal(int id){
        String sql = "DELETE FROM local WHERE idLocal=" + id;
        try{
            database.execSQL(sql);
            return true;
        }catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }


    public void changeAvailable(boolean available, int idLocal, int idTenant){
        int newAvailable = available ? 1 : 0;
        String sql = "UPDATE local SET available=" + newAvailable + ", idTenant=" + idTenant + " WHERE idLocal=" + idLocal;
        try{
            database.execSQL(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
