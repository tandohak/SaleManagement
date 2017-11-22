package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.Addr;

public interface AddrDao {
	List<Addr> selectAddrBySido();
	List<Addr> selectAddrBySigungu(Addr addr);
	List<Addr> selectAddrByDoro(Addr addr);
}
