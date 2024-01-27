package org.poo.cb.typeAccount;

import org.poo.cb.typeAccount.*;

public class AccountFactory{
        public Account getAccount(String cont) {
            if(cont.equals("USD")) {
                return new USD();
            } else if(cont.equals("EUR")) {
                return new EUR();
            } else if(cont.equals("GBP")) {
                return new GBP();
            } else if(cont.equals("JPY")) {
                return new JPY();
            } else if(cont.equals("CAD")) {
                return new CAD();
            }
            return null;
        }
}
