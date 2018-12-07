#!/usr/bin/perl
$maxx=0;
$maxy=0;
@grid=();
$no=1;
@coords=();
$printgrid=0;
$regionlimit=10000;
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

$regionsize=0;
for ($y=0;$y<=$maxy;$y++){
	for ($x=0;$x<=$maxx;$x++){
			$closest=-1;
			$mindist=$max+$maxy;
			$total=0;
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
				$total+=$mdist;

			}
			#print("\n$x,$y: $mindist - ".chr($closest+96)."\n");
			if($total<$regionlimit){
				print "#" if $printgrid;
				$regionsize++;
			} else{ 
				print "." if $printgrid;
			} 
	}
	print "\n" if $printgrid;
}
print "\n\nRegion Size : $regionsize\n";
