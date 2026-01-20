package fr.eni.tp_article.dao;

import fr.eni.tp_article.bo.Article;

import java.util.List;

public interface IArticleDAO {

    public List<Article> selectAll();

    public Article selectById(Long id);

    public boolean delete(Long id);

    public Article save(Article article, boolean update);
}
