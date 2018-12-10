@pos=();
@vel=();
$i=0;
$maxx=0;
$maxx=0;
$miny=0;
$miny=0;
$nopoints=0;
foreach (<STDIN>){
	if (/position=<(.*)> velocity=<(.*)>/){
		$coords=$1;
		$vector=$2;
		print "$i raw: $coords -> $vector\n";
		($pos[$i][0],$pos[$i][1])=split /,/,$coords;
		($vel[$i][0],$vel[$i][1])=split /,/,$vector;
		$pos[$i][0]=~s/ *$//;
		$pos[$i][1]=~s/ *$//;
		$vel[$i][0]=~s/ *$//;
		$vel[$i][1]=~s/ *$//;
		$pos[$i][0]=~s/^ *//;
		$pos[$i][1]=~s/^ *//;
		$vel[$i][0]=~s/^ *//;
		$vel[$i][1]=~s/^ *//;
		$maxx=$pos[$i][0] if $pos[$i][0]>$maxx;
		$maxy=$pos[$i][1] if $pos[$i][1]>$maxy;
		$minx=$pos[$i][0] if $pos[$i][0]<$minx;
		$miny=$pos[$i][1] if $pos[$i][1]<$miny;
		print "$i parsed: $pos[$i][0], $pos[$i][1]  -> $vel[$i][0],$vel[$i][1]\n";
		$i++;
	}
	
}
$nopoints=$i;

print "X:$minx -> $maxx\n";
print "Y:$miny -> $maxy\n";

$s=0;
@grid=();

$match=1;
while ($match){
    $minusedx=$maxx;
    $maxusedx=$minx;
    $minusedy=$maxy;
    $maxusedy=$minx;
    $s++;
    print("$s seconds\n") if ($s%1000==0);
    %points={};
    for($i=0;$i<$nopoints;$i++){
	$pos[$i][0]+=$vel[$i][0];
	$pos[$i][1]+=$vel[$i][1];
	$x=$pos[$i][0];
	$y=$pos[$i][1];
	$maxusedx=$x if $x>$maxusedx;
	$maxusedy=$y if $y>$maxusedy;
	$minusedx=$x if $x<$minusedx;
	$minusedy=$y if $y<$minusedy;
	$points{"$x,$y"}=1;
	$vertline=0;
	$limit=5;
	for($line=$y;$line>($y-$limit);$line--){
		if($points{"$x,$line"}==1){
			$vertline++;
		} else {
			last;
		}
	}
	$match=0 if($vertline>=$limit);
	
    }
}
print "$minusedx->$maxusedx , $minusedy->$maxusedy\n";
for ($y=$minusedy;$y<=$maxusedy;$y++){
    $line="";
    for ($x=$minusedx;$x<=$maxusedx;$x++){
	$point=0;
	for($i=0;$i<$nopoints;$i++){
	    if($x==$pos[$i][0] && $y==$pos[$i][1]){
		    $point=1;
	    }
	}
	$line.=$point?"#":".";

    }
    print "$line\n";

}	
print "At $s\n";
