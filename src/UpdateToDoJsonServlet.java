

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
 * Servlet implementation class UpdateToDoJsonServlet
 */
@WebServlet("/UpdateToDoJsonServlet")
public class UpdateToDoJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateToDoJsonServlet() {
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

		//取得。
		String userId = request.getParameter("id");
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String time01 = request.getParameter("time01");
		String time02 = request.getParameter("time02");
		String date = request.getParameter("date");
		String memo = request.getParameter("memo");
		String flag = request.getParameter("flag");

//		userId = "12345";
//		no = "2";

		System.out.println("UpdateToDoJsonServletにきたよ!!");

		GetData gd = null;
		Boolean result = false;
		try {
			gd = new GetData();
			result = gd.updateToDoData(userId, no, title, date, time01, time02, memo, flag);
			gd.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("RESULT", String.valueOf(result));
		RequestDispatcher rd = request.getRequestDispatcher("updatetodo.jsp");
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
