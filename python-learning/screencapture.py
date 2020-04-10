from ctypes import *

class Bitmap(Structure):
    _fields_ = [("bitmapType", c_long),
                ("width", c_long),
                ("height", c_long),
                ("widthBytes", c_long),
                ("planes", c_short),
                ("bitsPerPixel", c_short)
                ("data", POINTER(c_ulong))]


if __name__ == "__main__":
    user32 = WinDLL("user32.dll")
    gdi32 = WinDLL("gdi32.dll")
   
    #Constants
    SM_CXSCREEN = 0
    SM_CYSCREEN = 1
    SRCCOPY = 0xCC0020
   
    #Capture the Bitmap
    width = user32.GetSystemMetrics(SM_CXSCREEN)
    height = user32.GetSystemMetrics(SM_CYSCREEN)
    screenDC = user32.GetWindowDC(user32.GetDesktopWindow())
    captureDC = gdi32.CreateCompatibleDC(screenDC)
    captureBitmap = gdi32.CreateCompatibleBitmap(screenDC, width, height)
    gdi32.SelectObject(captureDC, captureBitmap)
    gdi32.BitBlt(captureDC, 0, 0, width, height, screenDC, 0, 0, SRCCOPY)

    picture = Bitmap()
    gdi32.GetObjectA( captureBitmap, 24, byref(picture))
