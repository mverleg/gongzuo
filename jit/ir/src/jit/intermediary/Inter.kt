package jit.intermediary

/**
 * A hardware inters that the {@link HighProcessor} can run.
 *
 * ('Inter' can be taken to mean 'intermediate')
 */
interface Inter<RT> {
    fun run(processor: HighProcessor): RT

    fun toText(): CharSequence
}


