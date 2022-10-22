$poss=0;
while (<STDIN>){
	if(/(\d+)\s*(\d+)\s*(\d+)/){
		@arr=($1,$2,$3);
		@sort=sort {$a<=>$b} @arr;
		$sum=$sort[0]+$sort[1];
		if ($sum>$sort[2]){
			$isok=1;
		}else{
			$isok=0;
		}

		print ("Sides: @sort  $sum $isok\n");
		$poss+=$isok;
	}
}
print ("Possible Triangles: $poss\n");
