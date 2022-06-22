/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stage;

import data.Person;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import utils.MyUtilities;

/**
 *
 * @author Panh
 */
public class Main {
    
    private static NormalDistribution N = new NormalDistribution(0, 1);
    private static TDistribution t = new TDistribution(29);

    public static void main(String[] args) {
        Menu menu = new Menu("Computer Project - @2022 by <SE151396 - Dang Phuong Anh>");
        menu.addNewOption("1. Probability");
        menu.addNewOption("2. Discrete Random Variables and Probability Distribution");
        menu.addNewOption("3. Continuous Random Variables and Probability Distribution");
        menu.addNewOption("4. Descriptive Statistics");
        menu.addNewOption("5. Sampling Distributions and Point Estimation of Paramaters");
        menu.addNewOption("6. Statistical Intervals for a Single Sample");
        menu.addNewOption("7. Test of Hypotheses for a Single Sample");
        menu.addNewOption("8. Simple Linear Regression and Correlation");
        menu.addNewOption("9. Quit");

        
        int choice;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    System.out.println("1. Probability");
                    getTopic1();
                    break;
                case 2:
                    System.out.println("2. Discrete Random Variables and Probability Distribution");
                    getTopic2();
                    break;
                case 3:
                    System.out.println("3. Continuous Random Variables and Probability Distribution");
                    getTopic3();
                    break;
                case 4:
                    System.out.println("4. Descriptive Statistics");
                    getTopic4();
                    break;
                case 5:
                    System.out.println("5. Sampling Distributions and Point Estimation of Paramaters");
                    getTopic5();
                    break;
                case 6:
                    System.out.println("6. Statistical Intervals for a Single Sample");
                    getTopic6();
                    break;
                case 7:
                    System.out.println("7. Test of Hypotheses for a Single Sample");
                    getTopic7();
                    break;
                case 8:
                    System.out.println("8. Simple Linear Regression and Correlation");
                    getTopic8();
                    break;
            }
        } while (choice != 9);

    }

    private static void getTopic1() {
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", MyUtilities.TOTAL_ROW);
        System.out.println("1. Choose a simple random sample with size of 30 from the population. What is the probability that there are six black ones.");
        int n = 1000000;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (MyUtilities.countBlack(list, 30) == 6) {
                count++;
            }
        }
        System.out.println((double) count / n);

        System.out.println("2. Choose 5 people in your subsample randomly. Find the probability that there is at least one black.");
        count = 0;
        for (int i = 1; i <= n; i++) {
            if (MyUtilities.haveBlack(list, 30, 5)) {
                count++;
            }
        }
        System.out.println((double) count / n);
    }

    private static void getTopic2() {
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", MyUtilities.TOTAL_ROW);
        int n = 10000;
        System.out.println("3. Suppose that the proportion of blacks in the population equals the proportion of backs in your subsample. \n"
                + "       a.	How many blacks do you expect in your subsample?");
        System.out.println("       " + (double) MyUtilities.countBlack(list) / MyUtilities.TOTAL_ROW * 30);
        System.out.println("       b.	How many blacks are in the data?");
        System.out.println("       " + MyUtilities.countBlack(list, 30));
        System.out.println("4.	Suppose that number of education (educ) is a discrete random variable that has a Poisson distribution such that its mean equals to the sample mean in your subsample. If we randomly choose a person, what is the probability that he/she has 6 years in education?");
        System.out.println("       The average years of education is: " + (double) MyUtilities.countEduc(list) / MyUtilities.TOTAL_ROW);
        System.out.print("       Education = 6: ");
        Random random = new Random();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int dedicate = random.nextInt(MyUtilities.TOTAL_ROW - 2 + 1) + 1;
            if (list.get(dedicate).getEduc() == 6) {
                count++;
            }
        }
        System.out.println((double) count / n);

        System.out.println("5.	Assume age is an integer-valued discrete uniform random variable, 24 ≤ age ≤ 34. Find the mean and the variance of age. Compare these corresponding values to the values in your subsample. Create the bar chart for age. Is age a uniform distribution?");
        System.out.println("mean: " + (24 + 34) /2);
        System.out.println("variance: " + (Math.pow((34-24+1), 2) - 1) / 2);
        int size = 30;
        List<Person> listS = MyUtilities.getSubSample("wage.xlsx", size);
        double[] ageArray = listS.stream().mapToDouble(m -> m.getAge()).toArray();  
        DescriptiveStatistics ageStatistics = new DescriptiveStatistics(ageArray);
        System.out.println("mean: " + ageStatistics.getMean());
        System.out.println("variance: " + ageStatistics.getVariance());
    }

    private static void getTopic3() {
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", MyUtilities.TOTAL_ROW);
        System.out.println("6.	Suppose that lwage is a normally distributed random variable with mean μ = 6.26 and standard deviation σ = 0.44. Find the probability P(5 < lwage < 6).");
        double stdLower = (5 - 6.26) / 0.44;
        double stdUpper = (6 - 6.25) /0.44;
        System.out.printf("P(5 < lwage < 6) = P(%0.2f < z < %0.2f)", stdLower, stdUpper);
        System.out.printf("= P(%0.2f) - P(%0.2f)", stdUpper, stdLower);
        System.out.printf("= %0.6f", (0.277595 - 0.002118));
        
        System.out.println("7.	Assume IQ ~ N(mi, sigma^2) , where  and  .");
        System.out.println("a.	For your random sample of n = 20 observations. Find the probability that the sample mean of IQ lies in [100, 110].");

        double[] iqArray = list.stream().mapToDouble(m -> m.getIq()).toArray();
        DescriptiveStatistics iqStatistics = new DescriptiveStatistics(iqArray);
        double mean = iqStatistics.getMean(); //= sample mean
        double variance = iqStatistics.getVariance() / 20;
        double lowerIQZ = (100 - mean) / Math.sqrt(variance);
        double upperIQZ = (110 - mean) / Math.sqrt(variance);
        System.out.printf("P(100 < SampleMean < 110) = P(%0.2f < Z < %0.2f)", lowerIQZ, upperIQZ);
        System.out.printf("= P(%f) - P(%f)", N.inverseCumulativeProbability(upperIQZ), N.inverseCumulativeProbability(lowerIQZ));
        
        System.out.println("b.	How large must the random sample be if we want the standard error of the sample mean to be 1?");
        System.out.println("Sample size: " + Math.ceil(iqStatistics.getVariance() / 1 * 1));
    }
    
    private static void getTopic4() {
        int size = 30;
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", size);
        System.out.println("8.	Calculate the statistics for wage, IQ, educ and exper.");
        System.out.println("    Wage");
        double[] wageArray = list.stream().mapToDouble(m -> m.getWage()).toArray();  
        DescriptiveStatistics wageStatistics = new DescriptiveStatistics(wageArray);
        System.out.println("Mean: " + wageStatistics.getMean());
        System.out.println("Median: " + wageStatistics.getPercentile(50));
        System.out.println("q1: " + wageStatistics.getPercentile(25));
        System.out.println("q3: " + wageStatistics.getPercentile(75));
        System.out.println("Standard deviation: " + Math.sqrt(wageStatistics.getVariance()));

        System.out.println("    IQ");
        double[] iqArray = list.stream().mapToDouble(m -> m.getIq()).toArray();
        DescriptiveStatistics iqStatistics = new DescriptiveStatistics(iqArray);
        System.out.println("Mean: " + iqStatistics.getMean());
        System.out.println("Median: " + iqStatistics.getPercentile(50));
        System.out.println("q1: " + iqStatistics.getPercentile(25));
        System.out.println("q3: " + iqStatistics.getPercentile(75));
        System.out.println("Standard deviation: " + Math.sqrt(iqStatistics.getVariance()));

        System.out.println("    Education");
        double[] educArray = list.stream().mapToDouble(m -> m.getEduc()).toArray();
        DescriptiveStatistics educStatistics = new DescriptiveStatistics(educArray);
        System.out.println("Mean: " + educStatistics.getMean());
        System.out.println("Median: " + educStatistics.getPercentile(50));
        System.out.println("q1: " + educStatistics.getPercentile(25));
        System.out.println("q3: " + educStatistics.getPercentile(75));
        System.out.println("Standard deviation: " + Math.sqrt(educStatistics.getVariance()));


        System.out.println("    Expertation");
        double[] expertArray = list.stream().mapToDouble(m -> m.getExper()).toArray();
        DescriptiveStatistics expertStatistics = new DescriptiveStatistics(expertArray);
        System.out.println("Mean: " + expertStatistics.getMean());
        System.out.println("Median: " + expertStatistics.getPercentile(50));
        System.out.println("q1: " + expertStatistics.getPercentile(25));
        System.out.println("q3: " + expertStatistics.getPercentile(75));
        System.out.println("Standard deviation: " + Math.sqrt(expertStatistics.getVariance()));
    }
    
    private static void getTopic5() {
        int size = 30;
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", MyUtilities.TOTAL_ROW);
        
        double[] iqArray = list.stream().mapToDouble(m -> m.getIq()).toArray();
        DescriptiveStatistics iqStatistics = new DescriptiveStatistics(iqArray);
        double mean = iqStatistics.getMean();
        double variance = iqStatistics.getVariance();
        System.out.print("9. The point estimation for mean of IQ: mean(IQ) ~ Norm(" + mean + ", " + variance/size + ")");
        
    }
    
    private static void getTopic6() {
        int size = 30;
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", size);
        List<Person> listP = MyUtilities.getSubSample("wage.xlsx", MyUtilities.TOTAL_ROW);
        //10.
        double[] lwageArray = list.stream().mapToDouble(m -> m.getLwage()).toArray();
        DescriptiveStatistics lwageStatistics = new DescriptiveStatistics(lwageArray);
        double mean = lwageStatistics.getMean();
        double variance = 0.44 * 0.44 / size;
        double std = Math.sqrt(variance);
        double E = N.inverseCumulativeProbability(0.975) * std;
        System.out.println("10. Suppose that lwage is a normally distributed random variable with standard deviation = 0.44");
        System.out.print("mean(lwage) ~ Norm(" + mean + ", " + variance + ")\n");
        System.out.println("95% CI: " + (mean - E) + " < true mean < " + (mean + E));        
        System.out.println("");
        
        //11.
        double[] iqArray = list.stream().mapToDouble(m -> m.getIq()).toArray();
        DescriptiveStatistics iqStatistics = new DescriptiveStatistics(iqArray);
        mean = iqStatistics.getMean();
        variance = iqStatistics.getVariance() / size;
        std = Math.sqrt(variance);
        E = N.inverseCumulativeProbability(0.98) * std;
        System.out.println("11. Suppose that IQ is a normally distributed random variable");
        System.out.print("mean(IQ) ~ Norm(" + mean + ", " + variance + ")\n");
        System.out.println("96% CI: " + (mean - E) + " < true mean < " + (mean + E));
        System.out.println("");
        
        //12. 
        System.out.println("12. If we want the error in estimating the mean lwage from the two-size confidence interval to be 0.2 at 95% confidence.");
        System.out.println("Assume that lwage is a normally distributed random variable with standard deviation = 0.44");
        System.out.println("Sample size: " + Math.ceil(Math.pow((N.inverseCumulativeProbability(0.975)*0.44/0.1), 2)));
        System.out.println("");
        
        //13. 
        System.out.println("13. Calculate a 99% confidence interval on the true proportion of all people who near 4-year college (nearc4).");
        double[] nearc4Array = list.stream().mapToDouble(m -> m.getNearc4()).toArray();
        DescriptiveStatistics nearc4Statistics = new DescriptiveStatistics(nearc4Array);
        double sum = 0;
        for (double d : nearc4Array) {
            sum += d;
        }
        double p_hat = sum / size;
        System.out.println("p^: " + p_hat);
        System.out.printf("99%% CI: %f < true proporttion < %f", p_hat - N.inverseCumulativeProbability(0.995)*Math.sqrt(p_hat * (1 - p_hat) / size), p_hat + N.inverseCumulativeProbability(0.995)*Math.sqrt(p_hat * (1 - p_hat) / size));
        System.out.println("");
        
        //14. 
        System.out.println("Sample size: " + Math.ceil(Math.pow((N.inverseCumulativeProbability(0.995) / (0.01/2)), 2) * 0.25));
    }
    
    private static void getTopic7() {
        int size =30;
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", size);
        
        //15. 
        double[] iqArray = list.stream().mapToDouble(m -> m.getIq()).toArray();
        DescriptiveStatistics iqStatistics = new DescriptiveStatistics(iqArray);
        double mean = iqStatistics.getMean();
        System.out.println("15.	Use your subsample to test the hypothesis H0: mean(IQ) = 100 against H1: mean(IQ) ≠ 100 at.\n "
                + "Assume that IQ is a normally distributed random variable with standard deviation σ = 15.");
        System.out.println("z_0: " + (mean - 100) / (15 / Math.sqrt(size)));
        System.out.println("z_stat: " + N.inverseCumulativeProbability(0.01));
        System.out.println("==> reject H0");
        System.out.println("");
        
        //16. 
        double[] lwageArray = list.stream().mapToDouble(m -> m.getLwage()).toArray();
        DescriptiveStatistics lwageStatistics = new DescriptiveStatistics(lwageArray);
        mean = lwageStatistics.getMean();
        double std = lwageStatistics.getStandardDeviation();
        System.out.println(std);
        System.out.println("T_0: " + (mean - 6) / (std / Math.sqrt(size)));
        System.out.println("T_stat: " + t.inverseCumulativeProbability(1-0.1));
        System.out.println("T_0 > T_stat ==> reject H0 ==> accept mean > 6");
        System.out.println("");
        
        //17. 
        double[] experArray = list.stream().mapToDouble(m -> m.getExper()).toArray();  
        DescriptiveStatistics experStatistics = new DescriptiveStatistics(experArray);
        int sum_less_than_10 = 0;
        for (double d : experArray) {
            if (d < 10) {
                sum_less_than_10 += 1;
            }
        }
        System.out.println("p^: " + sum_less_than_10 / size);
        System.out.println("H0: p = 0.07");
        System.out.println("H1: p < 0.07");
        int X = sum_less_than_10;
        int n = size;
        double p_0 = 0.07;
        System.out.println("z_0: " + (X - n * p_0) / Math.sqrt((n * p_0 * (1 - p_0))));
        System.out.println("z_stat: " + N.inverseCumulativeProbability(0.02));
        System.out.println("z_0 >> z_stat ==> accept H0 ==> people with less than 10 years of work experience is more than 0.07");
        System.out.println("");
        
        //18.
        double[] wageArray1 = list.stream().mapToDouble(m -> m.getWage()).toArray();
        DescriptiveStatistics wageStatistics1 = new DescriptiveStatistics(wageArray1);
        
        double[] wageArray2 = list.stream().mapToDouble(m -> m.getWage()).toArray();
        DescriptiveStatistics wageStatistics2 = new DescriptiveStatistics(wageArray2);
        
        double x1 = wageStatistics1.getMean();
        double s1 = wageStatistics1.getVariance();
        System.out.println("n1: " + size);
        System.out.println("x1: " + x1);
        System.out.println("s1: " + s1);
        System.out.println("");
        
        double x2 = wageStatistics2.getMean();
        double s2 = wageStatistics2.getVariance();
        System.out.println("n2: " + size);
        System.out.println("x2: " + x2);
        System.out.println("s2: " + s2);
        System.out.println("");
        
        System.out.println("H0: muy1 == muy2");
        System.out.println("H1: muy1 != muy2");
        double pooledVariance = ((size - 1)*s1 + (size - 1)*s2) / (size + size - 2);
        System.out.println("Pooled Variance: " + pooledVariance);
        System.out.println("T_0: " + (x1 - x2) / Math.sqrt(pooledVariance * (1/size + 1/size)));
        t = new TDistribution(58);
        System.out.println("T_stat: " + t.inverseCumulativeProbability(0.99));
        System.out.println("Accept H0");
        
    }
    
    private static void getTopic8() {
        int size =30;
        List<Person> list = MyUtilities.getSubSample("wage.xlsx", size);
        //19. 
        double[] educArray = list.stream().mapToDouble(m -> m.getEduc()).toArray();
        DescriptiveStatistics educStatistics = new DescriptiveStatistics(educArray);
        double[] X = educArray;
        double X_mean = educStatistics.getMean();
        
        double[] wageArray = list.stream().mapToDouble(m -> m.getWage()).toArray();  
        DescriptiveStatistics wageStatistics = new DescriptiveStatistics(wageArray);
        double[] Y = wageArray;
        double Y_mean = wageStatistics.getMean();
        
        double Sxy = 0, Sxx= 0;
        for (int i = 1; (i < X.length) && (i < Y.length); i++) {
            Sxy += (X[i] - X_mean) * (Y[i] - Y_mean);
            Sxx += Math.pow((X[i] - X_mean), 2);
        }
        double slope = Sxy / Sxx;
        double intercept = Y_mean - slope * X_mean;
        System.out.printf("y = %f + %fx", intercept, slope);
        
        //20.
        double[] lwageArray = list.stream().mapToDouble(m -> m.getLwage()).toArray();
        DescriptiveStatistics lwageStatistics = new DescriptiveStatistics(lwageArray);
        double[] Y2 = lwageArray;
        double Y2_mean = lwageStatistics.getMean();
        double Sxy2 = 0;
        for (int i = 0; (i < X.length) && (i < Y2.length); i++) {
            Sxy2 += (X[i] - X_mean) * (Y2[i] - Y2_mean);
        }
        double slope2 = Sxy2/Sxx;
        double intercept2 = Y2_mean - slope2 * X_mean;
        System.out.printf("y2 = %f + %fx", intercept2, slope2);
    }
}
