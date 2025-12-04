import fileinput

pt1=0
pt2=0


arr=[]


def isRoll(x,y):
	if(y<0 or y>=len(arr)):
		return False
	if(x<0 or x>=len(arr[y])):
		return False
	return (arr[y][x]=='@')	

for line in fileinput.input():
	line=line.rstrip()
	arr.append(list(line))

for y in range(0,len(arr)):
	for x in range(0, len(arr[y])):
		if(isRoll(x,y)==False):
			continue
		tot=0
		for xx in range(-1,2):
			if(tot==4):
				break
			for yy in range(-1,2):
				if(xx==0 and yy==0):
					continue
				if(isRoll(x+xx,y+yy)):
					tot=tot+1
					if(tot==4):
						break
		if(tot<4):
			pt1=pt1+1
		

print("Part 1 : "+str(pt1))	
