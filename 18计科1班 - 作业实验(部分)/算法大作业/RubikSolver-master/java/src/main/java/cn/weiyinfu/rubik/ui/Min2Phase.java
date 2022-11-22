package cn.weiyinfu.rubik.ui;

import cn.weiyinfu.rubik.three.min2phase.Min2PhaseSolver;

/**
 * 三阶魔方两阶段法魔方求解器
 */
public class Min2Phase {
public static void main(String[] args) {
    new WindowInput(new Min2PhaseSolver());
}
}
