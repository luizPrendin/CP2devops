package com.api.exemplo.escola.controller;

import com.api.exemplo.escola.dto.AlunoRequest;
import com.api.exemplo.escola.dto.AlunoResponse;
import com.api.exemplo.escola.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")

public class AlunoController {

    private final AlunoService alunoService;
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoResponse> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getAlunoById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.getAlunoById(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> createAluno(@Valid @RequestBody AlunoRequest alunoRequest) {
        return ResponseEntity.ok(alunoService.createAluno(alunoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoRequest alunoRequest) {
        return ResponseEntity.ok(alunoService.updateAluno(id, alunoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }

}
