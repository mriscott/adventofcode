@programs=();
$max=16;
for($x=0;$x<$max;$x++){
    push @programs, chr($x+97);
}

#@dance=("s1","x3/4","pe/b");

@dance=();
open (FILE,"<","input.txt");
while (<FILE>){
    push @dance, (split /,/,$_);
}




foreach(@programs){
    print;
}
print "\n";

foreach(@dance){
    print "$_ -> ";
    if(/s(\d+)/){
	print "spin $1\n";
	$start=$max-$1;
	$x=$start;
	@newprograms=();
	while($x<$max){
	    push @newprograms, $programs[$x];
	    $x++;
	}
	$x=0;
	while ($x<$start){
	    push @newprograms, $programs[$x];
	    $x++;
	}
	@programs=@newprograms;
    }
    if(/x(\d+)\/(\d+)/){
	print "exchange $1 and $2\n";
	$a=$programs[$1];
	$programs[$1]=$programs[$2];
	$programs[$2]=$a;
		
    }
    if(/p(\w+)\/(\w+)/){
	print "partner $1 and $2\n";
	
	for($x=0;$x<$max;$x++){
	    if ($programs[$x] eq $1) {
		$programs[$x]=$2;
	    }
	    elsif ($programs[$x] eq $2) {
		$programs[$x]=$1;
	    }
	}
    }

    foreach(@programs){
	print;
    }
    print "\n";

}
