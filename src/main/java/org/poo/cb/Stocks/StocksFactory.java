package org.poo.cb.Stocks;

public class StocksFactory {
    public Stocks getStocks(String stock) {
        if(stock.equals("TSLA")) {
            return new TSLA();
        } else if(stock.equals("PFE")) {
            return new PFE();
        } else if(stock.equals("KO")) {
            return new KO();
        } else if(stock.equals("JPM")) {
            return new JPM();
        } else if(stock.equals("AAPL")) {
            return new AAPL();
        } else if(stock.equals("AMZN")) {
            return new AMZN();
        }
        return null;
    }
}
