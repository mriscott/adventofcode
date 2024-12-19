import sys
# heads unique per 0
grid=[]
heads=set()

def addhead(s):
	heads.add(s)
        
def charat(x,y):
	if(x<0 or y<0 or y>=len(grid)):
		return '*'
	s=grid[y]
	if(x>=len(s)):
		return '*'
	return s[x]

def printGrid():
	for l in grid:
		print(l)


def trail(k,x,y):	
	s=0
	k2=str(int(k)+1)
	if(k=="9"):
		addhead(f"{x},{y}")
		return 1
	if(charat(x-1,y)==k2):
		s+=trail(k2,x-1,y)
	if(charat(x+1,y)==k2):
		s+=trail(k2,x+1,y)
	if(charat(x,y+1)==k2):
		s+=trail(k2,x,y+1)
	if(charat(x,y-1)==k2):
		s+=trail(k2,x,y-1)
	return s
	
	


if(len(sys.argv)>1):
	f=open(sys.argv[1],"r")
	for l in  f:
		grid.append(l.strip())
	f.close()


pt1=0
pt2=0


for y in range(len(grid)):
	for x in range(len(grid[y])):
		k=charat(x,y)
		if(k=="0"):
			heads.clear()
			pt2+=trail(k,x,y)
			pt1+=len(heads)


print ("Part 1: ",str(pt1))
if(sys.argv[1]=="test.txt" and pt1!=36):
	print ("PART 1 FAIL")
print ("Part 2: ",str(pt2))
if(sys.argv[1]=="test.txt" and pt2!=81):
	print ("PART 2 FAIL")


