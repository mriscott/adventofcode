@adapters=sort({$a <=> $b} <STDIN>);
$last=0;
$i=0;
$c=0;
@canskip=();
%runs=();
$run=1;
$ans=1;
$runstr="";
foreach (@adapters){
	chomp;
	print ("$_,");
}
print ("\n");
$ii=0;
foreach(@adapters){
	chomp;
	#print (",");
	#print;
	$jump=$_-$last;
	if ($jump>3) {
		#print ("ERROR - jump too big ($jump)\n");
		exit 1;
	}
	if($jump<3) {
		if($adapters[$i+1]<($last+3)){
			#print ("*");
			$canskip[$i]=1;
			$c++;
		}
		
	}
	$ii++;
	if ($ii==scalar(@adapters) && ($jump==1)){
		$run++;
		$runstr.=",$_";
		$jump=0;
	}
	if ($jump==1){
		$run++;
		$runstr.=",$_";
	}else{
		if($run!=0){
			$runs{$run}++;
# ignore runs of 1
# runs of 2 -> 1 options
# runs of 3 -> 2 options
# runs of 4 -> 3 options
			$ans*=2 if($run==3);
			$ans*=4 if($run==4);
			$ans*=7 if($run==5);
			if ($run>5){
				print ("ERROR: invalid run of $run\n");
				exit 1;
			}
			print ("Run of $run: $runstr\n");
			$run=1;
			$runstr=$_;
		}
	}
	$last=$_;
	$i++;
}
print ("\n");
$last+=3;
$max=$last;
print ("Built-in:->$max\n");
$arr=2**$c;
print("Possible arrangements: $arr\n");
print ("Runs:\n");
foreach (sort keys(%runs)){
	print ("  $_ -> $runs{$_}\n");
}

print ("Answer: $ans\n");
