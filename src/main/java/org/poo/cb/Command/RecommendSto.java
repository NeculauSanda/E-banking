package org.poo.cb.Command;

import org.poo.cb.Citire;
import org.poo.cb.EBanking;
import org.poo.cb.Stocks.StockAction;
import org.poo.cb.Stocks.StockRecommend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class RecommendSto implements Comanda {
    public void execute(EBanking eBanking, String[] intrari, String[] args) {

        Citire citim = new Citire();
        Map<String, List<String>> stockValue = citim.citimStocks(args[1]);

        StockAction stock = new StockAction(stockValue, eBanking,null,
                0, null);
        StockRecommend stockRecommend = new StockRecommend(stock);
        stockRecommend.execute();

    }

}
