#!/usr/bin/perl
foreach (<STDIN>){
  chomp;
  $line=$_;
  $len=length($line);
  print "$len:$line\n";
  $origline=$line;
  $bestchar='-';
  $bestlen=$len;
  foreach $clean (split //,'abcdefghijklmnopqrstuvwxyz'){
    $re='['.$clean.uc($clean).']';
    next if !/$re/;
    print "Cleaning $re:";
    $line=$origline;
    $line=~s/$re//eg;
    do{
      $oldlen=$len;
      foreach $l (split //,'abcdefghijklmnopqrstuvwxyz'){
        $re=$l.uc($l);
        $er=uc($l).$l;
        $line=~s/$re//e;
        $line=~s/$er//e;
      }
      $len=length($line);
    } while ($oldlen > $len);
    print "$len\n";
    if($len<$bestlen){
      $bestchar=$clean;
      $bestlen=$len;
    }
  } 
  print "Best char $bestchar with a len of $bestlen\n";

}
