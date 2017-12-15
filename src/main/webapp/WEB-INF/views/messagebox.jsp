<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String type = request.getParameter("type")==null?"primary":request.getParameter("type");
if(!"success".equals(type)&&!"primary".equals(type)&&!"danger".equals(type)&&!"warning".equals(type)&&!"default".equals(type)){
	type="default";
}
String title = request.getParameter("title")==null?"消息":request.getParameter("title");
%>
<div class="box box-<%=type %> box-solid box-msg" style="display: none;" id="<%=request.getParameter("id")==null?"":request.getParameter("id")%>">
  <div class="box-header with-border">
    <h3 class="box-title"><%=title %></h3>

    <div class="box-tools pull-right">
      <button type="button" class="btn btn-box-tool" onclick="$(this).parents('.box-msg:first').fadeOut()"><i class="fa fa-times"></i></button>
    </div>
    <!-- /.box-tools -->
  </div>
  <!-- /.box-header -->
  <div class="box-body msg">
    
  </div>
  <!-- /.box-body -->
</div>
<!-- /.box -->