package com.example.caixasugestoes.service;

import com.example.caixasugestoes.dto.ComentarioCreateDTO;
import com.example.caixasugestoes.dto.ComentarioResponseDTO;
import com.example.caixasugestoes.entity.Comentario;
import com.example.caixasugestoes.entity.Sugestao;
import com.example.caixasugestoes.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private SugestaoService sugestaoService;

    /**
     * Cria um novo comentário para uma sugestão
     */
    public ComentarioResponseDTO criarComentario(Long sugestaoId, ComentarioCreateDTO comentarioCreateDTO) {
        // Busca a sugestão
        Sugestao sugestao = sugestaoService.buscarSugestaoEntityPorId(sugestaoId)
                .orElseThrow(() -> new RuntimeException("Sugestão não encontrada com ID: " + sugestaoId));

        // Cria o comentário
        Comentario comentario = new Comentario();
        comentario.setTexto(comentarioCreateDTO.getTexto());
        comentario.setSugestao(sugestao);

        // Salva o comentário
        Comentario comentarioSalvo = comentarioRepository.save(comentario);

        // Atualiza a data de atualização da sugestão
        sugestao.setDataAtualizacao(LocalDateTime.now());
        sugestaoService.atualizarDataAtualizacao(sugestao);

        return new ComentarioResponseDTO(comentarioSalvo);
    }

    /**
     * Busca todos os comentários de uma sugestão ordenados por data de envio decrescente
     */
    @Transactional(readOnly = true)
    public List<ComentarioResponseDTO> buscarComentariosPorSugestao(Long sugestaoId) {
        List<Comentario> comentarios = comentarioRepository.findBySugestaoIdOrderByDataEnvioDesc(sugestaoId);
        return comentarios.stream()
                .map(ComentarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Conta o número de comentários de uma sugestão
     */
    @Transactional(readOnly = true)
    public long contarComentariosPorSugestao(Long sugestaoId) {
        return comentarioRepository.countBySugestaoId(sugestaoId);
    }

    /**
     * Verifica se uma sugestão possui comentários
     */
    @Transactional(readOnly = true)
    public boolean sugestaoPossuiComentarios(Long sugestaoId) {
        return comentarioRepository.existsBySugestaoId(sugestaoId);
    }
}
