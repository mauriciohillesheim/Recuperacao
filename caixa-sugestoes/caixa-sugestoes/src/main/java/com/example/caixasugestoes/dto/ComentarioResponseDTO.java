package com.example.caixasugestoes.dto;

import com.example.caixasugestoes.entity.Comentario;

import java.time.LocalDateTime;

public class ComentarioResponseDTO {

    private Long id;
    private String texto;
    private LocalDateTime dataEnvio;
    private Long sugestaoId;

    // Construtores
    public ComentarioResponseDTO() {}

    public ComentarioResponseDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.texto = comentario.getTexto();
        this.dataEnvio = comentario.getDataEnvio();
        this.sugestaoId = comentario.getSugestao() != null ? comentario.getSugestao().getId() : null;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Long getSugestaoId() {
        return sugestaoId;
    }

    public void setSugestaoId(Long sugestaoId) {
        this.sugestaoId = sugestaoId;
    }
}
