#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <cv.h> 
#include <iqa.h>

using namespace cv;
using namespace std;

Scalar getMSSIM( const Mat& i1, const Mat& i2);

Mat myEntropy(Mat seq, int histSize);

float entropy(Mat seq, Size size, int index);

int histSize = 256;

int main(int argc, char** argv) {
    if (argc != 5) {
        cout << " Usage: display_image ImageToLoadAndDisplay" << endl;
        return -1;
    }

    Mat imagenOriginal, hist_y;
    imagenOriginal = imread(argv[1], CV_LOAD_IMAGE_COLOR); // Read the file
//    namedWindow( "Display frame",CV_WINDOW_AUTOSIZE);
//    imshow("lena_GRAYSCALE", imagenOriginal);
    //    cout << "arg 1 " << argv[1]<< endl;
    //    cout << "arg 2 " << argv[2]<< endl;
    //    cout << "arg 3 " << argv[3]<< endl;
    //    cout << "arg 4 " << argv[4]<< endl;
    //    
    if (!imagenOriginal.data) // Check for invalid input
    {
        cout << "Could not open or find the image" << std::endl;
        return -1;
    }
    
    Mat imagenYCrCb;
    cvtColor(imagenOriginal, imagenYCrCb, COLOR_BGR2YCrCb);

    Mat canales[3];
    split(imagenYCrCb, canales);

    //    Mat y = canales[0];
    //    Mat cb = canales[1];
    //    Mat cr = canales[2];

    Ptr<CLAHE> clahe = createCLAHE();
    Size tamanho = Size(atoi(argv[2]),atoi(argv[3]));
//    tamanho->width=atoi(argv[2]);
//    tamanho->height=atoi(argv[3]);
    //Size tamanho = new Size(atoi(argv[2]),atoi(argv[3]));
    clahe->setTilesGridSize(tamanho);
    clahe->setClipLimit(atof(argv[4]));

    
    
    Mat dst;
    clahe->apply(canales[0], dst);

    Scalar resultados=getMSSIM(canales[0],dst);


    Mat ycrcb_merged;
    dst.copyTo(canales[0]);
    
    hist_y = myEntropy(canales[0], histSize);
    
    float entropia = entropy(hist_y,imagenOriginal.size(), histSize);
    
    merge(canales,3, ycrcb_merged);

    Mat imagenVuelta;
    cvtColor(ycrcb_merged, imagenVuelta, COLOR_YCrCb2BGR);
    
    vector<int> compression_params;
    compression_params.push_back(CV_IMWRITE_JPEG_QUALITY);
    compression_params.push_back(100);
    
    std::stringstream ss;
    
    ss << (1- (8 - entropia))/8 << "_" << 1 - resultados.val[0] << "_" << 1 - resultados.val[1]<< "_" << 1 - resultados.val[2] <<"resultado.jpg"

    try {
        imwrite(ss.str(), mat, compression_params);
    }
    catch (runtime_error& ex) {
        fprintf(stderr, "Exception converting image to PNG format: %s\n", ex.what());
        return 1;
    }

//    namedWindow( "Display frame",CV_WINDOW_AUTOSIZE);
//    imshow("lena_CLAHE", imagenVuelta);
//
//    waitKey(0);

    //8-entropia 1-mssim_b 1-mssim_g 1-mssim_r
    // asumiendo contexto de minimizacion
    //cout << (1- (8 - entropia)/8 * (1 - resultados.val[0])) << endl; //<< " " << 1 - resultados.val[0] << " " << 1 - resultados.val[1] << " "  << 1 - resultados.val[2] << endl; //retorno de prueba de las m�tricas
    cout << (1- (8 - entropia))/8 << " " << 1 - resultados.val[0] << " " << 1 - resultados.val[1] << " "  << 1 - resultados.val[2] << endl; //retorno de prueba de las m�tricas

    return 0;
}