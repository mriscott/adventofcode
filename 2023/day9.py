import re


def notZeros(a):
  for x in a:
    if(x!=0):
      return True
  return False

def getNextRow(nos):
	newnos=[]
	for x in range(len(nos)-1):
		n=nos[x+1]-nos[x]
		newnos.append(n)
	return newnos

def getLastNum(t):
    t=re.sub(" +"," ",t)
    nos=[]
    for x in t.split(" "):
        nos.append(int(x))
    lastno=nos[-1]
    tot=1
    diffs=[]
    while(notZeros(nos)):
        nos=getNextRow(nos)
        for x in nos:
            tot+=x
        diffs.append(nos[-1])

    mno=lastno
    for x in diffs:
        mno+=x
    return(mno)

def getHistory(t):
    starts=[]
	
    t=re.sub(" +"," ",t)
    nos=[]
    for x in t.split(" "):
        nos.append(int(x))
    lastno=nos[-1]
    tot=1

    diffs=[]
    while(notZeros(nos)):
        starts.append(nos[0])
        nos=getNextRow(nos)
    hist=[0]
    r=1
    for r in range(1,len(starts)+1):
       l=starts[len(starts)-r]
       s=l-hist[r-1]
       hist.append(s)
    return s

f = open("day9/test", "r")
pt1=0
pt2=0
for x in f:
    pt1+=getLastNum(x)
    pt2+=getHistory(x)
f.close()

if(pt1!=114):
    print( "Part 1 test failed")
    exit

if(pt2!=2):
    print( "Part 2 test failed")
    exit

f = open("day9/input", "r")
pt1=0
pt2=0
for x in f:
    pt1+=getLastNum(x)
    pt2+=getHistory(x)
f.close()

print("Part 1: "+str(pt1))
print("Part 2: "+str(pt2))
    

        
        

