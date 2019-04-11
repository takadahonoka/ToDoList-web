<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ToDoリスト</title>
<link rel="stylesheet" href="bootstrap/bootstrap-4.3.1-dist/css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>

<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'list')" id="defaultOpen">リスト検索</button>
  <button class="tablinks" onclick="openCity(event, 'cal')">カレンダー</button>
</div>

<div id="list" class="tabcontent">

	<form class="form-inline" id="searchs">
		<input type="text" id="contents" class="form-control">
		<input type="button" id="search" class="btn btn-primary" value="検索">
	</form>

	<div class="scroll01" id="top">
		<table id="todo-list01"  class="table table-striped table-hover">
			<tbody></tbody>
		</table>
	</div><!-- scroll01 -->

	<input type="button" id="new" class="btn btn-primary btn-block" value="新規">

</div><!-- list -->

<div id="cal" class="tabcontent">

	<div id="cal-button">
    		<div id="cal-title"><h3></h3></div>
    </div><!-- cal-button -->

	<table class="table table-bordered" id="cal-list">
		<thead>
 			<tr id="text-center">
				<th id="gray">月</th>
				<th id="gray">火</th>
				<th id="gray">水</th>
				<th id="gray">木</th>
				<th id="gray">金</th>
				<th id="bule">土</th>
				<th id="red">日</th>
			</tr>
		</thead>
		<tbody id="text-center"></tbody>
	</table>

	<div class="scroll02">
		<table id="todo-list02"  class="table table-striped table-hover">
			<tbody>
			</tbody>
		</table>
	</div><!-- scroll02 -->

</div><!-- cal -->

<div id="layer"></div>
<div id="popup">
	<div id="popup-contents">
		<div id="delete"></div><br>
		<label>タイトル:</label>
		<input type="text" id="title" class="form-control" value="">
		<form id="flags">
			<label class="btn btn-primary"><input type="radio" name="flag" id="flag" class="btn btn-info" value="0">未</label>
			<label class="btn btn-primary"><input type="radio" name="flag" id="flag" class="btn btn-info" value="1">済</label>
		</form>
		<label>日付:</label>
		<input type="date" id="date" class="form-control" value="">
		<label>時間:</label>
		<form class="form-inline">
			<select id="time01" class="form-control">
				<% for(int i=0; i<24; i++) { %>
					<option value="<%= i %>"><%= i %></option>
				<% } %>
			</select>時
			<select id="time02" class="form-control">
				<% for(int i=0; i<60; i++) { %>
					<option value="<%= i %>"><%= i %></option>
				<% } %>
			</select>分まで
		</form>
		<label>メモ:</label>
		<textarea id="memo" class="form-control" cols="50" rows="5"></textarea>
		<input type="button" id="cancel" class="btn btn-warning" value="キャンセル">
		<input type="button" id="registration" class="btn btn-primary" value="登録">
		<div id="update"></div>
	</div><!-- popup-contents -->
</div><!-- popup -->

<!-- javascriptに接続  -->
<script type="text/javascript" src="javascript/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="javascript/tab.js"></script>
<script type="text/javascript">

//ToDoリストクリック時の処理
function clickToDo(content) {
	$('#delete').show();
	$('#flags').show();
	$('#update').show();
	$('#registration').hide();
	$.ajax({
		url: '/ToDoList/GetOneDataJsonServlet',
		type: 'GET',
		dataType: 'json',
		data: {
			'id' : "12345",
			'no' : content,
		},
		timeout: 5000,
	})
	.done(function(data) {
		//タイトル。
		document.getElementById("title").value = data.title ;
		//済/未。
		var flagsform = document.getElementById( "flags" ) ;
		var flags = flagsform.flag ;
		flags[data.flag].checked = true ;
		//日付。
		document.getElementById("date").value = data.date ;
		//時間(01:時、02:分)
		var time01 = document.getElementById("time01");
		time01.selectedIndex = data.time01;
		var time02 = document.getElementById("time02");
		time02.selectedIndex = data.time02;
		//メモ。
		document.getElementById("memo").value = data.memo ;
		//変更ボタン。
		$("#update").html("");
		$('<input type="button" id="update" class="btn btn-primary" onclick="clickChange('+content+')" value="変更">').appendTo('#update');
		//変更ボタン。
		$("#delete").html("");
		$('<input type="button" id="delete" class="btn btn-danger" style="width:100px;float:right;" onclick="clickDelete('+content+')" value="削除">').appendTo('#delete');
	})
	.fail(function() {
		// 通信失敗時の処理を記述
	});
	$('#popup, #layer').show();
}

//カレンダークリック時の処理
function clickCal(text) {
	getToDo02(text);
}

//ToDoリスト01取得。
function getToDo01(text) {
	$.ajax({
		url: '/ToDoList/GetListJsonServlet',
		type: 'GET',
		dataType: 'json',
		data: {
			'id' : "12345",
			'contents' : text,
		},
		timeout: 5000,
	})
	.done(function(data) {
 		$("#todo-list01 tbody").html("");
		for(var index in data.todoList){
			$('<tr onclick="clickToDo('+data.todoList[index].no+')">'+
				'<td>'+data.todoList[index].title+'</td>'+
				'<td>'+data.todoList[index].date+'</td>'+
				'</tr>').appendTo('#todo-list01 tbody');
		}
	})
	.fail(function() {
		// 通信失敗時の処理を記述
	});
}

