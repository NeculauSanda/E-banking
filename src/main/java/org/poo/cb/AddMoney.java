package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class AddMoney implements Comanda{
    public void execute(EBanking eBanking, String[] intrari) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n user  " + intrari[2] + " " + intrari[3] + " " + intrari[4]);
            ActionUser actionUser = new ActionUser();
            // selectam userul
            User user = actionUser.returnareUser(eBanking,intrari[2]);
            // selectam contul in care vrem sa adaugam bani
            Account cont = actionUser.returnareCont(user.getConturi(), intrari[3]);
            out.println("<__________________MONEY_____________________>");
            out.println("prima data -->  " + cont.getDepozit() + "tip cont  " + cont.getName());
            cont.adaugaBani(Double.parseDouble(intrari[4]));

            out.println("dupa ce am adugat bani -->  " + cont.getDepozit());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
