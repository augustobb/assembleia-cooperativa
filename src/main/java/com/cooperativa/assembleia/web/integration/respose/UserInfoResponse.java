package com.cooperativa.assembleia.web.integration.respose;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponse implements Serializable {
    private static final long serialVersionUID = -3293315691222254009L;

    @JsonProperty("status")
    private String status;
}
