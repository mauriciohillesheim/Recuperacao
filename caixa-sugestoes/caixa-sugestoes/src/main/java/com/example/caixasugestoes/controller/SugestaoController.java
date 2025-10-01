package com.example.caixasugestoes.controller;

import com.example.caixasugestoes.dto.SugestaoCreateDTO;
import com.example.caixasugestoes.dto.SugestaoResponseDTO;
import com.example.caixasugestoes.service.SugestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sugestoes")
@CrossOrigin(origins = "*")
public class SugestaoController {

    @Autowired
    private SugestaoService sugestaoService;

    /**
     * Endpoint: POST /sugestoes
     * Cria uma nova sugestão
     */
    @PostMapping
    public ResponseEntity<SugestaoResponseDTO> criarSugestao(@Valid @RequestBody SugestaoCreateDTO sugestaoCreateDTO) {
        try {
            SugestaoResponseDTO sugestaoCriada = sugestaoService.criarSugestao(sugestaoCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(sugestaoCriada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint: GET /sugestoes
     * Consulta todas as sugestões ordenadas por data de atualização decrescente
     * Permite filtro opcional por título
     */
    @GetMapping
    public ResponseEntity<List<SugestaoResponseDTO>> buscarSugestoes(
            @RequestParam(value = "titulo", required = false) String titulo) {
        try {
            List<SugestaoResponseDTO> sugestoes;
            
            if (titulo != null && !titulo.trim().isEmpty()) {
                sugestoes = sugestaoService.buscarSugestoesPorTitulo(titulo.trim());
            } else {
                sugestoes = sugestaoService.buscarTodasSugestoes();
            }
            
            return ResponseEntity.ok(sugestoes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint: GET /sugestoes/{id}
     * Consulta uma sugestão por ID com seus comentários ordenados por data de envio decrescente
     */
    @GetMapping("/{id}")
    public ResponseEntity<SugestaoResponseDTO> buscarSugestaoPorId(@PathVariable Long id) {
        try {
            Optional<SugestaoResponseDTO> sugestao = sugestaoService.buscarSugestaoPorId(id);
            
            if (sugestao.isPresent()) {
                return ResponseEntity.ok(sugestao.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
