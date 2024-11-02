package com.devsuperior_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldMessageError {

    private String fieldName;
    private String message;

}
