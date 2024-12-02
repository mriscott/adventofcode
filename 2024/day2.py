def issafe(line):
	arr=x.split()
	dir=0
	for i in range(len(arr)-1):
		d=int(arr[i+1])-int(arr[i])
		if((dir<0 and d>0) or (dir>0 and d<0) or d==0):
			return 0
		if(abs(d)>3):
			return 0
		dir=d
	return 1

	

f=open("input.txt","r")
safe=0
for x in f:
	safe+=issafe(x)

f.close()
print ("Pt1: "+str(safe))
