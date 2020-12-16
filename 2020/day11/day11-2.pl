@lines=<STDIN>;
$y=0;
@grid=(['.','.','.'],['.','.','.']);
foreach (@lines)
{
	chomp;
	$x=0;
	foreach (split //,$_){
		$grid[$x][$y]=$_;
		$x++;
	}
	$maxx=$x-1;
	$y++;

}
$maxy=$y-1;

printgrid();

$try=0;
$changes=1;
while ($changes!=0){
	$changes=changegrid();
	$cc = printgrid();
	$try++;
	print ("$try - $cc occupied\n");
}


sub printgrid(){
	my $cnt=0;
	for ($y=0;$y<=$maxy;$y++){
		for ($x=0;$x<=$maxx;$x++){
	#		print ($grid[$x][$y]);
			$cnt++ if ($grid[$x][$y] eq '#');
		}
		#print ("\n");
	}
	return $cnt;
}

sub changegrid(){
	my $c=0;
	@newgrid=(['.','.','.'],['.','.','.']);
	my $x,$y;
	for ($y=0;$y<=$maxy;$y++){
		for ($x=0;$x<=$maxx;$x++){
			$newgrid[$x][$y]=$grid[$x][$y];
			next if ($grid[$x][$y] eq '.');
			my $n=newstatus($x,$y);
			if ( $n ne $grid[$x][$y]){
				$newgrid[$x][$y]=$n;
				$c++;
			}
		}
#		print("\n");
	}
	for ($y=0;$y<=$maxy;$y++){
		for ($x=0;$x<=$maxx;$x++){
			$grid[$x][$y]=$newgrid[$x][$y];
		}
	}
	return $c;
}

sub newstatus($$){
	my($x,$y)=@_;
	my $n=0;
	for ($xx=-1;$xx<=1;$xx++){
		for ($yy=-1;$yy<=1;$yy++){
			next if ($xx==0 && $yy==0);	
			$ch='.';
			$xxx=$x;
			$yyy=$y;
			while($ch eq '.'){
				$xxx+=$xx;
				$yyy+=$yy;
				last if ($xxx < 0);
				last if ($yyy < 0);
				last if ($xxx > $maxx);
				last if ($yyy > $maxy);
				$ch=$grid[$xxx][$yyy];
				$n++ if ($ch eq '#');
			}
		}
	}
	$old=$grid[$x][$y];
#	print ("$old $n\n");
	return '#' if ($n==0 && $old eq 'L');
	return 'L' if ($n>4 && $old eq '#');
	return $old;
}


