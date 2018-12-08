#!/bin/perl

@entries=();
$totalmeta=0;
$nodenum=0;
sub getnode(){
	$nodenum++;
	local $val=0;
	local $x;
	#local $node=chr(64+$nodenum);
	local $node=$nodenum;
	print "Processing node $node:\n";
	local $children=pop(@entries);
	local $metadata=pop(@entries);
	print " - children:$children\n";
	print " - metadata:$metadata\n";
	local @childvals=();
	for($x=1;$x<=$children;$x++){
		print "Processing child $x of $node...\n";
		$childvals[$x]=getnode();
	}
	for($x=1;$x<=$metadata;$x++){
		$entry=pop(@entries);
		print "Processing metadata $x of $node ($entry)...\n";
		if ($children==0){
			print "No kids - so add value directly\n";
			$val+=$entry;
		} else {
			print "Adding value of child $entry ($childvals[$entry])\n";
			$val+=$childvals[$entry];
		}
	}
	print "Node $node Value $val\n";
	return $val;	
}


foreach (<STDIN>)
{
	chomp;
	@entries=reverse (split / /);
}
	
print "Processing " . scalar(@entries) . "....\n";

$value=getnode();

if (@entries) {
	print "ERROR: Should be nothing left\n";
	print "Still have: @entries\n";
} else {
    print "Value of root node: $value\n";
}
	

