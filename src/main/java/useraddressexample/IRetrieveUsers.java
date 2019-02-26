package useraddressexample;

import java.util.Optional;

public interface IRetrieveUsers {
    Optional<User> findUserById(long userId);
}
