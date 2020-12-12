$x=0;
$y=0;
%l={'E'=>'N', 'N'=>'W', 'W'=>'S', 'S'=>'E'};
%r={'E'=>'S', 'N'=>'E', 'W'=>'N', 'S'=>'W'};
$dir='E';
foreach (<STDIN>){
	if(/(.)(\d+)/){
		$ins=$1;
		$num=$2;
		print ("$ins $num\n");
		$ins = $dir if ($ins eq 'F');
		$y+=$num if ($ins eq 'N');
		$y-=$num if ($ins eq 'S');
		$x-=$num if ($ins eq 'W');
		$x+=$num if ($ins eq 'E');
		# TODO include num degrees
		$dir = $l{$dir} if ($ins eq 'L');
		$dir = $r{$dir} if ($ins eq 'R');
		print ( "$x , $y  ($dir)\n");	
	}
}
$dist=abs{$x}+abs($y);
print ("$dist\n");
