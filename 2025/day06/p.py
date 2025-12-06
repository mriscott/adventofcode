import fileinput
import pprint
pt1=0
pt2=0

arr=[]

for line in fileinput.input():
	line=line.rstrip()
	row=[]
	a=line.split(" ")
	for x in a:
		x=x.strip()
		if(len(x)!=0):
			row.append(x)
	arr.append(row)	
pprint.pp ( arr )
for x in range(len(arr[0])):
	tot=0
	n=len(arr)-1
	mult=(arr[n][x]=="*")
	if(mult):
		tot=1
	print (f"row {x} {mult}")
	for y in range(n):
		print(arr[y][x])
		if(mult):
			tot=tot*int(arr[y][x])
		else:
			tot=tot+int(arr[y][x])
	pt1=pt1+tot



print (f"Part 1: {pt1}")
print (f"Part 2: {pt2}")
