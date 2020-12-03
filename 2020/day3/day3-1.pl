@lines=<STDIN>;

$trees=0;
$x=0;
foreach(@lines){
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
	foreach(@row){
		print ("$_");
	}
	print ("\n");
	$x+=3;
	$x-=@row if ($x>=@row) ;

}
print ("Hit $trees trees\n");
