import fileinput
pt1=0

def checkNum(num):
	snum=str(num)
	l=len(snum)
	mid=int(l/2)
	if (l%2==1):
		return True
	if(snum[:mid] == snum[mid:]):
		return False
	return True

for line in fileinput.input():
	ranges=line.rstrip().split(",")
	for therange in ranges:
		ends=therange.split("-")
		for x in range(int(ends[0]),(int(ends[1])+1)):
			if(checkNum(x)==False):
				pt1=pt1+x
print (f"Pt1: {pt1}")
