<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
     <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
    
    <script type="text/javascript">
    $(document).ready(function(){
    	
    	//jsonview 방식 ajax
    	$('#ajaxBtn').click(function(){
    		$.ajax({
    			type : "post",
    			url : "json.ajax",
    			success : function(data){
    				console.log(data);
    				$('#exp').empty();
    				$('#exp').append('<fieldset><legend>jsonview 방식</legend><p>View 객체가 Json 으로 파싱되어 넘어온다 </p></fieldset>');
    				createTable(data.emp, "jsonview");
    			}
    			
    		})
    		
    	});

        //삭제
        $(document).on("click", ".delete", function(){
        	$.ajax({
        		type : "post",
        		url : "delete.ajax",
        		data : {empno : $(this).attr("value2")},
        		success : function(data){
        			createTable(data.emp, "삭제완료");
        		}
        	})
        });

    })
    
    //수정 폼
    function empupdate(me){
    	var tr=$(me).closest('tr')
    	var datas = {empno:tr.children().html()};
    	tr.empty();
    	
    	$.ajax({
    		type : "get",
    		url : "update.ajax",
    		data : datas,
    		success : function(data){
    			var td = "<td><input type='text' value='"+data.emp.empno +"' readonly></td>";
				td +="<td><input type='text' value='"+data.emp.ename +"'></td>";
				td +="<td><input type='text' value='"+data.emp.job +"'></td>";
				td +="<td><input type='text' value='"+data.emp.mgr +"'></td>";
				td +="<td><input type='text' value='"+data.emp.hiredate.substring(0,10) +"' readonly></td>";
				td +="<td><input type='text' value='"+data.emp.sal +"'></td>";
				td +="<td><input type='text' value='"+data.emp.comm +"'></td>";
				td +="<td><input type='text' value='"+data.emp.deptno +"'></td>";
				td +="<td colspan='2'><input type='button'onclick='empupdateconfirm(this)' value='완료' value2="+data.emp.empno+" /></td>";
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
					job:tr.find("td:eq(2)").children().val(),
					mgr:tr.find("td:eq(3)").children().val(),
					hiredate:tr.find("td:eq(4)").children().val(),
					sal:tr.find("td:eq(5)").children().val(),
					comm:tr.find("td:eq(6)").children().val(),
					deptno:tr.find("td:eq(7)").children().val(),
				   };
		$.ajax({
			type : "post",
			url:"update.ajax",
			data:data,
			success : function(data){  
				createTable(data.emp, "수정완료");
			} 
		})
    	
    }
    
    //JSON 전용 table 생성
    function createTable(data, way){
    	$('#menuView').empty();
    	var opr="<table id='fresh-table' class='table'><tr>"+way+"</tr><thead><tr>"+
	    "<th>EMPNO</th>"+
    	"<th>ENAME</th>"+
    	"<th>JOB</th>"+
    	"<th>MGR</th>"+
    	"<th>HIREDATE</th>"+
    	"<th>SAL</th>"+
    	"<th>COMM</th>"+
    	"<th>DEPTNO</th>"+
    	"<th>EDIT</th><th>DELETE</th></tr></thead><tbody>";
    	$.each(data, function(index, emp){
    		opr += "<tr><td>" + emp.empno + 
    		"</td><td>"+emp.ename+
			"</td><td>"+emp.job+
			"</td><td>"+emp.mgr+
			"</td><td>"+emp.hiredate.substring(0,10)+
			"</td><td>"+emp.sal+
			"</td><td>"+emp.comm+
			"</td><td>"+emp.deptno+
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
		td +="<td><input type='text'></td>";
		td +="<td><input type='text'></td>";
		td +="<td><input type='text'></td>";
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
    
    //등록 처리
    function empinsert(me){
    	var tr = $(me).closest('tr');
    	var data = {empno:tr.find("td:eq(0)").children().val(),
				ename:tr.find("td:eq(1)").children().val(),
				job:tr.find("td:eq(2)").children().val(),
				mgr:tr.find("td:eq(3)").children().val(),
				hiredate:tr.find("td:eq(4)").children().val(),
				sal:tr.find("td:eq(5)").children().val(),
				comm:tr.find("td:eq(6)").children().val(),
				deptno:tr.find("td:eq(7)").children().val(),
			   };
    	$.ajax({
    		type : "post",
    		url: "insert.ajax",
    		data:data,
    		success : function(data){
    			createTable(data.emp, "추가");   			
    		}
    	})
    }

    
    
    </script>
    
    <style>
body {
   padding: 1.5em;
   background: #f5f5f5
}

table {
   border: 1px #a39485 solid;
   font-size: .9em;
   box-shadow: 0 2px 5px rgba(0, 0, 0, .25);
   width: 100%;
   border-collapse: collapse;
   border-radius: 5px;
   overflow: hidden;
}

th {
   text-align: left;
}

thead {
   font-weight: bold;
   color: #fff;
   background: #73685d;
}

td, th {
   padding: 1em .5em;
   vertical-align: middle;
}

td {
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
   }
   th {
      text-align: right;
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
 <a href="view.ajax">view</a>
<hr>
<div class="wrapper">
    <div class="fresh-table toolbar-color-azure full-screen-table" style="align-content: center;">
<div class="container-fluid">
<div class="row">
<div class="col-sm-6">
<fieldset>
	<legend>JSON</legend>
	<input type="button" value="Emp List" id="ajaxBtn">
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