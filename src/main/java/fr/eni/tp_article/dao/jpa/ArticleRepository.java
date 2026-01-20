package fr.eni.tp_article.dao.jpa;

import fr.eni.tp_article.bo.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
