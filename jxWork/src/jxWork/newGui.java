package jxWork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.Element;
import javax.swing.text.TableView;
import javax.swing.text.TableView.TableCell;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import jxWork.mainGui.jixieAction;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.*;

public class newGui extends JFrame 
{
	private Color white;
	private JTable table,table1,table2;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
    private FileDialog openDia, saveDia;// ���塰�򿪡����桱�Ի���
    private jixieAction action;
    private JButton btnBrsowfe,button_1,button;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JPanel panel;
    private JRbutton jrbutton;
    private JRadioButton rdbtnNewRadioButton,rdbtnNewRadioButton_1;
    private myMouse MyMouse;
    private tableModel tabmodel1,tabmodel2,tabmodel3;
    private JButton btnNewButton;
    private JMenuItem mntmNewMenuItem_1,mntmNewMenuItem_2,mntmNewMenuItem_3,mntmNewMenuItem_4;
    private JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newGui frame = new newGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public newGui() {
		
		//���ô��ڸ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1100,900);
		
		//ע�ᰴť������
		action = new jixieAction();
		//ע�ᵥѡ�����
		jrbutton=new JRbutton();
		//������������
		MyMouse = new myMouse();
		/**
		 * �����˵���
		 */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(238,241,250));
		setJMenuBar(menuBar);
		/**
		 * ���˵�����Ӳ˵�
		 */
		JMenu mnNewMenu = new JMenu("�ļ�");
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setFont(new Font("����", Font.PLAIN, 17));
		menuBar.add(mnNewMenu);
		
		/**
		 * ���˵��������Ӧ������
		 */
		mntmNewMenuItem_1 = new JMenuItem("�½�   ");
		mntmNewMenuItem_1.setFont(new Font("����", Font.PLAIN, 17));
		mntmNewMenuItem_1.addActionListener(action);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("��");
		mntmNewMenuItem_2.addActionListener(action);
		mntmNewMenuItem_2.setFont(new Font("����", Font.PLAIN, 17));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("����");
		mntmNewMenuItem_3.addActionListener(action);
		mntmNewMenuItem_3.setFont(new Font("����", Font.PLAIN, 17));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_4 = new JMenuItem("���Ϊ");
		mntmNewMenuItem_4.addActionListener(action);
		mntmNewMenuItem_4.setFont(new Font("����", Font.PLAIN, 17));
		mnNewMenu.add(mntmNewMenuItem_4);
		
		
		/**
		 * �������˵��������ѡ�񣬽��˵���һֱ��ʾΪѡ��
		 */
		JMenu mnNewMenu_2 = new JMenu("��������");
		mnNewMenu_2.setFont(new Font("����", Font.PLAIN, 17));
		mnNewMenu_2.setArmed(true);//�˵�����ʾһֱѡ��
		menuBar.add(mnNewMenu_2);
		
		/**
		 * ��ҳ���ϲ���Ӽ��㰴ť�Լ��������ݵ������
		 */
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(548, 817, 10, 10);
		panel_3.setBackground(new Color(209,224,239));
		getContentPane().add(panel_3,BorderLayout.NORTH);
		
		btnNewButton = new JButton("����");
		btnNewButton.addMouseListener(MyMouse);
		panel_3.add(btnNewButton);
		
		JLabel lblSxy = new JLabel("Sxy:");
		lblSxy.setFont(new Font("����", Font.PLAIN, 19));
		lblSxy.setBounds(211, 705, 133, 32);
		panel_3.add(lblSxy);
		
		textField_3 = new JTextField();
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSxz = new JLabel("Sxz:");
		lblSxz.setFont(new Font("����", Font.PLAIN, 19));
		panel_3.add(lblSxz);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_3.add(textField_4);
		
		JLabel lblSyz = new JLabel("Syz:");
		lblSyz.setFont(new Font("����", Font.PLAIN, 19));
		panel_3.add(lblSyz);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_3.add(textField_5);
		
