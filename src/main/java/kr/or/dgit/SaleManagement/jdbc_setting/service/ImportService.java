package kr.or.dgit.SaleManagement.jdbc_setting.service;

import kr.or.dgit.SaleManagement.jdbc_setting.Config;
import kr.or.dgit.SaleManagement.jdbc_setting.dao.DatabaseDao;

public class ImportService implements DbService {
	private static final ImportService instance = new ImportService();

	@Override
	public void service() {
		DatabaseDao.getInstance().executeUpdateSQL("SET FOREIGN_KEY_CHECKS = 0");
		DatabaseDao.getInstance().executeUpdateSQL("use " + Config.DB_NAME);
		for (String tableName : Config.TABLE_NAME) {
			DatabaseDao.getInstance().executeUpdateSQL(String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s ",	Config.getFilePath(tableName, false), tableName));
		}
		DatabaseDao.getInstance().executeUpdateSQL("SET FOREIGN_KEY_CHECKS = 1");
	}

	public ImportService() {}

	public static ImportService getInstance() {
		return instance;
	}
}
