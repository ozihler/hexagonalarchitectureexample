package useraddressexample;

import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements IRetrieveUsers, IUpdateUsers {
    private Map<User, Address> userAddresses;

    public InMemoryUserRepository(Map<User, Address> userAddresses) {
        this.userAddresses = userAddresses;
    }

    @Override
    public Optional<User> findUserById(long userId) {
        return userAddresses.keySet()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst();
    }

    @Override
    public void storeUserAddress(User user, Address address) {
        userAddresses.put(user, address);
    }
}
