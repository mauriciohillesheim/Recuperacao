package com.example.caixasugestoes.dto;

import com.example.caixasugestoes.entity.Sugestao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SugestaoResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataEnvio;
    private LocalDateTime dataAtualizacao;
    private List<ComentarioResponseDTO> comentarios;

    // Construtores
    public SugestaoResponseDTO() {}

    public SugestaoResponseDTO(Sugestao sugestao) {
        this.id = sugestao.getId();
        this.titulo = sugestao.getTitulo();
        this.descricao = sugestao.getDescricao();
        this.dataEnvio = sugestao.getDataEnvio();
        this.dataAtualizacao = sugestao.getDataAtualizacao();
        this.comentarios = sugestao.getComentarios().stream()
                .map(ComentarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public SugestaoResponseDTO(Sugestao sugestao, boolean incluirComentarios) {
        this.id = sugestao.getId();
        this.titulo = sugestao.getTitulo();
        this.descricao = sugestao.getDescricao();
        this.dataEnvio = sugestao.getDataEnvio();
        this.dataAtualizacao = sugestao.getDataAtualizacao();
        
        if (incluirComentarios && sugestao.getComentarios() != null) {
            this.comentarios = sugestao.getComentarios().stream()
                    .map(ComentarioResponseDTO::new)
                    .collect(Collectors.toList());
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<ComentarioResponseDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioResponseDTO> comentarios) {
        this.comentarios = comentarios;
    }
}
