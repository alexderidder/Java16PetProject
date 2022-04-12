package nl.alexderidder.websiteapi.service;

import liquibase.pro.packaged.W;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.alexderidder.websiteapi.dto.CreateWorkExperienceDTO;
import nl.alexderidder.websiteapi.dto.WorkExperienceDTO;
import nl.alexderidder.websiteapi.model.WorkExperience;
import nl.alexderidder.websiteapi.repository.WorkExperienceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;

    public List<WorkExperienceDTO> getArchivedWorkExperiences(){
        return workExperienceRepository.findAllByArchivedFalse()
                .stream()
                .map(this::mapWorkExperience)
                .collect(Collectors.toList());
    }

    public WorkExperienceDTO createWorkExperience(final CreateWorkExperienceDTO workExperienceDTO){
        final WorkExperience workExperience = new WorkExperience();
        workExperience.setSummary(workExperienceDTO.summary());
        workExperience.setDescription(workExperienceDTO.description());
        workExperience.setTitle(workExperienceDTO.title());
        workExperience.setArchived(workExperienceDTO.archived());

        return mapWorkExperience(workExperienceRepository.save(workExperience));
    }

    public WorkExperienceDTO archiveWorkExperience(final UUID workExperienceId){
        WorkExperience workExperience = workExperienceRepository.findById(workExperienceId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        workExperience.setArchived(true);

        return mapWorkExperience(workExperienceRepository.save(workExperience));
    }

    public WorkExperienceDTO mapWorkExperience(final WorkExperience workExperience){
        return new WorkExperienceDTO(workExperience.getId()
                , workExperience.getSummary()
                , workExperience.getTitle()
                , workExperience.getDescription()
                , workExperience.isArchived()
                );
    }
}
