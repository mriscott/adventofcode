#!/bin/perl
$tot=0;
while (<STDIN>){
	@numbers=split(/\t/);
	$min=10000;
	$max=0;
	foreach (@numbers){
		print;
		print " ";
		$max=$_ if $_>$max;
		$min=$_ if $_<$min;
	}
	$diff=$max-$min;
	$diff=-$diff if ($diff<0);
	print "Min $min Max $max Diff $diff\n"; 
	$tot+=$diff;

}
print "Total:$tot\n";
