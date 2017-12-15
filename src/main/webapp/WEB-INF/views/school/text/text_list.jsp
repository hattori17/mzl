<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../../importCSS.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Content Wrapper. Contains page content -->
  <div class="content content-wrapper">
	
	<jsp:include page="../../messagebox.jsp" flush="true" >
	<jsp:param name="id" value="dangerbox1" />
	<jsp:param name="type" value="danger" />
	<jsp:param name="title" value="错误" />
	</jsp:include>
	
   	<div class="box box-primary">
       <div class="box-header">
       	<div>
        <button type="button" class="btn btn-success">新增</button>
	  	<button type="button" class="btn btn-warning">修改</button>
	  	<button type="button" class="btn btn-danger">删除</button>
	  	</div>
	  	<br>
	  	<div class="row">
          <div class="col-xs-3">
            <select id="module_key_id" class="form-control select2" style="width: 100%;">
               <c:forEach var="list" items="${select_class_list}" varStatus="vs">
               <option value="${list.key_id }">${list.name }</option>
               </c:forEach>
             </select>
          </div>
          <div class="col-xs-3">
            <div class="input-group">
               <div class="input-group-addon">
                 <i class="fa fa-calendar"></i>
               </div>
               <input type="text" class="form-control pull-right" id="insert_time">
             </div>
          </div>
          <div class="col-xs-4">
            <input type="text" id="name_desc" class="form-control" placeholder="搜索：文字标题、文字描述">
          </div>
          <div class="col-xs-2">
            <button type="button" class="btn btn-primary" onclick="search();">查询</button>
          </div>
        </div>
       </div>
       <!-- /.box-header -->
       <div class="box-body">
         <table id="example1" class="table table-bordered table-striped" style="width:100%;">
           <thead>
           <tr>
             <th>key_id</th>
             <!-- <th>desc</th> -->
             <th>insert_time</th>
             <th>keywords</th>
             <th>module_key_id</th>
             <th>module_name</th>
             <th>permit_key_id</th>
             <th>title</th>
             <th>user_name</th>
             <th>author</th>
           </tr>
           </thead>
           
         </table>
       </div>
       <!-- /.box-body -->
     </div>
     <!-- /.box -->
       
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->

<%@include file="../../importJS.jsp"%>
<!-- page script -->
<script>
var table = undefined;
  $(function () {
	  
	  $('.select2').select2()
	  
	  $('#insert_time').datepicker({
		  language:"zh-CN",
		  format:'yyyy-mm-dd',
		  todayBtn:'linked',
		  autoclose: true
	    })
	  
  })
  
  function search(){
	  if(table==undefined){
		  var para = "module_key_id="+$('#module_key_id').val()+"&insert_time="+$('#insert_time').val()+"&name_desc="+$('#name_desc').val();
		  table = $('#example1').DataTable({
		         "ajax" : {
		       		type:"POST",
		      		url:"select_test1.action?"+para,
		      		data: function (data) {
		                 return JSON.stringify(data);
		            },
		      		contentType: 'application/json',
		      		dataType:"json",
		      		error:function(XMLHttpRequest, textStatus, errorThrown){
		      			$('#dangerbox1').find('.msg').html(XMLHttpRequest.responseText)
		      			$('#dangerbox1').fadeIn()
		      		}
	      		 },
		         serverSide : true,//开启服务器模式:启用服务器分页  
		         processing : true,//是否显示处理状态
		         lengthChange:false,
		         searching:false,
		         pagingType:'full_numbers',
		         
		         language: {
		             paginate: {
		            	 first:"首页",
		               last: "尾页",
		               previous:"上页",
		               next:"下页",
		             },
		             loadingRecords:"加载中",
		             processing:"加载中",
		             emptyTable:     "没有数据",
		             info:           "当前 _START_ 到 _END_  共 _TOTAL_ 行",
		             infoEmpty:      "当前 0 到 0  共 0 行",
		           },
		        "columns": [
					{ "data": "key_id" },
		            /* { "data": "desc","orderable": false }, */
		            { "data": "insert_time" },
		            { "data": "keywords" },
		            { "data": "module_key_id" },
		            { "data": "module_name" },
		            { "data": "permit_key_id" },
		            { "data": "title" },
		            { "data": "user_name" },
		            { "data": "author" }
		        ]
		    })
	  }else{
		  table.ajax.reload();
	  }
	  
  }
 
</script>
</body>
</html>
