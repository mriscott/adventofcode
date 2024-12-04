import sys

target="XMAS"
grid=[]
if(len(sys.argv)>1):
	inp=""

	f=open(sys.argv[1],"r")
	for l in  f:
		grid.append(l.strip())
	f.close()


def charat(x,y):
	if(x<0 or y<0 or y>=len(grid)):
		return '.'
	s=grid[y]
	if(x>=len(s)):
		return '.'
	return s[x]


def checkWord(x,y,dx,dy,c):
	x+=dx
	y+=dy
	c+=1
	if(c==len(target)):
		return True
	if(charat(x,y)==target[c]):
		return checkWord(x,y,dx,dy,c)
	return False


# return no of words found starting at x,y
def search(x,y):
	m=0
	if(charat(x,y)!=target[0]):
		 return False
	for dx in (1,0,-1):
		for dy in (1,0,-1):
			if(checkWord(x,y,dx,dy,0) ):
				m+=1	
	return m


def searchx(x,y):
	a1=charat(x-1,y-1)+charat(x+1,y+1)
	a2=charat(x-1,y+1)+charat(x+1,y-1)
	if((a1=="SM" or a1=="MS") and (a2=="SM" or a2=="MS")):
		return 1
	return 0

pt1=0
pt2=0
for y in range(len(grid)):
	s=grid[y]
	for x in range(len(s)):
		if(s[x]==target[0]):
			pt1+=search(x,y)
		if(s[x]=='A'):
			pt2+=searchx(x,y)
	


print ("Part 1: ",str(pt1))
print ("Part 2: ",str(pt2))

