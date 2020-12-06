package br.edu.ufam.icomp.stockskywalker.storage;

public class User {
    private int idUser;
    private String name;
    private String email;
    private String category;

    public User(int idUser, String name, String email, String category){
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.category = category;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCategory(){
        return category;
    }
}
