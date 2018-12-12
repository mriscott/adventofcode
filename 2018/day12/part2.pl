#!/usr/bin/perl

use Math::BigInt;
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
$targetgen=50000000000;
$target=calc($targetgen);
print "Calculated val for $targetgen = $target\n";
exit 0;
$pad=10000;
for ($x=0;$x<$pad;$x++){
    $initial=".$initial.";
}

@state=split //,$initial;
$len=scalar @state;
#print "Length:$len\n";
#print "00: @state\n";
@origstate=@state;
for($gen=1;$gen<=$pad;$gen++){
    $repeat=1;
    @newstate=@state;
    for ($i=0;$i<$len;$i++){
    	if(($i==0 || $i==($len-1)) && $state[$i] eq '#'){
	    die ("Entry $i is unexpectedly # at gen $gen");
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
	$repeat=0 if($newval ne $origstate[$i]);
    }
    @state=@newstate;
    #print "0" if ($gen<10);
    #print "$gen: @state\n";
   #  print "$gen\n" if $gen%100==0;
    if ($repeat==1){
	print "Repeats at $gen";
	last;
    }
    if ($gen%100==0){
	#$increment=($gen-200)*34+6811;
	#$calc=$lastscore+$increment;
	for ($i=0;$i<$len;$i++){
		if ($state[$i] eq '#'){
			$plant=$i-$pad;
			#print "Plant $plant\n";
			$score+=$plant;
		}
	}
	print "$gen\t$score\n";
	$calc=calc($gen);
	print "Calc\t$calc\n";
	die "mismatch" if $calc!=$score;
        if($gen>101) {
	    die "$score!=$calc" if $score!=$calc;
	}
	#print "Jump:".($score-$lastscore)."\n";
	$lastscore=$score;
    }
    if ($gen==$targetgen){
	die "Generated target score wrong" if $target!=$score;
	print "Success\n";
	last;
    }

}

sub calc($){
    ($x)=@_;
    $x/=100;
    $score=Math::BigInt->new("3489");
    for ($n=1;$n<$x;$n++){
	$jump=6811;
	$jump+=(3400*($n-1));
	#print "Jump:$jump\n";
	$score->badd($jump);
	print "$n/$x\r" if $n%100==0
    }
    return $score;
}
