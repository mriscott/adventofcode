$pt1=0;
$pt2=0;
$pos=50;
foreach (<STDIN>){
	chomp;
	s/R/+/;
	s/L/-/;
	$_=$_;
	next if ($_==0);
	$oldpos=$pos;
	$pos+=$_;
	$pt1++ if($pos%100 ==0);
	$pt2-- if($pos%100 ==0);
	while ($pos<0 || $pos>99){
		$pos-=100 if ($pos>99);
		$pos+=100 if ($pos<0);
		$pt2++;
	}
}
$pt2+=$pt1;
print ("Part 1: $pt1\n");
print ("Part 2: $pt2\n");
