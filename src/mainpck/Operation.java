package mainpck;
import chart.process;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Operation {
	public static String[] columnTitles = {  " PID" ,"Process", " Session_name", "Session#", "MemUsage"};
	public static String[][] rowData;
	static ArrayList<String> ps = new ArrayList<String>();
    
	private final static String TASKLIST = "tasklist";
	private final static String KILL = "taskkill /pid ";
        private final static String sus ="E:\\setup\\proj\\PSTools\\pssuspend.exe ";
	public static void loadProcess()
	{
		rowData = new String[150][5] ;
		process p;
	    p = new process();
        try {
			ps=(ArrayList<String>) p.tasklist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String l2;
        
       
	    for(int i=0,j=0;i<ps.size();i++)
		{
			if(i>3)
			{
				l2=ps.get(i);
				l2=l2.replaceAll("\\s+", " ");
				int k=0;
				for ( String ret: l2.split(" ", 6))
				{
				
					switch(k)
					{
						case 1:
							rowData[j][1]=ret;
							break;
						case 2:
							rowData[j][0]=ret;
							break;
						case 3:
							rowData[j][2]=ret;
							break;
						case 4:
							rowData[j][3]=ret;
							break;
						case 5:
							rowData[j][4]=ret;
							break;
						default:
						   break;
					}
					k++;
				}
				j++;
			  
			}
		}
	}
	public static boolean isProcessRunning(String serviceName) throws Exception 
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
	public static void killProcess(String serviceName) throws Exception
	{
		if (isProcessRunning(serviceName)) 
		{
			System.out.println("running :"+serviceName);
			Runtime.getRuntime().exec(KILL + serviceName);
		}

	}
	public static void startprocess(String path) throws Exception
	{
		Runtime.getRuntime().exec(path);
	}
        public static void suspend(String pid)throws Exception
	{
		System.out.println("suspending "+pid);
		Runtime.getRuntime().exec(sus + pid);
		
	}
	public static void resume(String pid)throws Exception
	{
		Runtime.getRuntime().exec(sus+" -r " + pid);
	}
	
}
