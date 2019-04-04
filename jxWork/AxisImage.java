package control;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import jxWork.JavaLeastSquare;
 
public class AxisImage {
 
    private static int MARGIN = 10;
    private static int AXIS_TEXT_INTERVAL = 3;
    private static Font FONT_TITLE = new Font("Dialog", Font.BOLD, 20);
    private static Font FONT_DEFAULT = new Font("Dialog", Font.PLAIN, 12);
    private static Color COLOR_TITLE = Color.BLACK;
    private static Color COLOR_AXIS = Color.LIGHT_GRAY;
    private static Color COLOR_LINE = Color.BLACK;
    private static Color COLOR_POINT = Color.BLUE;
    private static int AXIS_HEIGHT = 8;
    private static DecimalFormat format = new DecimalFormat("0.##");
    private int width = 700;
    private int height = 444;
    //--------------
    private int leftBorderWidth, rightBorderWidth, topBorderHeight, bottomBorderHeight;
    private int yAxisLength, xAxisLength;
    private double xMin, xMax, xInterval;
    private double yMin, yMax, yInterval;
    private int xSecant = 8, ySecant = 8;
    //--------------
    private String imageTitle = " ";
    private String xTitle = "X���λ��", yTitle = "���/um";
    private String filePath;
    private ArrayList<Double> dataX, dataY;
 
