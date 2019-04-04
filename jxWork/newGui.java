package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.Element;
import javax.swing.text.TableView;
import javax.swing.text.TableView.TableCell;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import control.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Model;
import model.algorithm;
import model.tableModel;

import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

public class newGui extends JFrame 
{
	private JTable table,table1,table2;
	private JTextField textField_2,textField,textField_1,textField_3, textField_4,textField_5;
    private FileDialog openDia, saveDia;// ���塰�򿪡����桱�Ի���
    private jixieAction action;
    private JButton btnBrsowfe,button_1,button;
    private JPanel panel,panel_1;
    private JRbutton jrbutton;
    private JRadioButton rdbtnNewRadioButton,rdbtnNewRadioButton_1;
    private myMouse MyMouse;
    private tableModel tabmodel1,tabmodel2,tabmodel3;
    private JButton btnNewButton;
    private JMenuItem mntmNewMenuItem_1,mntmNewMenuItem_2,mntmNewMenuItem_3,mntmNewMenuItem_4;
    private JLabel lblSxy,lblSxz,lblSyz;
    private JLabel lblNewLabel_1;
    private JLabel label_4;
    private JTextField textField_6;
    private JLabel label_5;
    private JTextField textField_7;
    private JLabel label_6;
    private JTextField textField_8;
    private JButton button_2;
    private Model excelMode;
    private double[][] outDatax,outDatay,outDataz;
	private List<double[]> result;
	private TextArea textArea;
	private JMenu menu;
	private JMenu menu_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newGui frame = new newGui();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Model writeClass = new Model();
//					writeClass.read("C:\\Users\\yao\\Desktop\\����\\������\\������.xlsx");
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
		try {
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} 
		catch (Exception e1) {
		    e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		//���ô��ڸ�ʽ
		setBounds(100, 50, 1100,900);
		//���ô���ȫ����ʾ
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		
		menu = new JMenu("�ƶ����");
		menu.setFont(new Font("����", Font.PLAIN, 17));
		menu.setArmed(true);
		menuBar.add(menu);
		
		menu_1 = new JMenu("ת�����");
		menu_1.setFont(new Font("����", Font.PLAIN, 17));
		menu_1.setArmed(true);
		menuBar.add(menu_1);
		
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
		
		lblSxy = new JLabel("Sxy:");
		lblSxy.setFont(new Font("����", Font.PLAIN, 19));
		lblSxy.setBounds(211, 705, 133, 32);
		panel_3.add(lblSxy);
		
		textField_3 = new JTextField();
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		lblSxz = new JLabel("Sxz:");
		lblSxz.setFont(new Font("����", Font.PLAIN, 19));
		panel_3.add(lblSxz);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_3.add(textField_4);
		
		lblSyz = new JLabel("Syz:");
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
		
		/**
		 * ���廬����壬����������ڶ������ڵ��Ϸ�
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_1 .setLeftComponent(scrollPane_1);
		
		/**
		 * �ڴ��ڵ��в����һ���ղ��ֵ����
		 */
		panel = new JPanel(null);
		panel.setBackground(Color.white);//����ɫ
		panel.setPreferredSize(new Dimension(900,1000));
		scrollPane_1.setViewportView(panel);//�������뵽����������
		
		/**
		 * ����һ�������������������ڶ������ڵ��·�
		 */
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane_1 .setRightComponent(scrollPane_2);
		
		JPanel panel_2 = new JPanel(null);
		panel_2.setPreferredSize(new Dimension(900,130));	//������ѡ��С
		panel_2.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(panel_2);
		
		lblNewLabel_1 = new JLabel("�����ѯ��");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_1.setBounds(14, 13, 99, 27);
		panel_2.add(lblNewLabel_1);
		
		label_4 = new JLabel("x���ֵ:");
		label_4.setFont(new Font("����", Font.PLAIN, 19));
		label_4.setBounds(85, 49, 86, 22);
		panel_2.add(label_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(178, 50, 86, 24);
		panel_2.add(textField_6);
		
		label_5 = new JLabel("y���ֵ:");
		label_5.setFont(new Font("����", Font.PLAIN, 19));
		label_5.setBounds(288, 49, 77, 22);
		panel_2.add(label_5);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(381, 50, 86, 24);
		panel_2.add(textField_7);
		
		label_6 = new JLabel("z���ֵ:");
		label_6.setFont(new Font("����", Font.PLAIN, 19));
		label_6.setBounds(489, 50, 86, 22);
		panel_2.add(label_6);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(584, 50, 86, 24);
		panel_2.add(textField_8);
		
		button_2 = new JButton("��ѯ");
		button_2.setBounds(698, 49, 63, 27);
		panel_2.add(button_2);
		
		textArea = new TextArea();
		textArea.setBounds(85, 98, 777, 190);
		panel_2.add(textArea);

		
		/**
		 * ������岢��������һ���ָ�ڵ����
		 */
		panel_1 = new JPanel(null);
		splitPane.setLeftComponent(panel_1);
		panel_1.setBackground(Color.white);
	}
	
	//������ѡ����¼�������
	class JRbutton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//����Ű�
			panel.removeAll();
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
				System.out.println("������");
				//���������ӱ�ǩ
				JLabel lblNewLabel = new JLabel("�ֶ����룺");
				lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));//��������
				lblNewLabel.setBounds(79, 36, 118, 32);//λ��
				panel.add(lblNewLabel);
				
				//����x������ԣ�����ģ������Ϊ�Զ���ģ��
				table1 = new JTable();
				table1.setRowHeight(24);//�����и�
				table1.setFont(new Font("�����п�",Font.PLAIN,15));
				tabmodel1 = new tableModel("x",null);
				table1.setModel(tabmodel1);//ѡ�����ģ��
				table1.setRowSelectionAllowed(false);//���ò���ѡ����
				table1.setSelectionForeground(Color.blue);
				//��ӹ������������ӵ���������
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(79, 70, 1400, 201);
				scrollPane.setViewportView(table1);
				panel.add(scrollPane);
				
				// ���y����
				table = new JTable();
				table.setRowHeight(24);
				table.setFont(new Font("�����п�",Font.PLAIN,15)); 
				tabmodel2 = new tableModel("y",null);
				table.setModel(tabmodel2);
				table.setRowSelectionAllowed(false);
				table.setSelectionForeground(Color.black);
				
				JScrollPane scrollPane1 = new JScrollPane();
				scrollPane1.setBounds(79, 301, 1400, 201);
				scrollPane1.setViewportView(table);
				panel.add(scrollPane1);
				
				//���z����
				table2 = new JTable();
				table2.setRowHeight(24);
				table2.setFont(new Font("�����п�",Font.PLAIN,15)); 
				tabmodel3 = new tableModel("z",null);
				table2.setModel(tabmodel3);
				table2.setRowSelectionAllowed(false);
				
				
				JScrollPane scrollPane2 = new JScrollPane();
				scrollPane2.setBounds(79, 544,1400, 201);
				scrollPane2.setViewportView(table2);
				panel.add(scrollPane2);
			}
			panel.updateUI();	
		}
	}
	
	/**
	 * ������������ʵ�����
	 * @author yao
	 *
	 */
	class jixieAction implements ActionListener
	{
		private Object[][] data;
		private File myFile;
		private JFileChooser jf;

		@Override
		public void actionPerformed(ActionEvent e) 
		{
//			openDia = new FileDialog(newGui.this,"",FileDialog.LOAD);
			//�½��¼�
			if(e.getSource()==mntmNewMenuItem_1) 
			{
				newGui new1 = new newGui();
				new1.setVisible(true);
				//��Ӵ��ڵĹر��¼���ֻ�رյ�ǰ����
				new1.addWindowListener(new WindowAdapter() {
					public void	windowClosed(WindowEvent e) {
						new1.dispose();
					}
				});
			}
			//���¼�
			if(e.getSource()==mntmNewMenuItem_2)
			{
				panel.removeAll();
				jf = new JFileChooser();
				jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				System.out.println("��2��Ӧ");
				jf.showOpenDialog(newGui.this);
				jf.setMultiSelectionEnabled(true);
				//����һ����״�˵�
				myFile =jf.getSelectedFile();
				System.out.println(myFile);
				treeMeun(myFile);
				excelMode= new Model();
				result = excelMode.read(myFile+"\\������\\������.xlsx");
				xianshi("x",myFile+"\\�������\\x���������.xlsx","xlsx","open");
				xianshi("y",myFile+"\\�������\\y���������.xlsx","xlsx","open");
				xianshi("z",myFile+"\\�������\\z���������.xlsx","xlsx","open");
				
			}
			if(e.getSource()==mntmNewMenuItem_3){
				if(myFile==null) {
					Save();
				}else {
					System.out.println(myFile);
					excelMode.write(outDatax, myFile+"\\�������\\x���������.xlsx","x");
					excelMode.write(outDatay, myFile+"\\�������\\y���������.xlsx","y");
					excelMode.write(outDataz, myFile+"\\�������\\z���������.xlsx","z");
					excelMode.write(result,myFile+"\\������\\������.xlsx");
					treeMeun(myFile);
				}
			}
			if(e.getSource()==mntmNewMenuItem_4)
			{
				System.out.println("��4��Ӧ");
				Save();

			}
			//���������ļ���ťʱ�������¼�
			if(e.getSource()==btnBrsowfe){
				//����FileDialogΪѡ���ļ�
				openDia = new FileDialog(newGui.this,"",FileDialog.LOAD);
				//����ֻ���Զ�ȡ���ļ�����
				openDia.setFile("*.xls;*.xlsx");
				openDia.setVisible(true);
				excelMode = new Model();
				if(openDia.getFile()==null) {
					JOptionPane.showMessageDialog(newGui.this, "�ļ�����Ϊ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}else {
					String type = (String) openDia.getFile().subSequence(openDia.getFile().indexOf(".")+1, openDia.getFile().length());
					textField_2.setText(openDia.getDirectory()+openDia.getFile());
					xianshi("x", openDia.getDirectory()+openDia.getFile(),type,"create");
				}
			}
			if(e.getSource()==button){
				//����FileDialogΪѡ���ļ�
				openDia = new FileDialog(newGui.this,"",FileDialog.LOAD);
				//����ֻ���Զ�ȡ���ļ�����
				openDia.setFile("*.xls;*.xlsx");
				openDia.setVisible(true);
				excelMode = new Model();
				if(openDia.getFile()==null) {
					JOptionPane.showMessageDialog(newGui.this, "�ļ�����Ϊ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}else {
					String type = (String) openDia.getFile().subSequence(openDia.getFile().indexOf(".")+1, openDia.getFile().length());
					textField.setText(openDia.getDirectory()+openDia.getFile());
					xianshi("y",openDia.getDirectory()+openDia.getFile(),type,"create");
				}
			}
			if(e.getSource()==button_1){
				//����FileDialogΪѡ���ļ�
				openDia = new FileDialog(newGui.this,"",FileDialog.LOAD);
				//����ֻ���Զ�ȡ���ļ�����
				openDia.setFile("*.xls;*.xlsx");
				openDia.setVisible(true);
				excelMode = new Model();
				if(openDia.getFile()==null) {
					JOptionPane.showMessageDialog(newGui.this, "�ļ�����Ϊ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}else {
					String type = (String) openDia.getFile().subSequence(openDia.getFile().indexOf(".")+1, openDia.getFile().length());
					textField_1.setText(openDia.getDirectory()+openDia.getFile());
					xianshi("z",openDia.getDirectory()+openDia.getFile(),type,"create");
				}
			}
		}
		/**
		 * ���Ϊ����
		 */
		public void Save() {
			jf=new JFileChooser();
			jf.setMultiSelectionEnabled(false);
			jf.showSaveDialog(newGui.this);
			myFile = jf.getSelectedFile();
			File file1=new File(jf.getSelectedFile(),"�������");
			File file2=new File(jf.getSelectedFile(),"������");
			System.out.println(jf.getSelectedFile());
			if (!file1.exists() && !file1.isDirectory()) {//�ļ������� �򴴽�һ��
				file1.mkdirs();
				file2.mkdirs();
			}
			excelMode.write(outDatax, jf.getSelectedFile()+"\\�������\\x���������.xlsx","x");
			excelMode.write(outDatay, jf.getSelectedFile()+"\\�������\\y���������.xlsx","y");
			excelMode.write(outDataz, jf.getSelectedFile()+"\\�������\\z���������.xlsx","z");
			excelMode.write(result,jf.getSelectedFile()+"\\������\\������.xlsx");
			treeMeun(myFile);
		}
		
		/**
		 * �ļ�����״�˵�
		 */
		public void treeMeun(File path) {
			//�������ڵ�
			DefaultMutableTreeNode root=new DefaultMutableTreeNode(path.getName());
			for(File i :path.listFiles())
			{
				DefaultMutableTreeNode  oneUser=new DefaultMutableTreeNode(i.getName());
				root.add(oneUser);
				for(String j :i.list()) {
					DefaultMutableTreeNode two =new DefaultMutableTreeNode(j);
					oneUser.add(two);
				}
			}
			JTree tree = new JTree(root);
			tree.setBounds(14, 13, 350, 291);
			panel_1.removeAll();
			panel_1.add(tree);
			panel_1.updateUI();	
		}
		
		/**
		 * ��ʾ�ļ�����ʱ�ı����ʾ
		 * @param xianshi
		 */
		public void xianshi(String tab,String path,String type,String way) 
		{
			switch (tab) 
			{
				case "x":
					//��Ŀ¼��ʾ����
//					textField_2.setText(path);
					// ����x������ԣ�����ģ������Ϊ�Զ���ģ��
					table1 = new JTable();
					table1.setRowHeight(24);//�����и�
					table1.setFont(new Font("�����п�",Font.PLAIN,15));
					tabmodel1 = new tableModel(tab,excelMode.read(path,type));
					table1.setModel(tabmodel1);//ѡ�����ģ��
					table1.setRowSelectionAllowed(false);
					//��ӹ������������ӵ���������
					JScrollPane scrollPane = new JScrollPane();
					if(way.equals("open")) {
						scrollPane.setBounds(79, 70, 1400, 201);
					}else {
						scrollPane.setBounds(79, 225, 1400, 201);
					}
					scrollPane.setViewportView(table1);
					panel.add(scrollPane);
					break;
				case "y":
//					textField.setText(path);
					// ���y����
					table = new JTable();
					table.setRowHeight(24);
					table.setFont(new Font("�����п�",Font.PLAIN,15)); 
					tabmodel2 = new tableModel(tab,excelMode.read(path,type));
					table.setModel(tabmodel2);
					table.setRowSelectionAllowed(false);
					
					JScrollPane scrollPane1 = new JScrollPane();
					if(way.equals("open")) {
						scrollPane1.setBounds(79, 301, 1400, 201);
					}else {
						scrollPane1.setBounds(79, 466, 1400, 201);
					}
					scrollPane1.setViewportView(table);
					panel.add(scrollPane1);
					panel.updateUI();
					break;
				case "z":
//					textField_1.setText(path);
					//���z����
					table2 = new JTable();
					table2.setRowHeight(24);
					table2.setFont(new Font("�����п�",Font.PLAIN,15));
					tabmodel3 = new tableModel(tab,excelMode.read(path,type));
					table2.setModel(tabmodel3);
					table2.setRowSelectionAllowed(false);
					
					JScrollPane scrollPane2 = new JScrollPane();
					if(way.equals("open")) {
						scrollPane2.setBounds(79, 544,1400, 201);
					}else {
						scrollPane2.setBounds(79, 709, 1400, 201);
					}
					scrollPane2.setViewportView(table2);
					panel.add(scrollPane2);
			}
			panel.updateUI();
		}
	}
	
	/**
	 * ����һ����������
	 * @author yao
	 *
	 */
	class myMouse extends MouseAdapter
	{
		double sxy,syz,sxz;
		int xleng,yleng,zleng;
		//�������ʱ������Ӧ�Ĺ��ܣ��ò����Ƕ��û�������㰴ťʱ������Ӧ�Ĺ��ܡ�
		public void mouseClicked(MouseEvent e) 
		{
			try {
				sxy = Double.valueOf(textField_3.getText());
				sxz = Double.valueOf(textField_4.getText());
				syz = Double.valueOf(textField_5.getText());
				controller contr = new controller();
				outDatax =tabmodel1.zhuanhuan();
				outDatay =tabmodel2.zhuanhuan();
				outDataz =tabmodel3.zhuanhuan();
				for(double[] i:outDatax) {
					for(double j:i) {
						System.out.print(j+"  ");
					}
					System.out.println();
				}
				result = new algorithm().resultList(outDatax,outDatay,outDataz,sxy,syz,sxz);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(newGui.this, "�������ݲ��淶", "������ʾ", JOptionPane.ERROR_MESSAGE);
				textField_3.setText(String.valueOf(sxy));
				textField_4.setText(String.valueOf(sxz));
				textField_5.setText(String.valueOf(syz));
			}
			catch (NullPointerException e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(newGui.this, "����û�е�������", "������ʾ", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
	
	
	
