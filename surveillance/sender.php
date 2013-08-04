<?php
/**
 * This script checks for a new image from Frame Grabber, and
 * POST the image to server.
 *
 * Run this script as daemon (background process)
 */
 
$recv = 'http://127.0.0.1/surveillance/receiver.php';
$file = 'img.jpg';
$time = 0;
 
while (true) 
{
    /* wait 1 second */
    sleep(1);
 
    /* don't cache the file status */
    clearstatcache();
 
    /* if newer image exists */
    if (file_exists($file) && filemtime($file) > $time)
    {
        /* POST the file to server */
        send_file($recv, $file);
 
        /* save the file's modification time */
        $time = filemtime($file);
    }
}


/**
 * Function to POST a file to server
 *
 * Parameters:
 * @handler   string  the remote script that handles the incoming file
 * @filename  string  the file to be sent
 */
function send_file($handler, $filename) {
    /* check if file exists */
    if (!file_exists($filename)) return;
 
    /* get hostname and path of remote script */
    $host = parse_url($handler, PHP_URL_HOST);
    $path = parse_url($handler, PHP_URL_PATH);
 
    /* setup default path if empty */
    if (empty($path)) $path = '/';
 
    /* setup request header and body */
    $boundary = "---------0123456789";
    $reqbody  = "--$boundary\r\n"
              . "Content-Disposition: form-data; name=\"img\"; filename=\"$filename\"\r\n"
              . "Content-Type: image/jpeg\r\n\r\n"
              . file_get_contents($filename) . "\r\n"
              . "--$boundary--\r\n";
    $reqhead  = "POST $path HTTP/1.1\r\n"
              . "Host: $host\r\n"
              . "Content-Type: multipart/form-data; boundary=$boundary\r\n"
              . "Content-Length: " . strlen($reqbody) . "\r\n"
              . "Connection: Close\r\n\r\n";
 
    /* open socket connection to remote host on port 80 */
    $fp = fsockopen($host, 80, $errno, $errmsg, 30);
 
    /* check the connection */
    if (!$fp) die("Cannot connect to $host!\n");
 
    /* send request */
    fwrite($fp, $reqhead);
    fwrite($fp, $reqbody);
 
    /* close the connection */
    fclose($fp);
}

?>