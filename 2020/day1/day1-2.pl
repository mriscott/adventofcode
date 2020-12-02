foreach (<STDIN>){
jrint;
chomp;
push @arr,$_;
}
foreach(@arr){
$x=$_;
foreach (@arr){
$y=$_;
foreach (@arr){
$z=$_;
$a=$x+$y+$z;
$b=$x*$y*$z;
if($a==2020){
print ("sum=$a\n");
print ("answer=$b\n");
}
}
}
}
