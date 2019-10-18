from Tkinter import *
root = Tk()
root.title('Hacked...')
frame = Frame().pack()
label = Label(root,text="Hacked..").pack()
entry = Entry(root)
button = Button(root,text="Dont Click!!!",command=root.quit).pack()
root.mainloop()
