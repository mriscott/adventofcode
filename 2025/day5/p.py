import fileinput

pt1=0
pt2=0

ranges=[]


def fixRanges():
	changes=True
	while (changes):
		changes=False
		for r1 in ranges:
			for r2 in ranges:
				thischange=False
				if(r1==r2):
					continue
				if(r1[0]==-1 or r1[1]==-1 or r2[0]==-1 or r2[1]==-1):
					continue
				if (len(r1)!=2 or len(r2)!=2 ):
					continue
				if(r1[0]<=r2[0] and r1[1]>=r2[0]):
					r2[0]=r1[0]
					thischange=True
				if(r1[1]>=r2[1] and r1[0]<=r2[1]):
					r2[1]=r1[1]
					thischange=True
				if(thischange):
					changes=True
					r1[0]=-1
					r1[1]=-1


for line in fileinput.input():
	line=line.rstrip()
	if ("-" in line):
		arr=line.split("-")
		arr[0]=int(arr[0])
		arr[1]=int(arr[1])
		if(arr[1]<=arr[0]):	
			print(arr)
		ranges.append(arr)
		continue
	if(line==""):
		print ("----")
		fixRanges()
		continue

	num=int(line)
	for r in ranges:
		if(num>=r[0] and num <=r[1]):
			pt1=pt1+1
			break

		
ranges.sort()
rlast=[]
for r in ranges:
	if (r[0]==-1):
		continue
	if(r == rlast):
		continue
	rlast=r
	print(r)
	diff=r[1]-r[0]
	diff=diff+1
	pt2=pt2+diff
print ("Part 1: "+str(pt1))
print ("Part 2: "+str(pt2))

