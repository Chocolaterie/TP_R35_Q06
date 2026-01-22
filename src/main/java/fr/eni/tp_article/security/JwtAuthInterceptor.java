package fr.eni.tp_article.security;

import fr.eni.tp_article.service.AuthService;
import fr.eni.tp_article.service.ServiceResponse;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    public JwtAuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Que faire avant d'arriver au controller ou au middleware suivant
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // Est-ce que je suis dans une methode Java (Handler est une method Java), ex: public ServiceResponse<List<BankAccount>> testFindAllDAO() ?
        // HandlerMethod handlerMethod = une method java
        if (handler instanceof HandlerMethod handlerMethod) {

            // Est-ce que j'ai  mon annot dans la method
            boolean haveMonAnnotation = handlerMethod.getMethod().isAnnotationPresent(JwtAuthGuard.class);

            // Si pas d'annotation JwtAuthGuard alors on peut passer
            if (!haveMonAnnotation) return true;

            // Si token passe pas -> alors je passe pas
            String token = request.getHeader("Authorization");

            // Si pas de token
            if (token == null) {
                // renvoyer une réponse JSON
                JsonResponseUtil.sendJson(response, HttpServletResponse.SC_FORBIDDEN, new ServiceResponse("584", "Token inexistant", false));
                return false;
            }

            // Appel service
            ServiceResponse<Boolean> serviceResponse = authService.check(token);

            // Ca passe pas
            if (serviceResponse.code.equals("689")) {
                // renvoyer une réponse JSON
                JsonResponseUtil.sendJson(response, HttpServletResponse.SC_FORBIDDEN, serviceResponse);
                return false;
            }
        }
        // Par defaut ca veut dire c'est OK -> ca passe
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
