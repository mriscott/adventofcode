use bigint;
%mem=();
while (<STDIN>){
	if(/mask = ([X\d]+)/){
		$mask=$1;
		print ("mask:$mask:\n");
	}
	if (/mem\[(\d+)\] *= *(\d+)/){
		$mem{$1}=domask($2);
	}
}
$sum=0;
foreach(keys (%mem)){
	$sum+=$mem{$_};
}
print (" sum: $sum\n");

sub domask($){
	($num)=@_;
	$d=1;
	#print ("$num -> ");
	foreach (reverse(split(//,$mask))){
		if (/1/){
			$num += $d if (($num & $d) ==0);
		}
		if (/0/){
			$num -= $d if (($num & $d)  != 0);

		}
		$d*=2;
	}
	#print ("$num\n ");
	return $num;
}

