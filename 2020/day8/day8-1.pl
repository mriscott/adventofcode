$acc=0;
@instructions=<STDIN>;
@done=();
$i=0;
$keepgoing=1;
while ($keepgoing==1){
	if ($i>=@instructions){
		print ("OUT OF INSTRUCTIONS\n");
		exit 1;
	}
	$cmd=$instructions[$i];
	chomp $cmd;
	if($cmd=~/(\w+) ([\+\d\-]+)/){
		print ("$i $1 ($2) ");
		@done[$i]=1;
		if ($1 eq 'acc'){
			$acc+=$2;
			print (" [ACC - $acc]\n");
			$i++;
		}
		if ($1 eq 'nop'){
			$i++;
			print (" NOP\n");
		}
		if ($1 eq 'jmp'){
			$i+=$2;
			print(" [ i -> $i]\n");
		}

	

	}else {
		print ("BAD COMMAND $cmd\n");
		exit 1;
	}
	if ($done[$i]==1){
		print("About to repeat instruction $i\n");
		print ("Acc: $acc\n");
		$keepgoing=0;
	}
}

