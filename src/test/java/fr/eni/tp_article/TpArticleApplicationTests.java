package fr.eni.tp_article;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.bo.LoginRequest;
import fr.eni.tp_article.service.ArticleService;
import fr.eni.tp_article.service.AuthService;
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

	@Autowired
	AuthService authService;

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

	@Test
	void testAuth(){
		// Quand connexion erroné
		// PS : testé quand les deux sont pas bon et quand un est bon
		assertThat(authService.auth(new LoginRequest("isi", "sjs")).code).isEqualTo("989");
		assertThat(authService.auth(new LoginRequest("sgobin@eni-ecole.fr", "sjs")).code).isEqualTo("989");
		assertThat(authService.auth(new LoginRequest("sdsqdqd", "123456")).code).isEqualTo("989");

		// Quand connexion fonctionne
		assertThat(authService.auth(new LoginRequest("sgobin@eni-ecole.fr", "123456")).code).isEqualTo("206");
	}

	@Test
	void testCheck() {
		// Quand marche pas
		assertThat(authService.check("sdsd").code).isEqualTo("689");

		// Pour tester que le check marche, generer un token et le mettre dans le test
		String token = authService.auth(new LoginRequest("sgobin@eni-ecole.fr", "123456")).data;

		assertThat(authService.check("Bearer " + token).code).isEqualTo("202");
	}
}
