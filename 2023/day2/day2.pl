$s=0;
while(<STDIN>){
$b=0;
foreach(/(\d+) red/g){
$b=1 if($_>12);
}
foreach(/(\d+) green/g){
$b=1 if($_>13);
}
foreach(/(\d+) blue/g){
$b=1 if($_>14);
}
if ($b==0){
($g)=/Game (\d+):/;
$s+=$g;
}
}
print $s;
