#!/usr/bin/perl
$x=0;
while (<STDIN>)
{
$x = eval ("$x$_");
}
print $x;
print "\n";

