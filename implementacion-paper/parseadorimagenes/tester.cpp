/* 
 * File:   main.cpp
 * Author: lg_more
 *
 * Created on 3 de septiembre de 2016, 02:49 PM
 */

#include <cstdlib>

using namespace std;

//#include <opencv2/core/core.hpp>
//#include <opencv2/imgproc/imgproc.hpp>
//#include <highgui/highgui.hpp>
//#include <iostream>
//#include <cv.h> 
//#include <fstream>
//#include <string.h>
//
//using namespace cv;
//
//int histSize = 256;
//
//int main__(int argc, char** argv) {
//
//    if (argc != 2) {
//        cout << " Usage: display_image ImageToLoadAndDisplay" << endl;
//        return -1;
//    }
//
//
//
//    Mat imagenOriginal, hist_y;
//    imagenOriginal = imread(argv[1], CV_LOAD_IMAGE_COLOR); // Read the file
//    //    namedWindow( "Display frame",CV_WINDOW_AUTOSIZE);
//    //    imshow("lena_GRAYSCALE", imagenOriginal);
//    //    cout << "arg 1 " << argv[1]<< endl;
//    //    cout << "arg 2 " << argv[2]<< endl;
//    //    cout << "arg 3 " << argv[3]<< endl;
//    //    cout << "arg 4 " << argv[4]<< endl;
//    //    
//
////    std::ifstream infile(argv[2]);
////
////    if (!imagenOriginal.data) // Check for invalid input
////    {
////        cout << "Could not open or find the image" << std::endl;
////        return -1;
////    }
//
//
//    string a, b, c, d, e, f, g, h, i;
//    int contador = 0;
//    while (infile >> i >> a >> b >> c >> d >> e >> f >> g >> h) {
//        if (h.compare("N")!=0) continue;
//        cout << "se procesa";
//        // process pair (a,b)
//        Mat imagenYCrCb;
//        cv::cvtColor(imagenOriginal, imagenYCrCb, CV_BGR2YCrCb);
//
//        Mat canales[3];
//        split(imagenYCrCb, canales);
//
//
//        cv::Ptr<cv::CLAHE> clahe = cv::createCLAHE();
//        Size tamanho = Size(stoi(a), stoi(b));
//        //    tamanho->width=atoi(argv[2]);
//        //    tamanho->height=atoi(argv[3]);
//        //Size tamanho = new Size(atoi(argv[2]),atoi(argv[3]));
//        clahe->setTilesGridSize(tamanho);
//        clahe->setClipLimit(stod(c));
//
//
//
//        Mat dst;
//        clahe->apply(canales[0], dst);
//
//        Mat ycrcb_merged;
//        dst.copyTo(canales[0]);
//
//        merge(canales, 3, ycrcb_merged);
//
//        Mat imagenVuelta;
//        cvtColor(ycrcb_merged, imagenVuelta, CV_YCrCb2BGR);
//
//        imwrite("" + std::to_string(contador) + "-resultado.jpg", imagenVuelta);
//
//        contador++;
//    }
//
//
//    return 0;
//}
