package org.poo.cb.Command;

import org.poo.cb.EBanking;

public interface Comanda {
    void execute(EBanking eBanking, String[] intrari, String[] args);
}
