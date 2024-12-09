import sys

pt1=0
pt2=0

if(len(sys.argv)>1):

	f=open(sys.argv[1],"r")
	for l in  f:
		l=l.strip()
		a=l.split(":")
		tgt=int(a[0])
		nums=a[1].split(" ")
		nos=[]
		solvable=0
		for n in nums:
			if(n!=""):
				nos.append(int(n))
				
		for i in range(pow(2,len(nos)-1)):
			tot=nos[0]
			sum=str(nos[0])
			
			for j in range (len(nos)-1):
				if(i & int(pow(2,j))):
					sum+=(f"*{nos[j+1]}")
					tot*=nos[j+1]
				else:
					sum+=(f"+{nos[j+1]}")
					tot+=nos[j+1]
			if(tgt==tot):
				solvable=tot
				
		pt1+=solvable	
	f.close()




print ("Part 1:",pt1)
print ("Part 2:",pt2)
			
