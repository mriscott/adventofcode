#include <stdio.h>




//int steps[]={3,4,1,5};
//#define STEPLEN 4
//#define LEN 5

int steps[]={97,167,54,178,2,11,209,174,119,248,254,0,255,1,64,190};
#define STEPLEN 16
#define LEN 256

int list[LEN];
int buf[LEN];

void setup(){
	for(int x=0;x<LEN;x++){
		list[x]=x;
	}
}

void printlist(int curpos){
	for(int x=0;x<LEN;x++){
		printf(" %d",list[x]);
		if(x==curpos) printf("*");
	}
	printf("\n");
}

void reverseslice(int start, int size){
	// store current
	int listpos=start;
	for(int x=0;x<size;x++){
		buf[x]=list[listpos];
		listpos++;
		if(listpos==LEN) listpos=0;
	}
	// reverse
	for(int x=0;x<size;x++){
		listpos--;
		if(listpos==-1) listpos=LEN-1;
		list[listpos]=buf[x];
	}
	
}


void run(){
	int curpos=0;
	int skipsize=0;
	printlist(curpos);
	for(int n=0;n<STEPLEN;n++){
		int step=steps[n];
		reverseslice(curpos,step);
		curpos+=step+skipsize;
		if(curpos>=LEN) curpos-=LEN;
		skipsize++;
		printlist(curpos);
		
	}
	printf("Result:%d\n",list[0]*list[1]);

}


void main(){
	setup();
	run();
}
