#!/bin/perl
%reqs=();
%steps=();
$debug=0;
foreach (<STDIN>){
	if (/Step (.) must be finished before step (.) can begin/){
		$reqs{$2}.=$1;
		$steps{$1}=1;
		$steps{$2}=1;
	}
	else {
		die "Bad line";
	}
}
foreach (sort keys %reqs){
	print "$_ -> $reqs{$_}\n";
}

$todo=scalar(keys %steps);
print "$todo steps to find\n";
while ($todo>0){
	foreach $step (sort keys %steps) {
		next if $steps{$step}==0;
		$deps=$reqs{$step};
		if(!$deps){
			print "$step" if $debug==0;
				print "$step completed as it has no deps\n" if $debug==1;
			$steps{$step}=0;
			$todo--;
			last;
		} else {
			$unmet=0;
			foreach $dep(split //,$deps){	
				print "$step dep  $dep - $steps{$dep} \n" if $debug==1;
				if($steps{$dep}==1){
					$unmet++;
				}
			}
			if($unmet==0){
				print "$step" if $debug==0;
				print "$step completed as it has no unmet deps\n" if $debug==1;
				$steps{$step}=0;
				$todo--;
				last;
			}
		}
	}
}
print "\n";
