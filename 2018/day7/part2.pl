#!/bin/perl
%reqs=();
%steps=();
$debug=0;
$workernum=5;
$atime=61;
#$workernum=2;
#$atime=1;
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
@workers=();
for($w=0;$w<$workernum;$w++){
	$workers[$w][0]='.';
}

$sec = -1;
@done=();
while (scalar(@done)<$todo){
	$sec++;
	print "$sec\t";
	# first handle completed tasks
	for($w=0;$w<$workernum;$w++){
		if($workers[$w][1]==$sec && $workers[$w][0] ne '.'){
			push @done,$workers[$w][0];
			print "$workers[$w][0] completed by worker $w\n" if $debug;
			$steps{$workers[$w][0]}=0;
			$workers[$w][0]='.' ;
		}
	}
	# now look for new tasks

	for($w=0;$w<$workernum;$w++){
		if($workers[$w][0] =~ /^\.$/) {
			print "Looking for job for worker $w\n" if $debug;
		foreach $step (sort keys %steps) {
			next if $steps{$step}!=1;
			$deps=$reqs{$step};
			if(!$deps){
				$workers[$w][0]=$step;
				$workers[$w][1]=ord($step)-65+$atime+$sec;
				print "$workers[$w][0] started by worker $w (till $workers[$w][1])\n" if $debug;
				$steps{$step}=2;
				last;
			} else {
				$unmet=0;
				foreach $dep(split //,$deps){	
					print "$step dep  $dep - $steps{$dep} \n" if $debug==1;
					if($steps{$dep}!=0){
						$unmet++;
oki
					}
				}
				if($unmet==0){
					$workers[$w][0]=$step;
					$workers[$w][1]=ord($step)-65+$atime+$sec;
					print "$workers[$w][0] started by worker $w (till $workers[$w][1])\n" if $debug;
					$steps{$step}=2;
					last;
				}
			}
		}
		}
		print "$workers[$w][0]\t";
	}
	print "@done\n";
}

print "Time taken : $sec\n";
