package org.poo.cb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class TransferMoney implements Comanda{

    private EBanking EBanking;
    private String emailSursa;
    private String emailDestinatar;
    private Double suma;
    private String cont;

    public TransferMoney() {
    }

    public EBanking getEBanking() {
        return EBanking;
    }

    public String getEmailSursa() {
        return emailSursa;
    }

    public String getEmailDestinatar() {
        return emailDestinatar;
    }

    public Double getSuma() {
        return suma;
    }

    public String getCont() {
        return cont;
    }

    private TransferMoneyObserver transferMoneyObserver;

    public TransferMoney(EBanking EBanking, String emailSursa, String emailDestinatar,
                         Double suma, TransferMoneyObserver transferMoneyObserver) {
        this.EBanking = EBanking;
        this.emailSursa = emailSursa;
        this.emailDestinatar = emailDestinatar;
        this.suma = suma;
    }

    public void notifyObserver() {
        if(transferMoneyObserver != null) {
            transferMoneyObserver.update();
        }
    }

    public void attach(TransferMoneyObserver observer) {
        this.transferMoneyObserver = observer;
    }

    public void extrageBaniUser() {
        ActionUser actions = new ActionUser();
        User user = actions.returnareUser(EBanking,emailSursa);
        Account accont = actions.returnareCont(user.getConturi(), cont);
        try {
            accont.verificaBani(suma);
            accont.retragereBani(suma);
            notifyObserver();
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }
    }
    public void execute(EBanking eBanking, String[] intrari, String[] args) {
        try (FileWriter fw = new FileWriter("src/main/java/" + "output.out", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("\n user  " + intrari[2] + " " + intrari[3] + " " + intrari[4] + " " + intrari[5]);
            this.EBanking = eBanking;
            this.emailDestinatar = intrari[3];
            this.emailSursa = intrari[2];
            this.suma = Double.parseDouble(intrari[5]);
            this.cont = intrari[4];

            TransferMoneyObserver transferMoneyObserverEx = new TransferMoneyObserver(this);

            try {

                /// verificare
                ActionUser actions = new ActionUser();
                User user = actions.returnareUser(eBanking,intrari[2]);
                Account accont = actions.returnareCont(user.getConturi(), intrari[4]);
                User destinatar = actions.returnareUser(eBanking, intrari[3]);


//                out.println("AICIC   " + destinatar.toString());
//                out.println("cont destinatar  " + destinatar.getConturi().toString());
                Map<String, Account> conturi = destinatar.getConturi();
//                out.println("AIIIIICICIICIC  conturi ");

                for(Map.Entry<String , Account> cont : conturi.entrySet()) {
                    if(cont != null) {
                        out.print("  " + cont.getValue().getName() + "   " + cont.getValue().getDepozit());
                    }
                }
//

                Account accontdestinatar = actions.returnareCont(destinatar.getConturi(), intrari[4]);
                out.println("Cont inaite de transfer nume cont" + user.getEmail() + "  " + accont.getName() + " depozit" + accont.getDepozit());
//                out.println("Cont inaite de transfer nume cont" + destinatar.getEmail() + "  " + accontdestinatar.getName() + " depozit" + accontdestinatar.getDepozit());


                extrageBaniUser();

                out.println("Cont dupa transfer nume cont" + user.getEmail() + "  " + accont.getName() + " depozit" + accont.getDepozit());
                out.println("Cont dupa transfer nume cont" + destinatar.getEmail() + "  " + accontdestinatar.getName() + " depozit" + accontdestinatar.getDepozit());


            } catch (UserException e) {
                System.out.println(e.getMessage());
                out.println(">>>>>>>" + e.getMessage());
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
