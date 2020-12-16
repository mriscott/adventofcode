@adapters=sort({$a <=> $b} <STDIN>);
$last=0;
$i=0;
$c=0;
@canskip=();
foreach(@adapters){
	chomp;
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
	#print ("\n");
	$last=$_;
	$i++;
}
$last+=3;
$max=$last;
print ("Built-in:->$max\n");
$arr=2**$c;
print("Possible arrangements: $arr\n");
$validcnt=0;
$lastprog=0;
@masks=();
for($x=0;$x<$arr;$x++){
	$prog=$x/$arr * 100;
	if ($prog>$lastprog+1){
		$lastprog=int($prog);
		#print ("$lastprog  \n");
	}
	#print ("$x)");
	$quickskip=9;
	foreach(@masks){
		$quickskip=1 if (($x & $_)==$_);
	}
	next if $quickskip==1;
	@copy=();
	$y=0;
	$last=0;
	$skipnum=0;
	$n=0;
	$last=0;
	$isvalid=1;
	foreach(@adapters){
		chomp;
		# if this one is skippable
		#print ("$_");
		if($canskip[$y]==1){
			#print("?");
			#check if we should skip this time
			$mybit=2**$skipnum;
			$skipnum++;
			if(($mybit & $x)!=0){
				#skip
				#print ("*");
			}
			else{
				$copy[$n]=$_;
				$n++;
				if(($_-$last)>3){
					$isvalid=0;
					#print ("Invalid jump $last -> $_\n");
					last;
				}
				$last=$_;
			}
		
		}else {
				$copy[$n]=$_;
				$n++;
				if(($_-$last)>3){
					$isvalid=0;
					#print ("Invalid jump $last -> $_\n");
					last;
				}
				$last=$_;
		}
		#print(",");

		$y++;

	}
	#print (" $isvalid , $max , $last  ");
	if($isvalid==1 && $max <= ($last+3)){
		$validcnt++;
	}
	else{
		push @masks, $x;
	}
	#	print ("\n");

	#print (" => ");
	# now check if @copy is valid
	$last=0;
	$valid=1;
	foreach(@copy){
		print ("$_,");
	if ($_>($last+3)){
			$valid=0;
			last;
		}
		$last=$_;
	}
	if ($valid==0){
		print ("INVALID JUMP");
	}
	print ("\n");
	$validcnt+=$valid;
	
}
print ("$validcnt valid arrangements\n");

