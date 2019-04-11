

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import general.GetData;

/**
 * Servlet implementation class InsertToDoJsonServlet
 */
@WebServlet("/InsertToDoJsonServlet")
public class InsertToDoJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertToDoJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//文字化け対策。
		request.setCharacterEncoding("utf-8");

		System.out.println("InsertToDoJsonServletにきました!!");

		//取得。
		String userId = request.getParameter("id");
		String title = request.getParameter("title");
		String time01 = request.getParameter("time01");
		String time02 = request.getParameter("time02");
		String date = request.getParameter("date");
		String memo = request.getParameter("memo");

//		userId = "12345";
//		title = "Insertのサンプルです。";
//		date = "2019-04-20 18:00";
//		memo = "Insert出来ましたか?";

		GetData gd = null;
		Boolean result = false;
		try {
			gd = new GetData();
			result = gd.insertToDoData(userId,title,date,time01,time02,memo);
			gd.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Insert終わったよ!!");

		request.setAttribute("RESULT", result);
		RequestDispatcher rd = request.getRequestDispatcher("inserttodo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
