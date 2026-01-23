package fr.eni.tp_article.bo;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;

    @ManyToOne
    public Category category;

    @ManyToMany
    public List<Tag> tags;

    public Article() {}

    public Article(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
