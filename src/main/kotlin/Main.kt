import compileExperiments.*

class Main {
    private class WeightedDurations(val arr: LongArray, val weight: Int) {
        operator fun plus(other: WeightedDurations) = WeightedDurations(
            LongArray(arr.size) { arr[it] + other.arr[it] },
            weight + other.weight
        )

        val averages get() = arr.map { it / weight }
    }

    companion object {
        private val compileExperimentPerformer = CompileExperimentPerformer()

        private fun measure(attempts: Iterable<Int>, experiment: CompileExperiment) =
            (0..5)
                .also { println(experiment.description) }
                .map {
                    attempts.map { i ->
                        compileExperimentPerformer
                            .measureExecutionTimeMillisFor(experiment, n = i)
                    }
                }
                .map { WeightedDurations(it.toLongArray(), 1) }
                .reduce(WeightedDurations::plus)
                .averages

        @JvmStatic
        fun main(args: Array<String>) = sequence {
            yield(measure(0..1000 step 50, SwiftTwoOverloadExperiment(PrivateEnvironment)))
            yield(measure(0..1000 step 50, SwiftOneImplementationExperiment(PrivateEnvironment)))
            yield(measure(0..11   step 1 , SwiftManyOverloadsIncludingGenericsExperiment(PrivateEnvironment)))
            yield(measure(0..1000 step 50, HaskellCompileExperiment(PrivateEnvironment)))
            yield(measure(0..17   step 1 , SwiftNonGenericClosureCompileExperiment(PrivateEnvironment)))
        }.forEach { println(it) }
    }
}