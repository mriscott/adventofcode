#!/usr/bin/perl
%seen={};
$x=0;
$seen{$x}=1;
$keepgoing=1;
while ($keepgoing){
	print "Reading $ARGV[0]\n";
	open (FILE,"<",$ARGV[0]);
	while (<FILE>){
		$x = eval ("$x$_");
		if ($seen{$x}==1){
			print "$x\n";
			$keepgoing=0;
			last;
		}
		$seen{$x}=1;
	}
	close (FILE);
}

