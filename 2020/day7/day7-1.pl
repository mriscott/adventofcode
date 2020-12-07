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
foreach (keys(%rules)){
	recurse($_,0,$_);
}

sub recurse($$$){
	my ($key,$pad,$parent)=@_;
	my $pads="";
	my $x;
	for($x=0;$x<$pad;$x++){
		$pads="$pads  ";
	}
	print ("$pads * $key ->\n");
	foreach(split(/,/,$rules{$key})){
		print ("$pads   - $_");
		my $newkey=$_;
		$newkey=~s/^\s*[0-9]+ //;
		$newkey=~s/bags/bag/;
		$newkey=~s/\.//;
		$pad++;
		print (" ($newkey) $pad\n");
		if (/shiny gold bag/){
			$parentbags{$parent}=1;
			print("** Can be under $parent") 
		}
		next if /no other bag/;
		recurse($newkey,$pad,$parent);
	}

}

foreach (keys (%parentbags)){
	print ("PARENT:$_\n");
}
$answer=scalar(keys (%parentbags));
print ("\n $answer\n");
