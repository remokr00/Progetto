package com.example.progetto.Backend.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;


@UtilityClass
@Log4j2
public class Utils {

/*
Classe per aggedere in maniera pulita ed elegante ai campi del token
all'interno dei nostri controller
 */
    public Jwt getPrincipal() { //restituisce colui che in quel dato istante sta effettuando la richiesta
        return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //il contesto è un ''luogo'' all'interno del quale vengono salvate informazioni riguardanti la sicurezza, in questo caso
    }

    /*
    metodi per prendere precisi campi del nome
     */
    public String getAuthServerId() {
        return getTokenNode().get("subject").asText();
    }

    public String[] getName() {
        String[] res=new String[2];
        res[0]= getTokenNode().get("claims").get("given_name").asText(); //Nome
        res[1]= getTokenNode().get("claims").get("family_name").asText(); //Cognome
        return res;
    }

    public String getEmail() {
        return getTokenNode().get("claims").get("preferred_username").asText();
    }

    /*
    Converte il nostro tokenJWT in una stringa, o meglio in un oggetto Json
     */
    private JsonNode getTokenNode() {
        Jwt jwt = getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();
        String jwtAsString;
        JsonNode jsonNode;
        try {
            jwtAsString = objectMapper.writeValueAsString(jwt);
            jsonNode = objectMapper.readTree(jwtAsString);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Unable to retrieve user's info!");
        }
        return jsonNode;
    }


}
