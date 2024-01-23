package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class AddAccount implements Comanda{
    public void execute(EBanking eBanking, String[] intrari) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n user  " + intrari[2] + " " + intrari[3]);
            ActionUser actionUser = new ActionUser();
            User user = actionUser.returnareUser(eBanking,intrari[2]);
            AccountFactory accountFactory = new AccountFactory();
            Account account = accountFactory.getAccount(intrari[3]);
            try {
                user.adaugaCont(eBanking, account);
            } catch (UserException e) {
                System.out.println(e.getMessage());
                out.println("-----> " + e.getMessage() + "\n");
            }
            Map<String, Account>  accounts = user.getConturi();
            out.println("-----------------------");
            out.println("user " + user.getEmail() + " conturi " + "\n");
            for(Map.Entry<String, Account> accountEntry : accounts.entrySet()) {
                out.print(accountEntry.getKey() + "   " + accountEntry.getValue().getName());
            }
            out.println();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
