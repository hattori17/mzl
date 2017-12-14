package com.mzl.basic;

public class StaticParameters {
	//操作系统判断
	private static OSinfo OSinfo = new OSinfo(); 
	//错误日志目录
	public static String ERROR_LOG_FOLDER = OSinfo.getError_log_folder();
	// sql日志目录
	public static String SQL_LOG_FOLDER = OSinfo.getSql_log_folder();
	//导出文件目录
	public static String EXPORT_FOLDER = OSinfo.getExport_folder();
	//导入文件目录
	public static String INPUT_FOLDER = OSinfo.getInput_folder();
	// ftp文件保存目录
	public static String FTP_FOLDER = OSinfo.getFtp_folder();
	//连接符windows下为\,其他系统为/
	public static String CONNECTOR = OSinfo.getConnector() ;
}
