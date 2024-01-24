package org.poo.cb;

public class TransferMoneyObserver {
    TransferMoney transferMoney;

    public void update() {
        //aici se v-a face transferul in contul celuilalt user
        try {
            ActionUser actionUser = new ActionUser();
            User user = actionUser.returnareUser(transferMoney.getEBanking(),transferMoney.getEmailSursa());
            actionUser.verificarePrieten(user,transferMoney.getEmailDestinatar());
            User destinatar = actionUser.returnareUser(transferMoney.getEBanking(),transferMoney.getEmailDestinatar());
            Account contDestinatar = actionUser.returnareCont(destinatar.getConturi(), transferMoney.getCont());
            contDestinatar.adaugaBani(transferMoney.getSuma());
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }
    }

    public TransferMoneyObserver(TransferMoney transferMoney) {
        this.transferMoney = transferMoney;
        transferMoney.attach(this);
    }
}
