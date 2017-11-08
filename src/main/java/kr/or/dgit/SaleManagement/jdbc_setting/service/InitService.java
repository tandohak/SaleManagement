package kr.or.dgit.SaleManagement.jdbc_setting.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import kr.or.dgit.SaleManagement.jdbc_setting.dao.DatabaseDao;

public class InitService implements DbService {
	private static final InitService instance = new InitService();
	
	@Override
	   public void service() {
	      File f = new File(System.getProperty("user.dir") + "\\resources\\create_sql.txt");
	      try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"))){
	         StringBuilder statement = new StringBuilder();
	         for (String line; (line = br.readLine()) != null;) {
	            if (!line.isEmpty() && !line.startsWith("--")) {
	               statement.append(line.trim()+" ");
	               System.out.println(line);
	            }
	            if (line.endsWith(";")) {
	               DatabaseDao.getInstance().executeUpdateSQL(statement.toString());
	               statement.setLength(0);
	            }
	         }
	      } catch (UnsupportedEncodingException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      } catch (FileNotFoundException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      } catch (IOException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      }
	   }

	public static InitService getInstance() {
		return instance;
	}

	public InitService() {}
}
