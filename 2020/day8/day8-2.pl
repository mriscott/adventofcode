@instructions=<STDIN>;
for ($change=0;$change<=@instructions;$change++){
$acc=0;
@done=();
$i=0;
$keepgoing=1;
while ($keepgoing==1){
	$cmd=$instructions[$i];
	chomp $cmd;
	if($cmd=~/(\w+) ([\+\d\-]+)/){
		$ins=$1;
		if ($change==$i){
			print ("CHANGE $ins->");
			if ($ins eq 'jmp'){
				$ins='nop';
			}
			elsif ($ins eq 'nop'){
				$ins='jmp';
			}
			print ("$ins :");
		}	
		print ("$i $ins ($2) ");
		@done[$i]=1;
		if ($ins eq 'acc'){
			$acc+=$2;
			print (" [ACC - $acc]\n");
			$i++;
		}
		if ($ins eq 'nop'){
			$i++;
			print (" NOP\n");
		}
		if ($ins eq 'jmp'){
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
	if ($i>=@instructions){
		print ("Successful termination\n");
		print ("Acc: $acc\n");
		$keepgoing=0;
		exit 0;
	}
}
}

print ("Ran out of instructions to change\n");
