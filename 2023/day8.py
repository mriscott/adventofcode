import re
mapr=dict()
mapl=dict()


dir=""
f = open("day8/input", "r")
for x in f:
    x=x.rstrip()
    if(dir==""):
        dir=x
    m=re.match(r"([A-Z]+) = \(([A-Z]+), ([A-Z]+)\)",x)
    if(m):
        mapl[m.group(1)]=m.group(2)
        mapr[m.group(1)]=m.group(3)
               
elem="AAA"
i=0
n=0

while(elem!="ZZZ"):
    if(dir[n]=="L"):
       elem=mapl[elem]
    else:
       elem=mapr[elem]
    n+=1
    if(n==len(dir)):
        n=0
    i+=1

print (f"Part 1 : {i}")
    

        
            
