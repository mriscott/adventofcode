$tot=0;
$pos=50;
foreach (<STDIN>){
	chomp;
	s/R/+/;
	s/L/-/;
	$_=$_%100;
	$pos+=$_;
	$pos-=100 if ($pos>99);
	$pos+=100 if ($pos<0);
	$tot++ if ($pos==0);
}
print ($tot);
