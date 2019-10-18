from ctypes import *
windll.LoadLibrary("user32")
windll.LoadLibrary("kernel32")
windll.user32.MessageBoxA(0,"Vizay Here...","Python Rocks",0)
i = 0
while i < 555:
    windll.user32.SetCursorPos(i,i)
    windll.kernel32.Sleep(9)
    i=i+1
