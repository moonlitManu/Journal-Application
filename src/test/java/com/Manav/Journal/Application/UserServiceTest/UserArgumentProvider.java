package com.Manav.Journal.Application.UserServiceTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;


public class UserArgumentProvider implements ArgumentsProvider{

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
            Arguments.of(new com.Manav.Journal.Application.model.User("Shayam", "1234")),
            Arguments.of(new com.Manav.Journal.Application.model.User("hari", "1234"))
        );
    }
    
    
}
