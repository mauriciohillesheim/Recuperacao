package com.example.caixasugestoes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ComentarioCreateDTO {

    @NotBlank(message = "Texto do comentário é obrigatório")
    @Size(max = 1000, message = "Comentário deve ter no máximo 1000 caracteres")
    private String texto;

    // Construtores
    public ComentarioCreateDTO() {}

    public ComentarioCreateDTO(String texto) {
        this.texto = texto;
    }

    // Getters e Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "ComentarioCreateDTO{" +
                "texto='" + texto + '\'' +
                '}';
    }
}
