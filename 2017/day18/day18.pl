%registerx=();
%registery=();
$max=26;
for($x=0;$x<$max;$x++){
    $registerx{chr($x+97)}=0;
    $registery{chr($x+97)}=0;
}
$registerx{'p'}=0;
$registery{'p'}=1;
#@dance=("s1","x3/4","pe/b");

@dance=();
open (FILE,"<","input.txt");
while (<FILE>){
    chomp;
    push @dance,$_;
}

close FILE;


$index=0;
$y=0;
$x=0;
@ystack=();
@xstack=();
$ysend=0;
while($y<@dance || $x<@dance){
    $ywaiting=0;
    $ywaiting=1 if($y>=@dance);
    $xwaiting=0;
    $xwaiting=1 if ($x>@dance);
    if($x<@dance){
	$_="$dance[$x]\n";


	print $_;
	if(/set (\w) (-?\d+)/){
	    $registerx{$1}=$2;
	}
	elsif(/set (\w) (\w)/){
	    $registerx{$1}=$registerx{$2};
	}
	if(/snd (-?\d+)/){
	    push @xstack,$1;
	    print "X sent $1\n";
	}
	elsif(/snd (\w)/){
	    push @xstack,$registerx{$1};
	    print "X sent $registerx{$1}\n";
	}
	if(/add (\w) (-?\d+)/){
	    $registerx{$1}+=$2;
	}    
	elsif(/add (\w) (\w)/){
	    $registerx{$1}+=$registerx{$2};
	}    
	if(/mul (\w) (-?\d+)/){
	    $registerx{$1}*=$2;
	}
	elsif(/mul (\w) (\w)/){
	    $registerx{$1}*=$registerx{$2};
	}
	if(/mod (\w) (-?\d+)/){
	    $registerx{$1}%=$2;
	}
	elsif(/mod (\w) (\w)/){
	    $registerx{$1}%=$registerx{$2};
	}
	if(/rcv (\w)/){
	
	    if(@ystack>0){
		@ystack=reverse(@ystack);
		$recv=pop @ystack;
		@ystack=reverse(@ystack);
		print"X Received $recv into $1\n";
		$registerx{$1}=$recv;
	    }
	    else {
		print ("X waiting\n");
		$xwaiting=1;
		$x--;
	    }
	    
	}
	if(/jgz (-?\d+) (-?\d+)/){
	    if($1>0){
	    $x+=$2;
	    $x--;
	    }
	}   
	elsif(/jgz (\w) (-?\d+)/){
	    if($registerx{$1}>0){
	    $x+=$2;
	    $x--;
	    }
	}
	elsif(/jgz (\w) (\w)/){
	    if($registerx{$1}>0){
	    $x+=$registerx{$2};
	    $x--;
	    }
	}
	print("X($x):");
	for($n=0;$n<$max;$n++){
	    $ch=chr($n+97);
	    if($registerx{$ch}!=0){
		print "$ch=$registerx{$ch} ";
	    }
	}
	print "\n";	
	$x++;
    }
    # Y
    

    if($y<@dance){
    $_="$dance[$y]\n";


	print $_;
    if(/set (\w) (-?\d+)/){
	$registery{$1}=$2;
    }
    elsif(/set (\w) (\w)/){
	$registery{$1}=$registery{$2};
    }
    if(/snd (-?\d+)/){
	push @ystack,$1;
	print "Y sent $1\n";
	$ysend++;
    }
    elsif(/snd (\w)/){
	$snd=$registery{$1};
	push @ystack,$snd;
	print "Y sent $snd\n";
	$ysend++;
    }
    if(/add (\w) (-?\d+)/){
	$registery{$1}+=$2;
    }    
    elsif(/add (\w) (\w)/){
	$registery{$1}+=$registery{$2};
    }    
    if(/mul (\w) (-?\d+)/){
	$registery{$1}*=$2;
    }
    elsif(/mul (\w) (\w)/){
	$registery{$1}*=$registery{$2};
    }
    if(/mod (\w) (-?\d+)/){
	$registery{$1}%=$2;
    }
    elsif(/mod (\w) (\w)/){
	$registery{$1}%=$registery{$2};
    }
    if(/rcv (\w)/){
	if(@xstack>0){
	    @xstack=reverse(@xstack);
	    $recv=pop @xstack;
	    @xstack=reverse(@xstack);
	    print"Y Received $recv into $1\n";
	    $registery{$1}=$recv;
	}	
	else {
	    print ("Y waiting\n");
	    $ywaiting=1;
	    $y--;
	}
	
    }
    if(/jgz (-?\d+) (-?\d+)/){
        if($1>0){
	    $y+=$2;
	    $y--;
	}
    }   
    elsif(/jgz (\w) (-?\d+)/){
        if($registery{$1}>0){
	    $y+=$2;
	    $y--;
	}
    }
    
    elsif(/jgz (\w) (\w)/){
        if($registery{$1}>0){
	    $y+=$registery{$2};
	    $y--;
	}
    }


    print("Y($y):");
    for($n=0;$n<$max;$n++){
	$ch=chr($n+97);
	if($registery{$ch}!=0){
	    print "$ch=$registery{$ch} ";
	}
    }
    print "\n";
    $y++;
    
    }
    if($xwaiting==1 && $ywaiting==1){
	print("Deadlock\n");
	last;
    }
    if($y==@dance){
	print("Y finished\n");
    }
    if($x==@dance){
	print("X finished\n");
    }
    #print "Xstack:@xstack\n";
    #print "Ystack:@ystack\n";
   
}
print "Y sent $ysend values\n";
