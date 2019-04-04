package model;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


/**
 * ��������ģ����
 * @author yao
 *
 */
public class tableModel extends AbstractTableModel    
{
//	   private mytableEvent mytbE;
	
	//����
	String[] ab = new String[30] ;
	//��¼���ݵ�����
	Object[][] data = new Object[7][30] ;
	
//	public Object[][] getData() {
//		return data;
//	}
	public void dataChange(Object[][] x) {
		for(int i=0;i<x.length;i++) {
			for(int j=0;j<x[i].length;j++) 
			{
				try {
					this.data[i][j+1]=x[i][j];
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO: handle exception
				}
					
			}
		}
	}

	/**
	 * ���캯����ͨ�����캯�������������������֣�������һ��������ʾ�ڱ����
	 * 
	 */
	public tableModel(String tab,Object[][] x) 
	{
		if(x!=null) {
			dataChange(x);
		}
		
		ab[0] = null;
		switch (tab) 
		{
			case "x":
				data[0][0]="x/mm";
				data[1][0]="dxx/mm";
				data[2][0]="dyx/mm";
				data[3][0]="dzx/mm";
				data[4][0]="exx/mm";
				data[5][0]="eyx/mm";
				data[6][0]="ezx/mm";
				for (int i=1;i<ab.length;i++) {
					ab[i] = "x"+i;
				}
				break;
			case "y":
				data[0][0]="y/mm";
				data[1][0]="dxy/mm";
				data[2][0]="dyy/mm";
				data[3][0]="dzy/mm";
				data[4][0]="exy/mm";
				data[5][0]="eyy/mm";
				data[6][0]="ezy/mm";
				for (int i=1;i<ab.length;i++) {
					ab[i] = "y"+i;
				}
				break;
			case "z":
				data[0][0]="z/mm";
				data[1][0]="dxz/mm";
				data[2][0]="dyz/mm";
				data[3][0]="dzz/mm";
				data[4][0]="exz/mm";
				data[5][0]="eyz/mm";
				data[6][0]="ezz/mm";
				for (int i=1;i<ab.length;i++) {
					ab[i] = "z"+i;
				}
				break;
		}
	}
	/**
	 * ѡ����洢�����ݽṹ
	 */
	 public String getColumnName(int column)  
     {  
            return ab[column];  
     }
	 
	 /**
	  * ���ñ�������
	  */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	
	/**
	 * ���ñ�������
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 30;
	}
	
	/** 
              * �õ���������Ӧ���� 
     */  
    @Override  
    public Object getValueAt(int rowIndex, int columnIndex)  
    {  
        return data[rowIndex][columnIndex];  
    }  
	
    /**
     * ����һ������Ϊ���ɱ༭
     */
	public boolean isCellEditable(int rowIndex, int columnIndex)  
    {  
        if (columnIndex < 1)  
            return false;  
        else  
            return true;  
    } 
	
	 /** 
     * ������ݵ�ԪΪ�ɱ༭���򽫱༭���ֵ�滻ԭ����ֵ 
     */  
    @Override  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)  
    {  
        data[rowIndex][columnIndex] = aValue;
        System.out.println("aValue:"+aValue+"  rowIndex:"+rowIndex+"  columnIndex:"+columnIndex);
        /*֪ͨ���������ݵ�Ԫ�����Ѿ��ı�*/  
        fireTableCellUpdated(rowIndex, columnIndex);  
        for(int i = 0;i<data.length;i++) 
		{
			for(int j = 0;j<data[i].length;j++) 
			{
				System.out.print(data[i][j]+"  ");
			}
			System.out.println();
		}
    }
    
	public double[][] zhuanhuan() 
	{
		int leng = 0;
		outer:for(int i = 0;i<7;i++) 
		{
			for(int j = 1;j<30;j++) 
			{
				if(data[i][j]==null) 
				{
					if(j<leng||leng==0) {
						leng = j;
					}
					break;
				}
			}
		}
		System.out.println(leng);
		double[][] jieg = new double[7][leng-1];
		for(int i = 0;i<7;i++) 
		{
			for(int j = 0,k=1;j<leng-1;j++) 
			{
				jieg[i][j]=Double.parseDouble(data[i][k].toString()) ;
				k++;
			}
		}
		return jieg;
	}
}
