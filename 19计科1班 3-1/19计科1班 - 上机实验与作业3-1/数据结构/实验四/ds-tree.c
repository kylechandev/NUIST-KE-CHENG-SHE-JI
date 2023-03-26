//
// Created by KChankc on 2021/11/23.
//

// 建立二叉树
// 先序遍历二叉树
// 中序遍历二叉树
// 后序遍历二叉树
// 计算二叉树结点总数
// 计算二叉树深度

#include <printf.h>
#include <stdlib.h>

typedef char TElemType;

typedef struct BiTNode {
    // 数据
    TElemType data;
    // 左右子树
    struct BiTNode *left, *right;
} BiTNode, *BiTree;

/**
 * 创建二叉树
 */
void createBiTree(BiTree *biTree) {
    char ch;

    scanf("%c", &ch);

    if (ch == '#') {
        *biTree = NULL;
    } else {
        if (!(*biTree = (BiTNode *) malloc(sizeof(BiTNode)))) exit(-1);

        // 数据
        (*biTree)->data = ch;
        // 左右结点
        createBiTree(&(*biTree)->left);
        createBiTree(&(*biTree)->right);
    }
}

/**
 * 先序遍历二叉树
 */
void preOrderTraverse(BiTree biTree) {
    if (biTree == NULL) return;

    printf("%c ", biTree->data);
    preOrderTraverse(biTree->left);
    preOrderTraverse(biTree->right);
}

/**
 * 中序遍历二叉树
 */
void inOrderTraverse(BiTree biTree) {
    if (biTree == NULL) return;

    inOrderTraverse(biTree->left);
    printf("%c ", biTree->data);
    inOrderTraverse(biTree->right);
}

/**
 * 后序遍历二叉树
 */
void postOrderTraverse(BiTree biTree) {
    if (biTree == NULL) return;

    postOrderTraverse(biTree->left);
    postOrderTraverse(biTree->right);
    printf("%c ", biTree->data);
}

/**
 * 计算二叉树结点总数
 */
int getBiTreeSize(BiTree biTree) {
    if (biTree == NULL) return 0;

    // 左节点数量
    int leftSize = getBiTreeSize(biTree->left);
    // 右结点数量
    int rightSize = getBiTreeSize(biTree->right);

    // 左右结点数量 + 根结点
    return leftSize + rightSize + 1;
}

/**
 * 计算二叉树深度
 */
int getBiTreeDepth(BiTree biTree) {
    if (biTree == NULL) return 0;

    // 左节点深度
    int leftDepth = getBiTreeDepth(biTree->left);
    // 右结点深度
    int rightDepth = getBiTreeDepth(biTree->right);

    if (leftDepth > rightDepth) {
        return leftDepth + 1;
    } else {
        return rightDepth + 1;
    }
}

int main() {
    BiTree biTree;
    createBiTree(&biTree);

    printf("前序遍历：");
    preOrderTraverse(biTree);
    printf("\n");

    printf("中序遍历：");
    inOrderTraverse(biTree);
    printf("\n");

    printf("后序遍历：");
    postOrderTraverse(biTree);
    printf("\n");

    printf("结点总数：%d\n", getBiTreeSize(biTree));

    printf("深度：%d\n", getBiTreeDepth(biTree));

    return 0;
}
