package fr.eni.tp_article.service;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.dao.IArticleDAO;
import fr.eni.tp_article.locale.LocaleHelper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleService {

    private final IArticleDAO articleDAO;
    private final LocaleHelper lH;

    public ArticleService(IArticleDAO articleDAO, LocaleHelper lH) {
        this.articleDAO = articleDAO;
        this.lH = lH;
    }

    public ServiceResponse<List<Article>> getAll(){

        List<Article> articles = articleDAO.selectAll();

        return ServiceHelper.response(lH, ServiceConstant.CD_SUCCESS, "Msg_Article_GetAll_Success", articles);
    }

    public ServiceResponse<Article> getById(Long id){
        // Appel dao pour récupérer l'article
        Article foundArticle = articleDAO.selectById(id);

        // Si je trouve pas
        if (foundArticle == null){
            return new ServiceResponse<Article>(ServiceConstant.CD_ERR_NOT_FOUND, lH.i18n("Msg_Article_GetById_NotFound"));
        }

        return new ServiceResponse<Article>(ServiceConstant.CD_SUCCESS, lH.i18n("Msg_Article_GetById_Success"), foundArticle);
    }

    public ServiceResponse<Article> deleteById(Long id) {
        // Appel dao qui s'occupe du delete
        boolean successRemove = articleDAO.delete(id);

        // Si je n'arrive pas a supprimer -> Erreur
        if (!successRemove){
            return new ServiceResponse<Article>(ServiceConstant.CD_ERR_NOT_FOUND, lH.i18n("Msg_Article_DeleteById_Error"));
        }

        return new ServiceResponse<Article>(ServiceConstant.CD_SUCCESS, lH.i18n("Msg_Article_DeleteById_Success"));
    }

    public ServiceResponse<Article> save(Article article) {
        // Trouver l'article
        // Attention il trouve pas si l'id est null ou n'existe pas l'id
        Article foundArticle = articleDAO.selectById(article.id);

        // Si je trouve je modifie
        if (foundArticle != null) {
            foundArticle = articleDAO.save(article, true);
            return new ServiceResponse<Article>(ServiceConstant.CD_EDIT_SUCCESS, lH.i18n("Msg_Article_Save_Edit_Success"), foundArticle);
        }

        // Sinon creer un article
        // generer un id (code à la main, vous l'inventez)
        // PS: Pour le moment tres fragile et pas pertinant
        article = articleDAO.save(article, false);

        return new ServiceResponse<Article>(ServiceConstant.CD_SUCCESS, lH.i18n("Msg_Article_Save_Create_Success"), article);
    }
}
