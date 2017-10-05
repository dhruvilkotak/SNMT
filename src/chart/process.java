package chart;

import java.util.*;
import java.io.*;

import java.lang.management.*;

import javax.management.MBeanServerConnection;
import java.lang.management.ManagementFactory;
//import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
//import sun.management.ManagementFactory;

//import java.lang.management.ManagementFactory;
//import java.lang.management.RuntimeMXBean;
import com.sun.management.*;
import com.sun.management.OperatingSystemMXBean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class process 
{
	private final String TASKLIST = "tasklist ";
	private final String KILL = "taskkill /pid ";
	private final String sus ="E:\\setup\\PSTools\\pssuspend.exe ";
	ArrayList<String> process = new ArrayList<String>();
	public List tasklist() throws Exception
	{
		 Process p = Runtime.getRuntime().exec(TASKLIST);
		 BufferedReader reader = new BufferedReader(new InputStreamReader(
		   p.getInputStream()));
		 String line,l2;
                 int i=0;
		 while ((line=l2 = reader.readLine()) != null) {
                         process.add("\n"+line);
		}
                 return process; 
	}
	public boolean isProcessRunning(String serviceName) throws Exception 
	{

		 Process p = Runtime.getRuntime().exec(TASKLIST);
		 BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream()));
		 String line;
		 while ((line = reader.readLine()) != null) 
		 {
		  if (line.contains(serviceName))
		  {
		   return true;
		  }
		 }

		 return false;
	}
	public void killProcess(String serviceName) throws Exception
	{
		if (isProcessRunning(serviceName)) 
		{
			System.out.println("running :"+serviceName);
			Runtime.getRuntime().exec(KILL + serviceName);
		}

	}
	public void startprocess(String path) throws Exception
	{
		Runtime.getRuntime().exec(path);
	}
	public void suspend(String pid)throws Exception
	{
		System.out.println("suspending "+pid);
		Runtime.getRuntime().exec(sus + pid);
		
	}
	public void resume(String pid)throws Exception
	{
		Runtime.getRuntime().exec(sus+" -r " + pid);
	}
	//netsh for ethernet and wifi
	public Double protime()
	{
		//c:\Windows\system32\typeperf "\processor(_total)\% processor time"

		   String host=null;
		try
		{
			Process p = Runtime.getRuntime().exec("hostname");
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			 String line,l2;
			 while ((line=l2 = reader.readLine()) != null)
			 {
				 host=line;
				 
			 }
		 p = Runtime.getRuntime().exec("typeperf "+host+" \"\\processor(_total)\\% processor time\" -si 00:00:01");
		 reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                 line =l2= reader.readLine();
                 line =l2= reader.readLine();
		int j=0;
		  while ((line =l2= reader.readLine()) != null)
		 {
                        int i=0;
			 for (String retval: l2.split("\"", 4))
                         {
                        	 if(i==3)
				 {
                        		 String per=retval;
					 per=per.replaceAll("\"", "");
                  //                       perc.add(per);
                                        // a.add((int)floor(Double.parseDouble(per)));
                                        //break;
                                         System.out.println("h::"+Runtime.getRuntime().exec("cmd /c ext\\SendSignalC.exe /im typeperf.exe"));
                                         System.out.println("killed::"+Runtime.getRuntime().exec("taskkill /f /im typeperf.exe"));
                                      //   System.out.println(Runtime.getRuntime().exec("^c"));
                                         return (Double.parseDouble(per));
				 }
                                 i++;
			}
		     	 j++;
                  }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
                //return perc;
            return null;
	}
        public void downlSpeed()
        {
            try {
                BufferedReader reader ;
                String line,l2;//&& del test500.zip
                Process p = Runtime.getRuntime().exec("wget http://www.serc.iisc.ernet.in/~vss/courses/PPP/MD.ppt");
               
               //   System.out.println("h::"+Runtime.getRuntime().exec("cmd /c ext\\SendSignalC.exe /im wget.exe"));
                       reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                int j=0;
                while ((line =l2= reader.readLine()) != null)
                {
                    System.out.println("j::"+j+"  "+line);
                }
            } catch (IOException ex) {
                Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, ex);
            }
	
        }
	public void protim2()
	{
/*
		
		MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

		OperatingSystemMXBean osMBean;
		try {
			osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
		
		long nanoBefore = System.nanoTime();
		long cpuBefore = osMBean.getProcessCpuTime();

		// Call an expensive task, or sleep if you are monitoring a remote process

		long cpuAfter = osMBean.getProcessCpuTime();
		long nanoAfter = System.nanoTime();

		long percent;
		if (nanoAfter > nanoBefore)
		 percent = ((cpuAfter-cpuBefore)*100L)/(nanoAfter-nanoBefore);
		else percent = 0;

		System.out.println("Cpu usage: "+percent+"%");

		} catch (Exception e) {
		}
*/
            OperatingSystemMXBean osMBean =
			(OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		
			RuntimeMXBean runtimeMBean = ManagementFactory.getRuntimeMXBean();


			System.out.println("Operating system:\t" + osMBean.getName());
			System.out.println("Architecture:\t\t" + osMBean.getArch());
			System.out.println("Number of processors:\t" + osMBean.getAvailableProcessors());
			System.out.println("Process CPU time:\t" + osMBean.getProcessCpuTime() );
			System.out.println("Total physical memory:\t" + osMBean.getTotalPhysicalMemorySize()/1024 +" kB");
			System.out.println("Free physical memory:\t" + osMBean.getFreePhysicalMemorySize()/1024 +" kB" );
			System.out.println("Comm. virtual memory:\t" + osMBean.getProcessCpuTime()/1024 +" kB" );

			System.out.println("Total swap space:\t" + osMBean.getTotalSwapSpaceSize()/1024 +" kB" );
			System.out.println("Free swap space:\t" + osMBean.getFreeSwapSpaceSize() /1024 +" kB" );



        }
	
	
	public void printUsage() {
		  OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		  for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
		    method.setAccessible(true);
		    if (method.getName().startsWith("get") 
		        && Modifier.isPublic(method.getModifiers())) {
		            Object value;
		        try {
		            value = method.invoke(operatingSystemMXBean);
		        } catch (Exception e) {
		            value = e;
		        } // try
		        System.out.println(method.getName() + " = " + value);
		    } // if
		  } // for
		  
		}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner( System.in );
		process p=new process();
		try {
                        //p.printUsage();//chk 
			//p.protim2();//chk this and above....
			//p.protime();//cpu precentage..
			System.out.println(p.tasklist());
                      //  System.out.println(p.protime());
                    //p.downlSpeed();
//String s;
			//s=sc.next();
			//p.startprocess(s);//path  E:\programfiles\Notepad++\notepad++.exe
			//p.suspend(""+5904);
			//p.resume(""+5904);
			//p.killProcess(""+3320);//pid
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
