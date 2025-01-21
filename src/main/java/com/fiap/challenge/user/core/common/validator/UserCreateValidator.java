package com.fiap.challenge.user.core.common.validator;

import com.fiap.challenge.user.core.common.util.validation.Validation;
import com.fiap.challenge.user.core.common.util.validation.Validator;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCreateValidator {

    private final UserGateway userGateway;

    public void validate(User user) {

        Validator validator = new Validator();

        validator.add(Validation.notBlank(user.getName(), "É obrigatório informar o nome"));
        validator.add(Validation.notBlank(user.getEmail(), "É obrigatório informar o e-mail"));
        validator.add(Validation.notInvalidEmail(user.getEmail(), "O e-mail informado é inválido"));
        validator.add(Validation.assertFalse(emailAlreadyExists(user.getEmail()), "Já existe um cliente com o e-mail '%s'", user.getEmail()));
        validator.add(Validation.notBlank(user.getDocument(), "É obrigatório informar o documento"));
        validator.add(Validation.notInvalidDocument(user.getDocument(), "O documento informado é inválido"));
        validator.add(Validation.assertFalse(documentAlreadyExists(user.getDocument()), "Já existe um cliente com o documento '%s'", user.getDocument()));
        validator.add(Validation.notBlank(user.getPassword(), "É obrigatório informar a senha"));

        if (user.getPassword() != null) {
            validator.add(Validation.assertFalse(user.getPassword().length() < 8, "A senha deve conter ao menos 8 caracteres"));
            validator.add(Validation.assertFalse(weakPassword(user.getPassword()), "A senha deve conter letras maiúsculas, minúsculas, números e caracteres especiais"));
        }

        validator.assertEmptyMessages();
    }

    private boolean documentAlreadyExists(String document) {
        return document != null && !userGateway.findByDocument(document).isEmpty();
    }

    private boolean emailAlreadyExists(String email) {
        return email != null && !userGateway.findByEmail(email).isEmpty();
    }

    private static boolean weakPassword(String password) {
        String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*";
        return !password.matches(pattern);
    }

}
