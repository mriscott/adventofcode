@inp=(<STDIN>);
@bus=split(/,/,$inp[1]);
$ans=0;
for($t=$bus[0];$ans==0;$t+=$bus[0]){
	print ("Checking $t\n");
	$bt=$t;
	$isok=1;
	foreach $x (@bus){
		chomp $x;
		if ($x !~ /x/ && $x!=0 && ($bt % $x != 0)){
			$isok=0;
			last;
		}
		$bt++;
	}
	$ans=$t if ($isok==1);
}
print ("Answer: $ans\n");
