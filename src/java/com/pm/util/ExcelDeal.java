package com.pm.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelDeal {
    String path;
    public ExcelDeal(String path){
        this.path = path;
    }

    /**
     * �ж��ļ��Ƿ����
     * @return
     */
    public boolean isExitFile(){
        File file = new File(path);
        if (file.exists()){
            return true;
        }else {
            return false;
        }
    }
    //��ȡָ���ļ����µ������ļ��о���·��
    public List<File> getDirs(){
        List<File> dirs = new ArrayList<File>() ;
        File file=new File(path);
        File[] files=file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()){
                dirs.add(files[i]);
            }
        }
        return dirs;
    }
    //��ȡָ���ļ����µ������ļ�·��
    public List<String> getFilePath(String path,ArrayList<String> filepaths){
//		List<String> filepaths = new ArrayList<String>() ;
        File file=new File(path);
        File[] files=file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                filepaths.add(files[i].getAbsolutePath());
//                System.out.println(files[i].getAbsolutePath());
            }else if (files[i].isDirectory()) {
//                System.out.println("���ļ���:"+files[i].getAbsolutePath());
                getFilePath(files[i].getAbsolutePath(),filepaths);
            }
        }
        System.out.println("====="+filepaths.size());
        return filepaths;
    }
    //�����ļ�
    public void createXlsx() throws Exception{
        XSSFWorkbook xf=new XSSFWorkbook();
        XSSFSheet sheet=xf.createSheet();
        FileOutputStream out=new FileOutputStream(path);
        xf.write(out);
        out.close();
    }

    /**��ȡ�ļ��ĵڶ��У�����list
     * @return
     * @throws Exception
     */
    public List<String> getValue() throws Exception{
        List<String> value = new ArrayList<String>();
        InputStream is=new FileInputStream(path);
        POIFSFileSystem fs=new POIFSFileSystem(is);
        HSSFWorkbook ws=new HSSFWorkbook(fs);
        HSSFSheet sheet=ws.getSheetAt(0);
        if (sheet == null) {
            return value;
        }
        for (int hang = 0; hang <= sheet.getLastRowNum(); hang++) {
            HSSFRow row=sheet.getRow(hang);
            System.out.println("hang:"+hang);
            try{
                HSSFCell cell = row.getCell((short)1);
                if (cell == null){
                    value.add("");
                    continue;
                }
                System.out.println(cell);
                value.add(String.valueOf(cell));
            }catch (Exception e){
                System.out.println("1111111111111");
                value.add("");
            }
        }
        return value;
    }

    /**
     * ��valueд��ָ���ļ���ָ����Ԫ��,׷��д��
     * @param hang ָ����
     * @param lie ָ����
     * @param value �ַ���
     */
    public void writeToXLSX(int hang, int lie, String value) throws Exception{
        FileInputStream isFileInputStream=new FileInputStream(path);

        XSSFWorkbook workbook=new XSSFWorkbook(isFileInputStream);
        XSSFSheet sheet=workbook.getSheetAt(0);//��һ��sheet
        XSSFRow row=sheet.getRow(hang);
        if (row == null) {
            row = sheet.createRow(hang);
        }
        XSSFCell cell=row.createCell((short)lie);
        cell.setCellValue(value);
        FileOutputStream fileout=new FileOutputStream(path);
        fileout.flush();
        workbook.write(fileout);
        fileout.close();
        isFileInputStream.close();
    }
    public void writeToXLSX(int hang, int lie, int value) throws Exception{
        FileInputStream isFileInputStream=new FileInputStream(path);

        XSSFWorkbook workbook=new XSSFWorkbook(isFileInputStream);
        XSSFSheet sheet=workbook.getSheetAt(0);//��һ��sheet
        XSSFRow row=sheet.getRow(hang);
        if (row == null) {
            row = sheet.createRow(hang);
        }
        XSSFCell cell=row.createCell((short)lie);
        cell.setCellValue(value);
        FileOutputStream fileout=new FileOutputStream(path);
        fileout.flush();
        workbook.write(fileout);
        fileout.close();
        isFileInputStream.close();
    }
    public void writeToXLSX(int hang, int lie, double value) throws Exception{
        FileInputStream isFileInputStream=new FileInputStream(path);

        XSSFWorkbook workbook=new XSSFWorkbook(isFileInputStream);
        XSSFSheet sheet=workbook.getSheetAt(0);//��һ��sheet
        XSSFRow row=sheet.getRow(hang);
        if (row == null) {
            row = sheet.createRow(hang);
        }
        XSSFCell cell=row.createCell((short)lie);
        cell.setCellValue(value);
        FileOutputStream fileout=new FileOutputStream(path);
        fileout.flush();
        workbook.write(fileout);
        fileout.close();
        isFileInputStream.close();
    }
    /**
     * ��listд��xlsx�ĵ�lie��
     * XSSF xlsx
     * @param value list<integer>
     * @param lie ��
     * @throws Exception
     */
    public void writeListToXLSX_XSSF(List<Double> value, int lie) throws Exception{
        FileInputStream isFileInputStream=new FileInputStream(path);

        XSSFWorkbook workbook=new XSSFWorkbook(isFileInputStream);
        XSSFSheet sheet=workbook.getSheetAt(0);
        for (int j = 0; j < value.size(); j++) {
            int hang=j+1;
            XSSFRow row=sheet.getRow(hang);//getrow����Ϊnull
            if (row == null) {
                row = sheet.createRow(hang);
            }
            XSSFCell cell=row.createCell((short)lie);
            cell.setCellValue(value.get(j));
        }
        FileOutputStream fileout=new FileOutputStream(path);
        fileout.flush();
        workbook.write(fileout);
        System.out.println("д��ɹ�:"+lie);

        fileout.close();
        isFileInputStream.close();
    }

    /**��listд���ļ��ĵ�lie��
     * HSSF xls
     * @param value
     * @param lie
     * @throws Exception
     */
    public void writeListToXLSX_HSSF(List<String> value, int lie) throws Exception{
        FileInputStream isFileInputStream=new FileInputStream(path);
        HSSFWorkbook workbook=new HSSFWorkbook(isFileInputStream);
        HSSFSheet sheet=workbook.getSheetAt(0);
        for (int j = 0; j < value.size(); j++) {
            HSSFRow row=sheet.getRow(j);
            if (row == null){
                row=sheet.createRow(j);
            }
            HSSFCell cell=row.createCell((short)lie);
            cell.setCellValue(value.get(j));
        }
        FileOutputStream fileout=new FileOutputStream(path);
        fileout.flush();
        workbook.write(fileout);
        System.out.println("д��ɹ�:"+lie);
        fileout.close();
        isFileInputStream.close();
    }
    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * ��������ͼ
     * @param xCol ���������
     * @param yCol ���������
     * @param startCol ����ͼλ��
     * @param startRow ����ͼλ��
     */
    public void xchart(String xchartName,int xCol, int yCol, int startCol, int startRow){
        try {
            FileInputStream isFileInputStream = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(isFileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, startCol, startRow, startCol+15, startRow+20);
            XSSFChart chart = drawing.createChart(anchor);
            XSSFChartLegend legend = chart.getOrCreateLegend();
            legend.setPosition(LegendPosition.BOTTOM);
            LineChartData data = chart.getChartDataFactory().createLineChartData();
            ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setCrosses(AxisCrosses.AUTO_ZERO);
            bottomAxis.setMajorTickMark(AxisTickMark.NONE);//ȡ��X��ı�̶�
            ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
            ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, sheet.getLastRowNum(), xCol, xCol));
            ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, sheet.getLastRowNum(), yCol, yCol));

            LineChartSeries series = data.addSeries(xs, ys);
            series.setTitle(xchartName);//������������
            chart.setTitleText(xchartName);//����ͼ�����
            chart.plot(data, new ChartAxis[] { bottomAxis, leftAxis });

            FileOutputStream fileout=new FileOutputStream(path);
            workbook.write(fileout);
            isFileInputStream.close();
            fileout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//        // TODO Auto-generated method stub
//        String path="D:\\per.xlsx";
//        ExcelDeal excelDeal = new ExcelDeal(path);
//        excelDeal.createXlsx("D:\\12.xlsx");
//        System.out.println(excelDeal.getStringDate());
//        for (int i=0;i<20;i++){
//            excelDeal.writeToXLSX(i, 0, excelDeal.getStringDate());
//            excelDeal.writeToXLSX(i, 1, i);
//            Thread.sleep(500);
//        }
//        excelDeal.xchart();
//    }
}
