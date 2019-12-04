def checknum(str):
  last=0
  d=0
  r=1
  for c in str:
    i=int(c)
    if(i<last):
      return 0
    if (last==i):
       r=r+1
    else:
        if(r==2):
            d=1
        r=1
    last=i
  if(r==2):
        d=1
  return d

tot=0
print checknum ('112233')
print checknum ('123444')
print checknum ('111122')


for x in range (171309,643603):  
        tot+=checknum(str(x))
print (tot)
