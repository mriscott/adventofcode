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
	$maxx=$x;
	$y++;

}
$maxy=$y;

printgrid();

$changes=1;
while ($changes!=0){
	$changes=changegrid();
	printgrid();
}


sub printgrid(){
	for ($y=0;$y<=$maxy;$y++){
		for ($x=0;$x<=$maxx;$x++){
			print ($grid[$x][$y]);
		}
		print ("\n");
	}
}

sub changegrid(){
	my $c=0;
	my $x,$y;
	for ($y=0;$y<=$maxy;$y++){
		for ($x=0;$x<=$maxx;$x++){
			next if ($rows[$x][$y] eq '.');
			$n=newstatus($x,$y);
			if ( $n ne $rows[$x][$y]){
				$rows[$x][$y]=$n;
				$c++;
			}
		}
	}
	return $c;
}

sub newstatus($$){
	my($x,$y)=@_;
	my $n=0;
	for ($xx=$x-1;$xx<=($x+1);$xx++){
		for ($yy=$y-1;$yy<=($y+1);$yy++){
			next if ($xx==0 && $yy==0);	
			$n++ if ($rows[$xx][$yy] eq '#');
		}
	}
	$old=$rows[$x][$y];
	return '#' if ($n==0 && $old eq 'L');
	return 'L' if ($n>3 && $old eq '#');
	return $old;
}


