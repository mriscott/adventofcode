#!/bin/perl

@entries=();
$totalmeta=0;
$nodenum=0;
sub getnode(){
	$nodenum++;
	local $x;
	local $node=$nodenum;
	print "Processing node $node:\n";
	local $children=pop(@entries);
	local $metadata=pop(@entries);
	print " - children:$children\n";
	print " - metadata:$metadata\n";
	for($x=1;$x<=$children;$x++){
		print "Processing child $x of $node...\n";
		getnode();
	}
	for($x=1;$x<=$metadata;$x++){
		$entry=pop(@entries);
		print "Processing metadata $x of $node ($entry)...\n";
		$totalmeta+=$entry;
	}
	
}


foreach (<STDIN>)
{
	chomp;
	@entries=reverse (split / /);
}
	
print "Processing " . scalar(@entries) . "....\n";

getnode();
if (@entries) {
	print "ERROR: Should be nothing left\n";
	print "Still have: @entries\n";
} else {
	print "Total metadata: $totalmeta\n";
}
	

