package nl.alexderidder.websiteapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class WorkExperience {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String summary;

    private String title;

    private String description;

    private boolean archived;
}
