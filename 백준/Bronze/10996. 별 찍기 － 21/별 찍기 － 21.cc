#include <stdio.h>

int main (){
	int n; 
	scanf("%d", &n); // N입력
	
	for(int i = 0 ; i < 2* n ; i++){
		if(i % 2 == 0){
			for(int j = 0 ; j < n;j++){
				if(j % 2 == 0){
					printf("*");
				}
				if(j % 2 == 1){
					printf(" ");
				}
			}
		}
		
		if(i % 2 == 1){
			for(int j = 0 ; j < n; j++){
				if(j % 2 == 1){
					printf("*");
				}
				if(j % 2 == 0){
					printf(" ");
				}
			}
		}
		printf("\n");
	}
	return 0;
}