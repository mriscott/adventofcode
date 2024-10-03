#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

//#define TEST
#ifdef TEST
#define ROWS 10
#define COLS 10
#define FILENAME "test"
#else
#define ROWS 140
#define COLS 140
#define FILENAME "input.3"
#endif


char grid[COLS][ROWS+1];


void die(char *s){
	printf("ERROR: %s\n",s);
	exit(1);
}

void loadgrid(){
	char c=0;
	int x,y;
	FILE *f=fopen(FILENAME,"r");
	for(y=0;y<ROWS;y++){
		for(x=0;x<COLS;x++){
			c=getc(f);
			if(c==-1) {
				die("Bad read - file too short");
			}
			grid[y][x]=c;
		}
		c=getc(f);
		/* should be eol or end of file*/
		if(c!='\n' && c!=-1) {
			printf("Got %d\n",c);
			die("Bad read - expected EOL");
		}
		// add nul for ease of printing
		grid[y][x+1]=0;

	

	}
	fclose(f);
}



int numat(int x, int y){
	char c=grid[y][x];
	char number[COLS];
	char * p;
	int n;
	if(isdigit(c)==0) return -1;
	p=grid[y];	
	p+=x;	
	while(isdigit(*p)) p--;
	p++;
	n=0;
	while(isdigit(*p)) n++,p++;
	p-=n;
	strncpy(number,p,n);
	number[n]=0;
	n=atoi(number);
	return n;
}

long getratio(int x,int y){
	long ratio=0;
	int n=0;
	int num=0;
	for(int yy=y-1;yy<y+2;yy++){
		for(int xx=x-1;xx<x+2;xx++){
			num=numat(xx,yy);
			if(num==-1) continue;
			printf("%d,%d->%d\n",xx,yy,num);
			if(ratio==0) ratio=num;
			else ratio*=num;
			n++;
			/* can only have another no on this row if this is top left and the top centre isnt a number
			 * so skip the last no if this is the middle one , or if the number in the middle matches this number*/
		 	if((xx==(x-1)) && (numat(x,yy)==num)) break;	
			if(xx>=x) break;

		}
	}
	if(n!=2) ratio=0;
	return ratio;
}

void process() {
	long total=0;
	for(int y=0;y<ROWS;y++){
		for(int x=0;x<COLS;x++){
			
			if(grid[y][x]=='*'){
				total+=getratio(x,y);
				printf ("Gear:%d,%d -> %ld\n",x,y,total);
			}
		}
	}
	printf("Total:%ld\n",total);
}
void printgrid() {
	for(int y=0;y<ROWS;y++){
		printf("%s\n",grid[y]);
	}
}

int main(int argc,char **argv){
	loadgrid();
	printgrid();
	process();

}
