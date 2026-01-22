package fr.eni.tp_article.dao.jpa;

import fr.eni.tp_article.bo.AppUser;
import fr.eni.tp_article.dao.IAuthDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jpa")
@Component
public class AuthDAOJPA implements IAuthDAO {

    private final AppUserRepository appUserRepository;

    public AuthDAOJPA(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser selectByEmailAndPassword(String email, String password) {
        AppUser foundUser = appUserRepository.findById(email).orElse(null);

        // Si je trouve pas, pas de user
        if (foundUser == null) return null;

        // Simuler que ton hasher test que le mot de passe est correct
        // ex: BCrypt.check(foundUser.password, password, key);
        if (!foundUser.password.equals(password)){
            return null;
        }
        return foundUser;
    }
}
