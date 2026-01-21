package fr.eni.tp_article.service;

import fr.eni.tp_article.bo.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class AuthService {

    private Key getSecretKey() {
        // convertir un string en base 64
        byte[] keyBytes = Decoders.BASE64.decode("69636e783529213d5722613b2b336c793371666524684a3445226e5573");

        // convertir une base 64 en Key
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public ServiceResponse<String> auth(LoginRequest loginRequest){
        // Est-ce que email/password
        if (!loginRequest.email.equals("sgobin@eni-ecole.fr") || !loginRequest.password.equals("123456")){
            return new ServiceResponse<>("989", "Couple email / mot de passe incorrect");
        }

        // Générer un token
        // nb heure
        int nbHour = 1;
        Date tokenLifetime = new Date(System.currentTimeMillis() + ((1000 * 60 * 60) * nbHour));

        // Le code pour générer un token
        String token = Jwts.builder()
                .subject("test")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(tokenLifetime)
                .signWith(getSecretKey())
                .compact();

        return new ServiceResponse<>("206", "Authentifié(e) avec succès", token);
    }

    public ServiceResponse<Boolean> check(String bearerToken){
        // Checker que le token est pas null
        if (bearerToken == null){
            return new ServiceResponse<Boolean>("689", "Token invalide", false);
        }

        // Je check que y'a au moins 7 lettres
        if (bearerToken.length() < 7){
            return new ServiceResponse<Boolean>("689", "Token invalide", false);
        }

        // Header authorization
        // Attention Nous on récupérer Bearer MONTOKEN
        // DONC il faut nettoyer le token recu pour récupérer que le MONTOKEN
        // DONC enlever le "Bearer " (7 premiers caractères)
        // Token
        String token = bearerToken.substring(7);

        // Verifier
        try {
            // Outil pour récupérer le token (déchiffrer)
            JwtParser jwtParser = Jwts.parser()
                    .verifyWith((SecretKey) getSecretKey())
                    .build();
            Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        } catch (Exception e){
            return new ServiceResponse<Boolean>("689", "Token invalide", false);
        }

        // Sinon ok
        return new ServiceResponse<Boolean>("202", "Token valide", true);
    }
}
