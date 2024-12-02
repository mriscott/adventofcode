def issafe(arr,damp):
	dir=0
		
	for i in range(len(arr)-1):
		d=int(arr[i+1])-int(arr[i])
		ok=1
		if((dir<0 and d>0) or (dir>0 and d<0) or d==0):
			ok=0
		if(abs(d)>3):
			ok=0 
		dir=d
		if(ok==0):
			if(damp==0):
				for j in (0,i,i+1):
					damp=1	
					arr2=arr.copy()
					arr2.pop(j)
					if(issafe(arr2,damp)==1) :
						ok=1
						break
				if(ok==0):
					break
				else:
					break
			break
	return ok

	

f=open("input.txt","r")
#f=open("test.txt","r")
safe1=0
safe2=0
for x in f:
	safe1+=issafe(x.split(),1)
	safe2+=issafe(x.split(),0)

f.close()
print()
print()
print()
print()
print ("Pt1: "+str(safe1))
print ("Pt2: "+str(safe2))

#too low 564
