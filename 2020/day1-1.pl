foreach (<STDIN>){
jrint;
chomp;
push @arr,$_;
}
foreach(@arr){
$x=$_;
foreach (@arr){
$y=$_;
$z=$x+$y;
$a=$x*$y;
if($z==2020){
print ("$x+$y=$z\n");
print ("answer=$a\n");
}
}
}
