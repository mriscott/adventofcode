$max =0;
foreach (<STDIN>){
print;
@bits=split(//,$_);
$row="0b";
$col="0b";
foreach(@bits){
if(/B/){
$row.='1';
}
if(/F/){
$row.='0';
}

if(/R/){
$col.='1';
}
if(/L/){
$col.='0';
}
}
$r=oct($row);
$c=oct($col);
$m=$r*8+$c;
print (" Row $r col $c id $m\n");
$max =$m if $m>$max;
}
print ("Max id $max\n");

