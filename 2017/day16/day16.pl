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




for ( $n=0;$n<(1000000000%30);$n++){
foreach(@dance){
    if(/s(\d+)/){
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
	$a=$programs[$1];
	$programs[$1]=$programs[$2];
	$programs[$2]=$a;
		
    }
    if(/p(\w+)\/(\w+)/){
	
	for($x=0;$x<$max;$x++){
	    if ($programs[$x] eq $1) {
		$programs[$x]=$2;
	    }
	    elsif ($programs[$x] eq $2) {
		$programs[$x]=$1;
	    }
	}
    }


}
$order="";
foreach(@programs) {
    $order="$order$_";
}

if ($order eq "abcdefghijklmnop"){
print "$n:$order\n";
}
}
print "$order\n";
