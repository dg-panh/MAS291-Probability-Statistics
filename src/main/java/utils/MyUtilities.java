/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.Person;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author Panh
 */
public class MyUtilities {

    public static final int TOTAL_ROW = 3010;

    private static final int COLUMN_INDEX_ID = 0;
    private static final int COLUMN_INDEX_NEARC2 = 1;
    private static final int COLUMN_INDEX_NEARC4 = 2;
    private static final int COLUMN_INDEX_EDUC = 3;
    private static final int COLUMN_INDEX_AGE = 4;
    private static final int COLUMN_INDEX_FATHEDUC = 5;
    private static final int COLUMN_INDEX_MOTHEDUC = 6;
    private static final int COLUMN_INDEX_WEIGHT = 7;
    private static final int COLUMN_INDEX_MOMDAD14 = 8;
    private static final int COLUMN_INDEX_SINMOM14 = 9;
    private static final int COLUMN_INDEX_STEP14 = 10;
    private static final int COLUMN_INDEX_REG661 = 11;
    private static final int COLUMN_INDEX_REG662 = 12;
    private static final int COLUMN_INDEX_REG663 = 13;
    private static final int COLUMN_INDEX_REG664 = 14;
    private static final int COLUMN_INDEX_REG665 = 15;
    private static final int COLUMN_INDEX_REG666 = 16;
    private static final int COLUMN_INDEX_REG667 = 17;
    private static final int COLUMN_INDEX_REG668 = 18;
    private static final int COLUMN_INDEX_REG669 = 19;
    private static final int COLUMN_INDEX_SOUTH66 = 20;
    private static final int COLUMN_INDEX_BLACK = 21;
    private static final int COLUMN_INDEX_SMSA = 22;
    private static final int COLUMN_INDEX_SOUTH = 23;
    private static final int COLUMN_INDEX_SMSA66 = 24;
    private static final int COLUMN_INDEX_WAGE = 25;
    private static final int COLUMN_INDEX_ENROLL = 26;
    private static final int COLUMN_INDEX_KWW = 27;
    private static final int COLUMN_INDEX_IQ = 28;
    private static final int COLUMN_INDEX_MARRIED = 29;
    private static final int COLUMN_INDEX_LIBCRD14 = 30;
    private static final int COLUMN_INDEX_EXPER = 31;
    private static final int COLUMN_INDEX_LWAGE = 32;
    private static final int COLUMN_INDEX_EXPERSQ = 33;

    public static void main(String[] args) {
        List<Person> list = getSubSample("wage.xlsx", 30);
        for (Person person : list) {
            System.out.println(person);
        }
    }

    public static List<Person> getSubSample(String excelFilePath, int amount) {

        List<Person> subSample = new ArrayList<Person>();
        Set<Integer> listRandomIntegers = new HashSet<Integer>();
        Random rand = new Random();

        if (amount == 0) {
            return subSample;
        }

        while (listRandomIntegers.size() < amount) {
            // get random number from 1 -> totalrow
            int rowNumber = rand.nextInt(TOTAL_ROW - 1 + 1) + 1;
            listRandomIntegers.add(rowNumber);
        }

        try {
            InputStream inputStream = new FileInputStream(new File(excelFilePath));
            // Get workbook
            Workbook workbook;
            workbook = getWorkbook(inputStream, excelFilePath);

            // get Sheet
            Sheet sheet = workbook.getSheetAt(0);

            for (int rIndex : listRandomIntegers) {
                Row row = sheet.getRow(rIndex);

                // Get all cells
                Iterator<Cell> cellIterator = row.cellIterator();

                // Read cells and set value for book object
                Person person = new Person();
                while (cellIterator.hasNext()) {
                    // Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().trim().isEmpty()) {
                        continue;
                    }
                    // Set value for book object
                    int columnIndex = cell.getColumnIndex();

                    switch (columnIndex) {
                        case COLUMN_INDEX_ID:
                            person.setId(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_NEARC2:
                            person.setNearc2(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_NEARC4:
                            person.setNearc4(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_EDUC:
                            person.setEduc(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_AGE:
                            person.setAge(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_FATHEDUC:
                            person.setFatheduc(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_MOTHEDUC:
                            person.setMotheduc(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_WEIGHT:
                            person.setWeight(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_MOMDAD14:
                            person.setMomdad14(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_SINMOM14:
                            person.setSinmom14(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_STEP14:
                            person.setStep14(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG661:
                            person.setReg661(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG662:
                            person.setReg662(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG663:
                            person.setReg663(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG664:
                            person.setReg664(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG665:
                            person.setReg665(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG666:
                            person.setReg666(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG667:
                            person.setReg667(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG668:
                            person.setReg668(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_REG669:
                            person.setReg669(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_SOUTH66:
                            person.setSouth66(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_BLACK:
                            person.setBlack(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_SMSA:
                            person.setSmsa(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_SOUTH:
                            person.setSouth(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_SMSA66:
                            person.setSmsa66(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_WAGE:
                            person.setWage(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_ENROLL:
                            person.setEnroll(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_KWW:
                            person.setKww(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_IQ:
                            person.setIq(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_MARRIED:
                            person.setMarried(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_LIBCRD14:
                            person.setLibcrd14(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_EXPER:
                            person.setExper(new BigDecimal((double) cellValue).intValue());
                            break;

                        case COLUMN_INDEX_LWAGE:
                            person.setLwage((double) cellValue);
                            break;

                        case COLUMN_INDEX_EXPERSQ:
                            person.setExpersq(new BigDecimal((double) cellValue).intValue());
                            break;

                        default:
                            break;
                    }

                }

                subSample.add(person);
            }

        } catch (IOException e) {
            subSample = new ArrayList<Person>();
        }
        // Get sheet
        return subSample;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

    public static int countBlack(List<Person> list, int amount) {
        int count = 0;
        Set<Integer> listRandomIntegers = new HashSet<Integer>();
        Random rand = new Random();

        if (amount == 0) {
            return 0;
        }

        while (listRandomIntegers.size() < amount) {
            // get random number from 1 -> totalrow
            int rowNumber = rand.nextInt(TOTAL_ROW - 2 + 1) + 1;
            listRandomIntegers.add(rowNumber);
        }

        for (Integer listRandomInteger : listRandomIntegers) {
            count += list.get(listRandomInteger).getBlack();
        }
        return count;
    }

    public static boolean haveBlack(List<Person> list, int n, int k) {
        Set<Integer> listRandomIntegers = new HashSet<Integer>();
        Random rand = new Random();

        if (k == 0) {
            return false;
        }

        while (listRandomIntegers.size() < k) {
            // get random number from 1 -> totalrow
            int rowNumber = rand.nextInt(n - 2 + 1) + 1;
            listRandomIntegers.add(rowNumber);
        }

        for (Integer listRandomInteger : listRandomIntegers) {
            if (list.get(listRandomInteger).getBlack() == 1) {
                return true;
            }
        }
        return false;
    }

    public static int countBlack(List<Person> list) {
        int count = 0;
        for (Person person : list) {
            count += person.getBlack();
        }
        return count;
    }

    public static int countEduc(List<Person> list) {
        int count = 0;
        for (Person person : list) {
            count += person.getEduc();
        }
        return count;
    }

    public static double getStandardNormalDistribution(double x) {
        double result = 0;
        NormalDistribution N = new NormalDistribution(0, 1);
        result = N.cumulativeProbability(x);
        return result;
    }
}
