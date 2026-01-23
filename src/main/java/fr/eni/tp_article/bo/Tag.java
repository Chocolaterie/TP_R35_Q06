package fr.eni.tp_article.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String value;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    public List<Article> articles;

}
