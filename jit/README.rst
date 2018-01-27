
JIT
===============================

The input code, here represented as an abstract syntax tree (AST), is converted to executable bytes, here represented as executable statements that are interpreted by a processor class.

Process structure
-------------------------------

This package starts at AST and skips uniquifying and optimizing.

TODO: find or invent better names for all stages

* *Reading*
* **Text**
* *Lexing*
* **Tokens**
* *Parsing*: connect the tokens
* **fast** (full abstract syntax tree)
* *Uniquify*: map multiple statements to the same construct
* **last** (limited abstract syntax tree, subset of *FAST*)
* *Semanticate* (semantic analysis): check types and signatures, connect variables
* **IR** intermediary representation
* *PI-opt* machine-independent optimizations
* **IR** (still)
* *Generation*
* **target** representation at low or high level, depending on target; also optimized and preliminary mode
* *PD-opt* platform-dependent optimizations
* **target** (still)
* *Writing*
* **assembly** or other runnable target

There is also a *Builder* that coordinates the process for multiple files, and a *Linker*, which communicates at the semantication step.

There might at some point be a macro/template/... processor, but not yet, and maybe never.

Compilation
-------------------------------

The unit of just-in-time compilation is a function, since this is a proof of concept.

The executable code exists in three stages:

1. A statements that compiles the AST in preliminary mode when executed (see next point), then runs that.
2. Debug code, which includes benchmarking code and which does not include slow optimizations. After being called often, it is optimized (see next point).
3. Optimized mode, which is compiled more slowly with many optimizations, based on the runtime benchmarking of the debug code.

The processor can only run a limited set of inters.

Language
-------------------------------

The input language is intentionally simple. It only has expressions, which return either integers or booleans.

Features include:

* Variables (only integers), which are all in function-scope.
* Functions (which are compiled on-demand). Functions always return an integer, and take any number of integers as input. They are all top-level.
* If statements, which return an integer.
* Logical operations, which are only used for if statement conditions.
* Arithmetic operations.
* Constants.

Loops may be added in the future.

Compilation
-------------------------------

There are two compilation targets:

* **Low** level is single-assignment mode and made of basic blocks, with only jumps as control flow structures. It somewhat resembles LLVM.
* **High** level allows restricted constructs such as if and while but no jumps, and exists in tree form. It somewhat resembles Javascript (or any other high-level imperative language).

Each has it's own processor.

The representations are:

1. **Source**, which is represented as an abstract syntax tree (lexing and parsing are out of scope).
2. **Intermediary**, which is type-checked and cross-referenced, removes redundant constructs and where optimizations are done (if any are ever implemented).
3. **Target**, which is either the low or high level representation.

Notes
-------------------------------

LLVM IR is quite low-level.

* Possibly when compiling to LLVM, there should be another intermediary language (without if statements and loops), which is not necessary for Javascript.
* Some optimizations are easier or only possible at LLVM IR level and should be done by LLVM itself, but some safety guarantees are lost by converting to more expressive statements, potentially disabling other optimizations.


