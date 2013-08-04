#include <stdio.h>
#include "cv.h"
#include "highgui.h"
 
int main(int argc, char** argv)
{
    CvCapture *capture;
    IplImage  *frame;
    double    t, ms = 0;
    int       key;
 
    /* initialize webcam */
    capture = cvCaptureFromCAM(0);
    
    if(!capture) capture = cvCaptureFromAVI("a.avi");
    
    cvNamedWindow("video", 1);
 
    while (key != 'q') {
        t = (double)cvGetTickCount();
 
        /* display video */
        frame = cvQueryFrame(capture);
        cvShowImage("video", frame);
        key = cvWaitKey(1);
 
        /* get elapsed time */
        t = (double)cvGetTickCount() - t;
        ms += t/((double)cvGetTickFrequency() * 1000.0);
 
        /* autosave every 3 seconds */
        if (ceil(ms) >= 3000) {
            cvSaveImage("img.jpg", frame);
            ms = 0;
        }
    }
 
    /* free memory */
    cvReleaseCapture(&capture);
    cvDestroyWindow("video");
 
    return 0;
}
