package org.poo.cb;

import java.util.Map;

public interface Actions {
    User returnareUser(EBanking eBanking, String email);
    Account returnareCont(Map<String, Account> conturi, String tipCont);

    void verificarePrieten(User user, String emailPrieten);
}
