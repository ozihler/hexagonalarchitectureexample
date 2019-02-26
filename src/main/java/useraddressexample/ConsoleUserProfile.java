package useraddressexample;

import java.io.PrintStream;

public class ConsoleUserProfile implements IUpdateAddressView {
    private PrintStream console;
    //Maybe better has an AddressForm and a UserProfile
    private IUpdateUserAddress iUpdateUserAddress;

    public ConsoleUserProfile(PrintStream console, IUpdateUserAddress iUpdateUserAddress) {
        this.console = console;
        this.iUpdateUserAddress = iUpdateUserAddress;
    }

    @Override
    public void updateView(Address address) {
        console.println(address);
    }

    public void updateAddressOf(long id) {
        //Input address from console
        Address address = readFromConsole();
        iUpdateUserAddress.update(id, address);
    }

    private Address readFromConsole() {
        return new Address("Gerlisbergstrasse", 15, 8302, "Kloten");
    }
}
