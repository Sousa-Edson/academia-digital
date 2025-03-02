package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

    private AlunoRepository repository;

    public AlunoServiceImpl(AlunoRepository repository) {
        this.repository = repository;
    }


    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());

        return repository.save(aluno);
    }


    public Optional<Aluno> get(Long id) {
        return repository.findById(id);
    }


    public List<Aluno> getAll(String dataDeNascimento) {

        if(dataDeNascimento == null) {
            return repository.findAll();
        } else {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return repository.findByDataDeNascimento(localDate);
        }
    }


    public Aluno update(Long id, AlunoUpdateForm form) {
        Optional<Aluno> alunoOptional = repository.findById(id);

        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            if (form.getNome() != null) aluno.setNome(form.getNome());
            if (form.getBairro() != null) aluno.setBairro(form.getBairro());
            if (form.getDataDeNascimento() != null) aluno.setDataDeNascimento(form.getDataDeNascimento());
            return repository.save(aluno);
        } else {
            throw new RuntimeException("Aluno não encontrado");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {

        Aluno aluno = repository.findById(id).get();

        return aluno.getAvaliacoes();

    }
}
