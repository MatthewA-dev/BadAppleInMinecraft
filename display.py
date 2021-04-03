from os import system
import os
import time
def getNum(element):
    return int(element[:-4])
clear = lambda: system('cls')
os.chdir("./bad")
fs = os.listdir()
fs.sort(key=getNum)
for f in fs:
    fil = open(f,"rb")
    text = fil.read()
    print(text.decode("utf8"))
    fil.close()
    #clear()
    time.sleep(0.033333)