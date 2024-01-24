package org.poo.cb;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ExchangeMoney implements Comanda{
    public void execute(EBanking eBanking, String[] intrari, String[] args) {

            Citire citim = new Citire();
            ActionUser actiuni = new ActionUser();
            // valorile de exchange
            Map<String, List<Double>> exchange = citim.citimExchange();


            try {
                User user = actiuni.returnareUser(eBanking, intrari[2]);
                Account contExtragere = actiuni.returnareCont(user.getConturi(), intrari[3]);
                contExtragere.exchange(Double.parseDouble(intrari[5]), exchange, intrari[4]);
                Account contDepunere = actiuni.returnareCont(user.getConturi(), intrari[4]);
                contDepunere.adaugaBani(Double.parseDouble(intrari[5]));


            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
    }
}
