package pcap2edited;


import java.io.File;
import org.jnetpcap.Pcap;

public class Tester 
{
    public String getPkt(int n,String filename)
    {
        File source;
        if(filename==null)
        {
             source = new File("tmp-capture-file.cap");
        }
        else
        {
             source = new File(filename);
        }
        
    StringBuilder errbuf = new StringBuilder();
    Pcap pcap = Pcap.openOffline(source.getAbsolutePath(), errbuf);
    if (pcap == null) {
      System.out.println(String.format("Unable to parse pcap file by path '%s' due to unexpected exception!%n%s", source.getAbsolutePath(), errbuf.toString()));
      return "d";
    }
    try {
       // Pcap.LOOP_INFINITE
      pcap.loop(n, new TestPacketHandler(n), null);
    } catch(Exception e) {
      System.out.println(String.format("Unable to parse pcap file by path '%s' due to unexpected exception!", source.getAbsolutePath()));
      e.printStackTrace();
    } finally {
      pcap.close();
    }
    return "d";
    }
  public static void main(String[] args) {
    
  }
}