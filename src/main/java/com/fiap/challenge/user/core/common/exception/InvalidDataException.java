package com.fiap.challenge.user.core.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InvalidDataException extends RuntimeException {

    private final List<String> messages;

}
