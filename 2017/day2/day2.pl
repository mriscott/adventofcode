#!/bin/perl
$tot=0;
while (<STDIN>){
	@numbers=split(/\t/);
	$division=-1;
	foreach $base (@numbers){
		last if $division!=-1;
		# check division with all others
		$base=~s/\s$//;
		print "$base ";
		foreach $top (@numbers){
			$top=~s/\s$//;
			next if $base==$top;
			if ($base % $top == 0 ){
				$division = $base/$top;
				print "\n$division = $base / $top\n";
				last;
			}
			if ($top % $base == 0){
				$division = $top/$base;
				print "\n$division = $top / $base\n";
				last;
			}
		}
	}
	$tot+=$division;

}
print "Total:$tot\n";
