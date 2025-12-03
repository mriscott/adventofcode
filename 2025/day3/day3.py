import fileinput
p1=0
p2=0

for line in fileinput.input():
	line=line.rstrip()
	max=0
	for x in range(len(line)):
		for y in range(x+1,len(line)):
			for z in range(y+1,len(line)):
				for a in range(z+1,len(line)):
					for b in range(a+1,len(line)):
						for c in range(b+1,len(line)):
							for d in range(c+1,len(line)):
								print(d)
								for e in range(d+1,len(line)):
									for f in range(e+1,len(line)):
										for g in range(f+1,len(line)):
											for h in range(g+1,len(line)):
													for i in range(h+1,len(line)):
															num=int(line[x]+line[y]+line[z]+line[a]+line[b]+line[c]+line[d]+line[e]+line[f]+line[g]+line[h]+line[i])
															if(num>max):
																	max=num

	p1=p1+int(max)

print ("Part 2: ", p1)

