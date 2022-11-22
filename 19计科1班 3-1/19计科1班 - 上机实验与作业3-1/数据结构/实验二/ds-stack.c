//
// Created by KChankc on 2021/11/7.
//

#include <stdlib.h>
#include <stdio.h>

typedef int Status;
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0

typedef int ElementType;

#define STACK_INIT_SIZE 100
#define STACK_INCREMENT 10

typedef struct Stack {
    ElementType *base;
    // 栈顶
    ElementType *top;
    // 已分配存储空间
    int stackSize;
} Stack;


/**
 * 创建空栈
 */
Status initStack(Stack *stack) {
    stack->base = (ElementType *) malloc(STACK_INIT_SIZE * sizeof(ElementType));

    if (!stack->base) {
        exit(ERROR);
    }

    stack->top = stack->base;
    stack->stackSize = STACK_INIT_SIZE;

    return OK;
}

/**
 * 判断是否为空栈
 */
Status isStackEmpty(Stack stack) {
    if (stack.top == stack.base) {
        return TRUE;
    } else {
        return FALSE;
    }
}

/**
 * 获取栈顶元素
 */
Status getTop(Stack stack, ElementType *e) {
    if (isStackEmpty(stack) == TRUE) {
        return ERROR;
    }

    *e = *(stack.top - 1);
    return OK;
}

Status push(Stack *stack, ElementType e) {
    if (stack->top - stack->base >= stack->stackSize) {
        // 已满，扩容栈
        // stack->base = (ElementType *) realloc(stack->base, (stack->stackSize + STACK_INCREMENT) * sizeof(ElementType));

        // 栈满
        return ERROR;
    }

    // if (!stack->base) {
    //     exit(ERROR);
    // }

    // 更新栈顶位置
    // stack->top = stack->base + stack->stackSize;
    // 更新栈长度
    // stack->stackSize += STACK_INCREMENT;

    *stack->top++ = e;

    return OK;
}

Status pop(Stack *stack, ElementType *e) {
    if (isStackEmpty(*stack) == TRUE) {
        // *e = NULL;
        return ERROR;
    }

    *e = *--stack->top;

    return OK;
}

/**
 * 判断是否为运算符
 *
 * @param c 需要判断的字符
 * @param op 所有运算符
 * @return
 */
Status in(ElementType c) {
    switch (c) {
        case '+':
        case '-':
        case '*':
        case '/':
        case '(':
        case ')':
        case '\n':
            return TRUE;
        default:
            return FALSE;
    }
}

/**
 * 比较两个运算符的优先级
 *
 * `0`表示不可能出现的比较
 *
 * @param a 待比较的运算符
 * @param b 待比较的运算符
 */
char precede(ElementType a, ElementType b) {
    int i, j;
    char pre[][7] = {
            {'>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '>', '>'},
            {'<', '<', '<', '<', '<', '=', '0'},
            {'>', '>', '>', '>', '0', '>', '>'},
            {'<', '<', '<', '<', '<', '0', '='}};

    switch (a) {
        case '+':
            i = 0;
            break;
        case '-':
            i = 1;
            break;
        case '*':
            i = 2;
            break;
        case '/':
            i = 3;
            break;
        case '(':
            i = 4;
            break;
        case ')':
            i = 5;
            break;
        case '\n':
            i = 6;
            break;
        default:
            i = -1;
            break;
    }
    switch (b) {
        case '+':
            j = 0;
            break;
        case '-':
            j = 1;
            break;
        case '*':
            j = 2;
            break;
        case '/':
            j = 3;
            break;
        case '(':
            j = 4;
            break;
        case ')':
            j = 5;
            break;
        case '\n':
            j = 6;
            break;
        default:
            j = -1;
            break;
    }

    return pre[i][j];
}

/**
 * 运算
 *
 * @param a 操作数1
 * @param theta 操作符
 * @param b 操作数2
 */
int operate(ElementType a, ElementType theta, ElementType b) {
    switch (theta) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            return a / b;
        default:
            return -1;
    }
}

ElementType evaluateExpression() {
    // 定义运算符栈、运算数栈
    Stack OPTR, OPND;

    ElementType a, b, c, x, result;

    // 初始化栈
    initStack(&OPTR);
    initStack(&OPND);

    push(&OPTR, '\n');

    // 键盘读入1个字符
    c = getchar();

    // 将运算符栈OPTR的栈顶元素赋给x
    getTop(OPTR, &x);

    // 当前读入的字符 或 运算符栈顶元素不为`#`时
    while (c != '\n' || x != '\n') {
        if (in(c)) {
            printf("是操作符: %c\n", c);
            printf("栈顶操作符x: %c\n", x);
            // 是运算符
            switch (precede(x, c)) {
                // 判断优先级
                case '<':
                    // 输入字符大于操作符栈栈顶运算符，入栈c，读入下一个字符
                    printf("操作符入栈\n");
                    push(&OPTR, c);
                    c = getchar();
                    break;
                case '=':
                    // 去括号，读入下一个字符
                    printf("去括号\n");
                    pop(&OPTR, &x);
                    c = getchar();
                    break;
                case '>':
                    printf("弹出操作符栈顶操作符并运算\n");
                    // 输入字符小于操作符栈顶运算符，出栈栈顶操作符并执行运算，并把运算结果入栈操作数栈
                    // 弹出运算符
                    pop(&OPTR, &x);
                    // 两个操作数
                    pop(&OPND, &b);
                    pop(&OPND, &a);
                    push(&OPND, operate(a, x, b));
                    break;
                default:
                    printf("啥也不是\n");
                    return -1;
            }
        } else if (c >= '0' && c <= '9') {
            printf("是操作数: %d\n", c - 48);
            // 操作数
            // 入栈操作数栈，读入下一个字符
            push(&OPND, c - 48);
            c = getchar();
        }

        // 将运算符栈的栈顶元素赋给x
        getTop(OPTR, &x);
    }

    // 弹出操作数栈的栈顶元素（运算结果）给x
    pop(&OPND, &result);

    // 运算数栈不空，运算符栈仅剩`\n`
    if (!isStackEmpty(OPND)) {
        printf("表达式不正确\n");
        exit(ERROR);
    }

    return result;
}

int main() {
    printf("表达式的值: %d\n", evaluateExpression());

    return 0;
}
