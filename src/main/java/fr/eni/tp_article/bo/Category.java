package fr.eni.tp_article.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @JsonIgnore
    @OneToMany
    public List<Article> articles;

    public Category() {
    }
}
