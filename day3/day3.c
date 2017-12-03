#include <stdio.h>

//#define MAX 527
#define MAX 5
int arr[MAX][MAX] ;

void init(){
	for (int x=0;x<MAX;x++){
		for (int y=0;y<MAX;y++){
			arr[x][y]=0;
		}
	}
	print();
}

void print(){
	for (int y=0;y<MAX;y++){
		for (int x=0;x<MAX;x++){
			printf("%3d ",arr[x][y]);
		}
		printf("\n");
	}
}
void main(){
	init();
	int x=MAX/2;
	int y=MAX/2;
	int n=1;
	arr[x][y]=n;
	int dx=1;
	int dy=0;
	while(x!=MAX && x!=0 && y!=MAX && y!=0){
		int ddx=0;
		int ddy=0;
		if (dx==0) {
			ddx=-dy;
		}
		else {
			ddy=-dx;	
		}
		x=x+dx;
		y=y+dy;
		printf("Testing:%d,%d=%d\n",x+ddx,y+ddy,arr[x+ddx,y+ddy]);
		if (arr[x+ddx,y+ddy]==0){
			dx=ddx;
			dy=ddy;
		}
		arr[x][y]=++n;

	}
	print();
	
}
