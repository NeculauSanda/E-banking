package org.poo.cb;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Citire {
    public void citim (String[] args, EBanking eBanking) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + args[2]))) {
            String line;
            String splitBy = " ";
            while ((line = br.readLine()) != null) {

                String[] intrari = line.split(splitBy);
                String comanda = intrari[0] + " " + intrari[1];

                // de sters de scris in fisier
                try (FileWriter fw = new FileWriter("src/main/java/output.out", true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {

                    out.println(line);
                    out.println("\n" + comanda + "\n");
                    out.println("*****************************");

                    // pana aici
                    // am facu tfactory pentru comenzi
                    ComandaFactory ComandaFactory = new ComandaFactory();
                    Comanda comanda1 = ComandaFactory.getComanda(comanda);
                    comanda1.execute(eBanking, intrari);

                } catch (IOException b) {
                    b.getMessage();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }

//        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + args[3]))) {
//            String line;
//            String splitBy = " ";
//            while ((line = br.readLine()) != null) {
//                String[] intrari = line.split(splitBy);
//                String comanda = intrari[0] + " " + intrari[1];
//
//                // de sters de scris in fisier
//                try (FileWriter fw = new FileWriter("src/main/java/" + "output1.out", true);
//                     BufferedWriter bw = new BufferedWriter(fw);
//                     PrintWriter out = new PrintWriter(bw)) {
//
//
//                } catch (IOException b) {
//                    b.getMessage();
//                }
//            }
//        } catch (IOException e) {
//            e.getMessage();
//        }
//            try {
//                PrintWriter writer = new PrintWriter("src/main/java/" + "output.out");
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
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
}
