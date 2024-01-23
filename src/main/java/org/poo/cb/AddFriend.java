package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddFriend implements Comanda {
    @Override
    public void execute(EBanking eBanking, String[] intrari) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);    // eliminat
             BufferedWriter bw = new BufferedWriter(fw); // eliminat
             PrintWriter out = new PrintWriter(bw)) { // eliminat

            out.println("\n user  " + intrari[2] + " " + intrari[3]);  // eliminat
            ActionUser prieten = new ActionUser();
            try {
                User userPrincipal = prieten.returnareUser(eBanking, intrari[2]);
                User userSec = prieten.returnareUser(eBanking, intrari[3]);  // eliminat
                out.println("\n user returnat  " + userPrincipal.toString() + userPrincipal.getPrieteni());  // eliminat
                userPrincipal.adaugaPrieteni(eBanking, intrari[3]);
                out.println("\n user returnat  " + userPrincipal.getPrieteni());  // eliminat
                out.println("\n prieten returnat  " + userSec.toString() + userSec.getPrieteni());  // eliminat
            } catch (UserException e) {
                System.out.println(e.getMessage());
                out.println("-----> " + e.getMessage() + "\n");   // eliminat
            }
        } catch (IOException b) {
            b.getMessage();
        }
    }
}
