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
int numx,numlen;


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
			if(c==0 || c==-1) {
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
}

int issymbol(int x, int y){
	if(x<0 || x>=ROWS || y<0 || y>=COLS) return 0;
	char c=grid[y][x];
	if(isdigit(c)) return 0;
	if(c=='.') return 0;
	return 1;
}



int numat(int x, int y){
	char c=grid[y][x];
	char number[COLS];
	char * p;
	int n;
	if(isdigit(c)==0) return -1;
	p=grid[y];	
	p+=x;	
	numx=x+1;
	while(isdigit(*p)) p--,numx--;
	p++;
	n=0;
	while(isdigit(*p)) n++,p++;
	p-=n;
	strncpy(number,p,n);
	numlen=n;
	number[n]=0;
	n=atoi(number);
	return n;
}


void process() {
	long total=0;
	for(int y=0;y<ROWS;y++){
		for(int x=0;x<COLS;x++){
			int xx,yy;
			int num=numat(x,y);
			int good=0;
			if(num==-1) continue;
			// look for symbols
			for(yy=y-1;yy<y+2;yy++){
				for(xx=numx-1;xx<(numx+numlen+1);xx++){
					if(issymbol(xx,yy)) good=1;
				}
			}
			printf("%d,%d\n",num,good);
			x=numx+numlen;

			total+=num*good;
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
