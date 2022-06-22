package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Validation {

    private static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String inputMsg, String errorMsg) { //input 1 + dau cach van gap loi -> fix
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;

        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg, double lowerBound) {
        double n;

        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getID(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static String getTwoOption(String inputMsg, String errorMsg, String option_1, String option_2) {
        String choice;
        String op_1 = option_1.toLowerCase();
        String op_2 = option_2.toLowerCase();

        while (true) {
            System.out.print(inputMsg);
            choice = sc.nextLine().trim().toLowerCase();
            if (choice.isEmpty() || (choice.matches(op_1) == false && choice.matches(op_2) == false)) {
                System.out.println(errorMsg);
            } else {
                return choice;
            }
        }
    }

    public static String getThreeOption(String inputMsg, String errorMsg, String option_1, String option_2, String option_3) {
        String choice;
        String op_1 = option_1.toLowerCase();
        String op_2 = option_2.toLowerCase();
        String op_3 = option_3.toLowerCase();

        while (true) {
            System.out.print(inputMsg);
            choice = sc.nextLine().trim().toLowerCase();
            if (choice.isEmpty() || choice.matches(op_1) == false || choice.matches(op_2) == false || choice.matches(op_3) == false) {
                System.out.println(errorMsg);
            } else {
                return choice;
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        String s;
        int tmp;

        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            System.out.print(inputMsg);
            s = sc.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println(errorMsg);
            } else if (s.length() < lowerBound || s.length() > upperBound) {
                System.out.println("The maximum length of the character string must be " + upperBound + " characters. Please input  another string!");
            } else {
                return s;
            }
        }
    }

    public static boolean getABoolean(String inputMsg, String errorMsg) {
        boolean b;
        while (true) {
            try {
                sc = new Scanner(System.in);
                System.out.print(inputMsg);
                b = sc.nextBoolean();
                sc = new Scanner(System.in);
                return b;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getDateFormat(String inputMsg, String errorMsg, String format, String lowerBound, String upperBound) { //da sua datatype cua upperBound 
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        while (true) {
            try {
                Date dateLower = formatter.parse(lowerBound);
                Date dateUpper = formatter.parse(upperBound);
                String inputDate;
                System.out.print(inputMsg);
                inputDate = sc.nextLine();
                Date date = formatter.parse(inputDate);
                if (date.before(dateLower) || date.after(dateUpper)) {
                    throw new Exception();
                }
                String outputDate = formatter.format(date);
                return outputDate;
            } catch (ParseException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Date must be from " + lowerBound + " to " + upperBound);
            }
        }
    }
    
    public static String getDateFormat(String inputMsg, String errorMsg, String format, String lowerBound) { //nang cap thanh try catch nhieu tang 
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        while (true) {
            try {
                Date dateLower = formatter.parse(lowerBound);
                String inputDate;
                System.out.print(inputMsg);
                inputDate = sc.nextLine();
                Date date = formatter.parse(inputDate);
                if (date.before(dateLower)) {
                    throw new Exception();
                }
                String outputDate = formatter.format(date);
                return outputDate;
            } catch (ParseException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Date must be start " + lowerBound);
            }
        }
    }

    
}
