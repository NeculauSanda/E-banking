package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddFriend implements Comanda {
    @Override
    public void execute(EBanking eBanking, String[] intrari, String[] args) {
        ActionUser prieten = new ActionUser();
        try {
            User userPrincipal = prieten.returnareUser(eBanking, intrari[2]);
            userPrincipal.adaugaPrieteni(eBanking, intrari[3]);

        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }
}
