import sys


def solvable(nos, tgt):

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
			return True	
	return False


def tobase(n,b):
        s=""
        while(n!=0):
                s=str(n%b)+s
                n=int(n/b)
        return s

def solvable(nos,tgt,base):
	print(nos)
	print(tgt,base)
	for i in range(pow(base,len(nos)-1)):
		tot=nos[0]
		sum=str(nos[0])
		ii=tobase(i,base)
		
		for j in range (len(nos)-1):
			if(j>=len(ii) or ii[-j-1]=="0"):	
				sum+=(f"*{nos[j+1]}")
				tot*=nos[j+1]
			elif(ii[-j-1]=="1"):	
				sum+=(f"+{nos[j+1]}")
				tot+=nos[j+1]
			elif(ii[-j-1]=="2"):
				sum+=(f"|{nos[j+1]}")
				tt=str(tot)
				tt+=str(nos[j+1])
				tot=int(tt)
		sum=sum+"="+str(tot)
		if(tgt==tot):
			return True	
	return False
			
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
		for n in nums:
			if(n!=""):
				nos.append(int(n))
		if(solvable(nos,tgt,3)):		
			pt2+=tgt	
			if(solvable(nos,tgt,2)):		
				pt1+=tgt	
	f.close()




print ("Part 1:",pt1)
if((sys.argv[1]=="test.txt" and pt1!=3749) or (sys.argv[1]=="input.txt" and pt1!=6392012777720)):
	print ("Pt1 test failed")

print ("Part 2:",pt2)
			
