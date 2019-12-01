#!/bin/perl
use strict;

sub getfuel($){
	my ($mass)=@_;
	my $fuel=(int($mass/3)-2);
	if ($fuel<0){
		return 0;	
	}
	my $fuel2=getfuel($fuel);
	return $fuel+$fuel2;
}

sub test($$){
	my($mass,$exp)=@_;
	my $fuel=getfuel($mass);
	if ($fuel!=$exp){
		print ("Expected $exp, got $fuel\n");
		exit 1;
	}
	print ("$mass -> $exp OK\n");
}


test(14,2);
test(1969,966);
test(100756,50346);


my $tot=0;
foreach (<STDIN>){
	chomp;
	my $ans=getfuel($_);
	print ("$_ -> $ans\n");
	$tot+=$ans;
}
print ("Total: $tot\n");

	
