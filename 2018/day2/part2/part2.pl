#!/usr/bin/perl
@lines=();
foreach (<STDIN>){
  chomp;
  push @lines, $_;
  @splt=split(//);
  foreach(@lines){
    @splto=split(//);
    $nomatch=0;
    $str="";
    for($x=0;$x<scalar(@splto);$x++){
      if ($splto[$x] ne $splt[$x]) {
        $nomatch++;
      } else {
        $str.=$splt[$x];
      }
    }
    if($nomatch==1) {
      print "Match: @splto :  @splt: $match\n";
      print "Common:$str\n";
    }
    
  }

  
}

