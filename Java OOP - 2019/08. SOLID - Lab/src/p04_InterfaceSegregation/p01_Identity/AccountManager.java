package p04_InterfaceSegregation.p01_Identity;

import p04_InterfaceSegregation.p01_Identity.interfaces.Account;

public class AccountManager implements Account {
    @Override
    public void changePassword(String oldPass, String newPass) {
        //change password
    }
}
