package com.example.alunos.service;

import com.example.alunos.dto.AlunoRequest;
import com.example.alunos.dto.AlunoResponse;
import com.example.alunos.dto.MatriculaDTO;
import com.example.alunos.entity.Aluno;
import com.example.alunos.entity.Matricula;
import com.example.alunos.mapper.AlunoMapper;
import com.example.alunos.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class AlunoService {

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoResponse salvar(AlunoRequest request){
        Aluno aluno = alunoMapper.toEntity(request);
        alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> listarTodos(){
        return alunoRepository.findAll().stream().map(alunoMapper::toResponse).toList();
    }

    public List<MatriculaDTO> listarMatriculas(Long id){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return aluno.getMatriculas().stream().map(m -> new MatriculaDTO(m.getCodigoMatricula(), m.getNomeCurso(), m.getDataInicio())).toList();
    }

    public void removerAluno(Long id){
if (!alunoRepository.existsById(id)){
    throw new EntityNotFoundException("ID do aluno não encontrado");
        }
    alunoRepository.deleteById(id);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request){
        Aluno a = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        a.setName(request.nome());
        a.setTelefone(request.telefone());
        a.setDataNascimento(request.dataNascimento());

        for (MatriculaDTO m : request.matriculas()){
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(m.codigoMatricula());
            matricula.setDataInicio(m.dataInicio());
            matricula.setNomeCurso(m.nomeCurso());
            a.getMatriculas().add(matricula);
        }
        return alunoMapper.toResponse(alunoRepository.save(a));
    }
}
