package p04_InterfaceSegregation.p01_Identity;

import p04_InterfaceSegregation.p01_Identity.interfaces.Account;

public class Main {
    public static void main(String[] args) {
        Account manager = new AccountManager();
        AccountController controller = new AccountController(manager);
        controller.changePassword("asd", "dsa");
    }
}
