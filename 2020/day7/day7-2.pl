%rules=();
%parentbags=();
foreach (<STDIN>){
	chomp;
	if( /(^.*)s contain (.*$)/){
		$rules{$1}=$2;
	}
}

foreach(keys (%rules)){
	print ("$_ -> $rules{$_}\n");
}
print ("\n\n");
print("Gold bag contains ". (recurse("shiny gold bag",0,"shiny gold bag")-1)." \n");

sub recurse($$$){
	my ($key,$pad,$parent)=@_;
	my $pads="";
	my $x;
	for($x=0;$x<$pad;$x++){
		$pads="$pads  ";
	}
	my $total=1;
	foreach(split(/,/,$rules{$key})){
		my $newkey=$_;
		my $subbags=0;
		if($newkey=~/\s*([0-9]+) /)
		{
			$subbags=$1;
		}
		$newkey=~s/^\s*[0-9]+ //;
		$newkey=~s/bags/bag/;
		$newkey=~s/\.//;
		print (" $pads * $newkey ($subbags)\n");
		$pad++;
		if (/shiny gold bag/){
			$parentbags{$parent}=1;
		}
		next if /no other bag/;
		print ("$pads contains $subbags of $newkey\n");
		my $underbag = recurse($newkey,$pad,$parent);
		$total+=$underbag*$subbags;

	}
	print ("$pads total under $key : $total\n");
	return $total;

}
