#!/bin/perl

sub getfuel($){
	($mass)=@_;
	return (int($mass/3)-2);
}

sub test($$){
	($mass,$exp)=@_;
	$fuel=getfuel($mass);
	if ($fuel!=$exp){
		print ("Expected $exp, got $fuel\n");
		exit 1;
	}
	print ("$mass -> $exp OK\n");
}


test(12,2);
test(14,2);
test(1969,654);
test(100756,33583);


$tot=0;
foreach (<STDIN>){
	chomp;
	$ans=getfuel($_);
	print ("$_ -> $ans\n");
	$tot+=$ans;
}
print ("Total: $tot\n");

	
