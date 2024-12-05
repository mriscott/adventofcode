import sys

rules=[]
pages=[]

if(len(sys.argv)>1):

	f=open(sys.argv[1],"r")
	for l in  f:
		l=l.strip()
		if "|" in l:
			rules.append(l.split("|"))
		if "," in l:
			pages.append(l.split(","))
			
	f.close()


pt1=0
pt2=0

for p in pages:
	ok=True
	ordered=False
	while (ordered==False):
		ordered=True
		for r in rules:
			if (r[0] in p and r[1] in p):
				if(p.index(r[0])>p.index(r[1])):
					ok=False
					ordered=False
					i=p.index(r[0])
					ii=p.index(r[1])
					p[i]=r[1]
					p[ii]=r[0]

	mid=int((len(p)-1)/2)
	if(ok):
		pt1+=int(p[mid])
	else:
		pt2+=int(p[mid])


print ("Part 1:",int(pt1))
print ("Part 2:",int(pt2))
			
