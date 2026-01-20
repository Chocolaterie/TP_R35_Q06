package fr.eni.tp_article.dao.jpa;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.dao.IArticleDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Component
public class ArticleDAOJPA implements IArticleDAO {

    private final ArticleRepository repository;

    public ArticleDAOJPA(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Article> selectAll() {
        return repository.findAll();
    }

    @Override
    public Article selectById(Long id) {
        if (id == null){
            return null;
        }
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null){
            return false;
        }
        // Est-ce que l'object existe ?
        Optional<Article> article = repository.findById(id);
        if (article.isEmpty()){
            return false;
        }

        repository.delete(article.get());

        return true;
    }

    @Override
    public Article save(Article article, boolean update) {
        return repository.save(article);
    }
}
