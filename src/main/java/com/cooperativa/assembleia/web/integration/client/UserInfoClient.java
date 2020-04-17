package com.cooperativa.assembleia.web.integration.client;

import com.cooperativa.assembleia.web.exception.BusinessException;
import com.cooperativa.assembleia.web.integration.respose.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.cooperativa.assembleia.web.message.MessageKey.CPF_ASSOCIADO_INVALIDO;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class UserInfoClient {

    private final RestTemplate restTemplate;

    public UserInfoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${user.info.url}")
    private String baseUrl;

    public String getByCpf(String cpf) {
        String url = this.baseUrl + cpf;
        log.info("Acessando sistema externo user-info: " + url);
        ResponseEntity<UserInfoResponse> response = restTemplate
                .exchange(url, HttpMethod.GET, getHttpEntityWithHeaders(), UserInfoResponse.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new BusinessException(CPF_ASSOCIADO_INVALIDO, cpf);
        }

        log.info("Retorno do serviço externo: " + response.getBody());
        return ofNullable(response.getBody())
                .map(UserInfoResponse::getStatus)
                .orElseGet(String::new);
    }

    private HttpEntity<String> getHttpEntityWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }
}
