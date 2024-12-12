import sys

grid=[]

        
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


def addAntiNode(x,y):
	c=charat(x,y)
	if(c=="*"):
		return -1
	if(c!="#"):
		l=list(grid[y])
		l[x]="#"
		grid[y]="".join(l)
		return 1
	return 0

if(len(sys.argv)>1):
	f=open(sys.argv[1],"r")
	for l in  f:
		grid.append(l.strip())
	f.close()


ant={}
for y in range(len(grid)):
	for x in range(len(grid[y])):
		k=charat(x,y)
		if(k!="."):
			if (k in ant):
				ant[k]=f"{ant[k]} {x},{y}"
			else:
				ant[k]=f"{x},{y}"



pt1=0
pt2=0
for k in ant.keys():
	coords=ant[k].split()
	for x in range(len (coords)):
		for y in range(x+1,len(coords)):
			n1=coords[x].split(",")
			n2=coords[y].split(",")
			dx=int(n1[0])-int(n2[0])
			dy=int(n1[1])-int(n2[1])
			i=0
			while(True):
				l=addAntiNode(int(n1[0])+(dx*i),int(n1[1])+(dy*i))
				r=addAntiNode(int(n2[0])-(dx*i),int(n2[1])-(dy*i))

				if(i==1):
					if(l>0):
						pt1+=l
					if(r>0):
						pt1+=r

				if(r==-1 and l==-1):
					break
				i+=1
				
				if(l>0):
					pt2+=l
				if(r>0):
					pt2+=r
			


			

printGrid()




print ("Part 1: ",str(pt1))
if(sys.argv[1]=="test.txt" and pt1!=14):
	print ("PART 1 FAIL")
print ("Part 2: ",str(pt2))
if(sys.argv[1]=="test.txt" and pt2!=34):
	print ("PART 2 FAIL")

