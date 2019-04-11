<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	ArrayList<ArrayList<String>> listData = (ArrayList<ArrayList<String>>)request.getAttribute("LISTDATA");
	String year = (String)request.getAttribute("YEAR");
	String month = (String)request.getAttribute("MONTH");
	String[] next = (String[])request.getAttribute("NEXT");
	String[] last = (String[])request.getAttribute("LAST");
%>
{
	"year": <%= year %>,
	"month": <%= month %>,
	"nextyear": <%= next[0] %>,
	"nextmonth": <%= next[1] %>,
	"lastyear": <%= last[0] %>,
	"lastmonth": <%= last[1] %>,
	"calList":[
		<% for(int i=0; i<listData.size(); i++){ %>
		{
			"date":"<%= listData.get(i).get(0) %>",
			"color":"<%= listData.get(i).get(1) %>",
			"year":"<%= listData.get(i).get(2) %>",
			"month":"<%= listData.get(i).get(3) %>"
		}<% if(i != (listData.size()-1)){ %>,<% } %>
		<% } %>
	]
}