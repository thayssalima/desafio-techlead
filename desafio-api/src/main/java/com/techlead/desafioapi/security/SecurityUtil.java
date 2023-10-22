package com.techlead.desafioapi.security;

import com.techlead.desafioapi.config.AppProperties;
import com.techlead.desafioapi.config.SpringApplicationContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
public class SecurityUtil {
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getTokenSecret(){
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
    	
    	return appProperties.getTokenSecret();
    }

    public static String extractUserInfo(String token) {
        token = token.replace(TOKEN_PREFIX, "");

        String user = Jwts.parser()
                .setSigningKey(getTokenSecret())
                .parseClaimsJws( token )
                .getBody()	
                .getSubject();
        
        return user;
    }
    
    public static Claims extractAuthorities(String token) {
        token = token.replace(TOKEN_PREFIX, "");
        
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(getTokenSecret())
                .parseClaimsJws(token);
        
        return claims.getBody();
    }

    public static boolean hasTokenExpired(String token) {
    	token = token.replace(TOKEN_PREFIX, "");
    	
        Claims claims = Jwts.parser()
        		.setSigningKey(getTokenSecret())
        		.parseClaimsJws(token).getBody();
    		
        Date tokenExpirationDate = claims.getExpiration();
        Date today = new Date();
    		
        return tokenExpirationDate.before(today);
    }
}
