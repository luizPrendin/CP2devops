package com.api.exemplo.escola.controller;

import com.api.exemplo.escola.dto.ProfessorRequest;
import com.api.exemplo.escola.dto.ProfessorResponse;
import com.api.exemplo.escola.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public List<ProfessorResponse> getAllProfessores() {
        return professorService.getAllProfessores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getProfessorById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @PostMapping
    public ResponseEntity<ProfessorResponse> createProfessor(@Valid @RequestBody ProfessorRequest professorRequest) {
        return ResponseEntity.ok(professorService.createProfessor(professorRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponse> updateProfessor(@PathVariable Long id, @Valid @RequestBody ProfessorRequest professorRequest) {
        return ResponseEntity.ok(professorService.updateProfessor(id, professorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

}
