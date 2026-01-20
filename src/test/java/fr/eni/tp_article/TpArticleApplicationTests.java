package fr.eni.tp_article;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.service.ArticleService;
import fr.eni.tp_article.service.ServiceConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("mock")
@SpringBootTest
class TpArticleApplicationTests {

	@Autowired
	ArticleService articleService;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetAll(){

		assertThat(articleService.getAll().code).isEqualTo(ServiceConstant.CD_SUCCESS);
	}

	@Test
	void testGetById(){

		assertThat(articleService.getById(20L).code).isEqualTo(ServiceConstant.CD_ERR_NOT_FOUND);

		assertThat(articleService.getById(1L).code).isEqualTo(ServiceConstant.CD_SUCCESS);
	}

	@Test
	void testSave(){
		assertThat(articleService.save(new Article(1L, "Teletubies")).code).isEqualTo(ServiceConstant.CD_EDIT_SUCCESS);

		assertThat(articleService.save(new Article(20L, "Teletubies")).code).isEqualTo(ServiceConstant.CD_SUCCESS);
	}

	@Test
	void testDelete(){

		assertThat(articleService.deleteById(12L).code).isEqualTo(ServiceConstant.CD_ERR_NOT_FOUND);

		assertThat(articleService.deleteById(1L).code).isEqualTo(ServiceConstant.CD_SUCCESS);
	}
}
