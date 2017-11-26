//  pSMPSO_main.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//
//  Copyright (c) 2013 Antonio J. Nebro
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
package jmetal.metaheuristics.smpso;

import java.io.File;
import java.io.FileInputStream;
import jmetal.core.Algorithm;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.operators.mutation.Mutation;
import jmetal.operators.mutation.MutationFactory;
import jmetal.problems.Kursawe;
import jmetal.problems.ProblemFactory;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import jmetal.util.parallel.IParallelEvaluator;
import jmetal.util.parallel.MultithreadedEvaluator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.FileHandler;
import jmetal.problems.PsoClahe;
import org.apache.log4j.Logger;

/**
 * This class executes pSMPSO, a multithreaded version of SMPSO, characterized
 * by evaluating the particles in parallel.
 */
public class pSMPSO_main {

    public static final Logger logger_ = Logger.getLogger(pSMPSO_main.class.getName());      // Logger object
    public static FileHandler fileHandler_; // FileHandler object

    public static Properties prop = new Properties();
    public static InputStream input = null;

    static {

        System.out.println();

    }

    /**
     * @param args Command line arguments. The first (optional) argument
     * specifies the problem to solve.
     * @throws JMException
     * @throws IOException
     * @throws SecurityException Usage: three options -
     * jmetal.metaheuristics.smpso.pSMPSO_main -
     * jmetal.metaheuristics.smpso.pSMPSO_main problemName -
     * jmetal.metaheuristics.smpso.pSMPSO_main problemName ParetoFrontFile
     */
    public static void main(String[] args) throws JMException, IOException, ClassNotFoundException {
        Problem problem;  // The problem to solve
        Algorithm algorithm;  // The algorithm to use
        Mutation mutation;  // "Turbulence" operator

        QualityIndicator indicators; // Object to get quality indicators

        HashMap parameters; // Operator parameters

        // Logger object and file to store log messages
        //logger_ = Configuration.logger_;
        logger_.info("inicia ejecucion");
        fileHandler_ = new FileHandler("SMPSO_main.log");
        //logger_.addHandler(fileHandler_);
        input = new FileInputStream("pso.properties");

        prop.load(input);

        indicators = null;
        if (args.length == 1) {
            Object[] params = {"Real"};
            problem = (new ProblemFactory()).getProblem(args[0], params);
        } // if
        else if (args.length == 2) {
            Object[] params = {"Real"};
            problem = (new ProblemFactory()).getProblem(args[0], params);
            indicators = new QualityIndicator(problem, args[1]);
        } // if
        else { // Default problem
            problem = new PsoClahe("Real", 3, args[0], args[1]);
            //problem = new Water("Real");
            //problem = new ZDT1("ArrayReal", 1000);
            //problem = new ZDT4("BinaryReal");
            //problem = new WFG1("Real");
            //problem = new DTLZ1("Real");
            //problem = new OKA2("Real") ;
        } // else
        logger_.info("cantidad de hilos: " + (prop.getProperty("cantidadHilos")));

        int threads = new Integer(prop.getProperty("cantidadHilos")); // 0 - use all the available cores
        IParallelEvaluator parallelEvaluator = new MultithreadedEvaluator(threads);
        logger_.info("cantidad de hilos: " + new Integer(prop.getProperty("cantidadHilos")));

        algorithm = new pSMPSO(problem, parallelEvaluator);

        // Algorithm parameters    
        algorithm.setInputParameter("swarmSize", new Integer(prop.getProperty("swarmSize")));
        algorithm.setInputParameter("archiveSize", new Integer(prop.getProperty("archiveSize")));
        algorithm.setInputParameter("maxIterations", new Integer(prop.getProperty("maxIterations")));

        logger_.info("swarmSize: " + new Integer(prop.getProperty("swarmSize")));
        logger_.info("archiveSize: " + new Integer(prop.getProperty("archiveSize")));
        logger_.info("maxIterations: " + new Integer(prop.getProperty("maxIterations")));

        parameters = new HashMap();
        parameters.put("probability", 1.0 / problem.getNumberOfVariables());
        parameters.put("distributionIndex", 20.0);
        mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);

        algorithm.addOperator("mutation", mutation);

        // Execute the Algorithm 
        long initTime = System.currentTimeMillis();
        SolutionSet population = algorithm.execute();
        long estimatedTime = System.currentTimeMillis() - initTime;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

// Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
// Using DateFormat format method we can create a string 
// representation of a date with the defined format.
        String reportDate = df.format(today);

        File file = new File("corrida" + reportDate);
        if (!file.exists()) {
            if (file.mkdir()) {
                logger_.info("Directory is created!corrida" + reportDate);
            } else {
                logger_.info("Failed to create directory!");
            }
        }

        // Result messages 
        logger_.info("Total execution time: " + estimatedTime + "ms");
        logger_.info("Objectives values have been writen to file FUN");
        population.printObjectivesToFile("corrida"+reportDate + "/FUN" + reportDate + ".txt");
        logger_.info("Variables values have been writen to file VAR");
        population.printVariablesToFile("corrida"+reportDate + "/VAR" + reportDate + ".txt");

        if (indicators != null) {
            logger_.info("Quality indicators");
            logger_.info("Hypervolume: " + indicators.getHypervolume(population));
            logger_.info("GD         : " + indicators.getGD(population));
            logger_.info("IGD        : " + indicators.getIGD(population));
            logger_.info("Spread     : " + indicators.getSpread(population));
            logger_.info("Epsilon    : " + indicators.getEpsilon(population));
        } // if                   
    } //main
} // SMPSO_main
