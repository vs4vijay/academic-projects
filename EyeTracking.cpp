#include <stdio.h>
#include "cv.h"
#include "highgui.h"
 
#define  TPL_WIDTH       12      /* template width       */
#define  TPL_HEIGHT      12      /* template height      */
#define  WINDOW_WIDTH    24      /* search window width  */
#define  WINDOW_HEIGHT   24      /* search window height */
#define  THRESHOLD       0.3
 
IplImage *frame, *tpl, *tm;
int      object_x0, object_y0, is_tracking = 0;
 
void mouseHandler( int event, int x, int y, int flags, void *param );
void trackObject();
CvMemStorage			*storage;
/* main code */
int main( int argc, char** argv )
{
    CvCapture   *capture;
    int         key;
 
 	storage = cvCreateMemStorage( 0 );
 
    /* initialize camera */
    capture = cvCaptureFromCAM( 0 );
 
    /* always check */
    if( !capture ) cvCaptureFromAVI( argv[1] );
 
    /* get video properties, needed by template image */
    frame = cvQueryFrame( capture );
    if ( !frame ) return 1;
   
    /* create template image */
    tpl = cvCreateImage( cvSize( TPL_WIDTH, TPL_HEIGHT ),
                         frame->depth, frame->nChannels );
   
    /* create image for template matching result */
    tm = cvCreateImage( cvSize( WINDOW_WIDTH  - TPL_WIDTH  + 1,
                                WINDOW_HEIGHT - TPL_HEIGHT + 1 ),
                        IPL_DEPTH_32F, 1 );
   
    /* create a window and install mouse handler */
    cvNamedWindow( "video", CV_WINDOW_AUTOSIZE );
    cvSetMouseCallback( "video", mouseHandler, NULL );
   
    while( key != 'q' ) {
        /* get a frame */
        frame = cvQueryFrame( capture );
 
        /* always check */
        if( !frame ) break;
 
        /* 'fix' frame */
        cvFlip( frame, frame, -1 );
        frame->origin = 0;
       
        /* perform tracking if template is available */
        if( is_tracking ) trackObject();
       
        /* display frame */
        cvShowImage( "video", frame );
 
        /* exit if user press 'q' */
        key = cvWaitKey( 1 );
    }
 
    /* free memory */
    cvDestroyWindow( "video" );
    cvReleaseCapture( &capture );
    cvReleaseImage( &tpl );
    cvReleaseImage( &tm );
   
    return 0;
}
 
/* mouse handler */
void mouseHandler( int event, int x, int y, int flags, void *param )
{
    /* user clicked the image, save subimage as template */
    if( event == CV_EVENT_LBUTTONUP ) {
        object_x0 = x - ( TPL_WIDTH  / 2 );
        object_y0 = y - ( TPL_HEIGHT / 2 );
       
        cvSetImageROI( frame,
                       cvRect( object_x0,
                               object_y0,
                               TPL_WIDTH,
                               TPL_HEIGHT ) );
        cvCopy( frame, tpl, NULL );
        cvResetImageROI( frame );
 
        /* template is available, start tracking! */
        fprintf( stdout, "Template selected. Start tracking... \n" );
        is_tracking = 1;
    }
}
 
/* track object */
void trackObject()
{
    CvPoint minloc, maxloc;
    double  minval, maxval;
 
    /* setup position of search window */
    int win_x0 = object_x0 - ( ( WINDOW_WIDTH  - TPL_WIDTH  ) / 2 );
    int win_y0 = object_y0 - ( ( WINDOW_HEIGHT - TPL_HEIGHT ) / 2 );
   
    /*
     * Ooops, some bugs here.
     * If the search window exceed the frame boundaries,
     * it will trigger errors.
     *
     * Add some code to make sure that the search window
     * is still within the frame.
     */
   
    /* search object in search window */
    cvSetImageROI( frame,
                   cvRect( win_x0,
                           win_y0,
                           WINDOW_WIDTH,
                           WINDOW_HEIGHT ) );
    cvMatchTemplate( frame, tpl, tm, CV_TM_SQDIFF_NORMED );
    cvMinMaxLoc( tm, &minval, &maxval, &minloc, &maxloc, 0 );
    cvResetImageROI( frame );
   
    /* if object found... */
    if( minval <= THRESHOLD ) {
        /* save object's current location */
        object_x0 = win_x0 + minloc.x;
        object_y0 = win_y0 + minloc.y;
 
        /* and draw a box there */
        cvRectangle( frame,
                     cvPoint( object_x0, object_y0 ),
                     cvPoint( object_x0 + TPL_WIDTH,
                              object_y0 + TPL_HEIGHT ),
                     cvScalar( 0, 0, 255, 0 ), 1, 0, 0 );
    } else {
        /* if not found... */
        fprintf( stdout, "Lost object.\n" );
        is_tracking = 0;
    }
}
