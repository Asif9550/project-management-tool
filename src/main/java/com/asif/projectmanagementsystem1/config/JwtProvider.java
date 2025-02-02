package com.asif.projectmanagementsystem1.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collector;

public class JwtProvider {
      static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//    Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//    String email=String.valueOf(claims.get("email"));
//  String authorities=String.valueOf(claims.get("authorities"));
     public static String generateToken(Authentication auth){

         return Jwts.builder().setIssuedAt(new Date())
                 .setExpiration(new Date(new Date().getTime()+8640000))
                 .claim("email",auth.getName())
                 .signWith(key)
                 .compact();

     }
     public static String getEmailFromToken(String jwt){
         jwt=jwt.substring(7);
         System.out.println(jwt);
         Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

         return String.valueOf(claims.get("email"));
     }
}
