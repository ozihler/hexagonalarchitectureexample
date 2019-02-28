package useraddressexample;

import java.io.PrintStream;

public class ConsoleUserProfile implements IConsumeAddresses {
    private PrintStream console;
    //Maybe better has an AddressForm and a UserProfile
    private IUpdateUserAddresses iUpdateUSerAddresses;

    public ConsoleUserProfile(PrintStream console, IUpdateUserAddresses iUpdateUSerAddresses) {
        this.console = console;
        this.iUpdateUSerAddresses = iUpdateUSerAddresses;
    }

    @Override
    public void consume(Address address) {
        console.println(address);
    }

    public void updateAddressOf(long id) {
        //Input address from console
        Address address = readFromConsole();
        iUpdateUSerAddresses.update(id, address);
    }

    private Address readFromConsole() {
        return new Address("Gerlisbergstrasse", 15, 8302, "Kloten");
    }
}
