//A program to detect a color object and move the mouse pointer with it and perform left click
//Here yellow colored object is used
//The color of the object can be changed by using the trackbars


//OpenCV Headers
#include<cv.h>
#include<highgui.h>
//Blob Libray Header from cvBlob
#include"cvblob.h"
#include<stdio.h>
//Height and Width
#define h 320
#define w 240
//Namespace for Blob Library
using namespace cvb;
using namespace std;
int main()
{
    //Structure for holding video
    CvCapture* capture=cvCreateCameraCapture(0);
    //Varibles for trackbars
    int h1=0;int h2=0;
    int s1=0;int s2=0;
    int v1=0;int v2=0;
    //Image variable
    IplImage* frame=cvCreateImage(cvSize(h,w),8,3);
    IplImage* hsvframe=cvCreateImage(cvSize(h,w),8,3);
    IplImage* thresh=cvCreateImage(cvSize(h,w),8,1);
    IplImage *labelImg=cvCreateImage(cvSize(h,w),IPL_DEPTH_LABEL, 1);
    //Windows
    cvNamedWindow("video",CV_WINDOW_AUTOSIZE);
    cvNamedWindow("thresh",CV_WINDOW_AUTOSIZE);
    cvNamedWindow("Control");
    //Trackbars
    cvCreateTrackbar("h1","Control",&h1,255,0);
    cvCreateTrackbar("h2","Control",&h2,255,0);
    cvCreateTrackbar("s1","Control",&s1,255,0);
    cvCreateTrackbar("s2","Control",&s2,255,0);
    cvCreateTrackbar("v1","Control",&v1,255,0);
    cvCreateTrackbar("v2","Control",&v2,255,0);
    //Setting the initial positions of the trackbars
    cvSetTrackbarPos("h1","Control",23);
    cvSetTrackbarPos("h2","Control",40);
    cvSetTrackbarPos("s1","Control",133);
    cvSetTrackbarPos("s2","Control",255);
    cvSetTrackbarPos("v1","Control",41);
    cvSetTrackbarPos("v2","Control",150);
    //Getting the screen information
    int screenx = GetSystemMetrics(SM_CXSCREEN);
    int screeny = GetSystemMetrics(SM_CYSCREEN);
    //Variables for blobs
    CvBlobs blobs;
    while(1)
    {
        IplImage *fram=cvQueryFrame(capture);
        if(!frame)
            break;
        //Resizing the image
        cvResize(fram,frame,CV_INTER_LINEAR);
        //Flipping the image
        cvFlip(frame,frame,1);
        //Changing to HSV
        cvCvtColor(frame,hsvframe,CV_BGR2HSV);
        //Thresholding the image
        cvInRangeS(hsvframe,cvScalar(h1,v1,s1),cvScalar(h2,v2,s2),thresh);
        //Smoothing the image
        cvSmooth(thresh,thresh,CV_MEDIAN,7,7);
        //Finding blobs in the image
        unsigned int result=cvLabel(thresh, labelImg, blobs);
        //Rendering the blobs
        cvRenderBlobs(labelImg,blobs,frame,frame);
        //Filtering the blobs
        cvFilterByArea(blobs,50,500);
        for (CvBlobs::const_iterator it=blobs.begin(); it!=blobs.end(); ++it)
        {
                 //Printing the data
                 cout << "Blob #" << it->second->label << endl;
                 cout << "Area:"<<it->second->area<<endl;
                 int label=it->second->label;
                 //Calculating the moments
                 double moment10 = it->second->m10;
                 double moment01 = it->second->m01;
                 double area = it->second->area;
                 // Holding the last and current positions
                 static int x1;
                 static int y1;
                 //Calculating the current position
                 x1 = moment10/area;
                 y1 = moment01/area;
                 //Mapping to the screen coordinates
                 int mx=(int)(x1*screenx/frame->width);
                 int my=(int)(y1*screeny/frame->height);
                 //If No blobs=1 navigate to that position
                 if(label==1)
                    SetCursorPos(mx,my);
                //If No blobs=2 perform left click
                 if(label==2)
                    mouse_event(MOUSEEVENTF_LEFTDOWN|MOUSEEVENTF_LEFTUP, 0, 0, 0, 0); // Left click
        }
        cvShowImage("video",frame);
        cvShowImage("thresh",thresh);
        cvShowImage("Control",NULL);
        char c=cvWaitKey(33);
        if(c==27)
            break;
    }
    cvDestroyAllWindows();
    cvReleaseCapture(&capture);
}

