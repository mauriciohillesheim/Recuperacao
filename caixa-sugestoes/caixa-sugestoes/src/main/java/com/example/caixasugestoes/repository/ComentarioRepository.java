package com.example.caixasugestoes.repository;

import com.example.caixasugestoes.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    /**
     * Busca todos os comentários de uma sugestão ordenados por data de envio decrescente
     */
    List<Comentario> findBySugestaoIdOrderByDataEnvioDesc(Long sugestaoId);

    /**
     * Busca comentários de uma sugestão com query customizada
     */
    @Query("SELECT c FROM Comentario c WHERE c.sugestao.id = :sugestaoId ORDER BY c.dataEnvio DESC")
    List<Comentario> findComentariosBySugestaoIdOrderByDataEnvioDesc(@Param("sugestaoId") Long sugestaoId);

    /**
     * Conta o número de comentários de uma sugestão
     */
    long countBySugestaoId(Long sugestaoId);

    /**
     * Verifica se existe comentário para uma sugestão
     */
    boolean existsBySugestaoId(Long sugestaoId);
}
