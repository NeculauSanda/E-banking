package org.poo.cb;

import org.poo.cb.Stocks.Stocks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ListPortofolio implements Comanda{
    public void execute(EBanking eBanking, String[] intrari) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n email  " + intrari[2]);
            ActionUser actiuni = new ActionUser();
            try {
                User user = actiuni.returnareUser(eBanking, intrari[2]);
                out.println("stockuri " + user.getStocks());
                Map<String, Stocks> stockuri = user.getStocks();
                System.out.print("{\"stocks\":[");
                for (Map.Entry<String, Stocks> stock : stockuri.entrySet()) {
//                    if(stock == null) {
                        System.out.print(stock.toString());
//                    }
                }
                System.out.print("],\"accounts\":[");
                Map<String, Account> conturi = user.getConturi();

//                Map<String, Account> conturiSortate = conturi.entrySet()
//                        .stream()
//                        .sorted((entry1, entry2) -> Double.compare(entry2.getValue().getDepozit(), entry1.getValue().getDepozit()))
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
                boolean first = false;
                for(Map.Entry<String, Account> cont : conturi.entrySet()) {
                    BigDecimal decimal = BigDecimal.valueOf(cont.getValue().getDepozit());
                    if(!first) {
                        System.out.print("{\"currencyName\":\"" + cont.getValue().getName() + "\",\"amount\":\"" + decimal.setScale(2, RoundingMode.HALF_UP) + "\"}");
                        first = true;
                    } else {
                        System.out.print( ",{\"currencyName\":\"" + cont.getValue().getName() + "\",\"amount\":\"" + decimal.setScale(2, RoundingMode.HALF_UP) + "\"}");
                    }
                    out.println("------> " + cont.getValue().getName() + "  \"amount\":\"  " + decimal.setScale(2, RoundingMode.HALF_UP));
                }
                System.out.println("]}");
            } catch (UserException e) {
                System.out.println(e.getMessage());
                out.println("--->" + e.getMessage());
            }


        } catch (IOException e) {
            e.getMessage();
        }

    }
}
