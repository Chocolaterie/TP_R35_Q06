package fr.eni.tp_article.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Primary;

@Entity
public class AppUser {

    @Id
    public String email;

    public String password;

    public AppUser() {
    }

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
