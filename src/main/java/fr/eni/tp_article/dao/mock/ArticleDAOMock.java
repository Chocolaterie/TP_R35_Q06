package fr.eni.tp_article.dao.mock;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.dao.IArticleDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("mock")
@Component
public class ArticleDAOMock implements IArticleDAO {

    public List<Article> articles;

    public ArticleDAOMock(){
        articles = new ArrayList<>();
        articles.add(new Article(1L, "Escargot au chocolat"));
        articles.add(new Article(2L, "Article 2"));
        articles.add(new Article(3L, "Frangipane"));
    }

    @Override
    public List<Article> selectAll(){
        return articles;
    }

    @Override
    public Article selectById(Long id){
        // Trouver l'article
        return articles.stream().filter(value -> value.id == id).findFirst().orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        // Trouver l'article
        Article foundArticle = articles.stream().filter(value -> value.id == id).findFirst().orElse(null);

        if (foundArticle == null){
            return false;
        }
        // Si aucune erreur supprimé l'article
        articles.remove(foundArticle);
        return true;
    }

    @Override
    public Article save(Article article, boolean update){
        // Si je modifie
        if (update) {
            article.title = article.title;
            return article;
        }

        // Generation faux id que dans le mock
        article.id = selectAll().size() + 1L;

        // Sinon creer un article
        articles.add(article);
        return article;
    }
}
