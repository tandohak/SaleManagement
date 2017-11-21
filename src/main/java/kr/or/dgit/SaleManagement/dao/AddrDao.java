package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import kr.or.dgit.SaleManagement.dto.Addr;

public interface AddrDao {
	List<Addr> searchAddr(Addr addr,RowBounds rowBounds); 
}
