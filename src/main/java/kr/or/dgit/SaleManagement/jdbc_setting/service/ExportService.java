package kr.or.dgit.SaleManagement.jdbc_setting.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.dgit.SaleManagement.jdbc_setting.Config;
import kr.or.dgit.SaleManagement.jdbc_setting.dao.DatabaseDao;
import kr.or.dgit.SaleManagement.jdbc_setting.jdbc.jdbcUtil;

public class ExportService implements DbService {
	private static final ExportService instance = new ExportService();

	private ExportService() {
	}

	public static ExportService getInstance() {
		return instance;
	}

	@Override
	public void service() {
		DatabaseDao.getInstance().executeUpdateSQL("USE " + Config.DB_NAME);
		checkBackupDir();
		for (String tblName : Config.TABLE_NAME) {
			exportData(String.format("select * from %s", tblName), Config.getFilePath(tblName, true), tblName);
		}
	}

	private void checkBackupDir() {
		File backupDir = new File(Config.EXPORT_DIR);

		if (backupDir.exists()) {
			for (File file : backupDir.listFiles()) {
				file.delete();
				System.out.printf("%s Delete Success! %n", file.getName());
			}
		} else {
			backupDir.mkdir();
			System.out.printf("%s make dir Success! %n", Config.EXPORT_DIR);
		}
	}

	private void exportData(String sql, String exportPath, String tblName) {
		StringBuilder sb = new StringBuilder();
		OutputStreamWriter dos = null;
		ResultSet rs = null;
		try {
			rs = DatabaseDao.getInstance().executeQuerySQL(sql);
			int colCnt = rs.getMetaData().getColumnCount();// 컬럼의 개수
			while (rs.next()) {
				for (int i = 1; i <= colCnt; i++) {
					sb.append(rs.getObject(i) + "	");
				}
				sb.replace(sb.length() - 1, sb.length(), ""); // 마지막 라인의 comma 제거
				sb.append("\r\n");
			}

			backupFileWrite(sb.toString(), exportPath);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(dos);
		}
	}

	private void backupFileWrite(String str, String exportPath)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		try (OutputStreamWriter dos = new OutputStreamWriter(new FileOutputStream(exportPath), "UTF-8")) {
			dos.write(str);
		}
	}
}