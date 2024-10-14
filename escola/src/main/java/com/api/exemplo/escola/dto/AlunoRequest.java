package com.api.exemplo.escola.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlunoRequest {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private Long professorId;
}
