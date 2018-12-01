$total=0;
foreach(<STDIN>){
	chomp;
	print;
	@words=split / /;
	%hashwords={};
	$valid=1;
	foreach $word (@words){
		$word=join '',sort(split(//,$word));
		if($hashwords{$word}){
			print " - $word";
			$valid=0;
		}
		$hashwords{$word}=$word;
	}
	print(":$valid\n");
	$total+=$valid;
		
}
print "\n$total valid\n";
