<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
     <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
    
    <script type="text/javascript">
    $(document).ready(function(){
    	
    	//jsonview 방식 ajax (전체조회)
    	$('#ajaxBtn').click(function(){
    		getList()
    	});
    	
    	//검색
    	$('#searchBtn').click(function(){
    		$.ajax({
    			type : "get",
    			url : "emp/"+$('#search').val(),
    			success : function(data){
    				console.log(data);
    				$('#exp').empty();
    				createTable({"emp" : data}, "search");
    			}
    			
    		})
    		
    	});
    	

        //삭제
        $(document).on("click", ".delete", function(element){
        	console.log($(element.target).attr("value2"))
        	$.ajax({
        		type : "delete",
        		url : "emp/" + $(element.target).attr("value2"),
        		success : function(data){
        			getList();
        		}
        	}) 
        });

    })
    
    //수정 폼
    function empupdate(me){
    	var tr=$(me).closest('tr')
    	var datas = tr.children().html(); //empno
    	tr.empty(); // table 을 비우고
    	$.ajax({
    		type : "get",
    		url : "emp/" + datas,
    		success : function(data){
    			console.log(data);
    			var td = "<td><input type='text' value='"+data.empno +"' readonly></td>";
				td +="<td><input type='text' value='"+data.ename +"'></td>";
				td +="<td><input type='text' value='"+data.sal +"'></td>";
				td +="<td colspan='2'><input type='button'onclick='empupdateconfirm(this)' value='완료' value2="+data.empno+" /></td>";
				
				console.log(td)
				$(tr).append(td); 
    		}  		
    	})    	
    }
    
    function empupdateconfirm(me){
    	empupdateok(me);
    }
    
    //수정 처리
    function empupdateok(me){
    	var tr = $(me).closest('tr');
		var data = {empno:tr.find("td:eq(0)").children().val(),
					ename:tr.find("td:eq(1)").children().val(),
					sal:tr.find("td:eq(2)").children().val(),
				   };
		$.ajax({
			type : "put",
			url:"emp",
			data:JSON.stringify(data),
			contentType:"application/json",
			success : function(data){  
				getList();
			} 
		})
    	
    }
    
    
    function getList(){
    	$.ajax({
			type : "get",
			url : "emp",
			success : function(data){
				console.log(data);
				$('#exp').empty();
				$('#exp').append('<fieldset><legend>jsonview 방식</legend><p>View 객체가 Json 으로 파싱되어 넘어온다 </p></fieldset>');
				createTable(data, "jsonview");
			}
			
		})
    	
    }
    //JSON 전용 table 생성
    function createTable(data, way){
    	$('#menuView').empty();
    	var opr="<table id='fresh-table' class='table'><tr>"+way+"</tr><thead><tr>"+
	    "<th>EMPNO</th>"+
    	"<th>ENAME</th>"+
    	"<th>SAL</th>"+
    	"<th>EDIT</th><th>DELETE</th></tr></thead><tbody>";
    	$.each(data, function(index, emp){
    		opr += "<tr><td>" + emp.empno + 
    		"</td><td>"+emp.ename+
			"</td><td>"+emp.sal+
			"</td><td><input type='button' onclick='empupdate(this)' value='수정' class ='update'  value2="+emp.empno+
			"></td><td><input type='button' value='삭제' class ='delete' value2="+emp.empno+"></td></tr>";
    		
    	});
    	opr+="<tr><td colspan='10'><input type='button' onclick='createinput(this)' value='추가'></td></tr></tbody></table>";
		$('#menuView').append(opr);
    }

    //등록 폼
    function createinput(me){
    	var tr = $(me).closest('tr');
    	tr.empty();
    	var td = "<td><input type='text'></td>";
		td +="<td><input type='text'></td>";
		td +="<td><input type='text'></td>";
		td +="<td><input type='button'onclick='empinsert(this)' value='완료'/></td>";
		td +="<td><input type='button'onclick='cancel(this)' value='취소'/></td>";
		$(tr).append(td);  
    	
    }
    //취소 버튼
    function cancel(me){
    	var tr = $(me).closest('tr');
		tr.empty();
		tr.append("<td colspan='10'><input type='button' onclick='createinput(this)' value='추가'></td>");
    	
    }
    
    //등록(삽입) 처리
    function empinsert(me){
    	var tr = $(me).closest('tr');
    	var data = {empno:tr.find("td:eq(0)").children().val(),
				ename:tr.find("td:eq(1)").children().val(),
				sal:tr.find("td:eq(2)").children().val(),
			   };
    	$.ajax({
    		type : "post",
    		url: "emp",
    		data:JSON.stringify(data),
			contentType:"application/json",
    		success : function(data){
    			getList();		
    		}
    	})
    }

    
    
    </script>
    
    <style>
