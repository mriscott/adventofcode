#!/bin/perl

@seeds=();
@soils=();
@ferts=();
@water=();
@light=();
@temp=();
@humid=();
@loc=();

%allhash={};

sub readfile($)
{
    ($file)=@_;
    open(F,$file);
    $curmap="";
    foreach (<F>){
	if (/^seeds:/){
	    @bit=split /:/;
	    $bit[1]=~s/^ *//;
	    $bit[1]=~s/\s*$//;
	    @seeds=split / /,$bit[1];
	}
	elsif (/(.*-to-.*) map:/){
	    $curmap=$1;
	    $allhash{$curmap}="";
	}
	else {
	    @nums=split / /;
	    if (defined ($nums[2])){
			($dest,$source,$range)=@nums;
			$diff=$dest-$source;
			$end=$source+$range;
			$allhash{$curmap}="$allhash{$curmap}$source-$end+$diff,";

	    }
	}
    }
	close (F);
}

sub test(){
    readfile("test");
    die "Test failed" if(getMinLoc1()!=35);
	print("Test 1 passed\n");
	die "Test 2 failed" if (getMinLoc2()!=46);
	print("Test 2 passed\n");
	readfile("input");
	print("Part 1:");
	print (getMinLoc1());
	print "\n";
	print("Part 2:");
	print (getMinLoc2());
	#2256906991 too high!
	print "\n";

}


sub getMinLoc1(){
	$min=999999999;
	for($x=0;$x<@seeds;$x++){
		$fert=seedToLoc($seeds[$x]);
		$min=$fert if ($fert<$min);
    }
	return $min;

}
sub getMinLoc2(){
	$seed=0;
	$min=0;
	$done=0;
	

	# get ranges
	while($done==0){
	    $min++;
		$seed=locToSeed($min);
		for($x=0;$x<@seeds;$x+=2){
			print ("Range:$seeds[$x]-") if($min==1);
			print ($seeds[$x]+$seeds[$x+1])if($min==1);
			print ("\n")if($min==1);
			if($seed>$seeds[$x]&& $seed<($seeds[$x]+$seeds[$x+1])){
				$done=1;
				print("$seed -> $min\n");
				last;
			}
		}

	}
	return $min;

}

sub seedToLoc($){
	($val)=@_;
	$type="seed";
	@maps=keys(%allhash);
	while ($type!~"location"){
		foreach(@maps){
			$match=0;
			if (/${type}-to-(.*)/){
				$target=$1;
				@mappings=split /,/, $allhash{"$type-to-$target"};
				foreach(@mappings){
					next if $match;
					if ( /(\d+)-(\d+)\+([\-\d]+)/){
						$sourcestart=$1;
						$sourceend=$2;
						$diff=$3;
						if($val<$sourceend && $val>=$sourcestart){
							$val+=$diff;
							$match=1;;
						}
					}
												
					
				}
				$type=$target;
			   
			}
		
		}
	}
	return $val;
}

sub locToSeed($){
	($val)=@_;
	$type="location";
	@maps=keys(%allhash);
	while ($type!~"seed"){
		foreach(@maps){
			$match=0;
			if (/(.*)-to-${type}/){
				$target=$1;
				@mappings=split /,/, $allhash{"$target-to-$type"};
				foreach(@mappings){
					next if $match;
					if ( /(\d+)-(\d+)\+([\-\d]+)/){
						$sourcestart=$1+$3;
						$sourceend=$2+$3;
						$diff=$3;
						
						if($val<$sourceend && $val>=$sourcestart){
							$val-=$diff;
							$match=1;;
						}
					}
												
					
				}
				$type=$target;
			   
			}
		
		}
	}
	return $val;
}



test();







