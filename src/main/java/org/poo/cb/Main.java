package org.poo.cb;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
        } else {
            Citire citire = new Citire();
            EBanking eBanking = EBanking.geteBanking();
            citire.citim(args, eBanking);

            // curata baza de date a utilizatorilor
            eBanking.curataMap();
//            System.out.println(args[2]);
        }
    }
}