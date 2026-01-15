package fr.eni.tp_article.dao;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.service.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDAO {

    public List<Article> articles;

    public ArticleDAO(){
        articles = new ArrayList<>();
        articles.add(new Article(1, "Escargot au chocolat"));
        articles.add(new Article(2, "Article 2"));
        articles.add(new Article(3, "Frangipane"));
    }

    public List<Article> selectAll(){
        return articles;
    }

    public Article selectById(int id){
        // Trouver l'article
        return articles.stream().filter(value -> value.id == id).findFirst().orElse(null);
    }

    public boolean delete(int id) {
        // Trouver l'article
        Article foundArticle = articles.stream().filter(value -> value.id == id).findFirst().orElse(null);

        if (foundArticle == null){
            return false;
        }
        // Si aucune erreur supprimé l'article
        articles.remove(foundArticle);
        return true;
    }

    public Article save(Article article, boolean update){
        // Si je modifie
        if (update) {
            article.title = article.title;
            return article;
        }

        // Sinon creer un article
        articles.add(article);
        return article;
    }
}
