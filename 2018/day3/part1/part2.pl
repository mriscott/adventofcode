#!/usr/bin/perl

@fabric=();
$maxx-0;
$maxy=0;
%overlaps={};
while (<STDIN>)
{
   if (/#(\d+) @ (\d+),(\d+): (\d+)x(\d+)/)
   {
      ($id,$x,$y,$w,$h)=($1,$2,$3,$4,$5);
      $maxx=$x+$w if($maxx<($x+$w));
      $maxy=$y+$h if($maxy<($y+$h));
      print "ID:$id - +$x +$y $w x $h\n";
      print "Fabric:$maxx x $maxy\n";
      for($xx=$x;$xx<($x+$w);$xx++) {
        for($yy=$y;$yy<($y+$h);$yy++) {
           if(!$fabric[$xx][$yy]) {
             $fabric[$xx][$yy]=$id;
           }else{
             $overlaps{$id}=1;
             $overlaps{$fabric[$xx][$yy]}=1;
             $fabric[$xx][$yy]='X';
           }
        }
      }
      
   }
}
$claims=0;
for($y=0;$y<=$maxy;$y++){
  for($x=0;$x<=$maxx;$x++){
    print '.' if !$fabric[$x][$y];
    print $fabric[$x][$y];
    $claims++ if ($fabric[$x][$y] eq 'X');
  }
  print "\n";
}

print "$claims in 2 or more\n";
foreach (keys %overlaps) {
   print "Testing $_\n";
   print "$_ has no overlaps\n" if !$overlaps{$_};
}
