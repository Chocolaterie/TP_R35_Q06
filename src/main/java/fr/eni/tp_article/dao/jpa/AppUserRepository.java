package fr.eni.tp_article.dao.jpa;

import fr.eni.tp_article.bo.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
