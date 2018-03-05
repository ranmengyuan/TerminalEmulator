package operateSystem;

import java.util.ArrayList;

public class date {
	public ArrayList<String> setHelp()
	{
		ArrayList<String> content=new ArrayList<>();
	    content.add("history [-c] [-d 偏移量] [n] 或 history >(( 表达式 ))");
	    content.add("pwd [-LP]");
	    content.add("cd [-L|[-P [-e]] [-@]] [dir]");
	    content.add("echo [-neE] [参数 ...]");
	    content.add("exit [n]");
	    content.add("help [-dms] [模式 …]");
	    return content;
	}

}
