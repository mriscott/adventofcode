$max =0;
%all=();
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
$all{$m}= (" Row $r col $c id $m\n");
$max =$m if $m>$max;
}
print ("Max id $max\n");
foreach (keys (%all)){
#print ("$_\n");
$d=$_+2;
$u=$_+1;

if (defined ($all{$d}) && !defined ($all{$u})){
print ("$u missing\n");
}
}
