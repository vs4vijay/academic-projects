import urllib,os
site = raw_input('Enter The Website(Without http://) : ')
page=urllib.urlopen('http://' + site)
data = page.read()
print data
x = raw_input("Data : "+ str(len(data)) +" Bytes. Do u wanna Save this data...")
save = open('urlfetch.html','w')
save.write(data)
save.close()
os.startfile('urlfetch.html')
