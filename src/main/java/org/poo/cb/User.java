package org.poo.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private final String email;
    private final String nume;
    private final String prenume;
    private final String adresa;
    private List<User> prieteni;

    private User(UserBuilder userBuilder) {
        this.email = userBuilder.email;
        this.nume = userBuilder.nume;
        this.prenume = userBuilder.prenume;
        this.adresa = userBuilder.adresa;
        this.prieteni = userBuilder.prieteni;
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

    // de sters
    @Override
    public String toString() {
        return email + " " + prenume + " " + nume + " " + adresa + " ";
    }

    // buider User
    public static class UserBuilder {
        private final String email;  // obligatoriu
        private final String nume;  // obligatoriu
        private final String prenume; // obligatoriu
        private String adresa;
        private List<User> prieteni;

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
            Friend friend = new Friend();
            User prieten = friend.returnareUser(eBanking,emailPri);
            //daca prietenul nu se afla in lista userului il adugam
            if(!prieteni.contains(prieten)) {
                //daca lista de prietei ii nula o initializam
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
}

class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}