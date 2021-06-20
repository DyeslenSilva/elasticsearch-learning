package br.com.learn.elasticsearch.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.learn.elasticsearch.model.Aluno;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlunoSearch {

	private static final String ALUNO_INDEX = "alunoIndex";
	
	private ElasticsearchOperations elasticsearchOperations;
	
	public List<IndexedObjectInformation> criandoIndicesDeAlunosEmMassa(List<Aluno> aluno){
		List<IndexQuery> queries = aluno.stream().map(alunoSearch -> new IndexQueryBuilder()
				.withId(alunoSearch.getRaDoAluno().toString())
				.withObject(alunoSearch).build()).collect(Collectors.toList());
		
		return elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(ALUNO_INDEX));
	}
	
	
	public String criandoIndicesDeAlunos(Aluno aluno) {
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withId(aluno.getRaDoAluno().toString())
				.withObject(aluno).build();
		
		String documentID = elasticsearchOperations
				.index(indexQuery, IndexCoordinates.of(ALUNO_INDEX));
		
		return documentID;
	}
	
	public void encontrarAlunoPorRA(Integer raAluno) {
			QueryBuilder queryBuilder = QueryBuilders
					.matchQuery("aluno", raAluno);
			
			NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
					.withQuery(queryBuilder).build();
			
		org.springframework.data.elasticsearch.core.SearchHits<Aluno> alunosHits = elasticsearchOperations.search(searchQuery, Aluno.class,
					IndexCoordinates.of(ALUNO_INDEX));
	}
	
	public void encontrarAlunoPorNome(String nome) {
		 StringQuery searchQuery = new StringQuery(
			      "{\"match\":{\"name\":{\"query\":\""+ nome + "\"}}}\"");
		 
		 org.springframework.data.elasticsearch.core.SearchHits<Aluno> alunos = elasticsearchOperations.search(searchQuery, Aluno.class,
				 IndexCoordinates.of(ALUNO_INDEX));
	}
	
	public List<Aluno>  precessSearch(String search, String nome,String desc){
		log.info("Search with query {}",search);
		
		QueryBuilder queryBuilder = 
				QueryBuilders.multiMatchQuery(search, nome,desc)
				.fuzziness(Fuzziness.AUTO);
		
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(queryBuilder).build();
		
		org.springframework.data.elasticsearch.core.SearchHits<Aluno> alunoHits = elasticsearchOperations
				.search(searchQuery, Aluno.class,IndexCoordinates.of(ALUNO_INDEX));
		
		List<Aluno> alunosMatches = new ArrayList<Aluno>();
		
		alunoHits.forEach(searchHit ->{
			alunosMatches.add(searchHit.getContent());
		});
		
		return alunosMatches;
	}
	
	
	
}
