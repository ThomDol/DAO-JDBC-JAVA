package cda.tom.service;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {



	 
	 public abstract List<T> findAll() throws ClassNotFoundException;
	 
	 
	 public abstract T find(long id) throws ClassNotFoundException;
	 
	 public abstract void save(T obj) throws SQLException, ClassNotFoundException;
 
	 public abstract void delete(T obj) throws SQLException, ClassNotFoundException;
	 
	// public abstract void update (T obj) throws SQLException, ClassNotFoundException;
	 
	
	
	

}
