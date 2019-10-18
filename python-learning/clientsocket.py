from socket import *
sock = socket(AF_INET,SOCK_STREAM)
sock.connect(("127.0.0.1",4444))
data = raw_input()
while not data=="":
    sock.send(data)
    data = raw_input()
sock.close()
x = input()
