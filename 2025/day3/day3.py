import fileinput
p1=0
p2=0
for line in fileinput.input():
	line=line.rstrip()
	max=0
	for x in range(len(line)):
		for y in range(x+1,len(line)):
			num=int(line[x]+line[y])
			if(num>max):
				max=num	

	print (line," -> ",max)
	p1=p1+max

print ("Part 1: ", p1)