		/**
		 * �������ѡ��򣬲������������һ���γɵ�ѡ��
		 */
		rdbtnNewRadioButton = new JRadioButton("�ļ�����");
		panel_3.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("�ֶ�����");
		panel_3.add(rdbtnNewRadioButton_1);
		
		ButtonGroup dataIn=new ButtonGroup();
		dataIn.add(rdbtnNewRadioButton);
		dataIn.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton.addActionListener(jrbutton);
		rdbtnNewRadioButton_1.addActionListener(jrbutton);
		
		/**
		 * ����һ���ָ��
		 */
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(200);
		splitPane.setDividerSize(7);//���÷ָ��ߴ�С
		splitPane.setOneTouchExpandable(true);//���÷ָ�������ʾ����
		getContentPane().add(splitPane,BorderLayout.CENTER);
		
		/**
		 * ����ڶ����ָ�ڣ������ô��ڷ�����һ���ָ�ڵ��Ҳ�
		 */
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);//���ô���Ϊ����ģʽ
		splitPane.setRightComponent(splitPane_1);
		splitPane_1.setDividerSize(7);
		splitPane_1.setOneTouchExpandable(true);
		splitPane_1.setDividerLocation(680);
		splitPane_1.setResizeWeight(1);
		
		/**
		 * ���廬����壬����������ڶ������ڵ��Ϸ�
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
//		splitPane_1 .setRightComponent(scrollPane_1);
		splitPane_1 .setLeftComponent(scrollPane_1);
		
		/**
		 * �ڴ��ڵ��в����һ���ղ��ֵ����
		 */
		panel = new JPanel(null);
		panel.setBackground(Color.white);//����ɫ
//		panel.setBounds(548, 817, 10, 10);
		panel.setPreferredSize(new Dimension(900,1000));
		scrollPane_1.setViewportView(panel);//�������뵽����������
		
		/**
		 * ����һ�������������������ڶ������ڵ��·�
		 */
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane_1 .setRightComponent(scrollPane_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(900,130));	//������ѡ��С
		panel_2.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(panel_2);
		
		
		JLabel label_4 = new JLabel("���:");
		label_4.setFont(new Font("����", Font.PLAIN, 19));
		label_4.setBounds(211, 705, 133, 32);
		panel_2.add(label_4);
		
		
		/**
		 * ������岢��������һ���ָ�ڵ����
		 */
		panel_1 = new JPanel(null);
		splitPane.setLeftComponent(panel_1);
		panel_1.setBackground(Color.white);
		
		//����һ����״�˵�
//		DefaultMutableTreeNode root=new DefaultMutableTreeNode("���к���");
//		JTree tree = new JTree(root);
//		tree.setBounds(14, 13, 171, 291);
//		panel_1.add(tree);
		
		
	}
	
	//������ѡ����¼�������
	class JRbutton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//����Ű�
			panel.removeAll();
