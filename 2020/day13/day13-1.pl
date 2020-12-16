@inp=(<STDIN>);
$time=$inp[0];
chomp($time);
print ("Earliest time : $time\n");
@bus=split(/,/,$inp[1]);
$ans=0;
for($t=$time;$ans==0;$t++){
	foreach $x (@bus){
		if($t=~/x/){
			print ("Not running\n");
			next;
		}
		if ($x!=0 && ($t % $x) == 0){
			print ("Bus $x departs at $t\n");
			$ans=($t-$time)*$x;
		}
	}
}
print ("Answer: $ans\n");
