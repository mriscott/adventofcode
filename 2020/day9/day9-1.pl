@nos=0;
$i=0;
$p=25;
foreach (<STDIN>){
chomp;
$nos[$i]=$_;
if($i>=$p){
$valid=0;
for ($x=$i-1;$x>($i-$p-1);$x--){
for ($y=$i-1;$y>($i-$p-1);$y--){
if($x!=$y && ($nos[$x]+$nos[$y]==$nos[$i])){
print("$nos[$x]+$nos[$y]=$nos[$i]\n");
$valid=1;
last;
}
last if ($valid==1);
}
}
if ($valid==0){
print ("line $i ( $nos[$i] ) invalid\n");
last;
}
}
$i++;
}

