//  Kursawe.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
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
package jmetal.problems;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.ArrayRealSolutionType;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;
import jmetal.util.wrapper.XReal;
import org.apache.log4j.Logger;

/**
 * Class representing problem PsoClahe
 */
public class PsoClahe extends Problem {

    //public static final Logger logger_ = Logger.getLogger(PsoClahe.class.getName());
    public static final Logger logger_ = Logger.getLogger(PsoClahe.class.getName());
    /**
     * Constructor. Creates a default instance of the Kursawe problem.
     *
     * @param solutionType The solution type must "Real", "BinaryReal, and
     * "ArrayReal".
     */
//    public PsoClahe(String solutionType) throws ClassNotFoundException {
//        this(solutionType, 3);
//    } // PsoClahe

    String comandoEjecutar;
    String pathImagen;

//    public PsoClahe(String solutionType, Integer nroVariables, String comandoEjecutar, String pathImagen) throws ClassNotFoundException {
//
//        this(solutionType, nroVariables);
//        this.comandoEjecutar = comandoEjecutar;
//        this.pathImagen = pathImagen;
//    } // PsoClahe
    /**
     * Constructor. Creates a new instance of the Kursawe problem.
     *
     * @param numberOfVariables Number of variables of the problem
     * @param solutionType The solution type must "Real", "BinaryReal, and
     * "ArrayReal".
     */
    public PsoClahe(String solutionType, Integer nroVariables, String comandoEjecutar, String pathImagen) {
        this.comandoEjecutar = comandoEjecutar;
        this.pathImagen = pathImagen;
        numberOfVariables_ = nroVariables;
        numberOfObjectives_ = 1;
        numberOfConstraints_ = 0;
        problemName_ = "PsoClahe";

        upperLimit_ = new double[numberOfVariables_];
        lowerLimit_ = new double[numberOfVariables_];

        List<Integer> dimensionesImagen;

        dimensionesImagen = getDimensionesImagen(pathImagen);

        lowerLimit_[0] = 2;
        upperLimit_[0] = Math.round(dimensionesImagen.get(0)/2); //valores de r_x
        lowerLimit_[1] = 2;
        upperLimit_[1] = Math.round(dimensionesImagen.get(1)/2); //valores de r_y
        lowerLimit_[2] = 0;
        upperLimit_[2] = 1; //valores de cliplimit

        if (solutionType.compareTo("BinaryReal") == 0) {
            solutionType_ = new BinaryRealSolutionType(this);
        } else if (solutionType.compareTo("Real") == 0) {
            solutionType_ = new RealSolutionType(this);
        } else if (solutionType.compareTo("ArrayReal") == 0) {
            solutionType_ = new ArrayRealSolutionType(this);
        } else {
            System.out.println("Error: solution type " + solutionType + " invalid");
            System.exit(-1);
        }
    } // Kursawe

    /**
     * Evaluates a solution
     *
     * @param solution The solution to evaluate
     * @throws JMException
     */
    public void evaluate(Solution solution) throws JMException {

        try {
            //        if (args.length <= 0) {
//            System.err.println("Need command to run");
//            System.exit(-1);
//        }
            
            DecimalFormat formato = new DecimalFormat("#");
            logger_.info("parametros de entrada: ");
            logger_.info("parametro 1: " + formato.format(solution.getDecisionVariables()[0].getValue()));
            logger_.info("parametro 2: " + formato.format(solution.getDecisionVariables()[1].getValue()));
            logger_.info("parametro 3: " + (solution.getDecisionVariables()[2]).getValue());
            StringBuilder comandoEjecutar_ = new StringBuilder();
            List<String> argumentos = new ArrayList<>();
            comandoEjecutar_.append(comandoEjecutar).append(" ")
                    .append(pathImagen).append(" ")
                    .append(formato.format(solution.getDecisionVariables()[0].getValue())).append(" ")
                    .append(formato.format(solution.getDecisionVariables()[1].getValue())).append(" ")
                    .append(solution.getDecisionVariables()[2].getValue());
            logger_.info("comando ejecutar: " + comandoEjecutar_.toString());
            argumentos = Arrays.asList(comandoEjecutar_.toString().split(" "));
            //asumiendo que estamos hablando de clahe,
            //las variables de decisión son R_x, R_y, C
            Process process = new ProcessBuilder(argumentos).start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            String line2 = null;
            while ((line = br.readLine()) != null) {

                line2 = line;
                logger_.info("resultado: " + line2);
            }
            List<String> items = Arrays.asList(line2.split(" "));
            solution.setObjective(0, Double.valueOf(items.get(0)));
//            solution.setObjective(1, Double.valueOf(items.get(1)));
//            solution.setObjective(2, Double.valueOf(items.get(2)));
//            solution.setObjective(3, Double.valueOf(items.get(3)));

        } catch (IOException ex) {
            logger_.error("error: ");
            logger_.error(ex.getMessage());

        }

    }
//        XReal vars = new XReal(solution);
//
//        double aux, xi, xj; // auxiliary variables
//        double[] fx = new double[2]; // function values     
//        double[] x = new double[numberOfVariables_];
//        for (int i = 0; i < numberOfVariables_; i++) {
//            x[i] = vars.getValue(i);
//        }
//
//        fx[0] = 0.0;
//        for (int var = 0; var < numberOfVariables_ - 1; var++) {
//            xi = x[var] * x[var];
//            xj = x[var + 1] * x[var + 1];
//            aux = (-0.2) * Math.sqrt(xi + xj);
//            fx[0] += (-10.0) * Math.exp(aux);
//        } // for
//
//        fx[1] = 0.0;
//
//        for (int var = 0; var < numberOfVariables_; var++) {
//            fx[1] += Math.pow(Math.abs(x[var]), 0.8)
//                    + 5.0 * Math.sin(Math.pow(x[var], 3.0));
//        } // for
//

    private List<Integer> getDimensionesImagen(String pathImagen) {

        File imageFile = new File(pathImagen);
        List<Integer> resultado = new ArrayList<>();
        try {

            BufferedImage image = ImageIO.read(imageFile);
            logger_.info("Width de imagen: " + image.getWidth());
            resultado.add(image.getWidth());
            logger_.info("Height de imagen: " + image.getHeight());
            resultado.add(image.getHeight());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
} // evaluate
// PsoClahe
