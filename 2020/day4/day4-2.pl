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
			print ("MANDATORY FIELDS MISSING\n");
		}
		else{
			$byr=$passport{'byr'};
			if ($byr!~/\d\d\d\d/ || $byr<1920 || $byr>2002){
				print ("Invalid byr: $byr\n");
				$valid=0;
			}	
			$iyr=$passport{'iyr'};
			if ($iyr!~/\d\d\d\d/ || $iyr<2010 || $iyr>2020){
				print ("Invalid iyr $iyr\n");
				$valid=0;
			}	
			$eyr=$passport{'eyr'};
			if ($eyr!~/\d\d\d\d/ || $eyr<2020 || $eyr>2030){
				print ("Invalid eyr $eyr\n");
				$valid=0;
			}	
			
			if(vhgt($passport{'hgt'})==0){
				$valid=0;
			}
			if(vhcl($passport{'hcl'})==0){
				$valid=0;
			}
			if(vecl($passport{'ecl'})==0){
				$valid=0;
			}
			if(vpid($passport{'pid'})==0){
				$valid=0;
			}
			
		}
		if ($valid==1){
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


sub vhgt($){
	($hgt)=@_;
	$validhgt=0;
	if ($hgt=~/(\d+)cm/){
		if ($1>=150 && $1<=193){
			$validhgt=1;
		}
	}
	if ($hgt=~/(\d+)in/){
		if ($1>=59 && $1<=76){
			$validhgt=1;
		}
	}
	print ("Invalid hgt : $hgt\n") if $validhgt==0;
	return ($validhgt);
}

sub vhcl($){
	($hcl)=@_;
	$validhcl=0;
	$err=0;
	$c=0;
	if($hcl=~/^#(.*)$/){
		foreach(split //,$1){
			if(/[a-f]/ || /[0-9]/){
				$c++;
			}else{
				$err=1;
			}

		}
		if($err==0 && $c==6){
			$validhcl=1;
		}
	}
	print ("Invalid hcl : $hcl \n") if $validhcl==0;
	return ($validhcl);
}
sub vecl($){
	($ecl)=@_;
	$validecl=0;
	@allowed=('amb','blu','brn','gry','grn','hzl','oth');
	foreach (@allowed){
		$validecl=1 if ($ecl eq $_);
	}
	
	print ("Invalid ecl : $ecl\n") if $validecl==0;
	return ($validecl);
}

sub vpid($){
	($pid)=@_;
	return 1 if(length($pid)==9 && $pid=~/^[0-9]+$/);
	print ("Invalid pid $pid\n");
	return 0;
}

