package operateSystem;

import java.util.ArrayList;

public class date {
	public ArrayList<String> setHelp()
	{
		ArrayList<String> content=new ArrayList<>();
	    content.add("history [-c] [-d ƫ����] [n] �� history >(( ���ʽ ))");
	    content.add("pwd [-LP]");
	    content.add("cd [-L|[-P [-e]] [-@]] [dir]");
	    content.add("echo [-neE] [���� ...]");
	    content.add("exit [n]");
	    content.add("help [-dms] [ģʽ ��]");
	    return content;
	}

}
