package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
    }


    public Matricula create(MatriculaForm form) {
        Aluno aluno = alunoRepository.findById(form.getAlunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado para o ID: " + form.getAlunoId()));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);

        return matriculaRepository.save(matricula);
    }


    public Optional<Matricula> get(Long id) {
        return Optional.ofNullable(matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada para o ID: " + id)));
    }


    public List<Matricula> getAll(String bairro) {
        if (bairro == null) {
            return matriculaRepository.findAll();
        } else {
            List<Matricula> matriculas = matriculaRepository.findAlunosMatriculadosBairro(bairro);
            if (matriculas.isEmpty()) {
                throw new RuntimeException("Nenhuma matrícula encontrada para o bairro: " + bairro);
            }
            return matriculas;
        }
    }


    public void delete(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada para o ID: " + id));

        matriculaRepository.delete(matricula);
    }
}
