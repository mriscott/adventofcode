#!/usr/bin/perl
foreach (<STDIN>){
  chomp;
  $line=$_;
  $len=length($line);
  print "$len:$line\n";
  do{
    $oldlen=$len;
    foreach $l (split //,'abcdefghijklmnopqrstuvwxyz'){
      $re=$l.uc($l);
      $er=uc($l).$l;
      $line=~s/$re//e;
      $line=~s/$er//e;
    }
    $len=length($line);
    #print "$len:$line\n";
  } while ($oldlen > $len);
  print "$line\n$len\n";

}
