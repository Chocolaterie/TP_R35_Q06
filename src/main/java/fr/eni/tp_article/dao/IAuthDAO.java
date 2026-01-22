package fr.eni.tp_article.dao;

import fr.eni.tp_article.bo.AppUser;

public interface IAuthDAO {

    AppUser selectByEmailAndPassword(String email, String password);
}
