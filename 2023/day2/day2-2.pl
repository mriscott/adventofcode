$s=0;
while(<STDIN>){
$b=0;
$r=0;
$g=0;
foreach(/(\d+) red/g){
$r=$_ if($_>$r);
}
foreach(/(\d+) green/g){
$g=$_ if($_>$g);
}
foreach(/(\d+) blue/g){
$b=$_ if($_>$b);
}
($gg)=/Game (\d+):/;

$p=$r*$b*$g;
print "$gg $r $b $g -$p\n";
$s+=$p;
}
print $s;
