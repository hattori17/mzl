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
  <%@include file="../importCSS.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">


  <!-- Content Wrapper. Contains page content -->
  <div class="content content-wrapper">

	<div class="row">
      <div class="col-xs-12">
      
       <div class="box box-warning collapsed-box">
         <div class="box-header with-border">
           <div class="col-xs-9">
           <input type="text" id="search_key" class="form-control col-xs-9" placeholder="输入搜索词">
           </div>
           <div class="col-xs-1">
           <button type="button" class="btn btn-primary" onclick="receive();">查询</button>
           </div>
           <div class="col-xs-2">
           <button type="button" class="btn btn-success" data-widget="collapse"><i class="fa fa-plus"></i>高级选项</button>
           </div>
           <!-- /.box-tools -->
         </div>
         <!-- /.box-header -->
         <div class="box-body">
           <div class="col-xs-3">
	          <select class="form-control select2" id="tsn" style="width: 100%;">
	             <option value="0">全部时间</option>
	             <option value="1">一天内</option>
	             <option value="2">一周内</option>
	             <option value="3">一月内</option>
	             <option value="4">一年内</option>
	             <option value="5">自定义</option>
	           </select>
	        </div>
	        <div class="col-xs-3 datepicker-col hide">
	          <div class="input-group">
	             <div class="input-group-addon">
	               <i class="fa fa-calendar"></i>
	             </div>
	             <input type="text" id="ft" class="form-control pull-right datepicker" placeholder="起始日期">
	           </div>
	        </div>
	        <div class="col-xs-3 datepicker-col hide">
	          <div class="input-group">
	             <div class="input-group-addon">
	               <i class="fa fa-calendar"></i>
	             </div>
	             <input type="text" id="et" class="form-control pull-right datepicker" placeholder="结束日期">
	           </div>
	        </div>
	        <div class="col-xs-3">
	        	<input type="text" id="usip" class="form-control col-xs-12" placeholder="在指定公众号中搜索">
	        </div>
         </div>
         <!-- /.box-body -->
       </div>
      </div>
    </div>
       
   	<div class="box box-primary">
       <div class="box-header">
	  	<button type="button" class="btn btn-danger">添加到文章库</button>
       </div>
       <!-- /.box-header -->
       <div class="box-body">
         <table id="example1" class="table table-bordered table-striped" style="width:100%;">
           <thead>
           <tr>
           	 <th><input type="checkbox" class="checkall" /></th>
           	 <th>标题</th>
           	 <th>公众号</th>
           	 <th>发布日期</th>
           	 <th>在线查看</th>
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

<%@include file="../importJS.jsp"%>
<!-- page script -->
<script>
  $(function () {
	  $('#tsn').change(function(){
		  if($(this).val()=="5"){
			  $('.datepicker-col').removeClass('hide');
		  }else{
			  $('.datepicker').val('');
			  $('.datepicker-col').addClass('hide');
		  }
	  })
	  
	  $('.select2').select2()
	  
	  $('.datepicker').datepicker({
		  language:"zh-CN",
		  format:'yyyy-mm-dd',
		  todayBtn:'linked',
		  autoclose: true
	    })
	  

  })
 
  var table =undefined ;
  
  function receive(){
	  var search_key =$('#search_key').val();
	  var tsn = $('#tsn').val();
	  var ft=$('#ft').val();
	  var et = $('#et').val();
	  var usip=$('#usip').val();
	  if(search_key==""){
		  return ;
	  }
	  if(tsn=="5"&&(ft==""||et=="")){
		  return ;
	  }else{
		  ft="";
		  et="";
	  }
	  var para = {'search_key':search_key,'tsn':tsn,'ft':ft,'et':et,'usip':usip};
	  if(typeof(table)!='undefined'){
		  table.destroy();
	      $('#example1').empty();
	  }
	  table = $('#example1').DataTable({
		    "ajax" : {  
	             url : "receive.action",  
	             type: "POST",
	             data: function (data) {
	                 return JSON.stringify($.extend(data,para));
	             },
	             dataType: "json",
	             processData: false,
	             contentType: 'application/json;charset=UTF-8'
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
	             emptyTable: "无可用数据"
	           },
	        "columns": [
	        	{
	                 "className": "text-center",
	                 "data": "title",
	                 "render": function (data, type, full, meta) {
	                     return '<input type="checkbox" class="flat-red">';
	                 },
	                 "orderable": false
	            },
	        	{ "data": "title","orderable": false },
	        	{ "data": "user","orderable": false },
	        	{ "data": "date","orderable": false },
				{ "data": "url","orderable": false,"visible": true },
	        ],
	        "initComplete": function (settings, data) {
	        	$('input[type="checkbox"].flat-red').iCheck({
	      	      checkboxClass: 'icheckbox_flat-green',
	      	      radioClass   : 'iradio_flat-green'
	      	    })
	          }
	  })
	  
	  /* }else{
		  //table.ajax.reload();
	  } */
  }
</script>
</body>
</html>
