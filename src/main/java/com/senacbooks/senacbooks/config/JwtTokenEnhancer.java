package com.senacbooks.senacbooks.config;

import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.clients.ClientRepository;
import com.senacbooks.senacbooks.users.UserEntity;
import com.senacbooks.senacbooks.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {
        UserEntity user = userRepository.findByLogin(oAuth2Authentication.getName());

        if (user != null) {
            // adicionando informações ao token
            Map<String, Object> map = new HashMap<>();
            map.put("login", user.getLogin());
            map.put("userId", user.getId());
            map.put("userStatus", user.getStatus());

            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;

            token.setAdditionalInformation(map);
        }

        ClientEntity client = clientRepository.findByLogin(oAuth2Authentication.getName());

        if (client != null){
            Map<String, Object> map = new HashMap<>();
            map.put("login", client.getLogin());
            map.put("clientId", client.getId());
            map.put("clientStatus", client.getStatus());
            map.put("clientFirstName", client.getFirstName());

            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;

            token.setAdditionalInformation(map);
        }

        return accessToken;

    }
}
