test="2333133121414131402"
test2="2422222020"

input=""
f=open ("input.txt","r")
for x in f:
	input=input+x.strip()
f.close()

disk=[]
def createDisk(str):
	disk.clear()
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

def defrag2():
	prev=-1
	count=0
	for x in range(len(disk)-1,-1,-1):
		if(x%1000==0):
			print(x)

		if(disk[x]==prev and prev!=-1):
			count+=1
			next

		if(prev!=-1 and disk[x]!=prev):
			count+=1
			spc=0
			if(prev!=-1):
				for y in range(x+1):
					if(disk[y]==-1):
						spc+=1
						if(spc==count):
							for i in range(count):
								disk[y-i]=prev
								disk[x+i+1]=-1
							count=-1


					else:
						spc=0
			count=0
		#printDisk()
		prev=disk[x]

def checksum():
	tot=0
	for x in range(len(disk)):
		if(disk[x]!=-1):
			tot+=(x*disk[x])
	return tot




print("Tests")
disk=[]
createDisk(test)
defrag()
pt1=checksum()
print ("Part 1: "+str(pt1))
if pt1!=1928:
	print ("FAIL") 



createDisk(test)
printDisk()
defrag2()
pt2=checksum()
print ("Part 2: "+str(pt2))
if pt2!=2858:
	print ("FAIL") 

createDisk(test2)
printDisk()
defrag2()
pt2=checksum()

print("Input")

createDisk(input)
#defrag()
pt1=checksum()
print ("Part 1: "+str(pt1))
disk=[]

createDisk(input)
defrag2()
pt2=checksum()
print ("Part 2: "+str(pt2))
print ("6323761820201 too high")
print ("15711394184427 much too high")
