package jit.common

class InstructionList(vararg instructions: Instruction) {
    val instructions: Array<out Instruction> = instructions
}
