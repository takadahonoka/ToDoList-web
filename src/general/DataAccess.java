package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccess {

	private final String DB_HOST = "localhost:3306";
	private final String DB_NAME = "todo2019_db";
	private final String DB_USER = "root";
	private final String DB_PASS = "";

	protected Connection cn;
	protected PreparedStatement pst;
	protected ResultSet rs;

	public DataAccess() throws SQLException, ClassNotFoundException {
		try {
			// MySQLのドライバと接続する
			Class.forName("com.mysql.jdbc.Driver");

			// Connectionにデータベース名、ユーザ名、パスワードを代入することで
			// 使用するデータベースを特定できる
			this.cn = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?autoReconnect=true&useSSL=false&characterEncoding=utf8", DB_USER, DB_PASS);
		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	//クローズするメソッド
	public void close() throws SQLException {
		try {
			if(this.rs != null) {
				this.rs.close();
			}
			if(this.pst != null) {
				this.pst.close();
			}
			if(this.cn != null) {
				this.cn.close();
			}
		} catch(SQLException e) {
			throw e;
		}
	}

}
