test="2333133121414131402"

input=""
f=open ("input.txt","r")
for x in f:
	input=input+x.strip()
f.close()

disk=[]
def createDisk(str):
	bnum=0
	for x in range(0,len(str),2):
		l=int(str[x])
		for ll in range(l):
			disk.append(bnum)
			
		if((x+1)<len(str)):
			l=int(str[x+1])
			for ll in range(l):
				disk.append(-1)

		bnum+=1


def printDisk():
	o=""
	for x in disk:
		if(x==-1):
			o+="."
		if(x>=0 and x<=9):
			o+=str(x)
		if(x>9):
			n=x%26
			o+=chr(ord("a")+n)
		
	print(o)
		
def defrag():
	for x in range(len(disk)-1,-1,-1):
		if(x%1000==0):
			print(x)
		if(disk[x]!=-1):
			for y in range(x):

				if(disk[y]==-1):
					disk[y]=disk[x]
					disk[x]=-1
					break

def checksum():
	tot=0
	for x in range(len(disk)):
		if(disk[x]!=-1):
			tot+=(x*disk[x])
	return tot




print("Tests")
disk=[]
createDisk(test)
printDisk()
defrag()
printDisk()
pt1=checksum()
print ("Part 1: "+str(pt1))
if pt1!=1928:
	print ("FAIL") 

print("Input")

disk=[]
createDisk(input)
defrag()
pt1=checksum()
print ("Part 1: "+str(pt1))

