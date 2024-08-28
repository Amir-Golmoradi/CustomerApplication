package dev.amir.masterclass.infrastructure.custom_actions;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
