<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
    
    
<%@include file="../includes/header.jsp" %>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                            href="https://datatables.net">official DataTables documentation</a>.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Board List Page</h6>
                            <button id='regBtn' type="button" class="btn btn-primary">Register New Board</button>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>BNO</th>
                                            <th>Title</th>
                                            <th>Writer</th>
                                            <th>Reg Date</th>
                                            <th>Update Date</th>
                                        </tr>
                                    </thead>
                                   
									<c:forEach items="${list }" var="board">
										<tr>
											<td><c:out value="${board.bno}" /></td>
											<td><a href='/board/get?bno=<c:out value="${board.bno}"/>'><c:out value="${board.title}" /> </a></td>
											<td><c:out value="${board.writer}" /></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" /></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></td>

										</tr>
									</c:forEach>
									
                                </table>
                                
                                <!-- 모달창 -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria labelledby="myModalLabel" aria-hiddem="true">
	                                <div class="modal-dialog">
	                                	<div class="modal-content">
	                                		<div class="modal-header">
	                                			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                                			<h4 class="modal-title" id="myModalLabel">Modal title</h4>
	                                		</div>
	                                		<div class="modal-body">처리가 완료되었습니다</div>
	                                		<div class="modal-footer">
		                                		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                                		</div>
	                                		
	                                	</div> <!-- modal-content end -->
	                                
	                                
	                                </div><!-- modal-dialog end -->

                              </div> <!-- 모달창 끝 -->
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
            
            <script type="text/javascript">
            $(document).ready(function(){
            	var result = '<c:out value="${result}" />';
            	
            	checkModal(result);
            	
            	function checkModal(result){
            		if(result === ''){
            			return;
            		}
            		if (parseInt(result) > 0){
            			$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다");
            		}
            		$("#myModal").modal("show");
            	}

            	
            	$("#regBtn").on("click", function(){
            		self.location = "/board/register";
            	});
            });
            </script>

       <%@include file="../includes/footer.jsp"%>