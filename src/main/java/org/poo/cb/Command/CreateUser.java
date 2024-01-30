package org.poo.cb.Command;

import org.poo.cb.EBanking;
import org.poo.cb.User;
import org.poo.cb.UserException;

public class CreateUser implements Comanda {

    public String adresaConcat(String[] input) {
        String adresa = "";
        for (int i = 5; i < input.length; i++) {
            if(i == input.length - 1) {
                adresa = adresa + input[i];
            } else {
                adresa = adresa + input[i] + " ";
            }
        }
        return adresa;
    }
    @Override
    public void execute(EBanking eBanking, String[] intrari, String[] args) {

            String adresa = adresaConcat(intrari);

            try {
                // am utilizat builder design pentru utilizator
                User user = new User.UserBuilder(intrari[2], intrari[3], intrari[4])
                        .adresa(adresa)
                        .build(eBanking);

                // se aduga utilizatorul in baza de date, deoarece este bun
                eBanking.adaugaUser(user);

            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
    }
}
