package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.AccountLevel;

public interface AccountLevelDao {
	List<AccountLevel> selectAccountLevelByAll();

	AccountLevel selectAccountLevelByOne(AccountLevel accountLevel);
}
