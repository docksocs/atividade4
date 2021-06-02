package model.dao;

import java.io.IOException;
import java.sql.SQLException;

import jdbc.db;

public class FactoryDao {

	public static AlunoDao createAlunoDao() throws IOException, SQLException{
		return new AlunoDaoImp(db.getConexao());
	}
}
