@mandatory=('byr','iyr','eyr','hgt','hcl','ecl','pid');
@optional=('cid');
%passport=();
$validcnt=0;
foreach(<STDIN>){
	if (/^\s*$/ && scalar(keys(%passport))>0){
		foreach (keys(%passport)){
			print ("$_ -> $passport{$_}\n");
		}
		$valid=1;
		foreach(@mandatory){
			if (! defined ($passport{$_})){
				print ("Mandatory field $_ missing\n");
				$valid=0;
			}
		}
		foreach(@optional){
			if (! defined ($passport{$_})){
				print ("Optional field $_ missing\n");
			}
		}
		if ($valid==0){
			print ("INVALID PASSPORT\n");
		}else{
			print ("OK\n");
			$validcnt++;
		}
		print("\n");
		%passport=();
	}
	foreach (split /\s+/){
		if(/(.*):(.*)/){
			$passport{$1}=$2;
		}
	}

}
print ("\n\n$validcnt valid passports\n");
