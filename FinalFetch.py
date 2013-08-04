from Tkinter import *
from urllib import *
import re,sys
res = ' '
r = ''
rn = ''
rollnext = ''
def result(r):
    param = 'trl=' + r
    #site='http://localhost:8080/PythonTest/result.html'
    #site='http://www.rtu.ac.in/results/fetchmodule_btech109reval.php'
    site='http://www.rtu.ac.in/results/fetchmodulesem_4_btech410m.php'
    try:
        page = urlopen(site,param)
    except:
        var.set('[#] I think you\'re NOT CONNECTED to internet...')
        label1['bg']='cyan'
        return ""

    data1 = page.read()
    data = re.sub('<[^>]*>','',data1)

    if data.find('Record not found') == 3:
        label1['bg']='red'
        var.set(r + ':Record_Not_Found.... ')
        return r + ':Record_Not_Found.... '
    else:
        n=data.find('Name')
        p=data.find('GTotal')
        name1 = data[n+10:n+20]
        t = data[p+13:p+16]
        name = name1.replace(" ",'_').replace("\n",'')
        if data.find('PASS'):
            var.set("[+] "+ name +" PASS : Total => " + t)
            label1['bg']='green'
            print name + ' : PASS : ' + t
            res = r +":"+ name + ':PASS:' + str(t) + " " 
        else:
            var.set("[-] "+ name +" FAIL : Total => " + t)
            label1['bg']='yellow'
            print name + ' : FAIL : ' + t
            res = r + name + ':FAIL:' + str(t) + " "
        print "---------------------------------------"
        return res

def call():
    rollnext.set(inc(rollnext.get()))

def inc(incr):
    a = incr[:-3]
    b = incr[-3:]
    if(incr[-3] == '0'):
        if(incr[-2] == '0'):
            c = str(eval(b.replace('0',''))+1)
        else:
            c = str(eval(re.sub('^0','',b))+1)
        c = c.zfill(3)
    else:
        c = str(eval(b)+1)
    return a + c
    
def submit():
    global r,res,rn
    rn = rollnext.get()
    r = roll.get()
    while not r > rn:
        res = res + result(r)
        r = inc(r)
        listz.set(res)
        
def save():
    fop = open('Result.txt','a')
    newres = res.replace(' ','\n')
    fop.write(newres)
    fop.close()
    
def clear():
    listz.set("")
    
root = Tk()
root.title('Fetch Result From RTU')
#root.wm_iconbitmap('a.ico')

var = StringVar()
roll = StringVar()
roll.set('08EEACS001')
rollnext = StringVar()
rollnext.set(roll.get())
listz = StringVar()
r = roll.get()
rn = rollnext.get()

label = Label(root,text="Enter Roll. No : ")
label.grid(column=0,row=0,pady=5)

entry = Entry(root,textvar=roll)
entry.grid(column=1,row=0,padx=5)

label2 = Label(root,text="Next Roll. No : ")
label2.grid(column=0,row=1,pady=5)

spin = Spinbox(root,textvar=rollnext,command=call)
spin.grid(column=1,row=1)

lista = Listbox(root,listvariable=listz,bg='gray',width=35,highlightbackground='red',highlightthickness=9,highlightcolor='blue')
lista.grid(column=3,row=0,rowspan=4)

frame = Frame(root)
frame.grid(column=0,row=2,columnspan=2)

button = Button(frame,text='Submit',command=submit)
button.grid(column=0,row=0,padx=5)

button1 = Button(frame,text='Save',command=save)
button1.grid(column=1,row=0,padx=5)

button2 = Button(frame,text='Show',command=clear)
button2.grid(column=2,row=0,padx=5)

label1 = Label(root,textvar=var)
label1.grid(column=0,row=3,columnspan=2)

root.mainloop()




