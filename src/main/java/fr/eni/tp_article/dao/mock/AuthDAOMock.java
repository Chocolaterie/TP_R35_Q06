package fr.eni.tp_article.dao.mock;

import fr.eni.tp_article.bo.AppUser;
import fr.eni.tp_article.dao.IAuthDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("mock")
@Component
public class AuthDAOMock implements IAuthDAO {

    public List<AppUser> users;

    public AuthDAOMock() {
        this.users = List.of(
                new AppUser("sgobin@eni-ecole.fr", "123456"),
                new AppUser("test@eni.fr", "1234")
        );
    }

    @Override
    public AppUser selectByEmailAndPassword(String email, String password) {
        AppUser foundUser = users.stream()
                        .filter(user -> user.email.equals(email) && user.password.equals(password))
                        .findFirst().orElse(null);
        return foundUser;
    }
}
