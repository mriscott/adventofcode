$onev=0;
$threev=0;
@adapters=sort({$a <=> $b} <STDIN>);
$last=0;
foreach(@adapters){
	print;
	chomp;
	$jump=$_-$last;
	if ($jump>3) {
		print ("ERROR - jump too big ($jump)\n");
		exit 1;
	}
	$onev++ if $jump==1;
	$threev++ if $jump==3;
	$last=$_;
}
$last+=3;
print ("Built-in:->$last\n");
$threev++;
$mult=$onev*$threev;
print ("One volt jumps: $onev\n");
print ("Three volt jumps: $threev\n");
print("Answer: $mult\n");

