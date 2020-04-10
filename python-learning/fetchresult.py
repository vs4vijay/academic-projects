from urllib import *
import re,sys
r = raw_input("Enter Your Roll No : ")
if r == '': r = '08EEACS098'
param = 'trl='+r
site='http://www.rtu.ac.in/results/fetchmodule_btech109.php'

page = urlopen(site,param)

data1 = page.read()

data = re.sub('<[^>]*>','',data1)

if data.find('Record not found') == 3:
    print '[#]OOPs Record Not Found....'
else:
    n=data.find('Name')
    p=data.find('GTotal')
    print '==========================='
    print '[+]Roll No : ' + r
    print '[+]Name : ' + data[n+10:n+21]
    if data.find('PASS'):
        print "[+]PASS : Total => " + data[p+13:p+16]
    else:
        print "[-]FAIL : Total => " + data[p+13:p+16]
    print "==========================="

    f = open('file.html','w')
    f.write(data)
    f.close()
x = raw_input()
