import sys
import re
test="xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
test="xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
inp=test

if(len(sys.argv)>1):
	inp=""
	print ("Loading input from "+sys.argv[1])
	f=open(sys.argv[1],"r")
	for x in f:
		inp+=x
	f.close()



hits= (re.findall("mul\(\d+,\d+\)|do\(\)|don\'t\(\)",inp))	
print (hits)
pt1=0
pt2=0
enabled=1
for hit in hits:
	if(hit.startswith("do")):
		if(hit.startswith("don")):
			enabled=0
		else:
			enabled=1
	else:
		a=re.split(",",hit)
		b=int(a[0].replace("mul(",""))
		c=int(a[1].replace(")",""))
		pt1+=(b*c)
		if(enabled==1):
			pt2+=(b*c)

print ("Part 1 : ",str(pt1))
print ("Part 2 : ",str(pt2))
