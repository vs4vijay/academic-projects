from Tkinter import *
master = Tk()
z = Canvas(master,width=333,height=333,cursor='heart')
z.pack()
z.create_line(0,0,222,222)
z.create_line(0,111,222,0,fill="red",dash=(4,4))
z.create_text(11,11,text="Viz")
z.create_rectangle(55,22,33,77,fill="green")
master.mainloop()
