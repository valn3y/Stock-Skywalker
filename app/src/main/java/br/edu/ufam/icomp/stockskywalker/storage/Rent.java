package br.edu.ufam.icomp.stockskywalker.storage;

public class Rent {
    private int idRent;
    private String startDate;
    private String endDate;
    private int security;
    private int keyExtra;
    private int controlWeather;
    private int idLocal;

    public Rent(int idRent, String startDate, String endDate, int security, int keyExtra, int controlWeather, int idLocal) {
        this.idRent = idRent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.security = security;
        this.keyExtra = keyExtra;
        this.controlWeather = controlWeather;
        this.idLocal = idLocal;
    }

    public int getIdRent() {
        return idRent;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean getSecurity() {
        return security == 1 ? true : false;
    }

    public boolean getKeyExtra() {
        return keyExtra == 1 ? true : false;
    }

    public boolean getControlWeather() {
        return controlWeather == 1 ? true : false;
    }

    public int getIdLocal() {
        return idLocal;
    }
}
