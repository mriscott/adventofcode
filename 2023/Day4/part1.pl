#!/bin/perl
#

sub processcard($$){
	($card,$list)=@_;
	$score=0;
	($winners,$guesses)=split /\|/, $list;
	%right={};

	foreach(split / +/,$winners){
		s/\s*$//;
		s/^\s*//;
		$right{$_}=1;
	}
	foreach(split / +/,$guesses){
		s/\s*$//;
		s/^\s*//;
		if($_!~/^$/ && $right{$_}==1){
			$score=$score*2;
			$score=1 if ($score==0);
		}
	}
	return $score;
}

sub checkfile($){
	($filename)=@_;
	$total=0;
	open (FILE, $filename);
	foreach (<FILE>)
	{
		@card=split /:/;
		$total+=processcard($card[0],$card[1]);
			
	}
	close FILE;
	return $total;
}


if(checkfile("test")!=13){
	die ("Test failed - $total");
}
print ("Part 1:");
print checkfile("input");
print ("\n");
