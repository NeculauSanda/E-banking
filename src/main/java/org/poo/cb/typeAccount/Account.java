package org.poo.cb.typeAccount;

import java.util.List;
import java.util.Map;

public interface Account {
    String getName();
    Double getDepozit();
    void adaugaBani(Double suma);

    void retragereBani(Double suma);

    void exchange(Double suma, Map<String, List<Double>> tabelExchange, String tip);

    void verificaBani(Double suma);

}
