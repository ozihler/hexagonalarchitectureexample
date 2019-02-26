package useraddressexample;

import java.util.Optional;

public class UserAddressService implements IUpdateUserAddress {
    private final IRetrieveUsers iRetrieveUsers;
    private final IStoreUserAddresses iStoreUserAddresses;
    private final IUpdateAddressView iUpdateAddressView;

    UserAddressService(IRetrieveUsers iRetrieveUsers, IStoreUserAddresses iStoreUserAddresses, IUpdateAddressView iUpdateAddressView) {
        this.iRetrieveUsers = iRetrieveUsers;
        this.iStoreUserAddresses = iStoreUserAddresses;
        this.iUpdateAddressView = iUpdateAddressView;
    }

    @Override
    public void update(long userId, Address address) {
        Optional<User> userO = iRetrieveUsers.findUserById(userId);
        userO.ifPresent(user -> iStoreUserAddresses.storeUserAddress(user, address));
        iUpdateAddressView.updateView(address);
    }
}
