#!/usr/bin/perl

$initial;
@state=();
%rules=();
foreach (<STDIN>){
    if (/initial state:(.*)/){
	$initial=$1;
	$initial=~s/^ *//;
	$initial=~s/ *$//;
    }	
    if (/^([#\.]+) => ([#\.])/){
    	$rules{$1}=$2;
	#print "$1 => $2\n";
    }
}
$pad=700;
for ($x=0;$x<$pad;$x++){
    $initial=".$initial.";
}

@state=split //,$initial;
$len=scalar @state;
print "Length:$len\n";
#print "00: @state\n";
print "Part 1...\n";
for($gen=1;$gen<=20;$gen++){
    #print "0" if ($gen<10);
    #print "$gen: @state\n";
    nextgen();
    $score=countplants();
    #print "$gen\t$score\n";
}

print "\n### Part 1 Answer:$score ###\n\n";

print "Skipping forward to find repeating value ....\n";
$lastscore=$score;
$stable=0;

for (;$gen<=250;$gen++){
    nextgen();
    $score=countplants();
    $jump=$score-$lastscore;
    $jumpjump=$jump-$lastjump;
    $stable++ if ($jumpjump==$lastjumpjump);
    #print "$gen\t$score\t$jump\t$jumpjump\n";
    $lastjump=$jump;
    $lastjumpjump=$jumpjump;
    $lastscore=$score;
}
die "Couldn't find stable state\n" if ($stable<5);
print "Stable jumpjump:$jumpjump (stable for $stable gens)\n";
#setting for calc
$jumpinc=$jumpjump;
$startjump=$jump;
$startscore=$score;
$startgen=$gen-1;

print "\nChecking calculation ....\n";
for (;$gen<=500;$gen++){
    nextgen();
    $score=countplants();
    $calcscore=calcscore($gen);
    #print "$gen\t$score\t$calcscore\n";
    die "Calc failed" if $calcscore!=$score;

}
$gen--;
print "Looks good up to $gen\n";
print "### Part 2: ".calcscore(50000000000)." ###\n";




sub nextgen(){
    @newstate=@state;
    for ($i=0;$i<$len;$i++){
    	if(($i==0 || $i==($len-1)) && $state[$i] eq '#'){
	    die ("Entry $i is unexpectedly #");
	}
	$match="";
	for ($y=$i-2;$y<($i+3);$y++){
	    if ($y<3 || $y >($len-2)){
	    	$match.='.';
	    }
	    else {
		$match.=$state[$y];
	    }
	}
	$newval=$rules{$match};
	die ("No rule for $match") if (!$newval);
	$newstate[$i]=$newval;
    }
    @state=@newstate;
}

sub countplants(){
    $score=0;
    for ($i=0;$i<$len;$i++){
	if ($state[$i] eq '#'){
		$plant=$i-$pad;
		#print "Plant $plant\n";
		$score+=$plant;
	}
    }
    return $score;
}

sub calcscore($){
    ($thisgen)=@_;
    $over=$thisgen-$startgen;
    #print "Over:$over Startjump:$startjump\n";
    $newscore=$startscore;
    $newscore+=($over*$startjump);
    $extrajump=$over*($over-1)*$jumpinc/2;
    $newscore+=$extrajump;
    return $newscore;
}
