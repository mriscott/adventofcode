@lines=<STDIN>;

$mult=1;
$skiprows=0;
@slopes=(1,3,5,7,1);
foreach $slope (@slopes){
$trees=0;
$x=0;
$linenum=0;
foreach(@lines){
	$linenum++;
	next if ($skiprows==1 && $linenum%2==0);
	chomp;
	@row=split (//,$_);
	if($row[$x] eq '#'){
		$trees++;
		$row[$x]='X';
	}
	else {
		if ($row[$x] eq '.'){
			$row[$x]='O';
		} else {
			print ("ERROR\n");
			exit 1;
		}
	}
	$x+=$slope;
	$x-=@row if ($x>=@row) ;

	 foreach(@row){  
	 print ("$_");
	 }                                                                                                     
	print ("\n");
}
print ("Hit $trees trees with slope $slope and $skiprows\n");
$mult*=$trees;
$skiprows=1 if ($slope==7);
}
print ("Answer: $mult\n");
