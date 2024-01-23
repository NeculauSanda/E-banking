package org.poo.cb;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class ExchangeMoney implements Comanda{
    public void execute(EBanking eBanking, String[] intrari) {
        try (FileWriter fw = new FileWriter("src/main/java/output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n user  " + intrari[2] + " " + intrari[3] + " " + intrari[4] + " " + intrari[5]);

            Citire citim = new Citire();
            ActionUser actiuni = new ActionUser();
            // valorile de exchange
            Map<String, List<Double>> exchange = citim.citimExchange();

            out.println("!!!!!!!!_______  " + exchange.toString());

            try {
                User user = actiuni.returnareUser(eBanking, intrari[2]);
                Account contExtragere = actiuni.returnareCont(user.getConturi(), intrari[3]);
                out.println("contul de unde se i-a bani dupa ce s-au retras " + contExtragere.getName() + "  depozit " + contExtragere.getDepozit() + "\n");
                contExtragere.exchange(Double.parseDouble(intrari[5]), exchange, intrari[4]);
                Account contDepunere = actiuni.returnareCont(user.getConturi(), intrari[4]);
                out.println("contul de unde se pune bani dupa ce s-au retras " + contDepunere.getName() + "  depozit " + contDepunere.getDepozit() + "\n");
                contDepunere.adaugaBani(Double.parseDouble(intrari[5]));


                out.println("\n\n");
                BigDecimal decimal = BigDecimal.valueOf(contDepunere.getDepozit());
                BigDecimal decimalSec = BigDecimal.valueOf(contExtragere.getDepozit());
                out.println("contul de unde se pune bani dupa ce s-au retras " + contDepunere.getName() + "  depozit " + decimal.setScale(2, RoundingMode.HALF_UP) + "\n");
                out.println("contul de unde se i-a bani dupa ce s-au retras " + contExtragere.getName() + "  depozit " + decimalSec.setScale(2, RoundingMode.HALF_UP) + "\n");
            } catch (UserException e) {
                System.out.println(e.getMessage());
                out.println("-----> " + e.getMessage() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