    public AxisImage(String filePath, double[] x, double[] jg) {
        this.filePath = filePath;
        dataX = new ArrayList<Double>();
        dataY = new ArrayList<Double>();
        for(int i = 0;i<x.length;i++) {
	    	dataX.add(x[i]);
	    	dataY.add(jg[i]);
        }
//        initData();
    }
 
    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }
 
    //��ʼ������
    private void initData() {
    	double[] x = {-800,-750,-700,-650,-600,-550,-500,-450,-400,-350,-300,-250,-200,-150,-100,-50,0};
    	double[] y = {-8.9,-8.1,-7.4,-6.9,-6.8,-7.5,-7.7,-8.5,-8.9,-9.1,-9.6,-11.2,-8.8,-7.4,-6.7,-6.7,-5.5};
    	double[] jg = new double[x.length];
	   	  JavaLeastSquare eastSquareMethod = new JavaLeastSquare(x,y,5);
	   	  for(int i = 0;i<y.length;i++) {
	   		  jg[i]=eastSquareMethod.fit(x[i]);
	   	  }
        dataX = new ArrayList<Double>();
        dataY = new ArrayList<Double>();
        for(int i = 0;i<x.length;i++) {
        	dataX.add(x[i]);
        	dataY.add(jg[i]);
        }
    }
 
    public String generate() {
    	//����ͼ�񻺳�
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //����Graphics2D
        Graphics2D g = (Graphics2D) image.getGraphics();
        paintImage(g);
        return saveFile(filePath, image);
    }
 
    private void paintImage(Graphics2D g) {
    	//����ͼƬ��Ⱦ�㷨
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //��䱳��
        g.setColor(Color.WHITE);
        //����ڲ�
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        //����xy����������Сֵ
        calculateMaxMin();
        //���㲢У�����������ʼֵ����ָ�
        adjustMaxMin();
 
        //���������ἰ�߽緶Χ
        calculateAxisLength();
 
        drawImageTitle(g);
        drawXTitle(g);
        drawYTitle(g);
        drawXAxis(g);
        drawYAxis(g);
 
        drawPoints(g);
    }
 
    private void drawImageTitle(Graphics2D g0) {
        if (isEmpty(imageTitle)) {
            return;
        }
        Graphics2D g = (Graphics2D) g0.create();
        g.setColor(COLOR_TITLE);
        g.setFont(FONT_TITLE);
 
        TextLayout layout = new TextLayout(imageTitle, g.getFont(), g.getFontRenderContext());
        int x = (int) ((width - layout.getBounds().getWidth()) / 2);
        int y = MARGIN + (int) layout.getAscent();
        g.drawString(imageTitle, x, y);
        g.dispose();
    }
 
    private void drawXTitle(Graphics2D g0) {
        if (isEmpty(xTitle)) {
            return;
        }
        Graphics2D gx = (Graphics2D) g0.create();
        gx.setColor(COLOR_TITLE);
        gx.setFont(FONT_TITLE);
 
        TextLayout layout = new TextLayout(xTitle, gx.getFont(), gx.getFontRenderContext());
        int x = (int) (leftBorderWidth + (xAxisLength - layout.getBounds().getWidth()) / 2);
        int y = topBorderHeight + yAxisLength + bottomBorderHeight - MARGIN - (int) layout.getDescent();
        gx.drawString(xTitle, x, y);
        gx.dispose();
    }
 
    private void drawYTitle(Graphics2D g0) {
        if (yTitle == null || yTitle.trim().equals("")) {
            return;
        }
 
        Graphics2D gy = (Graphics2D) g0.create();
        gy.setFont(FONT_TITLE);
        gy.setColor(COLOR_TITLE);
 
 
        AffineTransform at = new AffineTransform();
        at.setToRotation((Math.PI) / 2.0 * 3.0);
        gy.transform(at);
 
        TextLayout layout = new TextLayout(yTitle, gy.getFont(), gy.getFontRenderContext());
        int x = (int) -(topBorderHeight + (yAxisLength + layout.getBounds().getWidth()) / 2);
        int y = MARGIN + (int) layout.getAscent();
        gy.drawString(yTitle, x, y);
        gy.dispose();
    }
 
    private void drawXAxis(Graphics2D g0) {
        Graphics2D gx = (Graphics2D) g0.create();
 
        //����ÿ��������ָ���֮��ļ��
        int xInterLen = xAxisLength / xSecant;
 
        for (int i = 0; i < xSecant + 1; i++) {
            //��X������һ��һ��С�ķָ���
            gx.setColor(COLOR_AXIS);
            gx.drawLine(leftBorderWidth + i * xInterLen,
                    topBorderHeight + yAxisLength,
                    leftBorderWidth + i * xInterLen,
                    topBorderHeight + yAxisLength + AXIS_HEIGHT);
 
            //��һ��һ��С�ķָ�������ʾ��ֵ
            gx.setColor(COLOR_TITLE);
            String axisText = format.format(xMin + i * xInterval);
            TextLayout layout = new TextLayout(axisText, gx.getFont(), new FontRenderContext(null, false, false));
            gx.drawString(axisText,
                    leftBorderWidth + i * xInterLen - (int) layout.getBounds().getWidth() / 2,
                    topBorderHeight + yAxisLength + AXIS_HEIGHT + layout.getAscent() + AXIS_TEXT_INTERVAL);
        }
 
        //��X�᷽��������������
        gx.setColor(COLOR_LINE);
        gx.drawLine(leftBorderWidth, topBorderHeight, leftBorderWidth + xAxisLength, topBorderHeight);
        gx.drawLine(leftBorderWidth, topBorderHeight + yAxisLength, leftBorderWidth + xAxisLength, topBorderHeight + yAxisLength);
        gx.dispose();
    }
 
    private void drawYAxis(Graphics2D g0) {
        Graphics2D gy = (Graphics2D) g0.create();
        //����ÿ��������ָ���֮��ļ��
        int yInterLen = yAxisLength / ySecant;
 
        for (int i = 0; i < ySecant + 1; i++) {
            //��Y������һ��һ��С�ķָ���
            gy.setColor(COLOR_AXIS);
            gy.drawLine(leftBorderWidth - AXIS_HEIGHT,
                    (int) (topBorderHeight + yAxisLength - i * yInterLen),
                    leftBorderWidth,
                    (int) (topBorderHeight + yAxisLength - i * yInterLen));
            //��Y�������������м��ˮƽ�ķָ���
            if (i != 0 && i != ySecant) {
                gy.drawLine(leftBorderWidth,
                (int) (topBorderHeight + yAxisLength - i * yInterLen),
                leftBorderWidth + xAxisLength,
                (int) (topBorderHeight + yAxisLength - i * yInterLen));
            }
            //��һ��һ��С�ķָ�������ʾ��ֵ
            gy.setColor(COLOR_TITLE);
            gy.setFont(FONT_DEFAULT);
            String axisText = format.format(yMax - i * yInterval);
            TextLayout layout = new TextLayout(axisText, gy.getFont(), new FontRenderContext(null, false, false));
            gy.drawString(axisText,
                    leftBorderWidth - AXIS_HEIGHT - AXIS_TEXT_INTERVAL - (int) layout.getBounds().getWidth(),
                    topBorderHeight + i * yInterLen + (int) layout.getDescent());
        }
        //��Y�᷽��������������
        gy.setColor(COLOR_LINE);
        gy.drawLine(leftBorderWidth, topBorderHeight, leftBorderWidth, topBorderHeight + yAxisLength);
        gy.drawLine(leftBorderWidth + xAxisLength, topBorderHeight,
                leftBorderWidth + xAxisLength, topBorderHeight + yAxisLength);
        gy.dispose();
    }
 
    private void drawPoints(Graphics2D g0) {
        Graphics2D g = (Graphics2D) g0.create();
        double xScope = xMax - xMin;
        double yScope = yMax - yMin;
 
        int prevX = -1;
        int prevY = -1;
        g.setColor(COLOR_POINT);
        for (int i = 0; i < dataX.size(); i++) {
            Double xValue = dataX.get(i);
            Double yValue = dataY.get(i);
 
            if (xValue > xMax || xValue < xMin
                    || yValue > yMax || yValue < yMin) {
                continue;
            }
 
            int x = leftBorderWidth + (int) ((xValue - xMin) / xScope * xAxisLength);
            int y = topBorderHeight + yAxisLength - (int) ((yValue - yMin) / yScope * yAxisLength);
            g.fillOval(x - 5, y - 5, 10, 10);
 
            if (i != 0) {
                g.drawLine(x, y, prevX, prevY);
            }
            prevX = x;
            prevY = y;
        }
    }
 
    private String saveFile(String filePath, BufferedImage image) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filePath));
            ImageIO.write(image, "jpg", fos);
            fos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return filePath;
    }
 
    //����xy�������ֵ����Сֵ
    private void calculateMaxMin() {
        int length = dataX.size();
        if (length != 0) {
            xMin = dataX.get(0);
            xMax = dataX.get(0);
            yMin = dataY.get(0);
            yMax = dataY.get(0);
 
            for (int i = 0; i < length; i++) {
                double xValue = dataX.get(i);
                xMin = Math.min(xMin, xValue);
                xMax = Math.max(xMax, xValue);
 
                double yValue = dataY.get(i);
                yMin = Math.min(yMin, yValue);
                yMax = Math.max(yMax, yValue);
            }
        }
    }
 
    private void adjustMaxMin() {
        AxisImage.AxisAdjust adjustX = new AxisImage.AxisAdjust(xMax, xMin, xSecant);
        xMax = adjustX.getMax();
        xMin = adjustX.getMin();
        xInterval = adjustX.getInterval();
        xSecant = adjustX.getSecant();
        AxisImage.AxisAdjust adjustY = new AxisImage.AxisAdjust(yMax, yMin, ySecant);
        yMax = adjustY.getMax();
        yMin = adjustY.getMin();
        yInterval = adjustY.getInterval();
        ySecant = adjustY.getSecant();
    }
 
    private void calculateAxisLength() {
        //��������߽���������ľ���
        //���ܿհ�+���������߶ȣ�����������ͼƬ����߶�һ��
        TextLayout layout1 = new TextLayout(xTitle, FONT_TITLE, new FontRenderContext(null, false, false));
        int borderWidth1 = (int) Math.ceil(layout1.getAscent() + layout1.getDescent()) + MARGIN * 2;
        leftBorderWidth = borderWidth1;
        rightBorderWidth = borderWidth1;
        topBorderHeight = borderWidth1;
        bottomBorderHeight = borderWidth1;
 
        //�·��ټ����������ϵĸ�С�ָ�����ֵ�ĸ߶�
        TextLayout layout2 = new TextLayout("1980", FONT_DEFAULT, new FontRenderContext(null, false, false));
        int borderWidth2 = (int) Math.ceil(layout2.getAscent() + layout2.getDescent()) + AXIS_TEXT_INTERVAL * 2;
        bottomBorderHeight += borderWidth2;
 
 
        //��Ҫ���ϸ������ֵ������
        //�ҵ��������֣�Ȼ�������߶�
        String maxStr = format.format(yMin);
        for (int i = 1; i < ySecant + 1; i++) {
            String str = format.format(yMin + i * yInterval);
            if (str.length() > maxStr.length()) {
                maxStr = str;
            }
        }
        TextLayout layout3 = new TextLayout(maxStr, FONT_DEFAULT, new FontRenderContext(null, false, false));
        int borderWidth3 = (int) Math.ceil(layout3.getBounds().getWidth()) + AXIS_TEXT_INTERVAL * 2;
        leftBorderWidth += borderWidth3;
 
 
        //��������������ĳ���
        yAxisLength = height - topBorderHeight - bottomBorderHeight;
        xAxisLength = width - leftBorderWidth - rightBorderWidth;
 
        //��������ͱ߽����ֽ���У��
        int lessX = xAxisLength % xSecant;
        xAxisLength -= lessX;
        bottomBorderHeight += lessX;
 
        int lessY = yAxisLength % ySecant;
        yAxisLength -= lessY;
        rightBorderWidth += lessY;
    }
 
    public static void main(String[] args) {
    	//������
//    	1
    	double[] x = {-800,-750,-700,-650,-600,-550,-500,-450,-400,-350,-300,-250,-200,-150,-100,-50,0};
    	double[] y = {-8.9,-8.1,-7.4,-6.9,-6.8,-7.5,-7.7,-8.5,-8.9,-9.1,-9.6,-11.2,-8.8,-7.4,-6.7,-6.7,-5.5};
    	double[] jg = new double[x.length];
	   	JavaLeastSquare eastSquareMethod = new JavaLeastSquare(x,y,5);
	   	for(int i = 0;i<y.length;i++) {
	   		jg[i]=eastSquareMethod.fit(x[i]);
	   	}
	   	System.out.println(jg.length);
        AxisImage axisImage = new AxisImage("C:\\Users\\yao\\Desktop\\21.jpg", x, jg);
        System.out.println(axisImage.generate());
    }
 
    private class AxisAdjust {
 
        private double max;
        private double min;
        private int secant;
        private double interval;
 
        public AxisAdjust(double max, double min, int groupNo) {
            this.max = max;
            this.min = min;
            this.secant = groupNo;
            adjust();
        }
 
        public double getInterval() {
            return interval;
        }
 
        public double getMax() {
            return max;
        }
 
        public double getMin() {
            return min;
        }
 
        public int getSecant() {
            return secant;
        }
 
        private void adjust2() {
            //1 ��������С
            interval = (max - min) / secant;
            //2 ������10�Ķ��ٴη���ȡ������ͬʱ�����10�ĵ�ǰ�η���һ����Ϊ��׼ֵ����У��
            double m10 = Math.floor(Math.log10(interval));
            double standardValue = 5 * Math.pow(10, m10 - 1);
            //3 ���ֵȡ�����λʣ�µ�����
            double yInterval1 = interval % Math.pow(10, Math.floor(m10));
            //4 ��interval��ֵ����У��
            //����m10=2����ôstandardValue = 50
            //����������
            //(a)������50ʱ����50-100�����������루75�����ᵽ50��75���������뵽100��
            //(b)��С��50�ǣ���0-50�����������루25�����ᵽ0��25���������뵽50��
            //(c)ͬʱ���������ȥ��secant�������1
            double d;
            if (yInterval1 > standardValue) {
//            System.out.println(">");
                d = Math.round((yInterval1 - standardValue) * 2 / Math.pow(10, m10));
                if (d == 0) {
                    secant += 1;
                    interval = (Math.floor(interval / Math.pow(10, Math.floor(m10))) + 0.5) * Math.pow(10, m10);
                } else {
                    interval = (Math.floor(interval / Math.pow(10, Math.floor(m10))) + 1) * Math.pow(10, m10);
                }
            } else {
                d = Math.round(yInterval1 * 2 / Math.pow(10, m10));
                if (d == 0) {
                    secant += 1;
                    interval = Math.floor(interval / Math.pow(10, m10)) * Math.pow(10, m10);
                } else {
                    interval = (Math.floor(interval / Math.pow(10, m10)) + 0.5) * Math.pow(10, m10);
                }
            }
            System.out.println("interval = " + interval);
            max = min + interval * secant;
            System.out.println("max = " + max + ", min = " + min);
        }
 
        private void adjust() {
            //1 �������������ֶ��ٸ����䣬����������10������ȡһ��
            double scope = max - min;
            secant = (int) (scope / (5 * Math.pow(10, Math.floor(Math.log10(scope)) - 1)));
            if (secant > 10) {
                secant = (secant + 1) / 2;
            }
 
            //2 ��������С
            interval = (max - min) / secant;
            //3 ������10�Ķ��ٴη���ȡ������ͬʱ�����10�ĵ�ǰ�η���һ����Ϊ��׼ֵ����У��
            double m10 = Math.floor(Math.log10(interval));
            double standardValue = 5 * Math.pow(10, m10 - 1);
            //4 ���ֵȡ�����λʣ�µ�����
            double yInterval1 = 0;
            if (m10 > 0) {
                yInterval1 = interval % Math.pow(10, Math.floor(m10));
            } else {
                double temp = Math.pow(10, -m10);
                yInterval1 = (interval * temp) % (Math.pow(10, Math.floor(m10)) * temp) / temp;
            }
            //5 ��interval��ֵ����У��
            //����m10=2����ôstandardValue = 50
            //����������
            //(a)������50ʱ����50-100�����������루75�����ᵽ50��75���������뵽100��
            //(b)��С��50�ǣ���0-50�����������루25�����ᵽ0��25���������뵽50��
            //(c)ͬʱ���������ȥ��secant�������1
            double d = 0;
            if (yInterval1 == 0 || yInterval1 == standardValue) {
            } else if (yInterval1 > standardValue) {
                d = Math.round((yInterval1 - standardValue) * 2 / Math.pow(10, m10));
                if (d == 0) {
                    secant++;
                    interval = (Math.floor(interval / Math.pow(10, Math.floor(m10))) + 0.5) * Math.pow(10, m10);
                } else {
                    interval = (Math.floor(interval / Math.pow(10, Math.floor(m10))) + 1) * Math.pow(10, m10);
                }
            } else {
                d = Math.round(yInterval1 * 2 / Math.pow(10, m10));
                if (d == 0) {
                    secant++;
                    interval = Math.floor(interval / Math.pow(10, m10)) * Math.pow(10, m10);
                } else {
                    interval = (Math.floor(interval / Math.pow(10, m10)) + 0.5) * Math.pow(10, m10);
                }
            }
            //6 �����¼����interval����У��min��max��ֵ
            //  ͬʱ��Ҫ��֤�����ּ�������Ƿ��ܽ�ԭʼ����ȫ�����������������һ���������
            min = Math.floor(min / interval) * interval;
            max = Math.ceil(max / interval) * interval;
            if (interval * secant < scope) {
                secant++;
            }
        }
    }
}
