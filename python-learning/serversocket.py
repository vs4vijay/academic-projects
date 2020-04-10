from socket import *
sock = socket(AF_INET,SOCK_STREAM)
sock.bind(("",4444))
sock.listen(4)
con,cli = sock.accept()
print "Con " + str(con) + " cli " + str(cli)
data = 0
while not data=="":
    data = con.recv(111)
    print data
con.close()
sock.close()
x = input()
