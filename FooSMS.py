import httplib

no = raw_input('Enter Phone No : ')
msg = raw_input('Now Enter The Message : ')

param='mobile=' + no + '&message=' + msg + '&flash=1&limit=' + str(140 - len(msg))+ '&Submit=Send+SMS'

headers = {'Host': 'www.foosms.com',
           'User-Agent': 'Mozilla/5.0 (Windows XP) Firefox/3.6.8',
           'Accept': 'text/plain',
           'Accept-Language': 'en-us,en;q=0.5',
           'Accept-Encoding': 'gzip,deflate',
           'Accept-Charset': 'ISO-8859-1,utf-8;q=0.7,*;q=0.7',
           'Keep-Alive': '115',
           'Connection': 'keep-alive',
           'Referer': 'http://www.foosms.com/inner.php',
           'Content-Type': 'application/x-www-form-urlencoded',
           'Content-Length': str(len(param))}

con = httplib.HTTPConnection('www.foosms.com')
con.request("POST","/sendsms.php",param,headers)
r1 = con.getresponse()
data = r1.read()
con.close()
if not data.find('SUCCESS') == -1:
    print '[+]Message Sent...'
print data
x = raw_input()
