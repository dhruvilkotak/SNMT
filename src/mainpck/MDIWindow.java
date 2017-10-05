package mainpck;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;


import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pcap2edited.pcap;

import chart.NMT;

import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

@SuppressWarnings("serial")
public class MDIWindow extends JFrame{
    private JLabel welcome;
    public static JDesktopPane desktop;
  
    public static void main(String args[]){
    	new MDIWindow();
    }
    public MDIWindow() {
        
        
        super("Network Monitoring Tool");
        
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
        setFont(new Font("Times New Roman", Font.BOLD, 14));
        setTitle("Network Monitoring Tool");
        
        this.setExtendedState(MAXIMIZED_BOTH);      //Maximized Size for frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        this.setLocation(0, 0);
        this.setSize(new Dimension(1040, 817));
        //Set Current Date....
        java.util.Date currDate = new java.util.Date ();
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy");
        String d = sdf.format (currDate);
        
        welcome = new JLabel("Welcome Today is " + d, JLabel.CENTER);
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 14));
        welcome.setForeground(Color.blue);
        
        desktop = new JDesktopPane();
        desktop.setBackground(Color.LIGHT_GRAY);
        desktop.setBorder(new EmptyBorder(6, 4, 3, 4));
        getContentPane().add(welcome,BorderLayout.PAGE_END);
        getContentPane().add(desktop, BorderLayout.CENTER);
        desktop.setLayout(null);
        
     

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon (new ImageIcon(new ImageIcon(MDIWindow.class.getResource("/image/desk.png")).getImage().getScaledInstance( 1400,800, java.awt.Image.SCALE_SMOOTH)));
        lblNewLabel.setBounds(0, 0, 1424, 803);
        desktop.add(lblNewLabel);     
        
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton TaskList = new JButton("");
        TaskList.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		TaskManager TM = new TaskManager();
           		desktop.add(TM);     //Add Form in Desktop penal.....
                TM.setVisible(true);
        	}
        });
        TaskList.setBackground(SystemColor.controlHighlight);
        TaskList.setIcon (new ImageIcon(new ImageIcon(MDIWindow.class.getResource("/image/tm.png")).getImage().getScaledInstance( 50,50, java.awt.Image.SCALE_SMOOTH)));
        panel.add(TaskList);
        
               
        JButton btnChart = new JButton("");
        btnChart.setVerticalAlignment(SwingConstants.TOP);
        btnChart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
	        	NMT n=new NMT();
	        	desktop.add(n);     //Add Form in Desktop penal.....
	            n.setVisible(true);
        	}
        });
        btnChart.setIcon (new ImageIcon(new ImageIcon(MDIWindow.class.getResource("/image/chart.jpg")).getImage().getScaledInstance(  50,50, java.awt.Image.SCALE_SMOOTH)));

        panel.add(btnChart);
        
        JButton btnNM = new JButton("");
        btnNM.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	pcap p=new pcap();
        	desktop.add(p);     //Add Form in Desktop penal.....
            p.setVisible(true);
        	}
        });
        btnNM.setIcon (new ImageIcon(new ImageIcon(MDIWindow.class.getResource("/image/NM.jpg")).getImage().getScaledInstance(  50,50, java.awt.Image.SCALE_SMOOTH)));

        panel.add(btnNM);
        
        JButton btnspecification = new JButton("");
        btnspecification.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) 
                {
                    Specification p=new Specification();
                    desktop.add(p);     //Add Form in Desktop penal.....
                    p.setVisible(true);
        	}
        });
        btnspecification.setIcon (new ImageIcon(new ImageIcon(MDIWindow.class.getResource("/image/spec.png")).getImage().getScaledInstance(  50,50, java.awt.Image.SCALE_SMOOTH)));

        panel.add(btnspecification);
        
        setVisible(true);
        
    }
   
    ActionListener menulistener1 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String ActCmd = e.getActionCommand();
            if (ActCmd.equalsIgnoreCase("NewLaborEntry")) { 
            } 
        }
    };
    private JLabel lblNewLabel;
    private JPanel panel;
  
}
