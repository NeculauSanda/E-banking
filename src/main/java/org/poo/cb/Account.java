package org.poo.cb;

import java.util.List;
import java.util.Map;

public interface Account {
    String getName();
    Double getDepozit();
    void adaugaBani(Double suma);
    void exchange(Double suma, Map<String, List<Double>> tabelExchange, String tip);
}
