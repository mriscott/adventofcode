f = open("test.txt", "r")
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
  
  
print(tot)
