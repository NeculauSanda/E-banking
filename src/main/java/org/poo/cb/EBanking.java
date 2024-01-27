package org.poo.cb;

import java.util.HashMap;
import java.util.Map;

public class EBanking {
    private static EBanking eBanking;
    private final Map<String, User> utilizatori;

    private EBanking() {
        this.utilizatori = new HashMap<>();
    }

    public static synchronized EBanking geteBanking() {
        if(eBanking == null) {
            eBanking = new EBanking();
        }
        return eBanking;
    }

    // adaugam user
    public void adaugaUser(User user) {
        utilizatori.put(user.getEmail(), user);
    }

    public Map<String, User> getUtilizatori() {
        return utilizatori;
    }

    // dupa fiecare test curatam baza de date cu utilizatori
    public void curataMap(){
        utilizatori.clear();
    }
}
