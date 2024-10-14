package com.api.exemplo.escola.service;

import com.api.exemplo.escola.dto.AlunoRequest;
import com.api.exemplo.escola.dto.AlunoResponse;
import com.api.exemplo.escola.model.Aluno;
import com.api.exemplo.escola.model.Professor;
import com.api.exemplo.escola.repository.AlunoRepository;
import com.api.exemplo.escola.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public List<AlunoResponse> getAllAlunos() {
        return alunoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponse getAlunoById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return convertToResponse(aluno);
    }

    public AlunoResponse createAluno(AlunoRequest request) {
        Professor professor = professorRepository.findById(request.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setProfessor(professor);
        aluno = alunoRepository.save(aluno);

        return convertToResponse(aluno);
    }

    public AlunoResponse updateAluno(Long id, AlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Professor professor = professorRepository.findById(request.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        aluno.setNome(request.getNome());
        aluno.setProfessor(professor);
        return convertToResponse(alunoRepository.save(aluno));
    }

    public void deleteAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        alunoRepository.delete(aluno);
    }

    private AlunoResponse convertToResponse(Aluno aluno) {
        AlunoResponse response = new AlunoResponse();
        response.setId(aluno.getId());
        response.setNome(aluno.getNome());
        response.setProfessorNome(aluno.getProfessor().getNome());
        return response;
    }

}
