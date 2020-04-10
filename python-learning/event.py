from Tkinter import *
import tkColorChooser
import random
root = Tk()
can = Canvas(root,width=444,height=444,bg='green')
c = 0
def draw(event):
    global can
    global c
    if c == 0: can.create_line(33,33,event.x,event.y,fill='red')
    elif c ==1: can.create_line(33,33,event.x,event.y,fill='white')
    elif c == 2: can.create_line(33,33,event.x,event.y,fill='black')
    elif c == 3: can.create_line(33,33,event.x,event.y,fill='blue')
    elif c == 4: can.create_line(33,33,event.x,event.y,fill='yellow')
    elif c == 5: can.create_line(33,33,event.x,event.y,fill='pink')
    else:
        creare_oval(event.x-20,event.y-20,event.x+20,event.y+20)
    
def draw1(event):
    global c
    c = random.randrange(0,7)

def draw3(event):
    global c
    c=tkColorChooser.askcolor(color='blue')

can.pack()
can.create_line(22,22,333,333)
root.bind("<Motion>",draw)
root.bind("<Button-1>",draw1)
root.bind("<Button-3>",draw3)
root.mainloop()
