package pcap2edited;


import mainpck.MDIWindow;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

public class TestPacketHandler implements PcapPacketHandler<Object> {
  int num;
    String t;
 
  public TestPacketHandler(int n)
    {
        num=n;
    }
  private final Ip4 ip4 = new Ip4();
      private final Tcp tcp = new Tcp();
  private final Payload basePayload = new Payload();
  int i=0;
  @Override
  public void nextPacket(PcapPacket pcapPacket, Object obj) {
    System.out.println("num:"+num+"i:"+i);
    if(i==num-1)
    {
        t=pcapPacket.toString();
        System.out.println(t);  
        pcap2cap n=new pcap2cap(t);
        MDIWindow.desktop.add(n);
        n.setVisible(true);
    }
    i++; 

if (!pcapPacket.hasHeader(ip4)) {
      return;
    }
    if (!pcapPacket.hasHeader(tcp)) {
      return;
    }
    if (tcp.getPayloadLength() > 0 && !pcapPacket.hasHeader(basePayload)) {
      System.out.println("Invalid packet! No payload header detected, but TCP-PayloadLength = " + tcp.getPayloadLength());
      System.out.println("------------------------------------------------------------");
      System.out.println(pcapPacket.toString());
      System.out.println("------------------------------------------------------------");
      System.out.println(pcapPacket.toHexdump());
      return;
    }
    System.out.println("Everything is OK.");
  }
}