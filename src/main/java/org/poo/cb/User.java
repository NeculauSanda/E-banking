package org.poo.cb;

import org.poo.cb.Stocks.Stocks;
import org.poo.cb.typeAccount.Account;

import java.util.*;

public class User {
    private final String email;
    private final String nume;
    private final String prenume;
    private final String adresa;
    private List<User> prieteni;

    private Map<String, Account> conturi;

    private Map<String, Stocks> stocks;

    private User(UserBuilder userBuilder) {
        this.email = userBuilder.email;
        this.nume = userBuilder.nume;
        this.prenume = userBuilder.prenume;
        this.adresa = userBuilder.adresa;
        this.prieteni = userBuilder.prieteni;
        this.conturi = userBuilder.conturi;
    }

    public String getEmail() {
        return email;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public List<User> getPrieteni() {
        if(prieteni == null) {
            prieteni = new ArrayList<User>();
        }
        return prieteni;
    }

    public Map<String, Account> getConturi() {
        if(conturi == null) {
            // pentru a ramane conturile in ordinea in care s-au creat
            conturi = new LinkedHashMap<String, Account>();
        }
        return conturi;
    }

    public Map<String, Stocks> getStocks() {
        if(stocks == null) {
            stocks = new LinkedHashMap<String, Stocks>();
        }
        return stocks;
    }


    // Buider User
    public static class UserBuilder {
        private final String email;  // obligatoriu
        private final String nume;  // obligatoriu
        private final String prenume; // obligatoriu
        private String adresa;
        private List<User> prieteni;

        private Map<String, Account> conturi;

        private Map<String, Stocks> stocks;

        public UserBuilder(String email, String prenume, String nume) {
            this.email = email;
            this.prenume= prenume;
            this.nume = nume;
        }

        public UserBuilder adresa(String adresa) {
            this.adresa = adresa;
            return this;
        }

        public UserBuilder prieteni(List<User> prieteni) {
            this.prieteni = prieteni;
            return this;
        }

        public Map<String, Account> conturi(Map<String, Account> conturi) {
            this.conturi = conturi;
            return conturi;
        }

        public Map<String, Stocks> stock(Map<String, Stocks> stocks) {
            this.stocks = stocks;
            return stocks;
        }

        public User build(EBanking eBanking) {
            User user = new User(this);
            // verificam emaiul
            boolean rezultat = user.validareUser(eBanking, user.email);
            if(rezultat) {
                throw new UserException("User with " + email + " already exists");
            } else  {
                return user;
            }
        }
    }

    // verificam user cu ajutorul emailului
    public boolean validareUser (EBanking eBanking, String email) {
        Map<String, User> utilizatori= eBanking.getUtilizatori();
        for(Map.Entry<String, User> utilizator : utilizatori.entrySet()) {
            if(utilizator.getKey().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public void adaugaPrieteni(EBanking eBanking,String emailPri) {
        // daca exista prietenul in baza de date il adugam
        if (validareUser(eBanking, emailPri)) {
            User prieten = new ActionUser().returnareUser(eBanking,emailPri);
            //daca prietenul nu se afla in lista userului il adugam
            if(!prieteni.contains(prieten)) {
                //daca lista de prieteni ii nula o initializam
                if (prieteni == null || prieten.prieteni == null) {
                    getPrieteni();
                    prieten.getPrieteni();
                }
                prieteni.add(prieten); // adugam prietenul la userul principal
                prieten.prieteni.add(this);  //  concomitent adaugam la prieten userul principal
            } else {
                throw new UserException("User with " + emailPri + " is already a friend");
            }
        } else {
            throw new UserException("User with " + emailPri + " doesn’t exist");
        }
    }

    public void adaugaCont(EBanking eBanking, Account account) {
        if(validareUser(eBanking,this.email)) {
            if(conturi == null) {
                getConturi();
            }
            if(!conturi.containsKey(account.getName())) {
                conturi.put(account.getName(), account);
            } else {
                throw  new UserException("Account in currency " + account.getClass().getName() + " already exists for user");
            }
        }
    }
}
