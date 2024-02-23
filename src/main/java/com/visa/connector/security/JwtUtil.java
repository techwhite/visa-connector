package com.visa.connector.security;

import com.visa.connector.model.UserRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
JwtUtil class is used to generate Jwt token and parse it.
 */
public class JwtUtil {
    // The expiration interval of token. Current setting is 10 hours
    private static final long validityInMilliseconds = 36000000;
    // secret key to generate token, should different by person
    // todo: save into more secure places
    private static String secret = "123456603043";

    /*
     used for generating jwt token
     */
    public static Map<String, String> generateToken(UserRequest user) {
        String jwtToken="";
        jwtToken = Jwts.builder()
                .setSubject(user.getName())
                .setIssuedAt(new Date())
                // valid forever if not setting this item
                .setExpiration(new Date((new Date()).getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        return jwtTokenGen;
    }

    /*
     used for parse and validate jwt token
     */
    public static Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
