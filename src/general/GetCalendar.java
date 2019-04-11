package general;

import java.util.ArrayList;
import java.util.Calendar;

public class GetCalendar {

	private Calendar cal = null;
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private ArrayList<ArrayList<String>> dateList = null;

	public GetCalendar() {
		this.cal = Calendar.getInstance();
		this.year = 0;
		this.month = 0;
		this.day = 0;
		this.dateList = new ArrayList<ArrayList<String>>();
	}

	public GetCalendar(int year, int month, int day) {
		this.cal = Calendar.getInstance();
		if(month == 0) {
			this.year = cal.get(Calendar.YEAR);
			this.month = cal.get(Calendar.MONTH);
			this.day = cal.get(Calendar.DATE);
		} else {
			this.year = year;
			this.month = month-1;
			this.day = day;
		}
		this.dateList = new ArrayList<ArrayList<String>>();
	}

	/**
	 * 次の月を求める。
	 * @return String[]
	 */
	public String[] getNextDate() {
		String[] date = new String[2];
		int nextMonth = month + 1;
		if(12 == nextMonth) {
			date[0] = String.valueOf(this.year+1);
			date[1] = "1";
		} else {
			date[0] = String.valueOf(this.year);
			date[1] = String.valueOf(nextMonth+1);
		}
		return date;
	}

	/**
	 * 前の月を求める。
	 * @return String[]
	 */
	public String[] getLastDate() {
		String[] date = new String[2];
		int lastMonth = month + 1;
		if(1 == lastMonth) {
			date[0] = String.valueOf(this.year-1);
			date[1] = "12";
		} else {
			date[0] = String.valueOf(this.year);
			date[1] = String.valueOf(lastMonth-1);
		}
		return date;
	}

	public String getYear() {
		return String.valueOf(this.year);
	}

	public String getMonth() {
		return String.valueOf(this.month+1);
	}

	public ArrayList<ArrayList<String>> setDateList() {

		//今月の1日の曜日を求める。
		cal.set(this.year,this.month,1);
		int firstDate = cal.get(Calendar.DAY_OF_WEEK);
		if(firstDate == 1) {
			firstDate = 7;
		} else {
			firstDate = firstDate - 1;
		}

		//先月の最終日を求める。
		cal.set(this.year,(this.month-1),1);
		int lastMonthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		//今月の最終日を求める。
		cal.set(this.year,this.month,1);
		int maxMonthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		//今月の最終日の曜日を求める。
		cal.set(this.year,this.month,maxMonthDay);
		int lastDate = cal.get(Calendar.DAY_OF_WEEK);
		if(lastDate == 1) {
			lastDate = 7;
		} else {
			lastDate = lastDate - 1;
		}

		//先月の日付を代入。
		if(firstDate != 0) {
			int number = lastMonthDay - firstDate + 2;
			for(int i=0; i<(firstDate-1); i++) {
				ArrayList<String> date = new ArrayList<String>();
				date.add(String.valueOf(number + i));
				date.add("last");
				dateList.add(date);
			}
		}

		//今月の日付を代入。
		int j = 0;
		for(j=0; j<maxMonthDay; j++) {
			ArrayList<String> date = new ArrayList<String>();
			date.add(String.valueOf(j+1));
			date.add("now");
			dateList.add(date);
		}

		//来月の日付を代入。
		if(lastDate != 7) {
			for(int k=0; k<(7-lastDate); k++) {
				ArrayList<String> date = new ArrayList<String>();
				date.add(String.valueOf(k+1));
				date.add("next");
				dateList.add(date);
			}
		}

		//結果確認
//		System.out.println(year+"年"+(month+1)+"月");
//		for(int r=0; r<dateList.size(); r++) {
//			System.out.print(" | "+dateList.get(r).get(0));
//			if(((r+1)%7)==0 && r != 0) {
//				System.out.print("\n");
//			}
//		}
		return dateList;
	}

}
