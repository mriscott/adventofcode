$x=0;
$y=0;
$wx=10;
$wy=1;
%l=('E','N', 'N','W', 'W','S', 'S','E');
%r=('E','S', 'N','E', 'W','N', 'S','W');
$dir='E';
foreach (<STDIN>){
	if(/(.)(\d+)/){
		$ins=$1;
		$num=$2;
		print ("$ins $num\n");
		if ($ins eq 'F'){
			$x+=($wx*$num);
			$y+=($wy*$num);
		}
		$wy+=$num if ($ins eq 'N');
		$wy-=$num if ($ins eq 'S');
		$wx-=$num if ($ins eq 'W');
		$wx+=$num if ($ins eq 'E');
		if ($ins =~ /[LR]/){
			for (;$num>0;$num-=90){	
				if($ins eq 'L'){
					$t=$wx;
					$wx=-$wy;
					$wy=$t;
					$dir = $l{$dir};
				}else{
					$t=$wx;
					$wx=$wy;
					$wy=-$t;
					$dir = $r{$dir};
				}	
			}
		}
		print ( "$x , $y  ($dir)\n");	
	}
}
$dist=abs($x)+abs($y);
print ("$dist\n");
