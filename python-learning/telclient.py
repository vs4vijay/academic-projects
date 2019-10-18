from socket import *
sock = socket(AF_INET,SOCK_STREAM)
sock.connect(("127.0.0.1",23))
sock.send('y')
data = sock.recv(1024)
print data.encode('hex')
x = input()
