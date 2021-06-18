package br.com.learn.elasticsearch.service;

import org.springframework.stereotype.Service;

import br.com.learn.elasticsearch.model.Aluno;
import br.com.learn.elasticsearch.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlunoService {

	private AlunoRepository alunoRepository;
	
	public void cadastroAluno(Aluno aluno) {
		alunoRepository.save(aluno);
	}
}