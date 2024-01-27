package org.poo.cb.Stocks;

public class StockPrincipal implements Stocks{
    private String nume;

    private int stock;

    public StockPrincipal(String nume) {
        this.nume = nume;
    }

    public int getStock() {
        return stock;
    }
    @Override
    public void adaugaStock(int stock) {
        this.stock = this.stock + stock;
    }

    @Override
    public String toString() {
        return "{\"stockname\":\"" + nume + "\",\"amount\":" + stock + "}";
    }
}
