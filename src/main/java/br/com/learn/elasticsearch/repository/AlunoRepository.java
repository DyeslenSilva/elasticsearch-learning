package br.com.learn.elasticsearch.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.learn.elasticsearch.model.Aluno;

public interface AlunoRepository extends  ElasticsearchRepository<Aluno, Integer> {

	Page<Aluno> findByRA(Integer raAluno, Pageable pageable);
	
    @Query("{\"bool\": {\"must\": [{\"match\": {\"aluno.raAluno\": \"?0\"}}]}}")
    Page<Aluno> findByRANameUsingCustomQuery(Integer raAluno, Pageable pageable);

}
