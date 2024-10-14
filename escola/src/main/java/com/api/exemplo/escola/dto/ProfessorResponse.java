package com.api.exemplo.escola.dto;

import lombok.Data;

import java.util.List;
@Data
public class ProfessorResponse {

    private Long id;
    private String nome;
    private List<AlunoResponse> alunos;
}
