#!/bin/perl
#
@cardcopies=();
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
		    $score++;
		}
	}
	return $score;
}

sub checkfile($){
    ($filename)=@_;
    @cardcopies=();
	$total=0;
	open (FILE, $filename);
	foreach (<FILE>)
	{
		@card=split /:/;
		$score=processcard($card[0],$card[1]);
		$cardno=$card[0];
		$cardno=~s/Card //;
		$cardcopies[$cardno]++;
		$cp=$cardcopies[$cardno];


		for ($x=1;$x<=$score;$x++){
			$y=$cardno+$x;
			$cardcopies[$y]+=$cp;
		}
#		print ("Card $cardno score $score copies $cardcopies[$cardno]\n");
		$total+=$cardcopies[$cardno];
	}
	close FILE;
	return $total;
}


if(checkfile("test")!=30){
	die ("Test failed - $total expected 30");
}
print("Test passed\n");
print ("Part 2:");
print checkfile("input");
print ("\n");
