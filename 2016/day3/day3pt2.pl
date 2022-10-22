
sub tri($$$){
	@arr=@_;
	print "@_\n";
	@sort=sort {$a<=>$b} @_;
	$sum=$sort[0]+$sort[1];
	if ($sum>$sort[2]){
		$isok=1;
	}else{
		$isok=0;
	}
	print ("Sides: @sort  $sum $isok\n");
	return $isok;
}

$poss=0;
$c=0;
while (<STDIN>){
	@v=(-1,-1,-1,-1,-1,-1,-1,-1,-1);
	if(/(\d+)\s*(\d+)\s*(\d+)/){
		print "$c $1,$2,$3\n";
		$v[$c]=$1;
		$c++;
		$v[$c]=$2;
		$c++;
		$v[$c]=$3;
		$c++;
		if($c==9){
			print(@v);
			$poss+=tri($v[0],$v[3],$v[6]);
			$poss+=tri($v[1],$v[4],$v[7]);
			$poss+=tri($v[2],$v[5],$v[8]);
			$c=0;
		}
	}
}
print ("Possible Triangles: $poss\n");
