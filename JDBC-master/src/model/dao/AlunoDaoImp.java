package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import entities.Aluno;

public class AlunoDaoImp implements AlunoDao{

	private Connection con;
	
	public AlunoDaoImp(Connection con) {
		this.con = con;
	}

	@Override
	public void inserir(Aluno aluno)  {
		
		String sql = "insert into aluno " + "(nome, sexo, dt_nasc)" + " values (?, ?, ?)";

			try {
				
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ;
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getSexo());
			Calendar c = new GregorianCalendar(2001, 0, 0);
			stmt.setDate(3, new Date(c.getTimeInMillis()));
			
			int linhas = stmt.executeUpdate();
			
			if(linhas > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs.next()) {
					aluno.setId(rs.getInt(1));
				}
			}
			
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void alterar(Aluno aluno) {
		PreparedStatement stmt = null;
		
		String sql = "update aluno set nome = ? where id = ?";
		try {
			
		    stmt = con.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setInt(2, aluno.getId());
			stmt.executeUpdate();
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	public void apagar(int id) {
		PreparedStatement stmt = null;
		
		String sql = "delete from aluno where id = ?";
		try {
			
		    stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	public Aluno findByid(int id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		

		String sql = "select * from aluno where id = ?";

			try {
				st = con.prepareStatement(sql) ;
			    st.setInt(1, id);
			    rs = st.executeQuery();
			    if(rs.next()) {
			    	Aluno obj = new Aluno();
			    	obj.setId(rs.getInt(1));
			    	obj.setNome(rs.getString(2));
			    	obj.setSexo(rs.getString(3));
			    	obj.setDt_nasc(rs.getDate(4));
			    	
			    	return obj;
			}
			    return null;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Aluno> listar() {
		try {
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

	
}
