package mainpck;
import org.jdesktop.swingx.JXPanel;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import javax.swing.*;

import org.jdesktop.swingx.JXTable;

import com.sun.management.OperatingSystemMXBean;



import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@SuppressWarnings("serial")
public class Specification extends JInternalFrame implements Printable  {

	private final JPanel contentPanel = new JPanel();
	private JXTable table;	
	String[] columnNames = new String[] {"Detail List", "Configuration"};
	String[][] rowData = new String[9][3];
	
	public static void main(String args[])
	{
		Specification a=new Specification();
	//	a.setBounds(100, 50, 900, 600);
		a.setVisible(true);

	}
	
	public Specification() {
	
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		setTitle("System Specification");
		setBounds(100, 50, 600, 400);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		systemSpeci();
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setOpaque(true);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBorder(new TitledBorder(null, "",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JXTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				table.setColumnControlVisible(true);
				table.setShowVerticalLines(true);
				table.setInheritsPopupMenu(true);
				table.setFillsViewportHeight(false);
				
				DefaultTableModel dt=new DefaultTableModel(rowData,columnNames);
				table.setModel(dt);
				scrollPane.setViewportView(table);
			}
		}
		{
			JXPanel PHeader = new JXPanel();
			PHeader.setBackground(SystemColor.menu);
			PHeader.setForeground(Color.WHITE);
			PHeader.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			PHeader.setPreferredSize(new Dimension(10, 80));
			getContentPane().add(PHeader, BorderLayout.NORTH);
			PHeader.setLayout(new BorderLayout(0, 0));

			JPanel PTitle = new JPanel();
			PTitle.setBackground(SystemColor.inactiveCaptionBorder);
			PTitle.setOpaque(false);
			PHeader.add(PTitle, BorderLayout.NORTH);
			PTitle.setLayout(new BorderLayout(0, 0));

			JLabel lblAcceptList = new JLabel("Sytem Specification");
			lblAcceptList.setBackground(SystemColor.scrollbar);
			PTitle.add(lblAcceptList, BorderLayout.WEST);
			lblAcceptList.setFont(new Font("Times New Roman", Font.BOLD, 30));

			JLabel label = new JLabel("");
			PTitle.add(label, BorderLayout.EAST);
			label.setIcon(new ImageIcon((
					new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage(Specification.class.getResource("/image/Check.png"))))
					.getImage().getScaledInstance(60, 60,
							java.awt.Image.SCALE_SMOOTH)));
			
			label.setHorizontalAlignment(SwingConstants.CENTER);

			
		}
	}
	public void systemSpeci()
	{

            OperatingSystemMXBean osMBean =
			(OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		
			RuntimeMXBean runtimeMBean = ManagementFactory.getRuntimeMXBean();

			rowData[0][0]="Operating system:";
			rowData[1][0]="Architecture:";
			rowData[2][0]="Number of processors:";
			rowData[3][0]="Process CPU time:";
			rowData[4][0]="Total physical memory:";
			rowData[5][0]="Free physical memory:";
			rowData[6][0]="Comm. virtual memory:";
			rowData[7][0]="Total swap space:";
			rowData[8][0]="Free swap space:";
			
			rowData[0][1]=osMBean.getName();
			rowData[1][1]=osMBean.getArch();
			rowData[2][1]=String.valueOf(osMBean.getAvailableProcessors());
			rowData[3][1]=String.valueOf(osMBean.getProcessCpuTime());
			rowData[4][1]= osMBean.getTotalPhysicalMemorySize()/1024 +" kB";
			rowData[5][1]=osMBean.getFreePhysicalMemorySize()/1024 +" kB";
			rowData[6][1]=osMBean.getProcessCpuTime()/1024 +" kB" ;
			rowData[7][1]=osMBean.getTotalSwapSpaceSize()/1024 +" kB" ;
			rowData[8][1]=osMBean.getFreeSwapSpaceSize() /1024 +" kB";
			
		



        }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
