#include <stdio.h>
#include <stdlib.h>

#define BUFSIZE 20000

int process(char * input){
	int grouplevel=0;
	int nogroups=0;
	int garbage=0;
	int score=0;
	
	for (char *x=input;*x!=0;x++){
		if(garbage==1 && *x!='>' && *x!='!') continue;
		switch(*x){
		case '!':
			if(garbage==1){
				x++;
				if(*x==0) break;
				continue;
			}
			break;
		case '<':
			garbage=1;
			break;
		case '>':
			garbage=0;
			break;
		case '{':
			grouplevel++;
			nogroups++;
			score+=grouplevel;
			break;
		case '}':
			grouplevel--;
		}
		
	}
	if(grouplevel!=0){
		printf("Invalid input\n");
		exit(0);
	}
	return score;

}

void test(char * input, int answer){
	printf("Testing %s\n",input);
	int gps=process(input);
	if(answer!=gps){
		printf("Expected %d, got %d\n",answer,gps);
		exit(1);
	}
}

void runtests(){
	test("{}", 1);
	test("{{{}}}",  6);
	test("{{},{}}",  5);
	test("{{{},{},{{}}}}",  16);
	test("{<a>,<a>,<a>,<a>}",  1);
	test("{{<!!>},{<!!>},{<!!>},{<!!>}}",  9);
	test("{{<a!>},{<a!>},{<a!>},{<ab>}}",  3);

	printf("All tests pass\n");
}

int readfile(char *filename, char*buf,int max){
	FILE * file = fopen(filename,"r");
	int n=0;
	if(file){
		int c=0;
		while((c=getc(file))!=EOF){
			*buf=(char)c;
			buf++;
			n++;
			if(n==(max-2)) break;

		}
		fclose(file);
	}
	buf++;
	*buf=EOF;
	return n;


}

void main(){
	runtests();
	char buf[BUFSIZE];
	int n=readfile("input.txt",buf,BUFSIZE);
	printf("Read %d chars\n",n);
	printf("Answer:%d\n",process(buf));

}

