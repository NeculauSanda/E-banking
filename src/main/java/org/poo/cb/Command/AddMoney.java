package org.poo.cb.Command;

import org.poo.cb.*;
import org.poo.cb.typeAccount.Account;

public class AddMoney implements Comanda {
    public void execute(EBanking eBanking, String[] intrari, String[] args) {
            ActionUser actionUser = new ActionUser();
            // selectam userul
            User user = actionUser.returnareUser(eBanking,intrari[2]);
            // selectam contul in care vrem sa adaugam bani
            Account cont = actionUser.returnareCont(user.getConturi(), intrari[3]);
            cont.adaugaBani(Double.parseDouble(intrari[4]));
    }
}
