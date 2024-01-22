package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RecommendSto implements Comanda{
    public void execute(EBanking eBanking, String[] intrari) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n STOCKS  ");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
