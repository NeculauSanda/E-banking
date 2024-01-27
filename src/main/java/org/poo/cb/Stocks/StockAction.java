package org.poo.cb.Stocks;

import org.jetbrains.annotations.Nullable;
import org.poo.cb.ActionUser;
import org.poo.cb.EBanking;
import org.poo.cb.User;
import org.poo.cb.UserException;
import org.poo.cb.typeAccount.Account;

import java.util.List;
import java.util.Map;

//Command pattern la Buy si la Recommended

public class StockAction {
    private final Map<String, List<String>> listStocks;
    private final String email;
    private final EBanking eBanking;
    private String stockName;
    private final int suma;

    public StockAction(Map<String, List<String>> listStocks, EBanking eBanking, String stockName, int suma, String email) {
        this.listStocks = listStocks;
        this.eBanking = eBanking;
        this.stockName = stockName;
        this.suma = suma;
        this.email = email;
    }

    public void buy() {
        User user = new ActionUser().returnareUser(eBanking, email);

        if(new ActionUser().returnareCont(user.getConturi(), "USD") == null) {
            throw new UserException("Account in USD does not exist.");
        } else {
            Account cont = new ActionUser().returnareCont(user.getConturi(), "USD");
            try {
                List<String> valoriStock = getStrings();
                //verificam daca avem bani suficienti sa cumparam stocuri
                Double sumaFinala = Double.parseDouble(valoriStock.get(valoriStock.size() - 1)) * suma;
                //verificam daca putem achizitiona stock
                cont.verificaBani(sumaFinala);

                //luam din cont banii
                cont.retragereBani(sumaFinala);

                // creeam stockul respectiv
                Stocks stocks = new StockPrincipal(stockName);

                //adaugam cate stock-uri vrem
                stocks.adaugaStock(suma);
                user.getStocks().put(stockName, stocks);  // adaugam stock-ul la user

            }catch (UserException e) {
                throw new UserException("Insufficient amount in account for buying stock");
            }
        }
    }

    public void recommend() {
        boolean firstLine = false; //ignoram prima linie unde sunt tinute datile
        boolean first = false;
        System.out.print("{\"stocksToBuy\":[");
        for(Map.Entry<String, List<String>> sto : listStocks.entrySet()) {
            if(!firstLine) {
                firstLine = true;
            } else {
                double sumaCinci = calcul5Zile(sto.getKey());
                double sumaZece = calcul10Zile(sto.getKey());
                if(sumaCinci > sumaZece) {
                    if(!first) {
                        System.out.print("\"" + sto.getKey() + "\"");
                        first = true;
                    } else {
                        System.out.print(",\"" + sto.getKey() + "\"");
                    }
                }
            }
        }
        System.out.println("]}");
    }

    @Nullable
    private List<String> getStrings() {
        List<String> valoriStock = null;
        for(Map.Entry<String, List<String>> sto : listStocks.entrySet()) {
            if(sto.getKey().equals(stockName)) {
                valoriStock = sto.getValue();
            }
        }
        return valoriStock;
    }

    public double calcul5Zile(String nameStock) {
        double suma = 0d;
        this.stockName = nameStock;
        List<String> val = getStrings();
        for(int i = 5; i < val.size(); i++) {
            suma = suma + Double.parseDouble(val.get(i));
        }
        return suma/5;
    }

    public double calcul10Zile(String nameStock) {
        double suma = 0d;
        this.stockName = nameStock;
        List<String> val = getStrings();
        for (String s : val) {
            suma = suma + Double.parseDouble(s);
        }
        return suma/10;
    }

}
