import sys

turndict= {
        "U":"R",
        "R":"D",
        "D":"L",
        "L":"U"
        }
grid=[]
sx=-1
sy=-1
if(len(sys.argv)>1):
	f=open(sys.argv[1],"r")
	for l in  f:
		grid.append(l.strip())
	f.close()

        
def charat(x,y):
	if(x<0 or y<0 or y>=len(grid)):
		return '*'
	s=grid[y]
	if(x>=len(s)):
		return '*'
	return s[x]

def movex(x,d):
        if(d=="L"):
                x-=1
        if(d=="R"):
                x+=1
        return x

def movey(y,d):
        if(d=="U"):
                y-=1
        if(d=="D"):
                y+=1
        return y


def strpos(x,y):
        return f"({x},{y})"

# find start pos
posx=0
posy=0
for r in grid:
        print(r)
        if("^" in r):
                posx=r.index("^")
                break
        posy+=1

        d="U"

        
visits={strpos(posx,posy)}
while(True):
        xx=movex(posx,d)
        yy=movey(posy,d)
        #print(strpos(posx,posy)+"->"+strpos(xx,yy)+" ("+d+")")
        if(charat(xx,yy)=="*"):
                break
        if(charat(xx,yy)=="#"):
                d=turndict[d]
        else:
                posx=xx
                posy=yy
                visits.add(strpos(posx,posy))

        



pt1=len(visits)
pt2=0


print ("Part 1: ",str(pt1))
print ("Part 2: ",str(pt2))

