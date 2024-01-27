package org.poo.cb.Command;

import org.poo.cb.Command.*;

public class ComandaFactory {
    public Comanda getComanda(String comanda) {
        if(comanda == null) {
            return null;
        } else if(comanda.equals("CREATE USER")) {
            return new CreateUser();
        } else  if(comanda.equals("ADD FRIEND")) {
            return new AddFriend();
        } else if (comanda.equals("LIST USER")) {
            return new ListUser();
        } else if (comanda.equals("ADD ACCOUNT")) {
            return new AddAccount();
        } else if (comanda.equals("ADD MONEY")) {
            return new AddMoney();
        } else if (comanda.equals("TRANSFER MONEY")) {
            return new TransferMoney();
        } else if (comanda.equals("LIST PORTFOLIO")) {
            return new ListPortofolio();
        }  else if (comanda.equals("RECOMMEND STOCKS")) {
            return new RecommendSto();
        } else if (comanda.equals("BUY STOCKS")) {
            return new BuyStocks();
        } else if (comanda.equals("EXCHANGE MONEY")) {
            return new ExchangeMoney();
        }
        return null;
    }
}
