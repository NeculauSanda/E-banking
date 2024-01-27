package org.poo.cb.Stocks;

public class StockBuy implements StockActionInt{
    private final StockAction stock;

    public StockBuy(StockAction stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}
