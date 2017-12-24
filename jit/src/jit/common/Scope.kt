package jit.common

import jit.code.VarCode
import jit.instructions.DeallocateInstruction

class Scope(val context: HasScope) {

    var generateIndex = 0;
    val variables: MutableSet<Name> = LinkedHashSet<Name>()

    fun register(name: Name) {
        variables.add(name)
    }

    fun generateDeallocations(): List<DeallocateInstruction> {
        val deallocations: MutableList<DeallocateInstruction> = mutableListOf()
        for (variable in variables) {
            deallocations.add(DeallocateInstruction(variable))
        }
        variables.clear()
        return deallocations
    }

    // TODO: this can be called before normal variables are all declared, leading to conflicts
    fun createTempVar(): VarCode {
        while (true) {
            val name = Name(genName(generateIndex))
            generateIndex++
            if (name !in variables) {
                this.register(name)
                return VarCode(name)
            }
        }
    }
}

interface HasScope {
}

class Globally: HasScope {
}

interface ScopeStack {
    fun nearest(): Scope
}

class MutableScopeStack: ScopeStack {

    val scopes: MutableList<Scope> = mutableListOf(Scope(Globally()))

    fun push(context: HasScope) {
        scopes.add(Scope(context))
    }

    fun pop() {
        check(scopes.size > 0)
        scopes.removeAt(scopes.size - 1)
    }

    override fun nearest(): Scope {
        check(scopes.size > 0)
        return scopes[scopes.lastIndex]
    }
}

