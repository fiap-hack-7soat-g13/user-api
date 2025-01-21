package com.fiap.challenge.user.core.common.util.validation;

import com.fiap.challenge.user.core.common.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Validator {

    private final Set<String> messages = new LinkedHashSet<>();

    public void add(Validation validation) {
        validation.getMessage().ifPresent(messages::add);
    }

    public void assertEmptyMessages() {
        if (!messages.isEmpty()) {
            throw new InvalidDataException(new ArrayList<>(messages));
        }
    }

}
