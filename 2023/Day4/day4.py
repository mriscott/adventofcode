import re

#FILE="test"
FILE="input"

f=open(FILE)
cards=0
copys=[]

total=0
game=0
cardtotal=0

for i in range(300):
    copys.append(0)

for line in f:
    foo=line.split(": ")
    foo[1]=re.sub(" +"," ",foo[1])
    foo[1]=re.sub("^ +","",foo[1])
    bar=foo[1].split(" | ")
    win=bar[0].split(" ")
    have=bar[1].rstrip().split(" ")

    score=0
    winnos=0
    for w in win:
        for h in have:
            if(w==h):
                winnos=winnos+1
                if score==0:
                    score=1
                else:
                    score *=2
    total+=score
    copys[game]= copys[game]+1
    for i in range(game+1,(game+winnos+1)):
        copys[i]=copys[i]+copys[game]

    cardtotal=cardtotal+copys[game]
    game=game+1

f.close()


print ("Part 1: "+str(total))
print ("Part 2: "+str(cardtotal))
