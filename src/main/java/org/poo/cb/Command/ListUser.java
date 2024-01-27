package org.poo.cb.Command;

import org.poo.cb.ActionUser;
import org.poo.cb.EBanking;
import org.poo.cb.User;
import org.poo.cb.UserException;

import java.util.List;

public class ListUser implements Comanda {
    @Override
    public void execute(EBanking eBanking, String[] intrari, String[] args) {

            ActionUser actionUser = new ActionUser();
            try {
                User user = actionUser.returnareUser(eBanking, intrari[2]);
                List<User> prieteni = user.getPrieteni();
                System.out.print("{\"email\":\"" + user.getEmail() + "\",\"firstname\":\"" + user.getPrenume() + "\",\"lastname\":\"" +
                        user.getNume() + "\",\"address\":\"" + user.getAdresa() + "\",\"friends\":[");
                int first = 0;
                for (User prieten : prieteni) {
                    if (first == 0) {
                        System.out.print("\"" + prieten.getEmail() + "\"");
                        first = 1;
                    } else {
                        System.out.print(",\"" + prieten.getEmail() + "\"");
                    }
                }
                System.out.println("]}");
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
    }
}
