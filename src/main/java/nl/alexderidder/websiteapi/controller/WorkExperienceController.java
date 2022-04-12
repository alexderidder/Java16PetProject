package nl.alexderidder.websiteapi.controller;

import lombok.RequiredArgsConstructor;
import nl.alexderidder.websiteapi.dto.CreateWorkExperienceDTO;
import nl.alexderidder.websiteapi.dto.WorkExperienceDTO;
import nl.alexderidder.websiteapi.service.WorkExperienceService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("work-experience")
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @GetMapping
    public ResponseEntity<List<WorkExperienceDTO>> getWorkExperiences(){
        return ResponseEntity.ok(workExperienceService.getArchivedWorkExperiences());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkExperienceDTO> postWorkExperience(final @RequestBody CreateWorkExperienceDTO workExperienceDTO){
        return ResponseEntity.ok(workExperienceService.createWorkExperience(workExperienceDTO));
    }

    @PutMapping(path = "/{workExperienceId}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkExperienceDTO> archiveWorkExperience(final @PathVariable("workExperienceId") UUID workExperienceId){
        return ResponseEntity.ok(workExperienceService.archiveWorkExperience(workExperienceId));
    }
}
