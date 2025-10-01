package com.example.caixasugestoes.controller;

import com.example.caixasugestoes.dto.ComentarioCreateDTO;
import com.example.caixasugestoes.dto.ComentarioResponseDTO;
import com.example.caixasugestoes.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sugestoes")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    /**
     * Endpoint: POST /sugestoes/{id}/comentarios
     * Adiciona um comentário a uma sugestão
     * Atualiza a data de atualização da sugestão
     */
    @PostMapping("/{id}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> adicionarComentario(
            @PathVariable Long id,
            @Valid @RequestBody ComentarioCreateDTO comentarioCreateDTO) {
        try {
            ComentarioResponseDTO comentarioCriado = comentarioService.criarComentario(id, comentarioCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(comentarioCriado);
        } catch (RuntimeException e) {
            // Sugestão não encontrada
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint: GET /sugestoes/{id}/comentarios
     * Busca todos os comentários de uma sugestão ordenados por data de envio decrescente
     */
    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> buscarComentarios(@PathVariable Long id) {
        try {
            List<ComentarioResponseDTO> comentarios = comentarioService.buscarComentariosPorSugestao(id);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint: GET /sugestoes/{id}/comentarios/count
     * Conta o número de comentários de uma sugestão
     */
    @GetMapping("/{id}/comentarios/count")
    public ResponseEntity<Long> contarComentarios(@PathVariable Long id) {
        try {
            long count = comentarioService.contarComentariosPorSugestao(id);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
