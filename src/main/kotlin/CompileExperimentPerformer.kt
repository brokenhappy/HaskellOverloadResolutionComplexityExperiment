import kotlin.system.measureTimeMillis

class CompileExperimentPerformer {
    fun measureExecutionTimeMillisFor(experiment: CompileExperiment, n: Int): Long {
        experiment.basePath.resolve(experiment.compiledFilePath).toFile()
            .writeText(experiment.generateCode(n))

        return measureTimeMillis {
            experiment.executionCommand
                .executingIn(experiment.basePath)
                .start()
                .waitFor()
        }
    }
}