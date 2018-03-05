package operateSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class shell {
	//输出格式
	public static void prn(String add_address)
	{
	    System.out.print("ranmengyuan@ubuntu: "+add_address+"$ ");
	}
	public static int isPip(String instruct){
		int flag=-1;
		String[] temp=instruct.split(" ");
		for(int i=0;i<temp.length;i++){
			if(temp[i].equals("|")){
				flag=i;
				break;
			}
		}
		return flag;
	}
	//获得历史信息
	public static ArrayList<String> history(){
		File file = new File("/Users/ranmengyuan/Documents/date.txt");//Text文件
		BufferedReader br=null;
		FileReader fw = null;
		ArrayList<String> content=new ArrayList<>();
		try{
			fw=new FileReader(file);
			br = new BufferedReader(fw);//构造一个BufferedReader类来读取文件
			String s = null;
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
				content.add(s);
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
		return content;
		
	}
	public static void main(String args[]){
		String now_address="/Volumes/Transcend";
		String add_address="~";
		operate oper=new operate();
		Scanner in = new Scanner(System.in);
		while(true){
		        prn(add_address);
		        String instruct = in.nextLine();
	            if(instruct.equals("exit"))
	            	break;
	            else if(instruct.equals("history")){
		            oper.writeLogLine("/Users/ranmengyuan/Documents/date.txt",instruct);
		            oper.readLogLine("/Users/ranmengyuan/Documents/date.txt",-1);
		        }
		        else{
		            String[] temp=instruct.split(" ");
		            String comd=temp[0];
		            int num=isPip(instruct);
		            //后台命令
		            if(temp[temp.length-1].equals("&")){
		            	MutliThread m1=new MutliThread(oper,instruct,comd,now_address,add_address);
		            	m1.start();
		            }else if(num!=-1){//管道命令
		            	if(temp[0].equals("pwd")){
		            		if(temp[2].equals("ls")){
		            			oper.Ls("ls",now_address);
		            		}else if(temp[2].equals("echo")){
		            			System.out.println(now_address);
		            		}
		            		else{
		            			System.out.println(instruct+"未安装");
		            		}
		            	}else if(temp[0].equals("history")){
		            		ArrayList<String> data=history();
		            		if(temp[2].equals("more")){
		            			oper.More(data);
		            		}else{
		            			System.out.println(instruct+"未安装");
		            		}
		            	}else{
		            		System.out.println(instruct+"未安装");
		            	}
		        	}else{//一般命令
		            	try{
		            		oper.writeLogLine("/Users/ranmengyuan/Documents/date.txt",instruct);
				            if(comd.equals("cd")){
				                String[] add=oper.Cd(instruct,now_address,add_address);
				                now_address=add[0];
				                add_address=add[1];
				            }
				            else if(comd.equals("cp"))
				            	oper.Cp(instruct,now_address);
				            else if(comd.equals("rm"))
				            	oper.Rm(instruct,now_address);
				            else if(comd.equals("wc"))
				            	oper.Wc(instruct,now_address);
				            else if(comd.equals("mkdir"))
				            	oper.Mkdir(instruct,now_address);
				            else if(comd.equals("rmdir"))
				            	oper.Rmdir(instruct,now_address);
				            else if(comd.equals("ls"))
				            	oper.Ls(instruct,now_address);
				            else if(comd.equals("pwd"))
				            	oper.Pwd(instruct,now_address);
				            else if(comd.equals("echo"))
				            	oper.Echo(instruct);
				            else if(comd.equals("date"))
				            	oper.Date(instruct);
				            else if(comd.equals("history"))
				            	oper.History(instruct);
				            else if(comd.equals("help"))
				            	oper.Help(instruct);
				            else
				                System.out.println(comd+":未找到命令");
		            	}catch(Exception e){
		            		System.out.println("fail");
		            	}
		            }
		        }
		}
        in.close();
	}
}
//多线程程序
class MutliThread extends Thread{
	operate oper;
	String instruct;
	String comd;
	String now_address;
	String add_address;
    MutliThread(operate oper,String instruct,String comd,String now_address,String add_address){
        this.oper=oper;
        this.instruct=instruct;
        this.comd=comd;
        this.now_address=now_address;
        this.add_address=add_address;
    }
    public void run(){
    	try{
    		Thread.currentThread().sleep(3000);
	    	oper.writeLogLine("/Users/ranmengyuan/Documents/date.txt",instruct);
    		String[] temp=instruct.split(" ");
    		instruct=temp[0];
    		for(int i=1;i<temp.length-1;i++){
    			instruct=instruct+" "+temp[i];
    		}
	        if(comd.equals("cd")){
	            String[] add=oper.Cd(instruct,now_address,add_address);
	            now_address=add[0];
	            add_address=add[1];
	        }
	        else if(comd.equals("cp"))
	        	oper.Cp(instruct,now_address);
	        else if(comd.equals("rm"))
	        	oper.Rm(instruct,now_address);
	        else if(comd.equals("wc"))
	        	oper.Wc(instruct,now_address);
	        else if(comd.equals("mkdir"))
	        	oper.Mkdir(instruct,now_address);
	        else if(comd.equals("rmdir"))
	        	oper.Rmdir(instruct,now_address);
	        else if(comd.equals("ls"))
	        	oper.Ls(instruct,now_address);
	        else if(comd.equals("pwd"))
	        	oper.Pwd(instruct,now_address);
	        else if(comd.equals("echo"))
	        	oper.Echo(instruct);
	        else if(comd.equals("date"))
	        	oper.Date(instruct);
	        else if(comd.equals("history"))
	        	oper.History(instruct);
	        else if(comd.equals("help"))
	        	oper.Help(instruct);
	        else
	            System.out.println(comd+":未找到命令");
	        shell.prn(add_address);
    	}catch(Exception e){
    		System.out.println("fail");
    	}
    }
}
