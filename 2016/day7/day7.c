#include <stdio.h>
#include <stdlib.h>


int isTls(char * str){
  int sq=0;
  int i=0;
  int abba=0;
  int habba=0;
  for(i=0;str[i]!=0;i++){
    if(str[i]=='['){
      sq=1;
      continue;
    }
    if(str[i]==']'){
      sq=0;
      continue;
    }
    if(i<3) continue;
    if(str[i]==str[i-3] && str[i-1]==str[i-2] && str[i]!=str[i-1]){
      if(sq) habba=1;
      else abba=1;
    }
    
  }

  if(abba==1 && habba==0) return 1;
  else return 0;
}
int isSsl(char * str){
  int sq=0;
  int i=0;
  int s=0;
  int aba=0;
  int bab=0;
  int isok=0;
  char  abac[100];
  char babc[100];
  printf("%s\n",str);
  for(i=0;str[i]!=0;i++,s++){
    if(str[i]=='['){
      sq=1;
      s=0;
      continue;
    }
    if(str[i]==']'){
      sq=0;
      s=0;
      continue;
    }
    if(s<2) continue;
    if(str[i]==str[i-2] && str[i-1]!=str[i-2]){
      if(!sq) {
	babc[bab*2]=str[i];
	babc[bab*2+1]=str[i-1];
	bab++;
	printf("BAB:%c%c%c\n",str[i-2],str[i-1],str[i]);
      }
      else {
	abac[aba*2]=str[i];
	abac[aba*2+1]=str[i-1];
	aba++;
	printf("ABA:%c%c%c\n",str[i-2],str[i-1],str[i]);
      }
    }
    
  }
  for(int abai=0;abai<=aba;abai+=2){
    for(int babi=0;babi<=bab;babi+=2){
      if(abac[abai]==babc[babi+1] &&
	 abac[abai+1]==babc[babi]){
	isok=1;
      }
    }
  }

  return isok;
}

int countTls(char * filename){
  int c=0;
  FILE *fp = fopen(filename, "r");
  if(fp == NULL) {
    perror("Unable to open file!");
    exit(1);
  }
  
  char chunk[128];
  
  while(fgets(chunk, sizeof(chunk), fp) != NULL) {
    if(isTls(chunk)) c++;
  }
  
  fclose(fp);
  return c;
}

int countSsl(char * filename){
  int c=0;
  FILE *fp = fopen(filename, "r");
  if(fp == NULL) {
    perror("Unable to open file!");
    exit(1);
  }
  
  char chunk[128];
  
  while(fgets(chunk, sizeof(chunk), fp) != NULL) {
    if(isSsl(chunk)) c++;
  }
  
  fclose(fp);
  return c;
}

int test(){
  int ret=1;
  if((isTls("abba[mnop]qrst")==1)
     && (isTls("abcd[bddb]xyyx")==0)
     && (isTls("aaaa[qwer]tyui")==0)
     && (isTls("ioxxoj[asdfgh]zxcvbn")==1)) {
    printf("Part 1 tests passed\n");
  }
  else{
    printf("PART 1 TESTS FAILED\n");
    ret=0;
  }
  if((isSsl("aba[bab]xyz")==1) &&
     (isSsl("xyx[xyx]xyx")==0) &&
     (isSsl("xyx[xyx]sesyxy")==1) &&
     (isSsl("aaa[kek]eke")==1) &&
     (isSsl("zazbz[bzb]cdb")==1)){
    printf("Part 2 tests passed\n");
  }else{
    printf("PART 2 TESTS FAILED\n");
    ret=0;
  }
    
  
  return ret;
}



int main (int argc, char * argv[]){
  test();
  printf("IPs with TLS: %d\n",countTls("input.txt"));
  printf("IPs with SSL: %d\n",countSsl("input.txt"));
  return 0;
}
