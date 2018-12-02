#!/usr/bin/perl
$countoftwo=0;
$countofthree=0;
foreach (<STDIN>){
  %count={};
  $hasthree=0;
  $hastwo=0;
  chomp;
  print ;
  foreach(split(//)){
    $count{$_}++;
    #print "Processing $_:$count{$_}\n";
  }
  foreach (keys (%count)){
    $hasthree=1 if($count{$_}==3);
    $hastwo=1 if($count{$_}==2);
  }
  print " 2s:$hastwo 3s:$hasthree\n";
  $countoftwo++ if $hastwo==1;
  $countofthree++ if $hasthree==1;
}
print "Checksum:".($countoftwo*$countofthree)."\n";
