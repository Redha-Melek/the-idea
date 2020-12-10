package com.redha.idea.exception.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of= {"status", "entityName", "errorKey"})
@ToString(of= {"status", "entityName", "errorKey"})
public class ErrorResponseBean {
    int status;
    String entityName;
    String errorKey;
    String message;
}