//			System.out.println(e.getSource());
			//ѡ���ļ�����ʱ��ʾ�����
			if(e.getSource()==rdbtnNewRadioButton) 
			{
				/**
				 * ����ļ���д����
				 */
				JLabel label = new JLabel("�ļ���ȡ:");
				label.setFont(new Font("����", Font.PLAIN, 20));
				label.setBounds(79, 36, 118, 32);
				panel.add(label);
				
				/**
				 *x��ı�ǩ��ʾ��������Լ��ļ�ѡ�а�ť
				 */
				JLabel label_1 = new JLabel("x������ļ�:");
				label_1.setFont(new Font("����", Font.PLAIN, 19));
				label_1.setBounds(211, 60, 133, 32);
				panel.add(label_1);
				
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(334, 63, 475, 31);
				textField_2.setFont(new Font("����", Font.PLAIN, 20));
				panel.add(textField_2);
				
				btnBrsowfe = new JButton("���");
				btnBrsowfe.setFont(new Font("����", Font.PLAIN, 19));
				btnBrsowfe.setBounds(823, 65, 80, 27);
				btnBrsowfe.addActionListener(action);
				panel.add(btnBrsowfe);
				
				/**
				 * y���ѡ�����
				 */
				JLabel label_2 = new JLabel("y������ļ�:");
				label_2.setFont(new Font("����", Font.PLAIN, 19));
				label_2.setBounds(211, 101, 133, 32);
				panel.add(label_2);
				
				textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(334, 104, 475, 31);
				textField.setFont(new Font("����", Font.PLAIN, 20));
				panel.add(textField);
				
				button = new JButton("���");
				button.setFont(new Font("����", Font.PLAIN, 19));
				button.setBounds(823, 106, 80, 27);
				button.addActionListener(action);
				panel.add(button);
				
				/**
				 * z���ļ�ѡ�����
				 */
				JLabel label_3 = new JLabel("z������ļ�:");
				label_3.setFont(new Font("����", Font.PLAIN, 19));
				label_3.setBounds(211, 142, 133, 32);
				panel.add(label_3);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(334, 145, 475, 31);
				textField_1.setFont(new Font("����", Font.PLAIN, 20));
				panel.add(textField_1);
				
				button_1 = new JButton("���");
				button_1.setFont(new Font("����", Font.PLAIN, 19));
				button_1.setBounds(823, 147, 80, 27);
				button_1.addActionListener(action);
				panel.add(button_1);
			}else if(e.getSource()==rdbtnNewRadioButton_1) //�ֶ�����ʱ���ļ�
			{
				Object[][] data= new Object[7][15];
				System.out.println("������");
				/**
				 * ���������ӱ�ǩ
				 */
				JLabel lblNewLabel = new JLabel("�ֶ����룺");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));//��������
				lblNewLabel.setBounds(79, 36, 118, 32);//λ��
				panel.add(lblNewLabel);
				
				/**
				 * ����x������ԣ�����ģ������Ϊ�Զ���ģ��
				 */
				table1 = new JTable();
				table1.setRowHeight(25);//�����и�
				table1.setFont(new Font("�����п�",Font.PLAIN,15));
				table1.setModel(new tableModel(1,data));//ѡ�����ģ��
				System.out.println("�ı�������"+table1.getSelectedRow());
				
				/**
				 * ��ӹ������������ӵ���������
				 */
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(211, 60, 692, 200);
				scrollPane.setViewportView(table1);
				panel.add(scrollPane);
				
				/**
				 * ���y����
				 */
				table = new JTable();
				table.setRowHeight(25);
				table.setFont(new Font("�����п�",Font.PLAIN,15)); 
				table.setModel(new tableModel(2,data));
				
				JScrollPane scrollPane1 = new JScrollPane();
				scrollPane1.setBounds(211, 301, 692, 200);
				scrollPane1.setViewportView(table);
				panel.add(scrollPane1);
				
				/**
				 * ���z����
				 */
				table2 = new JTable();
				table2.setRowHeight(25);
				table2.setFont(new Font("�����п�",Font.PLAIN,15)); 
				table2.setModel(new tableModel(3,data));
				
				JScrollPane scrollPane2 = new JScrollPane();
				scrollPane2.setBounds(211, 544, 692, 200);
				scrollPane2.setViewportView(table2);
				panel.add(scrollPane2);
			}
			panel.updateUI();	
		}
	}
	
	/**
	 * ����������
	 * @author yao
	 *
	 */
	class jixieAction implements ActionListener
	{
		
		private String mulu,name;
		private Object[][] data;

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			openDia = new FileDialog(newGui.this,"",FileDialog.LOAD);
			JFileChooser jf = new JFileChooser();
			jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(e.getSource()==mntmNewMenuItem_1) 
			{
				System.out.println("��һ��Ӧ");
				openDia.setFile("");
				openDia.setVisible(true);
				newGui new1 = new newGui();
				new1.setVisible(true);
			}else if(e.getSource()==mntmNewMenuItem_2)
			{
				System.out.println("��2��Ӧ");
				jf.showOpenDialog(newGui.this);
				//����һ����״�˵�
				DefaultMutableTreeNode root=new DefaultMutableTreeNode(jf.getName(jf.getSelectedFile()));
				JTree tree = new JTree(root);
				tree.setBounds(14, 13, 171, 291);
				panel_1.add(tree);
				panel_1.updateUI();	
				
			}else if(e.getSource()==mntmNewMenuItem_3)
			{
				System.out.println("��3��Ӧ");

			}else if(e.getSource()==mntmNewMenuItem_4)
			{
				System.out.println("��4��Ӧ");

			}else if(e.getSource()==btnBrsowfe)
			{
				xianshi(1);
			}else if(e.getSource()==button)
			{
				xianshi(2);
			}else if(e.getSource()==button_1)
			{
				xianshi(3);
			}
		}
		
		/**
		 * ��ʾ�ļ�����ʱ�ı����ʾ
		 * @param xianshi
		 */
		public void xianshi(int xianshi) 
		{
			openDia = new FileDialog(newGui.this,"",FileDialog.LOAD);
			openDia.setFile("*.xls;*.xlsx");
			openDia.setVisible(true);
			Model model = new Model();
			if(openDia.getFile()==null) {
				JOptionPane.showMessageDialog(newGui.this, "�ļ�����Ϊ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
			}else {
				mulu = openDia.getDirectory();
				name = openDia.getFile();
				if(xianshi==1) 
				{
					textField_2.setText(mulu+name);
					model.read(mulu,name);
					data=model.getX();
					
					/**
					 * ����x������ԣ�����ģ������Ϊ�Զ���ģ��
					 */
					table1 = new JTable();
					table1.setRowHeight(25);//�����и�
					table1.setFont(new Font("�����п�",Font.PLAIN,15));
					tabmodel1 = new tableModel(1,data);
					table1.setModel(tabmodel1);//ѡ�����ģ��
					
					/**
					 * ��ӹ������������ӵ���������
					 */
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(211, 225, 692, 200);
					scrollPane.setViewportView(table1);
					panel.add(scrollPane);
				}else if(xianshi==2) 
				{
					textField.setText(mulu+name);
					model.read(mulu,name);
					data=model.getX();
					
					/**
					 * ���y����
					 */
					table = new JTable();
					table.setRowHeight(25);
					table.setFont(new Font("�����п�",Font.PLAIN,15)); 
					tabmodel2 = new tableModel(3,data);
					table.setModel(tabmodel2);
					
					JScrollPane scrollPane1 = new JScrollPane();
					scrollPane1.setBounds(211, 466, 692, 200);
					scrollPane1.setViewportView(table);
					panel.add(scrollPane1);
					panel.updateUI();
				}
				else if(xianshi==3) 
				{
					textField_1.setText(mulu+name);
					model.read(mulu,name);
					data=model.getX();
					/**
					 * ���z����
					 */
					table2 = new JTable();
					table2.setRowHeight(25);
					table2.setFont(new Font("�����п�",Font.PLAIN,15));
					tabmodel3 = new tableModel(3,data);
					table2.setModel(tabmodel3);
					
					JScrollPane scrollPane2 = new JScrollPane();
					scrollPane2.setBounds(211, 709, 692, 200);
					scrollPane2.setViewportView(table2);
					panel.add(scrollPane2);
				}
				panel.updateUI();
			}
			
		}
		
	}
	
	class myMouse extends MouseAdapter
	{
		Object[][] x,y,z;
		public void mouseClicked(MouseEvent e) 
		{
				x=tabmodel1.getData();
				y=tabmodel2.getData();
				z=tabmodel3.getData();
				new algorithm(x,y,z);
		}
	}
		
		
}
	
	
	
