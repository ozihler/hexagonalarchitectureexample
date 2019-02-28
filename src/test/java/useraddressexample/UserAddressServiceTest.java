package useraddressexample;

import org.junit.Test;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserAddressServiceTest {

    public static final Address ADDRESS = new Address("Gerlisbergstrasse", 15, 8302, "Kloten");
    public static final User USER = new User(1L);

    @Test
    public void testUserAddressSerivce() {
        long userId = 1L;
        User user = new User(1L);
        Address address = new Address("Gerlisbergstrasse", 15, 8302, "Kloten");

        IRetrieveUsers iRetrieveUsers = mock(IRetrieveUsers.class);
        when(iRetrieveUsers.findUserById(1L)).thenReturn(Optional.of(user));

        IUpdateUsers iUpdateUsers = mock(IUpdateUsers.class);

        IConsumeAddresses iConsumeAddresses = mock(IConsumeAddresses.class);
        UserAddressService userAddressService = new UserAddressService(iRetrieveUsers, iUpdateUsers, iConsumeAddresses);

        userAddressService.update(userId, address);

        verify(iRetrieveUsers, times(1)).findUserById(userId);
        verify(iUpdateUsers, times(1)).storeUserAddress(user, address);
        verify(iConsumeAddresses, times(1)).consume(address);
    }

    @Test
    public void test() {
        IRetrieveUsers userRepository = new InMemoryUserRepository(userAddresses());
        assertEquals(USER, userRepository.findUserById(USER.getId()).get());
    }

    @Test
    public void testUserRepoService() {
        Map<User, Address> userAddresses = userAddresses();
        IUpdateUsers userRepository = new InMemoryUserRepository(userAddresses);
        Address newAddress = new Address("Badenerstrasse", 142, 8001, "Zürich");

        assertEquals(ADDRESS, userAddresses.get(USER));
        userRepository.storeUserAddress(USER, newAddress);
        assertEquals(newAddress, userAddresses.get(USER));
        userRepository.storeUserAddress(USER, ADDRESS);
        assertEquals(ADDRESS, userAddresses.get(USER));
    }

    @Test
    public void testView() {
        PrintStream console = mock(PrintStream.class);
        IConsumeAddresses view = new ConsoleUserProfile(console, null);
        view.consume(ADDRESS);
        verify(console, times(1)).println(ADDRESS);
    }

    @Test
    public void integrateUserAddressService() {
        HashMap<User, Address> userAddresses = new HashMap<>();
        userAddresses.put(USER, null);

        InMemoryUserRepository userRepository = new InMemoryUserRepository(userAddresses);
        ConsoleUserProfile consoleUserProfile = new ConsoleUserProfile(System.out, null);

        UserAddressService userAddressService = new UserAddressService(userRepository, userRepository, consoleUserProfile);

        userAddressService.update(USER.getId(), ADDRESS);
        assertEquals(ADDRESS, userAddresses.get(USER));

        Address newAddress = new Address("Hello", 1, 2, "World");
        userAddressService.update(USER.getId(), newAddress);
        assertEquals(newAddress, userAddresses.get(USER));
    }

    @Test
    public void testConsole() {
        PrintStream printStream = mock(PrintStream.class);
        IUpdateUserAddresses iUpdateUSerAddresses = mock(IUpdateUserAddresses.class);
        ConsoleUserProfile consoleUserProfile = new ConsoleUserProfile(printStream, iUpdateUSerAddresses);
        consoleUserProfile.updateAddressOf(USER.getId());
        verify(iUpdateUSerAddresses, times(1)).update(USER.getId(), ADDRESS);
    }

    private Map<User, Address> userAddresses() {
        Map<User, Address> userAddresses = new HashMap<>();
        userAddresses.put(USER, ADDRESS);
        return userAddresses;
    }
}
