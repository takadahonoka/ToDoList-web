

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import general.GetData;

/**
 * Servlet implementation class GetCalToDoJsonServlet
 */
@WebServlet("/GetCalToDoJsonServlet")
public class GetCalToDoJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCalToDoJsonServlet() {
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
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = request.getParameter("date");

		String day = "";

		if(!"".equals(year)) {
			if(!"".equals(month) && Integer.parseInt(month) < 10) {
				month = "0" + month;
			}
			if(!"".equals(date) && Integer.parseInt(date) < 10) {
				date = "0" + date;
			}
			day = year+"-"+month+"-"+date;
		}

		GetData gd = null;
		ArrayList<ArrayList<String>> listData = new ArrayList<ArrayList<String>>();
		try {
			gd = new GetData();
			listData = gd.getListCalData(userId, day);
			gd.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("LISTDATA", listData);
		RequestDispatcher rd = request.getRequestDispatcher("getlist.jsp");
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
