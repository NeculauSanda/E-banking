package org.poo.cb.Command;

import org.poo.cb.*;

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
