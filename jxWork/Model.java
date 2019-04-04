package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Model 
{
	private  Object[][] data ;
	List<double[]> resultList;
	private InputStream in;
	//����һ����������
	Workbook wb;
	Sheet sheet;
	Row hssrow;
	Cell hsshell;
//	public Object[][] getData() {
//		return data;
//	}
	public Model() 
	{
		data= new Object[7][30];
	}
	public Object[][] read(String path,String type) 
	{
		try {
			//�����ļ����������
			in = new FileInputStream(new File(path));
			//���ļ��ĺ�׺ѡ�����
//			String pdwenjian = (String) name.subSequence(name.length()-4, name.length());
			//�жϺ�׺ѡ���Ӧ�Ĵ���ʽ
			if(type.equals("xls"))
			{
				wb = new HSSFWorkbook(in);
			}else if(type.equals("xlsx")) 
			{
				wb = new XSSFWorkbook(in);
			}
			sheet = wb.getSheetAt(0);	//���ظ��ļ��ĵ�0��Sheetҳ
			if(sheet==null)
			{
				return null;
			}
			//getLastRowNum()�Ƿ��ظ�ҳ�ж�����
			System.out.println(sheet .getLastRowNum());
			for(int row=1;row<=sheet .getLastRowNum();row++)
			{
				//getRow��x���Ƿ��ص�ǰҳ��x��----����
				hssrow = sheet.getRow(row);
				if(hssrow==null) 
				{
					continue;
				}
				//getLastCellNum()���ظ����ж����С�
				for(int cell=1;cell<=hssrow.getLastCellNum();cell++) 
				{
					//getCell��x���Ƿ��ص�ǰ�еĵ�x��----����
					hsshell = hssrow.getCell(cell);
					if(hsshell==null) 
					{
						continue;
					}
					//getNumericCellValue()��������ת��Ϊһ��double���͵����ݡ�
					double xzi = hsshell.getNumericCellValue();
					data[row-1][cell-1] = xzi;
				}
			}
			in.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public List<double[]> read(String path) {
		double[] resultArray = new double[6];
		resultList = new ArrayList<>();
		try {
			//�����ļ����������
			in = new FileInputStream(new File(path));
			//���ļ��ĺ�׺ѡ�����
			String pdwenjian = (String) path.subSequence(path.length()-4, path.length());
			//�жϺ�׺ѡ���Ӧ�Ĵ���ʽ
			if(pdwenjian.equals(".xls"))
			{
				wb = new HSSFWorkbook(in);
			}else if(pdwenjian.equals("xlsx")) 
			{
				wb = new XSSFWorkbook(in);
			}
			System.out.println(wb.getNumberOfSheets());
			for(int hssheet=0;hssheet<wb.getNumberOfSheets();hssheet++) {
				sheet = wb.getSheetAt(hssheet);	
				System.out.println(sheet.getSheetName().subSequence(sheet.getSheetName().indexOf("=")+1, sheet.getSheetName().length()));
				double SheetHead=Double.parseDouble((String)sheet.getSheetName().subSequence(sheet.getSheetName().indexOf("=")+1, sheet.getSheetName().length()));
				if(sheet==null) {
					continue;
				}
				for(int row=1;row<=sheet.getLastRowNum();row++)
				{
					//getRow��x���Ƿ��ص�ǰҳ��x��----����
					hssrow = sheet.getRow(row);
					if(hssrow==null) 
					{
						continue;
					}
					for(int cell=1;cell<hssrow.getLastCellNum();cell++) 
					{
						Cell hsshell;
						Row hangRow = sheet.getRow(row);
						Cell hangCell = hangRow.getCell(0);
						Row lieRow = sheet.getRow(0);
						Cell liecell = lieRow.getCell(cell);
						double RowHead = Double.parseDouble((String) hangCell.getStringCellValue().subSequence(hangCell.getStringCellValue().indexOf("=")+1, hangCell.getStringCellValue().length()));
						double CellHead = Double.parseDouble((String) liecell.getStringCellValue().subSequence(liecell.getStringCellValue().indexOf("=")+1,liecell.getStringCellValue().length()));
						//getCell��x���Ƿ��ص�ǰ�еĵ�x��----����
						hsshell = hssrow.getCell(cell);
						if(hsshell==null) 
						{
							continue;
						}
						String[] newstr = hsshell.getStringCellValue().split("��");
						resultArray[0]=SheetHead;
						resultArray[1]=RowHead;
						resultArray[2]=CellHead;
						for(int i = 0 ;i<newstr.length;i++) {
							resultArray[i+3]=Double.parseDouble(newstr[i]);
						}
						for(Double i:resultArray) {
							System.out.print(i+"  ");
						}
						System.out.println();
						resultList.add(resultArray);
					}
				}
			}
			in.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
		
	}

	public void write(double[][] outdata,String path,String tab) {
		String[] dataFormat = null;
		if(tab.equals("x")) {
			String[] dataFormat2 ={"x/mm","dxx/mm","dyx/mm","dzx/mm","exx/mm","eyx/mm","ezx/mm"};
			dataFormat = dataFormat2;
		}
		if(tab.equals("y")) {
			String[] dataFormat2 = {"y/mm","dxy/mm","dyy/mm","dzy/mm","exy/mm","eyy/mm","ezy/mm"};
			dataFormat = dataFormat2;
		}
		if(tab.equals("z")) {
			String[] dataFormat2 = {"z/mm","dxz/mm","dyz/mm","dzz/mm","exz/mm","eyz/mm","ezz/mm"};
			dataFormat = dataFormat2;
		}
		wb = new XSSFWorkbook();
		CellStyle style =wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		sheet = wb.createSheet();
		for(int i = 0;i<outdata.length;i++) {
			hssrow = sheet.createRow(i+1);
			for(int j = 0; j<outdata[0].length;j++) {
				hsshell = hssrow.createCell(j+1);
				hsshell.setCellValue(outdata[i][j]);
			}
				hsshell = hssrow.createCell(0);
				hsshell.setCellValue(dataFormat[i]);
				hsshell.setCellStyle(style);
		}
		hssrow = sheet.createRow(0);
		for(int i = 0; i<outdata[0].length;i++) {
			hsshell = hssrow.createCell(i+1);
			hsshell.setCellValue(tab+(i+1));
			hsshell.setCellStyle(style);
		}
		try {
			FileOutputStream out = new FileOutputStream(path);
			wb.write(out);
			wb.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(List<double[]> result,String path) {
		DecimalFormat df = new DecimalFormat("#.000");
		wb = new XSSFWorkbook();
		CellStyle style =wb.createCellStyle(); //�½���Ԫ���ʽ��
		style.setAlignment(HorizontalAlignment.CENTER);//���õ�Ԫ�����
		List<Double> XList = new ArrayList<>();
		List<Double> Ylist = null;
		List<Double> Zlist = new ArrayList<>();
		int y=1;
		int z=1;
		for(int i=0;i<result.size();i++) {
			if(!XList.contains(result.get(i)[0])) {
				XList.add(result.get(i)[0]);
				sheet = wb.createSheet("x="+result.get(i)[0]);
				Ylist = new ArrayList<>();
			}else {
				sheet = wb.getSheet("x="+result.get(i)[0]);
			}
			sheet.setDefaultRowHeightInPoints(15);
			sheet.setDefaultColumnWidth(40);
			//�ж�����û�д���
			if(Ylist.contains(result.get(i)[1])) {
				y=Ylist.indexOf(result.get(i)[1]);
				hssrow = sheet.getRow(y+1);
				z++;
			}else {
				hssrow = sheet.createRow(Ylist.size()+1);
				Ylist.add(result.get(i)[1]);
				z=1;
			}
			if(!Zlist.contains(result.get(i)[2])) {
				Zlist.add(result.get(i)[2]);
			}
			hsshell = hssrow.createCell(z);
			hsshell.setCellStyle(style);
			hsshell.setCellValue(df.format(result.get(i)[3])+"��"+df.format(result.get(i)[4])+"��"+df.format(result.get(i)[5]));
			hsshell = hssrow.createCell(0);
			hsshell.setCellStyle(style);
			hsshell.setCellValue("y="+result.get(i)[1]);
		}
		for(int i = 0;i<XList.size();i++) {
			sheet = wb.getSheet("x="+XList.get(i));
			sheet.setColumnWidth(0, 10*256);;
			System.out.println(XList.get(i));
			hssrow = sheet.createRow(0);
			for(int j = 0;j<Zlist.size();j++) {
				hsshell = hssrow.createCell(j+1);
				hsshell.setCellStyle(style);//���õ�Ԫ���ʽ
				hsshell.setCellValue("z="+Zlist.get(j));
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(path);
			wb.write(out);
			wb.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
