package org.poo.cb;

import org.poo.cb.Stocks.Stocks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ListPortofolio implements Comanda{
    public void execute(EBanking eBanking, String[] intrari, String[] args) {
            ActionUser actiuni = new ActionUser();
            try {
                User user = actiuni.returnareUser(eBanking, intrari[2]);
                Map<String, Stocks> stockuri = user.getStocks();
                System.out.print("{\"stocks\":[");
                for (Map.Entry<String, Stocks> stock : stockuri.entrySet()) {
                        System.out.print(stock.toString());
                }
                System.out.print("],\"accounts\":[");
                Map<String, Account> conturi = user.getConturi();

                boolean first = false;
                for(Map.Entry<String, Account> cont : conturi.entrySet()) {
                    BigDecimal decimal = BigDecimal.valueOf(cont.getValue().getDepozit());
                    if(!first) {
                        System.out.print("{\"currencyName\":\"" + cont.getValue().getName() + "\",\"amount\":\"" + decimal.setScale(2, RoundingMode.HALF_UP) + "\"}");
                        first = true;
                    } else {
                        System.out.print( ",{\"currencyName\":\"" + cont.getValue().getName() + "\",\"amount\":\"" + decimal.setScale(2, RoundingMode.HALF_UP) + "\"}");
                    }
                    }
                System.out.println("]}");
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }

    }
}
