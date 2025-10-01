package com.example.caixasugestoes.repository;

import com.example.caixasugestoes.entity.Sugestao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {

    /**
     * Busca todas as sugestões ordenadas por data de atualização decrescente
     */
    List<Sugestao> findAllByOrderByDataAtualizacaoDesc();

    /**
     * Busca sugestões por título contendo o texto especificado (case insensitive)
     * ordenadas por data de atualização decrescente
     */
    @Query("SELECT s FROM Sugestao s WHERE LOWER(s.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')) ORDER BY s.dataAtualizacao DESC")
    List<Sugestao> findByTituloContainingIgnoreCaseOrderByDataAtualizacaoDesc(@Param("titulo") String titulo);

    /**
     * Busca todas as sugestões com paginação ordenadas por data de atualização decrescente
     */
    Page<Sugestao> findAllByOrderByDataAtualizacaoDesc(Pageable pageable);

    /**
     * Busca sugestões por título com paginação
     */
    @Query("SELECT s FROM Sugestao s WHERE LOWER(s.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')) ORDER BY s.dataAtualizacao DESC")
    Page<Sugestao> findByTituloContainingIgnoreCaseOrderByDataAtualizacaoDesc(@Param("titulo") String titulo, Pageable pageable);

    /**
     * Busca uma sugestão por ID com seus comentários carregados
     */
    @Query("SELECT s FROM Sugestao s LEFT JOIN FETCH s.comentarios WHERE s.id = :id")
    Sugestao findByIdWithComentarios(@Param("id") Long id);
}
