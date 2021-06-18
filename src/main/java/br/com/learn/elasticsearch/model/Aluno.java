package br.com.learn.elasticsearch.model;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "alunoModel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	
	@Id
	private Integer raDoAlubo;
	
	@Field(type = FieldType.Text, name = "nomeDoAluno")
	private String nomeDoAluno;
	
	@Field(type = FieldType.Text, name = "curso")
	private String curso;
	
	

}
