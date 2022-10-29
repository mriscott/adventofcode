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

int test(){
  if((isTls("abba[mnop]qrst")==1)
     && (isTls("abcd[bddb]xyyx")==0)
     && (isTls("aaaa[qwer]tyui")==0)
     && (isTls("ioxxoj[asdfgh]zxcvbn")==1)) {
    printf("All tests passed\n");
    return 1;
  }else{
    printf("TEST FAILED\n");
    return 0;
  }
}


int main (int argc, char * argv[]){
  test();
  printf("IPs with TLS: %d\n",countTls("input.txt"));
  return 0;
}
