package dev.amir.masterclass.infrastructure.Dao;

public record CustomerRegistrationRequest(
        Integer id,
        String name,
        String email,
        Integer age
) {
}
