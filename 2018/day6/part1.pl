#!/usr/bin/perl
$maxx=0;
$maxy=0;
@grid=();
$no=1;
@coords=();
foreach (<STDIN>)
{
	if (/(\d+), (\d+)/){
		$grid[$1][$2]=$no;
		$maxx=$1 if $1>$maxx;	
		$maxy=$2 if $2>$maxy;	
                $coords[$no][0]=$1;
                $coords[$no][1]=$2;
		$no++;

	}
}
$maxc=$no;

$MAX=100*($maxx+$maxy);

print "Found $maxc coords\n";
for($c=1;$c<$maxc;$c++){
	print  chr($c+64);
	$x=$coords[$c][0];
	$y=$coords[$c][1];
	print " ( $x , $y ) \n";
}
for ($y=0;$y<=$maxy;$y++){
	for ($x=0;$x<=$maxx;$x++){
		if ($grid[$x][$y]) {
			$point=$grid[$x][$y];
			$coords[$point][2]++;
			#print  chr($grid[$x][$y]+64);
		} else {
			$closest=-1;
			$mindist=$max+$maxy;
			for($c=1;$c<$maxc;$c++){
				$xdist=abs($x-$coords[$c][0]);
				$ydist=abs($y-$coords[$c][1]);
				$mdist=$ydist+$xdist;
				if($mdist==$mindist && $c!=$closest){
					$closest=-1;
				}
				if($mdist<$mindist){
					$mindist=$mdist;
					$closest=$c;
				}

			}
			#print("\n$x,$y: $mindist - ".chr($closest+96)."\n");
			if ($closest==-1){
				#print ".";
			} else {
				#print (chr($closest+96));
				$coords[$c][2]++;
				if($x==0 || $y == 0 || $x==$maxx || $y==$maxy){
					$coords[$closest][2]=$MAX;
				}
				$coords[$closest][2]++;
			}
		}
	}
	#print "\n";
}
$maxsize=0;
for($c=1;$c<$maxc;$c++){
	print  chr($c+64);
	print " - ";
	if ($coords[$c][2]>=$MAX){
		print " INFINITE";
	} else {
		print $coords[$c][2];
		$maxsize=$coords[$c][2] if $coords[$c][2]>$maxsize;
	}
	print "\n";
}

print "\n\nMax Size : $maxsize\n";
