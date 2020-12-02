$valid=0;
foreach (<STDIN>){
	if (/(\d+)-(\d+) (.): (.*)$/){
		($min,$max,$ch,$pass)=($1,$2,$3,$4);
		print ("$pass should have a $ch as either char  $min or $max ... ");
		@arr=split(//,$pass);
		$count=0;
		print ("($arr[$min-1] $arr[$max-1] )");
		$count++ if($arr[$min-1] eq $ch);
		$count++ if($arr[$max-1] eq $ch);
		if ($count==1){
			print ("OK\n");
			$valid++;
		} else {
			print ("FAIL\n");
		}
	}
}
print ("$valid passwords were valid\n");
