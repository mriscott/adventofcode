@guards=();
$day=0;
$guard=0;
foreach (<STDIN>)
{
#[1518-11-05 00:03] Guard #99 begins shift
  if(/.*-(\d+)-(\d+) (\d+):(\d+).*Guard #(\d+) begins shift/){
     $day=($1*100)+$2;
     $guard=$5;
     die "Guard 0" if !$guard;
     $asleep=0;
     $min=0;
  }

# [1518-11-02 00:40] falls asleep
  if(/.*-(\d+)-(\d+) (\d+):(\d+).*falls asleep/){
    $day=($1*100)+$2;
    $guards[$day][60]=$guard;
    die "State error" if ($asleep);
    die "Fell asleep not in 00" if $3!=00;
    print "Guard $guard fell asleep on $day at $4\n";
    print "Awake from $min to $4\n";
    for($x=$min;$x<$4;$x++){
      $guards[$day][$x]=$asleep;
    }
    $min=$4;
    $asleep=1;
  }
# [1518-11-02 00:50] wakes up
  if(/.-*(\d+)-(\d+) (\d+):(\d+).*wakes up/){
     die "State error" if (!$asleep);
     die "Woke up not in 00" if $3!=00;
     print "Guard $guard woke on $day at $4\n";
     print "Asleep from $min to $4\n";
     for($x=$min;$x<$4;$x++){
       $guards[$day][$x]=$asleep;
     }
     $min=$4;
     $asleep=0;
  }

}
  print "$asleep from $min to $60\n";
  for($x=$min;$x<60;$x++){
    $guards[$day][$x]=$asleep;
  }
$maxsleep=0;
$worstguard=0;
@guardsleep=();
@guardsleepmins;
for ($x=99;$x<1231;$x++){
  $sleep=0;
  if($guards[$x][60]){
    $guard=$guards[$x][60];
    print "d:$x #$guard  ";
    for ($m=0;$m<60;$m++){
      if (!$guards[$x][$m]){
        print ".";
      } 
      else {
         print "#";
         $sleep++;
         $guardsleepmins[$guard][$m]++;
      }
    }
    print "\n";
    $sleep+=$guardsleep[$guard];
    if($sleep>$maxsleep){
       $maxsleep=$sleep;
       $badguard=$guard;
    }
    $guardsleep[$guard]=$sleep;
  }
}
print "Worst Guard #$badguard with $maxsleep mins\n";
$worstsleepmin=0;
$maxguardsleep=0;
for ($m=0;$m<60;$m++){
  if($guardsleepmins[$badguard][$m]>$maxguardsleep){
    $worstsleepmin=$m;
    $maxguardsleep=$guardsleepmins[$badguard][$m]; 
  }
}
print "Worst min $worstsleepmin with $maxguardsleep\n";
print "Answer:". $worstsleepmin * $badguard ."\n";
