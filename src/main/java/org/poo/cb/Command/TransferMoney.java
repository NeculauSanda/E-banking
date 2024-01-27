package org.poo.cb.Command;

import org.poo.cb.*;
import org.poo.cb.typeAccount.Account;

public class TransferMoney implements Comanda {

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
            this.EBanking = eBanking;
            this.emailDestinatar = intrari[3];
            this.emailSursa = intrari[2];
            this.suma = Double.parseDouble(intrari[5]);
            this.cont = intrari[4];

            //observatorul
            TransferMoneyObserver transferMoneyObserverEx = new TransferMoneyObserver(this);

            try {
                extrageBaniUser();
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }

    }
}
