/*
 * Copyright (c) 2014 Roc Project.  This is free software.  See
 * LICENSE.txt for details.
 */

package mloss.roc;


import java.io.PrintWriter;


// Alternative is an object that has a format and a list of what to
// include in the report
public class Reports {

    public static void report(String reportName, Curve curve, PrintWriter output) {
        if (reportName.equals("rocpts")) {
            rocPts(curve, output);
        } else if (reportName.equals("yaml")) {
            yaml(curve, output);
        } else {
            throw new IllegalArgumentException(String.format("Report name not recognized: %s", reportName));
        }
    }

    public static void rocPts(Curve curve, PrintWriter output) {
        double[][] rocPoints = curve.rocPoints();
        for (double[] point : rocPoints) {
            output.println(String.format("%s %s", point[0], point[1]));
        }
    }

    public static void yaml(Curve curve, PrintWriter output) {
        output.println("%YAML 1.1");
        output.println("---");
        output.println("ROC area: " + curve.rocArea());
        double[][] rocPoints = curve.rocPoints();
        output.println(String.format("ROC points count: %d", rocPoints.length));
        output.println("ROC points:");
        for (double[] point : rocPoints) {
            // Just format the floating point numbers to string for now
            // because I can't find a floating point format that works
            // like the string formatting and chops off the trailing
            // zeros.
            output.println(String.format("  - [%s, %s]", point[0], point[1]));
        }
        output.println("ROC points Gnuplot text: |");
        for (double[] point : rocPoints) {
            // Just format the floating point numbers to string for now
            // because I can't find a floating point format that works
            // like the string formatting and chops off the trailing
            // zeros.
            output.println(String.format("%s %s", point[0], point[1]));
        }
        output.println("# End ROC points Gnuplot text");
        output.println("...");
    }

}
