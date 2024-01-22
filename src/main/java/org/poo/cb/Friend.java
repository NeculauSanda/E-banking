package org.poo.cb;

import java.util.Map;

public class Friend implements Friends{

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
}
