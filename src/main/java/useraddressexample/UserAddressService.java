package useraddressexample;

import java.util.Optional;

public class UserAddressService implements IUpdateUserAddresses {
    private final IRetrieveUsers iRetrieveUsers;
    private final IUpdateUsers iUpdateUsers;
    private final IConsumeAddresses iConsumeAddresses;

    UserAddressService(IRetrieveUsers iRetrieveUsers, IUpdateUsers iUpdateUsers, IConsumeAddresses iConsumeAddresses) {
        this.iRetrieveUsers = iRetrieveUsers;
        this.iUpdateUsers = iUpdateUsers;
        this.iConsumeAddresses = iConsumeAddresses;
    }

    @Override
    public void update(long userId, Address address) {
        Optional<User> userO = iRetrieveUsers.findUserById(userId);
        userO.ifPresent(user -> iUpdateUsers.storeUserAddress(user, address));
        iConsumeAddresses.consume(address);
    }
}
