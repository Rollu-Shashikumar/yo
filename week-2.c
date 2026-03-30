#include<stdio.h>
int main()
{
    char*str="Helloworld";
    int i;
    printf("OriginalString:%s\n\n",str);
    printf("ANDwith127:\n");
    for(i=0; str[i]!='\0'; i++)
    {
        printf("'%c'AND127=%d\n",str[i],str[i]&127);
    }
    printf("\nXORwith127:\n");
    for(i=0; str[i]!='\0'; i++)
    {
        printf("'%c'XOR127=%d\n",str[i],str[i]^127);
    }
    return 0;
}
