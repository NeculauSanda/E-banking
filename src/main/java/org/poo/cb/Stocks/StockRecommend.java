package org.poo.cb.Stocks;

public class StockRecommend implements  StockActionInt {
    private final StockAction stock;

    public StockRecommend(StockAction stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.recommend();
    }
}
