f = open("input.txt", "r")
a1=[]
a2=[]
for x in f:
  a=x.split()
  a1.append(a[0])
  a2.append(a[1])
  
a1.sort()
a2.sort()


tot=0
for x in range(len(a1)):
  tot+=abs(int(a1[x])-int(a2[x]))
  
print ("Part 1:")  
print(tot)

sim=0
for x in range(len(a1)):
    m=0
    r=int(a1[x])
    for y in range(len(a2)):
        if(r==int(a2[y])):
            m+=1

    sim+=(r*m)

print ("Part 2:")
print (sim)


