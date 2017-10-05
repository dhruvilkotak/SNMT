package pcap2edited;

import java.io.File;  
import java.nio.ByteBuffer;  
import java.util.ArrayList;  
import java.util.Date;
import java.util.List;  
import java.util.Scanner;
  
import org.jnetpcap.Pcap;  
import org.jnetpcap.PcapDumper;  
import org.jnetpcap.PcapHandler;  
import org.jnetpcap.PcapIf;  
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
  
  
public class PcapDumperExample {  
            List<PcapIf> alldevs;
        int i=0;
            // For any error msgs
            StringBuilder errbuf;

PcapIf device;
 Pcap pcap;
    public List getDevice()
    {
        List<String> dev=new ArrayList<String>();
         alldevs = new ArrayList<PcapIf>();
         errbuf = new StringBuilder();
//Getting a list of devices
            int r = Pcap.findAllDevs(alldevs, errbuf);
            System.out.println(r);
            if (r != Pcap.OK) {
                System.err.printf("Can't read list of devices, error is %s", errbuf
                        .toString());
                return dev;
            }

            System.out.println("Network devices found:");
            
            for (PcapIf device : alldevs) {
                String description =
                        (device.getDescription() != null) ? device.getDescription()
                        : "No description available";
         //       System.out.printf("#%d: %s [%s]\n", i++, device.getName(), description);
                String temp="#"+(i++)+"  "+device.getName()+" ["+description+"]";
                dev.add(temp);
            }
       //     System.out.println("choose the one device from above list of devices");
                      return dev;
    }
    public int check(int ch)
    {
          //ch = new Scanner(System.in).nextInt();
        System.out.println("chk:"+ch);
        device = alldevs.get(ch);

            int snaplen = 64 * 1024;           // Capture all packets, no trucation
            int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
            int timeout = 10 * 1000;           // 10 seconds in millis

            //Open the selected device to capture packets
             pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

            if (pcap == null) {
                System.err.printf("Error while opening device for capture: "
                        + errbuf.toString());
                return -1;
            }
            System.out.println("device opened");
            return ch;
    }
    
    
    public List packet(String filename)
    {
        final List<String> pkt=new ArrayList<String>();
       String ofile;
         errbuf = new StringBuilder();
       
     if(filename==null)
    {
         ofile = "tmp-capture-file.cap";  
        PcapDumper dumper = pcap.dumpOpen(ofile); // output file  
  
        PcapHandler<PcapDumper> dumpHandler = new PcapHandler<PcapDumper>() {  
        int i=0;
          public void nextPacket(PcapDumper dumper, long seconds, int useconds,  
            int caplen, int len, ByteBuffer buffer) {  
        i++;
            dumper.dump(seconds, useconds, caplen, len, buffer);  
          }  
        };  
      pcap.loop(10, dumpHandler, dumper);  

        File file = new File(ofile);  
        System.out.printf("%s file has %d bytes in it!\n", ofile, file.length());  
        dumper.close(); // Won't be able to delete without explicit close  
        pcap.close();
         ofile = "tmp-capture-file.cap";  
    }
    else
    {
        ofile=filename.replace("\\","\\\\");
        System.out.println("f:"+ofile);
    }
             pcap = Pcap.openOffline(ofile, errbuf);  
  
        if (pcap == null) {  
            System.err.printf("Error while opening device for capture: "  
                + errbuf.toString());  
            return pkt;  
        }  
  
        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {  
  
            @Override
            public void nextPacket(PcapPacket packet, String user) {  
             pkt.add("Received at"+new Date(packet.getCaptureHeader().timestampInMillis())+" caplen="+packet.getCaptureHeader().caplen()+" len="+packet.getCaptureHeader().wirelen()+" "+user+"\n");
            }  
        };  
      try {  
            pcap.loop(10, jpacketHandler, "jNetPcap rocks!");  
        } finally {  
           pcap.close();  
       
        }  
        
        return pkt;
    }
    public static void main(String[] args) {
        List<String> pkt=new ArrayList<String>();
        PcapDumperExample p=new PcapDumperExample();
        pkt=p.getDevice();
        for(int i=0;i<pkt.size();i++)
        {
            System.out.println(pkt.get(i));
        }
        System.out.println("bye");
  }  
} 