#include <stdio.h>
#include <stdlib.h>

#define WIDTH 202
#define HEIGHT 202
#define FILENAME "input.txt"


//#define WIDTH 17
//#define HEIGHT 7
//#define FILENAME "example.txt"

char grid [WIDTH][HEIGHT];


void readFile(){
	FILE *f=fopen (FILENAME,"r");
	int x=0;
	int y=0;
	char c;
	if(f!=0){
		while(!feof(f)){
			c=fgetc(f);
			if(x>=WIDTH){
			  printf("Width %d too small\n",x);
			  exit(1);
			}
			//printf( "%d,%d=%d\n",x,y,c);
			if(c==-1){
				break;
			}
			if(c==10||c==13){
			  y++;
				x=0;
				if(y>=HEIGHT){
				  printf("Height %d too small\n",y);
				  exit(1);
				}

			}else{
				grid[x][y]=c;
				x++;
			}

		}
		printf("Height:%d\n",y);
		printf("Width:%d\n",x);

		fclose(f);
	}

}

void printGrid()
{
	char line[WIDTH];
	for(int y=0;y<HEIGHT;y++){
		for(int x=0;x<WIDTH;x++){
			line[x]=grid[x][y];
		}
		printf("%s\n",line);
	}
}

void followLine(){
	int x=0;
	int y=0;
	while(grid[x][0]!='|') x++;
	char d='S';
	int steps=0;
		
	do {
	  //printf("%d,%d:%d=",x,y,d);	
	  //		putc(grid[x][y],stdout);
	  //	printf("\n");
		switch(grid[x][y]){
			case '+':
			  //printf( "Turning at %d,%d\n",x,y);
				
				if(d=='N'||d=='S'){
					if(x>0 && grid[x-1][y]!=' ')  d='W';
					else if(x<(WIDTH-1) && grid[x+1][y]!=' ')  d='E';
				}
				else if(d=='E'||d=='W'){
					if(y>0 && grid[x][y-1]!=' ')  d='N';
					else if(y<(HEIGHT-1) && grid[x][y+1]!=' ')  d='S';
				}
				break;
			case '|':
			case '-':
				// keep going
				break;
			case ' ':
			default:
				putc(grid[x][y],stdout);
				break;
		}
		if(d=='S') y++;
		if(d=='N') y--;
		if(d=='W') x--;
		if(d=='E') x++;
		steps++;
		if(grid[x][y]==' ') break;

		if(y==-1||x==-1||x==WIDTH||y==HEIGHT) break;
	}while(1);
	printf("\nSteps: %d\n",steps);

}


int main(){
	readFile();
	//printGrid();
	followLine();
}
