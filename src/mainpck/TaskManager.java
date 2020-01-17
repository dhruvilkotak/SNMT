package mainpck;
import org.jdesktop.swingx.JXPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import javax.swing.*;

import org.jdesktop.swingx.JXTable;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;

public class TaskManager extends JInternalFrame implements Printable   {

	private final JPanel contentPanel = new JPanel();
	private JButton btnKill;
	private JButton btnLoad;
        private JButton btnsuspend;
	private JButton btnresume;
	private JButton btnExit;
	public static Object LName;
	public static int ANumber;
	private JXTable table;
	private DefaultTableModel tableModel; 
	private JPanel PTitle;

	public TaskManager() {
		setTitle("Task Manager ");
		setBounds(0, 0, 900, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setOpaque(true);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBorder(new TitledBorder(null, "",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			
			table = new JXTable();
                        
			Operation.loadProcess();
			tableModel = new DefaultTableModel(Operation.rowData, Operation.columnTitles);
			table = new JXTable(tableModel);
			
			scrollPane.setViewportView(table);
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

			PTitle = new JPanel();
			PTitle.setBackground(SystemColor.inactiveCaptionBorder);
			PTitle.setOpaque(false);
			PHeader.add(PTitle, BorderLayout.NORTH);
			PTitle.setLayout(new BorderLayout(0, 0));

			JLabel lblAcceptList = new JLabel("Welcome To Task Manager ...!!!");
			PTitle.add(lblAcceptList, BorderLayout.WEST);
			lblAcceptList.setFont(new Font("Times New Roman", Font.BOLD, 28));

			JLabel label = new JLabel("");
			PTitle.add(label, BorderLayout.EAST);
			
//			label.setIcon(new ImageIcon((
//					new ImageIcon(Toolkit.getDefaultToolkit()
//							.getImage(TaskManager.class.getResource("/images/Check.png"))))
//					.getImage().getScaledInstance(60, 60,
//							java.awt.Image.SCALE_SMOOTH)));
			label.setHorizontalAlignment(SwingConstants.CENTER);

			JToolBar toolBar = new JToolBar();
			toolBar.setBackground(SystemColor.activeCaptionBorder);
			toolBar.setPreferredSize(new Dimension(125, 4));
			toolBar.setOrientation(SwingConstants.VERTICAL);
			getContentPane().add(toolBar, BorderLayout.EAST);

			JButton btnAdd = new JButton("Add");

			btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String path = null;
						JFileChooser fc = null;
						 if (fc == null) 
						 {
						        fc = new JFileChooser();
						        fc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
						        fc.setAcceptAllFileFilterUsed(false);
						 }
						int returnVal = fc.showDialog(null, "Open");
						if (returnVal == JFileChooser.APPROVE_OPTION) {
						  
						   File file = fc.getSelectedFile();		 
						   path =  file.getAbsolutePath();		
						   Operation.startprocess(path);
						}
	
						
						
						
						Operation.loadProcess();
						tableModel = new DefaultTableModel(Operation.rowData, Operation.columnTitles);
						table.setModel(tableModel);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnAdd.setBackground(SystemColor.controlHighlight);
			btnAdd.setMnemonic('A');
			
			btnAdd.setToolTipText("Print Receipt");
			toolBar.add(btnAdd);

			JSeparator separator_4 = new JSeparator();
			toolBar.add(separator_4);

			btnKill = new JButton(
					"Kill");

			btnKill.setMnemonic('D');
			btnKill.setFont(new Font("Times New Roman", Font.BOLD, 18));
			
			btnKill.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				try {
					Operation.killProcess(table.getValueAt(table.getSelectedRow(), 0).toString());
                                        
                                        Operation.loadProcess();
					tableModel = new DefaultTableModel(Operation.rowData, Operation.columnTitles);
					table.setModel(tableModel);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
			btnKill.setBackground(SystemColor.controlHighlight);
			toolBar.add(btnKill);
			btnKill.setToolTipText("Edit (F2)");

			JSeparator separator = new JSeparator();
			toolBar.add(separator);
//-----------------------
                        btnsuspend = new JButton(
					"Suspend");

			btnsuspend.setMnemonic('D');
			btnsuspend.setFont(new Font("Times New Roman", Font.BOLD, 18));
			
			btnsuspend.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				try {
					Operation.suspend(table.getValueAt(table.getSelectedRow(), 0).toString());
                                        
                                        Operation.loadProcess();
					tableModel = new DefaultTableModel(Operation.rowData, Operation.columnTitles);
					table.setModel(tableModel);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
			btnsuspend.setBackground(SystemColor.controlHighlight);
			toolBar.add(btnsuspend);
			JSeparator separator_5 = new JSeparator();
			toolBar.add(separator_5);

                        
                        
//-----------------------                        
//-----------------------
                        btnresume = new JButton(
					"Resume");

			btnresume.setMnemonic('R');
			btnresume.setFont(new Font("Times New Roman", Font.BOLD, 18));
			
			btnresume.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				try {
					Operation.resume(table.getValueAt(table.getSelectedRow(), 0).toString());
                                        
                                        Operation.loadProcess();
					tableModel = new DefaultTableModel(Operation.rowData, Operation.columnTitles);
					table.setModel(tableModel);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
			btnresume.setBackground(SystemColor.controlHighlight);
			toolBar.add(btnresume);
			JSeparator separator_6 = new JSeparator();
			toolBar.add(separator_6);

                        
                        
//-----------------------                        
                        
                        
			btnLoad = new JButton(
					"Load");
//                        ,new ImageIcon((
//							new ImageIcon(Toolkit.getDefaultToolkit()
//									.getImage(TaskManager.class.getResource("/images/Refresh.png")))
//							.getImage().getScaledInstance(50, 50,
//									java.awt.Image.SCALE_SMOOTH)))
//					);

			btnLoad.setMnemonic('L');
			btnLoad.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btnLoad.setBackground(SystemColor.controlHighlight);
			toolBar.add(btnLoad);
			btnLoad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Operation.loadProcess();
					tableModel = new DefaultTableModel(Operation.rowData, Operation.columnTitles);
					table.setModel(tableModel);
				}
			});
			btnLoad.setToolTipText("Booking Canceled");
			
			JSeparator separator_3 = new JSeparator();
			toolBar.add(separator_3);

			btnExit = new JButton(
					"Exit");
//                        ,new ImageIcon((
//							new ImageIcon(Toolkit.getDefaultToolkit()
//									.getImage(TaskManager.class.getResource("/images/exit.png")))
//							.getImage().getScaledInstance(50, 50,
//									java.awt.Image.SCALE_SMOOTH)))
//					);
			btnExit.setMnemonic('X');
			btnExit.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btnExit.setBackground(SystemColor.controlHighlight);
			toolBar.add(btnExit);
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				}
			});
			btnExit.setToolTipText("Cancel Remove");

			JSeparator separator_2 = new JSeparator();
			toolBar.add(separator_2);		
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
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
				new TaskManager().setVisible(true);
			}
		});
	}

	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}




