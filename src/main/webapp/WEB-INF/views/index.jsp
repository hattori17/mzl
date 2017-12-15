<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="importCSS.jsp"%>
  <style type="text/css">
  .nav.nav-tabs li a button{
  padding:0px;
  margin:0px;
  border:0px;
  }
  </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <%@include file="header.jsp" %>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <%@include file="sidebar.jsp" %>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" id="content-wrapper">
  	<section class="content-header">
  		<ul class="nav nav-tabs">
         <li class="active"><a href="#desktop" data-toggle="tab">桌面</a></li>
       </ul>
  	</section>
    <section class="content">
    	<div class="tab-content">
        <%@include file="desktop.jsp" %> 
        </div>
    </section>
    
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.4.0
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
    reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <%@include file="control_sidebar.jsp" %>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<%@include file="importJS.jsp"%>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${basePath }dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${basePath }dist/js/demo.js"></script>
<script type="text/javascript">
$(function(){
	resetIframe('');
	iframeMinHeight = $('#content-wrapper').height()
})

var iframeMinHeight = 0 ;
function resetIframe(iframeID){
	if(iframeID==''){
		$('iframe').css('display',"none");
		$('iframe').attr('scrolling',"auto").attr('frameborder',"0")
	}else{
		iframe = 'div#'+iframeID+' iframe'
		$(iframe).css('display',"none");
		$(iframe).attr('scrolling',"auto").attr('frameborder',"0")
		$(iframe).css('width','100%').css('height','100%').css('overflow',"hidden").css('min-height',iframeMinHeight);
	}
}
function openTab(tabname,tabsrc,that){
	//是否存在
	var exist = false ;
	$.each($('#content-wrapper').find('section.content-header ul li'),function(){
		if($(this).text()==tabname){
			exist = true ;
			return false
		}
	})
	if(exist){
		$.each($('#content-wrapper').find('section.content-header ul li'),function(){
			if($(this).text()==tabname){
				$(this).find('a').click();
				return false
			}
		})
	}else{
		Math.random()*999999
		var num = Math.random()*999999;
		num = parseInt(num, 10);
		var tabnum = "tab"+num;
		
		var li = "<li><a href=\"#"+tabnum+"\" data-toggle=\"tab\"><i class=\"fa fa-circle-o\"></i>"+tabname+"<button type=\"button\" style=\"margin-left:10px;\" onclick=\"closeTab(this,'"+tabnum+"');\" class=\"btn btn-box-tool\" data-widget=\"remove\"><i class=\"fa fa-times\"></i></button></a></li>";
		var pane = "<div class=\"tab-pane\" id=\""+tabnum+"\"><iframe src=\""+tabsrc+"?iframeID="+tabnum+"\" ></iframe></div>";
		
		$('#content-wrapper').find('section.content-header ul').append(li);
		$('#content-wrapper').find('section.content div.tab-content:first').append(pane);
		
		resetIframe(tabnum);
		
		$('#content-wrapper').find('section.content-header ul li:last a').click();
	}
	//active
	//$('li').removeClass('active');
}
function closeTab(that,id){
	var li = $(that).parent().parent();
	if(li.hasClass('active')){
		if(li.next('li').length>0){
			li.next('li').addClass('active');
			var tab_pane_id = li.next('li').find('a').attr('href').replace('#','');
			$('#'+tab_pane_id).addClass('active');
		}else{
			li.prev('li').addClass('active');
			var tab_pane_id = li.prev('li').find('a').attr('href').replace('#','');
			$('#'+tab_pane_id).addClass('active');
		}
	}
	li.remove();
	$('#'+id).remove();
}
</script>
</body>
</html>
