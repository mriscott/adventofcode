import fileinput
p1=0
p2=0

def getMax(str,start,end):
	max='0'
	maxidx=-1
	for i in range(start,end):
		if(str[i]>max):
			maxidx=i
			max=str[i]
			if(max=='9'):
				return maxidx
	return maxidx


def getMaxStr(str,size):
	max=""
	maxi=-1
	for i in range(size):
		maxi=getMax(line,maxi+1,len(line)-size+i+1)
		max=max+str[maxi]
		
	return max
		
for line in fileinput.input():
	line=line.rstrip()
	max=int(getMaxStr(line,2))
	p1=p1+max

	max=int(getMaxStr(line,12))
	p2=p2+max

print ("Part 1: ", p1)
print ("Part 2: ", p2)

