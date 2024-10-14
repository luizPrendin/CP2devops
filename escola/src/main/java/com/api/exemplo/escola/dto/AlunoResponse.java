package com.api.exemplo.escola.dto;

import lombok.Data;

@Data
public class AlunoResponse {
    private Long id;
    private String nome;
    private String professorNome;
}
