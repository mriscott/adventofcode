@banks=(0,2,7,0);
@banks=(0,5,10,0,11,14,13,4,11,8,8,7,1,4,12,11);
%dists={};
$steps=0;
while(true){
    $steps++;
    $max=0;
    $maxn=0;
    for($x=0;$x<@banks;$x++){
	if($banks[$x]>$max){
		$maxn=$x;
		$max=$banks[$x];
	}
    }
    print "Biggest bank no $x:$max\n";
    $distrib=$max;
    $banks[$maxn]=0;
    $x=$maxn;
    while($distrib>0){
	$x++;
	$x=0  if($x==@banks);
	$banks[$x]++;
	$distrib--;
    }
    $key=join ':',@banks;
    print "$key\n";
    if ($dists{$key}){
    	$lastseen=$dists{$key};
	$diff=$steps-$lastseen;
    	print "Repeat of step $lastseen\n";
	print "Repeat cycle:$diff\n";
	last;
    }
    $dists{$key}=$steps;
}
print "Got repeat in $steps \n";
