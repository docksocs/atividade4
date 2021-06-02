package model.dao;


import java.util.List;

import entities.Aluno;

public interface AlunoDao {

	
	void inserir(Aluno aluno);
	void alterar(Aluno aluno);
	void apagar(int id);
	Aluno findByid(int id);
	List<Aluno> listar();
}
