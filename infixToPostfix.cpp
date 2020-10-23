#include <iostream>
#include <string>
using namespace std;


struct stack {
	int top;
	char* arr;
};

void push(struct stack* head, char a) {

		head->top++;
		head->arr[head->top] = a;
	}


char pop(struct stack* head) {

		char data = head->arr[head->top];
		head->top--;
		return data;
}

int main() {
	stack* a = new struct stack
			;
	string s;
	cin >> s;
	int l=s.length();
	a->top=-1;
	a->arr= new char[l];

	for(int i=0;i<l;i++){
		if((s[i]=='+' || s[i]=='-' ||s[i]=='/' || s[i]=='*') && a->top==-1)
			push(a,s[i]);
	   else if((s[i]=='+' || s[i]=='-') && a->top>-1){
			if(a->arr[a->top]=='+'||a->arr[a->top]=='-'||a->arr[a->top]=='/' ||a->arr[a->top]=='*'){
				while(a->arr[a->top]=='+'||a->arr[a->top]=='-' ||a->arr[a->top]=='/' ||a->arr[a->top]=='*')
			 cout << pop(a);
             push(a,s[i]);
			}
			else
				push(a,s[i]);
		}
		else if((s[i]=='/' || s[i]=='*') && a->top>-1){
				if(a->arr[a->top]=='/' ||a->arr[a->top]=='*'){
					while(a->arr[a->top]=='/' ||a->arr[a->top]=='*')
				 cout << pop(a);
				 push(a,s[i]);
				}
				else
					push(a,s[i]);
			}
		else{
			cout << s[i];
		}
	}
	while(a->top!=-1){
		cout<<pop(a);

	}
	return 0;
}
