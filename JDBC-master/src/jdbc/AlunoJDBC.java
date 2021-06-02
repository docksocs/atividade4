package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	Connection con;
	String sql;
	PreparedStatement pst;
	
	
	public void salvar(Aluno a) throws IOException {
		
		try {
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso !");
			
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES ( ?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
			db.fechaConexao();
			System.out.println("Conexão fechada com sucesso !");
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() throws IOException {
		try {
			con = db.getConexao();
			
			List<Aluno> aluno = new ArrayList<Aluno>();
			PreparedStatement stmt = this.con.prepareStatement("select * from aluno");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Aluno alunos = new Aluno();
				alunos.setId(rs.getInt("id"));
				alunos.setNome(rs.getString("Nome"));
				alunos.setSexo(rs.getString("Sexo"));
				alunos.setDt_nasc(rs.getDate("Dt_nasc"));
				aluno.add(alunos);
				
				
			}

			return aluno;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void apagar(int id) throws IOException {
PreparedStatement stmt = null;
		
		String sql = "delete from aluno where id = ?";
		try {
			Connection con = db.getConexao();
			
			
		    stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
	        System.out.println("\nExclusão do aluno realizado com sucesso!");
			
			db.fechaConexao();
			System.out.println("Conexão fechada com sucesso !");
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public void alterar(Aluno a) throws IOException {
PreparedStatement stmt = null;
		
		String sql = "update aluno set nome = ? where id = ?";
		try {
			Connection con = db.getConexao();
				
		    stmt = con.prepareStatement(sql);
			stmt.setString(1, a.getNome());
			stmt.setInt(2, a.getId());
			stmt.executeUpdate();
			stmt.close();
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
	}
}

