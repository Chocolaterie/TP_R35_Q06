package fr.eni.tp_article.controller;

import fr.eni.tp_article.bo.LoginRequest;
import fr.eni.tp_article.service.AuthService;
import fr.eni.tp_article.service.ServiceResponse;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ServiceResponse<String> auth(@RequestBody LoginRequest loginRequest){
      return authService.auth(loginRequest);
    }

    @GetMapping("/check-login")
    public ServiceResponse<Boolean> check(@RequestHeader("Authorization") String bearerToken){
       return authService.check(bearerToken);
    }
}
