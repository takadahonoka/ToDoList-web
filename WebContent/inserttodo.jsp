<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Boolean result = (Boolean)request.getAttribute("RESULT");
%>
{
	"result":<%= result %>
}