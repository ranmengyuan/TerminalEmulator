package operateSystem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 
import java.util.Calendar; 

import operateSystem.date;;

public class operate {
	//���ַ������ݿո���зָ���ָ�����ַ���
	public String getChar(String instruct,int index){
	    String[] temp=instruct.split(" ");
	    if(index<temp.length&&index>=0){
	    	return temp[index];
	    }
	    else{
	    	return null;
	    }
	}
	//д�ļ�
	public void writeLogLine(String fileName,String content){     //�������ļ�ȫ����д������
	    File file = new File(fileName);
	    FileWriter fw = null;
	    BufferedWriter writer = null;
	    try {
	        fw = new FileWriter(file,true);
	        writer = new BufferedWriter(fw);
	        writer.write(content);
	        writer.newLine();//����
	        writer.flush();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        try {
	            writer.close();
	            fw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }         //�ر��ļ�
	}
	//���ļ�
	public void readLogLine(String fileName,int sum){
		File file = new File(fileName);//Text�ļ�
		BufferedReader br=null;
		FileReader fw = null;
		int i=0;
		ArrayList<String> content=new ArrayList<>();
		try{
			fw=new FileReader(file);
			br = new BufferedReader(fw);//����һ��BufferedReader������ȡ�ļ�
			String s = null;
			while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
				if(sum==-1){
					i++;
					System.out.println(i+" "+s);
				}
				else
					content.add(s);
			}
			if(sum!=-1){
				if(sum<content.size()){
					for(i=content.size()-sum;i<content.size();i++){
						System.out.println((i+1)+" "+content.get(i));
					}
				}else if(sum<0){
					
				}else{
					for(i=0;i<content.size();i++){
						System.out.println((i+1)+" "+content.get(i));
					}
				}
			}
		}catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	    	try{
	    		br.close();
	    		fw.close();
	    	}catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	//����ļ�
	public void clearFileContent(String filePath) {  
		try { 
			FileOutputStream out = new FileOutputStream(filePath,false); 
			out.write(new String("").getBytes()); 
			out.close();  
		} catch (Exception ex) {
			System.out.println("fail to delete\n");
		} 
	} 


	//�жϸ��ַ����Ƿ�������
	int isNum(String content){
	    int flag=1;
	    int i=0;
	    char[] ch = content.toCharArray();//���ַ���ת����char����
	    for(i=0;i<content.length();i++)
	    {
	        if(ch[i]>'9'||ch[i]<'0')
	        {
	            flag=0;
	            break;
	        }
	    }
	    return flag;
	}
	

	//��ȡ��ǰ·�����ļ�����ϸ��Ϣ
	public List<File> getFileList(String filepath) {
		File file=new File(filepath);
    	String test[];
    	test=file.list();
    	for(int i=0;i<test.length;i++)
    	{
    		File   f   =   new   File( filepath+"/"+test[i]);    
    		long   size	=   f.length();	//   ��С   bytes 
    		long   modify	=   f.lastModified();	//   �޸�ʱ��    
    		System.out.println(new   Timestamp(modify).toString()+"\t"+size+"\t"+test[i]); 
    	}

		return null;

	}
	
	// �����ļ�   
	public static void copyFile(File sourceFile,File targetFile)   
	throws IOException{  
	        // �½��ļ����������������л���   
	        FileInputStream input = new FileInputStream(sourceFile);  
	        BufferedInputStream inBuff=new BufferedInputStream(input);  
	   
	        // �½��ļ���������������л���   
	        FileOutputStream output = new FileOutputStream(targetFile);  
	        BufferedOutputStream outBuff=new BufferedOutputStream(output);  
	           
	        // ��������   
	        byte[] b = new byte[1024 * 5];  
	        int len;  
	        while ((len =inBuff.read(b)) != -1) {  
	            outBuff.write(b, 0, len);  
	        }  
	        // ˢ�´˻���������   
	        outBuff.flush();  
	           
	        //�ر���   
	        inBuff.close();  
	        outBuff.close();  
	        output.close();  
	        input.close();  
	}  
	// �����ļ���   
	public static void copyDirectiory(String sourceDir, String targetDir)  
	        throws IOException {  
	        // �½�Ŀ��Ŀ¼   
	        (new File(targetDir)).mkdirs();  
	        // ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼   
	        File[] file = (new File(sourceDir)).listFiles();  
	        for (int i = 0; i < file.length; i++) {  
	            if (file[i].isFile()) {  
	                // Դ�ļ�   
	                File sourceFile=file[i];  
	                // Ŀ���ļ�   
	               File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());  
	               copyFile(sourceFile,targetFile);  
	            }  
	            if (file[i].isDirectory()) {  
	                // ׼�����Ƶ�Դ�ļ���   
	                String dir1=sourceDir + "/" + file[i].getName();  
	                // ׼�����Ƶ�Ŀ���ļ���   
	                String dir2=targetDir + "/"+ file[i].getName();  
	                copyDirectiory(dir1, dir2);  
	            }  
	        }  
	} 
	//�ж�Ŀ¼���ļ��Ƿ����
	public int isExist(String now_address,String filename){
	    	int flag=0;
	    	File file=new File(now_address);
	    	String[] test;
	    	test=file.list();
	    	for(int i=0;i<test.length;i++)
	    	{
	    		if(filename.equals(test[i])){
	    			flag=1;
	    			break;
	    		};
	    	}
	    	return flag;
	}
	