//カレンダー下のToDoリスト02取得。
function getToDo02(year,month,date) {
	$.ajax({
		url: '/ToDoList/GetCalToDoJsonServlet',
		type: 'GET',
		dataType: 'json',
		data: {
			'id' : "12345",
			'year' : year,
			'month' : month,
			'date' : date,
		},
		timeout: 5000,
	})
	.done(function(data) {
		$("#todo-list02 tbody").html("");
		for(var index in data.todoList){
			$('<tr onclick="clickToDo('+data.todoList[index].no+')">'+
				'<td>'+data.todoList[index].title+'</td>'+
				'<td>'+data.todoList[index].date+'</td>'+
				'</tr>').appendTo('#todo-list02 tbody');
		}
	})
	.fail(function() {
		// 通信失敗時の処理を記述
	});
}

//カレンダーリスト取得。
function getCal(year,month) {
	$.ajax({
		url: '/ToDoList/GetCalendarServlet',
		type: 'GET',
		dataType: 'json',
		data: {
			'id' : "12345",
			'year' : year,
			'month' : month,
		},
		timeout: 5000,
	})
	.done(function(data) {
		$("#cal-title").html("");
		$('<input type="button" class="btn btn-info" id="button-left" value="'+data.lastmonth+'月" onclick="getCal('+data.lastyear+','+data.lastmonth+')"><h3>'+data.year+'年'+data.month+'月</h3><input type="button" class="btn btn-info" id="button-right" value="'+data.nextmonth+'月" onclick="getCal('+data.nextyear+','+data.nextmonth+')">').appendTo('#cal-title');
		$("#cal-list tbody").html("");
		var html = '<tr>';
		var count = 0;
		for(var index in data.calList){
			var date = "";
			date += data.calList[index].year + '-' + data.calList[index].month + "-" +data.calList[index].date;
			html +='<td id="'+data.calList[index].color+'" onclick="getToDo02('+data.calList[index].year+','+data.calList[index].month+','+data.calList[index].date+')">'+data.calList[index].date+'</td>';
			if(((count+1)%7) == 0 && count != 0){
				html +='</tr><tr>';
			}
			count = count + 1;
		}
		$(html).appendTo('#cal-list tbody');
	})
	.fail(function() {
		// 通信失敗時の処理を記述
	});
}

//変更(#update)ボタンクリック時の処理
function clickChange(no) {

	var flagsform = document.getElementById( "flags" ) ;
	var flags = flagsform.flag ;
	var flag = flags.value;

	// Ajax通信を開始
	$.ajax({
		url: '/ToDoList/UpdateToDoJsonServlet',
		type: 'POST',
		dataType: 'json',
		data: {
			'id' : '12345',
			'no' : no,
			'title' : $('#title').val(),
			'time01' : $('#time01').val(),
			'time02' : $('#time02').val(),
			'date' : $('#date').val(),
			'memo' : $('#memo').val(),
			'flag' : flag,
		},
		timeout: 5000,
	})
	.done(function(data) {
		// 通信成功時の処理を記述
		getToDo01("");
		getToDo02("","","");
		getCal("","");
	})
	.fail(function() {
		// 通信失敗時の処理を記述
	});
	$('#popup, #layer').hide();
}

//削除(#delete)ボタンクリック時の処理
function clickDelete(no) {

	$.ajax({
		url: '/ToDoList/DeleteToDoJsonServlet',
		type: 'POST',
		dataType: 'json',
		data: {
			'id' : '12345',
			'no' : no,
		},
		timeout: 5000,
	})
	.done(function(data) {
		// 通信成功時の処理を記述
		getToDo01("");
		getToDo02("","","");
	})
	.fail(function() {
		// 通信失敗時の処理を記述
	});
	$('#popup, #layer').hide();
}

$(function() {

	// ToDoリスト取得。
	getToDo01("");
	getToDo02("","","");
	getCal("","");

	// 新規(#new)ボタンクリック時の処理
	$('#new').click(function(e) {
		$('#delete').hide();
		$('#update').hide();
		$('#flags').hide();
		$('#registration').show();
		$('#popup, #layer').show();
	});

	// 登録(#registration)ボタンクリック時の処理
	$('#registration').click(function(e) {

		$.ajax({
			url: '/ToDoList/InsertToDoJsonServlet',
			type: 'POST',
			dataType: 'json',
			data: {
				'id' : '12345',
				'title' : $('#title').val(),
				'time01' : $('#time01').val(),
				'time02' : $('#time02').val(),
				'date' : $('#date').val(),
				'memo' : $('#memo').val(),
			},
			timeout: 5000,
		})
		.done(function(data) {
			// 通信成功時の処理を記述
			getToDo01($('#contents').val());
			//タイトル。
			document.getElementById("title").value = "" ;
			//日付。
			document.getElementById("date").value = "" ;
			//時間(01:時、02:分)
			var time01 = document.getElementById("time01");
			time01.selectedIndex = "0";
			var time02 = document.getElementById("time02");
			time02.selectedIndex = "0";
			//メモ。
			document.getElementById("memo").value = "" ;
		})
		.fail(function() {
			// 通信失敗時の処理を記述
		});
		$('#popup, #layer').hide();
	});

	// キャンセル(#cancel)ボタンクリック時の処理
	$('#cancel, #layer').click(function(e) {
		$('#popup, #layer').hide();

		//タイトル。
		document.getElementById("title").value = "" ;
		//済/未。
		var flagsform = document.getElementById( "flags" ) ;
		var flags = flagsform.flag ;
		flags[0].checked = true ;
		//日付。
		document.getElementById("date").value = "" ;
		//時間(01:時、02:分)
		var time01 = document.getElementById("time01");
		time01.selectedIndex = "0";
		var time02 = document.getElementById("time02");
		time02.selectedIndex = "0";
		//メモ。
		document.getElementById("memo").value = "" ;
	});

	// 検索(#search)ボタンクリック時の処理。
	$('#search').click(function(e) {
		getToDo01($('#contents').val());
	});

});
</script>
<script src="bootstrap/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>

</body>
</html>