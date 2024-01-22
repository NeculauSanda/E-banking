package org.poo.cb;

import java.io.*;

public class Citire {
    public void citim (String[] args, EBanking eBanking) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + args[2]))) {
            String line;
            String splitBy = " ";
            while ((line = br.readLine()) != null) {

                String[] intrari = line.split(splitBy);
                String comanda = intrari[0] + " " + intrari[1];

                // de sters de scris in fisier
                try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
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


//            try {
//                PrintWriter writer = new PrintWriter("src/main/java/" + "output.out");
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
    }
}
