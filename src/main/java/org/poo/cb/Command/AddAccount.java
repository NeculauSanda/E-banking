package org.poo.cb.Command;

import org.poo.cb.*;
import org.poo.cb.typeAccount.Account;
import org.poo.cb.typeAccount.AccountFactory;

public class AddAccount implements Comanda {
    public void execute(EBanking eBanking, String[] intrari, String[] args) {
        ActionUser actionUser = new ActionUser();
        User user = actionUser.returnareUser(eBanking,intrari[2]);

        AccountFactory accountFactory = new AccountFactory();
        Account account = accountFactory.getAccount(intrari[3]);

        try {
            user.adaugaCont(eBanking, account);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }
}
