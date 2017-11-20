package kr.or.dgit.SaleManagement.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.SaleManagement.dao.AddrDao;
import kr.or.dgit.SaleManagement.dao.AddrDaoImpl;
import kr.or.dgit.SaleManagement.dto.Addr;
import kr.or.dgit.SaleManagement.util.MyBatisSqlSessionFactory;

public class AddrService {
	private final static AddrService instance = new AddrService();
	 
	public static AddrService getInstance() {
		return instance;
	}
	
	public List<Addr> findAddrSearch(Addr addr){
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()){
			AddrDao addrDao = new AddrDaoImpl(sqlSession);
			return addrDao.searchAddr(addr);
		}		
	}
}
