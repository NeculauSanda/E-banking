package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListUser implements Comanda{
    @Override
    public void execute(EBanking eBanking, String[] intrari) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n email  " + intrari[2]);
            ActionUser actionUser = new ActionUser();
            User user = actionUser.returnareUser(eBanking, intrari[2]);
            List<User> prieteni = user.getPrieteni();
            System.out.print("{\"email\":\"" + intrari[2] + "\",\"firstname\":\"" + user.getPrenume() + "\",\"lastname\":\"" + user.getNume() +
                    "\",\"address\":\"" + user.getAdresa() + "\",\"friends\":[");
            int first = 0;
            for (User prieten : prieteni) {
                if(first == 0) {
                    System.out.print("\"" + prieten.getEmail() + "\"");
                    first = 1;
                } else {
                    System.out.print(",\"" + prieten.getEmail() + "\"");
                }
            }
            System.out.println("]}");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
