package org.poo.cb;

import org.poo.cb.Command.Comanda;
import org.poo.cb.Command.ComandaFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Citire {
    public void citire(String[] args, EBanking eBanking) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + args[2]))) {
            String line;
            String splitBy = " ";
            while ((line = br.readLine()) != null) {

                String[] intrari = line.split(splitBy);
                String comanda = intrari[0] + " " + intrari[1];

                //factory pentru comenzi
                ComandaFactory ComandaFactory = new ComandaFactory();
                Comanda comanda1 = ComandaFactory.getComanda(comanda);
                comanda1.execute(eBanking, intrari, args);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Map<String, List<Double>> citimExchange () {

        Map<String, List<Double>> exchange = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/common/exchangeRates.csv"))) {
            String line;
            String splitBy = ",";
            boolean first = false;
            while ((line = br.readLine()) != null) {
                String[] intrariSec = line.split(splitBy);
                if(!first) {
                    // ignoram prima linie
                    first = true;
                } else {
                    List<Double> lista = new ArrayList<>();
                    for (int i = 1; i < intrariSec.length; i++) {
                        lista.add(Double.valueOf(intrariSec[i]));
                    }
                    exchange.put(intrariSec[0],lista);
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }

        return exchange;
    }

    public Map<String, List<String>> citimStocks (String args) {

        Map<String, List<String>> exchange = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + args))) {
            String line;
            String splitBy = ",";
            while ((line = br.readLine()) != null) {
                String[] intrariSec = line.split(splitBy);

                List<String> lista = new ArrayList<>();
                for (int i = 1; i < intrariSec.length; i++) {
                    lista.add(intrariSec[i]);
                }
                exchange.put(intrariSec[0],lista);
            }
        } catch (IOException e) {
            e.getMessage();
        }

        return exchange;
    }
}
