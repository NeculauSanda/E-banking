package org.poo.cb;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
        } else {

            Citire citire = new Citire();
            EBanking eBanking = EBanking.geteBanking();
            citire.citire(args, eBanking);

            // curata baza de date a utilizatorilor
            eBanking.curataMap();
        }
    }
}