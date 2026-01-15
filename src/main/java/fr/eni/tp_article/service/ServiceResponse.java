package fr.eni.tp_article.service;

public class ServiceResponse<T> {

    public String code;
    public String message;
    public T data;

    public ServiceResponse() {
    }

    public ServiceResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
