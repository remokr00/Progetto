package com.example.progetto.Backend.Authentication;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/*
Classe per convertire il token personalizzata in modo da estrarre le cose più utili
 */
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>{
    @Value("${keycloack.resource}") //prendiamo il valore del client dal file yml in keycloack.resource
    private String CLIENT_NAME;

    /*
    Il metodo converter legge delle informazioni all'interno del token
     */
    @Override
    @SuppressWarnings("unchecked")
    public AbstractAuthenticationToken convert(final Jwt source) {
        Map<String, Object> resourceAccess = source.getClaim("resource_access"); //andiamo a prendere le risorse per l'accesso
        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(CLIENT_NAME); //otteniamo i client che risiedono all'interno del token
        Collection<String> resourceRoles = (Collection<String>) resource.get("roles"); //prendiamo i ruoli del client che abbiamo configurato in keycloak (utente_progetto o _amministratore progetto)
        Set<GrantedAuthority> authorities = resourceRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()); //vediamo cio che l'utente può fare a seconda del ruolo
        return new JwtAuthenticationToken(source, authorities); //ritorniamo il token
    }




}


