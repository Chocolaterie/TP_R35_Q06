package fr.eni.tp_article.controller;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.security.JwtAuthGuard;
import fr.eni.tp_article.service.ArticleService;
import fr.eni.tp_article.service.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @JwtAuthGuard
    @Operation(description = "Swagger_GetAll")
    @GetMapping("/all")
    public ServiceResponse<List<Article>> getAll(){
        return articleService.getAll();
    }

    @Operation(description = "Swagger_GetById")
    @GetMapping("/article/{id}")
    public ServiceResponse<Article> getById(@PathVariable("id") Long id){
        return articleService.getById(id);
    }

    @Operation(description = "Swagger_Delete")
    @GetMapping("/delete-article/{id}")
    public ServiceResponse<Article> deleteById(@PathVariable("id") Long id) {
        return articleService.deleteById(id);
    }

    @Operation(description = "Swagger_Save")
    @PostMapping("/save")
    public ServiceResponse<Article> save(@RequestBody Article article) {
        return articleService.save(article);
    }

}
