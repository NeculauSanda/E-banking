package org.poo.cb;

import java.util.List;
import java.util.Map;

public class ActionUser implements Actions {

    @Override
    public User returnareUser(EBanking eBanking, String email) {
        Map<String, User> utilizatori= eBanking.getUtilizatori();
        for(Map.Entry<String, User> utilizator : utilizatori.entrySet()) {
            if(utilizator.getKey().equals(email)) {
                return utilizator.getValue();
            }
        }
        throw new UserException("User with " + email + " doesn’t exist") ;
    }

    public Account returnareCont(Map<String, Account> conturi, String tipCont) {
        for (Map.Entry<String, Account> cont : conturi.entrySet()) {
            if(cont.getKey().equals(tipCont)) {
                return cont.getValue();
            }
        }
        return null;
    }

    @Override
    public void verificarePrieten(User user, String emailPrieten) {
        List <User> prieteni = user.getPrieteni();
        for(User prieten : prieteni) {
            if(!prieten.getEmail().equals(emailPrieten)) {
                throw new UserException("You are not allowed to transfer money to " + emailPrieten);
            }
        }
    }
}
