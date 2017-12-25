package jit.common

/**
 * A hardware inters that the {@link HighProcessor} can run.
 *
 * ('Inter' can be taken to mean 'intermediate' or '???')
 * TODO(omfg I forgot the other meaning)
 */
interface Inter<RT> {
    fun run(processor: HighProcessor): RT

    fun toText(): CharSequence
}


