%yes=();
$tot=0;
foreach (<STDIN>){
chomp;
if (/^\s*$/){
$c=keys(%yes);
print ("$c yeses\n");
$tot+=$c;
%yes=();
}
foreach(split(//,$_)){
print("Adding $_\n");
$yes{$_}=1;
}
}
print ("Total : $tot\n");
