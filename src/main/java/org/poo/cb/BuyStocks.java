package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class BuyStocks implements Comanda {

    public void execute(EBanking eBanking, String[] intrari, String[] args) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n user  " + intrari[2] + " " + intrari[3] + " " + intrari[4]);
            Citire citim = new Citire();
            Map<String, List<String>> stockValue = citim.citimStocks(args[1]);

            out.println("----> " + stockValue);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
