import fileinput

pt1=0
pt2=0

ranges=[]

for line in fileinput.input():
	line=line.rstrip()
	if ("-" in line):
		ranges.append(line.split("-"))
		continue

	if(line==""):
		continue

	num=int(line)
	for r in ranges:
		if(num>=int(r[0]) and num <=int(r[1])):
			print(num)
			pt1=pt1+1
			break


print ("Part 1: "+str(pt1))
print ("Part 2: "+str(pt2))
