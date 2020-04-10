from hashlib import *
name = raw_input('Enter ur name : ')
print 'MD5 Hash of %s is %s' % (name,md5(name).digest().encode('hex'))
x = input()
