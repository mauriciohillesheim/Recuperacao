package com.example.caixasugestoes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SugestaoCreateDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 255, message = "Título deve ter no máximo 255 caracteres")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 2000, message = "Descrição deve ter no máximo 2000 caracteres")
    private String descricao;

    // Construtores
    public SugestaoCreateDTO() {}

    public SugestaoCreateDTO(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "SugestaoCreateDTO{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
