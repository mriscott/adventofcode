#include <stdio.h>
#include <math.h>

//#define MAXN 277678
#define MAXN (MAX*MAX)
#define MAX 100
#define USEARRAY (MAX*MAX>=MAXN)
#define PRINTARRAY 0
int arr[MAX][MAX] ;

void print(){
  if (PRINTARRAY) {
	for (int y=0;y<MAX;y++){
		for (int x=0;x<MAX;x++){
			printf("%3d ",arr[x][y]);
		}
		printf("\n");
	}
  }
}
void init(){
	for (int x=0;x<MAX;x++){
		for (int y=0;y<MAX;y++){
			arr[x][y]=0;
		}
	}
}

int getval(int x, int y){
  int val=0;
  int lox = (x>0) ? (x-1) : x;
  int hix = (x<MAX) ? (x+1) :x;
  int loy = (y>0) ? (y-1) : y;
  int hiy = (y<MAX) ? (y+1) : y;
    for(int xx=lox;xx<=hix;xx++){
      for(int yy=loy;yy<=hiy;yy++){
	if(xx!=x|| yy!=y) val+=arr[xx][yy];
      }
    }
  return val;
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
		if(USEARRAY) arr[x][y]=getval(x,y);
		if(USEARRAY || n==MAXN ||n%100==0) printf("%d (%d,%d) -> %d\n",n,x,y,arr[x][y]);
		if(USEARRAY && (arr[x][y]>277678)) break;
	       
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
