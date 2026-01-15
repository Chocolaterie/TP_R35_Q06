package fr.eni.tp_article;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TpArticleApplicationTests {

	@Autowired
	ArticleService articleService;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetAll(){

		assertThat(articleService.getAll().code).isEqualTo("202");
	}

	@Test
	void testGetById(){

		assertThat(articleService.getById(20).code).isEqualTo("703");

		assertThat(articleService.getById(1).code).isEqualTo("202");
	}

	@Test
	void testSave(){
		assertThat(articleService.save(new Article(1, "Teletubies")).code).isEqualTo("203");

		assertThat(articleService.save(new Article(20, "Teletubies")).code).isEqualTo("202");
	}

	@Test
	void testDelete(){

		assertThat(articleService.deleteById(12).code).isEqualTo("703");

		assertThat(articleService.deleteById(1).code).isEqualTo("202");
	}
}
