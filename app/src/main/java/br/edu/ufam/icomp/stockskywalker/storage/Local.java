package br.edu.ufam.icomp.stockskywalker.storage;

public class Local {
    private int idLocal;
    private String category;
    private String address;
    private int width;
    private int height;
    private int depth;
    private int price;
    private int available;
    private int idUser;
    private int idTenant;

    public Local(int idLocal, String category, String address, int width, int height, int depth, int price, int available, int idUser, int idTenant){
        this.idLocal = idLocal;
        this.category = category;
        this.address = address;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.price = price;
        this.available = available;
        this.idUser = idUser;
        this.idTenant = idTenant;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getWidth() {
        return width + "m";
    }

    public int getWidthInt(){return width;}

    public String getHeight() {
        return height + "m";
    }

    public int getHeightInt() {return height;}

    public String getDepth() {
        return depth + "m";
    }

    public int getDepthInt() {return depth;}

    public String getPrice() {
        return "R$ " + price + ",00";
    }

    public int getPriceInt() {return price;}

    public boolean getAvailable() {
        return available == 1 ? true : false;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdTenant() {
        return idTenant;
    }
}
