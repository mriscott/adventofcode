#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int suffix[]={17,31,73,47,23};
#define SUFFIXLEN 5
#define MAXSTEPS 100
int steps[MAXSTEPS];
#define LEN 256



#define BYTE_TO_BINARY_PATTERN "%c%c%c%c"
#define BYTE_TO_BINARY(byte)  \
  (byte & 0x08 ? '#' : '.'), \
  (byte & 0x04 ? '#' : '.'), \
  (byte & 0x02 ? '#' : '.'), \
  (byte & 0x01 ? '#' : '.') 

int list[LEN];
int buf[LEN*LEN];
char map [128][33];

char regions[128][128];


void setuplist(){
  for(int x=0;x<LEN;x++){
    list[x]=x;
  }
}

void setupsteps(char* input){
  char *ptr=input;
  int x=0;
  while(*ptr!=0){
    steps[x]=*ptr;
    ptr++;
    x++;
  }
  for(int y=0;y<SUFFIXLEN;y++){
    steps[x]=suffix[y];
    x++;
  }
  steps[x]=-1;
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


void run( char *hash){
  int curpos=0;
  int skipsize=0;
  // printlist(curpos);
  for(int ii=0;ii<64;ii++){
    //    printf("Round %d\n",ii);
    int step=steps[0];
    int n=0;
    while(step!=-1){
      if(n>MAXSTEPS) {
	printf("Index out of bounds - step %d\n",n);
	exit(1);
      }
      //      printf("Step:%d , reversing %d from %d\n",n,step,curpos);
      reverseslice(curpos,step);
      curpos+=step+skipsize;
      if(curpos>=LEN) curpos=curpos%LEN;
      skipsize++;
      //printlist(curpos);
      n++;
      step=steps[n];
    }
  }
  
  // now calc values
  
  int val;
  for(int x=0;x<16;x++){
    val=list[x*16];
    //printf("Val %d:%02x\n",x*16,val);
    for(int y=1;y<16;y++){
      val=val^list[x*16+y];
      //printf("Val %d:%02x -> %02x\n",x*16+y,list[x*16+y],val);
    }
    //printf("%0x",val);
    //printf("\n");
    sprintf(hash,"%02x",val);
    hash+=2;
  }
  *hash=0;
}

void verify(char *input, char* hash){
  printf("Testing input '%s'\n",input);
  setuplist();
  setupsteps(input);
  char actual[32];
  run(actual);
  if(strcmp(hash,actual)!=0){
    printf("Expected %s, got %s\n",hash,actual);
    exit(1);
  }
}

char * getHash(char *input, char *hash){
  setuplist();
  setupsteps(input);
  run(hash);
  return hash;
}

void testknot(){
  verify("","a2582a3a0e66e6e86e3812dcb672a272");
  verify("AoC 2017","33efeb34ea91902bb2f59c9920caa6cd");
  verify("1,2,3","3efbe78a8d82f29979031a4aa0b16a9d");
  verify("1,2,4","63960835bcdc130f0b66d7ff4f6a5a8e");
}


int charval(char c){
  char val=0;
  if(c>='0' && c <='9'){
    val=c-'0';
  }
  if (c>='a' && c<='z'){
    val=c-'a';
    val+=10;
  }
  return val;
}

void binprint(char c){
  int val=charval(c);
  printf(BYTE_TO_BINARY_PATTERN, BYTE_TO_BINARY(val));
}

int hexval(char c){
  char byte =charval(c);
  int num = (byte & 0x08 ? 1 : 0) +
  (byte & 0x04 ? 1 : 0) +
  (byte & 0x02 ? 1 : 0) +
    (byte & 0x01 ? 1 : 0) ;
  return num;
}

int hexcharnum(char c){
  char byte =charval(c);
  int num = (byte & 0x08 ? 1000 : 0) +
  (byte & 0x04 ? 100 : 0) +
  (byte & 0x02 ? 10 : 0) +
    (byte & 0x01 ? 1 : 0) ;
  return num;
}


int getUsedSquares(char * root){
  
  char hash[32];
  char input[]="flqrgnkx-aaa";

  int tot=0;
  for (int x=0;x<128;x++){
    sprintf(input,"%s-%d",root,x);
    printf("Hashing %s ...",input);
    getHash(input,hash);
    printf("%s",hash);
    strcpy(map[x],hash);
    char * ptr=hash;
    
    while(*ptr!=0){
      tot+=hexval(*ptr);
      ptr++;
    }
    printf("=%d\n",tot);
    
  }
  return tot;
}


int getRegions(){
  for(int x=0;x<128;x++){
    char *ptr = map[x];
    int y=0;
    while(*ptr!=0){
      char byte =charval(*ptr);
      regions[x][y]=byte&0x08 ? 1 : 0;
      y++;
      regions[x][y]=byte&0x04 ? 1 : 0;
      y++;
      regions[x][y]=byte&0x02 ? 1 : 0;
      y++;
      regions[x][y]=byte&0x01 ? 1 : 0;
      y++;
      ptr++;
    }
  }

  // and print them all
  for(int x=0;x<128;x++){
    for(int y=0;y<128;y++){
      printf("%d",regions[x][y]);
    }
    printf("\n");
  }
  return -1;
}




void main(){
  testknot();
  if(getUsedSquares("flqrgnkx")!=8108){
   printf("FAIL\n");
   exit(1);
  }
  if(getRegions("flqrgnkx")!=1242){
    printf("FAIL - regions");
    exit(1);
  }

  printf("Answer:%d\n",getUsedSquares("nbysizxe"));
  

}
