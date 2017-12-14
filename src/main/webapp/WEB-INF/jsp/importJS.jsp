<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- 全局都会引用的js -->
<!-- jQuery 3 -->
<script src="${basePath }bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${basePath }bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="${basePath }bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Select2 -->
<script src="${basePath }bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- DataTables -->
<script src="${basePath }bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${basePath }bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${basePath }bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- Morris.js charts -->
<script src="${basePath }bower_components/raphael/raphael.min.js"></script>
<script src="${basePath }bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="${basePath }bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${basePath }plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${basePath }plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="${basePath }bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="${basePath }bower_components/moment/min/moment.min.js"></script>
<script src="${basePath }bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="${basePath }bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script src="${basePath }bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<!-- SlimScroll -->
<script src="${basePath }bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${basePath }plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="${basePath }bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${basePath }bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${basePath }dist/js/adminlte.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${basePath }plugins/iCheck/icheck.min.js"></script>

<!-- 自定义全局js -->
<script>
$(function () {
//渲染结束，显示iframe内容
if($(window.parent.document).find('iframe').length>0){
	$(window.parent.document).find('div#${iframeID}').find('iframe').css('display','inline');
}
})
</script>