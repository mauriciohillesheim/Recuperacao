package com.example.caixasugestoes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sugestao_id", nullable = false)
    private Sugestao sugestao;

    @NotBlank(message = "Texto do comentário é obrigatório")
    @Size(max = 1000, message = "Comentário deve ter no máximo 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String texto;

    @CreationTimestamp
    @Column(name = "data_envio", nullable = false, updatable = false)
    private LocalDateTime dataEnvio;

    // Construtores
    public Comentario() {}

    public Comentario(String texto) {
        this.texto = texto;
    }

    public Comentario(String texto, Sugestao sugestao) {
        this.texto = texto;
        this.sugestao = sugestao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sugestao getSugestao() {
        return sugestao;
    }

    public void setSugestao(Sugestao sugestao) {
        this.sugestao = sugestao;
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

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", sugestaoId=" + (sugestao != null ? sugestao.getId() : null) +
                '}';
    }
}
