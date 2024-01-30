package org.poo.cb.Command;

import org.poo.cb.*;
import org.poo.cb.Stocks.StockAction;
import org.poo.cb.Stocks.StockBuy;
import org.poo.cb.Stocks.Stocks;
import org.poo.cb.typeAccount.Account;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BuyStocks implements Comanda {

    public void execute(EBanking eBanking, String[] intrari, String[] args) {
        //citim stock-urile
        Citire citim = new Citire();
        Map<String, List<String>> stockValue = citim.citimStocks(args[1]);

        //facem comanda
        StockAction stock = new StockAction(stockValue, eBanking, intrari[3],
                Integer.parseInt(intrari[4]), intrari[2]);

        StockBuy buy = new StockBuy(stock);

        try {
            buy.execute();
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }
}
