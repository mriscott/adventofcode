%yes=();
$g=0;
$tot=0;
foreach (<STDIN>){
chomp;
if (/^\s*$/){
$c=0;
foreach(keys(%yes)){
print ("$g $yes{$_}\n");
if ($g==$yes{$_}){
print("all yes - $_\n");
$c++;
}
}
print ("$c yeses\n");
$tot+=$c;

%yes=();
$g=0;
}
else {
$g++;
foreach(split(//,$_)){
print("Adding $_\n");
$yes{$_}+=1;
}
}
}
print ("Total : $tot\n");
