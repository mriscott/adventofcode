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
	print "$1 => $2\n";
    }
}
$pad=20;
for ($x=0;$x<$pad;$x++){
    $initial=".$initial.";
}

@state=split //,$initial;
$len=scalar @state;
print "Length:$len\n";
print "00: @state\n";
for($gen=1;$gen<=20;$gen++){
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
    print "0" if ($gen<10);
    print "$gen: @state\n";
}
$score=0;
for ($i=0;$i<$len;$i++){
	if ($state[$i] eq '#'){
		$plant=$i-$pad;
		#print "Plant $plant\n";
		$score+=$plant;
	}
}
print "Score:$score\n";
