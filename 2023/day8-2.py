import re
mapr=dict()
mapl=dict()

class Line:
    start=""
    repstart=-1
    replen=-1
    zindex=-1

    def __str__(self):
        return f"{self.start}:{self.repstart},{self.replen},{self.zindex}"

    def isz(self,n):
        n-=self.repstart
        n%=self.replen
        #print(f"{n} vs {self.zindex}")
        return(n==self.zindex)
    
dir=""
print("Reading file")
f = open("day8/input", "r")
for x in f:
    x=x.rstrip()
    if(dir==""):
        dir=x
    m=re.match(r"([0-9A-Z]+) = \(([0-9A-Z]+), ([0-9A-Z]+)\)",x)
    if(m):
        mapl[m.group(1)]=m.group(2)
        mapr[m.group(1)]=m.group(3)

elems=[]
starts=[]
for x in mapr.keys():
    if(re.search("A$",x)):
       elems.append(x)
       starts.append(x)
    
i=0
n=0

lines=dict()

print ("Finding loops")
# find the loops
for x in range(len(elems)):
    i=0
    n=0
    vals=[]
    elem=elems[x]
    print(elem)
    myline=Line()
    myline.start=elem
    lines[elem]=myline
    while(True):
        vals.append(f"{elem}:{n}")
        i=i+1
        if(dir[n]=="L"):
            elem=mapl[elem]
        else:
            elem=mapr[elem]
        n+=1
        if(n==len(dir)):
            n=0
        if(elem[-1]=="Z"):
            myline.zindex=i
            print(f"{myline.start}=>{elem} @ {i}")

        if(f"{elem}:{n}" in vals):
            # now find start
            for ii in range(i):
                if(vals[ii]==f"{elem}:{n}"):
                    myline.repstart=ii
                    myline.replen=(i-ii)
                    myline.zindex-=myline.repstart
                    print(f"Repeat starts @ {ii} = zindex {myline.zindex}") 
                    break
            print(myline)
            break


        
# check Z finding
for x in range(len(elems)):
    elem=elems[x]
    print ("Checking Zs in "+elem)
    line=lines[elem]
    n=0
    i=0
    h=0
    while(h<3):
        #    print(f"{elem}:{i} - {line.isz(i)}")
        i=i+1
        if(dir[n]=="L"):
            elem=mapl[elem]
        else:
            elem=mapr[elem]
        n+=1
        if(n==len(dir)):
            n=0
        if(elem[-1]=="Z"):
            if (line.isz(i)):
                print (f"{elem} - good - zindex {line.zindex}, myindex {i}")
                h=h+1
            else:
                print (f"{elem} - BAD - zindex {line.zindex}, myindex {i}")
                h=h+1
            

# and finaly find the answer
print ("And finding all the Zs")
elem1=lines[elems[0]]
i=elem1.repstart
i+=elem1.zindex
while(True):
    #print (f"Trying {i}")
    isok=True
    for x in elems:
        if(lines[x].isz(i) == False):
           isok=False
           #print(lines[x].start+" isnt z")
           break
    if(isok):
        print (f"Part 2 : {i}")
        break
    i+=elem1.replen



            
