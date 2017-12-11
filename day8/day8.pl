%reg=();
$maxeverval=0;
foreach (<STDIN>){
    print;
    chomp;
    @parts=split / /;
    $target=$parts[0];
    $op=$parts[1];
    $amt=$parts[2];
    $refop=$parts[4];
    $cmp=$parts[5];
    $refamt=$parts[6];

    $destval=$reg{$target};
    $destval=0 if !$destval;
    $srcval=$reg{$refop};
    $srcval=0 if !$srcval;

    $statement="$srcval $cmp $refamt";
    if(eval($statement)){
	print "TRUE\n";
	if($op eq 'inc'){
	    $destval+=$amt;
	}
	elsif($op eq 'dec'){
	    $destval-=$amt;
	}
	else {
	    print("Invalid op:$op\n");
	}
	$maxeverval=$destval if($destval>$maxeverval);
    }else{
	print "FALSE\n";
    }
    

    $reg{$refop}=$srcval;
    print "$refop -> $srcval\n";
    $reg{$target}=$destval;
    print "$target -> $destval\n";
}

print "Final registers:\n";
$maxval=-999;
foreach (sort keys (%reg)){
    $maxval=$reg{$_} if($reg{$_}>$maxval);
    print "$_ => $reg{$_}\n";
}
print "Max val:$maxval\n";
print "Max Ever val:$maxeverval\n";
