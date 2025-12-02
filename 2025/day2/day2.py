import fileinput
pt1=0
pt2=0

def checkNum(num):
	snum=str(num)
	l=len(snum)
	mid=int(l/2)
	if (l%2==1):
		return True
	if(snum[:mid] == snum[mid:]):
		return False
	return True

def checkNum2(num):
	snum=str(num)
	for l in range(1,int(len(snum)/2)+1):
		if(len(snum)%l !=0):
			continue
		invalid=True
		slice=snum[:l]
		for y in range(l,len(snum),l):
			bit=snum[y:y+l]
			if(bit!=slice):
				invalid=False
				break
		if(invalid):           
			return False
	return True


for line in fileinput.input():
	ranges=line.rstrip().split(",")
	for therange in ranges:
		ends=therange.split("-")
		for x in range(int(ends[0]),(int(ends[1])+1)):
			if(checkNum(x)==False):
				pt1=pt1+x
			if(checkNum2(x)==False):
				pt2=pt2+x
print (f"Pt1: {pt1}")
print (f"Pt2: {pt2}")
