package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl {

    private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;
    private final AlunoRepository alunoRepository;

    @Autowired
    public AvaliacaoFisicaServiceImpl(AvaliacaoFisicaRepository avaliacaoFisicaRepository, AlunoRepository alunoRepository) {
        this.avaliacaoFisicaRepository = avaliacaoFisicaRepository;
        this.alunoRepository = alunoRepository;
    }

    // Criar uma avaliação física
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        if (form.getPeso() <= 0 || form.getAltura() <= 0) {
            throw new IllegalArgumentException("Peso e altura devem ser maiores que zero.");
        }

        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());

        // Encontre o aluno que fez a avaliação ou lance uma exceção
        avaliacaoFisica.setAluno(alunoRepository.findById(form.getAlunoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado para o ID: " + form.getAlunoId())));

        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    // Atualizar uma avaliação física
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm form) {
        AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação física não encontrada para o ID: " + id));

        if (form.getPeso() > 0) {
            avaliacaoFisica.setPeso(form.getPeso());
        } else {
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }

        if (form.getAltura() > 0) {
            avaliacaoFisica.setAltura(form.getAltura());
        } else {
            throw new IllegalArgumentException("A altura deve ser maior que zero.");
        }

        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    // Deletar uma avaliação física
    public void delete(Long id) {
        if (!avaliacaoFisicaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação física não encontrada para o ID: " + id);
        }
        avaliacaoFisicaRepository.deleteById(id);
    }

    // Listar todas as avaliações físicas
    public List<AvaliacaoFisica> getAll() {
        return avaliacaoFisicaRepository.findAll();
    }
}
