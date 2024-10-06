# test
races=[[7,9],[15,40],[30,200]]
races2=[71530,940200]

#input
races=[
[60,476],
[94,2138],
[78,1015],
[82,1650]
]

races2=[60947882,475213810151650]


def getWins(t,r):
  w=0
  for x in range(t):
    s=x
    d=s*(t-x)
    if(d>r):
      w+=1
    #print (f"hold {x} => speed {s} => dist {d}")
  return w
  
 
p=1
for x in races:
  w=getWins(x[0],x[1])
  p*=w
  
print (f"Part 1:{p}")

w=getWins(races2[0],races2[1])

print (f"Part 2:{w}")

  

  