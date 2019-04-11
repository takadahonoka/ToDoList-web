<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	ArrayList<ArrayList<String>> listData = (ArrayList<ArrayList<String>>)request.getAttribute("LISTDATA");
%>
{
	"todoList":[
		<% for(int i=0; i<listData.size(); i++){ %>
		{
			"no":"<%= listData.get(i).get(0) %>",
			"title":"<%= listData.get(i).get(1) %>",
			"date":"<%= listData.get(i).get(2) %>",
			"memo":"<%= listData.get(i).get(3) %>",
 			"flag":"<%= listData.get(i).get(4) %>"
		}<% if(i != (listData.size()-1)){ %>,<% } %>
		<% } %>
	]
}