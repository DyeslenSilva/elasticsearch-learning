package br.com.learn.elasticsearch;

import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import br.com.learn.elasticsearch.model.Aluno;
import br.com.learn.elasticsearch.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AprendendoElasticSearch {

	private ElasticsearchOperations elasticsearchOperations;
	
	private List<Aluno> alunoList;
	private AlunoRepository alunoRepository;
	
	public void buildIndex() {
		elasticsearchOperations.indexOps(Aluno.class).refresh();
		alunoRepository.saveAll(prepareDataset());
	}
	
	private Collection<Aluno> prepareDataset(){
		ClassPathResource resource = new ClassPathResource("aluno.csv"); 
		
		return alunoList;
	}	
	public static void main(String[] args) {
		SpringApplication.run(AprendendoElasticSearch.class, args);
	}

}
