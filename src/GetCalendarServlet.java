

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import general.GetCalendar;
import general.GetData;

/**
 * Servlet implementation class GetCalendarServlet
 */
@WebServlet("/GetCalendarServlet")
public class GetCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCalendarServlet() {
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

		GetCalendar gc = null;
		if(!"".equals(year) && !"".equals(month)) {
			gc = new GetCalendar(Integer.parseInt(year),Integer.parseInt(month),1);
		} else {
			gc = new GetCalendar(0,0,0);
			year = gc.getYear();
			month = gc.getMonth();
		}

		GetData gd = null;
		ArrayList<ArrayList<String>> listData = new ArrayList<ArrayList<String>>();
		try {
			gd = new GetData();
			listData = gd.getCalListData(userId, year, month, gc.setDateList());
			gd.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("カレンダーゲットしたよ!!");

		request.setAttribute("YEAR", gc.getYear());
		request.setAttribute("MONTH", gc.getMonth());
		request.setAttribute("NEXT", gc.getNextDate());
		request.setAttribute("LAST", gc.getLastDate());
		request.setAttribute("LISTDATA", listData);
		RequestDispatcher rd = request.getRequestDispatcher("getcal.jsp");
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
