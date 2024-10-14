package com.api.exemplo.escola.service;

import com.api.exemplo.escola.dto.AlunoResponse;
import com.api.exemplo.escola.dto.ProfessorRequest;
import com.api.exemplo.escola.dto.ProfessorResponse;
import com.api.exemplo.escola.model.Professor;
import com.api.exemplo.escola.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<ProfessorResponse> getAllProfessores() {
        return professorRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public ProfessorResponse getProfessorById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return convertToResponse(professor);
    }

    public ProfessorResponse createProfessor(ProfessorRequest request) {
        Professor professor = new Professor();
        professor.setNome(request.getNome());
        professor = professorRepository.save(professor);
        return convertToResponse(professor);
    }

    public ProfessorResponse updateProfessor(Long id, ProfessorRequest request) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        professor.setNome(request.getNome());
        return convertToResponse(professorRepository.save(professor));
    }

    public void deleteProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        professorRepository.delete(professor);
    }

    private ProfessorResponse convertToResponse(Professor professor) {
        ProfessorResponse response = new ProfessorResponse();
        response.setId(professor.getId());
        response.setNome(professor.getNome());

        // Verifica se a lista de alunos não é nula
        if (professor.getAlunos() != null) {
            response.setAlunos(professor.getAlunos().stream()
                    .map(aluno -> {
                        AlunoResponse alunoResponse = new AlunoResponse();
                        alunoResponse.setId(aluno.getId());
                        alunoResponse.setNome(aluno.getNome());
                        alunoResponse.setProfessorNome(professor.getNome());
                        return alunoResponse;
                    }).collect(Collectors.toList()));
        } else {
            // Se for nula, inicializa a lista de alunos vazia
            response.setAlunos(new ArrayList<>());
        }

        return response;
    }

}
