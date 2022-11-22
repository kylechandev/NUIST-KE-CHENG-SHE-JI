#include <stdio.h>
#include <stdlib.h> 

typedef struct Node { 
	struct Node* next;
	int element;
} Node;


void printNode(Node *node){
	node = node->next;
	printf("Node: ");
	
	while(node) {
		printf("%d ", node->element);
		node = node->next;
	}
	
	printf("\n");
}


Node *addElement2Node(Node *tail, int element){
	Node *n = (Node *) malloc(sizeof(Node));
	n->element = element;
	n->next = NULL;
	tail->next = n;
	tail = n;
	
	return tail;
}

Node *createNode() {
	Node *header = malloc(sizeof(Node));
	Node *tail = header;
	
	int i = 0;
	for (i=0; i < 8; i++) {
		tail = addElement2Node(tail, i);
	}
	
	return header;
}

Node *merge(Node *a, Node *b) {
	Node *ha = a->next;
	Node *hb = b->next;
	
	Node *c = a;
	Node *hc = c;
	
	while (ha && hb) {
		if (ha->element <= hb->element){
			c->next = ha;
			c = ha;
			ha = ha->next;
		} else {
			c->next = hb;
			c = hb;
			hb = hb->next;
		}
	}
	
	c->next = ha ? ha : hb;
	
	return hc;
}

Node *createNodeByInput() {
	int x;
	scanf("%d", &x);
	Node *header = (Node *) malloc(sizeof(Node));
	Node *tail = header;
	while(x) {
		tail = addElement2Node(tail,x);
		scanf("%d", &x);
	}
	
	return header;
}


int main() {
	
	Node *header = createNode();
	
	printNode(header);
	
	Node *l1 = createNodeByInput();
	printf("\n");
	Node *l2 = createNodeByInput();
	
	printNode(merge(l1, l2));
	
	return 0;
} 
