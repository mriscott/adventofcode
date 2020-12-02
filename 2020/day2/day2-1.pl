$valid=0;
foreach (<STDIN>){
	if (/(\d+)-(\d+) (.): (.*)$/){
		($min,$max,$ch,$pass)=($1,$2,$3,$4);
		print ("$pass should have between $min and $max $ch ... ");
		@arr=split(//,$pass);
		$count=0;
		foreach (@arr){
			$count++ if ($_ eq $ch);
		}
		if ($count<=$max && $count >= $min){
			print ("OK\n");
			$valid++;
		} else {
			print ("FAIL\n");
		}
	}
}
print ("$valid passwords were valid\n");
