package fr.eni.tp_article.service;

import fr.eni.tp_article.bo.Article;
import fr.eni.tp_article.locale.LocaleHelper;

import java.util.List;

public class ServiceHelper {

    public static <T> ServiceResponse<T> response(LocaleHelper lH, String code, String key, T data){
        return new ServiceResponse<T>(code, lH.i18n(key), data);
    }
}
