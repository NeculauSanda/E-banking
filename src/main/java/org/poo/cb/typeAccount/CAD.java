package org.poo.cb.typeAccount;

import org.poo.cb.Account;
import org.poo.cb.UserException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CAD implements Account {
    final String name = "CAD";
    private Double depozit = 0d;

    public Double getDepozit() {
        return depozit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void adaugaBani(Double suma) {
        depozit = depozit + suma;
    }

    @Override
    public void exchange(Double suma, Map<String, List<Double>> tabelExchange, String tip) {
        Double sumaExchange = 0d;
        for(Map.Entry<String, List<Double>> exchange : tabelExchange.entrySet()) {
            if(exchange.getKey().equals(tip)) {
                List<Double> listaExchange = exchange.getValue();
                if(getName().equals("EUR")) {
                    sumaExchange = sumaExchange + (suma * listaExchange.get(0));
                    if(sumaExchange > depozit/2) {
                        sumaExchange = sumaExchange + ((0.01 * suma) * listaExchange.get(0));
                    }
                } else if(getName().equals("GBP")) {
                    sumaExchange = sumaExchange + (suma * listaExchange.get(1));
                    if(sumaExchange > depozit/2) {
                        sumaExchange = sumaExchange + ((0.01 * suma) * listaExchange.get(1));
                    }
                } else if(getName().equals("JPY")) {
                    sumaExchange = sumaExchange + (suma * listaExchange.get(2));
                    if(sumaExchange > depozit/2) {
                        sumaExchange = sumaExchange + ((0.01 * suma) * listaExchange.get(2));
                    }
                } else if(getName().equals("CAD")) {
                    sumaExchange = sumaExchange + (suma * listaExchange.get(3));
                    if(sumaExchange > depozit/2) {
                        sumaExchange = sumaExchange + ((0.01 * suma) * listaExchange.get(3));
                    }
                } else if(getName().equals("USD")) {
                    sumaExchange = sumaExchange + (suma * listaExchange.get(4));
                    if(sumaExchange > depozit/2) {
                        sumaExchange = sumaExchange + ((0.01 * suma) * listaExchange.get(4));
                    }
                }
            }
        }
        // scadem din contul nostru banii pe care i-am schimbat
        if(depozit - sumaExchange >= 0) {
            depozit = depozit - sumaExchange;
        } else {
            throw new UserException("Insufficient amount in account " + getName() + " for exchange");
        }
    }
}
