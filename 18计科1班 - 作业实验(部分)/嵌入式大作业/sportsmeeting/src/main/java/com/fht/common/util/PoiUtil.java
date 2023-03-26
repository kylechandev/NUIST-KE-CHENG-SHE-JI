package com.fht.common.util;

import com.fht.entity.Competition;
import com.fht.entity.User;
import com.fht.entity.UserCompetition;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class PoiUtil {
    //文件路径
    public static String path = System.getProperty("user.dir");

    public static String createScoreExcel(List<Competition> lists) throws IOException {
        Date startTime = new Date();
        // 1.创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2.创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //3.创建样式对象
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex()); // 是设置前景色不是背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER); // 居中
        //获取行列数量
        int rowNumerTotal = 0;
        for (Competition item : lists) {
            rowNumerTotal += item.getUserCompetition().size() + 2;
        }
        System.out.println(rowNumerTotal);
        //设置数据
        int row = 0;
        for (int i = 0; i < lists.size(); i++) {
            HSSFRow sheetRow0 = sheet.createRow(row);
            String title = lists.get(i).getName() + "(" + lists.get(i).getKind() + ")";
            //1.创建比赛标题
            HSSFCell cell = sheetRow0.createCell(0);
            cell.setCellValue(title);
            cell.setCellStyle(style);
            //创建合并单元格区域
            CellRangeAddress cra = new CellRangeAddress(row, row, 0, 5);
            //在sheet里增加合并单元格
            sheet.addMergedRegion(cra);
            //2.创建比赛表头
            String[] titleHead = {"比赛名称", "班级", "人员", "成绩", "最终得分"};
            HSSFRow sheetRow1 = sheet.createRow(row + 1);
            for (int r = 0; r < titleHead.length; r++) {
                sheetRow1.createCell(r).setCellValue(titleHead[r]);
            }
            row += 1;
            //3.填充数据
            for (int index = 0; index < lists.get(i).getUserCompetition().size(); index++) {
                HSSFRow sheetRow = sheet.createRow(row + 1);
                UserCompetition userCompetition = lists.get(i).getUserCompetition().get(index);
                sheetRow.createCell(0).setCellValue(title);
                sheetRow.createCell(1).setCellValue(userCompetition.getTeam());
                sheetRow.createCell(2).setCellValue(userCompetition.getRealname());
                sheetRow.createCell(3).setCellValue(userCompetition.getGrade()==null? "0" : userCompetition.getGrade().toString());
                sheetRow.createCell(4).setCellValue(userCompetition.getScore()==null? "0" : userCompetition.getScore().toString());
                row += 1;
            }
            //4.创建一行空格分隔
            HSSFRow sheetRow2 = sheet.createRow(row + 1);
            row += 2;
        }
        //写入文件
        String fileName = "\\比赛成绩表" + System.currentTimeMillis() + ".xls";
        String filePath = path + File.separator + fileName;
        try {
            workbook.write(new FileOutputStream(new File(filePath)));
            workbook.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Date endTime = new Date();
        System.out.println("Cost time(ms): " + (endTime.getTime() - startTime.getTime()));
        return filePath;
    }

    public static List<User> parseUserExcel(String path) throws FileNotFoundException {
        List<User> data = new ArrayList<>();
        File file = new File(path);
        InputStream excel = new BufferedInputStream(new FileInputStream(file));
        try {
            InputStream inputStream = excel;
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();// 行
            int cellNum;
            HSSFRow row;
            HSSFCell cell;
            String value = "";
            for (int i = 2; i <= rowNum; i++) {
                StringBuffer recStrb = new StringBuffer("");
                row = sheet.getRow(i);
                cellNum = row.getLastCellNum();// 列
                for (int j = 0; j < cellNum; j++) {//对一行的每个列进行解析
                    cell = row.getCell((short) j);
                    //设置单元格类型
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        value = cell.getStringCellValue();//字符型的值
                    } else {
                        recStrb.append("");//对取得的值进行处理
                    }
                    if (StringUtils.isEmpty(value)) {
                        recStrb.append("null,");
                    } else {
                        recStrb.append(value + ",");//对取得的值进行处理
                    }
                }
                //数据的自定义处置
                if (i > 1) {
                    User user = new User();
//                    recStrb.append("'" + "11" + "'"," ");
                    String strTemp = recStrb.toString();
                    strTemp = strTemp.substring(0, strTemp.lastIndexOf(","));
                    String[] strArr = strTemp.trim().split(",");//注意分隔符是需要转译
                    String[] title = {"department", "team", "username", "realname", "sex", "email", "phone", "startSchoolYear", "leaveSchoolYear"};
                    for (int k = 0; k < title.length; k++) {
                        Class<?> classuser = user.getClass();
                        Field field = classuser.getDeclaredField(title[k]);
                        field.setAccessible(true);  //暴力反射  获取私有属性
                        if (title[k] == "sex") {
                            Map sex = new HashMap();
                            sex.put("男", 0);
                            sex.put("女", 1);
                            field.set(user, sex.get(strArr[k]));
                        } else
                            field.set(user, strArr[k]);
                    }
                    data.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> classuser = Class.forName("com.fht.entity.User");
        Object user = classuser.newInstance();
        Field team = classuser.getDeclaredField("team");
        team.setAccessible(true);  //暴力反射  获取私有属性
        team.set(user, "1111");
        System.out.println(team.getType().getSimpleName());
        System.out.println(((User) user).getTeam());
    }
}