	//ɾ���ļ���Ŀ¼
    public void clearFiles(String workspaceRootPath){
          File file = new File(workspaceRootPath);
          if(file.exists()){
              deleteFile(file);
         }
    }
    public void deleteFile(File file){
         if(file.isDirectory()){
              File[] files = file.listFiles();
              for(int i=0; i<files.length; i++){
                   deleteFile(files[i]);
              }
         }
         file.delete();
   }
    //���ϵͳ��ǰʱ��
    public Time getTime(int flag){
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		month++;
		Time now=new Time(year,month,date,hour,minute,second);
		if(flag==1)
			System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second); 
		return now;
	} 
    //���Ŀ¼�µ��ļ�����
	public int getNum(String address){
		File file = (new File(address));
		String test[];
		test=file.list();
		return test.length;
	}
	    
  //help����������ʾshell�ڲ�����İ�����Ϣ
	//����help
	//help history
  	void Help(String instruct){
  		date oper=new date();
  	    ArrayList<String>content =oper.setHelp();
  	    int i=0;
  	    int flag=0;
  	    String comd=getChar(instruct, 1);
  	    if(comd==null)
  	    {
  	        System.out.println("GNU bash���汾 4.3.42(1)-release (x86_64-pc-linux-gnu)");
  	        System.out.println("��Щ shell �������ڲ�����ġ������� `help' �Ի�ȡһ���б�");
  	        System.out.println("���� `help ����' �Եõ��йغ���`����'�ĸ�����Ϣ��");
  	        System.out.println("ʹ�� `info bash' ����ù��� shell �ĸ���һ������Ϣ��");
  	        System.out.println("ʹ�� `man -k' �� `info' ����ȡ�����б��е�����ĸ�����Ϣ��");
  	        System.out.println("�����Աߵ��Ǻ�(*)��ʾ��������á�");
  	        for(i=0;i<content.size();i++)
  	        {
  	            System.out.println(content.get(i));
  	        }
  	    }
  	    else
  	    {
  	        for(i=0;i<content.size();i++)
  	        {
  	            String tempComd=getChar(content.get(i), 0);
  	            if(tempComd.equals(comd))
  	            {
  	                System.out.println(content.get(i));
  	                flag=1;
  	                break;
  	            }
  	        }
  	        if(flag==0)
  	            System.out.println("bash: help: û���� `"+comd+"' ƥ��İ������⡣���� `help help' �� `man -k"+comd+"' �� `info "+comd+"'��");
  	    }
  	}
    //����·���ı仯
  	//�� cd /cd ~/cd $HOME/cd \
  	//cd ..
  	//cd operate
	String[] Cd(String instruct,String now_address,String add_address){
	    String address;
	    if(instruct.equals("cd")||instruct.equals("cd ~")||instruct.equals("cd $HOME")||instruct.equals("cd \\")){
	        now_address="/Volumes/Transcend";
	        add_address="~";
	    }
	    else{
	    	address=getChar(instruct, 1);
	    	if(address.equals("..")){
	    		if(now_address.equals("/Volumes/Transcend")==false){
	    			String[] temp=now_address.split("/");
		    		String tempAdd="/Volumes/Transcend";
		    		int flag=0;
		    		for(int i=3;i<temp.length-1;i++){
		    			tempAdd=tempAdd+"/"+temp[i];
		    			flag=1;
		    		}
		    		now_address=tempAdd;
		    		if(flag==1)
		    			add_address=temp[temp.length-2];
		    		else
		    			add_address="~";
	    		}
	    		else{
	    			now_address="/Volumes/Transcend";
	    			add_address="~";
	    		}
	    	}
	    	else{
	    		if(isExist(now_address, address)==1){
			    	now_address=now_address+"/"+address;
			    	add_address=address;
	    		}
	    		else{
	    			System.out.println("bash: cd: "+address+": û���Ǹ��ļ���Ŀ¼");
	    		}
	    	}
	        
	    }
	    String[] add={now_address,add_address};
	    return add;
	}
	//�ļ����ļ��еĸ���
	//�� cp file1 file2
	void Cp(String instruct,String now_address) throws Exception{
		String start=getChar(instruct,1);
		String end=getChar(instruct,2);
		String url1=now_address+"/"+start;
		String url2=now_address+"/"+end;
		if(isExist(now_address, start)==1){
			// ����Ŀ���ļ���   
	        (new File(url2)).mkdirs();  
	        // ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼   
	        File[] file = (new File(url1)).listFiles();  
	        for (int i = 0; i < file.length; i++) {  
	            if (file[i].isFile()) {  
	                // �����ļ�   
	                copyFile(file[i],new File(url2+"/"+file[i].getName()));  
	            }  
	            if (file[i].isDirectory()) {  
	                // ����Ŀ¼   
	                String sourceDir=url1+File.separator+file[i].getName();  
	                String targetDir=url2+File.separator+file[i].getName(); 
	                copyDirectiory(sourceDir, targetDir);  
	            }  
	        }  
		}
		else{
			System.out.println("cp: �޷���ȡ"+start+" ���ļ�״̬(stat): û���Ǹ��ļ���Ŀ¼");
		}
		
	}
	//ɾ���ļ�
	//�� rm flie2
	//rm -rf file2/rm -f file2//ǿ��ɾ��
	void Rm(String instruct,String now_address){
		Scanner input = new Scanner(System.in);
		String temp=getChar(instruct, 1);
		if(temp.equals("-rf")||temp.equals("-f")){
			String comd=getChar(instruct, 2);
			String address = now_address+"/"+comd;
			if(isExist(now_address, comd)==1){
				
				clearFiles(address);
			}
			else{
				System.out.println("rm: �޷�ɾ��"+comd+": û���Ǹ��ļ���Ŀ¼");
			}
		}
		else{
			String comd=temp;
			String address = now_address+"/"+comd;
			if(isExist(now_address, comd)==1){
				System.out.println("�Ƿ�ɾ��"+comd+"(y/n)");
				String info = input.nextLine();
				if(info.equals("y"))
					clearFiles(address);
			}
			else{
				System.out.println("rm: �޷�ɾ��"+comd+": û���Ǹ��ļ���Ŀ¼");
			}
		}
		
	}
	//һ����ʾһҳ
	void More(ArrayList<String> info){
		Scanner in = new Scanner(System.in);
		for(int j=0;j<23;j++){
			if(j==info.size())
				break;
			System.out.println(j+" "+info.get(j));
		}
		int i=23;
		int sum=23;
		while(i<info.size()){
			String instruct = in.nextLine();
			char[] ch=instruct.toCharArray();
			if(ch.length==0){
				System.out.println(i+" "+info.get(i));
				i++;
				sum++;
			}else{
				for(int j=sum;j<sum+23;j++){
					if(j==info.size())
					break;
					System.out.println(j+" "+info.get(j));
					i++;
				}
				sum=sum+23;
			}
			
		}
		
	}
	//ͳ��ָ���ļ��е��ֽ���������������
	//�� wc -c date.txt ��С
	//wc -l date.txt ����
	//wx -m date.txt ����
	void Wc(String instruct,String now_address){
		String comd=getChar(instruct, 1);
		String filename=getChar(instruct,2);
		File   f   =   new   File( now_address+"/"+filename);
		if(isExist(now_address, filename)==1){
			if(comd.equals("-c")){    
	    		long   size	=   f.length();	//   ��С   bytes 
	    		System.out.println(size+"\t"+filename);
			}else if(comd.equals("-l")){
				BufferedReader br=null;
				FileReader fw = null;
				try{
					int count =0;
					fw=new FileReader(f);
					br = new BufferedReader(fw);//����һ��BufferedReader������ȡ�ļ�
					while((br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
						count++;
					}
					System.out.println(count+"\t"+filename);
				}catch(Exception e){
					System.out.println("wc: "+comd+": û���Ǹ��ļ���Ŀ¼");
				}finally{
					try{
						br.close();
						fw.close();
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}else if(comd.equals("-m")){
				BufferedReader br=null;
				FileReader fw = null;
				try{
					int count =0;
					fw=new FileReader(f);
					br = new BufferedReader(fw);//����һ��BufferedReader������ȡ�ļ�
					String s;
					while((s=br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
						String[] temp=s.split(" ");
						count=count+temp.length;
					}
					System.out.println(count+"\t"+filename);
				}catch(Exception e){
					System.out.println("wc: "+comd+": û���Ǹ��ļ���Ŀ¼");
				}finally{
					try{
						br.close();
						fw.close();
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}else{
				System.out.println("wc: "+comd+": û���Ǹ��ļ���Ŀ¼");
			}
		}else{
			System.out.println("wc: "+comd+": û���Ǹ��ļ���Ŀ¼");
		}
	}
	//����Ŀ¼
	//�� mkdir file2
	void Mkdir(String instruct, String now_address){
		String filename=getChar(instruct,1);
		if(isExist(now_address, filename)==0){
			String address=now_address+"/"+filename;
			try{
			(new File(address)).mkdirs();
			}catch(Exception e){
				System.out.println("can't create directory"+filename+":permission denied");
			}
		}else{
			System.out.println("mkdir: �޷�����Ŀ¼"+filename+": �ļ��Ѵ���");
		}
	}
	//ɾ���յ�Ŀ¼
	//�� rmdir file
	// rmkdir -p file/file1/file2
	void Rmdir(String instruct,String now_address){
		String comd=getChar(instruct, 1);
		try{
			if(comd.equals("-p")){
				String comd1=getChar(instruct, 2);
				System.out.println("asdfs");
				String add=now_address+"/"+comd1;
				String[] temp=comd1.split("/");
				String address=now_address;
				for(int i=0;i<temp.length-1;i++){
					address=address+"/"+temp[i];
				}
				if(isExist(address, temp[temp.length-1])==1){
					if(getNum(add)==0){
						deleteFile(new File(add));
						String[] now=add.split("/");
						add=now[0];
						for(int i=1;i<now.length-1;i++){
							add=add+"/"+now[i];
						}
						while(add.equals(now_address)==false){
							System.out.println(add+"    "+getNum(add));
							if(getNum(add)==0){
								deleteFile(new File(add));
							}
							else{
								break;
							}
							now=add.split("/");
							add=now[0];
							for(int i=1;i<now.length-1;i++){
								add=add+"/"+now[i];
							}
						}
					}else{
						System.out.println("rmdir: ɾ�� "+comd1+" ʧ��: Ŀ¼�ǿ�");
					}
				}else{
					System.out.println("rmdir: ɾ�� "+comd1+" ʧ��: û���Ǹ��ļ���Ŀ¼");
				}
			}else{
				String add=now_address+"/"+comd;
				String[] temp=comd.split("/");
				String address=now_address;
				for(int i=0;i<temp.length-1;i++){
					address=address+"/"+temp[i];
				}
				if(isExist(address, temp[temp.length-1])==1){
					if(getNum(add)==0){
						deleteFile(new File(add));
					}else{
						System.out.println("rmdir: ɾ�� "+comd+" ʧ��: Ŀ¼�ǿ�");
					}
				}else{
					System.out.println("rmdir: ɾ�� "+comd+" ʧ��: û���Ǹ��ļ���Ŀ¼");
				}
			}
		}catch(Exception e){
			System.out.println("rmdir: ɾ�� "+comd+" ʧ��: û���Ǹ��ļ���Ŀ¼");
			e.printStackTrace();
		}	
	}
	//��ʾ��ǰ�ļ����µ��ļ���
	//�� ls/ls-a
	// ls -l��ϸ��Ϣ
	void Ls(String instruct,String now_address){
		String comd;
		comd=getChar(instruct,1);
		if(comd==null||comd.equals("-a")){
			File file=new File(now_address);
	    	String test[];
	    	test=file.list();
	    	for(int i=0;i<test.length;i++)
	    	{
	    		System.out.println(test[i]);
	    	}
		}
		else if(comd.equals("-l")){
			getFileList(now_address);
		}
	}
	//��ʾ��ǰĿ¼����·��
	//pwd/pwd -L/pwd -p
	void Pwd(String instruct,String now_address){
		if(instruct.equals("pwd")||instruct.equals("pwd -L")||instruct.equals("pwd -p"))
			System.out.println(now_address);
		else
			System.out.println("bash: "+instruct+": ��Чѡ��");
	}
	//��ʾ����
	//�� echo file hj kjh �Զ�����
	//echo -n df lk ��Ҫ������Զ�����
	void Echo(String instruct){
		String[] str=instruct.split(" ");
		if(str[1].equals("-n")){
			for(int i=2;i<str.length;i++){
				System.out.print(str[i]+" ");
			}
		}
		else{
			for(int i=1;i<str.length;i++){
				System.out.print(str[i]+" ");
			}
			System.out.println();
		}
	}
	//���ʱ��
	//�� date
	//date -d "+24hour" %y%m%d
	void Date(String instruct){
		try{
			if(instruct.equals("date"))
				getTime(1);
			else if(getChar(instruct, 1).equals("-d")){
				Time now =getTime(1);
				String oper=getChar(instruct, 2);
				char[] ch=oper.toCharArray();
				int time=Integer.parseInt(oper.substring(2, oper.length()-5));
				String wei=oper.substring(oper.length()-5,oper.length()-1);
				if(ch[1]=='+'){
					if(wei.equals("hour")){
						int hour=now.getHour();
						int day=now.getDate();
						int month=now.getMonth();
						int year=now.getYear();
						hour=hour+time;
						if(hour>24){
							day=day+hour/24;
							hour=hour%24;
							if(day>30){
								month=month+day/30;
								day=day%30;
								if(month>12){
									year=year+month/12;
									month=month%12;
								}
							}
						}
						if(getChar(instruct, 3).equals("%y%m%d")&&getChar(instruct, 4)==null){
							System.out.println(year+"/"+month+"/"+day);
						}else{
							System.out.println(year + "/" + month + "/" + day + " " +hour + ":" +now.getMinute() + ":" + now.getSecond());
						}
					}
					else if(wei.equals("week")){
						int day=now.getDate();
						int month=now.getMonth();
						int year=now.getYear();
						day=day+7*time;
						if(day>30){
							month=month+day/30;
							day=day%30;
							if(month>12){
								year=year+month/12;
								month=month%12;
							}
						}
						if(getChar(instruct, 3).equals("%y%m%d")&&getChar(instruct, 4)==null){
							System.out.println(year+"/"+month+"/"+day);
						}else{
							System.out.println(year + "/" + month + "/" + day + " " +now.getHour() + ":" +now.getMinute() + ":" + now.getSecond());
						}		
					}else{
						System.out.println("date: ��Ч������"+getChar(instruct, 1));
					}
					
				}
				else if(ch[1]=='-'){
					if(wei.equals("hour")){
						int time_year=time/(24*30*12);
						time=time%(24*30*12);
						int time_month=time/(24*30);
						time=time%(24*30);
						int time_day=time/24;
						int time_hour=time%24;
						int hour=now.getHour();
						int day=now.getDate();
						int month=now.getMonth();
						int year=now.getYear();
						hour=hour-time_hour;
						if(hour<0){
							hour=hour+24;
							day=day-1;
						}
						day=day-time_day;
						if(day<0){
							day=day+30;
							month=month-1;
						}
						month=month-time_month;
						if(month<0){
							month=month+12;
							year=year-1;
						}
						year=year-time_year;
						if(getChar(instruct, 3).equals("%y%m%d")&&getChar(instruct, 4)==null){
							System.out.println(year+"/"+month+"/"+day);
						}else{
							System.out.println(year + "/" + month + "/" + day + " " +hour + ":" +now.getMinute() + ":" + now.getSecond());
						}
						
					}
					else if(wei.equals("week")){
						int timeday=time*7;
						int time_year=timeday/(30*12);
						timeday=timeday%(30*12);
						int time_month=timeday/30;
						timeday=timeday%30;
						int day=now.getDate();
						int month=now.getMonth();
						int year=now.getYear();
						day=day-timeday;
						if(day<0){
							day=day+30;
							month=month-1;
						}
						month=month-time_month;
						if(month<0){
							month=month+12;
							year=year-1;
						}
						year=year-time_year;
						if(getChar(instruct, 3).equals("%y%m%d")&&getChar(instruct, 4)==null){
							System.out.println(year+"/"+month+"/"+day);
						}else{
							System.out.println(year + "/" + month + "/" + day + " " +now.getHour() + ":" +now.getMinute() + ":" + now.getSecond());
						}		
					}else{
						System.out.println("date: ��Ч������"+getChar(instruct, 1));
					}	
				}else{
					System.out.println("date: ��Ч������"+getChar(instruct, 1));
				}
			}
			else{
				System.out.println("date: ��Ч������"+getChar(instruct, 1));
			}
		}catch(Exception e){
			System.out.println("date: ��Ч������"+getChar(instruct, 1));
		}
	}
	//��ʾ��ʷ��Ϣ
	//�� history
	//history 10
	//history -c
	public void History(String instruct){
	    String comd;
	    int flag=0;
	    comd=getChar(instruct, 1);
	    flag=isNum(comd);
	    if(flag==1)
	    {
	    	int sum = Integer.parseInt(comd);
	        readLogLine("/Users/ranmengyuan/Documents/date.txt", sum);
	    }
	    else if(comd.equals("-c"))
	    {
	    	clearFileContent("/Users/ranmengyuan/Documents/date.txt");//ɾ��ԭ�ļ�
	    }
	    else{
	        System.out.println("bash: history: "+comd+": ��Ҫ���ֲ���");
	    }
	}
}
