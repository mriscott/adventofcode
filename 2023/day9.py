import re

def getLastNum(t):
    print("Start:"+t)
    t=re.sub(" +"," ",t)
    nos=[]
    for x in t.split(" "):
        nos.append(int(x))
    lastno=nos[-1]
    tot=1
    diffs=[]
    while(tot!=0):
        print(nos)
        tot=0
        newnos=[]
        for x in range(len(nos)-1):
            n=nos[x+1]-nos[x]
            newnos.append(n)
            tot+=n
        diffs.append(newnos[-1])
        if(tot==0):
            print(newnos)
            break
        nos=newnos

    print (diffs)
    mno=lastno
    for x in diffs:
        mno+=x
    print ("Answer:"+str(mno))
    return(mno)

f = open("day9/test", "r")
tot=0
for x in f:
    tot+=getLastNum(x)
f.close()

if(tot!=114):
    print( "Part 1 test failed")
    exit


f = open("day9/input", "r")
tot=0
for x in f:
    tot+=getLastNum(x)
f.close()

print("Part 1: "+str(tot))
    

        
        

