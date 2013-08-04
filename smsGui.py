import httplib
from Tkinter import *


root = Tk()
root.title('Send SMS App By Vizay')
no = StringVar()
msg = StringVar()
status = StringVar()


label = Label(root,text='Enter Phone No : ')
label.grid(column=0,row=0,pady=5)

entry = Entry(root,textvar=no)
entry.grid(column=1,row=0,padx=5)

label1 = Label(root,text='Enter The Message : ')
label1.grid(column=0,row=1)

#entry1 = Entry(root,textvar=msg)
#entry1.grid(column=1,row=1)

text = Text(root,height=4,width=15)
text.grid(column=1,row=1,rowspan=2,padx=5)

label2 = Label(root,textvar=status)
label2.grid(column=0,row=4,columnspan=2)

def send():
    no1 = no.get()
    msg1 = msg.get()
    param='mobile=' + no1 + '&message=' + msg1 + '&flash=1&limit=' + str(140 - len(msg1))+ '&Submit=Send+SMS'

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
    try:
        con.request("POST","/sendsms.php",param,headers)
        r1 = con.getresponse()
        data = r1.read()
        con.close()
        if not data.find('SUCCESS') == -1:
            status.set('[+]Message Sent...')
            label2['bg'] = 'green'
    except:
        status.set('[#] I think you\'re NOT CONNECTED to internet...')
        label2['bg'] = 'yellow'
        

button = Button(root,text='Send',command=send)
button.grid(column=0,row=3,columnspan=2,padx=5,pady=5)

root.mainloop()




