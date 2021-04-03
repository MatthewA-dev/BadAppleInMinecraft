import os
from PIL import Image
import math
def calcdistance(point1, point2):
    return math.sqrt((point1[0] - point2[0])**2 + (point1[1] - point2[1])**2 + (point1[2] - point2[2])**2)
if("badapple" not in os.listdir()):
    os.mkdir("badapple")
    os.chdir("./badapple")
    os.system("")
else:
    os.chdir("./badapple")
files = os.listdir()
for f in files:
    if(f[-4:] != ".jpg"):
        continue
    img = Image.open(f)
    img = img.resize((88,72))
    p = open("../bad/" + f[:-4] + ".txt","bw")
    print(f"processing {f[:-4]}.txt")
    for y in range(img.height):
        for x in range(img.width):
            white = (255,255,255)
            black = (0,0,0)
            pixel = img.getpixel((x,y))
            if(calcdistance(white,pixel) < calcdistance(black,pixel)):
                p.write("█".encode("utf8"))
            else:
                p.write("▒".encode("utf8"))
        p.write("\n".encode("utf8"))
    p.flush()
    p.close()
    img.close()
    exit()