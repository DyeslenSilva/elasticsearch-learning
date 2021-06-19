package br.com.learn.elasticsearch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.learn.elasticsearch.model.Aluno;
import br.com.learn.elasticsearch.repository.AlunoRepository;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;
	
	
	public void cadastroDeIndexDeAlunos(List<Aluno> alunos) {
		alunoRepository.saveAll(alunos);
	}
	
	public void cadastroAlunoIndex(Aluno aluno) {
		alunoRepository.save(aluno);
	}
}