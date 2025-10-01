package com.example.caixasugestoes.service;

import com.example.caixasugestoes.dto.SugestaoCreateDTO;
import com.example.caixasugestoes.dto.SugestaoResponseDTO;
import com.example.caixasugestoes.entity.Sugestao;
import com.example.caixasugestoes.repository.SugestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SugestaoService {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    /**
     * Cria uma nova sugestão
     */
    public SugestaoResponseDTO criarSugestao(SugestaoCreateDTO sugestaoCreateDTO) {
        Sugestao sugestao = new Sugestao();
        sugestao.setTitulo(sugestaoCreateDTO.getTitulo());
        sugestao.setDescricao(sugestaoCreateDTO.getDescricao());
        
        Sugestao sugestaoSalva = sugestaoRepository.save(sugestao);
        return new SugestaoResponseDTO(sugestaoSalva, false);
    }

    /**
     * Busca todas as sugestões ordenadas por data de atualização decrescente
     */
    @Transactional(readOnly = true)
    public List<SugestaoResponseDTO> buscarTodasSugestoes() {
        List<Sugestao> sugestoes = sugestaoRepository.findAllByOrderByDataAtualizacaoDesc();
        return sugestoes.stream()
                .map(sugestao -> new SugestaoResponseDTO(sugestao, false))
                .collect(Collectors.toList());
    }

    /**
     * Busca sugestões por título
     */
    @Transactional(readOnly = true)
    public List<SugestaoResponseDTO> buscarSugestoesPorTitulo(String titulo) {
        List<Sugestao> sugestoes = sugestaoRepository.findByTituloContainingIgnoreCaseOrderByDataAtualizacaoDesc(titulo);
        return sugestoes.stream()
                .map(sugestao -> new SugestaoResponseDTO(sugestao, false))
                .collect(Collectors.toList());
    }

    /**
     * Busca uma sugestão por ID com seus comentários
     */
    @Transactional(readOnly = true)
    public Optional<SugestaoResponseDTO> buscarSugestaoPorId(Long id) {
        Sugestao sugestao = sugestaoRepository.findByIdWithComentarios(id);
        if (sugestao != null) {
            // Ordena os comentários por data de envio decrescente
            sugestao.getComentarios().sort((c1, c2) -> c2.getDataEnvio().compareTo(c1.getDataEnvio()));
            return Optional.of(new SugestaoResponseDTO(sugestao, true));
        }
        return Optional.empty();
    }

    /**
     * Busca uma sugestão por ID (sem comentários)
     */
    @Transactional(readOnly = true)
    public Optional<Sugestao> buscarSugestaoEntityPorId(Long id) {
        return sugestaoRepository.findById(id);
    }

    /**
     * Atualiza a data de atualização de uma sugestão
     */
    public void atualizarDataAtualizacao(Sugestao sugestao) {
        sugestaoRepository.save(sugestao);
    }
}
