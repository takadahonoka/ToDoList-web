package general;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetData extends DataAccess {

	private String strSql = "";

	/**
	 * コンストラクタ。
	 *
	 * @throws Exception 全ての例外。
	 */
	public GetData() throws Exception, SQLException {
		super();
	}

	public void DataAccessClose() throws Exception, SQLException {
		super.close();
	}

	/**
	 * ユーザーのデータを取得。
	 * @param id
	 * @return ArrayList<String>
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<String> getUserData(String id) throws Exception, SQLException {
		ArrayList<String> result = new ArrayList<String>();
		try {
			this.strSql = "SELECT * FROM user WHERE id = '" + id + "';";
			System.out.println(strSql);
			this.pst = this.cn.prepareStatement(this.strSql);
			this.rs = this.pst.executeQuery(this.strSql);
			if(rs.next()) {
				result.add("true");
				result.add(rs.getString("id"));
				result.add(rs.getString("name"));
			}else {
				result.add("false");
				result.add("");
				result.add("");
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * ToDoのデータを1件取得。
	 * @param id
	 * @param no
	 * @return ArrayList<String>
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<String> getToDoOneData(String id, String no) throws Exception, SQLException {
		ArrayList<String> result = new ArrayList<String>();
		try {
			this.strSql = "SELECT title, flag, DATE_FORMAT(date,'%k') AS time01, DATE_FORMAT(date,'%i') AS time02, DATE_FORMAT(date,'%Y-%m-%d') AS date, memo" +
					" FROM user u INNER JOIN list l ON u.id = l.user_id" +
					" WHERE u.id = '" + id + "' AND no = '"+ no +"';";
			System.out.println(strSql);
			this.pst = this.cn.prepareStatement(this.strSql);
			this.rs = this.pst.executeQuery(this.strSql);
			if(rs.next()) {
				result.add(rs.getString("title"));
				result.add(rs.getString("flag"));
				result.add(rs.getString("time01"));
				result.add(rs.getString("time02"));
				result.add(rs.getString("date"));
				result.add(rs.getString("memo"));
			}else {
				result.add("");
				result.add("");
				result.add("");
				result.add("");
				result.add("");
				result.add("");
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * リストデータを取得。
	 * @param id
	 * @return ArrayList<ArrayList<String>>
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> getListData(String id, String contents) throws Exception, SQLException {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		try {
			if("".equals(contents)) {
				this.strSql = "SELECT no, title, DATE_FORMAT(date,'%Y年%m月%d日%k時%i分') AS date, memo, flag FROM user u INNER JOIN list l ON u.id = l.user_id WHERE u.id = '" + id + "' AND delete_flag = 0 ORDER BY date;";
			} else {
				this.strSql = "SELECT no, title, DATE_FORMAT(date,'%Y年%m月%d日%k時%i分') AS date, memo, flag FROM user u INNER JOIN list l ON u.id = l.user_id WHERE u.id = '" + id + "' AND delete_flag = 0 AND ((title LIKE '%"+ contents +"%') OR (memo LIKE '%"+ contents +"%')) ORDER BY date;";
			}
			System.out.println(strSql);
			this.pst = this.cn.prepareStatement(this.strSql);
			this.rs = this.pst.executeQuery(this.strSql);
			while(rs.next()) {
				ArrayList<String> data = new ArrayList<String>();
				data.add(rs.getString("no"));
				data.add(rs.getString("title"));
				data.add(rs.getString("date"));
				data.add(rs.getString("memo"));
				data.add(rs.getString("flag"));
				result.add(data);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * カレンダークリック時のリストデータを取得。
	 * @param id
	 * @return ArrayList<ArrayList<String>>
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> getListCalData(String id, String contents) throws Exception, SQLException {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		try {
			this.strSql = "SELECT no, title, DATE_FORMAT(date,'%Y年%m月%d日%k時%i分') AS date, memo, flag FROM user u INNER JOIN list l ON u.id = l.user_id WHERE u.id = '" + id + "' AND delete_flag = 0 AND date LIKE '"+ contents +"%' ORDER BY date;";
			System.out.println(strSql);
			this.pst = this.cn.prepareStatement(this.strSql);
			this.rs = this.pst.executeQuery(this.strSql);
			while(rs.next()) {
				ArrayList<String> data = new ArrayList<String>();
				data.add(rs.getString("no"));
				data.add(rs.getString("title"));
				data.add(rs.getString("date"));
				data.add(rs.getString("memo"));
				data.add(rs.getString("flag"));
				result.add(data);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * カレンダーデータを取得。
	 * @param id
	 * @return ArrayList<ArrayList<String>>
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> getCalListData(String id, String year, String month, ArrayList<ArrayList<String>> cal) throws Exception, SQLException {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		try {
			for(int i=0; i<cal.size(); i++) {
				String changeColor = "";
				String color = "";
				String changeYear = "";
				String changeMonth = "";
				if("last".equals(cal.get(i).get(1))) {
					changeColor = "lastdo";
					color = "last";
					if(1 == Integer.parseInt(month)) {
						changeYear = String.valueOf(Integer.parseInt(year)-1);
						changeMonth = "12";
					} else {
						changeYear = year;
						changeMonth = String.valueOf(Integer.parseInt(month)-1);
					}
				} else if("now".equals(cal.get(i).get(1))) {
					changeColor = "nowdo";
					color = "now";
					changeYear = year;
					changeMonth = month;
				} else {
					changeColor = "nextdo";
					color = "next";
					if(12 == Integer.parseInt(month)) {
						changeYear = String.valueOf(Integer.parseInt(year)+1);
						changeMonth = "1";
					} else {
						changeYear = year;
						changeMonth = String.valueOf(Integer.parseInt(month)+1);
					}
				}
				if(Integer.parseInt(changeMonth)<10) {
					changeMonth = "0" + changeMonth;
				}
				if(Integer.parseInt(cal.get(i).get(0))<10) {
					ArrayList<String> data = new ArrayList<String>();
					data.add("0"+cal.get(i).get(0));
					data.add(cal.get(i).get(1));
					cal.set(i, data);
				}
				this.strSql = "SELECT * FROM user u INNER JOIN list l ON u.id = l.user_id WHERE u.id = '" + id + "' AND l.date LIKE '"+ changeYear +"-"+ changeMonth +"-"+ cal.get(i).get(0) +"%' AND delete_flag = 0 ORDER BY date;";
				//System.out.println(changeYear+"年"+changeMonth+"月"+cal.get(i).get(0)+"日: "+strSql);
				this.pst = this.cn.prepareStatement(this.strSql);
				this.rs = this.pst.executeQuery(this.strSql);
				if(rs.next()) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(cal.get(i).get(0));
					data.add(changeColor);
					data.add(changeYear);
					data.add(changeMonth);
					result.add(data);
				}else {
					ArrayList<String> data = new ArrayList<String>();
					data.add(cal.get(i).get(0));
					data.add(color);
					data.add(changeYear);
					data.add(changeMonth);
					result.add(data);
				}
			}
			System.out.println(result.get(0).get(1));
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}


	/**
	 * ToDoの新規登録。
	 * @param id
	 * @param title
	 * @param date
	 * @param memo
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean insertToDoData(String id, String title, String date, String time01, String time02, String memo) throws Exception {

		int no = 0;
		try {

			//連番の現在の最大値を取得。
			this.strSql = "SELECT MAX(no) AS no FROM user u INNER JOIN list l ON u.id = l.user_id WHERE u.id = '" + id + "';";
			System.out.println(strSql);
			this.pst = this.cn.prepareStatement(this.strSql);
			this.rs = this.pst.executeQuery(this.strSql);
			if(rs.next()) {
				no = Integer.parseInt(rs.getString("no"))+1;
			}

			//登録。
			this.strSql = "INSERT INTO todo2019_db.list(" +
					"user_id," +
					"no," +
					"title," +
					"date," +
					"memo," +
					"flag," +
					"delete_flag" +
					") VALUES (?, ?, ?, ?, ?, ?, ?)";
			this.pst = this.cn.prepareStatement(this.strSql);

			this.pst.setString(1, id);
			this.pst.setInt(2, no);
			this.pst.setString(3, title);
			this.pst.setString(4, date+" "+time01+":"+time02);
			this.pst.setString(5, memo);
			this.pst.setString(6, "0");
			this.pst.setString(7, "0");
			this.pst.executeUpdate();

			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 *ToDoの変更。
	 * @param user_id
	 * @param no
	 * @param title
	 * @param date
	 * @param memo
	 * @param flag
	 * @return boolean
	 */
	public boolean updateToDoData(String user_id, String no, String title, String date, String time01, String time02, String memo, String flag) {
		String sql = "UPDATE list SET title = ?, date = ?, memo  = ?, flag  = ?  WHERE user_id = ? AND no = ? ;";
		try {
			System.out.println("UPDATE list SET title = '"+title+"', date = '"+date+" "+time01+":"+time02+"', memo  = '"+memo+"', flag  = '"+flag+"'  WHERE user_id = '"+user_id+"' AND no = '"+no+"' ;");
			this.pst = cn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, date+" "+time01+":"+time02);
			pst.setString(3, memo);
			pst.setString(4, flag);
			pst.setString(5, user_id);
			pst.setString(6, no);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	/**
	 * ToDoの削除。
	 * @param user_id
	 * @param no
	 * @return deleteToDoData
	 */
	public boolean deleteToDoData(String user_id, String no) {
		String sql = "UPDATE list SET delete_flag  = ?  WHERE user_id = ? AND no = ? ;";
		try {
			this.pst = cn.prepareStatement(sql);
			pst.setString(1, "1");
			pst.setString(2, user_id);
			pst.setString(3, no);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}


}
