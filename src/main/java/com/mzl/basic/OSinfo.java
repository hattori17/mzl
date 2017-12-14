package com.mzl.basic;

import java.io.File;

import com.mzl.tools.PublicClass;

public class OSinfo {
	private String OS = System.getProperty("os.name").toLowerCase();  
	private String error_log_folder ;
	private String sql_log_folder ;
	private String export_folder ;
	private String input_folder ;
	private String ftp_folder ;
	private String connector ;
   
    public OSinfo(){  
    	if(OS.indexOf("windows")>=0){
    		this.setConnector("\\") ;
    		/*this.setExport_folder("d:\\demo\\export\\") ;
    		this.setInput_folder("d:\\demo\\input\\") ;
    		this.setError_log_folder("d:\\demo\\error_log\\") ;
    		this.setSql_log_folder("d:\\demo\\sql_log\\") ;
    		this.setFtp_folder("d:\\demo\\ftp\\") ;*/
    		//this.setInput_folder("D://www1/922f812233e8a0bed198e572c3101099-0b44449e5f89af68015f99264938045f/webapp/attached") ;
    		//this.setError_log_folder("D://www1/922f812233e8a0bed198e572c3101099-0b44449e5f89af68015f99264938045f/webapp/error_log") ;
    		this.setInput_folder(PublicClass.getProperties().getProperty("filePath")+"/attached") ;
    		this.setError_log_folder(PublicClass.getProperties().getProperty("filePath")+"/error_log") ;
    	}else{
    		this.setConnector("/") ;
    		/*this.setExport_folder("/opt/demo/export/") ;
    		this.setInput_folder("/opt/demo/input/") ;
    		this.setError_log_folder("/opt/demo/error_log/") ;
    		this.setSql_log_folder("/opt/demo/sql_log/") ;
    		this.setFtp_folder("/opt/demo/ftp/") ;*/
    		this.setInput_folder("/opt/demo/attached/") ;
    		this.setError_log_folder("/opt/demo/error_log/") ;
    	}
    	//查看目录是否存在，不存在则创建
		File input_folder = new File(this.getInput_folder());
		if (!input_folder.exists()) {
			input_folder.mkdirs();
		}
		File error_log_folder = new File(this.getError_log_folder());
		if (!error_log_folder.exists()) {
			error_log_folder.mkdirs();
		}
    	/*//查看目录是否存在，不存在则创建
		File error_log_folder = new File(this.getError_log_folder());
		if (!error_log_folder.exists()) {
			error_log_folder.mkdirs();
		}
		//查看目录是否存在，不存在则创建
		File sql_log_folder = new File(this.getSql_log_folder());
		if (!sql_log_folder.exists()) {
			sql_log_folder.mkdirs();
		}
		//查看目录是否存在，不存在则创建
		File export_folder = new File(this.getExport_folder());
		if (!export_folder.exists()) {
			export_folder.mkdirs();
		}
		//查看目录是否存在，不存在则创建
		File input_folder = new File(this.getInput_folder());
		if (!input_folder.exists()) {
			input_folder.mkdirs();
		}
		//查看目录是否存在，不存在则创建
		File ftp_folder = new File(this.getFtp_folder());
		if (!ftp_folder.exists()) {
			ftp_folder.mkdirs();
		}*/
    }  
    
	public String getExport_folder() {
		return export_folder;
	}

	public void setExport_folder(String export_folder) {
		this.export_folder = export_folder;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public String getConnector() {
		return connector;
	}

	public String getError_log_folder() {
		return error_log_folder;
	}

	public void setError_log_folder(String error_log_folder) {
		this.error_log_folder = error_log_folder;
	}

	public String getSql_log_folder() {
		return sql_log_folder;
	}

	public void setSql_log_folder(String sql_log_folder) {
		this.sql_log_folder = sql_log_folder;
	}

	public String getInput_folder() {
		return input_folder;
	}

	public void setInput_folder(String input_folder) {
		this.input_folder = input_folder;
	}

	public String getFtp_folder() {
		return ftp_folder;
	}

	public void setFtp_folder(String ftp_folder) {
		this.ftp_folder = ftp_folder;
	}
	
	 /*
    public static boolean isLinux(){  
        return OS.indexOf("linux")>=0;  
    }  
      
    public static boolean isMacOS(){  
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")<0;  
    }  
      
    public static boolean isMacOSX(){  
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0;  
    }  
      
    public static boolean isWindows(){  
        return OS.indexOf("windows")>=0;  
    }  
      
    public static boolean isOS2(){  
        return OS.indexOf("os/2")>=0;  
    }  
      
    public static boolean isSolaris(){  
        return OS.indexOf("solaris")>=0;  
    }  
      
    public static boolean isSunOS(){  
        return OS.indexOf("sunos")>=0;  
    }  
      
    public static boolean isMPEiX(){  
        return OS.indexOf("mpe/ix")>=0;  
    }  
      
    public static boolean isHPUX(){  
        return OS.indexOf("hp-ux")>=0;  
    }  
      
    public static boolean isAix(){  
        return OS.indexOf("aix")>=0;  
    }  
      
    public static boolean isOS390(){  
        return OS.indexOf("os/390")>=0;  
    }  
      
    public static boolean isFreeBSD(){  
        return OS.indexOf("freebsd")>=0;  
    }  
      
    public static boolean isIrix(){  
        return OS.indexOf("irix")>=0;  
    }  
      
    public static boolean isDigitalUnix(){  
        return OS.indexOf("digital")>=0&&OS.indexOf("unix")>0;  
    }  
      
    public static boolean isNetWare(){  
        return OS.indexOf("netware")>=0;  
    }  
      
    public static boolean isOSF1(){  
        return OS.indexOf("osf1")>=0;  
    }  
      
    public static boolean isOpenVMS(){  
        return OS.indexOf("openvms")>=0;  
    }  
    */
      
}
