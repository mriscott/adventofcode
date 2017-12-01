#!/bin/perl

$n=0;
$e=0;
$d=0;

@norths=(1,0,-1,0);
@easts=(0,1,0,-1);
@dirs=('N','E','S','W');

%locs={};

foreach (@ARGV){
	($t,$s)=/([RL])(\d+)/;
	if($t eq 'L'){
		$d--;
		$d=3 if ($d==-1);
	}
	elsif($t eq 'R'){
		$d++;
		$d=0 if ($d==4);
	}
	else {
		print("Error: Bad turn $t\n");
		exit (0);
	}
	print("Turn $t, walk $s blocks $dirs[$d]\n");
	$break=0;
	for ($i=0;$i<$s;$i++){
		$n+=($norths[$d]);
		$e+=($easts[$d]);
		$loc="$n,$e";
		print "$loc\n";

		if($locs{$loc}==1){
			print("I've been here before\n");
			$break=1;
			last;
		}
		$locs{$loc}=1;
	}
	last if $break;
}

print "$n blocks north, $e blocks east\n";
$n=-$n if $n<0;
$e=-$e if $e<0;
print "You are ".($n+$e)." blocks away\n";
	