@import url('https://fonts.googleapis.com/css2?family=Heebo:wght@500&display=swap');
@import url("https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap");
body {
   padding: 1.5em;
   background: #FFF0F5;
}

Button {
    margin: 5px;
}
.w-btn {
margin: 5px;
    width:10%;
	height:6%;
	font-family: 'Heebo', sans-serif;
   text-align: center;
    position: relative;
    border: none;
    display: inline-block;
    padding: 5px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 3px 3px rgba(0, 0, 0, 0.1);
    text-decoration: none;
    font-size:10px;
    font-weight: 500;
    transition: 0.25s;
}

.w-btn-gra1 {
    background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
    color: white;
}

table {
font-family: 'Heebo', sans-serif;
   border: 1px #a39485 solid;
   font-size: .9em;
   box-shadow: 0 2px 5px rgba(0, 0, 0, .25);
   width: 100%;
   border-collapse: collapse;
   border-radius: 5px;
   overflow: hidden;
}

th {
   text-align: center;
}

thead {
   font-weight: bold;
   color: #fff;
   background: #FFAAAF;
}

td, th {
   padding: 1em .5em;
   vertical-align: middle;
}

td {
	text-align:center;
   border-bottom: 1px solid rgba(0, 0, 0, .1);
   background: #fff;
}

input[type="text"]{
	width: 60%;
}


a {
   color: #73685d;
}

@media all and (max-width: 768px) {
   table, thead, tbody, th, td, tr {
      display: block;
      text-align:center;
   }
   th {
      text-align: center;
   }
   table {
      position: relative;
      padding-bottom: 0;
      border: none;
      box-shadow: 0 0 10px rgba(0, 0, 0, .2);
   }
   thead {
      float: left;
      white-space: nowrap;
   }
   tbody {
      overflow-x: auto;
      overflow-y: hidden;
      position: relative;
      white-space: nowrap;
   }
   tr {
      text-align: center;
      display: inline-block;
      vertical-align: top;
   }
   th {
      border-bottom: 1px solid #a39485;
   }
   td {
   
      border-bottom: 1px solid #e5e5e5;
   }
}
</style>
</head>
 <body>
 <!-- <a href="view.ajax">view</a>  -->
<hr>
<div class="wrapper">
    <div class="fresh-table toolbar-color-azure full-screen-table" style="align-content: center;">
<div class="container-fluid">
<div class="row">
<div class="col-sm-6">
<fieldset>
	<legend>JSON</legend>
	<!-- 
	<form class="d-none d-md-flex ms-4" action="searchWord.do" name="searchWord" id="searchWord" method="post">
	<input class="form-control border-0" type="search" name="searchWord" id="searchWord" placeholder="Search">
	<input class="btn btn-outline-primary m-2" type="submit" value="Search">
	</form>
	
	 -->
	
	 <div class="Button" name="searchWord">
		<input class="search" type="text" name="searchWord" id="search" placeholder="Search">
		<input class="w-btn w-btn-gra1"  type="submit" value="Search" id="searchBtn">
	</div>

	<div class="Button">
	<input class="w-btn w-btn-gra1" type="button" value="Emp List" id="ajaxBtn">
	</div>
	<!-- 
	<input type="button" value="Response" id="responseBtn">
	<input type="button" value="ObjectMapper" id="objMapperBtn">
	<input type="button" value="ResponseBody" id="responseBodyBtn">
	<input type="button" value="JsonView" id="ajaxBtn">
	<input type="button" value="RestController" id="restconBtn">
	 -->
</fieldset>
</div>

</div>

	<hr>
	<div id="exp"></div>
		<span id="menuView"></span>
	</div>
	</div>
</div>
	
</body>
</html>