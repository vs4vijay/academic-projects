from Tkinter import *
viz = Tk()
viz.title('viz here')
entry = Entry(viz)
entry.grid(column=0,row=0)
def onClick():
        print "You clicked the button !"
button = Button(viz,text='Click Me',fg="white",bg="red",command=onClick)
button.grid(column=2,row=1)
label = Label(viz,text='Click This :=>',fg="black",bg="green")
label.grid(column=1,row=1)
viz.mainloop()
x = input()
