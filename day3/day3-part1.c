#include <stdio.h>
#include <math.h>

#define MAXN 277678
#define MAX 5
#define USEARRAY (MAX*MAX>=MAXN)
int arr[MAX][MAX] ;

void print(){
	for (int y=0;y<MAX;y++){
		for (int x=0;x<MAX;x++){
			printf("%3d ",arr[x][y]);
		}
		printf("\n");
	}
}
void init(){
	for (int x=0;x<MAX;x++){
		for (int y=0;y<MAX;y++){
			arr[x][y]=0;
		}
	}
}

void main(){
	if (USEARRAY) init();
	int offset=MAX/2;
	int x=MAX/2;
	int y=MAX/2;
	int n=1;
	int maxx=x;
	int maxy=y;
	int minx=x;
	int miny=y;
	if (USEARRAY) arr[x][y]=n;
	int dx=1;
	int dy=0;
	for(n=2;n<=MAXN;n++){
		int ddx=0;
		int ddy=0;
		if (dx==0) {
			if(dy==1) ddx=-1;
			else ddx=1;
		}
		else {
			if(dx==1) ddy=1;
			else ddy=-1;
		}
		x=x+dx;
		y=y+dy;
		int d=abs(x-offset)+abs(y-offset);
		if(USEARRAY || n==MAXN ||n%100==0) printf("%d (%d,%d) -> %d\n",n,x,y,d);
		if(USEARRAY) arr[x][y]=n;
		int rotate=0;
		if(x<minx||x>maxx||y<miny||y>maxy){
			rotate=1;
		}
		if (rotate==1){
			dx=ddx;
			dy=ddy;
		}
		if (x<minx) minx=x;
		if (y<miny) miny=y;
		if (x>maxx) maxx=x;
		if (y>maxy) maxy=y;
		if(USEARRAY && (x==-1 || x== MAX || y==-1||y==MAX)) break;
	}

 	if(USEARRAY) print();
	
}
