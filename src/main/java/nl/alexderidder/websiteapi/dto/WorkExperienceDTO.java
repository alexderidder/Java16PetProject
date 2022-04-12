package nl.alexderidder.websiteapi.dto;

import java.util.UUID;

public record WorkExperienceDTO(UUID id, String summary, String title,  String description, boolean archived) {
}
