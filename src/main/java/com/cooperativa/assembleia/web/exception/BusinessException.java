package com.cooperativa.assembleia.web.exception;

import com.cooperativa.assembleia.web.message.MessageKey;

public class BusinessException extends BaseRuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessException(MessageKey messageKey, Object ...args) {
        super(messageKey, args);
    }
}
