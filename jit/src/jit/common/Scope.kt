package jit.common

import jit.instructions.DeallocateInstruction
import jit.instructions.Variable

/**
 * Scopes, such as functions and loops. Compile time. Lexical.
 */
class Scope(val context: HasScope) {

    var generateIndex = 0
    val variables: LinkedHashMap<String, Variable> = LinkedHashMap<String, Variable>()

    fun register(variable: Variable) {
        variables.put(variable.name.value, variable)
    }

    fun generateDeallocations(): List<DeallocateInstruction> {
        val deallocations: MutableList<DeallocateInstruction> = mutableListOf()
        for (variable in variables.values) {
            deallocations.add(DeallocateInstruction(variable))
        }
        variables.clear()
        return deallocations
    }

    // TODO: this can be called before normal variables are all declared, leading to future duplicates
    fun createTempVar(type: Type): Variable {
        while (true) {
            val name = genName(generateIndex)
            generateIndex++
            if (!variables.containsKey(name)) {
                val variable = Variable(Name(name), type)
                this.register(variable)
                return variable
            }
        }
    }

    fun getVar(name: Name): Variable? {
        if (variables.containsKey(name.value)) {
            return variables.get(name.value)
        }
        return null
    }

    fun toText(): CharSequence {
        val text = StringBuilder(" scope for ${context}; contains: ")
        text.append(variables.values.map{ it.name }.joinToString(", "))
        return text
    }
}

interface HasScope {
}

class Globally: HasScope {
}

interface ScopeStack {
    fun nearest(): Scope
    fun getVar(name: Name): Variable?
    fun toText(): CharSequence
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

    override fun getVar(name: Name): Variable? {
        for (scope in scopes.reversed()) {
            val variable = scope.getVar(name)
            if (variable != null) {
                return variable
            }
        }
        return null
    }

    override fun toText(): String {
        val text = StringBuilder("Scopes (${scopes.size}):\n")
        for (scope in scopes.reversed()) {
            text.append(scope.toText())
            text.append('\n')
        }
        return text.toString()
    }
}

