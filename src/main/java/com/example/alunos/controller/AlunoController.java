package com.example.alunos.controller;

import com.example.alunos.dto.AlunoRequest;
import com.example.alunos.dto.AlunoResponse;
import com.example.alunos.dto.MatriculaDTO;
import com.example.alunos.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> adicionarAluno(@RequestBody AlunoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvar(request));
    }

    @GetMapping
    public List<AlunoResponse> listarAlunos(){
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}/matriculas")
    public List<MatriculaDTO> listarMatriculas(@PathVariable Long id){
        return alunoService.listarMatriculas(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizar (@PathVariable Long id, @RequestBody AlunoRequest request){
        return ResponseEntity.ok(alunoService.atualizar(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        alunoService.removerAluno(id);
        return ResponseEntity.noContent().build();
}

}
