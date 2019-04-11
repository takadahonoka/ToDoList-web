<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	ArrayList<String> listData = (ArrayList<String>)request.getAttribute("ONEDATA");
%>
{
	"title": "<%= listData.get(0) %>",
	"flag": "<%= listData.get(1) %>",
	"time01": "<%= listData.get(2) %>",
	"time02": "<%= listData.get(3) %>",
	"date": "<%= listData.get(4) %>",
	"memo": "<%= listData.get(5) %>"
}