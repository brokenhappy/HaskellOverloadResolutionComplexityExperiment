class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val compileExperimentPerformer = CompileExperimentPerformer()
            val haskellCompileExperiment = HaskellCompileExperiment(PrivateEnvironment)
            println((0..500 step 20).map { i ->
                compileExperimentPerformer
                    .measureExecutionTimeMillisFor(haskellCompileExperiment, n = i)
            })
        }
    }
}